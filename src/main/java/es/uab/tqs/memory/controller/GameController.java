package es.uab.tqs.memory.controller;

import es.uab.tqs.memory.model.Game;
import es.uab.tqs.memory.model.Card;

public class GameController {
    private GameState state;
    private final Game game;

    public GameController(Game game) {
        this.game = game;
        this.state = new InitState(game);
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
