package turing.machine.m;

import org.junit.Test;
import turing.machine.TuringMachine;
import turing.machine.Util;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 08.06.2018.
 */
public class Tester {

    private final TuringMachine machine = new TuringMachine("sorting.out", 2);
    private final Random random = new Random();

    private void customTest(String input) {
        input = input.replace(" ", "");

        String answer = Stream
                .of(input.split("\\|+"))
                .map(str -> new BigInteger(str, 2).intValue())
                .sorted()
                .map(Integer::toBinaryString)
                .collect(Collectors.joining("|"));

        try {
            assertEquals(String.format("test: '%s'", input), answer,
                    machine.solveAndGetTape(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed '%s'", input), e);
        }
    }

    @Test
    public void test01() {
        customTest("1 1 0 | 1 1 | 1 0 1");
    }


    @Test
    public void test02() {
        customTest("1010|10101");
    }

    @Test
    public void test03() {
        customTest("100|1|10|10|10|0");
    }

    @Test
    public void test04() {
        customTest("1|0");
    }

    @Test
    public void test05() {
        customTest("10");
    }

    private String createRandomTest(final int numberOfArgs) {
        List<Integer> values = new ArrayList<>(numberOfArgs);
        final int bound = 5;

        for (int i = 0; i != numberOfArgs; i++) {
            values.add(random.nextInt(bound));
        }

        return values
                .stream()
                .map(Integer::toBinaryString)
                .collect(Collectors.joining("|"));
    }

    @Test
    public void randomTest() {
        for (int count = 0; count != 10000; count++) {
            for (int i = 1; i != 21; i++) {
                customTest(createRandomTest(i));
            }
        }
    }
}
