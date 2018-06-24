package turing.machine.g;

import turing.machine.TuringMachine;
import turing.machine.Util;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Tester {

    private TuringMachine machine = new TuringMachine("less.out");
    private Random random = new Random();

    private void customTest(String input) {
        input = input.replace(" ", "");
        String[] values = input.split("\\<");
        BigInteger a = new BigInteger(values[0], 2);
        BigInteger b = new BigInteger(values[1], 2);
        boolean isLess = a.compareTo(b) < 0;

        try {
            assertEquals(String.format("failed: '%s'", input), isLess, machine.solve(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'", input), e);
        }
    }

    @Test
    public void test01() {
        customTest("1 1 < 1 0");
    }

    @Test
    public void test02() {
        customTest("0 < 1");
    }

    @Test
    public void test03() {
        customTest("0<111");
    }

    @Test
    public void test04() {
        customTest("1<111");
    }

    @Test
    public void test05() {
        customTest("111<1");
    }

    @Test
    public void test06() {
        customTest("11<11");
    }

    @Test
    public void test07() {
        customTest("1<10");
    }

    @Test
    public void test08() {
        customTest("10<11");
    }

    @Test
    public void test09() {
        customTest("1<0");
    }

    @Test
    public void generalTest() {
        for (int i = 0; i != 15; i++) {
            for (int j = 0; j != 15; j++) {
                customTest(String.format("%s<%s", Integer.toBinaryString(i), Integer.toBinaryString(j)));
            }
        }
    }

    @Test
    public void randomTest() {
        int bound = 100000000;
        for (int i = 0; i != 200; i++) {
            customTest(String.format("%s<%s",
                    Integer.toBinaryString(Math.abs(random.nextInt(bound))),
                    Integer.toBinaryString(Math.abs(random.nextInt(bound)))));
        }
    }
}
