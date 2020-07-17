"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
class MyRegex {
    constructor() {
        this.bs = "\\";
        this.fslash = `/`;
        this.star = "[*]";
        this.any = "[^]";
        this.nl = "(?:\\r\\n|\\r|\\n)"; //newline
        this.hspace = "[ \\t]"; //horizontal space
        this.hs = "[ \\t]"; //horizontal space
        this.mhs = "[ \\t]*"; //maybe horizontal space
        this.ms = "\\s*"; //maybe space
        this.blankLine = `(?:^${this.mhs}$\\n)`;
        this.varName = `(?:[_a-zA-Z]\\w*)`;
        this.openP = "\\(";
        this.closeP = "\\)";
        this.openB = "\\[";
        this.closeB = "\\]";
        /** opening curly brace */
        this.openCB = "\\{";
        /** closing curly brace */
        this.closeCB = "\\}";
        this.word = "\\w";
        this.space = "\\s";
        this.d = "\\d";
    }
    static getNlRegex(flags = "g") {
        return new RegExp(new MyRegex().nl, flags);
    }
    static escapeMetaChars(text) {
        let result;
        let reMetaChars = /[\[\\^$.|?*+(){}]/g;
        result = text.replace(reMetaChars, '\\$&');
        return result;
    }
    static buildMatchedCharRecursiveRegex(openingChar, closingChar, depth) {
        var r = new MyRegex();
        if (openingChar.length !== 1) {
            throw "Invalid opening char: " + openingChar;
        }
        ;
        if (closingChar.length !== 1) {
            throw "Invalid closing char: " + closingChar;
        }
        ;
        let escapedOpeningChar = this.escapeMetaChars(openingChar);
        let escapedClosingChar = this.escapeMetaChars(closingChar);
        let matchedBraces = "";
        let matchedBracesWithPipe = "";
        let stuff = "";
        let anythingButOpeningClosing = `[^${escapedOpeningChar}${escapedClosingChar}]`;
        for (let i = 0; i < depth; i++) {
            stuff = `(?:${matchedBracesWithPipe}|${anythingButOpeningClosing})*?`;
            matchedBraces = `${escapedOpeningChar}${stuff}${escapedClosingChar}`;
            matchedBracesWithPipe = matchedBraces + "|";
            //console.log(matchedBraces);
        }
        let re = new RegExp(matchedBraces, "g");
        return re;
    }
    static buildMatchedSquareBracketsRegex(depth) {
        var r = new MyRegex();
        let matchedBraces = "";
        let matchedBracesWithPipe = "";
        let stuff = "";
        let anythingButSquareBrackets = `[^${r.openB}${r.closeB}]`;
        for (let i = 0; i < depth; i++) {
            stuff = `(?:${matchedBracesWithPipe}|${anythingButSquareBrackets})*?`;
            matchedBraces = `${r.openB}${stuff}${r.closeB}`;
            matchedBracesWithPipe = matchedBraces + "|";
            //console.log(matchedBraces);
        }
        let re = new RegExp(matchedBraces, "g");
        return re;
    }
    static buildMatchedBracesRegex(depth) {
        var r = new MyRegex();
        let stringRe = `(?:"[^"]*")|(?:'[^']*')`;
        let symbols = `[-+*/%=!<>&|^~:,{}.]`;
        let number = `(?:\\d[\\d\\w.]*)`;
        let varName = r.varName;
        let matchedBraces = "";
        let matchedBracesWithPipe = "";
        let stuff = "";
        for (let i = 0; i < depth; i++) {
            stuff = `(?:${matchedBracesWithPipe}${stringRe}|${symbols}|${number}|${varName}|[ \\t]+)*?`;
            matchedBraces = `${r.openP}${stuff}${r.closeP}`;
            matchedBracesWithPipe = matchedBraces + "|";
            //console.log(matchedBraces);
        }
        let re = new RegExp(matchedBraces, "g");
        return re;
    }
    static buildMatchedBracesCaptureParamsRegex(depth, maxParams) {
        // let str = 'do_stuff(this(), andthis(bb))';
        // console.log(XRegExp.matchRecursive(str, '\\(', '\\)', 'g'));
        var r = new MyRegex();
        let stringRe = `(?:"[^"]*")|(?:'[^']*')`;
        let symbols = `[-+*/%=!<>&|^~:,{}.$]`;
        let symbolsNoComma = `[-+*/%=!<>&|^~:{}.$]`;
        let number = `(?:\\d[\\d\\w.]*)`;
        let varName = r.varName;
        let matchedBraces = "";
        let matchedBracesWithPipe = "";
        let stuff = "";
        for (let i = 0; i < depth; i++) {
            stuff = `(?:${matchedBracesWithPipe}${stringRe}|${symbols}|${number}|${varName}|[ \\t]+)*?`;
            matchedBraces = `${r.openP}${stuff}${r.closeP}`;
            matchedBracesWithPipe = matchedBraces + "|";
        }
        stuff = `(?:${matchedBracesWithPipe}${stringRe}|${symbolsNoComma}|${number}|${varName}|[ \\t]+)*?`;
        let params = `(${stuff})?`;
        for (let i = 0; i < maxParams; i++) {
            params += `(?:,(${stuff}))?`;
        }
        matchedBraces = `${r.openP}${params}${r.closeP}`;
        let re = new RegExp(matchedBraces, "g");
        return re;
    }
    static buildCommentRegex() {
        var r = new MyRegex();
        var starComment = `${r.fslash}${r.star}+${r.any}*?${r.star}+${r.fslash}`; //match /*anything*/
        var ffComment = `${r.fslash}${r.fslash}.*(?=${r.nl})`;
        var anyComment = `(?:${starComment}|${ffComment})`;
        var regex = new RegExp(anyComment, "mg");
        return regex;
    }
    static buildPreCommentRegex() {
        var r = new MyRegex();
        var starComment = `${r.fslash}${r.star}+${r.any}*?${r.star}+${r.fslash}`; //match /*anything*/
        var ffComment = `${r.fslash}${r.fslash}.*$`;
        var xComment = `(?:${starComment}|${ffComment})`;
        var xCommentsSameLine = `(?:(?:${xComment}${r.mhs})+)`;
        var anyLineComment = `(?:${ffComment}|${xCommentsSameLine})`;
        var blockLineComment = `${ffComment}(?:\\n${r.mhs}${ffComment})*`;
        var blockStarsComment = `${xCommentsSameLine}${r.mhs}(?:\\n${r.mhs}${xCommentsSameLine})*`; //match /* blah */ newline /* more blah */
        var blockAnyComment = `${xCommentsSameLine}(?:\\n${r.mhs}${xCommentsSameLine})*`;
        var anyComment = `(?:${blockStarsComment}|${blockLineComment})`;
        var regex = new RegExp(blockAnyComment, "mg");
        return regex;
    }
    static buildPostCommentRegex() {
        var r = new MyRegex();
        var starComment = `${r.fslash}${r.star}${r.any}*?${r.star}${r.fslash}`; //match /*anything*/
        var allowedMultipleStarCommentsSameLine = `(?:${starComment}${r.mhs})+`;
        var ffComment = `${r.fslash}${r.fslash}.*?$`;
        var anyLineComment = `(?:${allowedMultipleStarCommentsSameLine}|${ffComment})`; //allow '/* comment */ /* another comment */'' on same line
        var regex = new RegExp(anyLineComment, "mg");
        return regex;
    }
    static buildExpansionRegex() {
        var r = new MyRegex();
        var preComment = this.buildPreCommentRegex().source;
        var postComment = this.buildPostCommentRegex().source;
        var varName = r.varName;
        var functionParameters = `(?:${varName}(?:${r.mhs},${r.mhs}${varName})*)`;
        var cFP = `(?:${r.openP}${r.mhs}(${functionParameters}?)${r.mhs}${r.closeP})`; //capturedFunctionParams
        var div = `====+>`;
        var ungreedyRest = `(?:.+?)`;
        var re = `^${r.mhs}(${varName})${r.mhs}${cFP}?${r.mhs}${div}${r.mhs}(${ungreedyRest})${r.mhs}`;
        var expWithComment = `(?:^${r.mhs}(${preComment})${r.mhs}\\n)?${re}${r.mhs}(${postComment})?${r.mhs}$(?:\\n${r.blankLine}*)?`;
        var regExp = new RegExp(expWithComment, "gmy"); //yayy for the sticky flag!!!
        return regExp;
    }
    static buildStructFieldRegex() {
        /*
          examples to match:
            bool is_active : 1;
            uint8_t counts[SOME_SIZE];
        */
        //captured groups: preComment, type, name, bitfieldSize, arraySize, lineComment
        var r = new MyRegex();
        var hs = r.hs;
        var mhs = r.mhs;
        var capturedType = /(?:\b(\w+)\b)/.source; //no pointers allowed
        var capturedVarName = `(${r.varName})`;
        var capturedBitFieldSize = /(?:[:][ \t]*(\d+)\b)/.source;
        var capturedArrayLength = /(?:[\[][ \t]*(\w+)[ \t]*[\]])/.source;
        var ending = /(?:[;])/.source;
        var preComment = this.buildPreCommentRegex().source;
        var postComment = this.buildPostCommentRegex().source;
        var ungreedyRest = `(?:.+?)`;
        var re = `^${mhs}${capturedType}${hs}${capturedVarName}${mhs}(?:${capturedBitFieldSize}|${capturedArrayLength})?${mhs}${ending}`;
        var expWithComment = `(?:^${r.mhs}(${preComment})${r.mhs}\\n)?${re}${r.mhs}(${postComment})?${r.mhs}$(?:\\n${r.blankLine}*)?`;
        var regExp = new RegExp(expWithComment, "gmy"); //yayy for the sticky flag!!!
        return regExp;
    }
}
exports.MyRegex = MyRegex;
//console.log(MyRegex.buildStructFieldRegex());
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiTXlSZWdleC5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzIjpbIk15UmVnZXgudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7QUFBQSxtREFBbUQ7QUFDbkQ7SUFBQTtRQUNFLE9BQUUsR0FBRyxJQUFJLENBQUM7UUFDVixXQUFNLEdBQUcsR0FBRyxDQUFDO1FBQ2IsU0FBSSxHQUFHLEtBQUssQ0FBQztRQUNiLFFBQUcsR0FBRyxLQUFLLENBQUM7UUFDWixPQUFFLEdBQUcsb0JBQW9CLENBQUMsQ0FBQyxTQUFTO1FBQ3BDLFdBQU0sR0FBRyxRQUFRLENBQUMsQ0FBQyxrQkFBa0I7UUFDckMsT0FBRSxHQUFHLFFBQVEsQ0FBQyxDQUFDLGtCQUFrQjtRQUNqQyxRQUFHLEdBQUcsU0FBUyxDQUFDLENBQUUsd0JBQXdCO1FBQzFDLE9BQUUsR0FBRyxNQUFNLENBQUMsQ0FBRSxhQUFhO1FBQzNCLGNBQVMsR0FBRyxPQUFPLElBQUksQ0FBQyxHQUFHLE9BQU8sQ0FBQztRQUNuQyxZQUFPLEdBQUcsbUJBQW1CLENBQUM7UUFDOUIsVUFBSyxHQUFHLEtBQUssQ0FBQztRQUNkLFdBQU0sR0FBRyxLQUFLLENBQUM7UUFDZixVQUFLLEdBQUcsS0FBSyxDQUFDO1FBQ2QsV0FBTSxHQUFHLEtBQUssQ0FBQztRQUNmLDBCQUEwQjtRQUMxQixXQUFNLEdBQUcsS0FBSyxDQUFDO1FBQ2YsMEJBQTBCO1FBQzFCLFlBQU8sR0FBRyxLQUFLLENBQUM7UUFDaEIsU0FBSSxHQUFHLEtBQUssQ0FBQztRQUNiLFVBQUssR0FBRyxLQUFLLENBQUM7UUFDZCxNQUFDLEdBQUcsS0FBSyxDQUFBO0lBb0xYLENBQUM7SUFsTEMsTUFBTSxDQUFDLFVBQVUsQ0FBQyxRQUFpQixHQUFHO1FBQ3BDLE1BQU0sQ0FBQyxJQUFJLE1BQU0sQ0FBQyxJQUFJLE9BQU8sRUFBRSxDQUFDLEVBQUUsRUFBRSxLQUFLLENBQUMsQ0FBQztJQUM3QyxDQUFDO0lBRUQsTUFBTSxDQUFDLGVBQWUsQ0FBQyxJQUFh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