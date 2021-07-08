grammar Grammar1;

/*
    todolow allow tags

    todolow - look into using LEXER rules _like_ `COLON` instead of chars like ':' in parsing rules.

    NOTES!
    Lexing rules are very important and should be used with care!
 */
optional_any_space: (HWS | line_end_with_hs)*;
ohs: HWS? ;

some_ws: (HWS | LINE_ENDER)+ ;

node:
    notes_node
    |
    state_defn
    |
    ortho_defn
    |
    statemachine_defn
    ;

statemachine_defn:
    optional_any_space
    '$STATEMACHINE'
    ohs
    ':'
    ohs
    IDENTIFIER
    optional_any_space
    EOF
    ;

notes_text:
    .*?
    ;

notes_node:
    optional_any_space
    '$NOTES'
    (
        some_ws
        notes_text
    )?
    EOF
    ;

state_behaviors:
    ohs
    (
        nl_behaviors
        |
        optional_any_space
    )
    optional_any_space
    ;

//$ORTHO 1 : BASIC
ortho_defn:
    optional_any_space
    '$ORTHO'
    ohs
    ortho_order?
    ohs
    ':'
    ohs
    state_id
    state_behaviors
    EOF
    ;

//examples:
//PRESSED
state_defn:
    optional_any_space
    state_id
    state_behaviors
    EOF
    ;

global_id:
    '#'
    IDENTIFIER
    ;

state_id:
    global_id
    |
    IDENTIFIER;

ortho_order:
    number
    ;

edge:
    optional_any_space
    edge_behaviors?
    optional_any_space
    EOF
    ;

edge_behaviors:
    behavior
    ohs
    nl_behaviors?
    ;

nl_behaviors:
    nl_behavior+ ;

nl_behavior:
    line_end_with_hs
    optional_any_space
    behavior 
    ;

behavior:
    order?
    ( 
        triggers guard action?
        |
        triggers action?
        |
        guard action?
        |
        action
    )
    ;


order: 
    ohs
    number  //should only be DIGIT here? Right now allows `1.2`
    ohs
    '.';


triggers: 
    trigger_id 
    |
    trigger_list
    ;

trigger_id:
    ohs
    expandable_identifier
    ;

/*
samples:
    (MY_TRIGGER)
    ( MY_TRIGGER )
    (TRIG1,TRIG2)
    ( TRIG1 , TRIG2 )
 */
trigger_list:
    ohs
    '('
        optional_any_space
        trigger_id
        (
            optional_any_space
            ','
            optional_any_space
            trigger_id
        )*
        ohs
    ')' ;


guard:
    ohs
    '['
        guard_code
    ']'
    ;


guard_code:
    ohs
    any_code  //allow to be empty in the parsing phase. Maybe warn later.
    ;


action:
    ohs
    '/'
    ohs
    action_code
    ;

/*
 Note: we cannot just use `any_code` because then it would be legal to do things like:
    MY_STATE
    [true] / func(); { code...} { code... }

That just looks too weird.
 */
action_code:
    naked_action_code // event / do_something();
    |
    braced_expression // event / { do_something(); }
    ;

naked_action_code:
    naked_action_code_elements+
    ;

/*
For expansions, we need to ensure not part of a member access.
Assume `time` is an expansion to `vars->time_ms`

Should match here:
    `if (time>10)`, `if (time > 10)`
    `time++`

Should NOT match here:
    `if (Class::time>10)`, `if (Class::time > 10)`
    `obj.time++`
    `obj->time++`
 */


member_access_operator:
    (
        PERIOD // foo.bar
        |
        COLON COLON //foo::bar
        |
        (DASH GT) //foo->bar. todolow this can conflict with Java lambda.
    )
    ;

member_access:
    ohs
    member_access_operator

    ohs
    (
        //no expansion checking here because this belongs to something else. `foo->bar`. `foo` should be checked for expansion though.
        IDENTIFIER
        |
        member_function_call
    )
    ;

//checked for expansions
expandable_identifier:
    ohs IDENTIFIER
    ;


group_expression: 
    ohs '(' any_code ')' 
    ;

square_brace_expression: '[' any_code ']' ;
braced_expression: '{' ohs any_code '}' ;   //ohs here to help with indent formatting

line_comment: 
    LINE_COMMENT 
    ( line_end_with_hs | EOF )
    ;
star_comment: STAR_COMMENT;

function_args:
    function_arg
    (
        optional_any_space
        COMMA
        function_arg
    )*
    ;

function_arg:
    optional_any_space
    code_element+
    ;

braced_function_args:
    '('
    optional_any_space
    function_args?
    optional_any_space
    ')'
    ;

//be a bit strict on whitespace as there is a lot of state machine specific syntax here already
// `foo (123)` will not be allowed, but `foo(123)` will be.
expandable_function_call:
    ohs 
    expandable_identifier
    braced_function_args
    ;

//NON expandable
member_function_call:
    ohs 
    IDENTIFIER
    braced_function_args
    ;

any_code: 
    code_element* ;

code_element: 
    code_line_element
    |
    line_end_with_hs
    ;


naked_action_code_elements:
    star_comment |
    string |
    expandable_function_call |
    member_access | //must come before identifier to prevent bad expansions: `obj.no_expand_here`
    expandable_identifier |
    number |
    code_symbol |
    group_expression |
    HWS
    ;

//does not include newlines except when inside other expressions
code_line_element:
    line_comment |
    star_comment |
    string |
    expandable_function_call |
    member_access | //must come before identifier to prevent bad expansions: `obj.no_expand_here`
    expandable_identifier |
    number |
    code_symbol |
    group_expression |
    square_brace_expression |
    braced_expression |
    HWS
    ;

code_line: 
    ohs;


LINE_ENDER: [\r\n]+;
line_end_with_hs: LINE_ENDER ohs;

IDENTIFIER  :   IDENTIFIER_NON_DIGIT   (   IDENTIFIER_NON_DIGIT | DIGIT  )*  ;

number :
    (DASH | PLUS)?
    DIGIT+
    (
        PERIOD
        DIGIT+
    )?
    (
        'e' DIGIT+
    )?
    ;


fragment NOT_NL_CR: ~[\n\r];
LINE_COMMENT: '//' NOT_NL_CR*;
STAR_COMMENT: '/*' .*? '*/' ;

// CHAR_LITERAL: [']
//       ( ESCAPED_CHAR | ~['] )*
//       ['] ;


fragment ESCAPED_CHAR: '\\' . ;
fragment NON_QUOTE_CHAR: ~["] ;
fragment STRING_CHAR: ESCAPED_CHAR | NON_QUOTE_CHAR ;
STRING: '"' STRING_CHAR* '"' ;
TICK_STRING: ['] STRING_CHAR* ['] ;
string: STRING | TICK_STRING;
//todolow js backtick strings. template literals.


fragment IDENTIFIER_NON_DIGIT :  [$a-zA-Z_] ;


DIGIT :   [0-9]  ;

PERIOD: '.' ;
COMMA: ',' ;
PLUS : '+' ;
DASH : '-' ;
COLON : ':' ;
GT : '>' ;
LT : '<' ;
OTHER_SYMBOLS: 
    [~!%^&*=:;/?|];  //don't include braces/parenthesis as those need to be grouped

code_symbol:
    PERIOD |
    COMMA |
    PLUS |
    DASH |
    COLON |
    GT |
    LT |
    OTHER_SYMBOLS
    ;

HWS : [ \t]+ ;
