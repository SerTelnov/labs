package turing.machine.k.evaluate;

/**
 * Created by Telnov Sergey on 08.06.2018.
 */
public class Const implements Expression {

    public final boolean value;

    public Const(final char value) {
        this.value = value == '1';
    }

    public Const(final boolean value) {
        this.value = value;
    }

    @Override
    public boolean evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return value ? "1" : "0";
    }
}
