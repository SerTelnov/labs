package ru.telnov.labs.translationmethods.parsergenerator.generator;

import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.*;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;
import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.telnov.labs.translationmethods.parsergenerator.utils.Constants.TABS;

public final class LexerGenerator {

    private static final String FILENAME = "Lexer";
    private static final String IMPORTS = "import java.util.ArrayList;\n" +
            "import java.util.List;\n" +
            "import java.util.regex.Matcher;\n" +
            "import java.util.regex.Pattern;\n";

    private static final String ADD_LINE = "                tokens.add(new " + Constants.LEXER_TOKEN_CLASS + "(" +
            Constants.ENUM_TYPE_CLASS + ".%s, matcher.group(" + Constants.ENUM_TYPE_CLASS + ".%s.name())));\n";


    public static Clazz generateLexer(List<Terminal> terminals) {
        Clazz lexerClass = new Clazz(FILENAME, new Arg("String", "input"));

        lexerClass.setImports(IMPORTS);
        lexerClass.addFields(new Field(Modifier.PRIVATE, "String", "input"),
                new Field(Modifier.PRIVATE, "int", "index"));
        lexerClass.addMethod(makeLexMethod(terminals));

        return lexerClass;
    }

    private static Method makeLexMethod(List<Terminal> terminals) {
        Method method = new Method(Modifier.PUBLIC, "List<" + Constants.LEXER_TOKEN_CLASS + ">", "lex");

        method.addStatement(Stream.of("List<" + Constants.LEXER_TOKEN_CLASS + "> tokens = new ArrayList<>()",
                "StringBuilder tokenPatternsBuffer = new StringBuilder()",
                "for (" + Constants.ENUM_TYPE_CLASS + " token : " + Constants.ENUM_TYPE_CLASS + ".values()) {",
                "    tokenPatternsBuffer.append(String.format(\"|(?<%s>%s)\", token.name(), token.pattern))",
                "}",
                "Pattern tokenPatterns = Pattern.compile(tokenPatternsBuffer.substring(1))",
                "Matcher matcher = tokenPatterns.matcher(input)",
                "while (matcher.find()) {")
                .map(s -> {
                    StringBuilder sb = new StringBuilder();
                    if (!s.startsWith("List")) {
                        sb.append(TABS[2]);
                    }
                    sb.append(s);

                    if (!s.endsWith("{") && !s.startsWith("}")) {
                        sb.append(';');
                    }

                    if (!s.startsWith("while"))
                        sb.append('\n');

                    return sb.toString();
                })
                .collect(Collectors.joining()));

        method.addStatement(terminals.stream()
                .map(term -> String.format("if (matcher.group(" + Constants.ENUM_TYPE_CLASS + ".%s.name()) != null) {\n" +
                        ADD_LINE +
                        "            }", term.getName(), term.getName(), term.getName()))
                .collect(Collectors.joining(" else "))
        );

        final String endLineName = Constants.END_LINE_TERMINAL.getName();
        method.addStatements("}",
                "tokens.add(new " + Constants.LEXER_TOKEN_CLASS + "(" +
                        Constants.ENUM_TYPE_CLASS + "." + endLineName + ", \"$\"))",
                "return tokens");

        return method;
    }
}
