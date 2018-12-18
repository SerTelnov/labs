package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.Arg;
import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.util.List;

public class AttributeNotTerminal extends NotTerminal {

    private Arg returnArg = Constants.EMPTY_ARG;
    private List<Arg> inputArgs;
    private String inValues;


    public AttributeNotTerminal(String name, Arg arg) {
        super(name);
        this.returnArg = arg;
    }

    public AttributeNotTerminal(String name, List<Rule> rules, Arg arg) {
        super(name, rules);
        this.returnArg = arg;
    }

    public AttributeNotTerminal(String name, List<Rule> rules, List<Arg> args) {
        super(name, rules);
        this.inputArgs = args;
    }

    public AttributeNotTerminal(String name, List<Rule> rules, List<Arg> args, Arg arg) {
        this(name, rules, args);
        this.returnArg = arg;
    }

    public boolean hasInputArg() {
        return inputArgs != null && !inputArgs.isEmpty();
    }

    public boolean hasReturnArg() {
        return returnArg != null;
    }

    public List<Arg> getInputArgs() {
        return inputArgs;
    }

    public String getReturnType() {
        return returnArg.getType();
    }

    public String getReturnValueName() {
        return returnArg.getName();
    }

    public boolean hasInValue() {
        return this.inValues != null;
    }

    public void setInValues(String inValues) {
        this.inValues = inValues;
    }

    public String getInValues() {
        return inValues;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.ATTRIBUTE_NTERMINAL;
    }
}
