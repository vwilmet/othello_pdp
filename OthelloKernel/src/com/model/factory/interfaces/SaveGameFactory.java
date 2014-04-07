package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.GameSettings;
import com.model.io.SaveGame;

/**
 * Interface de sécurisation de la fabrique du gestionnaire de sauvegarde.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface SaveGameFactory {
	
	/**
	 * Fabrique de la classe de sauvegarde du jeu.
	 * @return SaveGame : module de gestion de sauvegarde d'une partie du jeu.
	 * @throws FactoryHandlerException.
	 */
	public SaveGame getSaveGame() throws FactoryHandlerException;

}
