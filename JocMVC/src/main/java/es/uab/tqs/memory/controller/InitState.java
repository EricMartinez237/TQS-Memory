package es.uab.tqs.memory.controller;

import es.uab.tqs.memory.model.Game;
import es.uab.tqs.memory.model.ScoreSystem;
import es.uab.tqs.memory.view.MemoryView;

public class InitState implements GameState {
    private final Game game;
    private final ScoreSystem scoreSystem;
    private final MemoryView view;

    public InitState(Game game, ScoreSystem scoreSystem, MemoryView view) {
        this.game = game;
        this.scoreSystem = scoreSystem;
        this.view = view;
    }

    @Override
    public GameState startGame() {
        return new PlayingState(game, scoreSystem, view);
    }

    @Override
    public GameState flipCard(int row, int col) {
        return this;
    }

    @Override
    public GameState checkTurn() {
        return this;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
