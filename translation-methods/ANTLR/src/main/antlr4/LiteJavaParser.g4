parser grammar LiteJavaParser;

@parser::header {
    package ru.telnov.labs.translationmethods.antlr;
}

options {
    tokenVocab = LiteJavaLexer;
}

start
    : PUBLIC ? classDeclaration
    ;

// Class declaration

classDeclaration
    : CLASS className LBRACE (method)* RBRACE
    ;

// method declaration

method
    : methodInzialization LBRACE body RBRACE
    ;

methodInzialization
    : PUBLIC STATIC VOID MAIN LPAREN STRING ARRAY initArg RPAREN
    | modifiers? VOID initArg LPAREN methodArgs RPAREN
    ;

methodArgs
    : methodArg
    |
    ;

methodArg
    : variableType initArg COMMA methodArg
    | variableType initArg
    ;

// statements

body
    : statements
    |
    ;

statements
    : statement SEMI statements
    | statement SEMI
    | loop
    | conditions
    ;

statement
    : initVariable
    | print
    | expression
    ;

// conditions

conditions
    : initIf
    | initIf ELSE initIf
    | initIf ELSE LBRACE body RBRACE
    ;

initIf
    : IF LPAREN condition RPAREN LBRACE body RBRACE
    ;

condition
    : atom
    | atom boolBinOperators atom
    | condition boolBinOperators condition
    ;

atom
    : VARIABLE
    | BANG VARIABLE
    | initExpression
    ;

// print

print
    : SYSTEM DOT OUT DOT PRINTLN LPAREN value RPAREN
    ;

value
    : DIGIT
    | argName
    | MARK MARK
    | MARK VARIABLE MARK
    |
    ;


// loop

loop
    : FOR LPAREN forInit SEMI forCondition SEMI forStep RPAREN LBRACE body RBRACE
    ;

forInit
    : INT initArg ASSIGN initExpression
    ;

forCondition
    : condition
    ;

forStep
    : argName UN_OPERATORS
    ;

// expression

expression
    : argName ASSIGN initExpression
    | argName UN_ASSIGN_OPERATORS initExpression
    ;

initExpression
    : mulExpression plusMinus initExpression
    | mulExpression
    ;

plusMinus
    : PLUS
    | MINUS
    ;

mulExpression
    : var mulDiv mulExpression
    | var
    ;

mulDiv
    : MUL
    | DIV
    ;

var
    : MINUS initExpression
    | LPAREN initExpression RPAREN
    | argName
    | DIGIT
    ;

initVariable
    : numPrimitive initArg
    | numPrimitive initArg ASSIGN initExpression
    | STRING initArg
    | STRING initArg ASSIGN initString
    ;

initString
    : MARK MARK
    | MARK VARIABLE MARK
    | argName
    ;


//// utils

variableType
    : numPrimitive
    | STRING
    ;

numPrimitive
    : INT
    | LONG
    | SHORT
    ;

initArg
    : VARIABLE
    ;

argName
    : VARIABLE
    ;

className
    : VARIABLE
    ;

boolBinOperators
    : GT
    | LT
    | EQUAL
    | LE
    | GE
    | NOTEQUAL
    | AND
    | OR
    ;

modifiers
    : PUBLIC
    | PRIVATE
    | PROTECTED
    ;
