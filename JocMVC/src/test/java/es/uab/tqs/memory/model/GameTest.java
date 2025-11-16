package es.uab.tqs.memory.model;

import junit.framework.TestCase;

public class GameTest extends TestCase {
    public void testGameCreation() {
        Board board = new Board(2, 2);
        Game game = new Game(board);

        assertNotNull(game);
        assertFalse(game.isGameOver()); // El joc no hauria d'estar acabat al inici
    }

    public void testFlipCard() {
        Board board = new Board(2, 2);
        Game game = new Game(board);

        // Flip vàlid
        game.flipCard(0, 0);
        assertTrue(board.getCardAt(0, 0).isFaceUp());

        // Segon flip (hauria de deixar)
        game.flipCard(1, 1);
        assertTrue(board.getCardAt(1, 1).isFaceUp());
    }

    public void checkTurnAndGameOver() {
        Board board = new Board(2, 2);
        Game game = new Game(board);

        // Simular torn: flip 2 cartes que són parella
        game.flipCard(0, 0);
        game.flipCard(1, 1);
        game.checkTurn();

        // Verificar fi de joc
        assertTrue(game.isGameOver());
    }
}
