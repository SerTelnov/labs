package ru.telnov.labs.translationmethods.parsergenerator.tokens;

public class UnknownToken extends AbstractLexerToken {

    public UnknownToken(String name) {
        super(name);
    }

    @Override
    public boolean isTerminal() {
        return false;
    }
}
