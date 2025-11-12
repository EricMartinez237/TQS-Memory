package es.uab.tqs.memory.model;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class BoardTest extends TestCase {

    public void testBoardCreation() {
        int rows = 4;
        int cols = 4;
        Board board = new Board(rows, cols);

        assertEquals("El numero de files ha de ser 4", rows, board.getRows());
        assertEquals("El numero de columnes ha de ser 4", cols, board.getCols());

        // Comprovar el número total de cartes
        assertEquals("El numero total de cartes hauria de ser 16", rows * cols, board.getTotalCards());
    }

    public void testInvalidBoardDimensions() {
        try {
            new Board(0, 4);
            fail("S'hauria d'haver llençat una excepció per files = 0");
        } catch (IllegalArgumentException e) {

        }
        try {
            new Board(-1, 3);
            fail("S'hauria  d'haver llençat una excepció per files negatives");
        } catch (IllegalArgumentException e) {

        }

        try {
            new Board(1, 0);
            fail("S'hauria d'haver llençat una excepció per columna = 0");
        } catch (IllegalArgumentException e) {

        }

        try {
            new Board(1, -3);
            fail("S'hauria d'haver llençat una excepció per columne negatives");
        } catch (IllegalArgumentException e) {

        }
    }

    public void testTotalCardsDifferentSize() {
        Board board = new Board(2, 3);
        assertEquals("El numero total de cartes hauria de ser 6", 6, board.getTotalCards());

    }

    public void testOddTotalCards() {
        try {
            new Board(3, 3);
            fail("S'hauria d'haver llançat una excepció per nombre imparell de cartes");
        } catch (IllegalArgumentException e) {

        }
        try {
            new Board(2, 3);
        } catch (IllegalArgumentException e) {
            fail("No s'hauria de llançar excepció");
        }
    }

    public void testInicialitzarCartes() {
        Board board = new Board(4, 4);

        assertEquals(16, board.getTotalCards());

        // Comprovar que les cartes estan duplicades
        Map<String, Integer> counts = new HashMap<>();
        for (Card c : board.getCards()) {
            counts.put(c.getValue(), counts.getOrDefault(c.getValue(), 0) + 1);
            assertFalse(c.isFaceUp()); // totes boca avall
            assertFalse(c.isMatched()); // totes emparellades
        }

        for (int count : counts.values()) {
            assertEquals(2, count);
        }
    }

    public void testGetCardAt() {
        Board board = new Board(4, 4);

        // Accedim a una carta dins el rang
        Card c = board.getCardAt(0, 0);
        assertNotNull(c);

        // Accedim fora de rang
        try {
            board.getCardAt(-1, 0);
            fail("Falla per fila negativa");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            board.getCardAt(0, 4);
            fail("Falla per columna fora de rang");
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public void testFlipCard() {
        Board board = new Board(4, 4);
        Card c = board.getCardAt(0, 0);

        boolean initialState = c.isFaceUp();
        board.flipCard(0, 0);
        assertEquals(!initialState, c.isFaceUp());

        board.flipCard(0, 0);
        assertEquals(initialState, c.isFaceUp());
    }

}