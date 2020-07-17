"use strict";
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
Object.defineProperty(exports, "__esModule", { value: true });
const Generator_1 = require("./Generator");
const input_1 = require("./input");
const StateGen_1 = require("./StateGen");
const State_1 = require("./State");
const StringUtils_1 = require("../ts-utils/StringUtils");
const MyRegex_1 = require("../ts-utils/MyRegex");
const Triggers = require("./Triggers");
const Misc_1 = require("../ts-utils/Misc");
const r = new MyRegex_1.MyRegex();
exports.ROOT_STATE_LABEL = "ROOT";
const EXEC_CONTEXT_TYPE = "Jxc";
const EXEC_CONTEXT_USED_NAME = "jxc";
const EXEC_CONTEXT_TYPE_PARAM = "Jxc* jxc";
const insertBeforeClassInit = `TRY_BUBBLE_EXCEPTION_RET_0(${EXEC_CONTEXT_USED_NAME});\n`;
exports.ROOT_STATE_ID = 0;
class Compiler {
    constructor() {
        this.hsm = new Generator_1.RenderedHsm();
        this.expander = new input_1.MacroExpander();
        this.classFullName = "statemachine_123";
        this.classPrefix = "sm123"; // "my_statemachine"
        this.sep = "_";
        this.enumElementsPrefix = "";
        this.stateGen = new StateGen_1.StateGen(this);
        /** used for resolving the integer size and signedness of typedefs */
        this.typedefPrintfMapping = {};
        this.inputEventEnumsPrefix = ``;
        this.inputEventEnumsPostfix = ""; //`${this.sep}ID`;
        this.inputEventEnumType = "";
        this.inputValueFields = []; //TODO move to hsm
        this.outputValueFields = []; //TODO move to hsm
        this.outputEventFields = []; //TODO move to hsm
        this.insertCodeAtStartOfEveryNonConstructorFunction = `vTRY_BUBBLE_EXCEPTION();`;
    }
    setupStrings() {
        this.enumElementsPrefix = `${this.classPrefix}${this.sep}`;
        this.stateGen.stateEnumsPostfix = ``;
        this.stateGen.stateEnumType = `${this.classPrefix}${this.sep}StateId`;
        this.inputEventEnumsPrefix = `InputEvent${this.sep}`;
        this.inputEventEnumsPostfix = ""; //`${this.sep}ID`;
        this.inputEventEnumType = `${this.classPrefix}${this.sep}InputEvent`;
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
        this.hsm.executeAfterCode = this.removeYedCommentsAndExpand(inputHsm.executeAfterCode);
        this.hsm.executeBeforeDispatchCode = this.removeYedCommentsAndExpand(inputHsm.executeBeforeCode);
        this.hsm.h_file_top = this.removeYedCommentsAndExpand(inputHsm.h_file_top);
        //TODO expand and replace generator directives execute_after
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
        this.hsm.initialProcessAndValidate();
        this.hsm.convertPseudoInitialStates();
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
    buildClassEnumName(namePart) {
        let output = `${this.enumElementsPrefix}${namePart}`;
        return output;
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
                output = this.buildClassEnumName(`${this.inputEventEnumsPrefix}${label.toUpperCase()}${this.inputEventEnumsPostfix}`);
                break;
        }
        return output;
    }
    getInputEventEnumTypeName() {
        return this.inputEventEnumType;
    }
    genInputEventEnums() {
        let output = "";
        output += this.createCommentHeader(`Enumeration for all ${this.classFullName} input event IDs`);
        output += `typedef enum _${this.getInputEventEnumTypeName()}\n{\n`;
        let inputEvents = this.hsm.getAllNonDirectiveInputEventNames();
        let customCount = 0;
        let firstId = 10;
        let inner = "";
        let firstCustomEvent;
        //add in standard events first
        inner += `  ${this.buildInputEventEnumName("EXIT")}             = HsmEventId__EXIT,\n`;
        inner += `  ${this.buildInputEventEnumName("ENTER")}            = HsmEventId__ENTER,\n`;
        inner += `  ${this.buildInputEventEnumName("LANDED_IN")}        = HsmEventId__LANDED_IN,\n`;
        inner += `  ${this.buildInputEventEnumName("DO")}               = HsmEventId__DO,\n`;
        inner += `  ${this.buildInputEventEnumName("TEST_TRANSITIONS")} = HsmEventId__TEST_TRANSITIONS,\n`;
        for (let i = 0; i < inputEvents.length; i++) {
            let eventName = inputEvents[i];
            if (Triggers.isHsmEvent(eventName) || Triggers.isPseudoEventName(eventName)) {
            }
            else {
                if (customCount == 0) {
                    firstCustomEvent = eventName;
                    inner += `  ${this.buildInputEventEnumName(eventName)} = HsmEventId_CUSTOM_STARTS_AT,\n`;
                }
                else {
                    inner += `  ${this.buildInputEventEnumName(eventName)},\n`;
                }
                customCount++;
            }
        }
        inner = StringUtils_1.StringUtils.alignRegexInStringsSimple(/=/, inner);
        output += inner;
        output += `} ${this.getInputEventEnumTypeName()};\n`;
        output += `#define ${this.buildClassEnumName("INPUT_EVENT_COUNT")} ${customCount}\n`;
        //TODO do below check for output events.
        output += `static_assert( sizeof(${this.getInputEventEnumTypeName()}) == sizeof(HsmEventId), "HSM event ID type size must be increased to support more events. ");\n`;
        if (firstCustomEvent != null) {
            //output += `static_assert(${this.buildInputEventEnumName(firstCustomEvent)} == HSM_CUSTOM_EVENT_START_AT, "'${this.buildInputEventEnumName(firstCustomEvent)}' must equal HSM_CUSTOM_EVENT_START_AT");`
        }
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
    genStepFunctionName() {
        return `${this.classPrefix}${this.sep}step`;
    }
    genStepPrototype() {
        let output = "";
        //hsm_t* hsm, uint32_t current_time
        output = `void ${this.genStepFunctionName()}(${EXEC_CONTEXT_TYPE_PARAM}, ${this.genStatemachineStructName()}* sm)`;
        return output;
    }
    genStepDefinition() {
        let output;
        output = this.createCommentHeader(`Public step function for ${this.classFullName} state machine`);
        //hsm_t* hsm, uint32_t current_time
        output += `${this.genStepPrototype()}\n{\n`;
        output += `  ${this.insertCodeAtStartOfEveryNonConstructorFunction}\n`;
        output += `  ${this.genDispatchEventFunctionName()}(jxc, sm, HsmEventId__DO);\n`;
        output += `}\n`;
        return output;
    }
    genTestTransitionsFunctionName() {
        return `${this.classPrefix}${this.sep}test_transitions`;
    }
    genTestTransitionsPrototype() {
        let output = "";
        //hsm_t* hsm, uint32_t current_time
        output = `void ${this.genTestTransitionsFunctionName()}(${EXEC_CONTEXT_TYPE_PARAM}, ${this.genStatemachineStructName()}* sm, uint8_t dispatch_count)`;
        return output;
    }
    genTestTransitionsDefinition() {
        let output;
        output = this.createCommentHeader(`Public test transitions function for ${this.classFullName} state machine`);
        //hsm_t* hsm, uint32_t current_time
        output += `${this.genTestTransitionsPrototype()}\n{\n`;
        output += `  ${this.insertCodeAtStartOfEveryNonConstructorFunction}\n`;
        output += `  for (uint8_t i = 0; i < dispatch_count; i++){\n`;
        output += `    ${this.genDispatchEventFunctionName()}(jxc, sm, HsmEventId__TEST_TRANSITIONS);\n`;
        output += `  }\n`;
        output += `}\n`;
        return output;
    }
    genDispatchIfFunctionName() {
        return `${this.classPrefix}${this.sep}dispatch_if`;
    }
    genDispatchIfPrototype() {
        let output = "";
        //hsm_t* hsm, uint32_t current_time
        output = `void ${this.genDispatchIfFunctionName()}(${EXEC_CONTEXT_TYPE_PARAM}, ${this.genStatemachineStructName()}* sm, bool condition, HsmEventId event_id)`;
        return output;
    }
    genDispatchIfDefinition() {
        let output;
        output = this.createCommentHeader(`Public function to dispatch event upon a condition`);
        output += StringUtils_1.StringUtils.properIndent(`
      ${this.genDispatchIfPrototype()}
      {
        ${this.insertCodeAtStartOfEveryNonConstructorFunction}
        if(condition)
        {
          ${this.genDispatchEventFunctionName()}(jxc, sm, event_id);\n
        }
      }
      `, "");
        return output;
    }
    genStaticDispatchEventFunctionName() {
        return `dispatch_event`;
    }
    genStaticDispatchEventPrototype() {
        let output = "";
        //hsm_t* hsm, uint32_t current_time, const HsmEvent* event
        output = `static void ${this.genStaticDispatchEventFunctionName()}(${EXEC_CONTEXT_TYPE_PARAM}, ${this.genStatemachineStructName()}* sm, const HsmEvent* event)`;
        return output;
    }
    genStaticDispatchEventDefinition() {
        let output;
        output = this.createCommentHeader(`Private dispatch event function for ${this.classFullName} state machine`);
        let executeBeforeCode = "";
        if (this.hsm.executeBeforeDispatchCode) {
            executeBeforeCode = `${this.genExecuteBeforeFunctionName()}(jxc, sm, event);`;
        }
        else {
            executeBeforeCode = `//Note: no code to run before state machine event dispatch`;
        }
        let executeAfterCode = "";
        if (this.hsm.executeAfterCode) {
            executeAfterCode = `${this.genExecuteAfterFunctionName()}(jxc, sm, event);`;
        }
        else {
            executeAfterCode = `//Note: no code to run after state machine event dispatch`;
        }
        //hsm_t* hsm, uint32_t current_time
        output += StringUtils_1.StringUtils.properIndent(`
      ${this.genStaticDispatchEventPrototype()}
      {
        ${this.insertCodeAtStartOfEveryNonConstructorFunction}
        ${executeBeforeCode}
        Hsm_dispatch_event(jxc, &sm->hsm, event);
        ${executeAfterCode}
      }\n
    `, "");
        return output;
    }
    genDispatchEventFunctionName() {
        return `${this.classPrefix}${this.sep}dispatch_event`;
    }
    genDispatchEventPrototype() {
        let output = "";
        //hsm_t* hsm, uint32_t current_time
        output = `void ${this.genDispatchEventFunctionName()}(${EXEC_CONTEXT_TYPE_PARAM}, ${this.genStatemachineStructName()}* sm, ${this.getInputEventEnumTypeName()} event_id)`;
        return output;
    }
    genDispatchEventDefinition() {
        let output;
        output = this.createCommentHeader(`Public dispatch event function for ${this.classFullName} state machine`);
        //hsm_t* hsm, uint32_t current_time
        output += StringUtils_1.StringUtils.properIndent(`
      ${this.genDispatchEventPrototype()}
      {
        ${this.insertCodeAtStartOfEveryNonConstructorFunction}
        HsmEvent event = { .event_id = event_id };
        dispatch_event(jxc, sm, &event);
      }\n
    `, "");
        return output;
    }
    //not a public function
    //TODOLOW generate prototype for c file. For now just put above uses.
    genExecuteAfterFunctionName() {
        return `execute_after_dispatch`;
    }
    genExecuteAfterPrototype() {
        let output = this.genExecuteBeforeAfterPrototype(this.genExecuteAfterFunctionName());
        return output;
    }
    genExecuteAfterDefinition() {
        let output = this.genExecuteBeforeAfterDefinition(this.hsm.executeAfterCode, this.genExecuteAfterPrototype(), `Function called after state machine event dispatch`);
        return output;
    }
    //not a public function
    //TODOLOW generate prototype for c file. For now just put above uses.
    genExecuteBeforeFunctionName() {
        return `execute_before_dispatch`;
    }
    genExecuteBeforePrototype() {
        let output = this.genExecuteBeforeAfterPrototype(this.genExecuteBeforeFunctionName());
        return output;
    }
    genExecuteBeforeDefinition() {
        let output = this.genExecuteBeforeAfterDefinition(this.hsm.executeBeforeDispatchCode, this.genExecuteBeforePrototype(), `Function called before event dispatched to SM `);
        return output;
    }
    genExecuteBeforeAfterPrototype(functionName) {
        let output = "";
        //hsm_t* hsm, uint32_t current_time
        output = `static void ${functionName}(${EXEC_CONTEXT_TYPE_PARAM}, ${this.genStatemachineStructName()}* sm, const HsmEvent* event)`;
        return output;
    }
    genExecuteBeforeAfterDefinition(dispatchCode, cPrototype, comment) {
        let output;
        if (dispatchCode.trim().length == 0) {
            return "";
        }
        output = this.createCommentHeader(comment);
        let indent = "        ";
        dispatchCode = StringUtils_1.StringUtils.indent(dispatchCode, indent);
        dispatchCode = StringUtils_1.StringUtils.rTrim(dispatchCode);
        indent = indent.replace(new RegExp("^" + indent), "  ");
        //hsm_t* hsm, uint32_t current_time
        output += StringUtils_1.StringUtils.properIndent(`
        ${cPrototype}
        {
          ${this.insertCodeAtStartOfEveryNonConstructorFunction}
          Hsm2* hsm = &sm->hsm;
          (void)hsm; (void)${EXEC_CONTEXT_USED_NAME}; (void)event;
          ${dispatchCode.trim()}
        }

      `, "");
        return output;
    }
    genEventIdToStringFunctionName() {
        return `${this.classPrefix}${this.sep}InputEvent_to_string`;
    }
    genEventIdToStringPrototype() {
        let output = "";
        //hsm_t* hsm, uint32_t current_time
        output = `const char* ${this.genEventIdToStringFunctionName()}(HsmEventId event_id)`; //leave as HsmEventId to avoid warnings
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
        //hsm_t* hsm, uint32_t current_time
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
    /*
    bool BBH__print_input_events(button_basic_hsm_t* sm)
    {
      sm->hsm.debug(0, "inputs: button_input=%u", sm->input_values.button_input);
    }*/
    genPrintGenericValuesDefinition(valuesName, valueFields, prototype) {
        if (valueFields.length == 0) {
            return "";
        }
        let output;
        output = this.createCommentHeader(`Function that prints all ${valuesName}`);
        let formatString = "";
        let values = "";
        let sep = "";
        for (let field of valueFields) {
            formatString += `${sep}${field.name} = %${this.getPrintfFormatString(field.type)}`;
            values += `${sep}sm->${valuesName}.${field.name}`;
            sep = ", ";
        }
        output += StringUtils_1.StringUtils.properIndent(`
        ${prototype}
        {
          //TODOLOW update generator to implement this feature
          (void)sm;
          //hsm_print_debug_msg(&sm->hsm, 0, "${valuesName}: ${formatString}", ${values});
        }
        `, "");
        return output;
    }
    getPrintfFormatString(type) {
        let signed;
        let size;
        let formatterString;
        let match;
        type = this.typedefPrintfMapping[type] || type;
        //make booleans look like uint8_t
        if (match = type.match(/^bool$|^boolean$/i)) {
            type = "uint8_t";
        }
        if (match = type.match(/^float$|^double$/)) {
            formatterString = "f";
        }
        else if (match = type.match(/^(u)?int(\d+)_t$/)) {
            //if type is un/signed integer, use PRIsx `Format macro constants`. See http://en.cppreference.com/w/c/types/integer 
            let signed = match[1] == null;
            let size = match[2];
            let priName = "PRI";
            priName += signed ? "i" : "u";
            priName += size;
            formatterString = `"${priName}"`; //need to surround in quotes to break out of existing string, concatenate PRI macro, then concatenate rest of string
        }
        else {
            throw `Don't know printf symbol for ${type}`;
        }
        return formatterString;
    }
    genPrintInputValuesFunctionName() {
        return `${this.classPrefix}${this.sep}print_input_values`;
    }
    genPrintInputValuesPrototype() {
        let output = `void ${this.genPrintInputValuesFunctionName()}(${this.genStatemachineStructName()}* sm)`;
        return output;
    }
    genPrintInputValuesDefinition() {
        let output = this.genPrintGenericValuesDefinition("input_values", this.inputValueFields, this.genPrintInputValuesPrototype());
        return output;
    }
    genPrintOutputValuesFunctionName() {
        return `${this.classPrefix}${this.sep}print_output_values`;
    }
    genPrintOutputValuesPrototype() {
        let output = `void ${this.genPrintOutputValuesFunctionName()}(${this.genStatemachineStructName()}* sm)`;
        return output;
    }
    genPrintOutputValuesDefinition() {
        let output = this.genPrintGenericValuesDefinition("output_values", this.outputValueFields, this.genPrintOutputValuesPrototype());
        return output;
    }
    genPrintOutputEventsFunctionName() {
        return `${this.classPrefix}${this.sep}print_output_events`;
    }
    genPrintOutputEventsPrototype() {
        let output = `void ${this.genPrintOutputEventsFunctionName()}(${this.genStatemachineStructName()}* sm)`;
        return output;
    }
    genPrintOutputEventsDefinition() {
        let output = this.genPrintGenericValuesDefinition("output_events", this.outputEventFields, this.genPrintOutputEventsPrototype());
        return output;
    }
    genClearOutputEventsFunctionName() {
        return `${this.classPrefix}${this.sep}clear_output_events`;
    }
    genClearOutputEventsPrototype() {
        let output = `void ${this.genClearOutputEventsFunctionName()}(Jxc* jxc, ${this.genStatemachineStructName()}* sm)`;
        return output;
    }
    genClearOutputEventsDefinition() {
        let output = "\n";
        output += this.createCommentHeader(`Function that clears all output_events`);
        let innards = "";
        for (let field of this.outputEventFields) {
            innards += `sm->output_events.${field.name} = 0;\n`;
        }
        output += StringUtils_1.StringUtils.properIndent(`
        ${this.genClearOutputEventsPrototype()}
        {
          (void)jxc; (void)sm;
          ${this.insertCodeAtStartOfEveryNonConstructorFunction}
          <innards_insert_point>
        }
        `, "");
        output = output.replace(/^([ \t]*)<innards_insert_point>[ \t]*/gm, function (match, indent) {
            return StringUtils_1.StringUtils.properIndent(innards, indent);
        });
        return output;
    }
    genClassInitFunctionName() {
        //TODO have it set a flag when class initialized? Then other calls can return error if flag not set.
        //can't safely do singleton in constructor without blocking and semaphores...
        return `${this.classPrefix}${this.sep}class_init`;
    }
    genClassInitFunctionPrototype() {
        let output = "";
        output = `bool ${this.genClassInitFunctionName()}(${EXEC_CONTEXT_TYPE_PARAM})`;
        return output;
    }
    genClassInitDefinition() {
        let output;
        output = this.createCommentHeader(`Public class initialization function for ${this.classFullName} state machine.`);
        // output += `${this.genClassInitFunctionPrototype()}\n{\n`;
        // output += StringUtils.properIndent(`
        //   ${insertBeforeClassInit}
        //   bool result = hsm_init_tree(&${this.getStatemachineTreeArrayName()}[0], ${this.stateGen.getStateIdCountEnumName()});
        //   return result;
        // }
        // `, "");
        output += `\n`;
        return output;
    }
    genConstructorFunctionName() {
        return `${this.classPrefix}${this.sep}instance_init`;
    }
    genConstructorPrototype() {
        let output = "";
        output = `void ${this.genConstructorFunctionName()}(${EXEC_CONTEXT_TYPE_PARAM}, ${this.genStatemachineStructName()}* sm, const char * instance_name)`;
        return output;
    }
    genConstructorDefinition() {
        let output;
        output = this.createCommentHeader(`Public constructor function for ${this.classFullName} state machine`);
        output += `${this.genConstructorPrototype()}\n{\n`;
        output += StringUtils_1.StringUtils.properIndent(`
      ${this.insertCodeAtStartOfEveryNonConstructorFunction}
      sm->hsm.instance_name = instance_name;
      sm->hsm.tree = &${this.genHsmTreeName()};
      sm->hsm.get_state_vars = get_state_vars;
      //sm->hsm.listener = &hsmListener;
      Hsm_construct(jxc, &sm->hsm);      
    }
    `, "");
        output += `\n`;
        return output;
    }
    // private genPrepareToRunFunctionName() : string {
    //   return `${this.classPrefix}${this.sep}prepare_to_run`;
    // }
    // private genPrepareToRunPrototype() : string{
    //   let output = "";
    //   output = `void ${this.genPrepareToRunFunctionName()}(${this.genStatemachineStructName()}* sm, uint32_t current_time)`;
    //   return output;
    // }
    // private genPrepareToRunDefinition() : string {
    //   let output;
    //   output = this.createCommentHeader(`Public function to prepare running '${this.classFullName}' state machine`)
    //   let execAfter;
    //   if(this.hsm.executeAfterCode){
    //     execAfter = `${this.genExecuteAfterFunctionName()}(sm);`
    //   } else {
    //     execAfter = `//Note: no code to run after state machine iteration`
    //   }
    //   output += `${this.genPrepareToRunPrototype()}\n{\n`;
    //   output += StringUtils.properIndent(`
    //     ${this.insertCodeAtStartOfEveryNonConstructorFunction}
    //     hsm_prepare_to_run(&sm->hsm, current_time);
    //     ${execAfter}
    //   }
    //   `, "");
    //   output += `\n`;
    //   return output;
    // }
    genStatemachineVarStructName() {
        let output = `${this.classPrefix}_Vars`;
        return output;
    }
    genStatemachineVarsStruct() {
        let output = "\n";
        output += this.createCommentHeader(`STRUCT for ${this.classFullName} variables `);
        output += `typedef struct _${this.genStatemachineVarStructName()}\n{\n`;
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
        let output = `${this.classPrefix}_InputValues`;
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
        let output = `${this.classPrefix}_OutputValues`;
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
        let output = `${this.classPrefix}_OutputEvents`;
        return output;
    }
    genStatemachineOutputEventsStruct() {
        if (this.outputEventFields.length == 0) {
            return "";
        }
        let output = this.genStatemachineStructFieldsDefintion(`output_events`, this.genStatemachineOutputEventsStructName(), this.outputEventFields);
        return output;
    }
    genStateContextArrayName() {
        let output = `state_contexts`;
        return output;
    }
    genStateContextPointersArrayName() {
        let output = `state_contexts_p`;
        return output;
    }
    genHsmTreeName() {
        let output = this.classFullName + "Tree";
        return output;
    }
    genHsmTree() {
        let output = "";
        output = `
    <s>const HsmTree ${this.genHsmTreeName()} = {
    <s>  .htree = {
    <s>    .nodes = (HTreeNode*)&states[0],
    <s>    .node_sizeof = sizeof(states[0]),
    <s>  },
    <s>  .name = "${this.classFullName}",
    <s>};    
    `;
        output = StringUtils_1.StringUtils.processMarkers(output);
        output = this.createCommentHeader(`STRUCT for ${this.classFullName} `) + output + "\n\n\n";
        return output;
    }
    genGetStateTempVarsFunction() {
        let inner = "";
        for (let state of this.hsm.getAllStates()) {
            inner += `
      <s>    case ${this.stateGen.genStateEnumName(state)}:
      <s>      ptr = &sm->${this.stateGen.genStateTempVarPathFromRoot(state)}.base_vars;
      <s>      break;
      `;
        }
        let output = this.createCommentHeader("Function to get state's temporary variables by ID") +
            `
      
    <s>static HsmStateBaseVars* get_state_vars(Jxc* jxc, Hsm2* hsm, int id)
    <s>{
    <s>  (void)jxc; //remove compiler warning
    <s>  void* ptr = NULL;
    <s>  ${this.genStatemachineStructName()}* sm = (${this.genStatemachineStructName()}*)hsm;
    <s> 
    <s>  switch(id){ ${inner}
    <s>  }
    <s>
    <s>  return ptr;
    <s>}

    `.trim();
        output = StringUtils_1.StringUtils.processMarkers(output);
        return output;
    }
    genStatemachineStructName() {
        return `${this.classPrefix}`;
    }
    genStructRootStateVarsInstanceName() {
        return this.stateGen.genStateVarInstanceName(this.hsm.rootState);
    }
    genStatemachineStruct() {
        let output = this.createCommentHeader(`STRUCT for ${this.classFullName} `);
        let inputValues = this.inputValueFields.length == 0 ? "" : `${this.genStatemachineInputValuesStructName()} input_values;\n\n`;
        let outputValues = this.outputValueFields.length == 0 ? "" : `${this.genStatemachineOutputValuesStructName()} output_values;\n\n`;
        let outputEvents = this.outputEventFields.length == 0 ? "" : `${this.genStatemachineOutputEventsStructName()} output_events;\n\n`;
        output += StringUtils_1.StringUtils.properIndent(`
      typedef struct _${this.genStatemachineStructName()}
      {
        Hsm2 hsm; //MUST be first element for polymorphism

        ${inputValues}

        ${outputValues}

        ${outputEvents}

        ${this.genStatemachineVarStructName()} vars;

        ${this.stateGen.genStateVarsTypedefName(this.hsm.rootState)} ${this.genStructRootStateVarsInstanceName()};
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
        let printInputValuesPrototype = this.inputValueFields.length > 0 ? this.genPrintInputValuesPrototype() + ";\n" : "";
        let printOutputValuesPrototype = this.outputValueFields.length > 0 ? this.genPrintOutputValuesPrototype() + ";\n" : "";
        let printOutputEventsPrototype = this.outputEventFields.length > 0 ? this.genPrintOutputEventsPrototype() + ";\n" : "";
        let fullOutput = this.genFileHeaderInfo() +
            `
#pragma once
${this.hsm.h_file_top.trim()}
#include <stdint.h>
#include <stdbool.h>
#include "hsm2.h"

//******************************************************************************
// COMPILER SPECIFIC SECTION
//******************************************************************************

//disable specific Visual Studio warnings for this file
#ifdef _MSC_VER
#  pragma warning( push )
#  pragma warning(disable: 4214)  //allow bools to be used in bit fields: Warning	C4214	nonstandard extension used : bit field types other than int
#  pragma warning(disable: 4221)  //Warning for old code. Warning	C4221	nonstandard extension used: 'p': cannot be initialized using address of automatic variable 'buffer'
#  pragma warning(disable: 4204)  //Warning for old code. Warning	C4204	nonstandard extension used: non-constant aggregate initialize
#  pragma warning(disable: 4201)  //Warning	C4201	nonstandard extension used: nameless struct/union	
#endif


//#define _SHOW_PADDING_WARNINGS  //MSVC with all warnings enabled will alert to padding of structures. Note: it uses 4 bytes for enums as default whereas IAR is smarter and will use 1 byte if able.
#ifndef _SHOW_PADDING_WARNINGS
#  ifdef _MSC_VER
#    pragma warning(disable: 4820)
#  endif
#endif

\n
` +
            this.stateGen.genStateEnums(this.hsm.getAllStates()) +
            this.genInputEventEnums() +
            this.stateGen.genStateExternStateInstances(this.hsm.getAllStates()) +
            this.stateGen.genStateVarsPrototypes() +
            this.stateGen.genStateVarsStructs() +
            this.genStatemachineInputValuesStruct() +
            this.genStatemachineOutputValuesStruct() +
            this.genStatemachineOutputEventsStruct() +
            this.genStatemachineVarsStruct() +
            this.genStatemachineStruct() +
            this.createCommentHeader("public functions") +
            this.genClassInitFunctionPrototype() + ";\n" +
            this.genConstructorPrototype() + ";\n" +
            // this.genPrepareToRunPrototype() + ";\n" +
            this.genStepPrototype() + ";\n" +
            this.genTestTransitionsPrototype() + ";\n" +
            this.genDispatchEventPrototype() + ";\n" +
            this.genDispatchIfPrototype() + ";\n" +
            this.genEventIdToStringPrototype() + ";\n" +
            this.genClearOutputEventsPrototype() + ";\n" +
            printInputValuesPrototype +
            printOutputValuesPrototype +
            printOutputEventsPrototype +
            `\n\n
//******************************************************************************
// COMPILER SPECIFIC SECTION 2
//******************************************************************************
#ifdef _MSC_VER
#  pragma warning( pop )  //re-enable warnings disabled for this file
#endif
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
    genCustomFunctions() {
        let output = "";
        output += this.buildExpandedSection("CUSTOM FUNCTIONS", this.hsm.inputHsm.cFunctions);
        output += this.hsm.inputHsm.cFunctionsNoExp + "\n";
        output += StringUtils_1.StringUtils.processMarkers(`
    <s>static void event_handler_breakpoint(Jxc* jxc, Hsm2* hsm, HsmContext* context, const HsmEvent* event){
    <s>  (void)jxc; (void)hsm; (void)context; (void)event;
    <s>  int x = 5;  //set breakpoint here
    <s>  (void)x; //avoid compiler warning
    <s>}
    `);
        return output;
    }
    genFileHeaderInfo() {
        let output = "";
        let text = `
    @file

    @brief     State machine "${this.classFullName}"
               Auto generated from file: ${this.hsm.inputHsm.diagramSourceFilePath}

    @copyright Copyright (c) 2017 JCA Electronics, Winnipeg, MB.
               All rights reserved.
    `;
        text = StringUtils_1.StringUtils.removeBlankLinesAtTop(text);
        text = StringUtils_1.StringUtils.deIndent(text);
        output = this.createCommentHeader(text).trim() + "\n";
        return output;
    }
    /*
    FULL.NAME{op}.STATE:
      parent:
      is_ortho_parent:
      is_ortho_kid:
      immediate kid names (sorted alphabetically):
      event handlers
    */
    genStatesSummary(state) {
    }
    genSourceFile() {
        //TODO generate doxygen for file
        let fullOutput = this.genFileHeaderInfo() +
            `
#include "${this.getHeaderFilename()}"
#include "hsm2.h"
#include <inttypes.h> //for printf format macro constants like 'PRIu32'

//******************************************************************************
// COMPILER SPECIFIC SECTION
//******************************************************************************
//disable specific Visual Studio warnings for this file
#ifdef _MSC_VER
#  pragma warning(disable: 4214)  //allow bools to be used in bit fields: Warning	C4214	nonstandard extension used : bit field types other than int
#  pragma warning(disable: 4221)  //Warning for old code. Warning	C4221	nonstandard extension used: 'p': cannot be initialized using address of automatic variable 'buffer'
#  pragma warning(disable: 4204)  //Warning for old code. Warning	C4204	nonstandard extension used: non-constant aggregate initialize
#endif
` +
            this.hsm.inputHsm.c_file_top.trim() + "\n" +
            this.genCustomPrototypes() +
            this.genCustomFunctions() +
            this.stateGen.genEventHandlerPrototypes() +
            this.stateGen.genStateDefinitions(this.hsm.getAllStates()) +
            this.genHsmTree() +
            this.genGetStateTempVarsFunction() +
            this.stateGen.genEventHanlderDefinitions() +
            this.genClassInitDefinition() +
            this.genConstructorDefinition() +
            this.genExecuteBeforeDefinition() + //no prototype generated for this so it must be above
            this.genExecuteAfterDefinition() + //no prototype generated for this so it must be above
            this.genStaticDispatchEventDefinition() +
            // this.genPrepareToRunDefinition() +
            this.genStepDefinition() +
            this.genTestTransitionsDefinition() +
            this.genDispatchEventDefinition() +
            this.genDispatchIfDefinition() +
            this.genEventIdToStringDefinition() +
            this.genPrintInputValuesDefinition() + ";\n" +
            this.genPrintOutputValuesDefinition() + ";\n" +
            this.genPrintOutputEventsDefinition() + ";\n" +
            this.genClearOutputEventsDefinition() + ";\n" +
            "";
        fullOutput = this.postProcessCode(fullOutput);
        return fullOutput;
    }
} ////////////////////////////////////////////////
exports.Compiler = Compiler;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiQ29tcGlsZXIuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJDb21waWxlci50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUEsbURBQW1EOztBQUduRCwyQ0FBdUc7QUFDdkcsbUNBQTJEO0FBQzNELHlDQUFzQztBQUN0QyxtQ0FBZ0M7QUFDaEMseURBQXNEO0FBQ3RELGlEQUE4QztBQUM5Qyx1Q0FBc0M7QUFFdEMsMkNBQXVEO0FBRXZELE1BQU0sQ0FBQyxHQUFHLElBQUksaUJBQU8sRUFBRSxDQUFDO0FBRVgsUUFBQSxnQkFBZ0IsR0FBRyxNQUFNLENBQUM7QUFFdkMsTUFBTSxpQkFBaUIsR0FBRyxLQUFLLENBQUM7QUFDaEMsTUFBTSxzQkFBc0IsR0FBRyxLQUFLLENBQUM7QUFDckMsTUFBTSx1QkFBdUIsR0FBRyxVQUFVLENBQUM7QUFFM0MsTUFBTSxxQkFBcUIsR0FBRyw4QkFBOEIsc0JBQXNCLE1BQU0sQ0FBQztBQUU1RSxRQUFBLGFBQWEsR0FBRyxDQUFDLENBQUM7QUFFL0I7SUFBQTtRQUNFLFFBQUcsR0FBaUIsSUFBSSx1QkFBVyxFQUFFLENBQUM7UUFDdEMsYUFBUSxHQUFtQixJQUFJLHFCQUFhLEVBQUUsQ0FBQztRQUMvQyxrQkFBYSxHQUFZLGtCQUFrQixDQUFDO1FBQzVDLGdCQUFXLEdBQWEsT0FBTyxDQUFDLENBQUEsb0JBQW9CO1FBQzNDLFFBQUcsR0FBRyxHQUFHLENBQUM7UUFDbkIsdUJBQWtCLEdBQVksRUFBRSxDQUFDO1FBRWpDLGFBQVEsR0FBYyxJQUFJLG1CQUFRLENBQUMsSUFBSSxDQUFDLENBQUM7UUFFekMscUVBQXFFO1FBQ3JFLHlCQUFvQixHQUF5QixFQUFFLENBQUM7UUFFaEQsMEJBQXFCLEdBQVksRUFBRSxDQUFDO1FBQ3BDLDJCQUFzQixHQUFZLEVBQUUsQ0FBQyxDQUFBLGtCQUFrQjtRQUN2RCx1QkFBa0IsR0FBWSxFQUFFLENBQUM7UUFFakMscUJBQWdCLEdBQW1CLEVBQUUsQ0FBQyxDQUFFLGtCQUFr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