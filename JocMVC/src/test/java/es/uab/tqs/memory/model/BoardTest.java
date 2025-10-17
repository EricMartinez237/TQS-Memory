package es.uab.tqs.memory.model;

import junit.framework.TestCase;

public class BoardTest extends TestCase {

    public void testBoardHasCorrectDimensions() {
        Board board = new Board(4, 4);
        Card[][] cards = board.getCards();
        assertEquals("El tauler hauria de tenir 4 files", 4, cards.length);
        assertEquals("El tauler hauria de tenir 4 columnes", 4, cards[0].length);
    }

    public void testAllCardsAreInitialized() {
        Board board = new Board(4, 4);
        for (Card[] row : board.getCards()) {
            for (Card card : row) {
                assertNotNull("La carta no hauria de ser null", card);
            }
        }
    }

    public void testAllCardsAreFacedDownAndUnmatched() {
        Board board = new Board(4, 4);
        for (Card[] row : board.getCards()) {
            for (Card card : row) {
                assertFalse("Les cartes han d'estar boca avall inicialment", card.isFaceUp());
                assertFalse("Les cartes no han d'estar emparellades incialment", card.isMatched());
            }
        }
    }

    public void testGetCardReturnsSameCard() {
        Board board = new Board(4, 4);
        Card expected = board.getCards()[1][0];
        assertEquals("getCards hauria de retornar la mateixa carta", expected, board.getCard(1, 0));
    }
}