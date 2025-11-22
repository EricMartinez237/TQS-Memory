package es.uab.tqs.memory.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test
    public void testCardCreation() {
        Card card = new Card("A");
        assertEquals("A", card.getValue());
        assertFalse(card.isFaceUp(), "La carta hauria d'estar boca avall inicialment");
        assertFalse(card.isMatched(), "La carta no hauria de coincidir inicialment");
    }

    @Test
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

    @Test
    public void testFlipCard() {
        Card card = new Card("A");
        card.flip();
        assertTrue(card.isFaceUp(), "La carta hauria d'estar boca amunt després de girar-la.");
        card.flip();
        assertFalse(card.isFaceUp(), "La carta hauria d'estar boca avall després de 2 flips");
    }

    @Test
    public void testMultipleFlips() {
        Card card = new Card("A");
        card.flip(); // Boca amunt
        card.flip(); // Boca avall
        card.flip(); // Boca amunt
        assertTrue(card.isFaceUp(), "La carta hauria d'estar boca amunt després de 3 flips. ");
        card.flip();// Boca avall
        assertFalse(card.isFaceUp(), "La carta hauria d'estar boca avall després de 4 flips");
    }

    @Test
    public void testCardMatching() {
        Card card1 = new Card("A");
        Card card2 = new Card("A");
        Card card3 = new Card("B");

        assertTrue(card1.matches(card2), "Les cartes amb la mateixa lletra haurien de coincidir.");
        assertFalse(card1.matches(card3), "Les cartes amb diferents lletres no haurien de coincidir");
    }

    @Test
    public void testSetMatched() {
        Card card = new Card("A");
        card.setMatched(true);
        assertTrue(card.isMatched(), "La carta hauria d'estar marcada com a matched.");
    }

    @Test
    public void testSetMatchedFalse() {
        Card card = new Card("A");
        card.setMatched(true);
        assertTrue(card.isMatched(), "La carta hauria d'estar marcada com a matched. ");
        card.setMatched(false);
        assertFalse(card.isMatched(), "La carta hauria d'estar marcada com a no matched");
    }

}