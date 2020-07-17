"use strict";
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
/*
TODO
[ ] detect if state real or logical
[ ] ANY+ support
[ ] trace() support

*/
Object.defineProperty(exports, "__esModule", { value: true });
const Generator_1 = require("./Generator");
const input_1 = require("./input");
const State_1 = require("./State");
const StringUtils_1 = require("../ts-utils/StringUtils");
const MyRegex_1 = require("../ts-utils/MyRegex");
const Triggers = require("./Triggers");
const Misc_1 = require("../ts-utils/Misc");
const r = new MyRegex_1.MyRegex();
exports.ROOT_STATE_LABEL = "ROOT";
exports.ROOT_STATE_ID = 0;
class FlatCompiler {
    constructor() {
        this.hsm = new Generator_1.RenderedHsm();
        this.expander = new input_1.MacroExpander();
        this.inputValueFields = []; //TODO move to hsm
        this.outputValueFields = []; //TODO move to hsm
        this.outputEventFields = []; //TODO move to hsm
        this.classFullName = "ns_MySm";
        this.initialEntryActionCode = "";
    }
    expandUserMacrosAndGeneratorDirectives(text) {
        let output = text;
        let lastOutput = "";
        let iteration_count = 0;
        while (output != lastOutput) {
            lastOutput = output;
            output = this.replaceGeneratorDirectives(output);
            output = this.expander.expandText(output);
            iteration_count++;
            if (iteration_count > 100) {
                throw "Infinite loop in expansions detected. Check your expansions...";
            }
        }
        return output;
    }
    //TODO put in infinite loop handling
    replaceGeneratorDirectives(text) {
        let output;
        var tthis = this;
        //$stateNameToEnumName(PRESSED)
        output = text;
        output = this.expander.hideCommentAndStringInnards(text);
        output = output.replace(/[$]stateNameToEnumName[(]\s*(\w+)\s*[)]/g, function (match, stateName) {
            throw "No longer supported.";
            // let output = "";
            // output = tthis.stateGen.getStateEnumNameFromString(stateName);
            // return output;
        });
        output = output.replace(/[$][{]smStructName[}]/g, function (match) {
            let output = "";
            output = tthis.genStatemachineStructName();
            return output;
        });
        output = this.expander.unhideCommentAndStringInnards(output);
        return output;
    }
    compile(inputHsm) {
        this.hsm.inputHsm = inputHsm;
        let expansions = this.expandUserMacrosAndGeneratorDirectives(inputHsm.expansionDefinitions);
        this.expander.addMacroExpansions(expansions);
        //expand macros and remove yed //~ comments
        this.hsm.h_file_top = this.removeYedCommentsAndExpand(inputHsm.h_file_top);
        for (let i = 0; i < inputHsm.states.length; i++) {
            let inputState = inputHsm.states[i];
            let state = new State_1.State();
            state.label = inputState.label;
            state.inputState = inputState;
            this.hsm.addState(state);
        }
        this.inputValueFields = input_1.StructFieldParser.parse(this.hsm.inputHsm.inputValues);
        this.outputValueFields = input_1.StructFieldParser.parse(this.hsm.inputHsm.outputValues);
        this.outputEventFields = input_1.StructFieldParser.parse(this.hsm.inputHsm.outputEvents);
        this.hsm.reservedTriggers = [Triggers.ENTER, Triggers.EXIT, Triggers.DO];
        this.hsm.initialProcessAndValidate();
        //TODO don't render ROOT state. no point in having handlers in it.
        //optimization hack for now
        {
            let eh = new Generator_1.EventHandler();
            eh.guard = new Generator_1.Guard();
            eh.guard.guardCode = "true";
            eh.action = new Generator_1.Action();
            eh.action.actionCode = `sm->event_handled = true; //for loop efficiency`;
            this.hsm.rootState.addEventHandlers([eh]);
        }
        {
            let initial_states = this.hsm.getAllStates().filter(state => state.isInitialState);
            let tthis = this;
            function setInitialActionCode(state) {
                tthis.initialEntryActionCode = (state.eventHandlers[0].action || new Generator_1.Action()).actionCode || "";
            }
            for (const state of initial_states) {
                if (state.parent == this.hsm.rootState) {
                    this.rootInitialState = state;
                    setInitialActionCode(state);
                }
                if (state.isComplexInitialState()) {
                }
                else {
                    //simple initial state that can be removed
                    if (state.parent == this.hsm.rootState) {
                        this.rootInitialState = state.eventHandlers[0].nextState; //we know that the initial state has 1 event handler because of prior validation
                        setInitialActionCode(state);
                        this.hsm.removeState(state);
                    }
                }
            }
        }
        this.hsm.finalizeStates();
    }
    removeYedCommentsAndExpand(code) {
        let outputCode = "";
        if (code) {
            outputCode = code;
            outputCode = this.expandUserMacrosAndGeneratorDirectives(outputCode);
            outputCode = this.removeYedComments(outputCode);
            outputCode = StringUtils_1.StringUtils.removeBlankLines(outputCode);
        }
        return outputCode;
    }
    buildInputEventEnumName(label) {
        let output = "";
        switch (label.toUpperCase()) {
            case "ANY":
                let shouldThrow = 1;
                if (shouldThrow) {
                    throw "This should not be getting called";
                }
                break;
            default:
                output = `${this.getInputEventEnumTypeName()}__${label.toUpperCase()}`;
                break;
        }
        return output;
    }
    getInputEventEnumTypeName() {
        return `${this.classFullName}_EventId`;
    }
    genInputEventEnums() {
        let output = "";
        output += this.createCommentHeader(`Enumeration for all ${this.classFullName} input event IDs`);
        output += `typedef enum ${this.getInputEventEnumTypeName()}\n{\n`;
        let inputEvents = this.hsm.getAllNonDirectiveInputEventNames();
        let inner = "";
        //add in standard events first
        inner += `  ${this.buildInputEventEnumName(Triggers.DO)},\n`;
        for (let i = 0; i < inputEvents.length; i++) {
            let eventName = inputEvents[i];
            if (Triggers.isHsmEvent(eventName) || Triggers.isPseudoEventName(eventName)) {
                //do nothing
            }
            else {
                inner += `  ${this.buildInputEventEnumName(eventName)},\n`;
            }
        }
        inner = StringUtils_1.StringUtils.alignRegexInStringsSimple(/=/, inner);
        output += inner;
        output += `} ${this.getInputEventEnumTypeName()};\n`;
        return output;
    }
    createCommentHeader(header) {
        header = header.replace(new RegExp(r.nl, "g"), "\n* "); //replace all new lines so that we keep "*" on left side
        var output = `\n\n
/************************************************************************************************
* ${header}
************************************************************************************************/\n`;
        return output;
    }
    genEventHandlerType() {
        return `${this.classFullName}_EventHandler`;
    }
    genEventIdToStringFunctionName() {
        return `${this.classFullName}_InputEvent_to_string`;
    }
    genEventIdToStringPrototype() {
        let output = "";
        //hsm_t* hsm, uint32_t current_time
        output = `const char* ${this.genEventIdToStringFunctionName()}(${this.getInputEventEnumTypeName()} event_id)`;
        return output;
    }
    genEventIdToStringDefinition() {
        let output;
        output = this.createCommentHeader(`Function that translates a custom input event ID to a string\nNOTE: actual passed in enum values should be from '${this.getInputEventEnumTypeName()}'`);
        let inputEvents = this.hsm.getAllNonDirectiveInputEventNames();
        let switchInner = "";
        for (let i = 0; i < inputEvents.length; i++) {
            let eventName = inputEvents[i];
            if (Triggers.isHsmEvent(eventName) == false) {
                switchInner += `            case ${this.buildInputEventEnumName(eventName)}: str = "${eventName}"; break;\n`;
            }
        }
        if (switchInner.length == 0) {
            switchInner = "//no custom input events defined for this state machine";
        }
        else {
            switchInner = StringUtils_1.StringUtils.alignCompressedSwitch(switchInner).trim() + "\n";
        }
        output += StringUtils_1.StringUtils.properIndent(`
        ${this.genEventIdToStringPrototype()}
        {
          const char * str;
          switch(event_id)
          {
            ${switchInner}
            default: str = "??CUSTOM"; break;
          }

          return str;
        }
      `, "");
        return output;
    }
    genConstructorFunctionName() {
        return `${this.classFullName}_instance_init`;
    }
    genConstructorPrototype() {
        let output = "";
        output = `void ${this.genConstructorFunctionName()}(${this.genStatemachineStructName()}* sm)`;
        return output;
    }
    genConstructorDefinition() {
        let output = this.createCommentHeader(`Public constructor function for ${this.classFullName} state machine`);
        let peak = this.hsm.rootState;
        let actionCode = this.expandUserMacrosAndGeneratorDirectives(this.initialEntryActionCode);
        output += `${this.genConstructorPrototype()}\n{\n`;
        output += StringUtils_1.StringUtils.properIndent(`
${StringUtils_1.StringUtils.indent(actionCode, "      ")}
      enter_state(sm, ${this.classFullName}_StateId__${this.hsm.rootState.label});
${StringUtils_1.StringUtils.indent(this.genEnterCode(null, peak, this.rootInitialState), "      ")}
    }
    `, "");
        output += `\n`;
        return output;
    }
    genStatemachineVarStructName() {
        let output = `${this.classFullName}_Vars`;
        return output;
    }
    genStatemachineVarsStruct() {
        let output = "\n";
        output += this.createCommentHeader(`STRUCT for ${this.classFullName} variables `);
        output += `typedef struct ${this.genStatemachineVarStructName()}\n{\n`;
        let innards = this.hsm.inputHsm.varsStructInnerText || "  bool _unused;";
        output += Misc_1.indent(innards, " ", 0); //TODO auto detect if indentation required.
        output += "\n";
        output += `} ${this.genStatemachineVarStructName()};\n\n\n`;
        return output;
    }
    genStatemachineStructFieldsDefintion(headerText, structName, fields) {
        let output = "\n";
        output += this.createCommentHeader(`Struct for ${headerText}`);
        let innards = "";
        for (let field of fields) {
            innards += field.fullTextMatch.trim() + "\n";
        }
        output += StringUtils_1.StringUtils.properIndent(`
      typedef struct _${structName}
      {
        <innards_insert_point>
      } ${structName};
      `, "");
        output = output.replace(/^([ \t]*)<innards_insert_point>[ \t]*/gm, function (match, indent) {
            //console.log(`match: '${match}', indent:'${indent}'`);
            return StringUtils_1.StringUtils.properIndent(innards, indent);
        });
        //console.log(`output: '${output}'`);
        return output;
    }
    genStatemachineInputValuesStructName() {
        let output = `${this.classFullName}_InputValues`;
        return output;
    }
    genStatemachineInputValuesStruct() {
        if (this.inputValueFields.length == 0) {
            return "";
        }
        let output = this.genStatemachineStructFieldsDefintion(`input_values`, this.genStatemachineInputValuesStructName(), this.inputValueFields);
        return output;
    }
    genStatemachineOutputValuesStructName() {
        let output = `${this.classFullName}_OutputValues`;
        return output;
    }
    genStatemachineOutputValuesStruct() {
        if (this.outputValueFields.length == 0) {
            return "";
        }
        let output = this.genStatemachineStructFieldsDefintion(`output_values`, this.genStatemachineOutputValuesStructName(), this.outputValueFields);
        return output;
    }
    genStatemachineOutputEventsStructName() {
        let output = `${this.classFullName}_OutputEvents`;
        return output;
    }
    genStatemachineOutputEventsStruct() {
        if (this.outputEventFields.length == 0) {
            return "";
        }
        let output = this.genStatemachineStructFieldsDefintion(`output_events`, this.genStatemachineOutputEventsStructName(), this.outputEventFields);
        return output;
    }
    genStatemachineStructName() {
        return `${this.classFullName}`;
    }
    genStateIdTypeName() {
        return `${this.classFullName}_StateId`;
    }
    genStatemachineStruct() {
        let output = this.createCommentHeader(`STRUCT for ${this.classFullName} `);
        let inputValues = this.inputValueFields.length == 0 ? "" : `${this.genStatemachineInputValuesStructName()} input_values;\n\n`;
        let outputValues = this.outputValueFields.length == 0 ? "" : `${this.genStatemachineOutputValuesStructName()} output_values;\n\n`;
        let outputEvents = this.outputEventFields.length == 0 ? "" : `${this.genStatemachineOutputEventsStructName()} output_events;\n\n`;
        output += StringUtils_1.StringUtils.properIndent(`
      typedef struct ${this.genStatemachineStructName()}
      {
        bool event_handled;
        ${this.genStateIdTypeName()} state_id;

        ${inputValues}

        ${outputValues}

        ${outputEvents}

        ${this.genStatemachineVarStructName()} vars;
      } ${this.genStatemachineStructName()};
      `, "");
        output = "" + StringUtils_1.StringUtils.compressBlankLines(output);
        return output;
    }
    getOutputFilenameBase() {
        let result;
        if (this.hsm.inputHsm.output_filename) {
            result = this.hsm.inputHsm.output_filename;
        }
        else {
            //result = this.classFullName.toLowerCase().trim()
            result = this.classFullName.trim();
        }
        return result;
    }
    getSummaryFilename() {
        return this.getOutputFilenameBase() + "_summary.txt";
    }
    getHeaderFilename() {
        return this.getOutputFilenameBase() + ".h";
    }
    getSourceFilename() {
        return this.getOutputFilenameBase() + ".c";
    }
    genStateEnums() {
        let output = "";
        output += this.createCommentHeader(`Enumeration for all ${this.classFullName} state IDs`).trim() + "\n";
        output += `typedef enum ${this.genStateIdTypeName()}\n{\n`;
        let states = this.hsm.getAllStates();
        let inner = "";
        for (let i = 0; i < states.length; i++) {
            let state = states[i];
            inner += `  ${this.genStateIdTypeName()}__${state.label},\n`;
        }
        inner = StringUtils_1.StringUtils.alignRegexInStringsSimple(/=/, inner);
        output += inner;
        output += "  //--------------------------\n";
        output += `  ${this.classFullName}_StateCount,\n`;
        output += `} ${this.genStateIdTypeName()};\n`;
        return output;
    }
    removeYedComments(code, already_hidden = false) {
        if (already_hidden == false) {
            code = this.expander.hideCommentAndStringInnards(code);
        }
        //remove yed comments
        let tilde = this.expander.hideStringCharacters("~"); //tilde exists inside hidden comments. Need to hide it as well to match.
        code = code.replace(new RegExp(`^${r.mhs}//${tilde}.*${r.nl}`, "mg"), ""); //a full yed comment line "//~"
        code = code.replace(new RegExp(`^${r.mhs}/[*]${tilde}[^]*[*]/${r.nl}`, "mg"), ""); //a full yed comment line "/*~...*/"
        code = code.replace(new RegExp(`${r.mhs}//${tilde}.*$`, "mg"), ""); //a partial yed comment line "//~"
        code = code.replace(new RegExp(`${r.mhs}/[*]${tilde}[^]*[*]/`, "mg"), ""); //a partial yed comment line "/*~...*/"
        if (already_hidden == false) {
            code = this.expander.unhideCommentAndStringInnards(code);
        }
        return code;
    }
    postProcessCode(code) {
        code = this.expander.hideCommentAndStringInnards(code);
        //remove multiple redundant semicolons
        code = code.replace(/;(\s*;)+/g, ";");
        //remove yed comments
        code = this.removeYedComments(code, true);
        code = this.expander.unhideCommentAndStringInnards(code);
        //ensure file ends with a new line to avoid compiler warnings
        code = code.trim() + "\n";
        code = code.replace(new RegExp(`${r.nl}`, "g"), "\r\n");
        return code;
    }
    genPublicHeaderFile() {
        let fullOutput = this.genFileHeaderInfo() +
            `
#pragma once
#include <stdint.h>
#include <stdbool.h>

${this.hsm.h_file_top.trim()}
\n
` +
            this.genStateEnums() +
            this.genInputEventEnums() +
            this.genStatemachineInputValuesStruct() +
            this.genStatemachineOutputValuesStruct() +
            this.genStatemachineOutputEventsStruct() +
            this.genStatemachineVarsStruct() +
            this.genStatemachineStruct() +
            this.createCommentHeader("public functions") +
            this.genConstructorPrototype() + ";\n" +
            this.genEventIdToStringPrototype() + ";" +
            `
void ${this.classFullName}_dispatch_event(${this.classFullName}* sm, ${this.classFullName}_EventId event_id);

` +
            "";
        fullOutput = this.postProcessCode(fullOutput);
        return fullOutput;
    }
    genCustomPrototypes() {
        let output = this.buildExpandedSection("CUSTOM PROTOTYPES", this.hsm.inputHsm.cPrototypes);
        output += this.hsm.inputHsm.cPrototypesNoExp + "\n";
        return output;
    }
    buildExpandedSection(title, toExpand) {
        let output = "";
        toExpand = toExpand.trim();
        let text = this.expandUserMacrosAndGeneratorDirectives(toExpand);
        if (text) {
            output += this.createCommentHeader(title);
            output += text + "\n";
        }
        return output;
    }
    genEventHandlerPrototype(state) {
        return `static void ${state.label}_event_handler(${this.classFullName} *sm, ${this.classFullName}_EventId event_id)`;
    }
    genEventHandlerPrototypes() {
        let output = "\n\n";
        let states = this.hsm.getAllStates();
        output += this.createCommentHeader(`Handler Prototypes for ${this.classFullName}`).trim() + "\n";
        for (let state of states) {
            output += `${this.genEventHandlerPrototype(state)};\n`;
        }
        output = StringUtils_1.StringUtils.alignStringMarkersSimple(["("], output);
        return output;
    }
    genEventHanlderDefinitions() {
        let output = "";
        for (let state of this.hsm.getAllStates()) {
            output += "\n\n" + this.genEventHandlerDefinition(state);
        }
        return output;
    }
    genEventHandlerDefinition(state) {
        let inner = this.genNormalStateEventHandlers(state);
        let output = `
    <s>${this.genEventHandlerPrototype(state)}
    <s>{
    <s>  //TODO copy paste in entry and exit code in comments
    ${inner}
    <s>}

    `;
        output = StringUtils_1.StringUtils.processMarkers(output);
        return output;
    }
    genNormalStateEventHandlers(state) {
        let output = "";
        let joiner = "";
        // //render LANDED_IN next
        // hh = new Set<EventHandler>(restOfEvents);
        // hh.forEach(function(e: EventHandler, notNeeded: EventHandler, set: Set<EventHandler>){
        //   if(e.hasTrigger(Triggers.LANDED_IN)){
        //     output += joiner + tthis.genEventHandler(e);
        //     restOfEvents.delete(e);
        //   }
        // });
        for (const e of state.eventHandlers.filter(eh => !eh.hasTrigger(Triggers.ENTER) && !eh.hasTrigger(Triggers.EXIT))) {
            output += joiner + this.genEventHandler(state, e);
        }
        return output;
    }
    genEnterExitHandler(state, eh, skipEventTest = false) {
        let output = "";
        let expandedGuardCode = "";
        let guardText = "";
        let actionCode = "";
        if (eh.action) {
            actionCode += `${StringUtils_1.StringUtils.indent(eh.action.actionCode, "//")}\n`;
            actionCode += `${this.expandActionCode(state, eh, eh.noExpand)};`;
        }
        if (eh.guard && eh.guard.guardCode) {
            guardText = eh.guard.guardCode;
            expandedGuardCode += `(${this.expand(guardText, eh.noExpand)})`;
            output += `
      <s>      //if ${guardText}
      <s>      if ${expandedGuardCode}
      <s>      {
                  ${StringUtils_1.StringUtils.indent(actionCode, "<s>        ")}
      <s>      }
      `;
        }
        else if (actionCode) {
            output += `
        ${StringUtils_1.StringUtils.indent(actionCode, "<s>      ")}
      `;
        }
        if (eh.nextState) {
            throw "enter & exit can't transition"; //TODOLOW move to generator
        }
        output = StringUtils_1.StringUtils.processMarkers(output);
        return output;
    }
    getTxPeakState(from, dst) {
        if (from == dst) {
            return from.parent; //special case for self transitions
        }
        let peak = from;
        while (!peak.isAncestorOf(dst) && peak != dst) {
            peak = peak.parent;
        }
        return peak;
    }
    getStatesDownToDst(from, peak, dst) {
        let chain = [];
        let cur = dst;
        //special case for when self transitions
        if (from == dst) {
            return [from];
        }
        while (cur != peak) {
            chain.unshift(cur);
            cur = cur.parent;
        }
        return chain;
    }
    genEnterCode(from, peak, dst) {
        let inner = "";
        let chain = this.getStatesDownToDst(from, peak, dst);
        //may happen which child transitioning to parent
        if (chain.length == 0) {
            return "";
        }
        inner = chain.map(state => `  ${this.classFullName}_StateId__${state.label}`).join(",\n");
        //TODOLOW cleanup indenting
        let output = `${this.classFullName}_StateId states_to_enter[] = {
${inner}
};
enter_chain(sm, states_to_enter, COUNTOF(states_to_enter));`;
        return output;
    }
    genEventHandler(state, eh) {
        let output = "";
        let expandedGuardCode = "";
        let triggerCode = "";
        let triggerConditionWouldNeedBrackets = eh.triggersCount() > 1;
        let join = "";
        for (let trigger of eh.getTriggers()) {
            triggerCode += `${join}event_id == ${this.buildInputEventEnumName(trigger.toString())}`;
            join = " || ";
        }
        if (eh.hasTrigger(Triggers.ELSE)) {
            triggerCode = "true";
        }
        let guardText = "";
        if (eh.guard) {
            guardText = eh.guard.guardCode;
            if (triggerCode) {
                if (triggerConditionWouldNeedBrackets) {
                    triggerCode = `(${triggerCode})`; //surround in brackets so the && works properly
                }
                expandedGuardCode += " && ";
            }
            expandedGuardCode += `(${this.expand(guardText, eh.noExpand)})`;
        }
        let actionCode = "";
        if (eh.action) {
            actionCode = `${this.expandActionCode(state, eh, eh.noExpand)};`; //TODO someday: if external transition, use call back to call between exits and enters so that action code is actually run in middle of transition (AKA after all exits are fired).
            actionCode = "\n" + StringUtils_1.StringUtils.indent(actionCode, "<s>    ");
            //if check on DO at runtime because we can have multiple events together for a single handler
            actionCode = `
      <s>    if (event_id != ${this.classFullName}_EventId__DO) {
      <s>        sm->event_handled = true;  //done before action code to allow action code to allow bubbling
      <s>    }
      ` + actionCode;
        }
        let transitionCode = "";
        if (eh.nextState) {
            let tthis = this;
            const peak = this.getTxPeakState(state, eh.nextState);
            transitionCode = `
        <s>    //transitioning from ${state.label} to ${eh.nextState.label}
        <s>    exit_upto(sm, ${tthis.classFullName}_StateId__${peak.label});
        <s>
        <s>    //enter states
                ${StringUtils_1.StringUtils.indent(this.genEnterCode(state, peak, eh.nextState), "<s>    ")}
        <s>
        <s>    sm->event_handled = true;
        <s>    return; //stop processing because it transitioned
        <r>`;
        }
        let if_guard_text = "";
        if (guardText) {
            if_guard_text = " if " + guardText;
        }
        let if_trigger_text = "";
        if (eh.hasSomeTriggers()) {
            if_trigger_text = `ON ${Array.from(eh.getTriggers()).join(" or ")}`;
        }
        if (eh.hasTrigger(Triggers.ELSE)) {
            if_trigger_text = `ELSE `;
        }
        output += `
    <dummy>
    <s>  //${(if_trigger_text + if_guard_text).replace(/[\r\n]+/g, " ").replace(/[ ]{2,}|\\t/g, " ")}
    `.trim();
        output += `
    <s>  if (${triggerCode + expandedGuardCode}){ ${actionCode} ${transitionCode}
    <s>  }
    <s>
    `;
        output = StringUtils_1.StringUtils.processMarkers(output);
        return output;
    }
    expandActionCode(state, eh, noExpand) {
        if (noExpand) {
            return eh.action.actionCode;
        }
        let code = eh.action.actionCode;
        for (let i = 0; i < 10; i++) {
            code = this.expand(code, noExpand); //TODOLOW clean up & detect when done      
        }
        code = code.replace(/[$](handlerDescriptionString|handlerDescriptionStringNoAction)[$]/g, function (_, directive) {
            let handlerDescription = `${state.label}: `;
            let spacer = "";
            if (eh.hasSomeTriggers()) {
                if (eh.triggersCount() == 1) {
                    handlerDescription += `${eh.getTriggers()[0]}`;
                }
                else {
                    handlerDescription += `(${eh.getTriggers().join(",")})`;
                }
                spacer = " ";
            }
            if (eh.guard) {
                handlerDescription += spacer;
                handlerDescription += `[${eh.guard.guardCode}]`;
                spacer = " ";
            }
            if (eh.action && directive != "handlerDescriptionStringNoAction") {
                handlerDescription += spacer;
                handlerDescription += `/ {${eh.action.actionCode.replace(/\r|\n/g, "\\n")}}`;
                spacer = " ";
            }
            if (eh.nextState) {
                handlerDescription += spacer;
                handlerDescription += `-> ${eh.nextState.label}`;
            }
            return `"${handlerDescription}"`;
        });
        return code;
    }
    expand(textToExpand, preventExpand) {
        let output = textToExpand;
        if (preventExpand == false) {
            output = this.expander.expandText(textToExpand);
        }
        return output;
    }
    genCustomFunctions() {
        let output = "";
        output += this.buildExpandedSection("CUSTOM FUNCTIONS", this.hsm.inputHsm.cFunctions);
        output += this.hsm.inputHsm.cFunctionsNoExp + "\n";
        return output;
    }
    genParentMapping() {
        let output = "\n\n";
        output += this.createCommentHeader(`Parent mapping for all ${this.classFullName} state IDs`).trim() + "\n";
        output += `static const ${this.classFullName}_StateId parent_mapping[${this.classFullName}_StateCount] = {\n`;
        let states = this.hsm.getAllStates();
        let inner = "";
        for (let i = 0; i < states.length; i++) {
            let state = states[i];
            inner += `  [${this.genStateIdTypeName()}__${state.label}] = ${this.genStateIdTypeName()}__${(state.parent || state).label},\n`; //funkyness for root state that doesn't have a parent
        }
        inner = StringUtils_1.StringUtils.alignRegexInStringsSimple(/=/, inner);
        output += inner;
        output += `};\n`;
        return output;
    }
    genStateHandlerMapping() {
        let output = "\n\n";
        output += this.createCommentHeader(`Parent mapping for all ${this.classFullName} state IDs`).trim() + "\n";
        output += `static const ${this.classFullName}_EventHandler state_handlers[${this.classFullName}_StateCount] = {\n`;
        let states = this.hsm.getAllStates();
        let inner = "";
        for (let i = 0; i < states.length; i++) {
            let state = states[i];
            inner += `  [${this.genStateIdTypeName()}__${state.label}] = ${state.label}_event_handler,\n`;
        }
        inner = StringUtils_1.StringUtils.alignRegexInStringsSimple(/=/, inner);
        output += inner;
        output += `};\n`;
        return output;
    }
    genEnterStateDefinition() {
        let output = "\n\n";
        output += this.createCommentHeader(`TODO ${this.classFullName} `).trim() + "\n";
        output += `static void enter_state(${this.classFullName}* sm, ${this.classFullName}_StateId state_id)\n{\n`;
        output += `  switch(state_id)\n  {`;
        let states = this.hsm.getAllStates();
        let inner = "";
        for (let i = 0; i < states.length; i++) {
            let state = states[i];
            inner += `\n    case ${this.genStateIdTypeName()}__${state.label}:\n`;
            for (const eh of state.eventHandlers.filter(eh => eh.hasTrigger(Triggers.ENTER))) {
                inner += `${this.genEnterExitHandler(state, eh, true)}\n`;
            }
            inner += `      break;\n`;
        }
        output += inner;
        output += `  }\n`;
        output += `};\n`;
        return output;
    }
    genExitStateDefinition() {
        let output = "\n\n";
        output += this.createCommentHeader(`TODO ${this.classFullName} `).trim() + "\n";
        output += `static void exit_state(${this.classFullName}* sm, ${this.classFullName}_StateId state_id)\n{\n`;
        output += `  switch(state_id)\n  {`;
        let states = this.hsm.getAllStates();
        let inner = "";
        for (let i = 0; i < states.length; i++) {
            let state = states[i];
            inner += `\n    case ${this.genStateIdTypeName()}__${state.label}:\n`;
            for (const eh of state.eventHandlers.filter(eh => eh.hasTrigger(Triggers.EXIT))) {
                inner += `${this.genEnterExitHandler(state, eh, true)}\n`;
            }
            inner += `      break;\n`;
        }
        output += inner;
        output += `  }\n`;
        output += `};\n`;
        return output;
    }
    genFileHeaderInfo() {
        let output = "";
        let text = `
    @file

    @brief     State machine "${this.classFullName}"
               Auto generated from file: ${this.hsm.inputHsm.diagramSourceFilePath}

    @copyright Copyright (c)
    `;
        text = StringUtils_1.StringUtils.removeBlankLinesAtTop(text);
        text = StringUtils_1.StringUtils.deIndent(text);
        output = this.createCommentHeader(text).trim() + "\n";
        return output;
    }
    genSourceFile() {
        //TODO generate doxygen for file
        let fullOutput = this.genFileHeaderInfo() +
            `
#include "${this.getHeaderFilename()}"
#include <string.h>

#define COUNTOF(x) ((sizeof(x)/sizeof(0[x])) / ((size_t)(!(sizeof(x) % sizeof(0[x])))))

typedef void(*${this.classFullName}_EventHandler)(${this.classFullName}* sm, ${this.classFullName}_EventId event_id);

#define PARENT_HANDLER_BOOKMARK(parent_handler) //allows an IDE to jump to function. nothing else

static void exit_upto(${this.classFullName}* sm, ${this.classFullName}_StateId stop_before_exiting);
static void exit_state(${this.classFullName}* sm, ${this.classFullName}_StateId state_id);
static void enter_chain(${this.classFullName}* sm, ${this.classFullName}_StateId *state_ids, uint16_t chain_length);
static void enter_state(${this.classFullName}* sm, ${this.classFullName}_StateId state_id);
static ${this.classFullName}_StateId get_parent_id(${this.classFullName}_StateId state_id);
` +
            this.hsm.inputHsm.c_file_top.trim() + "\n" +
            this.genCustomPrototypes() +
            this.genCustomFunctions() +
            this.genEventHandlerPrototypes() +
            this.genParentMapping() +
            this.genStateHandlerMapping() +
            this.genEventHanlderDefinitions() +
            this.genConstructorDefinition() +
            this.genEnterStateDefinition() +
            this.genExitStateDefinition() +
            this.genEventIdToStringDefinition() +
            `


void ${this.classFullName}_dispatch_event(${this.classFullName}* sm, ${this.classFullName}_EventId event_id)
{
    ${this.classFullName}_StateId state_id_to_rx_event = sm->state_id;
    sm->event_handled = false;

    do
    {
        ${this.classFullName}_EventHandler event_handler = state_handlers[state_id_to_rx_event];
        event_handler(sm, event_id);
        state_id_to_rx_event = get_parent_id(state_id_to_rx_event);
    }
    while(!sm->event_handled);
}


static void enter_chain(${this.classFullName}* sm, ${this.classFullName}_StateId *state_ids, uint16_t chain_length)
{
    for(uint16_t i = 0; i < chain_length; i++)
    {
        ${this.classFullName}_StateId to_enter = state_ids[i];
        enter_state(sm, to_enter);
        sm->state_id = to_enter;
    }
}


static ${this.classFullName}_StateId get_parent_id(${this.classFullName}_StateId state_id)
{
    ${this.classFullName}_StateId parent = parent_mapping[state_id];
    return parent;
}


static void exit_upto(${this.classFullName}* sm, ${this.classFullName}_StateId stop_before_exiting)
{
    while(sm->state_id != ${this.classFullName}_StateId__ROOT && sm->state_id != stop_before_exiting)
    {
        exit_state(sm, sm->state_id);
        sm->state_id = get_parent_id(sm->state_id);
    }
}
`;
        fullOutput = this.postProcessCode(fullOutput);
        return fullOutput;
    }
} ////////////////////////////////////////////////
exports.FlatCompiler = FlatCompiler;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiRmxhdENvbXBpbGVyLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiRmxhdENvbXBpbGVyLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQSxtREFBbUQ7QUFDbkQ7Ozs7OztFQU1FOztBQUdGLDJDQUE4RjtBQUM5RixtQ0FBMkQ7QUFDM0QsbUNBQWdDO0FBQ2hDLHlEQUFzRDtBQUN0RCxpREFBOEM7QUFDOUMsdUNBQXNDO0FBRXRDLDJDQUF1RDtBQUV2RCxNQUFNLENBQUMsR0FBRyxJQUFJLGlCQUFPLEVBQUUsQ0FBQztBQUVYLFFBQUEsZ0JBQWdCLEdBQUcsTUFBTSxDQUFDO0FBRzFCLFFBQUEsYUFBYSxHQUFHLENBQUMsQ0FBQztBQUUvQjtJQUFBO1FBQ0UsUUFBRyxHQUFpQixJQUFJLHVCQUFXLEVBQUUsQ0FBQztRQUN0QyxhQUFRLEdBQW1CLElBQUkscUJBQWEsRUFBRSxDQUFDO1FBRS9DLHFCQUFnQixHQUFtQixFQUFFLENBQUMsQ0FBRSxrQkFBa0I7UUFDMUQsc0JBQWlCLEdBQW1CLEVBQUUsQ0FBQyxDQUFDLGtCQUFrQjtRQUMxRCxzQkFBaUIsR0FBbUIsRUFBRSxDQUFDLENBQUMsa0JBQWtCO1FBRTFELGtCQUFhLEdBQVksU0FBUyxDQUFDO1FBRW5DLDJCQUFzQixHQUFHLEVBQUUsQ0FBQztJQWkvQjlCLENBQUM7SUEvK0JTLHNDQUFzQyxDQUFDLElBQWE7UUFDMUQsSUFBSSxNQUFNLEdBQVksSUFBSSxDQUFDO1FBQzNCLElBQUksVUFBVSxHQUFHLEVBQUUsQ0FBQztRQUNwQixJQUFJLGVBQWUsR0FBRyxDQUFDLENBQUM7UUFDeEIsT0FBTSxNQUFNLElBQUksVUFBVSxFQUFFLENBQUM7WUFDM0IsVUFBVSxHQUFHLE1BQU0sQ0FBQztZQUNwQixNQUFNLEdBQUcsSUFBSSxDQUFDLDBCQUEwQixDQUFDLE1BQU0sQ0FBQyxDQUFDO1lBQ2pELE1BQU0sR0FBRyxJQUFJLENBQUMsUUFBUSxDQUFDLFVBQVUsQ0FBQyxNQUFNLENBQUMsQ0FB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