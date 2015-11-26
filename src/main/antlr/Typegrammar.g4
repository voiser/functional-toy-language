grammar Typegrammar;

ty : simple
   | generic
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

var : VAR
    ;

fn : left (',' left)* '->' right=ty2 
   ;

left : ty2
     ;

ID : [A-Z][a-zA-Z]* ;
VAR : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;

