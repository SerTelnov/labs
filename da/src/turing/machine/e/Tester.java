package turing.machine.e;

import turing.machine.TuringMachine;
import org.junit.Test;
import turing.machine.Util;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 04.06.2018.
 */
public class Tester {
    private Random random = new Random();
    private TuringMachine machine = new TuringMachine("balanced.out");

    private String appendString(String str) {
        return str + '_';
    }

    private String getRandomSequence(final int len) {
        char[] sequence = new char[len];

        for (int i = 0; i != len; i++) {
            sequence[i] = random.nextBoolean() ? '(' : ')';
        }
        return new String(sequence);
    }

    private boolean isBalanced(String sequence) {
        int balance = 0;
        for (int i = 0; i != sequence.length(); i++) {
            if (balance < 0) {
                return false;
            }

            if (sequence.charAt(i) == '(') {
                balance++;
            } else if (sequence.charAt(i) == ')') {
                balance--;
            }
        }
        return balance == 0;
    }

    private void customTest(String sequence) {
        String input = Util.appendInput(sequence);
        int index = 0;
        while (index < input.length() && input.charAt(index) == machine.getBlank()) {
            index++;
        }

        assertEquals(String.format("machine failed for test: '%s'", sequence),
                isBalanced(sequence), machine.solve(input, index));
    }

    @Test
    public void test01() {
        customTest("(())");
    }

    @Test
    public void test02() {
        customTest("(()");
    }

    @Test
    public void test03() {
        customTest("())");
    }

    @Test
    public void test04() {
        customTest("))(()))((()()(()())(");
    }

    @Test
    public void test05() {
        customTest("((()))");
    }

    @Test
    public void test06() {
        customTest("()");
    }

    @Test
    public void test07() {
        customTest("()()()()");
    }

    @Test
    public void test08() {
        customTest("(())()((()))");
    }

    @Test
    public void randomTest() {
        for (int i = 0; i != 50; i++) {
            customTest(getRandomSequence(20 + i));
        }
    }
}
