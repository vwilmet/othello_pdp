package com.view.interfaces;

import com.view.event.ChoosePositionButtonEventListener;

/**
 * Interface définissant les actions autorisées par la fenêtre gérant le choix de position
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface ChoosePositionView {
	/**
	 * Méthode qui initialise l'interface de communication pour les buttons de la vue
	 * @param event L'object interface qui sera appelé lors des cliques sur les boutons de la vue
	 */
	public void setButtonListener(ChoosePositionButtonEventListener event);
	/**
	 * Méthode permettant d'afficher la vue
	 */
	public void showFrame();
	/**
	 * Méthode permettant de cacher la vue
	 */
	public void hideFrame();
	/**
	 * Méthode permettant d'autoriser l'affichage des pièces jouables sur l'othellier
	 */
	public void showPlayablePiece(boolean visible);
}
