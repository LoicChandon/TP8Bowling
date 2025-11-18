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
	private int multiplicateurStrikeSpare = 1;
	private int nombreDeQuillesAbattuesSur1Tour = 0;
	private int currentTour = 1;
	private int score = 0;

	
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

		nombreDeQuillesAbattuesSur1Tour += nombreDeQuillesAbattues;

		if (lastWasSpare) {
			// Ajouter les points du lancer au score du spare précédent
			multiplicateurStrikeSpare = 2;
			lastWasSpare = false;
		}
		if (lastWasStrike) {
			// Ajouter les points du lancer au score du strike précédent
			multiplicateurStrikeSpare = 2;
		}

		if (numeroProchainLancer() == 1) {
			// Premier lancer du tour
			if (nombreDeQuillesAbattues == 10) {
				// Strike
				lastWasStrike = true;
				// Passer au tour suivant
				nombreDeQuillesAbattues = nombreDeQuillesAbattues * multiplicateurStrikeSpare;
				score(nombreDeQuillesAbattues);
				finTour();
				return false;
			} else {
				// Pas strike, le joueur peut lancer une deuxième fois
				return true;
			}
		} else if (numeroProchainLancer() == 2 && nombreDeQuillesAbattuesSur1Tour == 10) {
			// Deuxième lancer du tour et spare
			lastWasSpare = true;
			lastWasStrike = false;
			// Passer au tour suivant
			nombreDeQuillesAbattues = nombreDeQuillesAbattues * multiplicateurStrikeSpare;
			score(nombreDeQuillesAbattues);
			finTour();
			return false;
		} else if (numeroProchainLancer() == 2) {
			// Deuxième lancer du tour
			lastWasStrike = false;
			// Passer au tour suivant
			score(nombreDeQuillesAbattues);
			finTour();
			return false;
		} else if (numeroTourCourant() == 10 && numeroProchainLancer() == 3) {
			// Troisième lancer (seulement au 10ème tour)
			// Finir le jeu
			score(nombreDeQuillesAbattues);
			estTerminee();
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
		score += nombreDeQuillesAbattues;
		return nombreDeQuillesAbattues;
		//throw new UnsupportedOperationException("Pas encore implémenté");
	}

	public void finTour() {
		nombreDeQuillesAbattuesSur1Tour = 0;
		multiplicateurStrikeSpare = 1;
		currentTour++;
		if (currentTour > 10) {
			estTerminee();
		}
	}
	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		//throw new UnsupportedOperationException("Pas encore implémenté");
		nombreDeQuillesAbattuesSur1Tour = 0;
		multiplicateurStrikeSpare = 1;
		return true;
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
		throw new UnsupportedOperationException("Pas encore implémenté");
	}

}
