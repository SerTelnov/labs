package ru.telnov.labs.translationmethods.parsergenerator.generator.builders;

public class Field implements JavaToken {

    private Modifier modifier;
    private String type;
    private String name;

    public Field(Modifier modifier, String type, String name) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void build(ClassBuilder classBuilder) {
        classBuilder.newLine(modifier.pattern)
                .append(type)
                .add(name)
                .appendEndLine(";");
    }
}
