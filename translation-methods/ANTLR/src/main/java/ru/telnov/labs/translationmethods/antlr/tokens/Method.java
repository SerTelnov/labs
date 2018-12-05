package ru.telnov.labs.translationmethods.antlr.tokens;

import ru.telnov.labs.translationmethods.antlr.Utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Method implements Token {

    private String modifier;
    private String returnType;
    private String name;
    private List<Arg> args;

    private List<Statement> statements;


    public Method(final String type, final String name) {
        this("", type, name);
    }

    public Method(final String modifier, final String type, final String name, Arg args) {
        this(modifier, type, name);
        this.args = Collections.singletonList(args);
    }

    public Method(final String modifier, final String type, final String name, List<Arg> args) {
        this(modifier, type, name);
        this.args = args;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public Method(final String modifier, final String type, final String name) {
        this.modifier = modifier;
        this.returnType = type;

        if (!name.equals("main")) {
            this.name = Utils.generateObfuscateName(name);
        } else {
            this.name = name;
        }

        this.statements = Collections.emptyList();
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        Utils.append(builder, modifier)
                .append(returnType)
                .append(' ')
                .append(name);

        builder.append('(');
        builder.append(args.stream()
            .map(arg -> arg.type + " " + arg.argName)
            .collect(Collectors.joining(", ")));
        builder.append(") {\n");

        statements.forEach(statement -> statement.toStringBuilder(builder));

        builder.append("}\n");
    }

    public static class Arg {

        public String type;
        public String argName;

        public Arg(String type, String argName) {
            this.type = type;
            this.argName = Utils.generateObfuscateName(argName);
        }
    }
}
