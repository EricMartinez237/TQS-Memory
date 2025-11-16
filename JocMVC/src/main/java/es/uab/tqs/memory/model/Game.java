package es.uab.tqs.memory.model;

public class Game {

    private Board board;
    private boolean gameOver;
    private int flippedThisTurn;

    public Game(Board board) {
        this.board = board;
        this.gameOver = false;
        this.flippedThisTurn = 0;
    }

    public void flipCard(int row, int col) {

    }

    public void checkTurn() {

    }

    public boolean isGameOver() {
        return false;
    }
}
