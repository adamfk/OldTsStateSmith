"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
const fs = require("fs");
const StringUtils_1 = require("./StringUtils");
/**
 * Won't actually write file if not different from original contens.
 * Important to prevent compiler from compiling everything again and showing all warnings...
 * @param filename
 * @param new_contents
 */
function update_text_file(filename, new_contents) {
    new_contents = StringUtils_1.StringUtils.normalizeLineEndingsTo(new_contents, "\r\n");
    update_file(filename, new_contents);
}
exports.update_text_file = update_text_file;
function update_file(filename, new_contents) {
    let outdated = true;
    if (fs.existsSync(filename)) {
        let original_contents = fs.readFileSync(filename);
        if (original_contents == new_contents) {
            outdated = false;
        }
    }
    if (outdated) {
        console.log("file outdated and being WRITTEN: " + filename);
        fs.writeFileSync(filename, new_contents);
    }
    else {
        console.log("file didn't change, NOT writing: " + filename);
    }
}
exports.update_file = update_file;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiRmlsZVV0aWxzLmpzIiwic291cmNlUm9vdCI6IiIsInNvdXJjZXMiOlsiRmlsZVV0aWxzLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7O0FBQUEsbURBQW1EO0FBQ25ELHlCQUEwQjtBQUMxQiwrQ0FBNEM7QUFFNUM7Ozs7O0dBS0c7QUFDSCwwQkFBaUMsUUFBaUIsRUFBRSxZQUFxQjtJQUN2RSxZQUFZLEdBQUcseUJBQVcsQ0FBQyxzQkFBc0IsQ0FBQyxZQUFZLEVBQUUsTUFBTSxDQUFDLENBQUM7SUFDeEUsV0FBVyxDQUFDLFFBQVEsRUFBRSxZQUFZLENBQUMsQ0FBQztBQUN0QyxDQUFDO0FBSEQsNENBR0M7QUFFRCxxQkFBNEIsUUFBUSxFQUFFLFlBQVk7SUFDaEQsSUFBSSxRQUFRLEdBQUcsSUFBSSxDQUFDO0lBQ3BCLEVBQUUsQ0FBQSxDQUFDLEVBQUUsQ0FBQyxVQUFVLENBQUMsUUFBUSxDQUFDLENBQUMsQ0FBQSxDQUFDO1FBQzFCLElBQUksaUJBQWlCLEdBQUcsRUFBRSxDQUFDLFlBQVksQ0FBQyxRQUFRLENBQUMsQ0FBQztRQUNsRCxFQUFFLENBQUEsQ0FBQyxpQkFBaUIsSUFBSSxZQUFZLENBQUMsQ0FBQSxDQUFDO1lBQ3BDLFFBQVEsR0FBRyxLQUFLLENBQUM7UUFDbkIsQ0FBQztJQUNILENBQUM7SUFFRCxFQUFFLENBQUEsQ0FBQyxRQUFRLENBQUMsQ0FBQSxDQUFDO1FBQ1gsT0FBTyxDQUFDLEdBQUcsQ0FBQyxtQ0FBbUMsR0FBRyxRQUFRLENBQUMsQ0FBQztRQUM1RCxFQUFFLENBQUMsYUFBYSxDQUFDLFFBQVEsRUFBRSxZQUFZLENBQUMsQ0FBQztJQUMzQyxDQUFDO0lBQUMsSUFBSSxDQUFDLENBQUM7UUFDTixPQUFPLENBQUMsR0FBRyxDQUFDLG1DQUFtQyxHQUFHLFFBQVEsQ0FBQyxDQUFDO0lBQzlELENBQUM7QUFFSCxDQUFDO0FBaEJELGtDQWdCQyJ9