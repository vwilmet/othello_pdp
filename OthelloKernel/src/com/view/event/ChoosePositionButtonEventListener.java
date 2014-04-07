package com.view.event;

import com.model.Board;

/**
 * Interface contenant les méthodes soulevés par la vue de choix de position
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface ChoosePositionButtonEventListener {
	/**
	 * Méthode soulevé lorsque l'utilisateur valide un Othellier à jouer
	 * @param board La nouvelle board choisie
	 * @param position La position correspondant à la board (indice dans le tableau des boards)
	 */
	public void onValidButtonPressed(Board board, int position);
	/**
	 * Evènement soulever lorsque l'utilisateur annule le choix d'une position
	 */
	public void onCancelButtonPressed();
}
