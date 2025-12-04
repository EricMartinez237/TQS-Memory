package es.uab.tqs.memory;

import es.uab.tqs.memory.controller.GameController;
import es.uab.tqs.memory.model.Board;
import es.uab.tqs.memory.model.Game;
import es.uab.tqs.memory.model.ScoreSystem;
import es.uab.tqs.memory.view.MemoryView;

public class App 
{
    private Game game;
    private GameController controller;
    private MemoryView view;

    public void initializeGame() {
    
        Board board = new Board(4, 4);
        ScoreSystem scores = new ScoreSystem();

        this.game = new Game(board, scores);

        this.view = new MemoryView(this.game, scores);

        this.controller = new GameController(this.game, scores, this.view);

        this.controller.startGame(); 
    }

    public void run(){
        if(this.controller != null){
            this.controller.startInteractionLoop();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.initializeGame();
        app.run();
    }

    public Game getGame() { return game; }
    public GameController getController() { return controller; }
    public MemoryView getView() { return view; }
}
