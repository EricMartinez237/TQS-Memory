package es.uab.tqs.memory.model;

public class ScoreSystem {
    private int points;
    private int attempts;
    private int consecutiveSuccesses;

    public ScoreSystem() {
        this.points = 0;
        this.attempts = 0;
        this.consecutiveSuccesses = 0;
    }

    public int getPoints() {
        return points;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getConsecutiveSuccesses() {
        return consecutiveSuccesses;
    }

    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
        if (this.points < 0) {
        this.points = 0;
    }
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public void incrementConsecutiveSuccesses() {
        this.consecutiveSuccesses++;
    }

    public void resetConsecutiveSuccesses() {
        this.consecutiveSuccesses = 0;
    }

    public void recordSuccess() {
        incrementAttempts();
        incrementConsecutiveSuccesses();
    
        int puntsBase = 10;
        int bonus = 0;

        //Bonus amb increment: Per cada encert consecutiu sumem punts base + 5 punts per encert
        if (consecutiveSuccesses > 1) {
            bonus = 5 * (consecutiveSuccesses - 1);
        }
    
        addPoints(puntsBase + bonus);
    }


    public void recordFailure() {
        incrementAttempts();
        resetConsecutiveSuccesses();

        //Penalització per error
         addPoints(-2);
    }
}
