package ru.telnov.labs.translationmethods.antlr.tokens.expressions;

import ru.telnov.labs.translationmethods.antlr.Utils;
import ru.telnov.labs.translationmethods.antlr.tokens.Expression;

public class UnExpression implements Expression {

    private String variable;
    private String operation;

    public UnExpression(String variable, String operation) {
        this.variable = Utils.generateObfuscateName(variable);
        this.operation = operation;
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append(variable).append(operation);
    }
}
