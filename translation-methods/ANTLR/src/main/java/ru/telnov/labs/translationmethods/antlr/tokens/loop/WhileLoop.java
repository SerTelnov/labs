package ru.telnov.labs.translationmethods.antlr.tokens.loop;

import ru.telnov.labs.translationmethods.antlr.tokens.Expression;
import ru.telnov.labs.translationmethods.antlr.tokens.expressions.InitVariable;

import java.util.Collections;

public class WhileLoop extends Loop {

    public WhileLoop(Expression condition) {
        super(condition);
    }

    @Override
    protected void appendLoopName(StringBuilder builder) {
        builder.append("while");
    }
}
