/*
    Useful links:
        * Recursive function pointer type work arounds for state machines: http://c-faq.com/decl/recurfuncp.html
        * ARM cortex M3 Godbolt: https://gcc.godbolt.org/z/j8GeGj9ze

    For now, all states will have an exit handler!

*/

//
#include <stdio.h>
#include <stddef.h>
#include <string.h>

enum EventId
{
    EventId_DO = 1, //to fit with Trigger Id
    EventId_ROOT_ONLY,
    EventId_A_ONLY,
    EventId_ROOT_A,
};

enum TriggerId
{
    TriggerId_EXIT,
    // TriggerId_DO = EventId_DO,
    // TriggerId_EV1 = EventId_EV1,
    // ....
    TriggerId__COUNT = EventId_ROOT_A + 1,
};

typedef struct MySm MySm;

// Why this structure? See http://c-faq.com/decl/recurfuncp.html
typedef struct BehaviorFuncStruct BehaviorFuncStruct;

typedef BehaviorFuncStruct (*BehaviorFunc)(MySm* sm);

struct BehaviorFuncStruct {
    BehaviorFunc function;
};

enum MySm_StateId
{
    MySm_StateId_ROOT,
    MySm_StateId_A,
};

struct MySm
{
    enum MySm_StateId state_id;
    enum EventId current_event;
    BehaviorFunc current_behaviors[TriggerId__COUNT];

    struct {
        int count;
    } vars;
};

/////////////////////////////////////////////////////////////////////////////////////


static void ROOT_enter(MySm* sm);
static void ROOT_exit(MySm* sm);
static BehaviorFuncStruct ROOT_handle_ROOT_A(MySm* sm);
static BehaviorFuncStruct ROOT_handle_ROOT_ONLY(MySm* sm);

static void A_enter(MySm* sm);
static void A_exit(MySm* sm);
static BehaviorFuncStruct A_handle_A_ONLY(MySm* sm);
static BehaviorFuncStruct A_handle_ROOT_A(MySm* sm);

void MySm_start(MySm* sm);



/////////////////////////////////////////////////////////////////////////////////////


void MySm_dispatch_event(MySm* const sm, enum EventId event_id)
{
    printf("\n");
    BehaviorFunc behavior_func = sm->current_behaviors[event_id];

    if (behavior_func == NULL)
    {
        return;
    }

    sm->current_event = event_id;

    while (behavior_func != NULL)
    {
        behavior_func = behavior_func(sm).function;
    }
}


static void print_count(MySm* sm)
{
    printf("  count:%i\n", sm->vars.count);
}


/**
 * Returned from a #BehaviorFunc when event handling is complete because either:
 * 1. the event was handled
 * 2. the event was not handled and there are no ancestors that listen to the event
 */
static const BehaviorFuncStruct event_handling_finished = { .function = NULL };


void MySm_start(MySm* sm)
{
    print_count(sm);
    memset(sm, 0, sizeof(*sm));
    print_count(sm);

    ROOT_enter(sm);
    print_count(sm);
    sm->state_id = MySm_StateId_ROOT;
}


////////////// ROOT ////////////////////

static void ROOT_enter(MySm* sm)
{
    printf("%s\n", __func__);

    sm->current_behaviors[TriggerId_EXIT] = ROOT_exit;
    sm->current_behaviors[EventId_ROOT_A] = ROOT_handle_ROOT_A;
    sm->current_behaviors[EventId_ROOT_ONLY] = ROOT_handle_ROOT_ONLY;
}

static void ROOT_exit(MySm* sm)
{
    //todolow consider what to do here. ROOT can't be exited unless we add some kind of "finished running" functionality
    printf("%s\n", __func__);
}

static BehaviorFuncStruct ROOT_handle_ROOT_A(MySm* sm)
{
    printf("%s\n", __func__);

    //transition to A
    //Execute transition action if any.
    //Exit up to Least Common Ancestor (LCA).
    while (sm->current_behaviors[TriggerId_EXIT] != ROOT_exit)
    {
        BehaviorFunc exit_function = sm->current_behaviors[TriggerId_EXIT];
        exit_function(sm);
    }

    //Enter towards A
    A_enter(sm);

    //Update state_id
    sm->state_id = MySm_StateId_A;

    return event_handling_finished;
}



static BehaviorFuncStruct ROOT_handle_ROOT_ONLY(MySm* sm)
{
    printf("%s\n", __func__);

    return event_handling_finished;
}

////////////// A /////////////////////

static BehaviorFuncStruct A_handle_A_ONLY(MySm* sm)
{
    printf("%s\n", __func__);

    // uml:  / count++;
    sm->vars.count++;
    print_count(sm);

    // no ancestors listen to this event.
    return event_handling_finished;
}

static BehaviorFuncStruct A_handle_ROOT_A(MySm* sm)
{
    printf("%s\n", __func__);
    print_count(sm);

    // uml: [count < 10] / count++;
    if (sm->vars.count < 3)
    {
        // event handled
        sm->vars.count++;
        print_count(sm);

        return event_handling_finished;
    }

    // Event not handled. Next ancestor that listens to this event is `ROOT` state.
    BehaviorFuncStruct next_ancestor_handler = { .function = ROOT_handle_ROOT_A };
    return next_ancestor_handler;
}

static void A_enter(MySm* sm)
{
    printf("%s\n", __func__);

    // Add State A event listeners
    sm->current_behaviors[TriggerId_EXIT] = A_exit;
    sm->current_behaviors[EventId_A_ONLY] = A_handle_A_ONLY;
    sm->current_behaviors[EventId_ROOT_A] = A_handle_ROOT_A;
}

static void A_exit(MySm* sm)
{
    printf("%s\n", __func__);

    // Remove State A event listeners
    sm->current_behaviors[TriggerId_EXIT] = ROOT_exit;
    sm->current_behaviors[EventId_A_ONLY] = NULL;   // no ancestors to listen to this event
    sm->current_behaviors[EventId_ROOT_A] = ROOT_handle_ROOT_A;
}

const char* MySm_get_state_string(MySm * const sm)
{
    switch (sm->state_id)
    {
        default:
        return "???";

        case MySm_StateId_ROOT:
        return "ROOT";

        case MySm_StateId_A:
        return "A";
    }
}


int main(void)
{
    MySm sm = { 0 };
    MySm_start(&sm);

    MySm_dispatch_event(&sm, EventId_ROOT_ONLY);
    MySm_dispatch_event(&sm, EventId_ROOT_A);
    MySm_dispatch_event(&sm, EventId_ROOT_A);
    MySm_dispatch_event(&sm, EventId_ROOT_A);
    MySm_dispatch_event(&sm, EventId_ROOT_A);
    MySm_dispatch_event(&sm, EventId_ROOT_A);

    printf("ALL DONE!\n");
    return 0;
}
