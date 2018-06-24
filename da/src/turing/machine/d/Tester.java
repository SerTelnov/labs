package turing.machine.d;

import turing.machine.TuringMachine;
import turing.machine.Util;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Tester {
    private TuringMachine machine = new TuringMachine("tandem.out");
    private final Random random = new Random();

    private boolean isTandem(String input) {
        if (input.length() % 2 != 0)
            return false;

        int middle = input.length() / 2;
        for (int i = 0; i != middle; i++) {
            if (input.charAt(i) != input.charAt(i + middle)) {
                return false;
            }
        }
        return true;
    }

    private void customTest(String input) {
        input = input.replace(" ", "");

        boolean answer = isTandem(input);
        try {
            assertEquals(String.format("test: '%s'\n", input),
                    answer,
                    machine.solve(Util.appendInput(input))
            );
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'\n", input), e);
        }
    }


    @Test
    public void test01() {
        customTest("1 1 0 1 1 0");
    }

    @Test
    public void test02() {
        customTest("11");
    }

    @Test
    public void test03() {
        customTest("01");
    }

    @Test
    public void test04() {
        customTest("0101");
    }

    @Test
    public void test05() {
        customTest("111");
    }

    private void runTest(String str) {
        if (str.length() < 9) {
            String first = str + "0";
            String second = str + "1";
            customTest(first);
            customTest(second);

            runTest(first);
            runTest(second);
        }
    }

    @Test
    public void generalTest() {
        runTest("");
    }
}
