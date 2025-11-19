package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {

	private Tour[] tours;
	private int currentTour;

	public PartieMonoJoueur() {
		this.tours = new Tour[10];
		for (int i = 0; i < 10; i++) {
			this.tours[i] = new Tour(i + 1);
		}
		this.currentTour = 1;
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (estTerminee()) {
			throw new IllegalStateException("La partie est terminée");
		}

		Tour t = tours[currentTour - 1];
		// First, apply this roll as a bonus to previous frames that still need bonuses
		for (int i = 0; i < currentTour - 1; i++) {
			tours[i].applyBonus(nombreDeQuillesAbattues);
		}

		boolean doitRelancer = t.enregistreLancer(nombreDeQuillesAbattues);
		if (!doitRelancer) {
			currentTour++;
		}
		return doitRelancer;
	}

	/**
	 * Calcule le score courant à partir des lancers enregistrés.
	 * Si des lancers manquent pour calculer un bonus, ceux-ci sont considérés comme 0.
	 * @return score courant
	 */
	public int score() {
		int total = 0;
		for (int i = 0; i < 10; i++) {
			total += tours[i].getScore();
		}
		return total;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		return currentTour > 10;
	}

	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		if (estTerminee()) {
			return 0;
		}
		return currentTour;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		if (estTerminee()) {
			return 0;
		}
		return tours[currentTour - 1].getNumLancer();
	}

}
