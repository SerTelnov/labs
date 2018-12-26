package ru.telnov.labs.translationmethods.parsergenerator.utils;

import org.junit.Before;
import org.junit.Test;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.NotTerminal;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Rule;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.Terminal;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        Map<String, Set<String>> expected = new HashMap<>();

        expected.put("E", makeStringSet("LB", "VAR"));
        expected.put("EE", makeStringSet("PLUS", "EPSILON"));
        expected.put("T", makeStringSet("LB", "VAR"));
        expected.put("TT", makeStringSet("MUL", "EPSILON"));
        expected.put("F", makeStringSet("LB", "VAR"));

        assertEquals(expected.size(), first.size());
        first.forEach((name, value) -> {
            Set<String> expectedSet = expected.get(name);
            assertEquals(expectedSet.size(), value.size());
            value.forEach(v -> assertTrue(expectedSet.contains(v.getName())));
        });
    }

    @Test
    public void followTest() {
        Map<String, Set<Terminal>> first = FirstAndFollow.generateFirst(grammar);
        Map<String, Set<Terminal>> follow = FirstAndFollow.generateFollow(grammar, first, "E");
        Map<String, Set<String>> expected = new HashMap<>();

        expected.put("E", makeStringSet("ENDLINE", "RB"));
        expected.put("EE", makeStringSet("ENDLINE", "RB"));
        expected.put("T", makeStringSet("ENDLINE", "PLUS", "RB"));
        expected.put("TT", makeStringSet("ENDLINE", "PLUS", "RB"));
        expected.put("F", makeStringSet("ENDLINE", "PLUS", "RB", "MUL"));

        assertEquals(expected.size(), follow.size());
        follow.forEach((name, value) -> {
            Set<String> expectedSet = expected.get(name);
            assertEquals(expectedSet.size(), value.size());
            value.forEach(v -> assertTrue(expectedSet.contains(v.getName())));
        });
    }

    private static Set<String> makeStringSet(String... strings) {
        return new HashSet<>(Arrays.asList(strings));
    }
}