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

    public void checkTurnAndGameOver() {
        Board board = new Board(2, 2);
        Game game = new Game(board);

        // Simular torn: flip 2 cartes que són parella
        game.flipCard(0, 0);
        game.flipCard(1, 1);
        game.checkTurn();

        // Segon torn
        game.flipCard(1, 0);
        game.flipCard(0, 1);
        game.checkTurn();

        // Verificar fi de joc
        assertTrue(game.isGameOver());
    }
}
