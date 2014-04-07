package com.model.factory.interfaces;

import java.awt.Color;

import utils.FactoryHandlerException;

import com.model.player.Player;

/**
 * Interface de sécurisation de sécurisation de la fabrique d'un joueur.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface PlayerFactory {
	
	/**
	 * Fabrique d'un joueur humain.
	 * @param playerLogin : String, login du joueur.
	 * @param c : String, chaine de caractère représentant la couleur du pion.
	 * @param playerNumber : int, numero du joueur.
	 * @return Player : Joueur humain construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public Player getHumanPlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un joueur machine (IA).
	 * @param playerLogin : String, login du joueur.
	 * @param c : String, chaine de caractère représentant la couleur du pion.
	 * @param playerNumber : int, numero du joueur.
	 * @return Player : Joueur machine construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public Player getMachinePlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException;
}
