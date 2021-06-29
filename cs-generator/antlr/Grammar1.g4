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

state_defn: state_name behaviors? EOF ;
state_name: IDENTIFIER ;
behaviors: LINE_ENDER behavior ( LINE_ENDER behavior )*;   //LINE_ENDER here because it is optional for state_definition. could just be `<statename>`.
behavior: order? triggers? guard? action? ;

trigger_id: IDENTIFIER ;
triggers: trigger_id | trigger_list ;
order_number: DIGIT+ ;
order: order_number '.' ;

guard: '[' code_elements* ']' ;

action: '/' action_code ;
action_code:  code_elements* ;
// braced_action: '{' code_elements* '}' ;

//#func_call: CODE_IDENTIFIER group_expression ;
//#var_name: CODE_IDENTIFIER ;
group_expression: '(' code_elements* ')' ;
square_brace_expression: '[' code_elements* ']' ;
braced_expression: '{' code_elements* '}' ;

code_elements: code_element+ ;
code_element: 
    (
        LINE_COMMENT |
        ML_COMMENT |
        CHAR_LITERAL |
        STRING |
        IDENTIFIER |
        CODE_SYMBOLS |
        group_expression |
        square_brace_expression |
        braced_expression
    ) ;

fragment FOLLOWING_WS: [ \t\r\n] ;
LINE_ENDER: [\r\n]+ FOLLOWING_WS* ;

IDENTIFIER  :   IDENTIFIER_NON_DIGIT   (   IDENTIFIER_NON_DIGIT | DIGIT  )*  ;

fragment IDENTIFIER_NON_DIGIT :  [$a-zA-Z_] ;

DIGIT :   [0-9]  ;
trigger_list: '('
                (
                    IDENTIFIER | ( ',' IDENTIFIER )*
                )
              ')' ;

fragment NOT_NL_CR: ~[\n\r];
LINE_COMMENT: '//' NOT_NL_CR* LINE_ENDER ;
ML_COMMENT: '/*' .*? '*/' ;

fragment ESCAPED_CHAR: '\\' . ;
CHAR_LITERAL: [']
      ( ESCAPED_CHAR | ~['] )
      ['] ;

fragment NON_QUOTE_CHAR: ~["] ;
fragment STRING_CHAR: ESCAPED_CHAR | NON_QUOTE_CHAR ;
STRING: '"' STRING_CHAR* '"' ;

CODE_SYMBOLS: [-~!%^&*+=:;/,.<>?|] ;    //don't include braces/parenthesis as those need to be grouped

HWS : [ \t]+ -> skip; // skip horizontal white space. !!!DO NOT!!! include `\r\n` here because then WS will override LINE_ENDER. Perhaps largest token wins? I thought higher lexer rule would win, but it doesn't.
