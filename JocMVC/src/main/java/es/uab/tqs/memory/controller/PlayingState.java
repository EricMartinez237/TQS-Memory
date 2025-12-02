package es.uab.tqs.memory.controller;

import es.uab.tqs.memory.model.Game;
import es.uab.tqs.memory.model.ScoreSystem;
import es.uab.tqs.memory.view.MemoryView;

public class PlayingState implements GameState {
    private final Game game;
    private final ScoreSystem scoreSystem;
    private final MemoryView view;
    
    public PlayingState(Game game, ScoreSystem scoreSystem, MemoryView view) {
        this.game = game;
        this.scoreSystem = scoreSystem;
        this.view = view;
    }

    @Override
    public GameState startGame() {
        return this;
    }

    @Override
    public GameState flipCard(int row, int col) {
        game.flipCard(row, col);
        return this;
    }

    @Override
    public GameState checkTurn() {
        game.checkTurn();
        if (game.isGameOver()) {
            return new GameOverState();
        }
        return this;
    }

    @Override
    public boolean isGameOver() {
        return game.isGameOver();
    }
}
