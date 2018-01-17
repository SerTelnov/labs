package algorithmsOnLines;

import java.util.Scanner;

/**
 * Created by Telnov Sergey on 04.12.2017.
 */

public class E {

    private int[] zFunction;

    private void buildZFunction(String s) {
        zFunction = new int[s.length()];
        int left = 0;
        int right = 0;
        int prev = s.length();
        for (int i = 1; i != s.length(); i++) {
            zFunction[i] = Math.max(
                    0,
                    Math.min(
                            right - i,
                            zFunction[i - left]
                    )
            );
            while (i + zFunction[i] < s.length() && s.charAt(zFunction[i]) == s.charAt(i + zFunction[i])) {
                zFunction[i]++;
            }
            if (i + zFunction[i] > right) {
                left = i;
                right = i + zFunction[i];
            }
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        buildZFunction(s);

        int result = s.length();
        for (int i = 1; i != s.length(); i++) {
            if (zFunction[i] == s.length() - i) {
                boolean correct = true;
                int value = zFunction[i];
                for (int j = i; j < s.length(); j += i) {
                    if (zFunction[j] != value || j + i - 1 >= s.length()) {
                        correct = false;
                        break;
                    }
                    value -= i;
                }
                if (correct) {
                    result = i;
                    break;
                } else {
                    result = s.length();
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        new E().run();
    }
}
