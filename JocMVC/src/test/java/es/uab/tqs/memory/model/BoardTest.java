package es.uab.tqs.memory.model;

import java.util.HashMap;
import java.util.List;
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

    public void testCheckPair() {
        Card c1 = new Card("A");
        Card c2 = new Card("A");
        Card c3 = new Card("B");

        Board board = new Board(2, 2);

        // parella igual
        assertTrue(board.checkPair(c1, c2));
        assertTrue(c1.isMatched());
        assertTrue(c2.isMatched());

        // parella diferent
        assertFalse(board.checkPair(c1, c3));
        assertFalse(c3.isMatched());
    }

    public void testIsComplete() {
        Board board = new Board(2, 2);

        // Inicialment no està complet
        assertFalse(board.isComplete());

        // marcar totes les cartes com emparellades
        for (Card card : board.getCards()) {
            card.setMatched(true);
        }

        // Despres hauria d'estar complet
        assertTrue(board.isComplete());
    }

    //
    // Addició de tests per facilitar la implementació de la classe Game
    // Test per controlar quines cartes estan boca amunt però no emparellades
    public void testGetFlippedUnmatched() {
        Board board = new Board(2, 2);

        assertTrue(board.getFlippedUnmatched().isEmpty());

        // voltejar una carta
        board.flipCard(0, 0);
        List<int[]> positions = board.getFlippedUnmatched();
        assertEquals(1, positions.size());
        assertEquals(0, positions.get(0)[0]);// fila
        assertEquals(0, positions.get(0)[1]); // columna

        // voltejar segona carta
        board.flipCard(1, 1);
        positions = board.getFlippedUnmatched();
        assertEquals(2, positions.size());

        // Emparellar una carta
        Card c1 = board.getCardAt(0, 0);
        Card c2 = board.getCardAt(1, 1);
        if (board.checkPair(c1, c2)) { // Si són parella, es marcaran com matched
            positions = board.getFlippedUnmatched();
            assertTrue(positions.isEmpty());
        } else {
            // Si no són parella, encara hi ha dues
            assertEquals(2, positions.size());
        }

    }

    // Volteja de nou les cartes boca amunt pero no emparellades
    public void testFlipBackUnmatchedCards() {
        Board board = new Board(2, 2);

        // Voltejar dues cartes
        board.flipCard(0, 0);
        board.flipCard(1, 1);
        assertTrue(board.getCardAt(0, 0).isFaceUp());
        assertTrue(board.getCardAt(1, 1).isFaceUp());

        // Cridar flipBackUnmatchedCards
        board.flipBackUnmatchedCards();
        assertFalse(board.getCardAt(0, 0).isFaceUp());
        assertFalse(board.getCardAt(1, 1).isFaceUp());

        // Si una carta està emparellada, no es volteja
        board.flipCard(0, 0);
        board.getCardAt(0, 0).setMatched(true);
        board.flipCard(1, 1);
        board.flipBackUnmatchedCards();
        assertTrue(board.getCardAt(0, 0).isFaceUp()); // Encara voltejada perquè està matched
        assertFalse(board.getCardAt(1, 1).isFaceUp()); // Voltejada de nou
    }

    // Test per limitar el nombre de cartes girades a 2
    public void testGetFlippedCount() {
        Board board = new Board(2, 2);

        // Inicialment, 0
        assertEquals(0, board.getFlippedCount());

        // Voltejar una carta
        board.flipCard(0, 0);
        assertEquals(1, board.getFlippedCount());

        // Voltejar una altra
        board.flipCard(1, 1);
        assertEquals(2, board.getFlippedCount());

        // Emparellar (no afecta el compte de voltejades)
        board.checkPair(board.getCardAt(0, 0), board.getCardAt(1, 1));
        assertEquals(2, board.getFlippedCount());
    }
}