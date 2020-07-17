// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
//ALL CONST EVENT NAMES MUST BE UPPER CASE!
exports.EXIT = "EXIT";
exports.ENTER = "ENTER";
exports.LANDED_IN = "LANDED_IN";
exports.TEST_TRANSITIONS = "TEST_TRANSITIONS";
exports.DO = "DO";
exports.ANY_PLUS = "ANY+";
exports.ELSE = "ELSE";
exports.ANY_NORMAL = "ANY"; //TODO use?
function isHsmEvent(eventName) {
    let reserved = false;
    switch (eventName.toUpperCase()) {
        case exports.EXIT:
        case exports.ENTER:
        case exports.LANDED_IN:
        case exports.DO:
        case exports.TEST_TRANSITIONS:
            reserved = true;
            break;
    }
    return reserved;
}
exports.isHsmEvent = isHsmEvent;
function isPseudoEventName(eventName) {
    let reserved = false;
    switch (eventName.toUpperCase()) {
        case exports.ELSE:
        case exports.ANY_PLUS:
            reserved = true;
            break;
    }
    return reserved;
}
exports.isPseudoEventName = isPseudoEventName;
function getNonTransitionTriggerHandlers(state) {
    let result = [];
    for (let eh of state.eventHandlers) {
        if (hasTransitionTrigger(eh.getTriggersSet()) == false) {
            result.push(eh);
        }
    }
    return result;
}
exports.getNonTransitionTriggerHandlers = getNonTransitionTriggerHandlers;
class CategorizedTrigger {
    constructor() {
        this.transitional = [];
        this.normal = [];
    }
    isMixed() {
        let result = this.transitional.length > 0 && this.normal.length > 0;
        return result;
    }
}
exports.CategorizedTrigger = CategorizedTrigger;
function groupTransitionTriggers(triggers) {
    let result = new CategorizedTrigger();
    for (let trigger of triggers) {
        if (isTransitionEventName(trigger)) {
            result.transitional.push(trigger);
        }
        else {
            result.normal.push(trigger);
        }
    }
    return result;
}
exports.groupTransitionTriggers = groupTransitionTriggers;
function getHandlersByTrigger(state, triggerName) {
    let result = [];
    for (let eh of state.eventHandlers) {
        if (eh.hasTrigger(triggerName)) {
            result.push(eh);
        }
    }
    return result;
}
exports.getHandlersByTrigger = getHandlersByTrigger;
function hasTransitionTrigger(triggers) {
    if (triggers.has(exports.EXIT)) {
        return true;
    }
    if (triggers.has(exports.ENTER)) {
        return true;
    }
    if (triggers.has(exports.LANDED_IN)) {
        return true;
    }
    return false;
}
exports.hasTransitionTrigger = hasTransitionTrigger;
function isTransitionEventName(eventName) {
    let reserved = false;
    switch (eventName.toUpperCase()) {
        case exports.EXIT:
        case exports.ENTER:
        case exports.LANDED_IN:
            reserved = true;
            break;
    }
    return reserved;
}
exports.isTransitionEventName = isTransitionEventName;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiVHJpZ2dlcnMuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJUcmlnZ2Vycy50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUdBLDJDQUEyQztBQUU5QixRQUFBLElBQUksR0FBRyxNQUFNLENBQUM7QUFDZCxRQUFBLEtBQUssR0FBRyxPQUFPLENBQUM7QUFDaEIsUUFBQSxTQUFTLEdBQUcsV0FBVyxDQUFDO0FBQ3hCLFFBQUEsZ0JBQWdCLEdBQUcsa0JBQWtCLENBQUE7QUFDckMsUUFBQSxFQUFFLEdBQUcsSUFBSSxDQUFDO0FBRVYsUUFBQSxRQUFRLEdBQUcsTUFBTSxDQUFDO0FBQ2xCLFFBQUEsSUFBSSxHQUFHLE1BQU0sQ0FBQztBQUVkLFFBQUEsVUFBVSxHQUFHLEtBQUssQ0FBQyxDQUFFLFdBQVc7QUFHN0Msb0JBQTJCLFNBQWtCO0lBQzNDLElBQUksUUFBUSxHQUFHLEtBQUssQ0FBQztJQUNyQixNQUFNLENBQUEsQ0FBQyxTQUFTLENBQUMsV0FBVyxFQUFFLENBQUMsQ0FBQSxDQUFDO1FBQzlCLEtBQUssWUFBSSxDQUFDO1FBQ1YsS0FBSyxhQUFLLENBQUM7UUFDWCxLQUFLLGlCQUFTLENBQUM7UUFDZixLQUFLLFVBQUUsQ0FBQztRQUNSLEtBQUssd0JBQWdCO1lBQ25CLFFBQVEsR0FBRyxJQUFJLENBQUM7WUFDbEIsS0FBSyxDQUFDO0lBQ1IsQ0FBQztJQUNELE1BQU0sQ0FBQyxRQUFRLENBQUM7QUFDbEIsQ0FBQztBQVpELGdDQVlDO0FBRUQsMkJBQWtDLFNBQWtCO0lBQ2xELElBQUksUUFBUSxHQUFHLEtBQUssQ0FBQztJQUNyQixNQUFNLENBQUEsQ0FBQyxTQUFTLENBQUMsV0FBVyxFQUFFLENBQUMsQ0FBQSxDQUFDO1FBQzlCLEtBQUssWUFBSSxDQUFDO1FBQ1YsS0FBSyxnQkFBUTtZQUNYLFFBQVEsR0FBRyxJQUFJLENBQUM7WUFDbEIsS0FBSyxDQUFDO0lBQ1IsQ0FBQztJQUNELE1BQU0sQ0FBQyxRQUFRLENBQUM7QUFDbEIsQ0FBQztBQVRELDhDQVNDO0FBRUQseUNBQWdELEtBQWE7SUFDM0QsSUFBSSxNQUFNLEdBQW9CLEVBQUUsQ0FBQztJQUNqQyxHQUFHLENBQUEsQ0FBQyxJQUFJLEVBQUUsSUFBSSxLQUFLLENBQUMsYUFBYSxDQUFDLENBQUMsQ0FBQztRQUNsQyxFQUFFLENBQUEsQ0FBQyxvQkFBb0IsQ0FBQyxFQUFFLENBQUMsY0FBYyxFQUFFLENBQUMsSUFBSSxLQUFLLENBQUMsQ0FBQSxDQUFDO1lBQ3JELE1BQU0sQ0FBQyxJQUFJLENBQUMsRUFBRSxDQUFDLENBQUM7UUFDbEIsQ0FBQztJQUNILENBQUM7SUFDRCxNQUFNLENBQUMsTUFBTSxDQUFDO0FBQ2hCLENBQUM7QUFSRCwwRUFRQztBQUdEO0lBQUE7UUFDRSxpQkFBWSxHQUFlLEVBQUUsQ0FBQztRQUM5QixXQUFNLEdBQWUsRUFBRSxDQUFDO0lBTTFCLENBQUM7SUFKUSxPQUFPO1FBQ1osSUFBSSxNQUFNLEdBQUcsSUFBSSxDQUFDLFlBQVksQ0FBQyxNQUFNLEdBQUcsQ0FBQyxJQUFJLElBQUksQ0FBQyxNQUFNLENBQUMsTUFBTSxHQUFHLENBQUMsQ0FBQztRQUNwRSxNQUFNLENBQUMsTUFBTSxDQUFDO0lBQ2hCLENBQUM7Q0FDRjtBQVJELGdEQVFDO0FBRUQsaUNBQXdDLFFBQW1CO0lBQ3pELElBQUksTUFBTSxHQUFHLElBQUksa0JBQWtCLEVBQUUsQ0FBQztJQUV0QyxHQUFHLENBQUEsQ0FBQyxJQUFJLE9BQU8sSUFBSSxRQUFRLENBQUMsQ0FBQyxDQUFDO1FBQzVCLEVBQUUsQ0FBQSxDQUFDLHFCQUFxQixDQUFDLE9BQU8sQ0FBQyxDQUFDLENBQUEsQ0FBQztZQUNqQyxNQUFNLENBQUMsWUFBWSxDQUFDLElBQUksQ0FBQyxPQUFPLENBQUMsQ0FBQztRQUNwQyxDQUFDO1FBQUMsSUFBSSxDQUFDLENBQUM7WUFDTixNQUFNLENBQUMsTUFBTSxDQUFDLElBQUksQ0FBQyxPQUFPLENBQUMsQ0FBQztRQUM5QixDQUFDO0lBQ0gsQ0FBQztJQUVELE1BQU0sQ0FBQyxNQUFNLENBQUM7QUFDaEIsQ0FBQztBQVpELDBEQVlDO0FBRUQsOEJBQXFDLEtBQWEsRUFBRSxXQUFxQjtJQUN2RSxJQUFJLE1BQU0sR0FBb0IsRUFBRSxDQUFDO0lBQ2pDLEdBQUcsQ0FBQSxDQUFDLElBQUksRUFBRSxJQUFJLEtBQUssQ0FBQyxhQUFhLENBQUMsQ0FBQyxDQUFDO1FBQ2xDLEVBQUUsQ0FBQSxDQUFDLEVBQUUsQ0FBQyxVQUFVLENBQUMsV0FBVyxDQUFDLENBQUMsQ0FBQSxDQUFDO1lBQzdCLE1BQU0sQ0FBQyxJQUFJLENBQUMsRUFBRSxDQUFDLENBQUM7UUFDbEIsQ0FBQztJQUNILENBQUM7SUFFRCxNQUFNLENBQUMsTUFBTSxDQUFDO0FBQ2hCLENBQUM7QUFURCxvREFTQztBQUdELDhCQUFxQyxRQUE4QjtJQUNqRSxFQUFFLENBQUEsQ0FBQyxRQUFRLENBQUMsR0FBRyxDQUFDLFlBQUksQ0FBQyxDQUFDLENBQUEsQ0FBQztRQUFDLE1BQU0sQ0FBQyxJQUFJLENBQUM7SUFBQyxDQUFDO0lBQ3RDLEVBQUUsQ0FBQSxDQUFDLFFBQVEsQ0FBQyxHQUFHLENBQUMsYUFBSyxDQUFDLENBQUMsQ0FBQSxDQUFDO1FBQUMsTUFBTSxDQUFDLElBQUksQ0FBQztJQUFDLENBQUM7SUFDdkMsRUFBRSxDQUFBLENBQUMsUUFBUSxDQUFDLEdBQUcsQ0FBQyxpQkFBUyxDQUFDLENBQUMsQ0FBQSxDQUFDO1FBQUMsTUFBTSxDQUFDLElBQUksQ0FBQztJQUFDLENBQUM7SUFDM0MsTUFBTSxDQUFDLEtBQUssQ0FBQztBQUNmLENBQUM7QUFMRCxvREFLQztBQUVELCtCQUFzQyxTQUFrQjtJQUN0RCxJQUFJLFFBQVEsR0FBRyxLQUFLLENBQUM7SUFDckIsTUFBTSxDQUFBLENBQUMsU0FBUyxDQUFDLFdBQVcsRUFBRSxDQUFDLENBQUEsQ0FBQztRQUM5QixLQUFLLFlBQUksQ0FBQztRQUNWLEtBQUssYUFBSyxDQUFDO1FBQ1gsS0FBSyxpQkFBUztZQUNaLFFBQVEsR0FBRyxJQUFJLENBQUM7WUFDbEIsS0FBSyxDQUFDO0lBQ1IsQ0FBQztJQUNELE1BQU0sQ0FBQyxRQUFRLENBQUM7QUFDbEIsQ0FBQztBQVZELHNEQVVDIn0=