package bowling;

public class Tour {
    int numTour;
    int numLancer;
    boolean strike;
    boolean spare;

    private int[] lancers;
    private int score;
    private int remainingBonus;

    public Tour(int numTour) {
        this.numTour = numTour;
        this.numLancer = 1;
        this.strike = false;
        this.spare = false;
        this.lancers = new int[] {0, 0, 0};
    }

    /**
     * Enregistre un lancer pour ce tour.
     * @param quilles nombre de quilles abattues
     * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon
     */
    public boolean enregistreLancer(int quilles) {
        if (numTour < 10) {
            if (numLancer == 1) {
                lancers[0] = quilles;
                if (quilles == 10) {
                    strike = true;
                    // pas de 2ème lancer pour ce tour
                    // base score = 10, attente de 2 bonus
                    score = 10;
                    remainingBonus = 2;
                    return false;
                } else {
                    numLancer = 2;
                    // update partial score
                    score = lancers[0];
                    return true;
                }
            } else { // numLancer == 2
                lancers[1] = quilles;
                // fin du tour
                score = lancers[0] + lancers[1];
                if (score == 10) {
                    spare = true;
                    remainingBonus = 1;
                } else {
                    remainingBonus = 0;
                }
                return false;
            }
        } else {
            // 10ème tour : jusqu'à 3 lancers possibles
            if (numLancer == 1) {
                lancers[0] = quilles;
                if (quilles == 10) {
                    strike = true;
                }
                numLancer = 2;
                score = lancers[0];
                return true;
            } else if (numLancer == 2) {
                lancers[1] = quilles;
                score = lancers[0] + lancers[1];
                if (lancers[0] == 10 || lancers[0] + lancers[1] == 10) {
                    // droit à un 3ème lancer
                    numLancer = 3;
                    return true;
                } else {
                    // pas de 3ème lancer
                    remainingBonus = 0;
                    return false;
                }
            } else { // numLancer == 3
                lancers[2] = quilles;
                score = lancers[0] + lancers[1] + lancers[2];
                remainingBonus = 0;
                return false;
            }
        }
    }

    public int getNumLancer() {
        return numLancer;
    }

    public int[] getLancers() {
        return lancers;
    }

    public void applyBonus(int quilles) {
        if (remainingBonus > 0) {
            score += quilles;
            remainingBonus--;
        }
    }

    public int getScore() {
        return score;
    }

    public boolean needsBonus() {
        return remainingBonus > 0;
    }
}
