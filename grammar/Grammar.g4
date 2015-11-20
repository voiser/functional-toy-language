grammar Grammar;

file  : 'module' name=ID ('import' imp=ID)* block
      ;

block : (expression) +
      ;

expression : value 
           | fn
           | def
           | apply
           | ref
           | cond
           | '(' expression ')'
           | forward
           ;

value : INTEGER 
      | FLOAT 
      | STRING
      ;

fn : '{' block '}'
   | '{' fnargpair (',' fnargpair)* '->' block '}'  
   ;

fnargpair : ID CLASSID?
          ;

def : 'def' ID '=' expression
    ;

apply : ID '(' ')'
      | ID '(' expression ( ',' expression )* ')'
      ;

ref : ID ;

cond : 'if' condition=expression 'then' exptrue=expression 'else' expfalse=expression
     ;
 
forward : ID ':' ty=tydef
        ;

tydef : CLASSID | '[' tydef ']' | tydef '->' tydef
      ;

ID : [a-z][a-zA-Z0-9_\-]* ;
CLASSID : [A-Z][a-zA-Z]* ;
INTEGER : [0-9]+ ;
FLOAT : [0-9]* '.' [0-9]+ ;
STRING : '"' ~["]* '"' ;
WS : [ \t\r\n]+ -> skip ;

