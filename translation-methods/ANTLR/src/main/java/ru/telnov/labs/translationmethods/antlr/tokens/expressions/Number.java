package ru.telnov.labs.translationmethods.antlr.tokens.expressions;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;

public class Number implements Expression {

    private int value;

    public Number(final String valStr) {
        value = Integer.parseInt(valStr);
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append(value);
    }
}
