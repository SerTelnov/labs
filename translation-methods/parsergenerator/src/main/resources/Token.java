public class Token {

    private final TypeToken token;
    private final String value;

    public Token(String notTerminal) {
        this.token = null;
        this.value = notTerminal;
    }

    public Token(TypeToken token, String value) {
        this.token = token;
        this.value = value;
    }

    public TypeToken getTypeToken() {
        return token;
    }

    public String getValue() {
        return value;
    }

    public boolean isTerminal() {
        return token != null;
    }
}
