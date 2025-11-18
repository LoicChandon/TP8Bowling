package bowling;

public class Tour {
    int numTour;
    int numLancer;
    boolean strike;
    boolean spare;

    public Tour(int numTour) {
        this.numTour = numTour;
        this.numLancer = 1;
        this.strike = false;
        this.spare = false;
    }
}
