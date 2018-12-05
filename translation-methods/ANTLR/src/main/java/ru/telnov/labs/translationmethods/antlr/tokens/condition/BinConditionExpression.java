package ru.telnov.labs.translationmethods.antlr.tokens.condition;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;

public class BinConditionExpression implements Expression {

    private Expression a;
    private Expression b;

    private String op;

    public BinConditionExpression(Expression a,
                                  Expression b,
                                  String operator) {
        this.a = a;
        this.b = b;
        this.op = operator;
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        a.toStringBuilder(builder);
        builder.append(' ')
                .append(op);
        b.toStringBuilder(builder);
    }
}
