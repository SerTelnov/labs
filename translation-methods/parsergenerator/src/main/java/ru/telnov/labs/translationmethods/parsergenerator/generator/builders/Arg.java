package ru.telnov.labs.translationmethods.parsergenerator.generator.builders;

public class Arg {

    private String type;
    private String name;

    public Arg(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
