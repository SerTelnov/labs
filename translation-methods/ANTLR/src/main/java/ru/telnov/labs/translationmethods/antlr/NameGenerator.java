package ru.telnov.labs.translationmethods.antlr;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class NameGenerator {

    private final String OBFUSCATE_VAR = "[I|O][0-1]*";

    private Map<String, String> nameKeys = new HashMap<>();
    private long value = 638;

    public String generateName(String name) {
        if (name.matches(OBFUSCATE_VAR)) {
            return name;
        }
        if (!nameKeys.containsKey(name)) {
            String value = generateNextName();
            nameKeys.put(name, value);
        }

        return nameKeys.get(name);
    }

    @NotNull
    public String generateNextName() {
        String binValue = Long.toBinaryString(value);
        value++;

        char startLetter;
        if (binValue.charAt(0) == '0') {
            startLetter = 'O';
        } else {
            startLetter = 'I';
        }

        return "" + startLetter + binValue.substring(1);
    }
}
