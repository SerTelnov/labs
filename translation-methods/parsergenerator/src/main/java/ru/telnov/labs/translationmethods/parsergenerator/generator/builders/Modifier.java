package ru.telnov.labs.translationmethods.parsergenerator.generator.builders;

public enum Modifier {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected");

    public final String pattern;

    private Modifier(String s) {
        this.pattern = s;
    }
}
