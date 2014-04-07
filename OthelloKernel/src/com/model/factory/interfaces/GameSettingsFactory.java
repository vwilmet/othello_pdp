package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.piece.Piece;
import com.model.player.Player;

/**
 * Interface de sécurisation de la fabrique d'une partie d'othello.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface GameSettingsFactory {
	
	/**
	 * Fabrique d'une partie d'othello.
	 * @param player1 : Player, joueur 1 de la partie.
	 * @param player2 : Player, joueur 2 de la partie.
	 * @param gameBoard : BoardObservable, plateau courrant de jeu.
	 * @param artificialIntelligenceThinkingTime : int, temps de réflexion de l'intelligence artificielle.
	 * @param artificialIntelligenceDifficulty : int, difficulté par defaut de l'IA (d'aide).
	 * @param history : List<Piece>, historique de tous les coups joués (si les coups existent).
	 * return GameSettings : modèle d'une partie d'Othello.
	 * @throws FactoryHandlerException.
	 */
	public GameSettings getGameSettings(Player player1, Player player2, 
			BoardObservable gameBoard, int artificialIntelligenceThinkingTime,
			int artificialIntelligenceDifficulty, List<Piece> history)
					throws FactoryHandlerException;
}
