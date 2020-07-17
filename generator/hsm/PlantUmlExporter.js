"use strict";
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
/*

Bug. Not recursing into ROOT.SYSTEM_STARTED.REGULAR_SCREENS.NORMAL.HOME_VIEW.RUNNING in view_MainSm

Lots of room for efficiency gains.

How to see dot markup?
  can try adding `!pragma svek_trace on` but it doesn't seem to work.

for generating large diagrams, see http://plantuml.com/faq


http://plantuml.com/state-diagram

@startuml

[*] -> State3
state "BASIC" as ROOT__op__BASIC {
[*] --> ROOT__op__BASIC
}
state "long name" as State3 {
state "Accumulate Enough Data\nLong State Name" as long1
long1 : Just a test
[*] --> long1
long1 --> long1 : New Data
long1 --> ProcessData : TICK [condition] / { x++;\ny++; }
long1 --> AO1
state "another one" as AO1 {

}
}
State3 --> [*] : Aborted
 
@enduml

*/
Object.defineProperty(exports, "__esModule", { value: true });
const StringUtils_1 = require("../ts-utils/StringUtils");
const Triggers = require("./Triggers");
class DividedEventHandlers {
    constructor() {
        this.normal = [];
    }
}
exports.DividedEventHandlers = DividedEventHandlers;
class PlantUmlExporter {
    constructor() {
        this.stopAtCollapsed = true;
        this.showTransitions = true;
        this.showEventGuardActions = true;
        /**
         * if true, it will use the shortest possible name that uniquely identifies a state.
         * if false, the name for a state will be the full path.
         *
         * There are advantages to both, but shorter names can make source diagrams easier
         * to read and git diff. It is also has the benefit of being more immune to renaming
         * of parent states far up the change. If full path used and top most state renamed,
         * then you may have 100 states show up with changes in git diff which makes it very
         * difficult to see any other changes.
         */
        this.useShortUniqueNames = true;
        /** primarily used for anonymizing a structure before posting online */
        this.useIdsAsNames = false;
        this.shortUniqueNames = new Map();
    }
    getShortUniqueName(state) {
        let name = this.shortUniqueNames.get(state);
        if (name == null) {
            throw "No state found. make sure to preprocess.";
        }
        return name;
    }
    /**
     * Builds name by ascending up ancestry
     * @param state
     * @param maxLength
     */
    getUpwardChainName(state, maxLength) {
        let chainName = state.label;
        let curState = state;
        let count = 0;
        while (count < maxLength && curState.parent != null) {
            count++;
            curState = curState.parent;
            chainName = curState.label + "." + chainName; //can't use "__" as plant uml uses that to create __underlines__.
        }
        return chainName;
    }
    /**
     *
     * @param state
     */
    preProcess(inputStates, upwardLength) {
        let leafNames = new Map();
        for (let state of inputStates) {
            let label = this.getUpwardChainName(state, upwardLength);
            let states = leafNames.get(label) || [];
            states.push(state);
            leafNames.set(label, states);
        }
        for (let [key, states] of leafNames) {
            if (states.length == 1) {
                this.shortUniqueNames.set(states[0], key);
            }
            else if (states.length > 1) {
                //we have a conflict. recurse upwards until unique names generated.
                this.preProcess(states, upwardLength + 1);
            }
            else {
                throw "programming mistake...";
            }
        }
    }
    /** mostly copied from StateGen.ts */
    getStateFullName(state, seperator = ".") {
        let output = "";
        let cur = state;
        let sep = "";
        while (cur != null) {
            let prepend = "";
            prepend = cur.label.toUpperCase();
            prepend += sep;
            output = prepend + output;
            sep = seperator;
            cur = cur.parent;
        }
        return output;
    }
    getShownName(state) {
        let shownName = state.label;
        if (this.useIdsAsNames) {
            shownName = "State " + state.outputId;
        }
        return shownName;
    }
    /**
     *
     * @param resultArray
     * @param startingState
     */
    addStatesRecursivelyFrom(resultArray, startingState) {
        resultArray.push(startingState);
        for (let kid of startingState.kids) {
            this.addStatesRecursivelyFrom(resultArray, kid);
        }
        return resultArray;
    }
    buildOutputRecursively(startingState) {
        let output = this.buildOutput(startingState);
        let collapsedStates = this.collapsedStates;
        for (let state of collapsedStates) {
            output += "\n\n\n";
            output += this.buildOutputRecursively(state);
        }
        return output;
    }
    /**
     *
     * @param hsm
     */
    buildOutput(startingState) {
        this.collapsedStates = [];
        //starting from    
        let allStates = this.addStatesRecursivelyFrom([], startingState);
        this.preProcess(allStates, 0);
        let output = "@startuml\n";
        output += `
skinparam state {
 FontName<<class_ortho>>    Impact
 BorderColor<<class_ortho>> #AA0000
 BorderColor Black
}

note top of ${this.getUniqueName(startingState)}
Full state path to current diagram being exported:
${this.getStateFullName(startingState)}
end note\n\n`;
        let styles = { text: "" };
        let inner = this.renderSub(startingState, 0);
        output += styles.text;
        output += inner;
        output += "@enduml\n";
        return output;
    }
    /**
     *
     * @param input
     */
    escape(input) {
        return input.replace(/(\r\n|\r|\n)/g, "\\n");
    }
    /**
     *
     * @param state
     */
    divideEventHandlers(state) {
        let result = new DividedEventHandlers();
        for (let eh of state.eventHandlers) {
            if (eh.hasTrigger(Triggers.LANDED_IN)) {
                if (result.landed_in) {
                    throw "can't handle multiple!";
                }
                let newEh = eh.shallowCopy();
                result.landed_in = newEh;
                result.landed_in.setTriggers([]); //don't want "landed_in" trigger showing up
            }
            else {
                result.normal.push(eh);
            }
        }
        return result;
    }
    /**
     *
     * @param eh
     */
    getTriggerGuardActionTextParts(eh) {
        let result = { trigger: "", guard: "", action: "" };
        if (this.showEventGuardActions == false) {
            return result;
        }
        if (eh.hasSomeTriggers()) {
            result.trigger = `${eh.getTriggers().join(" || ")}`;
            if (eh.triggersCount() > 1) {
                result.trigger = `(${result.trigger})`;
            }
        }
        result.guard = (eh.guard) ? `[${eh.guard.guardCode}]` : "";
        result.action = (eh.action) ? ` / ${eh.action.actionCode}` : "";
        result.trigger = this.escape(result.trigger);
        result.guard = this.escape(result.guard);
        result.action = this.escape(result.action);
        return result;
    }
    getTriggerGuardActionText(eh) {
        let et = this.getTriggerGuardActionTextParts(eh);
        let output = `${et.trigger}${et.guard}${et.action}`;
        return output;
    }
    getTransitionText(eh) {
        let output = this.getTriggerGuardActionText(eh);
        if (output) {
            output = " : " + output;
        }
        return output;
    }
    getUniqueName(state) {
        let result;
        if (this.useIdsAsNames) {
            return `State_${state.outputId}`;
        }
        if (this.shortUniqueNames) {
            result = this.getShortUniqueName(state);
        }
        else {
            result = this.getStateFullName(state);
        }
        return result;
    }
    renderSub(state, depthCount) {
        let output = "";
        let thisUniqueName = this.getUniqueName(state);
        let eventHandlers = this.divideEventHandlers(state);
        let shownName = this.getShownName(state);
        let className = "";
        if (state.is_ortho_kid) {
            shownName = "ORTHO : " + shownName;
            className = "<<class_ortho>>";
        }
        let inner = "";
        //determine whether to show expanded state or not.
        //don't hide if this is the top of the current diagram being built.
        if (depthCount > 0 && this.stopAtCollapsed && state.inputState.groupIsCollapsed && state.kids.length > 0) {
            inner += `${thisUniqueName} : //COLLAPSED IN DIAGRAM\n`;
            this.collapsedStates.push(state);
        }
        else {
            //group is not collapsed in view or we should expand anyway
            if (eventHandlers.landed_in && this.showTransitions) {
                inner += `[*] --> ${this.getUniqueName(eventHandlers.landed_in.nextState)} ${this.getTransitionText(eventHandlers.landed_in)}\n`;
            }
            for (let kid of state.kids) {
                inner += this.renderSub(kid, depthCount + 1);
            }
            if (this.showEventGuardActions) {
                for (let eh of eventHandlers.normal) {
                    if (eh.nextState == null) {
                        inner += `${thisUniqueName} ${this.getTransitionText(eh)}\n`;
                    }
                }
            }
        }
        output += `state "${shownName}" as ${thisUniqueName}${className}`;
        output += ` {\n`; //NOTE: a state definition must always have brackets, otherwise plantuml will interpret it as a "weak" definition which leads to bugs as states can be defined by transitions. See `INIT` state of `ROOT.SYSTEM_STARTED.STUBBLE` in previous commit.
        if (inner) {
            inner = StringUtils_1.StringUtils.indent(inner, "  ");
            output += inner.replace(/[ \t]+$/, "");
        }
        output += `}`;
        output += `\n`;
        if (this.showTransitions) {
            for (let eh of eventHandlers.normal) {
                if (eh.nextState) {
                    //state --> next_state : action
                    output += `${thisUniqueName} --> ${this.getUniqueName(eh.nextState)} ${this.getTransitionText(eh)}\n`;
                }
            }
        }
        //output += "\n";
        return output;
    }
}
exports.PlantUmlExporter = PlantUmlExporter;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiUGxhbnRVbWxFeHBvcnRlci5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzIjpbIlBsYW50VW1sRXhwb3J0ZXIudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IjtBQUFBLG1EQUFtRDtBQUNuRDs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7RUFtQ0U7O0FBS0YseURBQXNEO0FBQ3RELHVDQUFzQztBQUV0QztJQUFBO1FBRUUsV0FBTSxHQUFrQixFQUFFLENBQUM7SUFDN0IsQ0FBQztDQUFBO0FBSEQsb0RBR0M7QUFFRDtJQUFBO1FBRUUsb0JBQWUsR0FBRyxJQUFJLENBQUM7UUFDdkIsb0JBQWUsR0FBRyxJQUFJLENBQUM7UUFDdkIsMEJBQXFCLEdBQUcsSUFBSSxDQUFDO1FBRTdCOzs7Ozs7Ozs7V0FTRztRQUNILHdCQUFtQixHQUFHLElBQUksQ0FBQztRQUUzQix1RUFBdUU7UUFDdkUsa0JBQWEsR0FBRyxLQUFLLENBQUM7UUFFdEIscUJBQWdCLEdBQUcsSUFBSSxHQUFHLEVBQWlCLENBQUM7SUFvVDlDLENBQUM7SUFoVEMsa0JBQWtCLENBQUMsS0FBYTtRQUM5QixJQUFJLElBQUksR0FBRyxJQUFJLENBQUMsZ0JBQWdCLENBQUMsR0FBRyxDQUFDLEtBQUssQ0FBQyxDQUFDO1FBRTVDLEVBQUUsQ0FBQSxDQUFDLElBQUksSUFBSSxJQUFJLENBQUMsQ0FBQSxDQUFDO1lBQ2YsTUFBTSwwQ0FBMEMsQ0FBQztRQUNuRCxDQUFDO1FBRUQsTUFBTSxDQUFDLElBQUksQ0FBQztJQUNkLENBQUM7SUFHRDs7OztPQUlHO0lBQ0gsa0JBQWtCLENBQUMsS0FBYSxFQUFFLFNBQWtCO1FBQ2xELElBQUksU0FBUyxHQUFHLEtBQUssQ0FBQyxLQUFLLENBQUM7UUFDNUIsSUFBSSxRQUFRLEdBQUcsS0FBSyxDQUFDO1FBQ3JCLElBQUksS0FBSyxHQUFHLENBQUMsQ0FBQztRQUNkLE9BQU0sS0FBSyxHQUFHLFNBQVMsSUFBSSxRQUFRLENBQUMsTUFBTSxJQUFJ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