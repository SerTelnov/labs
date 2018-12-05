package ru.telnov.labs.translationmethods.antlr.tokens.condition;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;

public class ConditionExpression implements Expression {

    private Expression exp;

    public ConditionExpression(Expression exp) {
        this.exp = exp;
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        exp.toStringBuilder(builder);
    }
}
