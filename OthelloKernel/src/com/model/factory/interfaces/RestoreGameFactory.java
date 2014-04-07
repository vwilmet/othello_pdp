package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.io.RestoreGame;

/**
 * Interface de sécurisation de la fabrique du gestionnaire de chargement d'une partie d'Othello.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface RestoreGameFactory {
	
	/**
	 * Fabrique de la classe de chargement d'une partie de jeu.
	 * @return RestoreGame : module de gestiond de chargement d'une partie du jeu.
	 * @throws FactoryHandlerException.
	 */
	public RestoreGame getRestoreGame() throws FactoryHandlerException;
}
