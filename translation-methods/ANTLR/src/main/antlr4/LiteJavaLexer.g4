lexer grammar LiteJavaLexer;

@lexer::header {
    package ru.telnov.labs.translationmethods.antlr;
}

LPAREN:                 '(';
RPAREN:                 ')';
LBRACE:                 '{';
RBRACE:                 '}';
LBRACK:                 '[';
RBRACK:                 ']';
SEMI:                   ';';
COMMA:                  ',';
DOT:                    '.';
MARK:                   '"';
ARRAY:                  '[]';

ASSIGN:                 '=';
GT:                     '>';
LT:                     '<';
BANG:                   '!';
TILDE:                  '~';
QUESTION:               '?';
COLON:                  ':';
EQUAL:                  '==';
LE:                     '<=';
GE:                     '>=';
NOTEQUAL:               '!=';
AND:                    '&&';
OR:                     '||';

PLUS:                   '+';
MINUS:                  '-';
DIV:                    '/';
MUL:                    '*';


// Key-words

CLASS:                  'class';
STATIC:                 'static';
VOID:                   'void';
MAIN:                   'main';
STRING:                 'String';
SYSTEM:                 'System';
OUT:                    'out';
PRINTLN:                'println';
IF:                     'if';
ELSE:                   'else';
FOR:                    'for';
WHILE:                  'while';
INT:                    'int';
LONG:                   'long';
SHORT:                  'short';

PUBLIC:                 'public';
PROTECTED:              'protected';
PRIVATE:                'private';

// Whitespace and comments

WS:                     [ \t\r\n]+       -> channel(HIDDEN);
COMMENT:                '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:           '//' ~[\r\n]*    -> channel(HIDDEN);


// Operators

UN_OPERATORS:           ('++' | '--');
UN_ASSIGN_OPERATORS:    ('+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' |  '%=' | '<<=' | '>>=' | '>>>=');

DIGIT:                  [0-9] ([0-9_]* [0-9])?;
VARIABLE:               [A-Za-z][A-Za-z0-9]*;