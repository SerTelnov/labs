parser grammar GrammarParser;

@parser::header {
    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.tokens.*;

    import java.util.Collections;
}

options {
    tokenVocab = GrammarLexer;
}

start returns [List<NotTerminal> ntList]
    : notTerminal   { $ntList = new ArrayList<>();  }
                    { $ntList.add($notTerminal.nt); }
     (notTerminal   { $ntList.add($notTerminal.nt); })*
    ;

notTerminal returns [NotTerminal nt]
    : NOT_TERMINAL DOUBLE_DOT initRules SEMI
        { $nt = new NotTerminal($NOT_TERMINAL.text, $initRules.rules); }
    ;

initRules returns [List<Rule> rules]
    : rrule           { $rules = new ArrayList<>();         }
                      { $rules.add($rrule.r);               }
     (OVER_RULE rrule { $rules.add($rrule.r);               })*
    |                 { $rules = Collections.emptyList();   }
    ;

rrule returns [Rule r]
    : nextToken     { List<LexerToken> tokens = new ArrayList<>();  }
                    { tokens.add($nextToken.t);                     }
     (nextToken     { tokens.add($nextToken.t);                     })*
                    { $r = new Rule(tokens);                        }
    |               { $r = new Rule();                              }
    ;

nextToken returns [LexerToken t]
    : TERMINAL      { $t = new Terminal($TERMINAL.text);            }
    | NOT_TERMINAL  { $t = new UnknownToken($NOT_TERMINAL.text);    }
    ;
