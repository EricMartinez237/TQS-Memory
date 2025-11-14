package es.uab.tqs.memory.model;

import junit.framework.TestCase;

public class CardTest extends TestCase {
    public void testCardCreation() {
        Card card = new Card("A");
        assertEquals("A", card.getValue());
        assertFalse("La carta hauria d'estar boca avall inicialment", card.isFaceUp());
        assertFalse("La carta no hauria de coincidir inicialment", card.isMatched());
    }

    public void testInvalidValue() {
        try {
            Card card = new Card(null);
            fail("Hauria de llançar una excepció quan el valor és null.");
        } catch (IllegalArgumentException e) {
            assertEquals("El valor de la carta no pot ser null", e.getMessage());
        }

        try {
            Card card = new Card("");
            fail("Hauria de llançar una excepció quan el valor és buit.");
        } catch (IllegalArgumentException e) {
            assertEquals("El valor de la carta no pot ser buit", e.getMessage());
        }
    }

    public void testFlipCard() {
        Card card = new Card("A");
        card.flip();
        assertTrue("La carta hauria d'estar boca amunt després de girar-la.", card.isFaceUp());
        card.flip();
        assertFalse("La carta hauria d'estar boca avall després de 2 flips", card.isFaceUp());
    }

    public void testMultipleFlips() {
        Card card = new Card("A");
        card.flip(); // Boca amunt
        card.flip(); // Boca avall
        card.flip(); // Boca amunt
        assertTrue("La carta hauria d'estar boca amunt després de 3 flips. ", card.isFaceUp());
        card.flip();// Boca avall
        assertFalse("La carta hauria d'estar boca avall després de 4 flips", card.isFaceUp());
    }

    public void testCardMatching() {
        Card card1 = new Card("A");
        Card card2 = new Card("A");
        Card card3 = new Card("B");

        assertTrue("Les cartes amb la mateixa lletra haurien de coincidir.", card1.matches(card2));
        assertFalse("Les cartes amb diferents lletres no haurien de coincidir", card1.matches(card3));
    }

    public void testSetMatched() {
        Card card = new Card("A");
        card.setMatched(true);
        assertTrue("La carta hauria d'estar marcada com a matched.", card.isMatched());
    }

    public void testSetMatchedFalse() {
        Card card = new Card("A");
        card.setMatched(true);
        assertTrue("La carta hauria d'estar marcada com a matched. ", card.isMatched());
        card.setMatched(false);
        assertFalse("La carta hauria d'estar marcada com a no matched", card.isMatched());
    }

}