"use strict";
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
/*

*/
Object.defineProperty(exports, "__esModule", { value: true });
const StringUtils_1 = require("../ts-utils/StringUtils");
const fs_1 = require("fs");
class StateTsExporter {
    constructor() {
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
    getStateFullName(state, separator = ".") {
        let output = "";
        let cur = state;
        let sep = "";
        while (cur != null) {
            let prepend = "";
            prepend = cur.label.toUpperCase();
            prepend += sep;
            output = prepend + output;
            sep = separator;
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
    /**
     * {
     *    root:{
     *    },
     *    uniqueNameStates:{
     *    }
     * }
     * @param hsm
     */
    buildOutput(startingState, smName) {
        //let allStates = this.addStatesRecursivelyFrom([], startingState);
        // this.preProcess(allStates, 0); for unique names
        let inner = this.renderSub(startingState);
        inner = StringUtils_1.StringUtils.indent(inner, "  ");
        inner = inner.replace(/[ \t]+$/, "");
        let output = fs_1.readFileSync(__dirname + "/views/doc-content/states/template.ts").toString();
        output += `\nexport const ${smName} = {\n`;
        output += inner;
        output += "};";
        return output;
    }
    /**
     *
     * @param input
     */
    escape(input) {
        return input.replace(/(\r\n|\r|\n)/g, "\\n");
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
    renderSub(state) {
        let output = "";
        let inner = "";
        inner += `getFc : getFullPathCode,\n`;
        inner += `getSc : getShortPathCode,\n`;
        for (let kid of state.kids) {
            inner += this.renderSub(kid);
        }
        if (inner) {
            inner = StringUtils_1.StringUtils.indent(inner, "  ");
            inner = inner.replace(/[ \t]+$/, "");
        }
        output = `${state.label} : {\n`;
        output += inner;
        output += "},\n";
        return output;
    }
}
exports.StateTsExporter = StateTsExporter;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiU3RhdGVUc0V4cG9ydGVyLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiU3RhdGVUc0V4cG9ydGVyLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQSxtREFBbUQ7QUFDbkQ7O0VBRUU7O0FBS0YseURBQXNEO0FBQ3RELDJCQUFrQztBQUlsQztJQUFBO1FBRUU7Ozs7Ozs7OztXQVNHO1FBQ0gsd0JBQW1CLEdBQUcsSUFBSSxDQUFDO1FBRTNCLHVFQUF1RTtRQUN2RSxrQkFBYSxHQUFHLEtBQUssQ0FBQztRQUV0QixxQkFBZ0IsR0FBRyxJQUFJLEdBQUcsRUFBaUIsQ0FBQztJQWtMOUMsQ0FBQztJQWhMQyxrQkFBa0IsQ0FBQyxLQUFhO1FBQzlCLElBQUksSUFBSSxHQUFHLElBQUksQ0FBQyxnQkFBZ0IsQ0FBQyxHQUFHLENBQUMsS0FBSyxDQUFDLENBQUM7UUFFNUMsRUFBRSxDQUFBLENBQUMsSUFBSSxJQUFJLElBQUksQ0FBQyxDQUFBLENBQUM7WUFDZixNQUFNLDBDQUEwQyxDQUFDO1FBQ25ELENBQUM7UUFFRCxNQUFNLENBQUMsSUFBSSxDQUFDO0lBQ2QsQ0FBQztJQUdEOzs7O09BSUc7SUFDSCxrQkFBa0IsQ0FBQyxLQUFhLEVBQUUsU0FBa0I7UUFDbEQsSUFBSSxTQUFTLEdBQUcsS0FBSyxDQUFDLEtBQUssQ0FBQztRQUM1QixJQUFJLFFBQVEsR0FBRyxLQUFLLENBQUM7UUFDckIsSUFBSSxLQUFLLEdBQUcsQ0FBQyxDQUFDO1FBQ2QsT0FBTSxLQUFLLEdBQUcsU0FBUyxJQUFJLFFBQVEsQ0FBQyxNQUFNLElBQUksSUFBSSxFQUFDLENBQUM7WUFDbEQsS0FBSyxFQUFFLENBQUM7WUFDUixRQUFRLEdBQUcsUUFBUSxDQUFDLE1BQU0sQ0FBQztZQUMzQixTQUFTLEdBQUcsUUFBUSxDQUFDLEtBQUssR0FBRyxHQUFHLEdBQUcsU0FBUyxDQUFDLENBQUMsaUVBQWlFO1FBQ2pILENBQUM7UUFFRCxNQUFNLENBQUMsU0FBUyxDQUFDO0lBQ25C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