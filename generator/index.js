"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
const commander = require("commander");
const runner_1 = require("./runner");
// commander
//   .version('0.0.1')
//   .usage('test')
//   .option('-s --size <size>', 'Pizza size', /^(large|medium|small)$/i, 'medium')
//   .option('-d --drink [drink]', 'Drink', /^(coke|pepsi|izze)$/i)
//   .parse(process.argv);
// console.log(' size: %j', commander.size);
// console.log(' drink: %j', commander.drink);
let toProcess = new runner_1.ToProcess();
commander.version("0.1.0")
    .option("--input-directory <path>", "required")
    .option("--input-file <path>", "required. Relative to `input-directory`.")
    .option("--compiler <type>", "required.")
    .option("--simplify-initial-states <boolean>", "optional")
    .option("--output-directory <path>", "optional")
    .parse(process.argv);
toProcess.inputDirectory = commander.inputDirectory;
toProcess.inputFile = commander.inputFile;
toProcess.compiler = commander.compiler;
toProcess.simplifyInitialStates = commander.simplifyInitialStates;
toProcess.outputDirectory = commander.outputDirectory;
try {
    runner_1.generateFor([toProcess]);
}
catch (e) {
    console.log(e);
    console.log();
    commander.outputHelp(() => commander.help());
    process.exitCode = 1;
}
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5kZXguanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJpbmRleC50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUFBLG1EQUFtRDtBQUNuRCx1Q0FBdUM7QUFDdkMscUNBQWtEO0FBR2xELFlBQVk7QUFDWixzQkFBc0I7QUFDdEIsbUJBQW1CO0FBQ25CLG1GQUFtRjtBQUNuRixtRUFBbUU7QUFDbkUsMEJBQTBCO0FBRTFCLDRDQUE0QztBQUM1Qyw4Q0FBOEM7QUFLOUMsSUFBSSxTQUFTLEdBQUcsSUFBSSxrQkFBUyxFQUFFLENBQUM7QUFFaEMsU0FBUyxDQUFDLE9BQU8sQ0FBQyxPQUFPLENBQUM7S0FDaEIsTUFBTSxDQUFDLDBCQUEwQixFQUFhLFVBQVUsQ0FBQztLQUN6RCxNQUFNLENBQUMscUJBQXFCLEVBQWtCLDBDQUEwQyxDQUFDO0tBQ3pGLE1BQU0sQ0FBQyxtQkFBbUIsRUFBb0IsV0FBVyxDQUFDO0tBQzFELE1BQU0sQ0FBQyxxQ0FBcUMsRUFBRSxVQUFVLENBQUM7S0FDekQsTUFBTSxDQUFDLDJCQUEyQixFQUFZLFVBQVUsQ0FBQztLQUN6RCxLQUFLLENBQUMsT0FBTyxDQUFDLElBQUksQ0FBQyxDQUFDO0FBRTlCLFNBQVMsQ0FBQyxjQUFjLEdBQUcsU0FBUyxDQUFDLGNBQWMsQ0FBQztBQUNwRCxTQUFTLENBQUMsU0FBUyxHQUFHLFNBQVMsQ0FBQyxTQUFTLENBQUM7QUFDMUMsU0FBUyxDQUFDLFFBQVEsR0FBRyxTQUFTLENBQUMsUUFBUSxDQUFDO0FBQ3hDLFNBQVMsQ0FBQyxxQkFBcUIsR0FBRyxTQUFTLENBQUMscUJBQXFCLENBQUM7QUFDbEUsU0FBUyxDQUFDLGVBQWUsR0FBRyxTQUFTLENBQUMsZUFBZSxDQUFDO0FBRXRELElBQUksQ0FBQztJQUNILG9CQUFXLENBQUMsQ0FBQyxTQUFTLENBQUMsQ0FBQyxDQUFDO0FBQzNCLENBQUM7QUFBQyxLQUFLLENBQUEsQ0FBQyxDQUFDLENBQUMsQ0FBQSxDQUFDO0lBQ1QsT0FBTyxDQUFDLEdBQUcsQ0FBQyxDQUFDLENBQUMsQ0FBQztJQUNmLE9BQU8sQ0FBQyxHQUFHLEVBQUUsQ0FBQztJQUNkLFNBQVMsQ0FBQyxVQUFVLENBQUMsR0FBRyxFQUFFLENBQUMsU0FBUyxDQUFDLElBQUksRUFBRSxDQUFDLENBQUM7SUFDN0MsT0FBTyxDQUFDLFFBQVEsR0FBRyxDQUFDLENBQUM7QUFDdkIsQ0FBQyJ9