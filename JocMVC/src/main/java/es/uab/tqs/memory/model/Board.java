package es.uab.tqs.memory.model;

public class Board {
    private Card[][] cards;

    public Board(int rows, int cols) {
        cards = new Card[rows][cols];
        char value = 'A';

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cards[r][c] = new Card(String.valueOf(value));
                value++;
                if (value > 'Z')
                    value = 'A';
            }
        }
    }

    public Card[][] getCards() {
        return cards;
    }

    public Card getCard(int row, int col) {
        return cards[row][col];
    }

    public boolean checkPair(int row1, int col1, int row2, int col2) {
        return false;
    }

    public boolean isGameOver() {
        return false;
    }

}
