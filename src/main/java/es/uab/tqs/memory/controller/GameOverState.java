package es.uab.tqs.memory.controller;

public class GameOverState implements GameState {
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
        return true;
    }
}
