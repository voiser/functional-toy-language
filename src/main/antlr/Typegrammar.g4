grammar Typegrammar;

ty : simple
   | fn
   ;

ty2 : simple
    | '(' fn ')'
    ;

simple : ID 
       ;

fn : left (',' left)* '->' right=ty2 
   ;

left : ty2
     ;

ID : [A-Z][a-zA-Z]* ;
WS : [ \t\r\n]+ -> skip ;

