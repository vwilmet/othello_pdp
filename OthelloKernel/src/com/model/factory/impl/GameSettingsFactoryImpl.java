package com.model.factory.impl;

import java.awt.Color;
import java.util.List;

import utils.FactoryHandlerException;

import com.model.BoardImpl;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
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
	public GameSettings getGameSettings(Player player1, Player player2, BoardImpl gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) {
		return new GameSettings(player1, player2, gameBoard, artificialIntelligenceThinkingTime, artificialIntelligenceDifficulty);
	}

	@Override
	public Piece getWhitePiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public BoardObservable getBoard(int sizeX, int sizeY,
			List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame(String gameFileName) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}
}
