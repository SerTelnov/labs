package ru.telnov.labs.translationmethods.antlr;

public final class Utils {

    private static NameGenerator nameGenerator = new NameGenerator();

    private Utils() {
    }

    public static String generateObfuscateName(String name) {
        return nameGenerator.generateName(name);
    }

    public static StringBuilder append(StringBuilder builder, String s) {
        if (!s.isEmpty()) {
            builder.append(s)
                    .append(' ');
        }
        return builder;
    }

    public static String generateName() {
        return nameGenerator.generateNextName();
    }
}
