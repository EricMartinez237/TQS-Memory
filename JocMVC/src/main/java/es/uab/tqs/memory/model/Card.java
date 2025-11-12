package es.uab.tqs.memory.model;

public class Card {
    private String value;
    private boolean isFaceUp;
    private boolean isMatched;

    public Card(String value) {
        if (value == null) {
            throw new IllegalArgumentException("El valor de la carta no pot ser null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("El valor de la carta no pot ser buit");
        }
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
        isFaceUp = !isFaceUp;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
