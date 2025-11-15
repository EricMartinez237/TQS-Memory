package es.uab.tqs.memory.model;

import junit.framework.TestCase;

public class ScoreSystemTest extends TestCase {

    public void testScoreSystemInitialization() {
        ScoreSystem scoreSystem = new ScoreSystem();
        assertEquals("La puntuació inicial ha de ser 0", 0, scoreSystem.getPoints());
        assertEquals("El número inicial d'intents ha de ser 0", 0, scoreSystem.getAttempts());
        assertEquals("Els encerts consecutius inicials han de ser 0", 0, scoreSystem.getConsecutiveSuccesses());
    }
     // Tests de partició equivalents per addPoints

     //Valor positiu
    public void testAfegirPuntsPositius() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(10);
        assertEquals(10, sistema.getPoints());
    }
    
    //Valor 0
    public void testAfegirPuntsZero() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(0);
        assertEquals(0, sistema.getPoints());
    }
    
    //Valor negatiu
    public void testAfegirPuntsNegatius() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(5);
        sistema.addPoints(-2);
        assertEquals(3, sistema.getPoints());
    }

    // Tests de valors límit

    // Valor límit: 1
    public void testPuntMinimPositiu() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(1);
        assertEquals(1, sistema.getPoints());
    }

    // Valor límit: -1 (mínim negatiu)
    public void testPuntMinimNegatiu() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(5);
        sistema.addPoints(-1);
        assertEquals(4, sistema.getPoints());
    }

    // Valor límit: zero després de tenir punts
    public void testAfegirZeroAmbPuntsExistents() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(10);
        sistema.addPoints(0);
        assertEquals(10, sistema.getPoints());
    }

    // Cas frontera: restar exactament els punts que es tenen
    public void testRestarExactamentElsPuntsActuals() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(7);
        sistema.addPoints(-7);
        assertEquals(0, sistema.getPoints());
    }

    //Valor negatiu desde l'inici
    public void testPuntsNegatiusDesdeZero() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(-10);
        assertEquals(0, sistema.getPoints());
    }

    //Valor negatiu després de restar
    public void testPuntsNoSonNegatius() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(3);
        sistema.addPoints(-5);
        assertEquals(0, sistema.getPoints());
    }
    
    
    public void testIncrementAttempts() {
        ScoreSystem scoreSystem = new ScoreSystem();
        
        scoreSystem.incrementAttempts();
        assertEquals(1, scoreSystem.getAttempts());
        
        scoreSystem.incrementAttempts();
        assertEquals(2, scoreSystem.getAttempts());
    }
}