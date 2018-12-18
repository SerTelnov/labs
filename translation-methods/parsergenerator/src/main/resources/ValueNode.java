public class ValueNode<E> extends Node {

    private E value;

    public ValueNode(String notTerminal) {
        super(notTerminal);
    }

    public ValueNode(String notTerminal, E value) {
        super(notTerminal);
        this.value = value;
    }

    public ValueNode(Token token) {
        throw new UnsupportedOperationException("this constructor only for Terminals");
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
