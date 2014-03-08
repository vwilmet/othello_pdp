package jnt;

/**
 * Interface définissant les actions du module de BenchMark
 * @author <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 *
 */
public interface BenchMarkResultEvent {

	/**
	 * Méthode appelée à chaque étape majeure du calcul de la performance de la machine afin de prévenir de l'avancement<br/>
	 * Cette méthode est appelée 9 fois maximum en tout
	 * @param progress L'entier contenant le pourcentage d'avancement du calcul complet
	 */
	public void onProgress(int progress);
	/**
	 * Méthode soulevée dès le commencement du calcul
	 */
	public void onStart();
	/**
	 * Evènement soulevé dès que le calcul du BenchMark est terminé
	 * @param result Le résultat du calcul du BenchMark de la machine
	 */
	public void onEnd(BenchMarkResult result);
}
