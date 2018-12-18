package ru.telnov.labs.translationmethods.parsergenerator.generator.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clazz implements JavaToken {

    private final String className;

    private List<Field> fields;
    private List<Method> methods;
    private List<Arg> constructorArgs;

    private String imports;

    public Clazz(String name, Arg... args) {
        this.className = name;
        this.constructorArgs = Arrays.asList(args);

        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public String getClassName() {
        return className;
    }

    public void addFields(Field... fields) {
        this.fields.addAll(Arrays.asList(fields));
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    @Override
    public void build(ClassBuilder classBuilder) {
        classBuilder.appendLine(imports);
        classBuilder.appendLine("");

        classBuilder
                .newLine("public class")
                .append(className)
                .appendEndLine("{");

        classBuilder.appendLine("");

        fields.forEach(f -> f.build(classBuilder));

        classBuilder.appendLine("");

        classBuilder.newLine("public")
                .add(className)
                .appendArgs(constructorArgs)
                .add(" ")
                .appendEndLine("{");

        for (Arg arg : constructorArgs) {
            classBuilder.addNewLine("this.")
                    .append(arg.getName())
                    .append("=")
                    .add(arg.getName())
                    .appendEndLine(";");
        }

        classBuilder.appendLine("}");

        classBuilder.appendLine("");

        methods.forEach(m -> m.build(classBuilder));

        classBuilder.appendLine("}");
    }
}
