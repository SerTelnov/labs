package ru.telnov.labs.translationmethods.parsergenerator.generator;

import ru.telnov.labs.translationmethods.parsergenerator.generator.builders.*;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.*;
import ru.telnov.labs.translationmethods.parsergenerator.utils.Constants;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.telnov.labs.translationmethods.parsergenerator.utils.Constants.TABS;

public final class ParserGenerator {

    private static final String FILENAME = "Parser";
    private static final String IMPORTS = "import java.util.List;\n" +
            "import java.lang.RuntimeException;" +
            "import java.util.stream.Collectors;";

    private static final String CONSUME_METHOD = "consume";
    private static final String LIST_TOKENS = "List<" + Constants.LEXER_TOKEN_CLASS + ">";

    private static final String NO_TERM_TYPE = Constants.TREE_NODE;

    private ParserGenerator() {
        throw new RuntimeException();
    }

    public static Clazz generateParser(Map<String, Set<Terminal>> first,
                                       Map<String, Set<Terminal>> follow,
                                       List<NotTerminal> notTerminals,
                                       Map<String, NotTerminal> grammar) {
        Clazz clazz = new Clazz(FILENAME, new Arg(LIST_TOKENS, "tokens"));

        clazz.setImports(IMPORTS);
        clazz.addFields(new Field(Modifier.PRIVATE, LIST_TOKENS, "tokens"),
                new Field(Modifier.PRIVATE, "int", "index"));

        clazz.addMethod(makeStartMethod(notTerminals.get(0).getName()));

        notTerminals.forEach(notTerminal ->
                clazz.addMethod(makeNotTerminalMethod(notTerminal, first, follow, grammar)));

        clazz.addMethod(makeConsumeMethod());
        clazz.addMethod(makeTerminalGetter());
        return clazz;
    }

    private static Method makeTerminalGetter() {
        Method method = new Method(Modifier.PUBLIC, "String", "getLastTerminal");
        method.addStatement("return tokens.get(index - 1).getValue()");
        return method;
    }

    private static Method makeStartMethod(String startNotTerminal) {
        Method method = new Method(Modifier.PUBLIC, Constants.TREE_NODE, "parseStartMethod");
        method.addStatements("this.index = 0",
                Constants.TREE_NODE + " root = " + startNotTerminal + "()");

        method.addStatements("if (index != tokens.size() - 1) {",
                "String leftTokens = tokens.subList(index, tokens.size())\n" +
                        "                    .stream()\n" +
                        "                    .map(t -> t.getValue())\n" +
                        "                    .collect(Collectors.joining(\", \"))",
                "throw new RuntimeException(\"didn't parse all expression\\nLeft tokens: \" + leftTokens)",
                "}",
                "return root");
        return method;
    }

    private static Method makeConsumeMethod() {
        Method method = new Method(Modifier.PRIVATE, NO_TERM_TYPE, CONSUME_METHOD);
        method.addStatement("return new " + NO_TERM_TYPE + "(tokens.get(index++))");
        return method;
    }

    private static Method makeNotTerminalMethod(NotTerminal notTerminal,
                                                Map<String, Set<Terminal>> first,
                                                Map<String, Set<Terminal>> follow,
                                                Map<String, NotTerminal> grammar) {

        Method method;
        String curNodeInit = "%s curNode = new %s(\"" + notTerminal.getName() + "\")";
        String returnValueName = "";

        if (notTerminal.getTokenType() == TokenType.NOT_TERMINAL) {
            method = new Method(Modifier.PUBLIC, Constants.TREE_NODE, notTerminal.getName());
            method.addStatement(String.format(curNodeInit, Constants.TREE_NODE, Constants.TREE_NODE));
        } else {
            AttributeNotTerminal anTerminal = (AttributeNotTerminal) notTerminal;

            if (anTerminal.hasInputArg()) {
                method = new Method(Modifier.PUBLIC, Constants.TREE_NODE, notTerminal.getName(), anTerminal.getInputArgs());
            } else {
                method = new Method(Modifier.PUBLIC, Constants.TREE_NODE, notTerminal.getName());
            }

            String nodeType;
            if (anTerminal.hasReturnArg()) {
                nodeType = Constants.TREE_VALUE_NODE + "<" + anTerminal.getReturnType() + ">";
                returnValueName = anTerminal.getReturnValueName();
            } else {
                nodeType = Constants.TREE_NODE;
            }

            method.addStatement(String.format(curNodeInit, nodeType, nodeType));
            if (!returnValueName.isEmpty()) {
                method.addStatement(anTerminal.getReturnType() + " " + returnValueName + " = null");
            }
        }
        method.addStatement(Constants.LEXER_TOKEN_CLASS + " curToken = tokens.get(index)");

//        first
        StringBuilder switchBuilder = new StringBuilder();
        switchBuilder.append("switch (curToken.getTypeToken()) {\n");

        boolean wasEpsilon = false;
        for (Rule rule : notTerminal.getRules()) {
            LexerToken firstToken = rule.firstToken();
            Set<Terminal> curFirst;
            if (!firstToken.isTerminal()) {
                curFirst = first.get(firstToken.getName());
            } else {
                curFirst = Collections.singleton((Terminal) firstToken);
            }

            int sbLen = switchBuilder.length();

            switchBuilder.append(makeCaseBlock(curFirst));
            switchBuilder.append(rule.getRule().stream()
                    .filter(token -> !token.equals(Constants.EPSILON_TERMINAL))
                    .map(value -> {
                        if (value.isCode()) {
                            JCode code = (JCode) value;
                            return code.getCode();
                        } else {
                            LexerToken token = (LexerToken) value;
                            String methodName;
                            StringBuilder builder = new StringBuilder();

                            methodName = token.getName();
                            if (token.getTokenType() == TokenType.ATTRIBUTE_NTERMINAL) {
                                AttributeNotTerminal anTerminal = (AttributeNotTerminal) token;
                                builder.append("ValueNode<")
                                        .append(anTerminal.getReturnType())
                                        .append("> ")
                                        .append(methodName)
                                        .append("Node = ")
                                        .append("(ValueNode<")
                                        .append(anTerminal.getReturnType())
                                        .append(">) ")
                                        .append(methodName)
                                        .append("(");

                                NotTerminal nextNotTerm = grammar.get(methodName);
                                if (nextNotTerm.getTokenType() == TokenType.ATTRIBUTE_NTERMINAL) {
                                    AttributeNotTerminal anTerm = (AttributeNotTerminal) nextNotTerm;
                                    if (anTerm.hasInValue()) {
                                        builder.append(anTerm.getInValues());
                                    }
                                }

                                builder
                                        .append(");")
                                        .append(TABS[4])
                                        .append("curNode.addChild(")
                                        .append(methodName)
                                        .append("Node")
                                        .append(");");
                            } else {
                                if (token.isTerminal()) {
                                    methodName = CONSUME_METHOD;
                                }

                                builder.append("curNode.addChild(")
                                        .append(methodName)
                                        .append("());");
                            }
                            return builder.toString();
                        }
                    })
                    .map(s -> TABS[4] + s + "\n")
                    .collect(Collectors.joining()));

            if (sbLen < switchBuilder.length()) {
                switchBuilder.append(TABS[4]).append("break;\n");
            }

            wasEpsilon |= curFirst.contains(Constants.EPSILON_TERMINAL);
        }

//        follow
        if (wasEpsilon) {
            switchBuilder.append(makeCaseBlock(follow.get(notTerminal.getName())));
            switchBuilder.append(TABS[4]).append("break;\n");
        }

//        default
        switchBuilder.append(TABS[3])
                .append("default:\n")
                .append(TABS[4]).append("throw new RuntimeException(\"unexpected token " +
                "'\" + curToken.getTypeToken() + \"' at notTerminal '")
                .append(notTerminal.getName())
                .append("'\");\n");

        switchBuilder.append(TABS[2]).append("}");

        method.addStatement(switchBuilder.toString());

        if (!returnValueName.isEmpty()) {
            method.addStatement("curNode.setValue(" + returnValueName + ")");
        }
        method.addStatements("return curNode");
        return method;
    }

    private static String makeCaseBlock(Set<Terminal> terminals) {
        return terminals.stream()
                .filter(t -> !t.equals(Constants.EPSILON_TERMINAL))
                .map(terminal -> TABS[3] + "case " + terminal.getName() + ":\n")
                .collect(Collectors.joining());
    }
}
