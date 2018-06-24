package turing.machine.k.evaluate;

/**
 * Created by Telnov Sergey on 08.06.2018.
 */
public class Operation implements Expression {

    public final char sign;
    public final Expression left, right;

    public Operation(final char sign, final Expression left, final Expression right) {
        this.sign = sign;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate() {
        boolean a = left.evaluate();
        boolean b = right.evaluate();

        if (sign == 'o') {
            return a | b;
        } else {
            return a & b;
        }
    }

    @Override
    public String toString() {
        return String.format("%s%c%s", left.toString(), sign, right.toString());
    }
}
