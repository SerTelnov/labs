package ru.telnov.labs.translationmethods.antlr.tokens;

import ru.telnov.labs.translationmethods.antlr.Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ClassToken implements Token {

    private List<Method> methods;
    private String name;
    private String modifier;

    public ClassToken(final String name) {
        this(name, "");
    }

    public ClassToken(final String name, final String modifier) {
        this.name = Utils.generateObfuscateName(name);
        this.modifier = modifier;
        this.methods = Collections.emptyList();
    }

    public String getClassName() {
        return name;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        toStringBuilder(builder);

        return builder.toString();
    }

    @Override
    public void toStringBuilder(StringBuilder builder) {
        Utils.append(builder, modifier)
                .append("class ")
                .append(name)
                .append(' ')
                .append('{')
                .append('\n');

        methods.forEach(m -> m.toStringBuilder(builder));

        builder.append('}');
    }

    public void printClass() throws IOException {
        printPath(Paths.get(String.format("%s.java", name)));
    }

    public void printPath(Path path) throws IOException {
        String classString = this.toString();
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(classString);
        }
    }
}
