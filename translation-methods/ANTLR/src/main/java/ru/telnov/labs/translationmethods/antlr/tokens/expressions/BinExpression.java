package ru.telnov.labs.translationmethods.antlr.tokens.expressions;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;

public class BinExpression implements Expression {

    private Expression a;
    private Expression b;

    private String sign;

    public BinExpression(Expression a, Expression b, String sign) {
        this.a = a;
        this.b = b;
        this.sign = sign;
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        a.toStringBuilder(builder);
        builder.append(' ')
            .append(sign)
            .append(' ');
        b.toStringBuilder(builder);
    }

    public Expression getA() {
        return a;
    }

    public Expression getB() {
        return b;
    }

    public String getSign() {
        return sign;
    }
}
