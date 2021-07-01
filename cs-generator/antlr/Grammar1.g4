grammar Grammar1;

/*

Things we need to parse out:
- state name
- behaviors

expansions:
expansions should be expanded fully beforehand so we can expand as needed easily during listening.

- expansion_var_candidates
    - name
- expansion_function_candidates
    - name, arguments
*/

/*
    NOTES!
    Lexing rules are very important and should be used with care!

 */
optional_any_space: (HWS | LINE_ENDER)*;
ohs: HWS? ;

state_defn:
    optional_any_space
    state_name
    ohs
    (
        nl_behaviors
        |
        optional_any_space
    )
    optional_any_space
    EOF
    ;

state_name: 
    IDENTIFIER ;

nl_behaviors:
    nl_behavior+ ;

nl_behavior:
    LINE_ENDER
    optional_any_space
    behavior 
    ;

behavior:
    order?
    ( 
        triggers guard
        |
        triggers
        |
        guard
    )
    action?
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
    code_line_element+
    ;

oneline_action: 
    ohs
    code_line
    ;

// action_code:
//     ohs
//     any_code  //allow to be empty in the parsing phase. Maybe warn later.
//     ;

// braced_action: '{' maybe_code '}' ;


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
        '.' // foo.bar
        |
        '::' //foo::bar
        |
        '->' //foo->bar. todolow this can conflict with Java lambda.
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
braced_expression: '{' any_code '}' ;

line_comment: LINE_COMMENT;
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
    LINE_ENDER
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
    CODE_SYMBOL |
    group_expression |
    square_brace_expression |
    braced_expression |
    HWS
    ;

code_line: 
    ohs;


LINE_ENDER: [\r\n]+;

IDENTIFIER  :   IDENTIFIER_NON_DIGIT   (   IDENTIFIER_NON_DIGIT | DIGIT  )*  ;


number :
    ('-' | '+')?
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
LINE_COMMENT: '//' NOT_NL_CR* LINE_ENDER ;
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
CODE_SYMBOL: 
    [-~!%^&*+=:;/<>?|] | PERIOD ;    //don't include braces/parenthesis as those need to be grouped
    //don't include comma because we need to be able to parse function arguments

HWS : [ \t]+ ;


// HWS : [ \t]+ -> skip; // skip horizontal white space. !!!DO NOT!!! include `\r\n` here because then WS will override LINE_ENDER. Perhaps largest token wins? I thought higher lexer rule would win, but it doesn't.
