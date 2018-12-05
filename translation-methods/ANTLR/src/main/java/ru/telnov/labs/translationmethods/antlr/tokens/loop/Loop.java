package ru.telnov.labs.translationmethods.antlr.tokens.loop;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;
import ru.telnov.labs.translationmethods.antlr.tokens.Statement;
import ru.telnov.labs.translationmethods.antlr.tokens.expressions.InitVariable;

import java.util.Collections;
import java.util.List;

public abstract class Loop implements Statement {

    private List<Statement> statements;
    protected Expression condition;

    public Loop() {
        this.statements = Collections.emptyList();
    }

    public Loop(Expression condition) {
        this.condition = condition;
        this.statements = Collections.emptyList();
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    protected abstract void appendLoopName(StringBuilder builder);

    protected void appendLoopInit(StringBuilder builder) {
        condition.toStringBuilder(builder);
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        appendLoopName(builder);

        builder.append(' ').append('(');
        appendLoopInit(builder);
        builder.append(") {\n");

        statements.forEach(st -> st.toStringBuilder(builder));
        builder.append("}\n");
    }
}
