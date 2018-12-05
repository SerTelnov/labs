package ru.telnov.labs.translationmethods.antlr.tokens.condition;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;
import ru.telnov.labs.translationmethods.antlr.tokens.Statement;

import java.util.List;

public class Condition implements Statement {

    private Expression condition;
    private List<Statement> statements;

    public Condition(Expression condition, List<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        builder.append("if (");
        condition.toStringBuilder(builder);
        builder.append(") {\n");

        statements.forEach(st -> st.toStringBuilder(builder));
        builder.append("}\n");
    }
}
