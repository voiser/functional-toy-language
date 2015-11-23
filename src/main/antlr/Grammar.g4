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
           | binary
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

tydef : CLASSID | ID | tydef '[' tydef ']' | tydef '->' tydef
      ;

binary : xleft=binexp op=BINOP right=binexp
       | bleft=binary op=BINOP right=binexp
       ;

binexp : value 
       | apply
       | ref
       | '(' binary ')'
       ;

ID : [a-z][a-zA-Z0-9_\-]* ;
CLASSID : [A-Z][a-zA-Z]* ;
INTEGER : [0-9]+ ;
FLOAT : [0-9]* '.' [0-9]+ ;
STRING : '"' ~["]* '"' ;
BINOP : '+' | '-' | '*' | '/' | '==' ;
WS : [ \t\r\n]+ -> skip ;

