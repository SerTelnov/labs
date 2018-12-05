package ru.telnov.labs.translationmethods.antlr.tokens.loop;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;
import ru.telnov.labs.translationmethods.antlr.tokens.expressions.InitVariable;

public class ForLoop extends Loop {

    private InitVariable variable;
    private Expression step;

    public ForLoop(InitVariable variable, Expression condition, Expression step) {
        super(condition);
        this.variable = variable;
        this.condition = condition;
        this.step = step;
    }

    @Override
    protected void appendLoopName(StringBuilder builder) {
        builder.append("for");
    }

    @Override
    protected void appendLoopInit(StringBuilder builder) {
        variable.toStringBuilder(builder);
        super.appendLoopInit(builder);
        builder.append("; ");
        step.toStringBuilder(builder);
    }
}
