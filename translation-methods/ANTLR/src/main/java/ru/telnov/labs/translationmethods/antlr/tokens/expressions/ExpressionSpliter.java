package ru.telnov.labs.translationmethods.antlr.tokens.expressions;

import ru.telnov.labs.translationmethods.antlr.Utils;
import ru.telnov.labs.translationmethods.antlr.tokens.Expression;
import ru.telnov.labs.translationmethods.antlr.tokens.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpressionSpliter {

    public static Optional<Value> split(Expression exp) {
        if (exp instanceof BinExpression) {
            BinExpression binExp = (BinExpression) exp;
            List<Statement> statements = new ArrayList<>(3);

            String aName = Utils.generateName();
            String bName = Utils.generateName();
            String rName = Utils.generateName();

            statements.add(new InitVariable("int", aName, binExp.getA()));
            statements.add(new InitVariable("int", bName, binExp.getB()));
            statements.add(new InitVariable("int", rName,
                    new BinExpression(
                            new Variable(aName),
                            new Variable(bName),
                            binExp.getSign()
                    ), false));
            return Optional.of(new Value(rName, statements));
        } else {
            return Optional.empty();
        }
    }

    public static class Value {

        public String name;
        public List<Statement> statements;

        public Value(String name, List<Statement> list) {
            this.name = name;
            this.statements = list;
        }
    }
}
