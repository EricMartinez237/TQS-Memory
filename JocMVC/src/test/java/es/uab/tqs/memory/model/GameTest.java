package es.uab.tqs.memory.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    // Helper per crear un Board sense shuffle
    private Board createFixedBoard() {
        Card a1 = new Card("A");
        Card a2 = new Card("A");
        Card b1 = new Card("B");
        Card b2 = new Card("B");

        Card[][] fixed = {
                { a1, a2 },
                { b1, b2 }
        };
        return new Board(2, 2, fixed);
    }

    @Test
    public void testGameCreation() {
        Board board = new Board(2, 2);
        Game game = new Game(board);

        assertNotNull(game);
        assertFalse(game.isGameOver()); // El joc no hauria d'estar acabat al inici

        // Valor limit - 0 flips
        assertEquals(0, board.getFlippedCount());
    }

    @Test
    public void testFlipCard() {
        Board board = new Board(2, 2);
        Game game = new Game(board);

        // Flip vàlid
        game.flipCard(0, 0);
        assertTrue(board.getCardAt(0, 0).isFaceUp());

        // Segon flip (hauria de deixar)
        game.flipCard(1, 1);
        assertTrue(board.getCardAt(1, 1).isFaceUp());

        // Tercer flip ja no hauria de deixar
        game.flipCard(0, 1);
        assertFalse(board.getCardAt(0, 1).isFaceUp());

        // Valors límits per a flip vàlid(files i columnes)
        // cantonada superior esquerra
        game.flipCard(0, 0);
        assertTrue(board.getCardAt(0, 0).isFaceUp());
        game.checkTurn();

        // Cantonada superior dreta
        game.flipCard(1, 0);
        assertTrue(board.getCardAt(1, 0).isFaceUp());
        game.checkTurn();

        // Cantonada inferior esquerra
        game.flipCard(0, 1);
        assertTrue(board.getCardAt(0, 1).isFaceUp());
        game.checkTurn();

        // Cantonada inferior dreta
        game.flipCard(1, 1);
        assertTrue(board.getCardAt(1, 1).isFaceUp());
        game.checkTurn();

        // Cas extrem - indexs fora de rang
        try {
            game.flipCard(-1, 0);
            fail("Hauria de fallar per index invalid");
        } catch (IndexOutOfBoundsException e) {
        }

    }

    @Test
    public void testCheckTurnAndGameOver() {
        Board board = createFixedBoard();
        Game game = new Game(board);

        // PRIMERA PARELLA
        game.flipCard(0, 0);
        game.flipCard(0, 1);
        game.checkTurn();

        assertTrue(board.getCardAt(0, 0).isMatched());
        assertTrue(board.getCardAt(0, 1).isMatched());

        // Segona parella (b-b)
        game.flipCard(1, 0);
        game.flipCard(1, 1);
        game.checkTurn();

        assertTrue(board.getCardAt(1, 0).isMatched());
        assertTrue(board.getCardAt(1, 1).isMatched());

        assertTrue(game.isGameOver());
    }
}
