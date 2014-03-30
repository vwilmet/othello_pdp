package com.model.factory.impl;

import java.util.List;

import utils.FactoryHandlerException;
import utils.TextManager;

import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.piece.PieceImpl;
import com.model.player.Player;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class RestoreGameFactoryImpl extends AbstractFactory {

	private static RestoreGameFactoryImpl instance;

	private RestoreGameFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public static RestoreGameFactoryImpl getInstance() {
		if (instance == null)
			instance = new RestoreGameFactoryImpl();

		return instance;
	}

	@Override
	public RestoreGame getRestoreGame(String gameFileName) {
		return new RestoreGame(gameFileName);
	}

	@Override
	public PieceImpl getWhitePiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public PieceImpl getBlackPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public PieceImpl getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public BoardObservable getBoard(int sizeX, int sizeY,
			List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}
	
	@Override
	public BoardObservable getInitialBoard(int sizeX, int sizeY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty, List<Piece> history) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame(GameSettings gameSettings) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}
}
