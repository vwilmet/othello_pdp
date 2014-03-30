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
public class GameSettingsFactoryImpl extends AbstractFactory {

	private static GameSettingsFactoryImpl instance;

	private GameSettingsFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public static GameSettingsFactoryImpl getInstance() {
		if (instance == null)
			instance = new GameSettingsFactoryImpl();

		return instance;
	}

	@Override
	public GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty, List<Piece> history) {
		return new GameSettings(player1, player2, gameBoard, artificialIntelligenceThinkingTime, artificialIntelligenceDifficulty, history);
	}

	@Override
	public PieceImpl getWhitePiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public PieceImpl getBlackPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public PieceImpl getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public BoardObservable getBoard(int sizeX, int sizeY,
			List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}
	
	@Override
	public BoardObservable getInitialBoard(int sizeX, int sizeY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame(GameSettings gameSettings) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame(String gameFileName) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}
}
