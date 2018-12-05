package ru.telnov.labs.translationmethods.antlr.tokens.expressions;

import ru.telnov.labs.translationmethods.antlr.Utils;
import ru.telnov.labs.translationmethods.antlr.tokens.Expression;

public class Variable implements Expression {

    private String name;

    public Variable(final String name) {
        this.name = Utils.generateObfuscateName(name);
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append(name);
    }
}
