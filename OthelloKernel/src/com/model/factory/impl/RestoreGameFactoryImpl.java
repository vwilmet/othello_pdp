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
	public RestoreGame getRestoreGame() {
		return new RestoreGame();
	}

	@Override
	public Piece getWhitePiece(int width, int height, int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public BoardObservable getBoard(int sizeX, int sizeY,
			List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings(Player player1, Player player2, BoardImpl gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

}
