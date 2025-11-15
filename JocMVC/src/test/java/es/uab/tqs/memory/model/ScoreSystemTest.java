package es.uab.tqs.memory.model;

import junit.framework.TestCase;

public class ScoreSystemTest extends TestCase {

    public void testScoreSystemInitialization() {
        ScoreSystem scoreSystem = new ScoreSystem();
        assertEquals("La puntuació inicial ha de ser 0", 0, scoreSystem.getPoints());
        assertEquals("El número inicial d'intents ha de ser 0", 0, scoreSystem.getAttempts());
        assertEquals("Els encerts consecutius inicials han de ser 0", 0, scoreSystem.getConsecutiveSuccesses());
    }

    public void testAddPoints() {
        ScoreSystem scoreSystem = new ScoreSystem();
        
        scoreSystem.addPoints(10);
        assertEquals(10, scoreSystem.getPoints());
        
        scoreSystem.addPoints(5);
        assertEquals(15, scoreSystem.getPoints());
        
        // Punts negatius
        scoreSystem.addPoints(-3);
        assertEquals(12, scoreSystem.getPoints());
    }

    public void testIncrementAttempts() {
        ScoreSystem scoreSystem = new ScoreSystem();
        
        scoreSystem.incrementAttempts();
        assertEquals(1, scoreSystem.getAttempts());
        
        scoreSystem.incrementAttempts();
        assertEquals(2, scoreSystem.getAttempts());
    }
}