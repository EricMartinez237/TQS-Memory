package es.uab.tqs.memory;

public class Card {
    private String value;
    private boolean isFaceUp;
    private boolean isMatched;

    public Card(String value) {
        this.value = value;
        this.isFaceUp = false;
        this.isMatched = false;
    }

    public String getValue() {
        return value;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flip() {
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public boolean matches(Card other) {
        return this.value.equals(other.value);
    }
}