"use strict";
// Copyright (c) 2020 JCA Electronics, Winnipeg, MB
Object.defineProperty(exports, "__esModule", { value: true });
const Misc_1 = require("../ts-utils/Misc");
class State {
    constructor() {
        this._eventHandlers = [];
        this.eventHandlers = this._eventHandlers;
        this._incomingEventHandlers = [];
        this.incomingEventHandlers = this._incomingEventHandlers;
        this.kids = [];
        this.is_ortho_parent = false;
        this.depth = 0;
    }
    isAncestorOf(otherState) {
        if (otherState.outputId == null || this.outputId == null) {
            throw "tree needs to be setup first";
        }
        return otherState.outputId > this.outputId && otherState.outputId <= this.max_descendant_id;
    }
    isComplexInitialState() {
        if (!this.isInitialState) {
            throw "you should check `isInitialState` first";
        }
        //TODOLOW optimize here for when it has an else statement
        return this.eventHandlers.length > 1 || this.eventHandlers[0].hasSomeTriggers() || this.eventHandlers[0].guard.guardCode != "true";
    }
    isSimpleInitialState() {
        return !this.isComplexInitialState();
    }
    getSimpleInitialStateTranstion() {
        if (!this.isSimpleInitialState()) {
            throw "you should check `isInitialState` first";
        }
        if (this.eventHandlers.length != 1) {
            throw "unexpected";
        }
        return this.eventHandlers[0].nextState;
    }
    static retargetTransition(transition, newTarget) {
        Misc_1.removeFromArray(transition.nextState._incomingEventHandlers, transition);
        transition.nextState = newTarget;
        newTarget._incomingEventHandlers.push(transition);
    }
    removeEventHandler(eh) {
        if (eh.nextState) {
            Misc_1.removeFromArray(eh.nextState._incomingEventHandlers, eh);
        }
        Misc_1.removeFromArray(this._eventHandlers, eh);
    }
    addEventHandlers(handlers) {
        for (const eh of handlers) {
            this._eventHandlers.push(eh);
            if (eh.nextState) {
                eh.nextState._incomingEventHandlers.push(eh);
            }
        }
    }
    sortEventHandlers(compareFunc) {
        this._eventHandlers.sort(compareFunc);
    }
}
exports.State = State;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiU3RhdGUuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJTdGF0ZS50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUEsbURBQW1EOztBQUduRCwyQ0FBbUQ7QUFFbkQ7SUFBQTtRQUlVLG1CQUFjLEdBQXlCLEVBQUUsQ0FBQztRQUNsRCxrQkFBYSxHQUFpQyxJQUFJLENBQUMsY0FBYyxDQUFDO1FBQzFELDJCQUFzQixHQUF5QixFQUFFLENBQUM7UUFDMUQsMEJBQXFCLEdBQWlDLElBQUksQ0FBQyxzQkFBc0IsQ0FBQztRQUdsRixTQUFJLEdBQWEsRUFBRSxDQUFDO1FBR3BCLG9CQUFlLEdBQWEsS0FBSyxDQUFDO1FBQ2xDLFVBQUssR0FBWSxDQUFDLENBQUM7SUFnRXJCLENBQUM7SUEzREMsWUFBWSxDQUFDLFVBQWtCO1FBRTdCLEVBQUUsQ0FBQyxDQUFDLFVBQVUsQ0FBQyxRQUFRLElBQUksSUFBSSxJQUFJLElBQUksQ0FBQyxRQUFRLElBQUksSUFBSSxDQUFDLENBQUMsQ0FBQztZQUN6RCxNQUFNLDhCQUE4QixDQUFDO1FBQ3ZDLENBQUM7UUFDRCxNQUFNLENBQUMsVUFBVSxDQUFDLFFBQVEsR0FBRyxJQUFJLENBQUMsUUFBUSxJQUFJLFVBQVUsQ0FBQyxRQUFRLElBQUksSUFBSSxDQUFDLGlCQUFpQixDQUFDO0lBQzlGLENBQUM7SUFFRCxxQkFBcUI7UUFDbkIsRUFBRSxDQUFDLENBQUMsQ0FBQyxJQUFJLENBQUMsY0FBYyxDQUFDLENBQUMsQ0FBQztZQUN6QixNQUFNLHlDQUF5QyxDQUFDO1FBQ2xELENBQUM7UUFFRCx5REFBeUQ7UUFDekQsTUFBTSxDQUFDLElBQUksQ0FBQyxhQUFhLENBQUMsTUFBTSxHQUFHLENBQUMsSUFBSSxJQUFJLENBQUMsYUFBYSxDQUFDLENBQUMsQ0FBQyxDQUFDLGVBQWUsRUFBRSxJQUFJLElBQUksQ0FBQyxhQUFhLENBQUMsQ0FBQyxDQUFDLENBQUMsS0FBSyxDQUFDLFNBQVMsSUFBSSxNQUFNLENBQUM7SUFDckksQ0FBQztJQUVELG9CQUFvQjtRQUNsQixNQUFNLENBQUMsQ0FBQyxJQUFJLENBQUMscUJBQXFCLEVBQUUsQ0FBQztJQUN2QyxDQUFDO0lBRUQsOEJBQThCO1FBQzVCLEVBQUUsQ0FBQyxDQUFDLENBQUMsSUFBSSxDQUFDLG9CQUFvQixFQUFFLENBQUMsQ0FBQyxDQUFDO1lBQ2pDLE1BQU0seUNBQXlDLENBQUM7UUFDbEQsQ0FBQztRQUVELEVBQUUsQ0FBQyxDQUFDLElBQUksQ0FBQyxhQUFhLENBQUMsTUFBTSxJQUFJLENBQUMsQ0FBQyxDQUFDLENBQUM7WUFDbkMsTUFBTSxZQUFZLENBQUM7UUFDckIsQ0FBQztRQUVELE1BQU0sQ0FBQyxJQUFJLENBQUMsYUFBYSxDQUFDLENBQUMsQ0FBQyxDQUFDLFNBQVMsQ0FBQztJQUN6QyxDQUFDO0lBRUQsTUFBTSxDQUFDLGtCQUFrQixDQUFDLFVBQXlCLEVBQUUsU0FBaUI7UUFDcEUsc0JBQWUsQ0FBQyxVQUFVLENBQUMsU0FBUyxDQUFDLHNCQUFzQixFQUFFLFVBQVUsQ0FBQyxDQUFDO1FBQ3pFLFVBQVUsQ0FBQyxTQUFTLEdBQUcsU0FBUyxDQUFDO1FBQ2pDLFNBQVMsQ0FBQyxzQkFBc0IsQ0FBQyxJQUFJLENBQUMsVUFBVSxDQUFDLENBQUM7SUFDcEQsQ0FBQztJQUVELGtCQUFrQixDQUFDLEVBQWlCO1FBQ2xDLEVBQUUsQ0FBQyxDQUFDLEVBQUUsQ0FBQyxTQUFTLENBQUMsQ0FBQyxDQUFDO1lBQ2pCLHNCQUFlLENBQUMsRUFBRSxDQUFDLFNBQVMsQ0FBQyxzQkFBc0IsRUFBRSxFQUFFLENBQUMsQ0FBQztRQUMzRCxDQUFDO1FBQ0Qsc0JBQWUsQ0FBQyxJQUFJLENBQUMsY0FBYyxFQUFFLEVBQUUsQ0FBQyxDQUFDO0lBQzNDLENBQUM7SUFFRCxnQkFBZ0IsQ0FBQyxRQUF5QjtRQUN4QyxHQUFHLENBQUMsQ0FBQyxNQUFNLEVBQUUsSUFBSSxRQUFRLENBQUMsQ0FBQyxDQUFDO1lBQzFCLElBQUksQ0FBQyxjQUFjLENBQUMsSUFBSSxDQUFDLEVBQUUsQ0FBQyxDQUFDO1lBQzdCLEVBQUUsQ0FBQyxDQUFDLEVBQUUsQ0FBQyxTQUFTLENBQUMsQ0FBQyxDQUFDO2dCQUNqQixFQUFFLENBQUMsU0FBUyxDQUFDLHNCQUFzQixDQUFDLElBQUksQ0FBQyxFQUFFLENBQUMsQ0FBQztZQUMvQyxDQUFDO1FBQ0gsQ0FBQztJQUNILENBQUM7SUFHRCxpQkFBaUIsQ0FBQyxXQUF3RDtRQUN4RSxJQUFJLENBQUMsY0FBYyxDQUFDLElBQUksQ0FBQyxXQUFXLENBQUMsQ0FBQztJQUN4QyxDQUFDO0NBQ0Y7QUE5RUQsc0JBOEVDIn0=