package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.piece.Piece;
import com.model.player.Player;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface GameSettingsFactory {
	public GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty, List<Piece> history) throws FactoryHandlerException;
}
