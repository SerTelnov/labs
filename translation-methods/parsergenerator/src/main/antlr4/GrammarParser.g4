parser grammar GrammarParser;

@parser::header {
    package ru.telnov.labs.translationmethods.parsergenerator;

    import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.Arg;
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
    : JNAME_DEF initRules
        { $nt = new NotTerminal($JNAME_DEF.text, $initRules.rs);                            }
    | JNAME_DEF RETURNS LPAREN arg RPAREN initRules
        { $nt = new AttributeNotTerminal($JNAME_DEF.text, $initRules.rs, $arg.a);           }
    | JNAME_DEF LPAREN args RPAREN initRules
        { $nt = new AttributeNotTerminal($JNAME_DEF.text, $initRules.rs, $args.as);         }
    | JNAME_DEF LPAREN args RPAREN RETURNS LPAREN arg RPAREN initRules
        { $nt = new AttributeNotTerminal($JNAME_DEF.text, $initRules.rs, $args.as, $arg.a); }
    ;

args returns [List<Arg> as]
    : arg   { $as = new ArrayList<>();  }
            { $as.add($arg.a);          }
     (COMMA arg   { $as.add($arg.a);    })*
    ;

arg returns [Arg a]
    : type name { $a = new Arg($type.s, $name.s);   }
    ;

type returns [String s]
    : JAVA_CLASS_NAME   { $s = $JAVA_CLASS_NAME.text;   }
    | JNAME_DEF         { $s = $JNAME_DEF.text;         }
    ;

name returns [String s]
    : JNAME_DEF  { $s = $JNAME_DEF.text;  }
    ;

initRules returns [List<Rule> rs]
    : DOUBLE_DOT rules SEMI { $rs = $rules.rs;   }
    ;

rules returns [List<Rule> rs]
    : rrule           { $rs = new ArrayList<>();         }
                      { $rs.add($rrule.r);               }
     (OVER_RULE rrule { $rs.add($rrule.r);               })*
    |                 { $rs = Collections.emptyList();   }
    ;

rrule returns [Rule r]
    : nextToken     { List<LexerValue> tokens = new ArrayList<>();  }
                    { tokens.add($nextToken.v);                     }
     (nextToken     { tokens.add($nextToken.v);                     })*
                    { $r = new Rule(tokens);                        }
    |               { $r = new Rule();                              }
    ;

nextToken returns [LexerValue v]
    : TERMINAL
        { $v = new Terminal($TERMINAL.text);                        }
    | JNAME_DEF
        { $v = new UnknownToken($JNAME_DEF.text);                   }
    | JNAME_DEF IN_VALUE
        { $v = new UnknownToken($JNAME_DEF.text, $IN_VALUE.text);   }
    | JCODE
        { $v = new JCode($JCODE.text);                              }
    ;
