package turing.machine.k.evaluate;

/**
 * Created by Telnov Sergey on 08.06.2018.
 */
public class Parser {

    private int index;
    private String input;

    public boolean evaluate(String input) {
        this.input = input.replace(" ", "") + '\0';
        this.index = 0;

        return or().evaluate();
    }

    private Expression or() {
        Expression curr = and();
        while (test('o')) {
            curr = new Operation('o', curr, and());
        }
        return curr;
    }

    private Expression and() {
        Expression curr = value();
        while (test('a')) {
            curr = new Operation('a', curr, value());
        }
        return curr;
    }

    private Expression value() {
        Expression expr;
        if (test('(')) {
            expr = or();
        } else {
            expr = new Const(input.charAt(index));
        }
        index++;
        return expr;
    }

    private boolean test(char ch) {
        if (input.charAt(index) == ch) {
            index++;
            return true;
        }
        return false;
    }
}
