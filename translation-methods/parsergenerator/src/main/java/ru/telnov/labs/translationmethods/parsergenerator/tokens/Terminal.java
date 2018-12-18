package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.util.Objects;

public class Terminal extends AbstractLexerToken {

    private final String value;

    private boolean isRegex;

    public Terminal() {
        super("EPSILON");
        this.value = Constants.EPSILON;
    }

    public Terminal(String name) {
        super(name);
        this.value = "";
    }

    public Terminal(String name, String value) {
        super(name);

        if (value.startsWith("'")) {
            isRegex = false;
            this.value = value.substring(1, value.length() - 1);
        } else {
            isRegex = true;
            this.value = value.replace(" ", "");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!super.equals(obj)) {
            return false;
        }

        if (obj instanceof Terminal) {
            Terminal other = (Terminal) obj;
            return Objects.equals(value, other.value);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
