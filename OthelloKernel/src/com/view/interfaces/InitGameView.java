package com.view.interfaces;

import com.view.event.InitGameButtonEventListener;

/**
 * Interface définissant les actions autorisées pour la fenêtre d'initialisation 
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface InitGameView {
	/**
	 * Méthode qui initialise l'interface de communication pour les buttons de la vue
	 * @param event L'object interface qui sera appelé lors des cliques sur les boutons de la vue
	 */
	public void setButtonListener(InitGameButtonEventListener event);
	/**
	 * Méthode permettant d'afficher la vue
	 */
	public void showFrame();
	/**
	 * Méthode permettant de cacher la vue
	 */
	public void hideFrame();
}