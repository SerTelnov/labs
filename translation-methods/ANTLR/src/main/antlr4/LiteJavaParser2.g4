parser grammar LiteJavaParser2;

@parser::header {
    package ru.telnov.labs.translationmethods.antlr;
    import ru.telnov.labs.translationmethods.antlr.tokens.*;
    import ru.telnov.labs.translationmethods.antlr.tokens.condition.*;
    import ru.telnov.labs.translationmethods.antlr.tokens.expressions.*;
    import ru.telnov.labs.translationmethods.antlr.tokens.loop.*;
    import ru.telnov.labs.translationmethods.antlr.tokens.expressions.Number;

    import org.antlr.v4.runtime.Token;

    import java.util.Collections;
}

options {
    tokenVocab = LiteJavaLexer;
}

start returns [ClassToken cl]
    : classDeclaration { $cl = $classDeclaration.cl; }
    ;

classDeclaration returns [ClassToken cl]
    : CLASS VARIABLE LBRACE methods RBRACE { $cl = new ClassToken($VARIABLE.text); }
                                           { $cl.setMethods($methods.ms);          }
    ;

methods returns [List<Method> ms]
    : method    { $ms = new ArrayList<>();   }
                { $ms.add($method.m);        }
      (method   { $ms.add($method.m);        }
      )*
    |           { $ms = Collections.emptyList(); }
    ;

method returns [Method m]
    : methodInit LBRACE body RBRACE { $m = $methodInit.m;         }
                                    { $m.setStatements($body.st); }
    ;

methodInit returns [Method m]
    : PUBLIC STATIC VOID MAIN LPAREN STRING ARRAY VARIABLE RPAREN
        { Method.Arg arg = new Method.Arg("String[]", $VARIABLE.text);               }
        { $m  = new Method("public static", "void", "main", arg);                    }
    | modifiers ? VOID VARIABLE LPAREN methodArgs RPAREN
        { $m  = new Method($modifiers.s, "void", $VARIABLE.text, $methodArgs.args);  }
    ;

methodArgs returns [List<Method.Arg> args]
    : methodArg { $args = $methodArg.args;         }
    |           { $args = Collections.emptyList(); }
    ;

methodArg returns [List<Method.Arg> args]
    : numPrim VARIABLE COMMA methodArg
        { $args = new ArrayList<>();                                                     }
        { $args.add(new Method.Arg($numPrim.t, $VARIABLE.text));                         }
        { $args.addAll($methodArg.args);                                                 }
    | numPrim VARIABLE
        { $args = Collections.singletonList(new Method.Arg($numPrim.t, $VARIABLE.text)); }
    ;

body returns [List<Statement> st]
    : statements { $st = $statements.st;          }
    |            { $st = Collections.emptyList(); }
    ;

statements returns [List<Statement> st]
    : statement SEMI { $st = new ArrayList<>();                        }
                     { $st.add($statement.s);                          }
     statements      { $st.addAll($statements.st);                     }
    | statement SEMI { $st = Collections.singletonList($statement.s);  }
    | loop           { $st = Collections.singletonList($loop.lp);      }
    | condition      { $st = Collections.singletonList($condition.cd); }
    ;

statement returns [Statement s]
    : initVariable { $s = $initVariable.v; }
    | expr         { $s = $expr.s;       }
    ;

////// loop

loop returns [Loop lp]
    : loopInit LBRACE body RBRACE
        { $lp = $loopInit.lp;               }
        { $lp.setStatements($body.st);  }
    ;

loopInit returns [Loop lp]
    : FOR LPAREN forInit SEMI conditionInit SEMI forStep RPAREN
            { $lp = new ForLoop($forInit.v, $conditionInit.exp, $forStep.exp);  }
    | WHILE LPAREN conditionInit RPAREN
            { $lp = new WhileLoop($conditionInit.exp);                          }
    ;

forInit returns [InitVariable v]
    : INT VARIABLE ASSIGN initExpr
        { $v = new InitVariable("int", $VARIABLE.text, $initExpr.exp); }
    ;


forStep returns [Expression exp]
    : VARIABLE UN_OPERATORS { $exp = new UnExpression($VARIABLE.text, $UN_OPERATORS.text); }
    ;

// conditions

condition returns [Condition cd]
    : IF LPAREN conditionInit RPAREN LBRACE body RBRACE
        { $cd = new Condition($conditionInit.exp, $body.st); }
    ;

conditionInit returns [Expression exp]
    : atom
        { $exp = $atom.e; }
    | atom boolBinOp atom1
        { $exp = new BinConditionExpression(
                $atom.e,
                $atom1.e,
                $boolBinOp.op); }
    ;

atom1 returns [Expression e]
    : atom
        { $e = $atom.e; }
    ;

atom returns [Expression e]
    : VARIABLE    { $e = new Variable($VARIABLE.text); }
    | initExpr    { $e = $initExpr.exp;                }
    ;

// expression

expr returns [Statement s]
    : VARIABLE ASSIGN initExpr
        { $s = new Assign($VARIABLE.text, $initExpr.exp);                            }
    | VARIABLE UN_ASSIGN_OPERATORS initExpr
        { $s = new Assign($VARIABLE.text, $initExpr.exp, $UN_ASSIGN_OPERATORS.text); }
    ;

initExpr returns [Expression exp]
    : mulExpr plusMinus initExpr
        { $exp = new BinExpression($mulExpr.exp, $initExpr.exp, $plusMinus.op); }
    | mulExpr
        { $exp = $mulExpr.exp; }
    ;

plusMinus returns [String op]
    : PLUS  { $op = "+"; }
    | MINUS { $op = "-"; }
    ;

mulExpr returns [Expression exp]
    : var mulDiv mulExpr
        { $exp = new BinExpression($var.exp, $mulExpr.exp, $mulDiv.op); }
    | var
        { $exp = $var.exp;                                              }
    ;

mulDiv returns [String op]
    : MUL { $op = "*"; }
    | DIV { $op = "/"; }
    ;

var returns [Expression exp]
    : LPAREN initExpr RPAREN
        { $exp = $initExpr.exp;                }
    | VARIABLE
        { $exp = new Variable($VARIABLE.text); }
    | DIGIT
        { $exp = new Number($DIGIT.text);      }
    ;

initVariable returns [InitVariable v]
    : numPrim VARIABLE
        { $v = new InitVariable($numPrim.t, $VARIABLE.text);                }
    | numPrim VARIABLE ASSIGN initExpr
        { $v = new InitVariable($numPrim.t, $VARIABLE.text, $initExpr.exp); }
    | STRING VARIABLE
        { $v = new InitVariable("String", $VARIABLE.text);                  }
    | STRING VARIABLE ASSIGN initString
        { $v = new InitVariable("String", $VARIABLE.text, $initString.s);   }
    ;

initString returns [String s]
    : MARK MARK            { $s = "\"\"";                       }
    | MARK VARIABLE MARK   { $s = "\"" + $VARIABLE.text + "\""; }
    ;


//// utils

numPrim returns [String t]
    : INT   { $t = "int";  }
    | LONG  { $t = "long"; }
    ;

boolBinOp returns [String op]
    : GT        { $op = ">";  }
    | LT        { $op = "<";  }
    | EQUAL     { $op = "=="; }
    | LE        { $op = "<="; }
    | GE        { $op = ">="; }
    | NOTEQUAL  { $op = "!="; }
    | AND       { $op = "&&"; }
    | OR        { $op = "||"; }
    ;

modifiers returns [String s]
    : PUBLIC    { $s = "public";    }
    | PRIVATE   { $s = "private";   }
    | PROTECTED { $s = "protected"; }
    ;
