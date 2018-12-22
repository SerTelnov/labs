package ru.telnov.labs.translationmethods.parsergenerator.tokens;

import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.Arg;
import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AttributeNotTerminal extends NotTerminal {

    private List<String> inValues;
    private int indexInValue;

    private Arg returnArg = Constants.EMPTY_ARG;
    private List<Arg> inputArgs;


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
        return this.inValues != null && indexInValue < inValues.size();
    }

    public void setInValue(String inValue) {
        if (inValues == null) {
            inValues = new ArrayList<>();
        }
        inValues.add(inValue);
    }

    public String getInValues() {
        return inValues.get(indexInValue++);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.ATTRIBUTE_NTERMINAL;
    }
}
