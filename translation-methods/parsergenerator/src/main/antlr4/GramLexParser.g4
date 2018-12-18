parser grammar GramLexParser;

@parser::header {
    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;
    import java.util.Collections;
}

options {
    tokenVocab = GrammarLexer;
}

parseTokens returns [List<Terminal> trmls]
    : terminal  { $trmls = new ArrayList<>();       }
                { $trmls.add($terminal.t);          }
      (terminal { $trmls.add($terminal.t);          })*
    |           { $trmls = Collections.emptyList(); }
    ;

terminal returns [Terminal t]
    : TERMINAL DOUBLE_DOT value SEMI { $t = new Terminal($TERMINAL.text, $value.v); }
    ;

value returns [String v]
    : TOKEN_VALUE   { $v = $TOKEN_VALUE.text;    }
    | REGEX_VALUE   { $v = $REGEX_VALUE.text;    }
    ;