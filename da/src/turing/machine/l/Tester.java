package turing.machine.l;

import org.junit.Test;
import turing.machine.CodeInterpreter;
import turing.machine.TuringMachine;
import turing.machine.Util;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 07.06.2018.
 */
public class Tester {

    private final TuringMachine machine = new TuringMachine("factorial.txt");
    private final CodeInterpreter interpreter = new CodeInterpreter("temp-factorial.out");

    private String factorial(int val) {
        BigInteger result = new BigInteger("1");
        while (val > 1) {
            result = result.multiply(new BigInteger(Integer.toString(val)));
            val--;
        }
        return result.toString(2);
    }

    private void customTest(String input, String answer) {
        try {
            assertEquals(String.format("test: '%s'", input),
                    answer, machine.solveAndGetTape(Util.appendInput(input)));
//                    answer, interpreter.run(input));
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'", input), e);
        }
    }

    private void customTest(String input) {
        input = input.replace(" ", "");
        String answer = factorial(new BigInteger(input, 2).intValue());
        customTest(input, answer);
    }

    @Test
    public void test01() {
        customTest("101", "1111000");
    }

    @Test
    public void test02() {
        customTest("1 0 11 1 1 1 000");
    }

    @Test
    public void test03() {
        customTest("10");
    }

    @Test
    public void test04() {
        customTest("11");
    }

    @Test
    public void test05() {
        customTest("101");
    }

    @Test
    public void test06() {
        customTest("111000");
    }

    @Test
    public void generalTest() {
        final int count = (int) Math.pow(30, 2);

        for (int i = 0; i <= count; i++) {
            customTest(Integer.toBinaryString(i));
        }
    }
}
