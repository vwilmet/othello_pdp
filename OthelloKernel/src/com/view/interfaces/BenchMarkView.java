package com.view.interfaces;

import com.view.event.BenchMarkViewButtonEventListener;

/**
 * Interface définissant les actions autorisées pour le BenchMark
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface BenchMarkView {
	/**
	 * Méthode qui initialise l'interface de communication
	 * @param event L'object interface qui sera appelé lors des évènements soulevés par le BenchMark
	 */
	public void setButtonListener(BenchMarkViewButtonEventListener event);
	/**
	 * Méthode qui affiche la fenêtre de BenchMark
	 */
	public void showFrame();
	/**
	 * Méthode qui cache la fenêtre de BenchMark
	 */
	public void hideFrame();
	
	/**
	 * Cette méthode lance le calcul de BenchMark sur un thread à part
	 * L'avancement du BenchMark est transmis pas l'intermédiaire de l'interface {@link com.view.event.BenchMarkViewButtonEventListener}
	 */
	public void launchBenchMark();
}
