package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.Board;
import com.model.GameSettings;
import com.model.player.Player;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface GameSettingsFactory {
	public GameSettings getGameSettings(Player player1, Player player2, Board gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) throws FactoryHandlerException;
}
