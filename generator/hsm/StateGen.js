"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
const Compiler_1 = require("./Compiler");
const StringUtils_1 = require("../ts-utils/StringUtils");
const Generator_1 = require("./Generator");
const Triggers = require("./Triggers");
const nunjucks = require("nunjucks");
class StateGen {
    constructor(compiler) {
        this.stateEnumsPrefix = "StateId__";
        this.stateEnumsPostfix = "";
        this.stateEnumType = "";
        this.compiler = compiler;
    }
    genStateUniqueName(state) {
        let output = this.getFullPathNameToState(state);
        if (state == this.compiler.hsm.rootState) {
            output = "Root";
        }
        return output;
    }
    genStateEventHandlerName(state) {
        return this.genStateUniqueName(state) + "_handler";
    }
    genStateVarsTypedefName(state) {
        return this.compiler.classPrefix + "_" + this.genStateUniqueName(state) + "_Vars";
    }
    getFullPathNameToState(state, seperator = "__") {
        let output = "";
        let cur = state;
        let sep = "";
        while (cur != null) {
            let prepend = "";
            prepend = cur.label.toUpperCase();
            if (cur.is_ortho_parent && cur != state) {
                prepend += "__op__"; //meant to signify an ortho parent
            }
            else {
                prepend += sep;
            }
            output = prepend + output;
            sep = seperator;
            cur = cur.parent;
        }
        return output;
    }
    genStateEnumName(state) {
        let path = this.getFullPathNameToState(state);
        let output = "";
        output = this.compiler.buildClassEnumName(`${this.stateEnumsPrefix}${path}${this.stateEnumsPostfix}`);
        //let output = this.getStateEnumNameFromString(state.label) + "_S"+state.outputId;
        return output;
    }
    getStateEnumNameFromString(stateName) {
        // let output = "";
        // switch(stateName){
        //   // case ROOT_STATE_LABEL:
        //   //   output = "HSM_ROOT_ID";
        //   // break;
        //   default:
        //   output = this.compiler.buildClassEnumName(`${this.stateEnumsPrefix}${stateName.toUpperCase()}${this.stateEnumsPostfix}`);
        //   break;
        // }
        // return output;
        throw "not supported.";
    }
    allStates() {
        return this.compiler.hsm.getAllStates();
    }
    genStateVarsPrototypes() {
        // let output = "";
        // let states = this.allStates();
        // for(let state of states) {
        //   output += `typedef struct _${this.genStateVarsTypedefName(state)} ${this.genStateVarsTypedefName(state)};\n`;
        // }
        // output = StringUtils.alignStringMarkersSimple([" "," "," "], output);
        // output = "\n\n" + this.compiler.createCommentHeader(`State variable typedef prototypes for ${this.compiler.classFullName}`).trim() + "\n" + output;
        // return output;
        return "";
    }
    genStateVarInstanceName(state) {
        return this.genStateUniqueName(state).toLowerCase() + "_vars";
    }
    genStateVarStruct(state) {
        let output = "";
        let inner = "";
        for (let kid of state.kids) {
            inner += `\n<s>  ${this.genStateVarsTypedefName(kid)} ${this.genStateVarInstanceName(kid)};`;
        }
        if (state.kids.length > 0) {
            inner = inner.replace(/<s>/g, "<s>  "); //increase indent
            inner = StringUtils_1.StringUtils.alignRegexesInStringsSimple([/\b +\b/], inner); //align struct/union field names (which comes after field type).
            if (state.is_ortho_parent) {
                inner = `
        <s>  HsmContext orthoKidContexts[${state.kids.length}];
        <s>  struct 
        <s>  { //context variables for kids  ${inner}
        <s>  };
        `;
            }
            else {
                inner = `
        <s>  union 
        <s>  { //context variables for kids  ${inner}
        <s>  }; `;
            }
        }
        output += `
    <s>typedef struct _${this.genStateVarsTypedefName(state)}
    <s>{
    <s>  HsmStateBaseVars base_vars; ${inner}
    <s>} ${this.genStateVarsTypedefName(state)};
      `;
        output = StringUtils_1.StringUtils.processMarkers(output) + "\n\n\n";
        return output;
    }
    genStateVarsStructs() {
        let output = "";
        let states = this.allStates();
        let depth = 0;
        for (let state of states) {
            depth = Math.max(depth, state.depth);
        }
        for (; depth >= 0; depth--) {
            let fstates = states.filter(function (state) {
                return state.depth == depth;
            });
            for (let state of fstates) {
                output += this.genStateVarStruct(state);
            }
        }
        output = "\n\n" + this.compiler.createCommentHeader(`State variable structs for ${this.compiler.classFullName}`).trim() + "\n" + output;
        return output;
    }
    genStateDefinition(state) {
        let parent = state.parent;
        if (state.outputId == Compiler_1.ROOT_STATE_ID) {
            parent = state;
        }
        var output = `
    <s>  [${this.genStateEnumName(state)}] = {
    <s>    .name = "${state.label}", 
    <s>    .node = {
    <s>      .id = ${this.genStateEnumName(state)},
    <s>      .max_descendant_id = ${this.genStateEnumName(this.compiler.hsm.getStateFromOutputId(state.max_descendant_id))},
    <s>      .parent_id = ${this.genStateEnumName(parent)},
    <s>    },
    <s>    .event_handler = ${this.genStateEventHandlerName(state)},
    <s>    .vars_sizeof = sizeof(${this.genStateVarsTypedefName(state)}),
    <s>  },
     `;
        output = StringUtils_1.StringUtils.removeBlankLinesAtBottom(output);
        return output;
    }
    genEventHandlerPrototype(state) {
        return `static void ${this.genStateEventHandlerName(state)}(Jxc* jxc, Hsm2* hsm, HsmContext* context, const HsmEvent* event)`;
    }
    genEventHandlerPrototypes() {
        let output = "\n\n";
        let states = this.allStates();
        output += this.compiler.createCommentHeader(`Handler Prototypes for ${this.compiler.classFullName}`).trim() + "\n";
        for (let state of states) {
            output += `${this.genEventHandlerPrototype(state)};\n`;
        }
        output = StringUtils_1.StringUtils.alignStringMarkersSimple(["("], output);
        return output;
    }
    genEventHanlderDefinitions() {
        let output = "";
        for (let state of this.allStates()) {
            output += "\n\n" + this.genEventHandlerDefinition(state);
        }
        return output;
    }
    genEventHandlerDefinition(state) {
        let inner = this.genEventHandlersForState(state);
        let output = `
    <s>${this.genEventHandlerPrototype(state)}
    <s>{
    <s>  event_handler_breakpoint(jxc, hsm, context, event);
    <s>  ${this.compiler.genStatemachineStructName()}* sm = (${this.compiler.genStatemachineStructName()}*)hsm;
    <s>  ${this.genStateVarsTypedefName(state)}* vars = &sm->${this.genStateTempVarPathFromRoot(state)};
    <s>  const HsmState* this_state = &states[${this.genStateEnumName(state)}];
    <s>  uint32_t current_time = get_general_ms_counts();
    <s>  uint32_t time_in_state = current_time - vars->base_vars.time_state_entered;
    <s>  const ${this.compiler.getInputEventEnumTypeName()} event_id = event->event_id;
    <s>  bool default_event_handled_value = true; //used to allow handlers to "not consume" an event and allow parent to process it.
    <s>  (void)jxc; (void)hsm; (void)context; (void)event; (void)this_state; (void)time_in_state; (void)event_id; (void)default_event_handled_value; //prevent compiler warnings
    ${inner}
    <s>}

    `;
        output = StringUtils_1.StringUtils.processMarkers(output);
        return output;
    }
    listTriggers(triggers) {
        let output = "";
        let sep = "";
        for (let trigger of triggers) {
            output += `${sep}${this.compiler.buildInputEventEnumName(trigger.toString())}`;
            sep = ", ";
        }
        return output;
    }
    listTriggersReadable(triggers) {
        let output = this.listTriggers(triggers);
        output = output.replace(/HsmEventId__/g, "");
        return output;
    }
    expand(textToExpand, preventExpand) {
        let output = textToExpand;
        if (preventExpand == false) {
            output = this.compiler.expander.expandText(textToExpand);
        }
        return output;
    }
    genHandlersForTrigger(eventHandlers, trigger) {
        let output = "";
        for (let handler of eventHandlers) {
            if (handler.hasTrigger(trigger)) {
                output += this.genEventHandler(handler);
            }
        }
        return output;
    }
    genEventHandler(eh) {
        let output = "";
        let expandedGuardCode = "";
        let triggerCode = "";
        if (eh.hasSomeTriggers()) {
            triggerCode = "(";
            let join = "";
            for (let trigger of eh.getTriggers()) {
                triggerCode += `${join}(event_id == ${this.compiler.buildInputEventEnumName(trigger.toString())})`;
                join = " || ";
            }
            triggerCode += ")";
        }
        if (eh.hasTrigger(Triggers.ELSE)) {
            triggerCode = "true";
        }
        let guardText = "";
        if (eh.guard) {
            guardText = eh.guard.guardCode;
            if (triggerCode) {
                expandedGuardCode += " && ";
            }
            expandedGuardCode += `(${this.expand(guardText, eh.noExpand)})`;
        }
        let actionCode = "";
        if (eh.action) {
            actionCode = `${this.expand(eh.action.actionCode, eh.noExpand)};`; //TODO someday: if external transition, use call back to call between exits and enters so that action code is actually run in middle of transition (AKA after all exits are fired).
            actionCode = "\n" + StringUtils_1.StringUtils.indent(actionCode, "<s>    ");
        }
        let transitionCode = "";
        if (eh.nextState) {
            transitionCode = `\n<s>    Hsm_mark_transition_request(jxc, hsm, context, this_state, ${this.genStateEnumName(eh.nextState)});`;
        }
        let if_guard_text = "";
        if (guardText) {
            if_guard_text = "if " + guardText;
        }
        let if_trigger_text = "";
        if (eh.hasSomeTriggers()) {
            if_trigger_text = `ON ${this.listTriggersReadable(eh.getTriggers())} `;
        }
        if (eh.hasTrigger(Triggers.ELSE)) {
            if_trigger_text = `ELSE `;
        }
        output += `
    <dummy>
    <s>  //${(eh.commentOverride || if_trigger_text + if_guard_text).replace(/[\r\n]+/g, " ").replace(/[ ]{2,}|\\t/g, " ")}
    `.trim();
        if (Triggers.hasTransitionTrigger(eh.getTriggersSet())) {
            eh.markContextHandled = false; //not applicable to these events
        }
        else {
            output += `
      <s>
      <s>  if (Hsm_is_transition_requested(context)) { return; }    //stop processing if transitioning
      `.trim();
        }
        let context_handled_text = `
    <s>    //mark that event has handled
    <s>    context->event_handled = default_event_handled_value;
    <r>`;
        if (eh.markContextHandled == false) {
            context_handled_text = "";
        }
        output += `
    <s>  if( ${triggerCode + expandedGuardCode}  ){ ${actionCode} ${transitionCode}${context_handled_text}
    <s>  }
    <s>
    `;
        output = StringUtils_1.StringUtils.processMarkers(output);
        return output;
    }
    genEventHandlersForState(state) {
        let output = "";
        if (state.is_ortho_parent) {
            output = this.genOrthoParentEventHandlers(state);
        }
        else {
            output = this.genNormalStateEventHandlers(state);
        }
        return output;
    }
    genOrthoParentEventHandlers(state) {
        let inner = "";
        let output = "";
        //add event handlers specific to being an ortho parent
        let event = new Generator_1.EventHandler();
        let actionCode;
        //EXIT EVENT
        event = new Generator_1.EventHandler();
        event.action = new Generator_1.Action();
        event.setTriggers([Triggers.EXIT]);
        actionCode = nunjucks.renderString(`<r>
      <s>//loop through ortho kids and exit any of them that are still running
      <s>Hsm_exit_okids(jxc, hsm, vars->orthoKidContexts, COUNTOF(vars->orthoKidContexts));
      <r>`, { compiler: this, state: state });
        event.action.actionCode = StringUtils_1.StringUtils.processMarkers(actionCode);
        output += this.genEventHandler(event);
        output += this.genHandlersForTrigger(state.eventHandlers, Triggers.EXIT);
        //ENTER EVENT
        //generate this state's regular user defined enter action
        output += this.genHandlersForTrigger(state.eventHandlers, Triggers.ENTER); //BUG FIX! the enter event for the parent must come before childrens.
        //generate special entry code for being an ortho parent
        event = new Generator_1.EventHandler();
        event.action = new Generator_1.Action();
        event.setTriggers([Triggers.ENTER]);
        actionCode = nunjucks.renderString(`<r>
      <s>//setup orthogonal kid contexts
      <s>Hsm_set_contexts_region_parent_id(jxc, hsm, this_state->node.id, vars->orthoKidContexts, COUNTOF(vars->orthoKidContexts));
      {%- for kid in state.kids %}
      <s>vars->orthoKidContexts[{{loop.index0}}].region_top_state_id = {{compiler.stateGen.genStateEnumName(kid)}};
      {%- endfor %}      
      <s>Hsm_handle_ortho_kids_enter(jxc, hsm, context, vars->orthoKidContexts, COUNTOF(vars->orthoKidContexts)); 

    `, { compiler: this.compiler, state: state });
        event.action.actionCode = StringUtils_1.StringUtils.processMarkers(actionCode);
        output += this.genEventHandler(event);
        //LANDED_IN EVENT
        output += this.genHandlersForTrigger(state.eventHandlers, Triggers.LANDED_IN);
        //rest of events.
        let nonTransitionHandlers = Triggers.getNonTransitionTriggerHandlers(state);
        let customEventHandlers = [];
        let doEventHandlers = [];
        //render 
        nonTransitionHandlers.filter(function (handler) {
            if (handler.hasTrigger(Triggers.DO)) {
                //TODO HIGH - do not differentiate! It causes problems if DO event is joined with something custom like "(DO || MY_EVENT) / action".
                // why? Because being lumped in with the DO event prevents the check of event already handled.
                doEventHandlers.push(handler);
            }
            else {
                customEventHandlers.push(handler);
            }
        });
        //EVENT proxying + do
        event = new Generator_1.EventHandler();
        event.action = new Generator_1.Action();
        //enterEvent.triggers = new Set<Trigger>([Triggers.ORTHO_PROXY]);
        event.guard = new Generator_1.Guard();
        event.noExpand = true;
        event.commentOverride = "Proxy events to orthogonal kids";
        event.guard.guardCode = "Hsm_event_part_of_transition(event) == false";
        actionCode = `<r>
      <s>Hsm_handle_ortho_kids_event(jxc, hsm, context, vars->orthoKidContexts, COUNTOF(vars->orthoKidContexts), event);
      <s>vTRY_BUBBLE_EXCEPTION();
      <s>
      <s>//break if ortho kids triggered a transition above their level
      <s>if (Hsm_is_transition_requested(context)) {
      <s>  return;
      <s>}
      <s>
      <s>//this ortho parent state should only do something with the event if none of its ortho kids has handled it
      <s>if (event->event_id == HsmEventId__DO) 
      <s>{
      <s>  //ortho parent does the DO event like normal
      <s>  ${this.genEventHandlersForArray(doEventHandlers)}
      <s>} 
      <s>if (event->event_id == HsmEventId__DO || context->event_handled == false || event->event_id == HsmEventId__TEST_TRANSITIONS) 
      <s>{
      <s>  //only handle events in parent state if kids hadn't already dealt with it
      <s>  ${this.genEventHandlersForArray(customEventHandlers)}
      <s>}
      <s><r>`;
        event.action.actionCode = StringUtils_1.StringUtils.processMarkers(actionCode);
        event.markContextHandled = false; //not applicable to this event
        output += this.genEventHandler(event);
        return output;
    }
    genEventHandlersForArray(eventHandlers) {
        let output = "";
        for (let eh of eventHandlers) {
            output += this.genEventHandler(eh);
        }
        return output;
    }
    genNormalStateEventHandlers(state) {
        let output = "";
        let joiner = "\n  ";
        let handlers = state.eventHandlers.slice(0);
        let restOfEvents = new Set(state.eventHandlers);
        let hh;
        var tthis = this;
        //render EXIT first
        hh = new Set(restOfEvents);
        hh.forEach(function (e, notNeeded, set) {
            if (e.hasTrigger(Triggers.EXIT)) {
                output += joiner + tthis.genEventHandler(e);
                restOfEvents.delete(e);
            }
        });
        //render ENTER next
        hh = new Set(restOfEvents);
        hh.forEach(function (e, notNeeded, set) {
            if (e.hasTrigger(Triggers.ENTER)) {
                output += joiner + tthis.genEventHandler(e);
                restOfEvents.delete(e);
            }
        });
        //render LANDED_IN next
        hh = new Set(restOfEvents);
        hh.forEach(function (e, notNeeded, set) {
            if (e.hasTrigger(Triggers.LANDED_IN)) {
                output += joiner + tthis.genEventHandler(e);
                restOfEvents.delete(e);
            }
        });
        output += StringUtils_1.StringUtils.processMarkers(`
      <s>
      <s>  //------------- END OF TRANSITION HANDLERS --------------------
      <s>  if (Hsm_event_part_of_transition(event)) { 
      <s>    return; 
      <s>  }    
      <s>
    `);
        //render DO next
        //FUTURE TODO - don't treat do any differently. This will help for upcoming event ordering.
        hh = new Set(restOfEvents);
        hh.forEach(function (e, notNeeded, set) {
            if (e.hasTrigger(Triggers.DO)) {
                output += joiner + tthis.genEventHandler(e);
                restOfEvents.delete(e);
            }
        });
        //render normal rest
        hh = new Set(restOfEvents);
        hh.forEach(function (e, notNeeded, set) {
            output += joiner + tthis.genEventHandler(e);
            restOfEvents.delete(e);
        });
        return output;
    }
    classPrefix() { return this.compiler.classPrefix; }
    sep() { return this.compiler.sep; }
    isRoot(state) {
        return state == this.compiler.hsm.rootState;
    }
    genStateTempVarPathFromRoot(state) {
        let output = "";
        let joiner = "";
        do {
            output = this.genStateVarInstanceName(state) + joiner + output;
            state = state.parent;
            joiner = ".";
        } while (state != null);
        return output;
    }
    genStateIdCountEnumName() {
        return this.compiler.buildClassEnumName("STATE_COUNT");
    }
    genStateEnums(states) {
        let output = "";
        output += this.compiler.createCommentHeader(`Enumeration for all ${this.compiler.classFullName} state IDs`).trim() + "\n";
        output += `typedef enum _${this.stateEnumType}\n{\n`;
        let rootState;
        let inner = "";
        for (let i = 0; i < states.length; i++) {
            let state = states[i];
            if (state === this.compiler.hsm.rootState) {
                rootState = state;
                //inner += `  ${this.getStateEnumName(state)} = ${HSM_ROOT_STATE_ENUM_ID},\n`;
            }
            inner += `  ${this.genStateEnumName(state)} = ${state.outputId},\n`;
        }
        inner = StringUtils_1.StringUtils.alignRegexInStringsSimple(/=/, inner);
        output += inner;
        output += "  //--------------------------\n";
        output += `  ${this.genStateIdCountEnumName()} = ${states.length},\n`;
        output += `} ${this.stateEnumType};\n`;
        output += `static_assert(${this.genStateEnumName(rootState)} == ${Compiler_1.ROOT_STATE_ID}, "'${this.genStateEnumName(rootState)}' must equal ${Compiler_1.ROOT_STATE_ID} for root state");`;
        return output;
    }
    genStateInstanceName(state) {
        return this.compiler.classPrefix + "_" + this.genStateUniqueName(state) + "_ref";
    }
    genStateExternStateInstances(states) {
        //const HsmState * const ButSm_State_ROOT_STATE_S0 = &states[ButSm_StateId__ROOT_STATE_S0];
        let moreOutput = `\n`;
        for (let state of states) {
            moreOutput += `extern const HsmState * const ${this.genStateInstanceName(state)};\n`;
        }
        return moreOutput;
    }
    genStateDefinitions(states) {
        let innerString = "";
        for (let i = 0; i < states.length; i++) {
            let state = states[i];
            innerString += this.genStateDefinition(state);
        }
        let output = "";
        output += `
      <s>static HsmState const states[${this.genStateIdCountEnumName()}] = {
        ${innerString}
      <s>};
    <r>`;
        //const HsmState * const ButSm_State_ROOT_STATE_S0 = &states[ButSm_StateId__ROOT_STATE_S0];
        let moreOutput = `\n`;
        for (let state of states) {
            moreOutput += `const HsmState * const ${this.genStateInstanceName(state)} = &states[${this.genStateEnumName(state)}];\n`;
        }
        moreOutput = StringUtils_1.StringUtils.alignStringMarkersSimple(["="], moreOutput);
        output += moreOutput;
        output = StringUtils_1.StringUtils.processMarkers(output);
        output = this.compiler.createCommentHeader(`'${this.compiler.classFullName}' STATE DEFINITION`) + output;
        return output;
    }
}
exports.StateGen = StateGen;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiU3RhdGVHZW4uanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJTdGF0ZUdlbi50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUFBLG1EQUFtRDtBQUNuRCx5Q0FBcUQ7QUFFckQseURBQXNEO0FBQ3RELDJDQUFtRTtBQUNuRSx1Q0FBc0M7QUFDdEMscUNBQXFDO0FBR3JDO0lBT0UsWUFBYSxRQUFtQjtRQU5oQyxxQkFBZ0IsR0FBWSxXQUFXLENBQUM7UUFDeEMsc0JBQWlCLEdBQVksRUFBRSxDQUFDO1FBQ2hDLGtCQUFhLEdBQVksRUFBRSxDQUFDO1FBSzFCLElBQUksQ0FBQyxRQUFRLEdBQUcsUUFBUSxDQUFDO0lBQzNCLENBQUM7SUFFRCxrQkFBa0IsQ0FBQyxLQUFXO1FBQzVCLElBQUksTUFBTSxHQUFHLElBQUksQ0FBQyxzQkFBc0IsQ0FBQyxLQUFLLENBQUMsQ0FBQztRQUNoRCxFQUFFLENBQUEsQ0FBQyxLQUFLLElBQUksSUFBSSxDQUFDLFFBQVEsQ0FBQyxHQUFHLENBQUMsU0FBUyxDQUFDLENBQUEsQ0FBQztZQUN2QyxNQUFNLEdBQUcsTUFBTSxDQUFDO1FBQ2xCLENBQUM7UUFDRCxNQUFNLENBQUMsTUFBTSxDQUFDO0lBQ2hCLENBQUM7SUFFRCx3QkFBd0IsQ0FBQyxLQUFZO1FBQ25DLE1BQU0sQ0FBQyxJQUFJLENBQUMsa0JBQWtCLENBQUMsS0FBSyxDQUFDLEdBQUcsVUFBVSxDQUFDO0lBQ3JELENBQUM7SUFFRCx1QkFBdUIsQ0FBQyxLQUFZO1FBQ2xDLE1BQU0sQ0FBQyxJQUFJLENBQUMsUUFBUSxDQUFDLFdBQVcsR0FBRyxHQUFHLEdBQUksSUFBSSxDQUFDLGtCQUFrQixDQUFDLEtBQUssQ0FBQyxHQUFHLE9BQU8sQ0FBQztJQUNyRixDQUFDO0lBRUQsc0JBQXNCLENBQUMsS0FBYSxFQUFFLFNBQVMsR0FBRyxJQUFJO1FBQ3BELElBQUksTUFBTSxHQUFHLEVBQUUsQ0FBQztRQUNoQixJQUFJLEdBQUcsR0FBRyxLQUFLLENBQUM7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