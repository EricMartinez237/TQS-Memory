package es.uab.tqs.memory.controller;

import es.uab.tqs.memory.model.Game;
import es.uab.tqs.memory.model.Card;
import es.uab.tqs.memory.model.ScoreSystem;
import es.uab.tqs.memory.view.MemoryView;

import java.awt.event.ActionEvent; // Per gestionar esdeveniments del Timer
import java.awt.event.ActionListener; // Per gestionar els clics dels botons
import javax.swing.Timer; // Per crear un retard sense bloquejar la GUI


public class GameController {
    private GameState state;
    private final Game game;
    private final ScoreSystem scoreSystem;
    private final MemoryView view;

    public GameController(Game game, ScoreSystem scoreSystem, MemoryView view) {
        this.game = game;
        this.scoreSystem = scoreSystem;
        this.view = view;
        this.state = new InitState(game,scoreSystem, view);
    }

    public void startGame() {
        state = state.startGame();
    }

    public void flipCard(int row, int col) {
        state = state.flipCard(row, col);
    }

    public void checkTurn() {
        state = state.checkTurn();
    }

    public boolean isGameOver() {
        return state.isGameOver();
    }

    public Card getCardAt(int row, int col) {
        return game.getBoard().getCardAt(row, col);
    }

    public Game getGame() {
        return this.game;
    }

    public ScoreSystem getScoreSystem() {
        return this.scoreSystem;
    }

    public MemoryView getView() {
        return this.view;
    }

    public void startInteractionLoop() {
        view.setVisible(true);
        view.resetBoard();
        initializeCardListeners(); 
        view.setStatusMessage("Joc en marxa! Tria la primera carta.");
    }

    private void initializeCardListeners() {
        int rows = view.getBoardRows();
        int cols = view.getBoardCols();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                final int r = row;
                final int c = col;
            
                view.addCardListener(row, col, e -> handleCardClick(r, c));
            }
        }

    } 

    public void handleCardClick(int row, int col) { 
       
        game.flipCard(row, col); 
        Card clickedCard = game.getBoard().getCardAt(row, col);
        view.updateCard(row, col, clickedCard);
    
        if (game.getFlippedCount() == 2) {

            view.disableAllCards();
            view.setStatusMessage("Veient resultat...");

            // Timer per esperar 1 segon (1000ms)
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                
                    game.checkTurn(); 
                
                    view.resetBoard(); 
                    view.updateScoreInfo(); 
                
                    if (game.isGameOver()) {
                        view.showGameOverMessage();
                    } else {
                        view.enableAllCards(); 
                        view.setStatusMessage("Torn resolt. Tria la primera carta.");
                    }
                
                    ((Timer)evt.getSource()).stop(); 
                }
            });
            timer.setRepeats(false); 
            timer.start();
    
        } else if (game.getFlippedCount() == 1) {
            view.setStatusMessage("Tria la segona carta.");
        }
    }

}
