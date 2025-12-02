package es.uab.tqs.memory.controller;

import es.uab.tqs.memory.model.Game;
import es.uab.tqs.memory.model.Card;
import es.uab.tqs.memory.model.ScoreSystem;
import es.uab.tqs.memory.view.MemoryView;


public class GameController {
    private GameState state;
    private final Game game;
    private final ScoreSystem scoreSystem;
    private final MemoryView view;

    public GameController(Game game, ScoreSystem scoreSystem, MemoryView view) {
        this.game = game;
        this.scoreSystem = scoreSystem;
        this.view = view;
        this.state = new InitState(game,scoreSystem, view);
    }

    public void startGame() {
        state = state.startGame();
    }

    public void flipCard(int row, int col) {
        state = state.flipCard(row, col);
    }

    public void checkTurn() {
        state = state.checkTurn();
    }

    public boolean isGameOver() {
        return state.isGameOver();
    }

    public Card getCardAt(int row, int col) {
        return game.getBoard().getCardAt(row, col);
    }
}
