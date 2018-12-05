package ru.telnov.labs.translationmethods.antlr.tokens.expressions;

import ru.telnov.labs.translationmethods.antlr.Utils;
import ru.telnov.labs.translationmethods.antlr.tokens.Expression;
import ru.telnov.labs.translationmethods.antlr.tokens.Statement;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Assign implements Statement {

    protected String name;
    protected Expression value;
    protected String valueStr;

    private List<Statement> splitExpression;

    protected Assign(String name) {
        this.name = Utils.generateObfuscateName(name);
        this.splitExpression = Collections.emptyList();
    }

    public Assign(String name, Expression value) {
        this(name);
        Optional<ExpressionSpliter.Value> optionalValue = ExpressionSpliter.split(value);

        if (optionalValue.isPresent()) {
            ExpressionSpliter.Value splitValue = optionalValue.get();
            this.splitExpression = splitValue.statements;
            this.valueStr = splitValue.name;
        } else {
            this.value = value;
        }
    }

    public Assign(String name, Expression value, String op) {
        this(name);
        op = op.substring(0, op.indexOf('='));
        Optional<ExpressionSpliter.Value> optionalValue = ExpressionSpliter.split(value);

        if (optionalValue.isPresent()) {
            ExpressionSpliter.Value splitValue = optionalValue.get();
            this.splitExpression = splitValue.statements;
            this.value = new BinExpression(
                    new Variable(this.name),
                    new Variable(splitValue.name),
                    op);
        } else {
            this.value = new BinExpression(
                    new Variable(this.name),
                    value,
                    op);
        }
    }

    protected void appendMainValue(StringBuilder builder) {
        builder.append(name).append(" = ");
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        splitExpression.forEach(st -> st.toStringBuilder(builder));

        appendMainValue(builder);

        if (value == null) {
            if (valueStr != null) {
                builder.append(valueStr);
            }
        } else {
            value.toStringBuilder(builder);
        }

        builder.append(";\n");
    }
}
