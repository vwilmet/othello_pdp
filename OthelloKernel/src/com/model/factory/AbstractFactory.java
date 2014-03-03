package com.model.factory;

import java.awt.Color;
import java.util.List;

import utils.FactoryHandlerException;

import com.model.BoardImpl;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.factory.interfaces.RestoreGameFactory;
import com.model.factory.interfaces.SaveGameFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.player.Player;

/**
 * Fabrique abstraite du modèle.
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public abstract class AbstractFactory implements PieceFactory, PlayerFactory,
		BoardFactory, GameSettingsFactory, RestoreGameFactory, SaveGameFactory {

	public abstract Piece getWhitePiece(int posX,
			int posY) throws FactoryHandlerException;

	public abstract Piece getBlackPiece(int posX,
			int posY) throws FactoryHandlerException;

	public abstract Piece getEmptyPiece(int posX,
			int posY) throws FactoryHandlerException;

	public abstract Piece[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException;

	public abstract List<Piece> getArrayListOfPiece()
			throws FactoryHandlerException;

	public abstract Player getHumanPlayer(String playerLogin, Color c)
			throws FactoryHandlerException;

	public abstract Player getMachinePlayer(String playerLogin, Color c)
			throws FactoryHandlerException;

	public abstract BoardObservable getBoard(int sizeX, int sizeY,
			List<Piece> initiaPieces) throws FactoryHandlerException;

	public abstract GameSettings getGameSettings(Player player1, Player player2, BoardImpl gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty)
			throws FactoryHandlerException;

	public abstract SaveGame getSaveGame() throws FactoryHandlerException;

	public abstract RestoreGame getRestoreGame(String gameFileName) throws FactoryHandlerException;
}
