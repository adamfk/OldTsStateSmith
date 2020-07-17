"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
const MyRegex_1 = require("../ts-utils/MyRegex");
const State_1 = require("./State");
const Compiler_1 = require("./Compiler");
const Triggers = require("./Triggers");
const Misc_1 = require("../ts-utils/Misc");
const r = new MyRegex_1.MyRegex();
//TODOLOW someday make an actual class that stores original text & casing for trigger.
class Trigger extends String {
}
exports.Trigger = Trigger;
class Guard {
}
exports.Guard = Guard;
class Action {
}
exports.Action = Action;
exports.DEFAULT_TRANSITION_ORDER_NUMBER = 1000000;
exports.ELSE_TRANSITION_ORDER_OFFSET_NUMBER = exports.DEFAULT_TRANSITION_ORDER_NUMBER * 100;
class EventHandler {
    constructor() {
        /** if specified, it will determine which event handler should be checked first. Lower numbers take priority. */
        this.order = exports.DEFAULT_TRANSITION_ORDER_NUMBER;
        this.triggers = new Set(); //used because they must be unique anyway
        this.guard = null;
        this.action = null;
        this.nextState = null;
        this.stopsPropagation = false; //TODO support?
        this.noExpand = false; //TODO support?
        this.commentOverride = ""; //TODO support?
        this.markContextHandled = true; //TODO support?
    }
    setTriggers(triggers) {
        this.triggers = new Set();
        this.addTriggers(triggers);
    }
    addTriggers(triggers) {
        for (const t of triggers) {
            this.addTrigger(t);
        }
    }
    addTrigger(trigger) {
        this.triggers.add(trigger.toUpperCase());
    }
    triggersCount() {
        return this.triggers.size;
    }
    hasSomeTriggers() {
        return this.triggers.size > 0;
    }
    getTriggers() {
        let triggers = [...this.triggers];
        return triggers;
    }
    getTriggersSet() {
        return this.triggers;
    }
    hasTrigger(trigger) {
        let result = this.triggers.has(trigger.toUpperCase());
        return result;
    }
    removeTriggers(triggers) {
        for (let trigger of triggers) {
            if (this.triggers.delete(trigger) == false) {
                throw `failed removing trigger '${trigger}'`;
            }
        }
    }
    shallowCopy() {
        let newEh = new EventHandler();
        newEh.set(this);
        return newEh;
    }
    deepCopy() {
        let newEh = new EventHandler();
        Misc_1.shallowCopyInto(newEh, this);
        newEh.triggers = new Set(this.triggers);
        newEh.action = deepCopy(this.action); //TODOLOW not ideal
        newEh.guard = deepCopy(this.guard); //TODOLOW not ideal
        return newEh;
    }
    set(eh) {
        Misc_1.shallowCopyInto(this, eh);
        return this;
    }
}
exports.EventHandler = EventHandler;
function deepCopy(obj) {
    let result = JSON.parse(JSON.stringify(obj));
    return result;
}
class InputEventHandler {
    constructor() {
        /** if specified, it will determine which event handler should be checked first. Lower numbers take priority. */
        this.order = exports.DEFAULT_TRANSITION_ORDER_NUMBER;
        this.triggers = [];
        this.guard = null;
        this.action = null;
        this.nextInputStateId = null;
        this.stopsPropagation = false;
    }
}
exports.InputEventHandler = InputEventHandler;
class InputState {
    constructor() {
        /** true if state is a folder/group in yed diagram and is currently closed. */
        this.groupIsCollapsed = false;
        this.eventHandlers = [];
        this.isInitialState = false;
        //TODO state context
    }
    is_orthogonal() {
        return this.orthogonal_order != null;
    }
}
exports.InputState = InputState;
class InputParsedVar {
}
exports.InputParsedVar = InputParsedVar;
class StructField {
    constructor() {
        this.preComment = "";
        this.lineComment = "";
    }
    //initialValue : string; //TODO maybee initial value for struct fields
    isArray() {
        return this.arraySize.trim().length > 0;
    }
    isBitField() {
        return this.bitFieldSize.trim().length > 0;
    }
}
exports.StructField = StructField;
class InputHsm {
    constructor() {
        this.states = [];
        this.varsStructInnerText = "";
        this.expansionDefinitions = ""; //like   'output_event(x) ====> hsm->vars.outputs.events.{{x}} == true;'
        this.executeBeforeCode = "";
        this.executeAfterCode = "";
        this.c_file_top = "";
        this.h_file_top = "";
        this.cFunctions = "";
        this.cFunctionsNoExp = "";
        this.cPrototypes = "";
        this.cPrototypesNoExp = "";
        this.inputValues = "";
        this.outputValues = "";
        this.outputEvents = "";
        this.imports = "";
        this.output_filename = "";
    }
}
exports.InputHsm = InputHsm;
class CompileSettings {
}
exports.CompileSettings = CompileSettings;
class Hsm {
    constructor() {
        this.states = [];
    }
}
exports.Hsm = Hsm;
class RenderedHsm extends Hsm {
    constructor() {
        super();
        this.eventSet = new Set();
        this.inputIdToStateMapping = new Map();
        this.enumIdToStateMapping = new Map();
        this.nextStateId = 1;
        this.outputOldBadInitialName = true;
        this.shouldSimplifyInitialStateTransitions = false;
        //doesn't include custom event triggers
        this.reservedTriggers = [];
    }
    addEvent(label) {
        label = label.trim().replace(/^enter$|^entry$/ig, Triggers.ENTER);
        this.eventSet.add(label.toUpperCase());
        return label;
    }
    //allowed to return null
    getStateFromInputId_raw(inputId) {
        let state = this.inputIdToStateMapping.get(inputId) || null;
        return state;
    }
    getStateFromOutputId(id) {
        let state = this.enumIdToStateMapping.get(id) || null;
        return state;
    }
    getStateFromInputId(inputId) {
        let state = this.inputIdToStateMapping.get(inputId);
        if (!state) {
            console.log(inputId, state);
            throw "Not found!";
        }
        return state;
    }
    addAndGetTrigger(triggerLabel) {
        let trigger = this.addEvent(triggerLabel);
        return trigger;
    }
    mangleCodeIntoFunctionName(code) {
        var result = code
            .replace(/\(\s*\)/g, "") //remove () for invocation
            .replace(/</g, "_is_lt_")
            .replace(/>/g, "_is_gt_")
            .replace(/==/g, "_eq_")
            .replace(/&&/g, "_and_")
            .replace(/\|\|/g, "_or_")
            .replace(/!/g, "_not_")
            .replace(/\(/g, "_Pp_")
            .replace(/\)/g, "_pP_")
            .replace(/\[/g, "_Bb_") //[ = _Bb_
            .replace(/\]/g, "_bB_")
            .replace(/\W/g, "");
        //.toLowerCase();
        return result;
    }
    genFunctionNamePart(code) {
        let name;
        code = code.trim() + "_" + Math.round(Math.random() * 10000); //TODO keep track of used names to ensure unique or resuse where possible
        name = this.mangleCodeIntoFunctionName(code);
        name = name.substr(0, 50);
        return name;
    }
    maybeCreateGuard(code) {
        let guard = null;
        if (code && code.trim()) {
            code = code.trim();
            let name = this.mangleCodeIntoFunctionName(code);
            guard = new Guard();
            guard.name = name;
            guard.guardCode = code;
        }
        return guard;
    }
    maybeCreateAction(code) {
        let action = null;
        if (code && code.trim()) {
            code = code.trim();
            let name = this.mangleCodeIntoFunctionName(code);
            action = new Action();
            action.name = name;
            action.actionCode = code;
        }
        return action;
    }
    processInputEventHandler(ih) {
        //look through nextInputStateId and create links
        var eh = new EventHandler();
        eh.guard = this.maybeCreateGuard(ih.guard);
        eh.action = this.maybeCreateAction(ih.action);
        eh.stopsPropagation = ih.stopsPropagation;
        eh.nextState = this.getStateFromInputId_raw(ih.nextInputStateId);
        eh.order = ih.order;
        for (let i = 0; i < ih.triggers.length; i++) {
            eh.addTrigger(this.addAndGetTrigger(ih.triggers[i]));
        }
        //console.log(eh);
        return eh;
    }
    setupStateEventHandlers(state) {
        for (let i = 0; i < state.inputState.eventHandlers.length; i++) {
            let inputEH = state.inputState.eventHandlers[i];
            state.addEventHandlers([this.processInputEventHandler(inputEH)]);
        }
    }
    getAllStates() {
        if (this.orderedStates) {
            return this.orderedStates;
        }
        var result = Array.from(this.inputIdToStateMapping.values());
        return result;
    }
    getAllNonDirectiveInputEventNames() {
        var result = Array.from(this.eventSet.keys());
        result.sort(); //to help improve git diffing
        for (var i = 0; i < result.length; i++) {
            var element = result[i];
            if (Triggers.isPseudoEventName(element)) {
                result.splice(i, 1);
                i--;
            }
        }
        return result;
    }
    setupAllStateEventHandlers() {
        for (const state of this.getAllStates()) {
            this.setupStateEventHandlers(state);
        }
    }
    addState(state) {
        //figure out mapping from input state string ID to state so that
        //we can establish links later
        if (this.inputIdToStateMapping.has(state.inputState.id)) {
            console.log(state.inputState);
            throw "Input State ID already added!!!";
        }
        else {
            this.inputIdToStateMapping.set(state.inputState.id, state);
        }
        //determine output IDs
        if (state.label == Compiler_1.ROOT_STATE_LABEL) {
            this.rootState = state;
        }
    }
    anyPlusSupport() {
        this.reservedTriggers = this.reservedTriggers.map(e => e.toUpperCase());
        let anyPlusEvents = [...new Set([...this.reservedTriggers, ...this.getAllNonDirectiveInputEventNames()])];
        for (const state of this.getAllStates()) {
            let newHandlers = [];
            if (state.eventHandlers.length == 0) {
                continue;
            }
            for (const eh of state.eventHandlers) {
                if (eh.hasTrigger(Triggers.ANY_PLUS)) {
                    if (eh.triggersCount() > 1) {
                        throw "can't combine other events with ANY+. Doesn't make sense.";
                    }
                    if (eh.nextState) {
                        throw "ANY+ can't be used for a transition. You'll have to use ANY instead.";
                    }
                    for (const anyPartTrigger of anyPlusEvents) {
                        let newEh = eh.deepCopy();
                        newEh.setTriggers([anyPartTrigger]);
                        newHandlers.push(newEh);
                    }
                }
                else {
                    newHandlers.push(eh);
                }
            }
            while (state.eventHandlers.length > 0) {
                state.removeEventHandler(state.eventHandlers[0]);
            }
            for (const eh of newHandlers) {
                state.addEventHandlers([eh]);
            }
        }
    }
    numberTreeFrom(state) {
        let next = state.outputId + 1;
        //do some other tracking while looping tree
        this.enumIdToStateMapping.set(state.outputId, state);
        for (let kid of state.kids) {
            kid.depth = state.depth + 1;
            kid.outputId = next;
            this.numberTreeFrom(kid);
            next = kid.max_descendant_id + 1;
        }
        state.max_descendant_id = next - 1;
    }
    validateOrthogonalFrom(state) {
        let orthoCount = 0;
        let otherCount = 0;
        for (let kid of state.kids) {
            if (kid.inputState.is_orthogonal()) {
                orthoCount++;
                state.is_ortho_parent = true;
            }
            else {
                otherCount++;
            }
            this.validateOrthogonalFrom(kid);
        }
        if (orthoCount > 0 && otherCount > 0) {
            console.log();
            throw new Error(`State '${state.label}' has mix of ortho/normal kids. They must all be ortho or not ortho.`);
        }
    }
    sortOrthogonalStates(state) {
        if (state.is_ortho_parent) {
            state.kids = state.kids.sort(function (a, b) {
                let result = a.inputState.orthogonal_order - b.inputState.orthogonal_order;
                return result;
            });
        }
        for (let kid of state.kids) {
            this.sortOrthogonalStates(kid);
        }
    }
    validateInitialStates() {
        let states = this.getAllStates();
        for (const state of states) {
            var initialStates = state.kids.filter(state => state.isInitialState);
            if (initialStates.length > 1) {
                console.log(initialStates);
                throw `State '${state.label}' can have only up to 1 initial states but it has count=${initialStates.length}.`;
            }
            if (initialStates.length == 1) {
                if (initialStates[0].eventHandlers.length == 0) {
                    throw `Initial state for '${state.label}' must have a transition`;
                }
                if (initialStates[0].incomingEventHandlers.length > 0) {
                    //throw "initial state cannot have an incoming edge/transition";  //why not? It's convenient.
                }
            }
        }
    }
    processAndValidateStateTransitions() {
        let states = this.getAllStates();
        for (const state of states) {
            let elseCount = 0;
            let eventTriggerCount = 0;
            for (const eh of state.eventHandlers) {
                if (Triggers.hasTransitionTrigger(eh.getTriggersSet()) && eh.nextState != null) {
                    console.log(state, eh);
                    throw "Cannot initiate a transition on a transition event";
                }
                if (eh.hasTrigger(Triggers.ELSE) == false) {
                    //doesn't have else
                    if (eh.order >= exports.ELSE_TRANSITION_ORDER_OFFSET_NUMBER) {
                        throw "order exceeds limit";
                    }
                    if (eh.hasSomeTriggers()) {
                        eventTriggerCount += eh.triggersCount();
                    }
                }
                else {
                    //has else
                    if (elseCount > 0) {
                        throw "Can only have one transition with `else`";
                    }
                    elseCount++;
                    if (eh.nextState == null) {
                        console.log(state, eh);
                        throw "'else' keyword only valid on external transitions";
                    }
                    if (eh.order != exports.DEFAULT_TRANSITION_ORDER_NUMBER) {
                        console.log(state, eh);
                        throw "don't specify an order for else. It will always be last.";
                    }
                    if (eh.triggersCount() > 1) {
                        console.log(state, eh);
                        throw "can't combine any other triggers with 'else'";
                    }
                    if (eh.guard != null) {
                        console.log(state, eh);
                        throw "can't combine guard with 'else'";
                    }
                    eh.order = exports.ELSE_TRANSITION_ORDER_OFFSET_NUMBER;
                }
            } //end of event handlers
            if (elseCount > 0 && eventTriggerCount > 0) {
                console.log(state, state.eventHandlers);
                console.log(`Warning: Probably doesn't make sense to mix event triggers and else statements as the else will accept any event.`);
            }
        }
    }
    splitBadlyGroupedEventHandlers() {
        for (const state of this.getAllStates()) {
            //need to split apart any event handlers that have both a transition trigger and a normal trigger
            let toAdd = [];
            for (let eh of state.eventHandlers) {
                let grouping = Triggers.groupTransitionTriggers(eh.getTriggers());
                if (grouping.isMixed()) {
                    let newEh = eh.deepCopy();
                    eh.setTriggers(grouping.transitional); //leave original object with transitional triggers
                    newEh.setTriggers(grouping.normal); //cloned object keeps normal triggers
                    toAdd.push(newEh);
                }
            }
            state.addEventHandlers(toAdd);
        }
    }
    sortTransitionsForStates() {
        //sort transitions
        for (const state of this.getAllStates()) {
            state.sortEventHandlers(function (a, b) {
                let result = a.order - b.order;
                return result;
            });
        }
    }
    setupHeirachyLinks() {
        var rootFound = false;
        for (const state of this.getAllStates()) {
            if (state === this.rootState) {
                rootFound = true;
            }
            else {
                let parentState = this.getStateFromInputId(state.inputState.parentId);
                state.parent = parentState;
                state.parent.kids.push(state);
            }
        }
        if (!rootFound) {
            console.log(this);
            throw "Failed to find root! Must match name: " + Compiler_1.ROOT_STATE_LABEL;
        }
    }
    moveInitialStatesBehindParents() {
        this.orderedStates = this.getAllStates();
        var statesToMove = this.getAllStates().filter(state => state.isInitialState);
        for (const stateToMove of statesToMove) {
            Misc_1.removeFromArray(this.orderedStates, stateToMove);
            let parentIndex = this.orderedStates.indexOf(stateToMove.parent);
            if (parentIndex < 0) {
                throw "couldn't find parent??? wut wut?";
            }
            let insertIndex = parentIndex + 1;
            this.orderedStates.splice(insertIndex, 0, stateToMove);
            Misc_1.removeFromArray(stateToMove.parent.kids, stateToMove);
            stateToMove.parent.kids.unshift(stateToMove);
            //see STATE-8
            if (this.outputOldBadInitialName) {
                stateToMove.label = stateToMove.parent.label + `__` + stateToMove.label; //hack to make output the same as previous. Should someday clean up.
            }
        }
    }
    makeBlankAction() {
        let a = new Action();
        a.actionCode = "";
        return a;
    }
    simplifyInitialStateTransitions() {
        let initialStates = this.getAllStates().filter(state => state.isInitialState);
        let actionCodeToAdd;
        for (const initialState of initialStates) {
            if (!initialState.parent.is_ortho_parent && initialState.parent.incomingEventHandlers.length > 0) {
                let eventualTarget;
                if (initialState.isComplexInitialState()) {
                    eventualTarget = initialState;
                }
                else {
                    eventualTarget = initialState.getSimpleInitialStateTranstion();
                    let onlyTransition = initialState.eventHandlers[0];
                    if (initialState.eventHandlers.length != 1) {
                        throw "???";
                    }
                    if (onlyTransition.action && onlyTransition.action.actionCode) {
                        actionCodeToAdd = onlyTransition.action.actionCode;
                    }
                    this.removeState(initialState);
                }
                //find all transitions to parent and move to optimized destination
                while (initialState.parent.incomingEventHandlers.length > 0) {
                    let transitionToParent = initialState.parent.incomingEventHandlers[0]; //loop this way because retargetting will affect `intialState.parent.incomingEventHandlers`
                    //add initialState's transition action code to transition if needed
                    if (actionCodeToAdd) {
                        if (transitionToParent.action) {
                            transitionToParent.action.actionCode += "\n" + actionCodeToAdd;
                        }
                        else {
                            transitionToParent.action = new Action();
                            transitionToParent.action.actionCode = actionCodeToAdd;
                        }
                    }
                    State_1.State.retargetTransition(transitionToParent, eventualTarget);
                }
                //TODOLOW consider putting some kind of handler back in for any skipped states to allow for dynamically setting state
                //ex: `S --> S1 --> S11` becomes `S --> S11` and `S1` has no handler to go to `S11` anymore.
            }
        }
    }
    removeState(state) {
        this.inputIdToStateMapping.delete(state.inputState.id);
        Misc_1.removeFromArray(this.orderedStates, state);
        Misc_1.removeFromArray(state.parent.kids, state);
        for (const eh of state.eventHandlers.filter(e => e.nextState)) {
            state.removeEventHandler(eh);
        }
        if (state.incomingEventHandlers.length > 0) {
            console.log(state.incomingEventHandlers);
            throw "can't remove state while it still has incoming edges";
        }
    }
    initialProcessAndValidate() {
        this.rootState.outputId = Compiler_1.ROOT_STATE_ID;
        for (const state of this.getAllStates()) {
            state.is_ortho_kid = state.inputState.is_orthogonal();
            state.isInitialState = state.inputState.isInitialState;
        }
        this.setupHeirachyLinks();
        this.moveInitialStatesBehindParents();
        this.validateOrthogonalFrom(this.rootState);
        this.setupAllStateEventHandlers();
        this.anyPlusSupport();
        this.validateInitialStates();
        if (this.shouldSimplifyInitialStateTransitions) {
            this.simplifyInitialStateTransitions();
        }
        this.validateInitialStates();
        this.splitBadlyGroupedEventHandlers();
        this.processAndValidateStateTransitions();
        this.sortTransitionsForStates(); //has to happen after event handlers split and processed above
    }
    convertPseudoInitialStates() {
        let initial_states = this.getAllStates().filter(state => state.isInitialState);
        for (const state of initial_states) {
            let newParentEh = new EventHandler();
            //we know that the initial state has 1 or more event handlers because of prior validation
            if (state.isComplexInitialState()) {
                //need to add event handler to transition parent state to pseudo initial state
                newParentEh.nextState = state;
                newParentEh.addTrigger(Triggers.LANDED_IN);
            }
            else {
                //simple initial state that can be removed
                newParentEh = state.eventHandlers[0]; //need to keep original transition's possible action code
                //newParentEh.nextState = state.eventHandlers[0].nextState;
                newParentEh.addTrigger(Triggers.LANDED_IN);
                newParentEh.guard = null; //we know it was just "true" or null at this point
                this.removeState(state);
            }
            state.parent.addEventHandlers([newParentEh]);
        }
    }
    /**
     * done so that states can be added and removed
     */
    finalizeStates() {
        this.numberTreeFrom(this.rootState);
        this.sortOrthogonalStates(this.rootState); //needs to be done after `numberTreeFrom()` to keep same output as before
    }
    genSummaryText() {
        let output = "";
        let event_names = this.getAllNonDirectiveInputEventNames();
        event_names.sort();
        output += "INPUT EVENTS:\n";
        for (let input of event_names) {
            output += `  ${input}\n`;
        }
        output += "\n------------------------------------------\n\n";
        output += "this.hsm.inputHsm : \n";
        output += JSON.stringify(this.inputHsm, null, "\t");
        return output;
    }
} /////////////////
exports.RenderedHsm = RenderedHsm;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiR2VuZXJhdG9yLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiR2VuZXJhdG9yLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7O0FBQUEsbURBQW1EO0FBQ25ELGlEQUEyQztBQUMzQyxtQ0FBK0I7QUFDL0IseUNBQTZEO0FBQzdELHVDQUFzQztBQUN0QywyQ0FBb0U7QUFHcEUsTUFBTSxDQUFDLEdBQUcsSUFBSSxpQkFBTyxFQUFFLENBQUM7QUFFeEIsc0ZBQXNGO0FBQ3RGLGFBQXFCLFNBQVEsTUFBTTtDQUVsQztBQUZELDBCQUVDO0FBRUQ7Q0FHQztBQUhELHNCQUdDO0FBRUQ7Q0FHQztBQUhELHdCQUdDO0FBR1ksUUFBQSwrQkFBK0IsR0FBRyxPQUFPLENBQUM7QUFDMUMsUUFBQSxtQ0FBbUMsR0FBRyx1Q0FBK0IsR0FBRyxHQUFHLENBQUM7QUFFekY7SUFBQTtRQUNFLGdIQUFnSDtRQUNoSCxVQUFLLEdBQUcsdUNBQStCLENBQUM7UUFDaEMsYUFBUSxHQUFrQixJQUFJLEdBQUcsRUFBVyxDQUFDLENBQUMseUNBQXlDO1FBQy9GLFVBQUssR0FBWSxJQUFJLENBQUM7UUFDdEIsV0FBTSxHQUFhLElBQUksQ0FBQztRQUN4QixjQUFTLEdBQVksSUFBSSxDQUFDO1FBQzFCLHFCQUFnQixHQUFjLEtBQUssQ0FBQyxDQUFFLGVBQWU7UUFDckQsYUFBUSxHQUFhLEtBQUssQ0FBQyxDQUFDLGVBQWU7UUFDM0Msb0JBQWUsR0FBRyxFQUFFLENBQUMsQ0FBTyxlQUFlO1FBQzNDLHVCQUFrQixHQUFHLElBQUksQ0FBQyxDQUFFLGVBQWU7SUFtRTdDLENBQUM7SUFqRUMsV0FBVyxDQUFDLFFBQW9CO1FBQzlCLElBQUksQ0FBQyxRQUFRLEdBQUcsSUFBSSxHQUFHLEVBQUUsQ0FBQztRQUMxQixJQUFJLENBQUMsV0FBVyxDQUFDLFFBQVEsQ0FBQyxDQUFDO0lBQzdCLENBQUM7SUFFRCxXQUFXLENBQUMsUUFBb0I7UUFDOUIsR0FBRyxDQUFDLENBQUMsTUFBTSxD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