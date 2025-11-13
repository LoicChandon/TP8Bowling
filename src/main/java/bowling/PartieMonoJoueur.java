package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {

	/**
	 * Constructeur
	 */

	private boolean lastWasStrike = false;
	private boolean lastWasSpare = false;

	
	public PartieMonoJoueur() {
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
		if (numeroProchainLancer() == 1) {
			// Premier lancer du tour
			if (nombreDeQuillesAbattues == 10) {
				// Strike
				// Passer au tour suivant
				score(nombreDeQuillesAbattues);
				return false;
			} else {
				// Pas strike, le joueur peut lancer une deuxième fois
				return true;
			}
		} else if (numeroProchainLancer() == 2 && nombreDeQuillesAbattues == 10) {
			// Deuxième lancer du tour et spare
			// Passer au tour suivant
			score(10);
			return false;
		} else if (numeroProchainLancer() == 2) {
			// Deuxième lancer du tour
			// Passer au tour suivant
			score(nombreDeQuillesAbattues);
			return false;
		} else if (numeroTourCourant() == 10 && numeroProchainLancer() == 3) {
			// Troisième lancer (seulement au 10ème tour)
			// Passer au tour suivant
			score(nombreDeQuillesAbattues);
			return false;
		}
		return true;
		//throw new UnsupportedOperationException("Pas encore implémenté");
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score(int nombreDeQuillesAbattues) {
		if (!estTerminee()) {
			return 0;
		}
		return nombreDeQuillesAbattues;
		//throw new UnsupportedOperationException("Pas encore implémenté");
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		throw new UnsupportedOperationException("Pas encore implémenté");
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		throw new UnsupportedOperationException("Pas encore implémenté");
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		throw new UnsupportedOperationException("Pas encore implémenté");
	}

}
