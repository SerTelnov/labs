package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NotTerminal extends AbstractLexerToken {

    private List<Rule> rules;

    public NotTerminal(String name) {
        this(name, new ArrayList<>());
    }

    public NotTerminal(String name, List<Rule> rules) {
        super(name);
        this.rules = rules;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.NOT_TERMINAL;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public final void addRules(Rule... rules) {
        this.rules.addAll(Arrays.asList(rules));
    }

    public List<Rule> getRules() {
        return rules;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!super.equals(obj)) {
            return false;
        }

        if (obj instanceof NotTerminal) {
            NotTerminal other = (NotTerminal) obj;
            return this.rules.equals(other.rules);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rules);
    }
}
