package es.uab.tqs.memory.controller;

import es.uab.tqs.memory.view.MemoryView;
import es.uab.tqs.memory.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionListener;
import javax.swing.text.View;

public class GameControllerTest {
    // startGame ha de canviar l'estat inicial a PlayingState
    @Test
    public void testStartGameTransitionsToPlayingState() {
        Game game = mock(Game.class);
        ScoreSystem scoreSystem = mock(ScoreSystem.class);
        MemoryView view = mock(MemoryView.class);
        GameController controller = new GameController(game, scoreSystem, view);

        controller.startGame();

        assertFalse(controller.isGameOver());
    }

    // FlipCard en estat PlayingState ha de delegar al model Game
    @Test
    public void testFlipCardDelegatesToGameInPlayingState() {
        Game game = mock(Game.class);
        ScoreSystem scoreSystem = mock(ScoreSystem.class);
        MemoryView view = mock(MemoryView.class);
        GameController controller = new GameController(game, scoreSystem, view);

        controller.startGame();
        controller.flipCard(1, 1);

        verify(game).flipCard(1, 1);
    }

    // checkTurn ha de cridar a isGameOer i actualitzar l'estat
    @Test
    public void testCheckTurnCallsGameIsOver() {
        Game game = mock(Game.class);
        when(game.isGameOver()).thenReturn(true);
        ScoreSystem scoreSystem = mock(ScoreSystem.class);
        MemoryView view = mock(MemoryView.class);
        GameController controller = new GameController(game, scoreSystem, view);
        controller.startGame();
        controller.checkTurn();

        verify(game).isGameOver();
        assertTrue(controller.isGameOver());
    }

    @Test
    public void testCheckTurnStaysInPlayingStateIfNotOver() {
        Game game = mock(Game.class);
        when(game.isGameOver()).thenReturn(false);
        ScoreSystem scoreSystem = mock(ScoreSystem.class);
        MemoryView view = mock(MemoryView.class);

        GameController controller = new GameController(game, scoreSystem, view);
        controller.startGame();

        controller.checkTurn();

        verify(game).checkTurn();

        assertFalse(controller.isGameOver());
    }

    // getCardAt ha de delegar la crida a getCardAt de game
    @Test
    public void testGetCardAtDelegatesToGame() {
        Game game = mock(Game.class);
        Board board = mock(Board.class);
        Card mockCard = mock(Card.class);
        ScoreSystem scoreSystem = mock(ScoreSystem.class);
        MemoryView view = mock(MemoryView.class);

        when(game.getBoard()).thenReturn(board);
        when(board.getCardAt(0, 0)).thenReturn(mockCard);

        GameController controller = new GameController(game, scoreSystem, view);

        assertEquals(mockCard, controller.getCardAt(0, 0));
        verify(board).getCardAt(0, 0);
    }

    //Comprova que initializeCardListeners ha fet la seva feina: 4 crides a addCardListener
    @Test
    public void testStartInteractionLoopInitializesGUIAndListeners() {
        Game game = mock(Game.class);
        ScoreSystem scoreSystem = mock(ScoreSystem.class);
        MemoryView view = mock(MemoryView.class);
        when(view.getBoardRows()).thenReturn(2); 
        when(view.getBoardCols()).thenReturn(2);
    
        GameController controller = new GameController(game, scoreSystem, view);
        controller.startInteractionLoop(); 

        verify(view).setVisible(true);
        verify(view).resetBoard();
        verify(view).setStatusMessage(eq("Joc en marxa! Tria la primera carta."));
        verify(view, times(4)).addCardListener(anyInt(), anyInt(), any(ActionListener.class));  
    }
}
