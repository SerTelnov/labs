package turing.machine.k;

import org.junit.Test;
import turing.machine.TuringMachine;
import turing.machine.Util;
import turing.machine.k.evaluate.Const;
import turing.machine.k.evaluate.Expression;
import turing.machine.k.evaluate.Operation;
import turing.machine.k.evaluate.Parser;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Telnov Sergey on 07.06.2018.
 */
public class Tester {

    private final TuringMachine machine = new TuringMachine("infixlogic.out", 3);
    private final Random random = new Random();

    private void customTest(String input) {
        input = input.replace(" ", "");
        try {
            assertEquals(String.format("test: '%s'", input),
                    new Parser().evaluate(input) ? "1" : "0",
                    machine.solveAndGetTape(Util.appendInput(input)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("failed: '%s'", input), e);
        }
    }

    @Test
    public void test01() {
        customTest("1 a 0 o 1");
    }

    @Test
    public void test02() {
        customTest("(0 o 0 o 1) a 1 a 0");
    }

    @Test
    public void test03() {
        customTest("(1)o0o0a0");
    }

    @Test
    public void test04() {
        customTest("(0a0o0)o0");
    }

    @Test
    public void test05() {
        customTest("1a0a1");
    }

    private class Counter {
        private int counter;

        Counter(final int counter) {
            this.counter = counter / 2;
        }

        boolean hasArgs() {
            return counter > 0;
        }

        void dec() {
            counter--;
        }
    }

    private Expression createExpression(Counter counter) {
        if (counter.hasArgs()) {
            counter.dec();
            char sign = random.nextBoolean() ? 'o' : 'a';
            return new Operation(sign, createExpression(counter), createExpression(counter));
        } else {
            return new Const(random.nextBoolean());
        }
    }

    private String createRandomExpression(final int numberOfArgs) {
        StringBuilder expr = new StringBuilder(
                createExpression(new Counter(numberOfArgs)).toString());

//        final int bound = expr.length() / 2;
//        if (bound > 0) {
//            int bracketsCounter = random.nextInt(bound);
//            for (int i = 0; i < bracketsCounter; i++) {
//                int randomLeftIndex = random.nextInt(expr.length() - 3);
//
//                char left = expr.charAt(randomLeftIndex);
//                if (left == 'o' || left == 'a'
//                        || left == ')') {
//                    continue;
//                }
//
//                int randomRightIndex = random.nextInt(expr.length() - randomLeftIndex);
//                char right = expr.charAt(randomLeftIndex + randomRightIndex);
//
//                if (right != 'o' && right != 'a' && right != ')' && randomRightIndex > 2) {
//                    expr.insert(randomLeftIndex, '(');
//                    expr.insert(randomLeftIndex + randomRightIndex, ')');
//                }
//            }
//        }
        return expr.toString();
    }

    @Test
    public void randomTest() {
        for (int counter = 0; counter != 1000; counter++) {
            for (int i = 0; i != 20; i++) {
                customTest(
                        createRandomExpression(i == 0 ? 1 : i * 2));
            }
        }
    }
}
