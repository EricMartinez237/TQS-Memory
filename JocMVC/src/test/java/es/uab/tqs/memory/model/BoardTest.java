package es.uab.tqs.memory.model;

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

}