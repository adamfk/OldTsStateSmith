"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
const MyRegex_1 = require("../ts-utils/MyRegex");
const StringUtils_1 = require("../ts-utils/StringUtils");
const Generator_1 = require("./Generator");
class MacroExpander {
    constructor() {
        this.expansions = [];
        this.HIDE_CHAR_OFFSET = 0x1000;
    }
    addMacroExpansions(text) {
        var expansions = ExpansionParser.parse(text);
        //add each expansion one at a time, trying to expand it's definition
        for (let i = 0; i < expansions.length; i++) {
            let exp = expansions[i];
            exp.expandedReplaceTemplate = this.expandText(exp.replaceTemplate);
            exp.expandedReplaceTemplate = this.expandOriginalNameShorthand(exp);
            exp.buildRegex();
            this.expansions.push(exp);
        }
    }
    /**
     * Allows using the name of the expansion in the expansion replace template as $$.
     * Makes for easier writing/reading and less error prone for copy paste.
     * Example. The below 2 examples are equivalent.
     *   EX1: is_mas_nand_outdated( ) ====> sp_Private_$$(jxc, sm)
     *   EX2: is_mas_nand_outdated( ) ====> sp_Private_is_mas_nand_outdated(jxc, sm)
     */
    expandOriginalNameShorthand(exp) {
        let result;
        result = exp.expandedReplaceTemplate.replace(/[$][$]/g, exp.name);
        if (result != exp.expandedReplaceTemplate) {
            let for_breakpoint = 0; //for testing
        }
        return result;
    }
    expandText(text) {
        let result = text;
        //convert chars inside comments and strings from matching. a real hack :)
        result = this.hideCommentAndStringInnards(text);
        for (let i = 0; i < this.expansions.length; i++) {
            var exp = this.expansions[i];
            result = result.replace(exp.searchRegex, function (match, preMatch, name, p1, p2, p3, p4, p5, postMatch) {
                let result = null;
                result = exp.expandedReplaceTemplate;
                switch (exp.functionParameters.length) {
                    case 0:
                        result = result; //no changes needed
                        break;
                    case 1:
                        //console.log(`param: '${exp.functionParameters[0]}', 'p1:${p1}'`);
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[0]}\\s*}}`, 'g'), p1.trim());
                        break;
                    case 2:
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[0]}\\s*}}`, 'g'), p1.trim());
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[1]}\\s*}}`, 'g'), p2.trim());
                        break;
                    case 3:
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[0]}\\s*}}`, 'g'), p1.trim());
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[1]}\\s*}}`, 'g'), p2.trim());
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[2]}\\s*}}`, 'g'), p3.trim());
                        break;
                    case 4:
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[0]}\\s*}}`, 'g'), p1.trim());
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[1]}\\s*}}`, 'g'), p2.trim());
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[2]}\\s*}}`, 'g'), p3.trim());
                        result = result.replace(new RegExp(`{{\\s*${exp.functionParameters[3]}\\s*}}`, 'g'), p4.trim());
                        break;
                    default: throw "not implemented yet";
                }
                result = preMatch + result;
                return result;
            });
        }
        result = this.unhideCommentAndStringInnards(result);
        return result;
    }
    ripOutComments(text) {
        let result = "";
        let r = new MyRegex_1.MyRegex();
        let re = `${r.fslash}${r.star}(${r.any}*?)${r.star}${r.fslash}|${r.fslash}${r.fslash}(.*)|"([^"]*)"`;
        var tthis = this;
        result = text.replace(new RegExp(re, "g"), function (match, starCommentInner, lineCommentInner, stringInner) {
            let result = "";
            let preResult = "";
            let postResult = "";
            if (starCommentInner) {
                preResult = "/*";
                postResult = "*/";
            }
            else if (lineCommentInner) {
                preResult = "//";
            }
            else if (stringInner) {
                preResult = postResult = '"';
            }
            else {
                throw "Unknown matching...";
            }
            let inner = starCommentInner || lineCommentInner || stringInner;
            let newInner = inner.replace(/./g, function (match) {
                return String.fromCharCode(match.charCodeAt(0) + tthis.HIDE_CHAR_OFFSET);
            });
            result = preResult + newInner + postResult;
            return result;
        });
        return result;
    }
    hideStringCharacters(text) {
        var tthis = this;
        let hidden = text.replace(/[^]/g, function (match) {
            return String.fromCharCode(match.charCodeAt(0) + tthis.HIDE_CHAR_OFFSET);
        });
        return hidden;
    }
    unhideStringCharacters(text) {
        var tthis = this;
        let unhidden = text.replace(/[^]/g, function (match) {
            return String.fromCharCode(match.charCodeAt(0) - tthis.HIDE_CHAR_OFFSET);
        });
        return unhidden;
    }
    hideCommentAndStringInnards(text) {
        let result = "";
        let r = new MyRegex_1.MyRegex();
        let re = `${r.fslash}${r.star}(${r.any}*?)${r.star}${r.fslash}|${r.fslash}${r.fslash}(.*)|"([^"]*)"`;
        var tthis = this;
        result = text.replace(new RegExp(re, "g"), function (match, starCommentInner, lineCommentInner, stringInner) {
            let result = "";
            let preResult = "";
            let postResult = "";
            let inner;
            if (starCommentInner != undefined) {
                preResult = "/*";
                postResult = "*/";
                inner = starCommentInner;
            }
            else if (lineCommentInner != undefined) {
                preResult = "//";
                inner = lineCommentInner;
            }
            else if (stringInner != undefined) {
                preResult = postResult = '"';
                inner = stringInner;
            }
            else {
                console.log({ text: text, re: re, match: match });
                throw "Unknown matching...";
            }
            let newInner = tthis.hideStringCharacters(inner);
            result = preResult + newInner + postResult;
            return result;
        });
        return result;
    }
    unhideCommentAndStringInnards(text) {
        let result = "";
        let r = new MyRegex_1.MyRegex();
        let re = `[\\u${this.HIDE_CHAR_OFFSET.toString(16)}-\\u${(this.HIDE_CHAR_OFFSET + 10000).toString(16)}]`;
        var tthis = this;
        result = text.replace(new RegExp(re, "g"), function (match) {
            let result = String.fromCharCode(match.charCodeAt(0) - tthis.HIDE_CHAR_OFFSET);
            return result;
        });
        return result;
    }
}
exports.MacroExpander = MacroExpander;
class MyInputExpansion {
    constructor() {
        this.functionParameters = [];
    }
    buildRegex() {
        var r = new MyRegex_1.MyRegex();
        //say you have expansions `state ====> MY_STATE` and `add(state) ====> x += {{state}}`. Need to prevent second expansion from becoming `add(state) ====> x += {{MY_STATE}}`.
        let preventMatchingReplacementVar = "(?!\s*}})";
        var preMatch = "(?:^|\\s|[-+;<>,%?:\\(/&|!~*={}\\[$]|::)";
        var postMatch = `${preventMatchingReplacementVar}(?:$|\\s|[-+;<>,%?:\\(\\)/&|!~*={}\\]$.]|::)`;
        var capturedName = `(${this.name})`;
        var re = `(${preMatch})${capturedName}(?=${postMatch})`;
        //
        if (this.isFunction) {
            var r = new MyRegex_1.MyRegex();
            var capturedInner = MyRegex_1.MyRegex.buildMatchedBracesCaptureParamsRegex(2, 4);
            re = `(${preMatch})${capturedName}${capturedInner.source}(?=${postMatch})`;
        }
        //console.log(re);
        //console.log(this);
        this.searchRegex = new RegExp(re, "mg");
    }
}
class ExpansionParser {
    static parse(text) {
        let result = [];
        let r = new MyRegex_1.MyRegex();
        text = text.trim();
        text = StringUtils_1.StringUtils.normalizeLineEndingsTo(text, "\n");
        //remove comments not part of an expansion line
        text = StringUtils_1.StringUtils.removeUnattachedComments(text);
        let re = MyRegex_1.MyRegex.buildExpansionRegex();
        var array;
        var lastIndex = 0;
        while ((array = re.exec(text)) !== null) {
            let match = new MyInputExpansion();
            match.preComment = array[1];
            match.name = array[2];
            var functionParameters = array[3];
            match.replaceTemplate = array[4];
            match.lineComment = array[5];
            result.push(match);
            lastIndex = re.lastIndex;
            if (functionParameters == null) {
                match.isFunction = false;
            }
            else {
                match.isFunction = true;
                functionParameters = functionParameters.trim();
                if (functionParameters) {
                    match.functionParameters = functionParameters.trim().split(/\s*,\s*/);
                }
                else {
                    match.functionParameters = []; //no actual parameters but still a function
                }
            }
            // console.log(`lastIndex: ${re.lastIndex}`);
        }
        //TODOLOW error if expansion doesn't use a passed in parameter
        if (lastIndex !== text.length) {
            // console.log(`lastIndex: ${lastIndex}`);
            // console.log(result);
            // console.log("Failed parsing at index: " + lastIndex + ": '" + text.substr(lastIndex, 30) + "'...");
            throw `Failed parsing at index: ${lastIndex}. Unrecognized 'input' starts with: '${text.substr(lastIndex, 50).replace(/\n/g, "\\n")}'...`;
        }
        return result;
    }
} //////////////////////////////////////////////////////////////////////////////
class StructFieldParser {
    static parse(text) {
        let result = [];
        let r = new MyRegex_1.MyRegex();
        text = text.trim();
        text = StringUtils_1.StringUtils.normalizeLineEndingsTo(text, "\n");
        //remove comments not part of an expansion line
        text = StringUtils_1.StringUtils.removeUnattachedComments(text);
        //captured groups: preComment, type, name, bitfieldSize, arraySize, lineComment
        let re = MyRegex_1.MyRegex.buildStructFieldRegex();
        var array;
        var lastIndex = 0;
        while ((array = re.exec(text)) !== null) {
            let i = 1;
            let match = new Generator_1.StructField();
            match.preComment = array[i++] || "";
            match.type = array[i++] || "";
            match.name = array[i++] || "";
            match.bitFieldSize = array[i++] || "";
            match.arraySize = array[i++] || "";
            match.lineComment = array[i++] || "";
            match.fullTextMatch = array[0];
            result.push(match);
            lastIndex = re.lastIndex;
        }
        if (lastIndex !== text.length) {
            // console.log(`lastIndex: ${lastIndex}`);
            // console.log(result);
            // console.log("Failed parsing at index: " + lastIndex + ": '" + text.substr(lastIndex, 30) + "'...");
            throw `Failed parsing at index: ${lastIndex}. Unrecognized 'input' starts with: '${text.substr(lastIndex, 50).replace(/\n/g, "\\n")}'...`;
        }
        return result;
    }
} //////////////////////////////////////////////////////////////////////////////
exports.StructFieldParser = StructFieldParser;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5wdXQuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJpbnB1dC50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUFBLG1EQUFtRDtBQUNuRCxpREFBMkM7QUFHM0MseURBQW1EO0FBQ25ELDJDQUF1QztBQUV2QztJQUFBO1FBQ0UsZUFBVSxHQUF3QixFQUFFLENBQUM7UUFDckMscUJBQWdCLEdBQUcsTUFBTSxDQUFDO0lBbUw1QixDQUFDO0lBakxRLGtCQUFrQixDQUFDLElBQUk7UUFDNUIsSUFBSSxVQUFVLEdBQXdCLGVBQWUsQ0FBQyxLQUFLLENBQUMsSUFBSSxDQUFDLENBQUM7UUFFbEUsb0VBQW9FO1FBQ3BFLEdBQUcsQ0FBQyxDQUFDLElBQUksQ0FBQyxHQUFHLENBQUMsRUFBRSxDQUFDLEdBQUcsVUFBVSxDQUFDLE1BQU0sRUFBRSxDQUFDLEVBQUUsRUFBRSxDQUFDO1lBQzNDLElBQUksR0FBRyxHQUFHLFVBQVUsQ0FBQyxDQUFDLENBQUMsQ0FBQztZQUN4QixHQUFHLENBQUMsdUJBQXVCLEdBQUcsSUFBSSxDQUFDLFVBQVUsQ0FBQyxHQUFHLENBQUMsZUFBZSxDQUFDLENBQUM7WUFDbkUsR0FBRyxDQUFDLHVCQUF1QixHQUFHLElBQUksQ0FBQywyQkFBMkIsQ0FBQyxHQUFHLENBQUMsQ0FBQztZQUNwRSxHQUFHLENBQUMsVUFBVSxFQUFFLENBQUM7WUFDakIsSUFBSSxDQUFDLFVBQVUsQ0FBQyxJQUFJLENBQUMsR0FBRyxDQUFDLENBQUM7UUFDNUIsQ0FBQztJQUNILENBQUM7SUFFRDs7Ozs7O09BTUc7SUFDSywyQkFBMkIsQ0FBQyxHQUFzQjtRQUN4RCxJQUFJLE1BQWUsQ0FBQztRQUVwQixNQUFNLEdBQUcsR0FBRyxDQUFDLHVCQUF1QixDQUFDLE9BQU8sQ0FBQyxTQUFTLEVBQUUsR0FBRyxDQUFDLElBQUksQ0FBQyxDQUFDO1FBRWxFLEVBQUUsQ0FBQSxDQUFDLE1BQU0sSUFBSSxHQUFHLENBQUMsdUJBQXVCLENBQUMsQ0FBQSxD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