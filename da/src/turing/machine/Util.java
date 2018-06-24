package turing.machine;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Util {

    public static String appendInput(String input) {
        final int len = input.length() * 100;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i != len; i++) {
            builder.append('_');
        }
        String emptyLine = builder.toString();

        return emptyLine + input + emptyLine;
    }
}
