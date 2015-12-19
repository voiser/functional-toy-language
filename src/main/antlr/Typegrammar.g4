grammar Typegrammar;

ty : simple
   | generic
   | var
   | fn
   | '(' fn ')'
   ;

ty2 : simple
    | generic
    | var
    | '(' fn ')'
    ;

simple : ID 
       ;

generic : ID '[' ty2 (',' ty2)*  ']'
        ;

var : VAR restriction*
    ;

restriction : '+' (simple | generic)
            ;

fn : left (',' left)* '->' right=ty2 
   ;

left : ty2
     ;

ID : [A-Z][a-zA-Z]* ;
VAR : [a-z0-9]+ ;
WS : [ \t\r\n]+ -> skip ;

