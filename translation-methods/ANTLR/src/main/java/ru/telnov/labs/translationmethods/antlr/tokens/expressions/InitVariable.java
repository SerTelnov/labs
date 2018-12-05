package ru.telnov.labs.translationmethods.antlr.tokens.expressions;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;

public class InitVariable extends Assign {

    private String type;

    public InitVariable(String type, String name) {
        super(name);
        this.type = type;
    }

    public InitVariable(String type, String name, Expression value) {
        super(name, value);
        this.type = type;
    }

    InitVariable(String type, String name, Expression value, boolean b) {
        this(type, name);
        this.value = value;
    }

    public InitVariable(String type, String name, String valueStr) {
        this(type, name);
        this.valueStr = valueStr;
    }

    @Override
    protected void appendMainValue(StringBuilder builder) {
        builder.append(type)
                .append(' ')
                .append(name)
                .append(" = ");
    }
}
