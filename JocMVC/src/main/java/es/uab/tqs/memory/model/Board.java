package es.uab.tqs.memory.model;

import java.util.List;

public class Board {
    private int rows;
    private int cols;
    private Card[][] cards;

    public Board(int rows, int cols) {
        if (rows <= 0 || cols <= 0 || (rows * cols) % 2 != 0) {
            throw new IllegalArgumentException(
                    "Les dimensions han de ser positives i el número total de cartes parell.");
        }

        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public int getTotalCards() {
        return this.rows * this.cols;
    }

    public List<Card> getCards() {
        return null;
    }

    public Card getCardAt(int row, int col) {
        return null;
    }

    public void flipCard(int row, int col) {

    }
}