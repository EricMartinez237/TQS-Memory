package es.uab.tqs.memory.model;

import java.util.List;

public class Game {

    private Board board;
    private int flippedThisTurn;
    private ScoreSystem scoreSystem = new ScoreSystem();

    public Game(Board board) {
        this.board = board;
        this.flippedThisTurn = 0;
    }

    public Game(Board board, ScoreSystem scoreSystem) {
        this.board = board;
        this.scoreSystem = scoreSystem;
        this.flippedThisTurn = 0;
    }

    public Board getBoard() {
        return board;
    }

    public ScoreSystem getScoreSystem() {
        return scoreSystem;
    }

    public void flipCard(int row, int col) {
        if (canFlip(row, col)) {
            board.flipCard(row, col);
            flippedThisTurn++;
        }
    }

    public int getFlippedCount() {
        return flippedThisTurn;
    }

    // Helper petit per validar
    private boolean canFlip(int row, int col) {
        return flippedThisTurn < 2 && !board.getCardAt(row, col).isMatched();
    }

    // Verificar parella i reseteja torn
    public void checkTurn() {
        if (flippedThisTurn == 2) {
            List<int[]> flipped = board.getFlippedUnmatched();
            if (flipped.size() == 2) {
                Card c1 = board.getCardAt(flipped.get(0)[0], flipped.get(0)[1]);
                Card c2 = board.getCardAt(flipped.get(1)[0], flipped.get(1)[1]);

                boolean success = board.checkPair(c1, c2);

                if (success) {
                    scoreSystem.recordSuccess();
                } else {
                    scoreSystem.recordFailure();
                }
            }
        }
        board.flipBackUnmatchedCards();
        flippedThisTurn = 0; // Reset torn
    }

    public boolean isGameOver() {
        return board.isComplete();
    }
}
