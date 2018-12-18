package ru.telnov.labs.translationmethods.parsergenerator.generator.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Method implements JavaToken {

    private Modifier modifier;
    private String type;
    private String name;
    private List<String> body;
    private List<Arg> args;


    public Method(Modifier modifier, String type, String name, Arg... args) {
        this.modifier = modifier;
        this.type = type;
        this.name = name;
        this.args = Arrays.asList(args);
        this.body = new ArrayList<>();
    }

    public void addStatement(String statement) {
        body.add(statement);
    }

    public void addStatements(String... statements) {
        this.body.addAll(Arrays.asList(statements));
    }

    @Override
    public void build(ClassBuilder classBuilder) {
        classBuilder.newLine(modifier.pattern)
                .append(type)
                .add(name)
                .appendArgs(args)
                .appendEndLine(" {");

        body.stream()
                .filter(s -> !s.isEmpty())
                .forEach(statement -> {
                    if (statement.endsWith("}") || statement.endsWith("{")) {
                        classBuilder.appendLine(statement);
                    } else {
                        classBuilder.addNewLine(statement)
                                .appendEndLine(";");
                    }
                });
        classBuilder.appendLine("}\n");
    }
}
