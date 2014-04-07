package com.controller.interfaces;

import com.model.BoardObservable;
import com.model.GameSettings;

/**
 * Cette interface contient les méthodes permettant au contrôleur de savoir que certain évènements sont arrivés
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface NotifyGameControllerGraphical {
	/**
	 * Evènement soulevé lorsque l'utilisateur à terminé l'instantiation de la nouvelle partie
	 * @param valid booléen qui détermine si l'initialisation est correct ou non
	 * @param game Le nouveau model associé à l'initialisation  
	 */
	public void initGameFinished(boolean valid, GameSettings game);
	/**
	 * Evènement soulevé lorsque l'utilisateur à terminer de choisir la nouvelle position
	 * @param valid booléen qui détermine si le choix de la position est correct ou non
	 * @param board La board représentant l'othellier choisi par l'utilisateur
	 * @param position La position dans l'historique correspondant à l'othellier choisi 
	 */
	public void chooseGameBoardFinished(boolean valid, BoardObservable board, int position);
}