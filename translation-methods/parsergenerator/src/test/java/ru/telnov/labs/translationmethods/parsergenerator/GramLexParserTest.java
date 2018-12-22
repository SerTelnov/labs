package ru.telnov.labs.translationmethods.parsergenerator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GramLexParserTest {

    private static TestUtils testUtils = new TestUtils();

    @Test
    public void helloTest() throws IOException {
        String[] expected = {"HELLO", "WORLD", "COMMA", "MARK", "NAME", "DIGITS"};
        test("grammars/hello/Tokens.gr", expected);
    }

    private void test(String filename, String[] expectedTerminals) throws IOException {
        CharStream input = testUtils.getCharStreamFromFile(filename);

        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        GramLexParser parser = new GramLexParser(tokens);
        List<Terminal> terminals = parser.parseTokens().trmls;

        assertEquals(expectedTerminals.length, terminals.size());
        for (String s : expectedTerminals) {
            List<Terminal> filterList = terminals.stream()
                    .filter(it -> it.getName().equals(s))
                    .collect(Collectors.toList());

            assertEquals(1, filterList.size());
            Terminal terminal = filterList.get(0);
            assertNotNull(terminal.getValue());
        }
    }
}
