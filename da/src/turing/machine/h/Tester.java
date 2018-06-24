package turing.machine.h;

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
    private final TuringMachine machine = new TuringMachine("convertto2.out");
    private final Random random = new Random();

    private void customTest(String input) {
        input = input.replace(" ", "");

        BigInteger val = new BigInteger(input, 3);
        try {
            assertEquals(String.format("test: '%s'", input),
                    val.toString(2),
                    machine.solveAndGetTape(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'", input), e);
        }
    }

    @Test
    public void test01() {
        customTest("102");
    }

    @Test
    public void test02() {
        customTest("0");
    }

    @Test
    public void test03() {
        customTest("10101");
    }

    @Test
    public void test04() {
        customTest("1");
    }

    @Test
    public void test05() {
        customTest("2");
    }

    @Test
    public void generalTest() {
        for (int i = 0; i != 100; i++) {
            customTest(new BigInteger(Integer.toString(i), 10).toString(3));
        }
    }
}
