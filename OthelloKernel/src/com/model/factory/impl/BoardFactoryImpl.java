package com.model.factory.impl;

import java.awt.Color;
import java.util.List;

import utils.FactoryHandlerException;
import utils.GameHandlerException;

import com.error_manager.Log;
import com.model.Board;
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
public class BoardFactoryImpl extends AbstractFactory {

	private static BoardFactoryImpl instance;

	private BoardFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public static BoardFactoryImpl getInstance() {
		if (instance == null)
			instance = new BoardFactoryImpl();

		return instance;
	}

	@Override
	public Board getBoard(int width, int height, int sizeX, int sizeY,
			List<Piece> initiaPieces) {
		Board b = null;

		try {
			b = new Board(width, height, sizeX, sizeY, initiaPieces);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Piece getWhitePiece(int width, int height, int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.BOARD_FACTORY_REQUIRED_FR);
	}
}