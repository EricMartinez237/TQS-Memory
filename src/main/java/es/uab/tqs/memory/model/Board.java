package es.uab.tqs.memory.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private int rows;
    private int cols;
    private Card[][] cards;

    public Board(int rows, int cols) {
        validateDimensions(rows, cols);
        this.rows = rows;
        this.cols = cols;
        // Inicialitzar matriu de cartes
        this.cards = new Card[rows][cols];
        initializeCards();
    }

    // Constructor per evitar aleatorietat(per poder fer bé els tests)
    public Board(int rows, int cols, Card[][] fixedCards) {
        this.rows = rows;
        this.cols = cols;
        this.cards = fixedCards; // No fem shuffle
    }

    private void validateDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0 || (rows * cols) % 2 != 0) {
            throw new IllegalArgumentException(
                    "Les dimensions han de ser positives i el número total de cartes parell.");
        }
    }

    private void initializeCards() {
        // Crear parelles de cartes amb valors duplicats
        int totalPairs = (rows * cols) / 2;
        List<Card> temp = new ArrayList<>();
        for (int i = 1; i <= totalPairs; i++) {
            String value = String.valueOf(i);
            temp.add(new Card(value));
            temp.add(new Card(value));
        }

        Collections.shuffle(temp);

        int k = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cards[r][c] = temp.get(k++);
            }
        }
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
        List<Card> allCards = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                allCards.add(cards[r][c]);
            }
        }
        return allCards;
    }

    public Card getCardAt(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Fila o columna fora de rang");
        }
        return cards[row][col];
    }

    public void flipCard(int row, int col) {
        Card c = getCardAt(row, col);
        if (!c.isMatched()) {
            c.flip();
        }
    }

    public boolean checkPair(Card c1, Card c2) {
        if (c1.equals(c2)) {
            c1.setMatched(true);
            c2.setMatched(true);
            return true;
        }
        return false;
    }

    public boolean isComplete() {
        for (Card c : getCards()) {
            if (!c.isMatched()) {
                return false;
            }
        }
        return true;
    }

    public List<int[]> getFlippedUnmatched() {
        List<int[]> positions = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Card card = cards[r][c];
                if (card.isFaceUp() && !card.isMatched()) {
                    positions.add(new int[] { r, c });
                }
            }
        }
        return positions;
    }

    public void flipBackUnmatchedCards() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Card card = cards[r][c];
                if (card.isFaceUp() && !card.isMatched()) {
                    card.flip();
                }
            }
        }
    }

    public int getFlippedCount() {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (cards[r][c].isFaceUp()) {
                    count++;
                }
            }
        }
        return count;
    }
}