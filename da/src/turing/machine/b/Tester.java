package turing.machine.b;

import turing.machine.TuringMachine;
import turing.machine.Util;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 04.06.2018.
 */
public class Tester {

    private Random random = new Random();
    private TuringMachine machine = new TuringMachine("aplusb.out");

    private void customTest(String input) {
        String[] values = input.split("\\+");
        BigInteger a = new BigInteger(values[0], 2);
        BigInteger b = new BigInteger(values[1], 2);
        String res = a.add(b).toString(2);

        try {
            assertEquals(String.format("test: '%s'", input),
                    res,
                    machine.solveAndGetTape(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'", input), e);
        }
    }

    @Test
    public void test01() {
        customTest("11+01");
    }

    @Test
    public void test02() {
        customTest("11+10");
    }

    @Test
    public void test03() {
        customTest("1+111");
    }

    @Test
    public void test04() {
        customTest("0+11111");
    }

    @Test
    public void test05() {
        customTest("11+11");
    }

    @Test
    public void test06() {
        customTest("1+10001100");
    }

    @Test
    public void test07() {
        customTest("10000+1001101");
    }

    @Test
    public void randomTest() {
        final int bound = (int) Math.pow(15, 2);
        for (int i = 0; i != 50; i++) {
            int a = Math.abs(random.nextInt(bound));
            int b = Math.abs(random.nextInt(bound));
            customTest(String.format("%s+%s", Integer.toBinaryString(a), Integer.toBinaryString(b)));
        }
    }
}
