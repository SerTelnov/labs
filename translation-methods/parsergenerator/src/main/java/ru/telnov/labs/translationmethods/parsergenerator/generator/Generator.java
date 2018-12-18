package ru.telnov.labs.translationmethods.parsergenerator.generator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ru.telnov.labs.translationmethods.parsergenerator.GramLexParser;
import ru.telnov.labs.translationmethods.parsergenerator.GrammarLexer;
import ru.telnov.labs.translationmethods.parsergenerator.GrammarParser;
import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.ClassBuilder;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.LexerToken;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.NotTerminal;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Rule;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;
import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;
import ru.telnov.labs.translationmethods.parsergenerator.utils.FirstAndFollow;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class Generator {

    private ClassBuilder classBuilder;

    public Generator() {
        this("");
    }

    public Generator(final String packageName) {
        this.classBuilder = new ClassBuilder(packageName);
    }

    public void generate(String grammarFile, String lexerFile) throws IOException {
        CharStream lexerStream = getFileStream(lexerFile);
        CharStream grammarStream = getFileStream(grammarFile);

        generate(grammarStream, lexerStream);
    }

    public void generate(CharStream grammarStream, CharStream lexerStream) {
        List<Terminal> terminals = generateLexer(lexerStream);
        classBuilder.buildEnum(terminals);
        classBuilder.buildHelperClass(Constants.TREE_NODE);
        classBuilder.buildHelperClass(Constants.LEXER_TOKEN_CLASS);
        classBuilder.buildHelperClass(Constants.TREE_VALUE_NODE);

        generateParser(grammarStream, terminals);
    }

    private List<Terminal> generateLexer(CharStream lexerStream) {
        GrammarLexer lexer = new GrammarLexer(lexerStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        GramLexParser parser = new GramLexParser(tokens);
        List<Terminal> terminals = parser.parseTokens().trmls;

        classBuilder.buildClass(LexerGenerator.generateLexer(terminals));

        return terminals;
    }

    private void generateParser(CharStream grammarStream, List<Terminal> terminals) {
        GrammarLexer lexer = new GrammarLexer(grammarStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        GrammarParser parser = new GrammarParser(tokens);
        List<NotTerminal> notTerminals = parser.start().ntList;

        refactorRules(notTerminals, terminals);

        Map<String, List<Rule>> grammar = FirstAndFollow.removeUselessSymbols(notTerminals);
        Map<String, Set<Terminal>> first = FirstAndFollow.generateFirst(grammar);
        Map<String, Set<Terminal>> follow = FirstAndFollow.generateFollow(grammar, first, notTerminals.get(0).getName());

        classBuilder.buildClass(ParserGenerator.generateParser(first, follow, notTerminals, toMap(notTerminals)));
    }

    private void refactorRules(List<NotTerminal> notTerminals, List<Terminal> terminals) {
        Map<String, NotTerminal> notTerminalsMap = toMap(notTerminals);
        Map<String, Terminal> terminalsMap = toMap(terminals);

        for (NotTerminal notTerminal : notTerminals) {
            for (Rule rule : notTerminal.getRules()) {
                rule.removeUnknownToken(notTerminalsMap, terminalsMap);
            }
        }
    }

    private <E extends LexerToken> Map<String, E> toMap(List<? extends E> tokens) {
        return tokens.stream()
                .collect(Collectors.toMap(LexerToken::getName, (token) -> token));
    }

    private CharStream getFileStream(String fileName) throws IOException {
        return CharStreams.fromStream(
                this.getClass().getClassLoader().getResourceAsStream(fileName));
    }
}
