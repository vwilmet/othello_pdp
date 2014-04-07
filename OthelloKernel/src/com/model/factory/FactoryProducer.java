package com.model.factory;

import com.model.factory.impl.BoardFactoryImpl;
import com.model.factory.impl.GameSettingsFactoryImpl;
import com.model.factory.impl.PieceFactoryImpl;
import com.model.factory.impl.PlayerFactoryImpl;
import com.model.factory.impl.RestoreGameFactoryImpl;
import com.model.factory.impl.SaveGameFactoryImpl;

/**
 * Gestionnaire de fabrique de la fabrique abstraite.
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class FactoryProducer {

	/**
	 * Méthode statique permettant de recupérer une fabrique de pion.
	 * @return
	 */
	public static AbstractFactory getPieceFactory() {
		return PieceFactoryImpl.getInstance();
	}

	/**
	 * Méthode statique permettant de recupérer une fabrique de joueur.
	 * @return
	 */
	public static AbstractFactory getPlayerFactory() {
		return PlayerFactoryImpl.getInstance();
	}

	/**
	 * Méthode statique permettant de recupérer une fabrique de plateau de jeu.
	 * @return
	 */
	public static AbstractFactory getBoardFactory() {
		return BoardFactoryImpl.getInstance();
	}

	/**
	 * Méthode statique permettant de recupérer une fabrique de partie.
	 * @return
	 */
	public static AbstractFactory getGameSettingsFactory() {
		return GameSettingsFactoryImpl.getInstance();
	}

	/**
	 * Méthode statique permettant de recupérer une fabrique de module de chargement d'une partie.
	 * @return
	 */
	public static AbstractFactory getRestoreGameFactory() {
		return RestoreGameFactoryImpl.getInstance();
	}

	/**
	 * Méthode statique permettant de recupérer une fabrique de module de sauvegarde d'une partie. 
	 * @return
	 */
	public static AbstractFactory getSaveGameFactory() {
		return SaveGameFactoryImpl.getInstance();
	}
}
