package es.uab.tqs.memory.controller;

import es.uab.tqs.memory.model.Game;

public class PlayingState implements GameState {
    private final Game game;

    public PlayingState(Game game) {
        this.game = game;
    }

    @Override
    public GameState startGame() {
        return this;
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
