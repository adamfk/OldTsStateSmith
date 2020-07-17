"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
const YedParser_1 = require("./hsm/YedParser");
const Compiler_1 = require("./hsm/Compiler");
const FileUtils_1 = require("./ts-utils/FileUtils");
const StateTsExporter_1 = require("./hsm/StateTsExporter");
const PlantUmlExporter_1 = require("./hsm/PlantUmlExporter");
const FlatCompiler_1 = require("./hsm/FlatCompiler");
class ToProcess {
    constructor() {
        this.appendToSmName = "";
    }
}
exports.ToProcess = ToProcess;
function generateFor(toProcess, typedefPrintfMapping = {}) {
    for (const item of toProcess) {
        generateInner(item, typedefPrintfMapping);
    }
}
exports.generateFor = generateFor;
//https://stackoverflow.com/questions/50470025/nameof-keyword-in-typescript
const nameof = (name) => name;
function validate(toProcess) {
    let fieldsToValidate = [
        nameof("inputDirectory"),
        nameof("inputFile"),
        nameof("compiler"),
    ];
    for (const mapping of fieldsToValidate) {
        if (toProcess[mapping] === undefined) {
            throw `field '${mapping}' must be defined`;
        }
    }
}
function generateInner(toProcess, typedefPrintfMapping = {}) {
    validate(toProcess);
    let inputDirectory = toProcess.inputDirectory;
    let outputDirectory = toProcess.outputDirectory || toProcess.inputDirectory;
    let filePath = inputDirectory + "/" + toProcess.inputFile;
    let appendToSmName = toProcess.appendToSmName || "";
    let compType = toProcess.compiler;
    let yedParser = new YedParser_1.YedParser();
    console.log("\r\nProcessing file: " + toProcess.inputFile);
    let parsedStateMachines = yedParser.run(filePath);
    let stateTsExporter = new StateTsExporter_1.StateTsExporter();
    for (let inputHsm of parsedStateMachines) {
        inputHsm.diagramSourceFilePath = filePath;
        let compiler;
        switch (compType) {
            default:
                throw `unknown compiler type ${compType}`;
            case "hsm2":
                compiler = new Compiler_1.Compiler();
                compiler.hsm.shouldSimplifyInitialStateTransitions = toProcess.simplifyInitialStates || false;
                compiler.typedefPrintfMapping = typedefPrintfMapping;
                compiler.classFullName = inputHsm.name + appendToSmName;
                compiler.classPrefix = (inputHsm.prefix || inputHsm.name) + appendToSmName;
                console.log(inputHsm.prefix);
                console.log(compiler.classPrefix);
                compiler.setupStrings();
                break;
            case "flat":
                compiler = new FlatCompiler_1.FlatCompiler();
                compiler.hsm.shouldSimplifyInitialStateTransitions = true; //toProcess.simplifyInitialStates;
                compiler.classFullName = inputHsm.name + appendToSmName;
                break;
        }
        compiler.compile(inputHsm);
        FileUtils_1.update_text_file(`${outputDirectory}/${compiler.getSummaryFilename()}`, compiler.hsm.genSummaryText());
        FileUtils_1.update_text_file(`${outputDirectory}/${compiler.getHeaderFilename()}`, compiler.genPublicHeaderFile());
        FileUtils_1.update_text_file(`${outputDirectory}/${compiler.getSourceFilename()}`, compiler.genSourceFile());
        let plantUmlExporter = new PlantUmlExporter_1.PlantUmlExporter();
        let plantUml = plantUmlExporter.buildOutputRecursively(compiler.hsm.rootState);
        FileUtils_1.update_text_file(`${outputDirectory}/${compiler.getSourceFilename()}.plantuml`, plantUml);
        //let ts = stateTsExporter.buildOutput(compiler.hsm.rootState, inputHsm.name);
        //update_text_file(`views/doc-content/states/${inputHsm.name}.ts`, ts);          
    }
}
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoicnVubmVyLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsicnVubmVyLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7O0FBQUEsbURBQW1EO0FBQ25ELCtDQUEyQztBQUMzQyw2Q0FBMEM7QUFDMUMsb0RBQXdEO0FBRXhELDJEQUF3RDtBQUN4RCw2REFBMEQ7QUFDMUQscURBQWtEO0FBR2xEO0lBQUE7UUFRRSxtQkFBYyxHQUFJLEVBQUUsQ0FBQztJQUd2QixDQUFDO0NBQUE7QUFYRCw4QkFXQztBQUdELHFCQUE0QixTQUF1QixFQUFFLHVCQUE0QyxFQUFFO0lBQ2pHLEdBQUcsQ0FBQyxDQUFDLE1BQU0sSUFBSSxJQUFJLFNBQVMsQ0FBQyxDQUFDLENBQUM7UUFDN0IsYUFBYSxDQUFDLElBQUksRUFBRSxvQkFBb0IsQ0FBQyxDQUFDO0lBQzVDLENBQUM7QUFDSCxDQUFDO0FBSkQsa0NBSUM7QUFFRCwyRUFBMkU7QUFDM0UsTUFBTSxNQUFNLEdBQUcsQ0FBSSxJQUE4QixFQUFVLEVBQUUsQ0FBQyxJQUFJLENBQUM7QUFFbkUsa0JBQWtCLFNBQW9CO0lBRXBDLElBQUksZ0JBQWdCLEdBQUc7UUFDckIsTUFBTSxDQUFZLGdCQUFnQixDQUFDO1FBQ25DLE1BQU0sQ0FBWSxXQUFXLENBQUM7UUFDOUIsTUFBTSxDQUFZLFVBQVUsQ0FBQztLQUM5QixDQUFDO0lBRUYsR0FBRyxDQUFDLENBQUMsTUFBTSxPQUFPLElBQUksZ0JBQWdCLENBQUMsQ0FBQyxDQUFDO1FBQ3ZDLEVBQUUsQ0FBQyxDQUFDLFNBQVMsQ0FBQyxPQUFPLENBQUMsS0FBSyxTQUFTLENBQUMsQ0FDckMsQ0FBQztZQUNDLE1BQU0sVUFBVSxPQUFPLG1CQUFtQixDQUFDO1FBQzdDLENBQUM7SUFDSCxDQUFDO0FBQ0gsQ0FBQztBQUdELHVCQUF1QixTQUFxQixFQUFFLHVCQUE0QyxFQUFFO0lBQzFGLFFBQVEsQ0FBQyxTQUFTLENBQUMsQ0FBQztJQUVwQixJQUFJLGNBQWMsR0FBRyxTQUFTLENB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