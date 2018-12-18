import java.util.List;
import java.util.ArrayList;

public class Node {

    private Token token;
    private List<Node> children;

    public Node(String notTerminal) {
        this.token = new Token(notTerminal);
        this.children = new ArrayList<>();
    }

    public Node(Token token) {
        this.token = token;
        this.children = new ArrayList<>();
    }

    public Token getToken() {
        return token;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public String terminalsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        walkTerminals(stringBuilder);
        return stringBuilder.toString();
    }

    private void walkTerminals(StringBuilder builder) {
        if (token.isTerminal()) {
            builder.append(token.getValue());
        }

        for (Node child : this.getChildren()) {
            child.walkTerminals(builder);
        }
    }
}