package ru.telnov.labs.translationmethods.parsergenerator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.NotTerminal;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GrammarParserTest {

    private static TestUtils utils = new TestUtils();

    @Test
    public void helloTest() throws Exception {
        List<NotTerminal> grammar = parse("grammars/hello/Grammar.gr");

        assertEquals(3, grammar.size());

        NotTerminal notTerminal = grammar.get(0);
        assertEquals("start", notTerminal.getName());
        assertEquals(4, notTerminal.getRules().size());
        assertEquals(5, notTerminal.getRules().get(0).getRule().size());
    }

    @Test
    public void bracketTest() throws Exception {
        List<NotTerminal> grammar = parse("grammars/brackets/Grammar.gr");

        assertEquals(1, grammar.size());

        NotTerminal notTerminal = grammar.get(0);
        assertEquals("s", notTerminal.getName());
        assertEquals(2, notTerminal.getRules().size());
        assertEquals(4, notTerminal.getRules().get(0).getRule().size());
        assertEquals(1, notTerminal.getRules().get(1).getRule().size());
    }

    private List<NotTerminal> parse(final String filename) throws IOException {
        CharStream input = utils.getCharStreamFromFile(filename);

        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        GrammarParser parser = new GrammarParser(tokens);
        return parser.start().ntList;
    }
}
