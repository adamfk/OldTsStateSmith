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

WORD: [a-zA-Z_0-9]+ ;

LINE_ENDER: [\r\n]+ ;

state_name: WORD LINE_ENDER ;

TRIGGER_SIMPLE: [a-zA-Z_]+ ;
trigger_list: '('
                (
                    TRIGGER_SIMPLE | ( ',' TRIGGER_SIMPLE )*
                )
              ')' ;
triggers: TRIGGER_SIMPLE | trigger_list ;
ORDER: [0-9]+ '.' ;

guard: '[' code_expression ']' ;

action: braced_action | naked_action ;
braced_action: '/' '{' code_expression '}' ;
naked_action:  '/' code_expression ;

behavior: ORDER? triggers? guard? action? ;


LINE_COMMENT: '//' [^\n\r]* LINE_ENDER ;
ML_COMMENT: '/*' .*? '*/' ;
CODE_IDENTIFIER: ('$' | [a-zA-Z_0-9])+ ; // variable, function name, "if", "true", ...
ESCAPED_CHAR: '\\' . ;
CHAR_LITERAL: [']
      ( ESCAPED_CHAR | [^'] )
      ['] ;
STRING_CHAR: ESCAPED_CHAR | [^"] ;
string: '"' STRING_CHAR* '"' ;
//#func_call: CODE_IDENTIFIER group_expression ;
//#var_name: CODE_IDENTIFIER ;
CODE_SYMBOLS: [-~!%^&*+=:;/,.<>?|] ;
group_expression: '(' code_expression* ')' ;
square_brace_expression: '[' code_expression* ']' ;
braced_expression: '{' code_expression* '}' ;

code_expression: 
    (
        LINE_COMMENT |
        ML_COMMENT |
        CHAR_LITERAL |
        string |
        CODE_IDENTIFIER |
        CODE_SYMBOLS |
        group_expression |
        square_brace_expression |
        braced_expression
    ) ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
