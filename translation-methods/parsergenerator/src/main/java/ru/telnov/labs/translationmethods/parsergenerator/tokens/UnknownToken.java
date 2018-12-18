package ru.telnov.labs.translationmethods.parsergenerator.tokens;

public class UnknownToken extends AbstractLexerToken {

    private String inValue;

    public UnknownToken(String name) {
        super(name);
    }

    public UnknownToken(String name, String inValue) {
        super(name);
        this.inValue = inValue.substring(1, inValue.length() - 1);
    }

    public boolean hasInValues() {
        return inValue != null;
    }

    public String getInValue() {
        return inValue;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.UNKNOWN_TOKEN;
    }
}
