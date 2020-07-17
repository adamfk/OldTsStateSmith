"use strict";
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
Object.defineProperty(exports, "__esModule", { value: true });
function indent(input, char, charTimes) {
    let result = input;
    result = input.replace(/(\r\n|\r|\n)|^/g, "$1" + char.repeat(charTimes)); //TODOLOW escape replace text
    return result;
}
exports.indent = indent;
function shallowCopyInto(into, from) {
    for (var key in from) {
        let property = from[key];
        //if(from.hasOwnProperty(key)){
        if (typeof property !== "function") {
            into[key] = property;
        }
    }
    return into;
}
exports.shallowCopyInto = shallowCopyInto;
function removeFromArray(arr, toRemove) {
    let deleteIndex = arr.indexOf(toRemove);
    if (deleteIndex < 0) {
        console.log(toRemove);
        throw "Failed to delete!";
    }
    arr.splice(deleteIndex, 1);
}
exports.removeFromArray = removeFromArray;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiTWlzYy5qcyIsInNvdXJjZVJvb3QiOiIiLCJzb3VyY2VzIjpbIk1pc2MudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IjtBQUFBLG1EQUFtRDs7QUFTbkQsZ0JBQXVCLEtBQWMsRUFBRSxJQUFhLEVBQUUsU0FBa0I7SUFDdEUsSUFBSSxNQUFNLEdBQUcsS0FBSyxDQUFDO0lBQ25CLE1BQU0sR0FBRyxLQUFLLENBQUMsT0FBTyxDQUFDLGlCQUFpQixFQUFFLElBQUksR0FBRyxJQUFJLENBQUMsTUFBTSxDQUFDLFNBQVMsQ0FBQyxDQUFDLENBQUMsQ0FBQyw2QkFBNkI7SUFDdkcsTUFBTSxDQUFDLE1BQU0sQ0FBQztBQUNoQixDQUFDO0FBSkQsd0JBSUM7QUFFRCx5QkFBbUMsSUFBUSxFQUFHLElBQU87SUFDbkQsR0FBRyxDQUFBLENBQUMsSUFBSSxHQUFHLElBQUksSUFBSSxDQUFDLENBQUEsQ0FBQztRQUNuQixJQUFJLFFBQVEsR0FBRyxJQUFJLENBQUMsR0FBRyxDQUFDLENBQUM7UUFDekIsK0JBQStCO1FBQy9CLEVBQUUsQ0FBQSxDQUFDLE9BQU8sUUFBUSxLQUFLLFVBQVUsQ0FBQyxDQUFBLENBQUM7WUFDakMsSUFBSSxDQUFDLEdBQUcsQ0FBQyxHQUFHLFFBQVEsQ0FBQztRQUN2QixDQUFDO0lBQ0gsQ0FBQztJQUNELE1BQU0sQ0FBQyxJQUFJLENBQUM7QUFDZCxDQUFDO0FBVEQsMENBU0M7QUFFRCx5QkFBbUMsR0FBTyxFQUFFLFFBQVU7SUFDcEQsSUFBSSxXQUFXLEdBQUcsR0FBRyxDQUFDLE9BQU8sQ0FBQyxRQUFRLENBQUMsQ0FBQztJQUV4QyxFQUFFLENBQUMsQ0FBQyxXQUFXLEdBQUcsQ0FBQyxDQUFDLENBQUMsQ0FBQztRQUNwQixPQUFPLENBQUMsR0FBRyxDQUFDLFFBQVEsQ0FBQyxDQUFDO1FBQ3RCLE1BQU0sbUJBQW1CLENBQUM7SUFDNUIsQ0FBQztJQUVELEdBQUcsQ0FBQyxNQUFNLENBQUMsV0FBVyxFQUFFLENBQUMsQ0FBQyxDQUFDO0FBQzdCLENBQUM7QUFURCwwQ0FTQyJ9