lexer grammar GrammarLexer;

@lexer::header {
    package ru.telnov.labs.translationmethods.parsergenerator;
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
DOUBLE_DOT:             ':';
OVER_RULE:              '|';

TERMINAL:               [A-Z] [A-Z_0-9]*;
NOT_TERMINAL:           [a-z] [A-Za-z0-9]*;

TOKEN_VALUE:            '\'' ~[\r\n]+ '\'';
REGEX_VALUE:            ('[' ~[\r\n]+ ']' ('*'|'+'|'?')?)+;

// Whitespace and comments

WS:                     [ \t\r\n]+       -> channel(HIDDEN);
COMMENT:                '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:           '//' ~[\r\n]*    -> channel(HIDDEN);
