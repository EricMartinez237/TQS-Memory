package es.uab.tqs.memory.controller;

public interface GameState {
    GameState startGame();

    GameState flipCard(int row, int col);

    GameState checkTurn();

    boolean isGameOver();
}
