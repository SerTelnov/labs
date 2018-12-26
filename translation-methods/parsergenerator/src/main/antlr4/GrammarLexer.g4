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
RETURNS:                'returns';

TERMINAL:               [A-Z] [A-Z_0-9]*;
JNAME_DEF:              [a-z] [A-Za-z0-9]*;
JAVA_CLASS_NAME:        [A-Z] [a-zA-Z0-9]*;

// regex

TOKEN_VALUE:            '\'' ~[\r\n]+ '\'';
REGEX_VALUE:            ('[' ~[\r\n]+ ']' ('*'|'+'|'?'|'!')*)+;
JCODE:                  '{' ~[\r\n]+ '}';
IN_VALUE:               '<' ~[\r\n]+ '>';

// Whitespace and comments

WS:                     [ \t\r\n]+       -> channel(HIDDEN);
COMMENT:                '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:           '//' ~[\r\n]*    -> channel(HIDDEN);
