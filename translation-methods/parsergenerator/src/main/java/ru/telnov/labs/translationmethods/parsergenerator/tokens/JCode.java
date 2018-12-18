package ru.telnov.labs.translationmethods.parsergenerator.tokens;

public class JCode implements LexerValue {

    private final String code;

    public JCode(String code) {
        this.code = code.substring(1, code.length() - 1);
    }

    @Override
    public boolean isCode() {
        return true;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (null == obj) {
            return false;
        }

        if (obj instanceof JCode) {
            JCode other = (JCode) obj;
            return this.code.equals(other.code);
        }

        return false;
    }
}
