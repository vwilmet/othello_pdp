package com.controller.interfaces;

import java.awt.Point;

/**
 * Cette interface est utiliser pour communiquer quand le choix du prochain coup du joueur Machine est encapsulé dans le thread
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface IAResponseTimeHandler {
	/**
	 * Cette évènement est soulevé quand l'IA à répondue une position jouable
	 * @param pointChoosen Le point choisie par l'IA
	 */
	public void onIAPositionGiven(Point pointChoosen);
}
