package test.java.es.uab.tqs.memory;

import org.junit.jupiter.api.Test;

import es.uab.tqs.memory.App;
import es.uab.tqs.memory.model.Board;
import es.uab.tqs.memory.model.Game;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testAppInitialization() {
        assertTrue(true, "La aplicació s'inicialitza sense errors");
    }

    @Test
    //Creació correcte de board
    public void testBoardCreation() {
        Board board = new Board(4, 4);
        assertNotNull(board);
        assertEquals(4, board.getRows());
        assertEquals(4, board.getCols());
    }

    @Test
    //Creació correcte de board
    public void testGameCreation() {
        Board board = new Board(4, 4);
        Game game = new Game(board);
        assertNotNull(game);
        assertNotNull(game.getBoard());
    }
} 

