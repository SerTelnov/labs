package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import java.util.Objects;

abstract class AbstractLexerToken implements LexerToken {

    protected final String name;

    public AbstractLexerToken(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof AbstractLexerToken) {
            AbstractLexerToken token = (AbstractLexerToken) obj;
            return Objects.equals(name, token.name);
        }
        return false;
    }
}
