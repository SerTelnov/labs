package turing.machine.f;

import turing.machine.TuringMachine;
import turing.machine.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Tester {
    private TuringMachine machine = new TuringMachine("reverse.out");

    private void customTest(String input) {
        String result = new StringBuilder()
                .append(input)
                .reverse()
                .toString();

        try {
            assertEquals(result, machine.solveAndGetTape(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'", input), e);
        }

    }

    @Test
    public void test01() {
        customTest("1");
    }

    @Test
    public void test02() {
        customTest("1111");
    }

    @Test
    public void test03() {
        customTest("0101011000110101");
    }
}
