package turing.machine.c;

import turing.machine.TuringMachine;
import turing.machine.Util;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 05.06.2018.
 */
public class Tester {
    private Random random = new Random();
    private TuringMachine machine = new TuringMachine("mirror.out");

    private void customTest(String input) {
        input = input.replace(" ", "");

        String result = String.format("%s%s",
                input,
                new StringBuilder().append(input).reverse().toString()
        );

        try {
            assertEquals(result, machine.solveAndGetTape(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException("fault: " + input, e);
        }
    }

    @Test
    public void test01() {
        customTest("11");
    }

    @Test
    public void test02() {
        customTest("01");
    }

    @Test
    public void test03() {
        customTest("1 0 1 0 0");
    }
}
