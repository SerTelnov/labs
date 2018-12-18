package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class Rule {

    private List<LexerToken> rule;
    private Set<Terminal> terminals;
    private Set<NotTerminal> notTerminals;

    private Set<String> names;

    public Rule() {
        this(Collections.singletonList(Constants.EPSILON_TERMINAL));
    }

    public Rule(LexerToken... tokens) {
        this(Arrays.asList(tokens));
    }

    public Rule(List<LexerToken> tokens) {
        this.rule = Collections.unmodifiableList(tokens);
        this.names = rule.stream()
                .map(LexerToken::getName)
                .collect(Collectors.toSet());
    }


    public List<LexerToken> getRule() {
        return rule;
    }

    public LexerToken first() {
        return rule.get(0);
    }

    public Set<Terminal> getTerminals() {
        if (terminals == null) {
            terminals = rule.stream()
                    .filter(LexerToken::isTerminal)
                    .map(it -> (Terminal) it)
                    .collect(Collectors.toSet());
        }

        return terminals;
    }

    public Set<NotTerminal> getNotTerminals() {
        if (notTerminals == null) {
            notTerminals = rule.stream()
                    .filter(it -> !it.isTerminal())
                    .map(it -> (NotTerminal) it)
                    .collect(Collectors.toSet());
        }

        return notTerminals;
    }

    public boolean contains(String name) {
        return names.contains(name);
    }

    public void removeUnknownToken(Map<String, NotTerminal> notTerminalMap, Map<String, Terminal> terminalMap) {
        if (rule.size() == 1 && rule.get(0).equals(Constants.EPSILON_TERMINAL)) {
            return;
        }

        List<LexerToken> newRule = new ArrayList<>(rule.size());

        for (LexerToken token : rule) {
            if (token.isTerminal()) {
                if (!token.equals(Constants.EPSILON_TERMINAL)) {
                    newRule.add(terminalMap.get(token.getName()));
                }
            } else {
                newRule.add(notTerminalMap.get(token.getName()));
            }
        }

        this.rule = Collections.unmodifiableList(newRule);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        }

        if (obj instanceof Rule) {
            Rule other = (Rule) obj;
            return rule.equals(other.rule);
        }

        return false;
    }
}
