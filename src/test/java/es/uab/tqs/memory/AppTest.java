package es.uab.tqs.memory;

import org.junit.jupiter.api.Test;

import es.uab.tqs.memory.App;
import es.uab.tqs.memory.controller.GameController;
import es.uab.tqs.memory.model.Board;
import es.uab.tqs.memory.model.Game;
import es.uab.tqs.memory.model.ScoreSystem;
import es.uab.tqs.memory.view.MemoryView;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {


    @Test
    public void testGameControllerIsWiredCorrectly() {
        App app = new App();
        app.initializeGame();

        GameController controller = app.getController();

        //Verificar que el Controller té la instància de Game
        assertEquals(app.getGame(), controller.getGame(), 
            "El GameController ha d'utilitzar la mateixa instància de Game que l'App va crear");

        //Verificar que el Controller té la instància de ScoreSystem
        assertEquals(app.getGame().getScoreSystem(), controller.getScoreSystem(), 
            "El GameController ha de tenir la referència al ScoreSystem");

        //Verificar que el Controller té la instància de MemoryView
        assertEquals(app.getView(), controller.getView(), 
            "El GameController ha d'estar connectat a la View creada per l'App");
    }

    @Test
    public void testViewIsWiredCorrectly() {
        App app = new App();
        app.initializeGame();
        
        // Comprovar la connexió de view amb el model
        assertEquals(app.getGame(), app.getView().getGame(), 
            "La View ha d'estar connectada a la instància de Game");
    }
} 

