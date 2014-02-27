package com.model.factory.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import utils.FactoryHandlerException;

import com.model.Board;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.BlackPiece;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.model.player.Player;

/**
 * Fabrique d'un pion.
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class PieceFactoryImpl extends AbstractFactory {

	private static PieceFactoryImpl instance;

	private PieceFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public static PieceFactoryImpl getInstance() {
		if (instance == null)
			instance = new PieceFactoryImpl();

		return instance;
	}

	@Override
	public Piece getWhitePiece(int width, int height, int posX, int posY) {
		return (new Piece(width, height, posX, posY))
				.setPieceState(new WhitePiece());
	}

	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY) {
		return (new Piece(width, height, posX, posY))
				.setPieceState(new BlackPiece());
	}

	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY) {
		return (new Piece(width, height, posX, posY))
				.setPieceState(new EmptyPiece());
	}

	@Override
	public Piece[][] getMatrixPiece(int i, int j) {
		return new Piece[i][j];
	}

	@Override
	public List<Piece> getArrayListOfPiece() {
		return new ArrayList<Piece>();
	}

	@Override
	public Player getHumanPlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public Board getBoard(int sizeX, int sizeY,	List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings(Player player1, Player player2, Board gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				FactoryHandlerException.PIECE_FACTORY_REQUIRED_FR);
	}

}