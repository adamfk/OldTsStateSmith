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

state_defn: state_name behaviors? EOF ;
state_name: WORD ;
behaviors: LINE_ENDER behavior ( LINE_ENDER behavior ) *;   //LINE_ENDER here because it is optional for state_definition. could just be `<statename>`.
behavior: ORDER? Triggers? guard? action? LINE_ENDER ;



fragment TRIGGER_SIMPLE: [a-zA-Z_0-9]+ ;
Trigger_list: '('
                (
                    TRIGGER_SIMPLE | ( ',' TRIGGER_SIMPLE )*
                )
              ')' ;
Triggers: TRIGGER_SIMPLE | Trigger_list ;
ORDER: [0-9]+ '.' ;

guard: '[' code_elements ']' ;

action: braced_action | naked_action ;
braced_action: '/' '{' code_elements* '}' ;
naked_action:  '/' code_elements* ;


fragment NOT_NL_CR: ~[\n\r];
LINE_COMMENT: '//' NOT_NL_CR* LINE_ENDER ;
ML_COMMENT: '/*' .*? '*/' ;
CODE_IDENTIFIER: ('$' | [a-zA-Z_0-9])+ ; // variable, function name, "if", "true", ...
fragment ESCAPED_CHAR: '\\' . ;
CHAR_LITERAL: [']
      ( ESCAPED_CHAR | ~['] )
      ['] ;

fragment NON_QUOTE_CHAR: ~["] ;
fragment STRING_CHAR: ESCAPED_CHAR | NON_QUOTE_CHAR ;
STRING: '"' STRING_CHAR* '"' ;
//#func_call: CODE_IDENTIFIER group_expression ;
//#var_name: CODE_IDENTIFIER ;
CODE_SYMBOLS: [-~!%^&*+=:;/,.<>?|] ;    //don't include braces/parenthesis as those need to be grouped
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
        CODE_IDENTIFIER |
        CODE_SYMBOLS |
        group_expression |
        square_brace_expression |
        braced_expression
    ) ;

LINE_ENDER: [\r\n]+ ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

WORD: [a-zA-Z_0-9]+ ;

