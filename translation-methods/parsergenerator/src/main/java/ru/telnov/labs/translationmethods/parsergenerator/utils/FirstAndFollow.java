package ru.telnov.labs.translationmethods.parsergenerator.utils;

import org.jetbrains.annotations.NotNull;
import ru.telnov.labs.translationmethods.parsergenerator.tokens.*;

import java.util.*;

public final class FirstAndFollow {

    private FirstAndFollow() {
    }

    public static Map<String, Set<Terminal>> generateFirst(@NotNull Map<String, List<Rule>> grammar) {
        Map<String, Set<Terminal>> firstSet = new HashMap<>();
        grammar.forEach((name, v) -> firstSet.put(name, new HashSet<>()));

        boolean[] wasChange = {true};

        while (wasChange[0]) {
            wasChange[0] = false;

            grammar.forEach((name, rules) -> rules.forEach(rule -> {
                Set<Terminal> terminals = getFirst(rule, firstSet);
                wasChange[0] |= firstSet.get(name).addAll(terminals);
            }));
        }

        return firstSet;
    }

    private static Set<Terminal> getFirst(@NotNull Rule rule, Map<String, Set<Terminal>> firstSet) {
        List<LexerToken> tokens = rule.getTokens();
        Set<Terminal> terminals = new HashSet<>();

        boolean wasEpsilon = true;
        int i = 0;

        while (wasEpsilon && i < tokens.size()) {
            LexerToken cur = tokens.get(i++);
            if (!cur.isTerminal()) {
                Set<Terminal> curFirst = firstSet.get(cur.getName());
                wasEpsilon = curFirst.contains(Constants.EPSILON_TERMINAL);
                terminals.addAll(curFirst);
            } else {
                wasEpsilon = cur == Constants.EPSILON_TERMINAL;
                terminals.add((Terminal) cur);
            }
        }

        return terminals;
    }

    private static Set<Terminal> getFirst(Map<String, Set<Terminal>> first, LexerToken token) {
        if (token == null) {
            return Constants.EPSILON_SET;
        } else {
            if (token.isTerminal()) {
                return Collections.singleton((Terminal) token);
            } else {
                return first.get(token.getName());
            }
        }
    }

    public static Map<String, Set<Terminal>> generateFollow(Map<String, List<Rule>> grammar,
                                                            Map<String, Set<Terminal>> first,
                                                            String startNotTerminalStr) {
        Map<String, Set<Terminal>> followSet = new HashMap<>();
        grammar.forEach((name, rules) -> followSet.put(name, new HashSet<>()));

        followSet.get(startNotTerminalStr).add(Constants.END_LINE_TERMINAL);
        boolean[] wasChange = {true};

        while (wasChange[0]) {
            wasChange[0] = false;

            grammar.forEach((name, rules) -> rules.forEach(rule -> {
                Iterator<LexerToken> iterator = rule.getTokens().iterator();
                LexerToken next = iterator.next();

                while (next != null) {
                    LexerToken cur = next;
                    next = iterator.hasNext() ? iterator.next() : null;

                    if (!cur.isTerminal()) {
                        Set<Terminal> curFollow = followSet.get(cur.getName());
                        Set<Terminal> nextFirst = getFirst(first, next);

                        if (nextFirst.contains(Constants.EPSILON_TERMINAL)) {
                            wasChange[0] |= curFollow.addAll(followSet.get(name));

                            if (nextFirst.size() > 1) {
                                nextFirst.remove(Constants.EPSILON_TERMINAL);
                                wasChange[0] |= curFollow.addAll(nextFirst);
                                nextFirst.add(Constants.EPSILON_TERMINAL);
                            }
                        } else {
                            wasChange[0] |= curFollow.addAll(nextFirst);
                        }
                    }
                }
            }));
        }

        return followSet;
    }

    public static Map<String, List<Rule>> removeUselessSymbols(List<NotTerminal> grammar) {
        Map<String, List<Rule>> simplifiedGrammar = new HashMap<>();
        Map<String, List<Rule>> rulesToCheck = new HashMap<>();

        grammar.forEach(notTerminal -> notTerminal.getRules().forEach(rule -> {
            if (rule.getNotTerminals().isEmpty()) {
                checkAndPut(simplifiedGrammar, notTerminal.getName(), rule);
            } else {
                checkAndPut(rulesToCheck, notTerminal.getName(), rule);
            }
        }));

        boolean wasChange = !rulesToCheck.isEmpty();
        Map<String, List<Rule>> toRemove = new HashMap<>();

        while (wasChange) {
            rulesToCheck.forEach((notTerminalName, rules) ->
                    rules.forEach(rule -> {
                        Set<NotTerminal> notTerminals = rule.getNotTerminals();
                        if (containsAll(simplifiedGrammar, notTerminals)) {
                            checkAndPut(toRemove, notTerminalName, rule);
                        }
                    }));

            wasChange = !toRemove.isEmpty();
            toRemove.forEach((name, rules) -> {
                List<Rule> allRules = rulesToCheck.get(name);

                rules.forEach(rule -> {
                    checkAndPut(simplifiedGrammar, name, rule);
                    allRules.remove(rule);
                });

                if (allRules.isEmpty()) {
                    rulesToCheck.remove(name);
                }
            });

            toRemove.clear();
        }

        return simplifiedGrammar;
    }

    private static void checkAndPut(Map<String, List<Rule>> map, String notTerminalName, Rule rule) {
        if (map.containsKey(notTerminalName)) {
            map.get(notTerminalName).add(rule);
        } else {
            List<Rule> rules = new ArrayList<>();
            rules.add(rule);

            map.put(notTerminalName, rules);
        }
    }

    private static boolean containsAll(Map<String, List<Rule>> map, Set<NotTerminal> notTerminals) {
        for (NotTerminal notTerminal : notTerminals) {
            if (!map.containsKey(notTerminal.getName())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotLL1Grammar(Map<String, List<Rule>> grammar,
                                          Map<String, Set<Terminal>> first,
                                          Map<String, Set<Terminal>> follow) {

        return grammar.entrySet().stream()
                .anyMatch(entry -> {
                    String name = entry.getKey();
                    List<Rule> rules = entry.getValue();
                    for (int i = 0; i != rules.size(); i++) {
                        for (int j = 0; j != rules.size(); j++) {
                            if (i != j) {
                                Set<Terminal> aFirst = getFirst(rules.get(i), first);
                                Set<Terminal> bFirst = getFirst(rules.get(j), first);

                                if (containAny(aFirst, bFirst)) {
                                    return true;
                                }

                                if (aFirst.contains(Constants.EPSILON_TERMINAL)) {
                                    if (containAny(follow.get(name), bFirst)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    return false;
                });
    }

    private static <E> boolean containAny(Set<E> a, Set<E> b) {
        for (E el : a) {
            if (b.contains(el)) {
                return true;
            }
        }

        return false;
    }
}
