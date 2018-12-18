package ru.telnov.labs.translationmethods.parsergenerator.tokens;

public interface LexerToken extends LexerValue {

    TokenType getTokenType();

    String getName();

    @Override
    default boolean isCode() {
        return false;
    }

    default boolean isTerminal() {
        return getTokenType() == TokenType.TERMINAL;
    }
}
