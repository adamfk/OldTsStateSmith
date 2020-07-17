"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
const MyRegex_1 = require("./MyRegex");
const XRegExp = require("xregexp");
const r = new MyRegex_1.MyRegex();
class StringUtils {
    //TODOLOW consider detecting indent from first match instead of ALL lines.
    //can always use <R> or <S> set detected indent if first line should 
    //be indented from the rest.
    static deIndent(str, tabWidth = 2) {
        let result;
        var minIndent = Infinity;
        str = str.replace(/\t/g, " ".repeat(tabWidth));
        //find spaces and tabs before first non space character
        str.replace(/^([ ]*)(?=\S)/mg, function (match, indent) {
            minIndent = Math.min(minIndent, indent.length);
            return indent; //TODOLOW convert to exec loop instead
        });
        if (minIndent == 0 || minIndent == Infinity) {
            return str;
        }
        let toRemove = " ".repeat(minIndent);
        result = str.replace(new RegExp("^" + toRemove, "mg"), "");
        return result;
    }
    static indent(str, indentString) {
        let result;
        //TODO escape special characters in replacement string
        result = indentString + str.replace(/(?:\r\n|\r|\n)/g, `$&${indentString}`); //can't match on /^/mg because it will match after \r in \r\n causing output to be \r<indent>\n<indent>
        return result;
    }
    static properIndent(str, indentString) {
        str = StringUtils.removeBlankLinesAtTop(str);
        str = StringUtils.deIndent(str);
        str = StringUtils.indent(str, indentString);
        //remove last line
        str = str.replace(new RegExp(`${r.nl}${r.mhs}$`, "g"), "");
        return str;
    }
    static removeBlankLinesAtTop(str) {
        str = str.replace(/\s*^/ym, "");
        return str;
    }
    static compressBlankLines(str) {
        //bl = ^[ \t]*$(?:\r\n|\r|\n)?
        str = str.replace(/(?:^[ \t]*$(\r\n|\r|\n)?){2,}/gm, "$1");
        return str;
    }
    static rTrim(str) {
        str = str.replace(/\s+$/, "");
        return str;
    }
    static normalizeLineEndingsTo(text, lineEnding) {
        text = text.replace(/\r\n|\r|\n/g, lineEnding);
        return text;
    }
    static removeAllComments(text) {
        text = text.replace(MyRegex_1.MyRegex.buildCommentRegex(), "");
        return text;
    }
    static removeUnattachedComments(text) {
        //remove comments not part of an expansion line
        //rip out any comments that start a line and do not have a non comment line after them
        var lineComments = MyRegex_1.MyRegex.buildPreCommentRegex().source;
        let re = `^\\s*${lineComments}\\s*${r.blankLine}`;
        text = text.replace(new RegExp(re, "mg"), "");
        //now remove trailing comments at end of string
        re = `(\n)*${lineComments}\\s*$`;
        text = text.replace(new RegExp(re, "g"), "$1"); //put back in \n
        text = text.trim();
        return text;
    }
    static removeBlankLinesAtBottom(str) {
        str = str.replace(/(\r\n|\r|\n)\s*$/, "$1");
        return str;
    }
    static removeBlankLines(str) {
        str = StringUtils.removeBlankLinesAtBottom(str);
        str = StringUtils.removeBlankLinesAtTop(str);
        return str;
    }
    static alignStringMarkersSimple(stringMarkers, str) {
        let simpleRegexes = [];
        for (let marker of stringMarkers) {
            simpleRegexes.push(new RegExp(MyRegex_1.MyRegex.escapeMetaChars(marker)));
        }
        str = this.alignRegexesInStringsSimple(simpleRegexes, str);
        return str;
    }
    static alignRegexInStringsSimple(simpleRegexes, str) {
        str = this.alignRegexesInStringsSimple([simpleRegexes], str);
        return str;
    }
    static alignRegexesInStringsSimple(simpleRegexes, str) {
        let pointRegexes = [];
        for (let re of simpleRegexes) {
            let newRe = new RegExp(`(^.*?)(${re.source})(.*$)`, "m"); // /(^.*?)( break;)(.*$)/m
            pointRegexes.push(newRe);
        }
        str = this.alignPointsInStrings(pointRegexes, str);
        return str;
    }
    /**
     *
     * @param pointRegexes Regexes must have only 3 capturing groups: 1) before align point, 2) point to align, 3) after point to align
     * @param str
     */
    static alignPointsInStrings(pointRegexes, str) {
        for (let re of pointRegexes) {
            str = this.alignPointInStrings(re, str);
        }
        return str;
    }
    /**
     *
     * @param pointRegex Regex must have only 3 capturing groups: 1) before align point, 2) point to align, 3) after point to align
     * @param str
     */
    static alignPointInStrings(pointRegex, str) {
        let re = new RegExp(pointRegex, pointRegex.flags.replace("g", "") + "g"); //ensure we have the g flag set
        var maxIndex = 0;
        //capture groups: beforeAlign, pointToAlign, afterAlign
        let match;
        while (match = re.exec(str)) {
            let beforeAlign = match[1];
            let pointToAlign = match[2];
            let afterAlign = match[3];
            maxIndex = Math.max(maxIndex, beforeAlign.length);
        }
        str = str.replace(re, function (match, beforeAlign, pointToAlign, afterAlign) {
            let alignedLine;
            let linePointIndex = beforeAlign.length;
            let padLength = maxIndex - linePointIndex;
            alignedLine = beforeAlign + " ".repeat(padLength) + pointToAlign + afterAlign;
            return alignedLine;
        });
        return str;
    }
    static alignCompressedSwitch(switchInner) {
        let regexes = [
            /(^.*)(:)(.*$)/m,
            /(^.*)( break;)(.*$)/m,
        ];
        let output = StringUtils.alignPointsInStrings(regexes, switchInner);
        return output;
    }
    static removeRLineMarkers(str) {
        str = str.replace(/(?:\r\n|\r|\n).*<R>.*/g, ""); //instructs to remove line
        return str;
    }
    //all characters on a line with <C> are cleared to " "
    static clearCLineMarkers(str) {
        str = str.replace(/^.*<C>.*$/gm, (g0) => " ".repeat(g0.length));
        return str;
    }
    //TODOLOW: replace with better parser.
    static changeParagraphDivToSpan(str) {
        let inParagraph = false;
        str = str.replace(/<(\/)?(div|p)(\s|>)/ig, function (g0, tagEndFs = "", divOrP, delimiter) {
            let result = "";
            let outType = divOrP;
            if (divOrP.toLowerCase() == "p") {
                inParagraph = (tagEndFs != "/");
            }
            else {
                //it's a div
                if (inParagraph) {
                    outType = "span";
                }
            }
            result = `<${tagEndFs}${outType}${delimiter}`;
            return result;
        });
        return str;
    }
    static processMarkers(str) {
        function getIntOrDefault(string, defaultValue) {
            if (string == null) {
                return defaultValue;
            }
            return parseInt(string);
        }
        str = str.trim().replace(/[ \t]*<s>/g, "");
        str = str.replace(/(?:\r\n|\r|\n).*<[rR]>.*/g, ""); //instructs to remove line
        str = str.replace(/([ \t]*(?:\r\n|\r|\n))*[ \t]*<removeBlankLines\/?>([ \t]*(?:\r\n|\r|\n))*/g, "");
        str = str.replace(/(?:[ \t]*(?:\r\n|\r|\n))*[ \t]*<setBlankLines[*](\d+)\/?>(?:[ \t]*(?:\r\n|\r|\n))*/g, function (substring, repeat) {
            return "\r\n".repeat(getIntOrDefault(repeat, 1));
        });
        str = str.replace(/<n(?:ewline)?(?:[*](\d+))?\/?>/g, function (substring, repeat) {
            return "\r\n".repeat(getIntOrDefault(repeat, 1));
        });
        str = str.replace(/<space(?:[*](\d+))?\/?>/g, function (substring, repeat) {
            return " ".repeat(getIntOrDefault(repeat, 1));
        });
        str = str.replace(/<dummy\/?>/g, "");
        return str;
    }
    static makeCssClassName(desiredName) {
        let result = desiredName.replace(/[^-\w]/g, function (group0) {
            return `-0x${group0.charCodeAt(0).toString(16).toUpperCase()}-`;
        });
        return result;
    }
    /**
     * Still allows html.
     *
     * @static
     * @param {string} str
     * @memberof StringUtils
     */
    static escapeUnattachedAmpLtGt(str) {
        str = str.replace(/&(?!(?:\w+|#\d+);)/g, "&amp;");
        let re = `
      (
        <
          /?
          ${r.word}+
          (?:
            ${r.space}+
            [a-z]+(?:[:${r.word}]*)
            (?:
              ${r.space}* = ${r.space}*
              (?:
                ${r.word}+
                |
                "[^"]*"
              )
            )*
          )*
          /?
        >
      )
      |
      (<)
      |
      (>)
    `;
        let result = XRegExp.replace(str, XRegExp(re, "igx"), function (g0, validHtmlTag, lt, gt) {
            let result = "";
            if (validHtmlTag) {
                result = validHtmlTag;
            }
            else if (lt) {
                result = "&lt;";
            }
            else if (gt) {
                result = "&gt;";
            }
            else {
                throw "???";
            }
            ;
            return result;
        });
        //str = str.replace(/<(?![a-z])/ig, "&lt;");
        return result;
    }
    static useAmpLtGtEntities(str) {
        str = this.useAmpEntity(str);
        str = this.useLtGtEntities(str);
        return str;
    }
    static useLtGtEntities(str) {
        str = str.replace(/</g, "&lt;");
        str = str.replace(/>/g, "&gt;");
        return str;
    }
    static useAmpEntity(str) {
        str = str.replace(/&/g, "&amp;");
        return str;
    }
    static makeSafeForHtmlTitle(str) {
        str = this.simpleRemoveHtmlTags(str);
        str = this.escapeForHtmlAttributeValue(str);
        return str;
    }
    /**
     * escapes double quotes and ampersand
     * https://stackoverflow.com/questions/5320177/what-values-can-i-put-in-an-html-attribute-value
     */
    static escapeForHtmlAttributeValue(str) {
        str = str.replace(/"/g, "&quot;");
        str = this.useAmpEntity(str);
        return str;
    }
    static simpleRemoveHtmlTags(str) {
        //consider which characters are allowed in an attributes string
        //https://stackoverflow.com/questions/5002111/javascript-how-to-strip-html-tags-from-string
        let result = str.replace(/<\/?.*?>/g, "");
        return result;
    }
    static getAllIndexes(str, char) {
        if (char.length !== 1) {
            throw "searched for char must be single character";
        }
        let result = [];
        for (let i = 0; i < str.length; i++) {
            if (str[i] === char) {
                result.push(i);
            }
        }
        return result;
    }
    static escapeForCString(str) {
        let result = str;
        result = result.replace('"', '\\"'); // '\\"' is to escape double quotes for generated c
        result = result.replace(/([\x00-\x1F]|[\x80-\xFF])/g, function (fullMatch, capture) {
            let hexDigits = capture.charCodeAt(0).toString(16);
            if (hexDigits.length < 2) {
                hexDigits = '0' + hexDigits;
            }
            return `" "\\x${hexDigits}" "`; //relies on 'eager catenation' to avoid hex escape accidentally grabbing more than it should
        });
        return result;
    }
}
exports.StringUtils = StringUtils;
function demo() {
    let output;
    let input = "";
    input = `

      @file

      @brief     State machine
                Auto generated from file:

     @copyright Copyright (c) 2017 JCA Electronics, Winnipeg, MB. All rights
                reserved.
  `;
    console.log("'" + StringUtils.removeBlankLinesAtTop(input) + "'");
    console.log("'" + StringUtils.deIndent(input) + "'");
    input = `
  [HSM_ID_TO_INDEX(BBH__ROOT_STATE__ID)] =   { .defs = &root_state_tree_def},
  [HSM_ID_TO_INDEX(BBH__NOT_PRESSED__ID)] =   { .defs = &not_pressed_tree_def},
  [HSM_ID_TO_INDEX(BBH__PRESSED__ID)] =   { .defs = &pressed_tree_def},
  [HSM_ID_TO_INDEX(BBH__INITIAL_PRESS__ID)] =   { .defs = &initial_press_tree_def},
  [HSM_ID_TO_INDEX(BBH__HELD__ID)] =   { .defs = &held_tree_def},
  [HSM_ID_TO_INDEX(BBH__HELD_LONG__ID)] =   { .defs = &held_long_tree_def},
  [HSM_ID_TO_INDEX(BBH__HELD__PSEUDO_INIT__ID)] =   { .defs = &held__pseudo_init_tree_def},
  `;
    output = StringUtils.alignPointInStrings(/(^.*\]\s*)(=)(\s*{.*$)/m, input);
    console.log("\n<aligned>\n", output, "\n</aligned>\n");
    ///////////////////////////////////////////////////////////////////////////////////////////
    {
        input = `
    case HSM_EVENT_NULL: str = "NULL"; break;
    case HSM_EVENT_STATE_ENTRY: str = "STATE_ENTRY"; break;
    case HSM_EVENT_STATE_LANDED_IN: str = "STATE_LANDED_IN"; break;
    case HSM_EVENT_STATE_EXIT: str = "STATE_EXIT"; break;
    case HSM_EVENT_TRY_PRE_ENTER_TRANSITIONS: str = "TRY_PRE_ENTER_TRANSITIONS"; break;
    case HSM_EVENT_STATE_DO_WORK: str = "STATE_DO_WORK"; break;
    case HSM_EVENT_ANY: str = "ANY"; break;
    `;
        let regexes = [
            /(^.*)(:)(.*$)/m,
            /(^.*)( break;)(.*$)/m,
        ];
        output = StringUtils.alignPointsInStrings(regexes, input);
        console.log("\n<alignedPoints>\n", output, "\n</alignedPoints>\n");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    {
        input = `
    if (mem->destination_offset < 0) { return MY_MEMCPY_RESULT__ERROR_DST_OFFSET_INVALID; }
    if (mem->destination == NULL) { return MY_MEMCPY_RESULT__ERROR_DST_NULL;           }
    if (mem->source == NULL) { return MY_MEMCPY_RESULT__ERROR_SRC_NULL;           }
    if (max_allowed_store < 0) { return MY_MEMCPY_RESULT__ERROR_MAX_STORE_LENGTH_NEGATIVE; }
    if (max_allowed_read < 0) { return MY_MEMCPY_RESULT__ERROR_MAX_READ_LENGTH_NEGATIVE; }

    `;
        output = StringUtils.alignRegexesInStringsSimple([/[{]/, /[}]/], input);
        console.log("\n<alignRegexInStringsSimple>", output, "</alignRegexInStringsSimple>\n");
    }
    {
        input = `

    blankLine


    blank

    already spaced

    `;
        output = StringUtils.compressBlankLines(input);
    }
    console.log("\n<compressBlankLines>", output, "</compressBlankLines>\n");
}
//demo();
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiU3RyaW5nVXRpbHMuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJTdHJpbmdVdGlscy50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUFBLG1EQUFtRDtBQUNuRCx1Q0FBaUM7QUFFakMsbUNBQW9DO0FBRXBDLE1BQU0sQ0FBQyxHQUFHLElBQUksaUJBQU8sRUFBRSxDQUFDO0FBR3hCO0lBRUUsMEVBQTBFO0lBQzFFLHFFQUFxRTtJQUNyRSw0QkFBNEI7SUFDckIsTUFBTSxDQUFDLFFBQVEsQ0FBQyxHQUFZLEVBQUUsV0FBb0IsQ0FBQztRQUN4RCxJQUFJLE1BQWUsQ0FBQztRQUNwQixJQUFJLFNBQVMsR0FBRyxRQUFRLENBQUM7UUFDekIsR0FBRyxHQUFHLEdBQUcsQ0FBQyxPQUFPLENBQUMsS0FBSyxFQUFFLEdBQUcsQ0FBQyxNQUFNLENBQUMsUUFBUSxDQUFDLENBQUMsQ0FBQztRQUUvQyx1REFBdUQ7UUFDdkQsR0FBRyxDQUFDLE9BQU8sQ0FBQyxpQkFBaUIsRUFBRSxVQUFTLEtBQWMsRUFBRSxNQUFlO1lBQ3JFLFNBQVMsR0FBRyxJQUFJLENBQUMsR0FBRyxDQUFDLFNBQVMsRUFBRSxNQUFNLENBQUMsTUFBTSxDQUFDLENBQUM7WUFDL0MsTUFBTSxDQUFDLE1BQU0sQ0FBQyxDQUFDLHNDQUFzQztRQUN2RCxDQUFDLENBQUMsQ0FBQztRQUVILEVBQUUsQ0FBQSxDQUFDLFNBQVMsSUFBSSxDQUFDLElBQUksU0FBUyxJQUFJLFFBQVEsQ0FBQyxDQUFBLENBQUM7WUFDMUMsTUFBTSxDQUFDLEdBQUcsQ0FBQztRQUNiLENBQUM7UUFFRCxJQUFJLFFBQVEsR0FBRyxHQUFHLENBQUMsTUFBTSxDQUFDLFNBQVMsQ0FBQyxDQUFDO1FBQ3JDLE1BQU0sR0FBRyxHQUFHLENBQUMsT0FBTyxDQUFDLElBQUksTUFBTSxDQUFDLEdBQUcsR0FBRyxRQUFRLEVBQUUsSUFBSSxDQUFDLEVBQUUsRUFBRSxDQUFDLENBQUM7UUFDM0QsTUFBTSxDQUFDLE1BQU0s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