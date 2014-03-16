package com.model.factory;

import java.util.List;

import utils.FactoryHandlerException;

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
import com.model.piece.PieceImpl;
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

	public abstract PieceImpl getWhitePiece(int posX,
			int posY) throws FactoryHandlerException;

	public abstract PieceImpl getBlackPiece(int posX,
			int posY) throws FactoryHandlerException;

	public abstract PieceImpl getEmptyPiece(int posX,
			int posY) throws FactoryHandlerException;

	public abstract PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException;

	public abstract List<Piece> getArrayListOfPiece()
			throws FactoryHandlerException;

	public abstract Player getHumanPlayer(String playerLogin, String c)
			throws FactoryHandlerException;

	public abstract Player getMachinePlayer(String playerLogin, String c)
			throws FactoryHandlerException;

	public abstract BoardObservable getBoard(int sizeX, int sizeY,
			List<PieceImpl> initiaPieces) throws FactoryHandlerException;
	
	public abstract BoardObservable getInitialBoard(int sizeX, int sizeY) throws FactoryHandlerException;

	public abstract GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty)
			throws FactoryHandlerException;

	public abstract SaveGame getSaveGame(GameSettings gameSettings, String saveFileName) throws FactoryHandlerException;

	public abstract RestoreGame getRestoreGame(String gameFileName) throws FactoryHandlerException;
}
