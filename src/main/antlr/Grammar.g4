grammar Grammar;

file
    : 'module' name=ID imp* block
    ;

imp
    : 'import' ID ('.' ID)+ ('as' alias=ID)?
    ;

block
    : (expression | forward)+
    ;

expression
    : value
    | fn
    | apply
    | objapply
    | ref
    | cond
    | '(' exp=expression ')'
    | list
    | map
    | left=expression binop='==' right=expression
    | left=expression binop=('*' | '/') right=expression
    | left=expression binop=('+' | '-') right=expression
    | klass
    | instantiation
    | objfield
    | defsimple=ID '=' defsimple2=expression
    | defn=ID '(' ')'                            '=' body=expression 
    | defn=ID '(' fnargpair (',' fnargpair)* ')' '=' body=expression 
    ;

value
    : INTEGER
    | FLOAT
    | STRING
    ;

fn
    : '{' block '}'
    | '{' fnargpair (',' fnargpair)* '=>' block '}'
    ;

fnargpair
    : ID tydef?
    ;

apply
    : ID '(' ')'
    | ID '(' expression ( ',' expression )* ')'
    ;

objapply
    : ref '.' apply
    ;

objfield
    : ref '.' ID
    ;

ref
    : ID
    ;

cond
    : 'if' condition=expression 'then' exptrue=expression 'else' expfalse=expression
    ;

forward
    : ID ':' ty=tydef
    ;

tydef
    : CLASSID | ID ('+' tydef)* | tydef '[' tydef (',' tydef)* ']' | tydef '->' tydef | tydef ',' tydef  | '(' tydef ')'
    ;

list
    : '[' expression (',' expression)* ']'
    ;

map
    : '[' mappair (',' mappair)* ']'
    ;

mappair
    : mapkey=expression ':' mapvalue=expression
    ;

klass 
	: 'class' CLASSID '(' (klassvar (',' klassvar)*)? ')' ('is' klassparent (',' klassparent)*)?
	;

klassvar
	: ID ty=tydef
	;

klassparent
	: CLASSID | ID | klassparent '[' klassparent (',' klassparent)* ']' | klassparent ',' klassparent 
	;

instantiation
    : CLASSID '(' (expression (',' expression)*)? ')'
    ;

ID
    : [a-z][a-zA-Z0-9_\-']*
    ;

CLASSID
    : [A-Z][a-zA-Z]*
    ;

INTEGER
    : [0-9]+
    ;

FLOAT
    : [0-9]* '.' [0-9]+
    ;

STRING
    : '"' ~["]* '"'
    ;

WS
    : [ \t\r\n]+ -> skip
    ;
