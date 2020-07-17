"use strict";
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
Object.defineProperty(exports, "__esModule", { value: true });
//debug in Chrome!
//node --inspect --debug-brk YedParser.js
//https://blog.hospodarets.com/nodejs-debugging-in-chrome-devtools#debug-in-devtools
//https://medium.com/@paul_irish/debugging-node-js-nightlies-with-chrome-devtools-7c4a1b95ae27#.xd1lmpcdr
//Console will not work unless you change URL to use 127.0.0.1 instead of 'localhost'
// see https://github.com/nodejs/node/issues/9382
//npm install cheerio
//typings install dt~cheerio --save --global
const cheerio = require("cheerio");
const Generator_1 = require("./Generator");
const Generator_2 = require("./Generator");
const Generator_3 = require("./Generator");
const input_1 = require("./input");
const MyRegex_1 = require("../ts-utils/MyRegex");
const path = require("path");
const r = new MyRegex_1.MyRegex();
const attrKeys = {
    SM: "_tagged_as_sm",
    SM_NAME: "_sm_name",
    LABEL: "_label",
    IS_PSEUDO_INITIAL_STATE: "_tagged_as_pseduo_initial",
    ORTHOGONAL_STATE_ORDER: "_orthogonal_state_order",
}; //attribute keys
//something like this: typings install dt~node
const fs = require("fs"); //node filesystem
const Compiler_1 = require("./Compiler");
// export class StateElement  {
//
//   doSomething(){
//     let c : CheerioElement;
//     c.
//   }
//
// }
class YedParser {
    run(diagramSourceFilePath) {
        this.diagramSourceFilePath = path.resolve(diagramSourceFilePath);
        let fileText = fs.readFileSync(diagramSourceFilePath).toString();
        let parsedStateMachines = [];
        let yedParser = this;
        fileText = this.modifyXml(fileText);
        this.$ = cheerio.load(fileText, { xmlMode: true });
        let $ = this.$;
        //find id for edge descriptions
        this.edgeDescriptionKey = $("key[attr\\.name=description][for=edge]")[0];
        this.edgeDescriptionKeyId = this.edgeDescriptionKey.attribs["id"];
        //find id for node descriptions
        this.nodeDescriptionKey = $("key[attr\\.name=description][for=node]")[0];
        this.nodeDescriptionKeyId = this.nodeDescriptionKey.attribs["id"];
        //find id for node labels and graphics (folder open/closed)
        this.nodeGraphicsKey = $("key[yfiles\\.type=nodegraphics][for=node]")[0];
        this.nodeGraphicsKeyId = this.nodeGraphicsKey.attribs["id"];
        //find id for graph description
        this.graphDescriptionKey = $("key[attr\\.name=Description][for=graph]")[0];
        this.graphDescriptionKeyId = this.graphDescriptionKey.attribs["id"];
        let b = $(`data[key=${this.graphDescriptionKeyId}]`).eq(0);
        this.graphDescription = $(`data[key=${this.graphDescriptionKey.attribs["id"]}]`).text();
        this.allNodes = $("graph node").toArray();
        //expose all node labels as attributes
        for (let s of this.allNodes) {
            s.attribs[attrKeys.LABEL] = this.getNodeLabel(s);
            //console.log( `NODE LABEL starts with : '${s.attribs[attrKeys.LABEL]}'`);
        }
        //find all statemachine roots
        this.statemachineRoots = $("graph node").filter(function (index, element) {
            let label = yedParser.getNodeLabel(element);
            //console.log("LABEL: " + label);
            let re = /^\s*[$]STATEMACHINE\s*:\s*(\w+)\s*(?:$|\r|\n)/i;
            let match = re.exec(label);
            if (match) {
                //console.log("MATCH!: " + match);
                let statemachineName = match[1];
                element.attribs[attrKeys.SM_NAME] = statemachineName;
            }
            return match !== null;
        }).toArray(); //http://api.jquery.com/filter/
        for (let s of this.statemachineRoots) {
            s.attribs[`${attrKeys.SM}`] = "yes"; //tag as a statemachine root
            //console.log("STATEMACHINE NAME: " + s.attribs[attrKeys.SM_NAME]);
        }
        this.preValidate();
        //console.log(this.statemachineRoots);
        //build HSM
        for (let s of this.statemachineRoots) {
            let result = this.generateInputHsm(s);
            //console.log("\n\n\n\n HSM:", this.getNodeLabel(s), "\n", result);
            parsedStateMachines.push(result);
        }
        this.postValidate(parsedStateMachines);
        console.log("End of parsing");
        return parsedStateMachines;
    }
    postValidateState(state) {
        let $ = this.$;
        for (let eh of state.eventHandlers) {
            //don't allow event handlers that do nothing
            if (eh.nextInputStateId == null && eh.action == null) {
                console.log("Error details:");
                console.log(state, eh);
                throw "Event handler for state has no action";
            }
        }
    }
    postValidateInputHsm(sm) {
        let $ = this.$;
        for (let state of sm.states) {
            this.postValidateState(state);
        }
    }
    postValidate(parsedStateMachines) {
        for (let sm of parsedStateMachines) {
            this.postValidateInputHsm(sm);
        }
    }
    preValidate() {
        let $ = this.$;
        //fail on nested statemachines
        for (let s of this.statemachineRoots) {
            let outerParent = $(s).parent().closest(`[${attrKeys.SM}]`).get(0);
            if (outerParent) {
                console.log("Nested statemachine detected! Not yet supported.");
                console.log("Inner Statemachine label: ", this.getNodeLabel(s));
                console.log("Outer Statemachine label: ", this.getNodeLabel(outerParent));
                throw "Nested statemachine detected! Not yet supported.";
            }
        }
        //TODO do not allow edges to or from GEN directive nodes
    }
    getAllDescendentNodes(parentNode) {
        let $ = this.$;
        let nodes = $(parentNode).find("node").toArray();
        return nodes;
    }
    findAllStateNodes(parentNode) {
        let $ = this.$;
        let all = this.getAllDescendentNodes(parentNode);
        let special = this.findSpecialNodes(parentNode);
        let result = $(all).not($(special)).toArray();
        return result;
    }
    findSpecialNodes(parentNode) {
        let $ = this.$;
        let nodes = $(parentNode).find(`[${attrKeys.LABEL}^="$"]`).toArray();
        //console.log("SPECIIAL NODES: ", nodes);
        return nodes;
    }
    handleSpecialDirectives(hsm, rootNode) {
        //find all directive strings
        let directiveString = "";
        let directiveNodes = this.findSpecialNodes(rootNode);
        for (let d of directiveNodes) {
            let label = this.getNodeLabel(d);
            //console.log("SPECIAL NODE LABEL: ", label);
            let directiveName = label.match(/^\s*[$](\w+)\b/)[1];
            switch (directiveName.toUpperCase()) {
                case "GEN":
                    //console.log("GENNNNNNNNN");
                    this.parseGenDirectiveNode(hsm, d);
                    break;
                case "NOTES":
                    break;
                case "PARENT_ALIAS":
                    {
                        let parentNode = this.findParentNode(d);
                        //remove PARENT_ALIAS part and then copy the rest over to the parent's label
                        let restOfLabel = label.replace(/^\s*[$]PARENT_ALIAS\b\s*/i, "");
                        let parentLabel = this.getNodeLabel(parentNode);
                        let newLabel = parentLabel + "\n" + restOfLabel;
                        this.setNodeLabel(parentNode, newLabel);
                    }
                    break;
                case "GEN_INCLUDE":
                    {
                        //syntax: $GEN_INCLUDE : "<filename>"
                        //get file name to try and import
                        let match = label.match(/^\s*[$]GEN_INCLUDE\s*:\s*"(.+)"\s*$/i);
                        if (!match) {
                            console.log(label);
                            throw new Error(`Invalid syntax for GEN_INCLUDE. Must be: $GEN_INCLUDE:"<relative_file_path>"`);
                        }
                        let sourceDirectory = path.dirname(this.diagramSourceFilePath);
                        let filename = match[1];
                        let filePath = sourceDirectory + path.sep + filename;
                        try {
                            let text = fs.readFileSync(filePath).toString();
                            this.parseGenDirectiveText(hsm, text);
                        }
                        catch (error) {
                            console.log("Tried reading path: " + filePath);
                            throw new Error(`Failed reading file '${filename}' for for GEN_INCLUDE `);
                        }
                    }
                    break;
                case "INITIAL_STATE":
                    d.attribs[attrKeys.IS_PSEUDO_INITIAL_STATE] = "yes";
                    d.attribs[attrKeys.LABEL] = "PSEUDO_INIT";
                    break;
                case "ORTHO":
                    {
                        let order = d.attribs[attrKeys.LABEL].match(/^\s*[$]ORTHO\s*(-?\d+)?\s*:\s*/i)[1] || 1000000; //TODOLOW document 1000000 as default ordering number
                        //rip off "ORTHO digit : " from label
                        d.attribs[attrKeys.LABEL] = d.attribs[attrKeys.LABEL].replace(/^\s*[$]ORTHO\s*(-?\d+)?\s*:\s*/i, "");
                        d.attribs[attrKeys.ORTHOGONAL_STATE_ORDER] = order.toString();
                    }
                    break;
                case "STATEMACHINE":
                    //nothing needed to be done
                    break;
                default:
                    throw `Unknown directive name : '${directiveName}'`;
            }
        }
    }
    parseGenDirectiveNodeSectionShorthand(hsm, sectionName, sectionContents) {
        //sectionContents = sectionContents.replace(/^[ \t]*(?:\r\n|\n|\r)/g, "");//rip out empty leading lines
        sectionContents += "\n";
        try {
            this.addTextToHsmField(hsm, sectionName, sectionContents);
        }
        catch (e) {
            let msg = `Invalid section name: '${sectionName}'`;
            console.log("\n\n" + msg);
            console.log("<failure_details>");
            console.log(sectionName, sectionContents);
            console.log("Original exception", e);
            console.log("</failure_details>\n\n");
            throw msg;
        }
    }
    addTextToHsmField(hsm, fieldName, contents) {
        switch (fieldName) {
            case "input_values":
                hsm.inputValues += contents;
                break;
            case "output_values":
                hsm.outputValues += contents;
                break;
            case "output_events":
                hsm.outputEvents += contents;
                break;
            case "c_functions":
                hsm.cFunctions += contents;
                break;
            case "c_functions_no_exp":
                hsm.cFunctionsNoExp += contents;
                break;
            case "c_prototypes":
                hsm.cPrototypes += contents;
                break;
            case "c_prototypes_no_exp":
                hsm.cPrototypesNoExp += contents;
                break;
            case "expansions":
                hsm.expansionDefinitions += contents;
                break;
            case "vars":
                hsm.varsStructInnerText += contents;
                break;
            case "execute_after_dispatch":
                hsm.executeAfterCode += contents;
                break;
            case "execute_before_dispatch":
                hsm.executeBeforeCode += contents;
                break;
            case "imports":
                hsm.imports += contents;
                break;
            case "h_file_top":
                hsm.h_file_top += contents;
                break;
            case "c_file_top":
                hsm.c_file_top += contents;
                break;
            default:
                throw "Unknown InputHsm field: '" + fieldName + "'";
        }
    }
    parseGenDirectiveText(hsm, directiveContents) {
        directiveContents = directiveContents.trim();
        let re;
        let match;
        //hide comment and string innards
        let m = new input_1.MacroExpander();
        let s = m.hideCommentAndStringInnards(directiveContents);
        {
            let field = `^${r.mhs}(\\w+)${r.mhs}=${r.mhs}"(.*)"${r.mhs}$`;
            let ignored = `((?:\\s+|${MyRegex_1.MyRegex.buildCommentRegex().source})+)`;
            let section = /\s*^(\w+)\s*(?:\r\n|\n|\r)[ \t]*====+[ \t]*$([^]*?)^[ \t]*\/====+[ \t]*$/.source;
            let invalid = /([^]{1,30})/.source;
            let reStr = `${ignored}|${section}|${field}|${invalid}`;
            re = new RegExp(reStr, "mgy");
        }
        let previousIgnored = "";
        while (match = re.exec(s)) {
            let ignored = match[1];
            let sectionName = match[2];
            let sectionContents = match[3];
            let field = match[4];
            let fieldContents = match[5];
            let unknownStuff = match[6];
            if (ignored != undefined) {
                previousIgnored += ignored;
                continue; //skip ignored stuff
            }
            if (unknownStuff != undefined) {
                console.log("Error details:");
                console.log(re, match);
                throw "Invalid gen directive section. Format should be <section_name>\\n======\\nstuff\\n/======\\n";
            }
            //TODOLOW ensure field and section not filled out
            if (field) {
                fieldContents = m.unhideCommentAndStringInnards(fieldContents);
                //is a single field directive
                field = field.trim();
                switch (field) {
                    case "prefix":
                        hsm.prefix = fieldContents;
                        break;
                    case "output_filename":
                        hsm.output_filename = fieldContents;
                        break;
                    default:
                        console.log("Error details:");
                        console.log(re, match);
                        throw "Unknown gen directive field";
                }
            }
            else {
                //is a directive section
                sectionContents = m.unhideCommentAndStringInnards(sectionContents);
                //TODOLOW remove the pre comments detection... probably no use.
                let comments = previousIgnored.trim();
                if (comments) {
                    comments = m.unhideCommentAndStringInnards(comments) + "\n";
                }
                let sectionContentsAndPreComments = comments + sectionContents;
                try {
                    this.addTextToHsmField(hsm, sectionName, sectionContents);
                }
                catch (e) {
                    let msg = `Invalid section name: '${sectionName}'`;
                    console.log("\n\n" + msg);
                    console.log("<failure_details>");
                    console.log(re, match);
                    console.log("Original exception", e);
                    console.log("</failure_details>\n\n");
                    throw msg;
                }
            }
            previousIgnored = ""; //clear it
        }
    }
    parseGenDirectiveNode(hsm, node) {
        //"vars", "expansions", "execute_after_dispatch"
        let string;
        string = node.attribs[attrKeys.LABEL];
        let re = /^\s*[$](\w+)\b(?:[ \t]*:[ \t]*(\w+)[ \t]+section\b[ \t]*(?:\r\n|\n|\r|$))?/;
        let match = re.exec(string);
        let directiveName = match[1];
        let sectionName = match[2];
        let directiveContents = string.substr(match.index + match[0].length);
        if (sectionName != null) {
            this.parseGenDirectiveNodeSectionShorthand(hsm, sectionName, directiveContents);
            return;
        }
        else {
            this.parseGenDirectiveText(hsm, directiveContents);
        }
    }
    preprocessEventHandlerText(text) {
        let followingGuards = MyRegex_1.MyRegex.buildMatchedSquareBracketsRegex(5).source;
        let mainRe = /\bafterMs\s*\(\s*([^)]*)\s*\)/g.source;
        let reStr = `${mainRe}\\s*(${followingGuards})?`;
        let re = new RegExp(reStr, "g");
        //convert afterMs(x) to become another guard expression
        text = text.replace(re, function (match, afterMsParameter, optionalFollowingGuards) {
            let result = `[ (time_in_state >= ${afterMsParameter}) `;
            if (optionalFollowingGuards != null) {
                optionalFollowingGuards = optionalFollowingGuards.trim().replace(/^\[|\]$/g, ""); //remove outer most square brackets
                result += "&& (" + optionalFollowingGuards + ")]";
            }
            else {
                result += "]";
            }
            return result;
        });
        return text;
    }
    parseEventHandlers(text) {
        let result = [];
        let m = new input_1.MacroExpander();
        text = m.hideCommentAndStringInnards(text).trim();
        text = this.preprocessEventHandlerText(text);
        let re = ParseRegexes.getEventHandlerRegex("y"); //get it with a sticky flag
        let match;
        while (match = re.exec(text)) {
            //captured groups: event ordering, simple event trigger, multi eventTrigger, guard outer, action simple, action multiline, invalid
            let i = 1;
            let eventOrder = match[i++];
            let eventTrigger = match[i++];
            let multiEventTrigger = match[i++];
            let guardOuter = match[i++];
            let actionSimple = match[i++];
            let actionMulti = match[i++];
            let invalid = match[i++];
            if (match[0] == "") {
                break;
            }
            if (invalid) {
                console.log("Error details:");
                console.log(re, match);
                throw "Invalid event handler syntax";
            }
            function process(str) {
                str = str || "";
                let result = m.unhideCommentAndStringInnards(str.trim()).trim();
                return result;
            }
            let ieh = new Generator_2.InputEventHandler();
            let triggers = process(eventTrigger || multiEventTrigger).trim();
            if (triggers.length == 0) {
                ieh.triggers = [];
            }
            else {
                ieh.triggers = triggers.split(/\s*[|][|]\s*/);
            }
            ieh.guard = process(guardOuter).replace(/^\s*\[|\]\s*$/g, "").trim() || null;
            ieh.action = process(actionSimple || actionMulti) || null;
            ieh.order = parseInt(eventOrder) || Generator_1.DEFAULT_TRANSITION_ORDER_NUMBER;
            result.push(ieh);
        }
        return result;
    }
    escapeCss(string) {
        let result = string.replace(/(?=[.:])/g, "\\");
        return result;
    }
    generateRootState(hsm, stateNode) {
        let $ = this.$;
        let state = new Generator_3.InputState();
        let stateLabelEventHandlerText;
        state.id = this.getNodeId(stateNode);
        state.label = Compiler_1.ROOT_STATE_LABEL;
        //make sure it is a valid statemachine name
        let label = this.getNodeLabel(stateNode);
        state.parentId = null; //
        let match = label.match(/^\s*[$]STATEMACHINE\s*:\s*.*$/);
        if (!match) {
            throw `Root node didn't have matching label. Label: '${label}'`;
        }
        stateLabelEventHandlerText = label.substr(match[0].length);
        //add internal event handlers (stuff like entry/exit stuff in label definition)
        let newHandlers = this.parseEventHandlers(stateLabelEventHandlerText);
        state.eventHandlers = newHandlers.concat(state.eventHandlers); //make state label handlers come first
        //fail if root state has any edges
        if ($(`edge[source=${this.escapeCss(state.id)}]`).length > 0) {
            throw "You cannot have any edges from Root Node";
        }
        if ($(`edge[target=${this.escapeCss(state.id)}]`).length > 0) {
            throw "You cannot have any edges to Root Node";
        }
        return state;
    }
    generateState(hsm, stateNode) {
        let $ = this.$;
        let state = new Generator_3.InputState();
        let stateLabelEventHandlerText;
        let label = this.getNodeLabel(stateNode);
        state.id = this.getNodeId(stateNode);
        //make sure it is a valid statemachine name
        let match = label.match(/^\s*(\w+)\s*(?:\r\n|\r|\n|$)/);
        if (match == null) {
            throw `State node (id:'${stateNode.attribs["id"]}') had invalid label. Requires proper name. Label was: ${label}'`;
        }
        let stateName = match[1];
        let parentNode = this.findParentNode(stateNode);
        if (parentNode == null) {
            throw "couldn't find parent node!";
        }
        ;
        state.parentId = this.getNodeId(parentNode);
        if (state.parentId == null) {
            throw "couldn't find parent node ID!";
        }
        ;
        state.label = stateName;
        stateLabelEventHandlerText = label.substr(match[0].length);
        //add internal event handlers (stuff like entry/exit stuff in label definition)
        let newHandlers = this.parseEventHandlers(stateLabelEventHandlerText);
        state.eventHandlers = state.eventHandlers.concat(newHandlers);
        //now find all exiting edges and add their event handlers to the state
        let edges = $(`edge[source=${this.escapeCss(state.id)}]`).toArray();
        //determine if initial state
        if (stateNode.attribs[attrKeys.IS_PSEUDO_INITIAL_STATE]) {
            state.isInitialState = true;
        }
        for (let edge of edges) {
            let edgeLabel = $(edge).find("y\\:EdgeLabel").eq(0).text().trim();
            //hack for edges with no trigger or guard
            if (edgeLabel.trim() == "") {
                if (!state.isInitialState) {
                    console.log("WARNING: accidental? edge with no EVENT[GUARD] found from " + stateName);
                }
                edgeLabel = "[true]"; //an always condition. Currently needed so that edge will be added.
            }
            let targetId = edge.attribs["target"].trim();
            let eventHandlers = this.parseEventHandlers(edgeLabel);
            for (let handler of eventHandlers) {
                handler.nextInputStateId = targetId;
                state.eventHandlers.push(handler);
            }
        }
        //determine if ortho state
        let ortho_order = stateNode.attribs[attrKeys.ORTHOGONAL_STATE_ORDER];
        state.orthogonal_order = (ortho_order != null) ? parseFloat(ortho_order) : null;
        //determine if group open or closed
        state.groupIsCollapsed = this.getNodeIsCollapsed(stateNode);
        return state;
    }
    getNodeId(node) {
        return node.attribs["id"];
    }
    findParentNode(node) {
        let parentNode = this.$(node).parent().closest("node").get(0);
        return parentNode;
    }
    findInputStateByXmlNode(hsm, element) {
        let state = this.findInputStateById(hsm, this.getNodeId(element));
        return state;
    }
    findInputStateById(hsm, id) {
        for (let state of hsm.states) {
            if (state.id == id) {
                return state;
            }
        }
        return null;
    }
    generateAllStates(hsm, rootNode) {
        let stateNodes = this.findAllStateNodes(rootNode);
        let state;
        state = this.generateRootState(hsm, rootNode);
        hsm.states.push(state);
        for (let s of stateNodes) {
            if (s !== rootNode) {
                state = this.generateState(hsm, s);
                hsm.states.push(state);
            }
        }
    }
    generateInputHsm(rootNode) {
        let hsm = new Generator_1.InputHsm();
        hsm.name = rootNode.attribs[attrKeys.SM_NAME];
        this.handleSpecialDirectives(hsm, rootNode);
        this.generateAllStates(hsm, rootNode);
        return hsm;
    }
    /**
     * Must be run after labels stored in attributes
     * @param {CheerioElement} node
     * @param {string} newlabel
     */
    setNodeLabel(node, newlabel) {
        node.attribs[attrKeys.LABEL] = newlabel;
    }
    getNodeIsCollapsed(node) {
        let isCollapsed;
        let nodegraphics = this.$(node).children(`data[key=${this.escapeCss(this.nodeGraphicsKeyId)}]`);
        let realizers = nodegraphics.find("y\\:Realizers").eq(0);
        let groupNodeIndex = parseInt(realizers.attr("active"));
        let activeGroupNode = realizers.children("y\\:GroupNode").eq(groupNodeIndex);
        isCollapsed = activeGroupNode.children("y\\:State[closed=true]").length > 0;
        return isCollapsed;
    }
    getNodeLabel(node) {
        var label;
        label = node.attribs[attrKeys.LABEL];
        if (label === undefined) {
            let nodegraphics = this.$(node).children(`data[key=${this.escapeCss(this.nodeGraphicsKeyId)}]`);
            let realizers = nodegraphics.find("y\\:Realizers").eq(0);
            let nodeLabel;
            //more accurately find group node
            if (realizers.length == 0) {
                //not a group node
                nodeLabel = nodegraphics.find("y\\:NodeLabel");
            }
            else {
                //label is in a group node which has individual labels for closed and open states. Find the open one.
                //find `<y:State closed="false"`, then parent, then `<y:NodeLabel`
                nodeLabel = realizers.find(" > y\\:GroupNode > y\\:State[closed=false]").parent().children("y\\:NodeLabel");
            }
            label = nodeLabel.eq(0).text().trim();
        }
        if (label.match(/^\s*<html>/i)) {
            label = removeHtmlTags(label).trim();
        }
        return label;
    }
    getNodeDescription(node) {
        var descriptionNode = this.$(node).find(`data[key=${this.nodeDescriptionKeyId}]`).eq(0).text().trim();
        return descriptionNode;
    }
    findNodesWithMatchingLabels(parent, regex, limitToImmediateChildren = false) {
        let result = [];
        let $ = this.$;
        let nodes;
        if (!parent) {
            parent = this.allNodes[0];
        }
        if (limitToImmediateChildren) {
            nodes = $(parent).children().toArray();
        }
        else {
            nodes = this.getAllDescendentNodes(parent);
        }
        for (let node of nodes) {
            let label = this.getNodeLabel(node);
            label = removeHtmlTags(label);
            if (label.match(regex) !== null) {
                result.push(node);
            }
        }
        return result;
    }
    findStatemachineNodes() {
        let result = [];
        result = this.findNodesWithMatchingLabels(null, /^\s*[$]STATEMACHINE\s*:\s*\w/i);
        return result;
    }
    modifyXml(xmlText) {
        xmlText = xmlText.replace(/<!\[CDATA\[([^]*?)\]\]>(?=\s*<)/ig, "$1"); //bug in cheerio strips CDATA http://stackoverflow.com/questions/15472213/nodejs-using-cheerio-parsing-xml-returns-empty-cdata
        return xmlText;
    }
}
exports.YedParser = YedParser;
class ParseRegexes {
    static getEventHandlerRegex(additionalReFlags = "") {
        if (ParseRegexes.eventHandlerRegex == null) {
            ParseRegexes.eventHandlerRegex = ParseRegexes._buildEventHandlerRegex();
        }
        let result = new RegExp(ParseRegexes.eventHandlerRegex, ParseRegexes.eventHandlerRegex.flags + additionalReFlags);
        return result;
    }
    static _buildEventHandlerRegex() {
        /*
         may have examples like
    
         new syntax for ordering event handlers
           1. EVENT [my_condition] / action
    
         or
    
           enter / output_event( PRESSED )
           exit / output_event( RELEASED )
    
         or
            enter / action1(); action2();
    
         or
            enter / action1();
            action2();
    
         or the below which is parsed as "X_PRESS [mm_preview_timedout]"
           X_PRESS
           [mm_preview_timedout]
    
         or the below which is still parsed as "X_PRESS [mm_preview_timedout]", but seems sketchy. to review.
           X_PRESS
    
           [mm_preview_timedout]
    
         or the below which is parsed as two separate conditions "X_PRESS" "[mm_preview_timedout]"
           X_PRESS / {}
           [mm_preview_timedout] / { }
    
         or
           (EVENT1 || EVENT2)
    
    
        or
           enter / action1(); myvar =
           number / x
           exit / grumble();
    
         or
            enter / { action1(); myvar =
            number / x }
            exit / grumble();
    
        Event trigger
          Required
          format: must be a simple word with no spaces like \w+
    
        Guard condition
          optional
          may be on another line and span lines
          format: anything inside matching square brackets
    
        Actions
          optional
          may be on another line and span lines if in curly braces.
          simple format: anything on rest of line
          multiline format: '{' then anything until matching '}'
        */
        //captured groups: event trigger, multi event trigger, guard outer, action simple, action multiline, invalid
        //TODO HIGH UNIT TEST THIS! This is one of the most important places.
        let orderRe = /(?:(\d+)\s*[.]\s*)/.source; //ordering for transitions
        let eventTriggerRe = `(\\w+|ANY[+])`;
        let multiEventTriggerRe = /(?:\(\s*(\w+(?:\s*[|][|]\s*\w+)*)\s*\))/.source; //(EVENT1 || EVENT2)
        let guardRe = `(?:(${MyRegex_1.MyRegex.buildMatchedSquareBracketsRegex(4).source}))`;
        let actionSimpleRe = `([^{\\s].+)(?:${r.nl}|$)`; //can start with anything but { or white space.
        let actionMulitLineRe = `(${MyRegex_1.MyRegex.buildMatchedCharRecursiveRegex('{', '}', 4).source})${r.mhs}(?:${r.nl}|$)`;
        let action = `(?:${actionSimpleRe}|${actionMulitLineRe})`;
        let ender = `(?:${r.nl}|$)`;
        let invalid = "([^]{1,30})";
        let reStr = `\\s*^\\s*${orderRe}?(?:${eventTriggerRe}|${multiEventTriggerRe})?\\s*${guardRe}?(?:\\s*/\\s*${action})?\s*${ender}|${invalid}`;
        return new RegExp(reStr, 'mg'); //TODO rewrite with xregexp so that we can use named capturing groups.
    }
}
//TODO respect strings. hide?
function removeHtmlTags(text) {
    text = text.replace(/<\/?[^>]+?>/ig, ""); //rip out html tags
    //text = text.replace(/<\/?[a-z]\w*?>/ig, "");  //rip out html tags
    return text;
}
// console.log(ParseRegexes.getEventHandlerRegex());
// let y = new YedParser();
// let fileText = fs.readFileSync(`buttons.graphml`).toString();
// y.run(fileText);
// console.log("END....");
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiWWVkUGFyc2VyLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiWWVkUGFyc2VyLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQSxtREFBbUQ7O0FBRW5ELGtCQUFrQjtBQUNsQix5Q0FBeUM7QUFDekMsb0ZBQW9GO0FBQ3BGLHlHQUF5RztBQUN6RyxxRkFBcUY7QUFDckYsaURBQWlEO0FBR2pELHFCQUFxQjtBQUNyQiw0Q0FBNEM7QUFDNUMsbUNBQW9DO0FBRXBDLDJDQUFxRTtBQUNyRSwyQ0FBNkM7QUFDN0MsMkNBQXNDO0FBQ3RDLG1DQUFxQztBQUNyQyxpREFBMkM7QUFDM0MsNkJBQThCO0FBRTlCLE1BQU0sQ0FBQyxHQUFHLElBQUksaUJBQU8sRUFBRSxDQUFDO0FBR3hCLE1BQU0sUUFBUSxHQUFHO0lBQ2YsRUFBRSxFQUFDLGVBQWU7SUFDbEIsT0FBTyxFQUFFLFVBQVU7SUFDbkIsS0FBSyxFQUFFLFFBQVE7SUFDZix1QkFBdUIsRUFBRywyQkFBMkI7SUFDckQsc0JBQXNCLEVBQUcseUJBQXlCO0NBQ25ELENBQUMsQ0FBRSxnQkFBZ0I7QUFFcEIsOENBQThDO0FBQzlDLHlCQUEwQixDQUFDLGlCQUFpQjtBQUM1Qyx5Q0FBOEM7QUFHOUMsK0JBQStCO0FBQy9CLEVBQUU7QUFDRixtQkFBbUI7QUFDbkIsOEJBQThCO0FBQzlCLFNBQVM7QUFDVCxNQUFNO0FBQ04sRUFBRTtBQUNGLElBQUk7QUFHSjtJQWVTLEdBQUcsQ0FBQyxxQkFBcUI7UUFDOUIsSUFBSSxDQUFDLHFCQUFxQixHQUFHLElBQUksQ0FBQyxPQUFPLENBQUMscUJBQXFCLENBQUMsQ0FBQztRQUNqRSxJQUFJLFFBQVEsR0FBRyxFQUFFLENBQUMsWUFBWSxDQUFDLHFCQUFxQixDQUFDLENBQUMsUUFBUSxFQUFFLENBQUE7UUFFaEUsSUFBSSxtQkFBbUIsR0FBZ0IsRUFBRSxDQUFDO1FBQzFDLElBQUksU0FBUyxHQUFHLElBQUksQ0FBQztRQUNyQixRQUFRLEdBQUcsSUFBSSxD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