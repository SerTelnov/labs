package ru.telnov.labs.translationmethods.parsergenerator.tokens;

public class JCode implements LexerValue {

    private final String code;

    public JCode(String code) {
        this.code = trimString(code);
    }

    private String trimString(String s) {
        int left = 1;
        while (left < s.length() && Character.isWhitespace(s.charAt(left))) {
            left++;
        }

        int right = s.length() - 2;
        while (right > left && Character.isWhitespace(s.charAt(right))) {
            right--;
        }

        return s.substring(left, right + 1);
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
