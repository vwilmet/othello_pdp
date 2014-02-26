package com.model.factory.impl;

import java.awt.Color;

import utils.FactoryHandlerException;

import com.error_manager.Log;
import com.model.Board;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.factory.FactoryMessages;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.BlackPiece;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.model.player.Player;

/**
 * Fabrique d'un pion.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class PieceFactoryImpl extends AbstractFactory {

private static PieceFactoryImpl instance;
	
	private PieceFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static PieceFactoryImpl getInstance() {
		if(instance == null)
			instance = new PieceFactoryImpl();
		
		return instance;
	}
	
	@Override
	public Piece getWhitePiece(int width, int height, int posX, int posY) {
		return (new Piece(width, height, posX, posY)).setPieceState(new WhitePiece());
	}
	
	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY) {
		return (new Piece(width, height, posX, posY)).setPieceState(new BlackPiece());
	}
	
	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY) {
		return (new Piece(width, height, posX, posY)).setPieceState(new EmptyPiece());
	}
	
	@Override
	public Player getHumanPlayer(String playerLogin, Color c) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.PIECE_FACTORY_REQUIRED_FR);
	}	

	@Override
	public Board getBoard(int width, int height, int sizeX, int sizeY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.PIECE_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.PIECE_FACTORY_REQUIRED_FR);
	}
}