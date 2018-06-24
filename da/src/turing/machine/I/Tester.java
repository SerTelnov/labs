package turing.machine.I;

import turing.machine.TuringMachine;
import turing.machine.Util;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 06.06.2018.
 */
public class Tester {
    private TuringMachine machine = new TuringMachine("multiplication.out");
    private final Random random = new Random();

    private void customTest(String input) {
        input = input.replace(" ", "");

        String[] values = input.split("\\*");
        BigInteger a = new BigInteger(values[0], 2);
        BigInteger b = new BigInteger(values[1], 2);

        String answer = a.multiply(b).toString(2);

        try {
            assertEquals(String.format("test: '%s'\n", input),
                    answer,
                    machine.solveAndGetTape(Util.appendInput(input))
            );
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'\n", input), e);
        }
    }

    @Test
    public void test01() {
        customTest("11*10");
    }

    @Test
    public void test02() {
        customTest("1*0");
    }

    @Test
    public void test03() {
        customTest("0*1");
    }

    @Test
    public void test04() {
        customTest("100*1");
    }

    @Test
    public void test05() {
        customTest("11*11");
    }

    @Test
    public void generalTest() {
        for (int i = 0; i != 100; i++) {
            for (int j = 0; j != 100; j++) {
                customTest(String.format("%s*%s", Integer.toBinaryString(i), Integer.toBinaryString(j)));
            }
        }
    }
}
