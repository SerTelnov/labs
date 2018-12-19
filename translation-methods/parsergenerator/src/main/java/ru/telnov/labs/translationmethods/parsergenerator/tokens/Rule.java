package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rule {

    protected List<LexerValue> rule;
    private Set<Terminal> terminals;
    private Set<NotTerminal> notTerminals;
    private List<LexerToken> tokens;

    private Set<String> names;

    public Rule() {
        this(Collections.singletonList(Constants.EPSILON_TERMINAL));
    }

    public Rule(LexerValue... tokens) {
        this(Arrays.asList(tokens));
    }

    public Rule(List<LexerValue> tokens) {
        if (allIsCode(tokens)) {
            List<LexerValue> values = new ArrayList<>();
            values.add(Constants.EPSILON_TERMINAL);
            values.addAll(tokens);

            this.rule = Collections.unmodifiableList(values);
        } else {
            this.rule = Collections.unmodifiableList(tokens);
        }

        this.names = rule.stream()
                .filter(it -> !it.isCode())
                .map(it -> ((LexerToken) it).getName())
                .collect(Collectors.toSet());
    }

    private boolean allIsCode(List<LexerValue> values) {
        return values.stream()
                .filter(LexerValue::isCode)
                .count() == values.size();
    }

    public List<LexerValue> getRule() {
        return rule;
    }

    public List<LexerToken> getTokens() {
        if (tokens == null) {
            this.tokens = new ArrayList<>();
            for (LexerValue value : rule) {
                if (!value.isCode()) {
                    tokens.add((LexerToken) value);
                }
            }
        }

        return tokens;
    }

    public LexerToken firstToken() {
        for (LexerValue value : rule) {
            if (!value.isCode()) {
                return (LexerToken) value;
            }
        }

        throw new RuntimeException("");
    }

    public Set<Terminal> getTerminals() {
        if (terminals == null) {
            terminals = getLexerTokenStream()
                    .filter(LexerToken::isTerminal)
                    .map(it -> (Terminal) it)
                    .collect(Collectors.toSet());
        }

        return terminals;
    }

    public Set<NotTerminal> getNotTerminals() {
        if (notTerminals == null) {
            notTerminals = getLexerTokenStream()
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

        List<LexerValue> newRule = new ArrayList<>(rule.size());

        for (LexerValue value : rule) {
            if (!value.isCode()) {
                LexerToken token = (LexerToken) value;

                if (token.isTerminal()) {
                    if (!token.equals(Constants.EPSILON_TERMINAL)) {
                        newRule.add(terminalMap.get(token.getName()));
                    } else {
                        newRule.add(Constants.EPSILON_TERMINAL);
                    }
                } else {
                    UnknownToken unknownToken = (UnknownToken) token;
                    NotTerminal notTerminal = notTerminalMap.get(token.getName());

                    if (notTerminal.getTokenType() == TokenType.ATTRIBUTE_NTERMINAL && unknownToken.hasInValues()) {
                        ((AttributeNotTerminal) notTerminal).setInValues(unknownToken.getInValue());
                    }

                    newRule.add(notTerminal);
                }
            } else {
                newRule.add(value);
            }
        }

        this.rule = Collections.unmodifiableList(newRule);
    }

    private Stream<LexerToken> getLexerTokenStream() {
        return rule.stream()
                .filter(it -> !it.isCode())
                .map(it -> (LexerToken) it);
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
            return this.rule.equals(other.rule);
        }

        return false;
    }
}
