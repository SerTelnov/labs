package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NotTerminal extends AbstractLexerToken {

    private List<Rule> rules;

    public NotTerminal(String name) {
        super(name);
        this.rules = new ArrayList<>();
    }

    public NotTerminal(String name, Rule... rules) {
        super(name);
        this.rules = new ArrayList<>(Arrays.asList(rules));
    }

    public NotTerminal(String name, List<Rule> rules) {
        super(name);
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    @Override
    public boolean isTerminal() {
        return false;
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
            return rules.equals(other.rules);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rules);
    }
}
