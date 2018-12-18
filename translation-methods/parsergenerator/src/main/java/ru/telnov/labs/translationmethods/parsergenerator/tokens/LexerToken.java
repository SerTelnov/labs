package ru.telnov.labs.translationmethods.parsergenerator.tokens;

public interface LexerToken {

    boolean isTerminal();

    String getName();
}
