package ru.telnov.labs.translationmethods.parsergenerator.utils;

import org.junit.Before;
import org.junit.Test;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.LexerToken;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.NotTerminal;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Rule;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FirstAndFollowTest {

    private Map<String, List<Rule>> grammar;

    @Before
    public void init() {
        grammar = new HashMap<>();
        NotTerminal E = new NotTerminal("E");
        NotTerminal EE = new NotTerminal("EE");
        NotTerminal T = new NotTerminal("T");
        NotTerminal TT = new NotTerminal("TT");
        NotTerminal F = new NotTerminal("F");

        E.addRule(new Rule(T, EE));
        EE.addRule(new Rule(new Terminal("PLUS", "+"), T, EE));
        EE.addRule(Constants.EPSILON_RULE);

        T.addRule(new Rule(F, TT));
        TT.addRule(new Rule(new Terminal("MUL", "*"), F, TT));
        TT.addRule(Constants.EPSILON_RULE);

        F.addRules(new Rule(new Terminal("VAR", "value")),
                new Rule(new Terminal("LB", "("), E, new Terminal("RB", ")")));

        grammar.put("E", E.getRules());
        grammar.put("EE", EE.getRules());
        grammar.put("T", T.getRules());
        grammar.put("TT", TT.getRules());
        grammar.put("F", F.getRules());
    }

    @Test
    public void firstTest() {
        Map<String, Set<Terminal>> first = FirstAndFollow.generateFirst(grammar);
        Map<String, String> expected = new HashMap<>();

        expected.put("E", "{LB, VAR}");
        expected.put("EE", "{EMPTY, PLUS}");
        expected.put("T", "{LB, VAR}");
        expected.put("TT", "{MUL, EMPTY}");
        expected.put("F", "{LB, VAR}");

        first.forEach((name, value) -> {
            String actual = value.stream()
                    .map(LexerToken::getName)
                    .collect(Collectors.joining(", ", "{", "}"));
            assertEquals(expected.get(name), actual);
        });
    }

    @Test
    public void followTest() {
        Map<String, Set<Terminal>> first = FirstAndFollow.generateFirst(grammar);
        Map<String, Set<Terminal>> follow = FirstAndFollow.generateFollow(grammar, first, "E");
        Map<String, String> expected = new HashMap<>();

        expected.put("E", "{ENDLINE, RB}");
        expected.put("EE", "{ENDLINE, RB}");
        expected.put("T", "{ENDLINE, PLUS, RB}");
        expected.put("TT", "{ENDLINE, PLUS, RB}");
        expected.put("F", "{MUL, ENDLINE, PLUS, RB}");

        follow.forEach((name, value) -> {
            String actual = value.stream()
                    .map(LexerToken::getName)
                    .collect(Collectors.joining(", ", "{", "}"));
            assertEquals("Not terminal: " + name, expected.get(name), actual);
        });
    }
}