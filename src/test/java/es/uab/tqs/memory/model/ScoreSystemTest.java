package es.uab.tqs.memory.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreSystemTest {

    @Test
    public void testScoreSystemInitialization() {
        ScoreSystem scoreSystem = new ScoreSystem();
        assertEquals(0, scoreSystem.getPoints(), "La puntuació inicial ha de ser 0");
        assertEquals(0, scoreSystem.getAttempts(), "El número inicial d'intents ha de ser 0");
        assertEquals(0, scoreSystem.getConsecutiveSuccesses(), "Els encerts consecutius inicials han de ser 0");
    }
    // Tests de partició equivalents per addPoints

    // Valor positiu
    @Test
    public void testAfegirPuntsPositius() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(10);
        assertEquals(10, sistema.getPoints());
    }

    // Valor 0
    @Test
    public void testAfegirPuntsZero() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(0);
        assertEquals(0, sistema.getPoints());
    }

    // Valor negatiu
    @Test
    public void testAfegirPuntsNegatius() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(5);
        sistema.addPoints(-2);
        assertEquals(3, sistema.getPoints());
    }

    // Tests de valors límit

    // Valor límit: 1
    @Test
    public void testPuntMinimPositiu() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(1);
        assertEquals(1, sistema.getPoints());
    }

    // Valor límit: -1 (mínim negatiu)
    @Test
    public void testPuntMinimNegatiu() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(5);
        sistema.addPoints(-1);
        assertEquals(4, sistema.getPoints());
    }

    // Valor límit: zero després de tenir punts
    @Test
    public void testAfegirZeroAmbPuntsExistents() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(10);
        sistema.addPoints(0);
        assertEquals(10, sistema.getPoints());
    }

    // Cas frontera: restar exactament els punts que es tenen
    @Test
    public void testRestarExactamentElsPuntsActuals() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(7);
        sistema.addPoints(-7);
        assertEquals(0, sistema.getPoints());
    }

    // Valor negatiu desde l'inici
    @Test
    public void testPuntsNegatiusDesdeZero() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(-10);
        assertEquals(0, sistema.getPoints());
    }

    // Valor negatiu després de restar
    @Test
    public void testPuntsNoSonNegatius() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(3);
        sistema.addPoints(-5);
        assertEquals(0, sistema.getPoints());
    }

    @Test
    public void testIncrementAttempts() {
        ScoreSystem scoreSystem = new ScoreSystem();

        scoreSystem.incrementAttempts();
        assertEquals(1, scoreSystem.getAttempts());

        scoreSystem.incrementAttempts();
        assertEquals(2, scoreSystem.getAttempts());
    }

    // Tests de la part d'encerts
    @Test
    public void testRecordSuccessAfegeixPunts() {
        ScoreSystem sistema = new ScoreSystem();
        int puntsInicials = sistema.getPoints();

        sistema.recordSuccess();

        assertTrue(sistema.getPoints() > puntsInicials, "recordSuccess ha d'augmentar la puntuació");
    }

    @Test
    public void testRecordSuccessDonaPuntsPositius() {
        ScoreSystem sistema = new ScoreSystem();
        int puntsInicials = sistema.getPoints();

        sistema.recordSuccess();

        assertTrue(sistema.getPoints() > puntsInicials, "recordSuccess ha de donar punts positius");
    }

    @Test
    public void testMultipleSuccessesDonaMesPunts() {
        ScoreSystem sistema = new ScoreSystem();

        sistema.recordSuccess();
        int puntsDespresPrimer = sistema.getPoints();

        sistema.recordSuccess();
        int puntsDespresSegon = sistema.getPoints();

        int incrementSegon = puntsDespresSegon - puntsDespresPrimer;

        sistema.recordSuccess();
        int puntsDespresTercer = sistema.getPoints();
        int incrementTercer = puntsDespresTercer - puntsDespresSegon;

        assertTrue(incrementTercer > incrementSegon, "Els encerts consecutius han de donar més punts");
    }

    // Tests de la part de fallades
    @Test
    public void testRecordFailureRedueixPunts() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(10); // Posem punts inicials
        int puntsInicials = sistema.getPoints();

        sistema.recordFailure();

        assertTrue(sistema.getPoints() < puntsInicials, "recordFailure ha de reduir la puntuació");
    }

    @Test
    public void testRecordFailureAfectaLaRatxa() {
        ScoreSystem sistema = new ScoreSystem();

        // Dos encerts consecutius per acumular ratxa
        sistema.recordSuccess();
        sistema.recordSuccess();

        // Es trenca la ratxa
        sistema.recordFailure();

        // Nou encert després de l'error que ha de donar menys que el segon encert
        // anterior
        sistema.recordSuccess();
        int puntsFinal = sistema.getPoints();

        // Fem el mateix escenari sense errors per comparar
        ScoreSystem sistemaSenseErrors = new ScoreSystem();
        sistemaSenseErrors.recordSuccess();
        sistemaSenseErrors.recordSuccess();
        sistemaSenseErrors.recordSuccess();
        int puntsSenseErrors = sistemaSenseErrors.getPoints();

        // El sistema amb error ha de tenir menys punts que el sense errors
        assertTrue(puntsFinal < puntsSenseErrors,
                "Un error ha de reduir la puntuació total respecte a una ratxa sense errors");
    }

    // Test de frontera per a múltiples errors consecutius
    @Test
    public void testMultipleFailuresPenalitzenIgual() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(20);

        sistema.recordFailure();
        int puntsDespresPrimerError = sistema.getPoints();

        sistema.recordFailure();
        int puntsDespresSegonError = sistema.getPoints();

        int penalitzacioPrimer = 20 - puntsDespresPrimerError;
        int penalitzacioSegon = puntsDespresPrimerError - puntsDespresSegonError;

        // Verifiquem que les penalitzacions són iguals sense saber el valor exacte
        assertEquals(penalitzacioPrimer, penalitzacioSegon, "Els errors consecutius han de penalitzar igual");
    }

    // Test de frontera per a punts negatius amb varies fallades
    @Test
    public void testMultiplesErradesNoProvoquenNegatiu() {
        ScoreSystem sistema = new ScoreSystem();
        sistema.addPoints(5);

        // Forcem múltiples errors fins assegurar que arribem al límit
        for (int i = 0; i < 10; i++) {
            sistema.recordFailure();
        }

        // Verifiquem si el comportament és correcte
        assertTrue(sistema.getPoints() >= 0, "Els errors no poden deixar punts negatius");
    }

}