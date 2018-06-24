package turing.machine.j;

import org.junit.Test;
import turing.machine.TuringMachine;
import turing.machine.Util;

import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 07.06.2018.
 */
public class Tester {

    private final TuringMachine machine = new TuringMachine("postfixlogic.out", 1);
    private final Random random = new Random();

    private String evaluate(String input) {
        Stack<Boolean> st = new Stack<>();
        int index = 0;
        while (index < input.length()) {
            final char curr = input.charAt(index);
            if (curr == '0' || curr == '1') {
                st.push(curr == '1');
            } else {
                boolean a = st.pop();
                boolean b = st.pop();
                if (curr == 'o') {
                    st.push(a | b);
                } else {
                    st.push(a & b);
                }
            }
            index++;
        }
        if (st.size() != 1) {
            throw new RuntimeException("invalid input: " + input + '\n');
        }
        return st.peek() ? "1" : "0";
    }

    private void customTest(String input) {
        input = input.replace(" ", "");
        try {
            String answer = evaluate(input);
            assertEquals(String.format("test: '%s'", input),
                    answer, machine.solveAndGetTape(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException("failed: " + input + "\n", e);
        }
    }

    @Test
    public void test01() {
        customTest("1 0 a 1 o");
    }

    @Test
    public void test02() {
        customTest("0 1 o 0 o 1 a 0 a");
    }

    @Test
    public void test03() {
        customTest("0");
    }

    @Test
    public void test04() {
        customTest("011010ooooo");
    }

    @Test
    public void test05() {
        customTest("001oa");
    }

    private String createTest(int numberOfArgs) {
        int currBalance = 0;
        StringBuilder testBuilder = new StringBuilder();
        while (numberOfArgs > 0) {
            if (currBalance < 2 || random.nextBoolean()) {
                testBuilder.append(random.nextBoolean() ? '1' : '0');
                currBalance++;
                numberOfArgs--;
            } else {
                testBuilder.append(random.nextBoolean() ? 'o' : 'a');
                currBalance--;
            }
        }

        while (currBalance > 1) {
            testBuilder.append(random.nextBoolean() ? 'o' : 'a');
            currBalance--;
        }
        return testBuilder.toString();
    }

    @Test
    public void randomTest() {
        for (int i = 0; i != 100; i++) {
            customTest(createTest(i + 1));
        }
    }
}
