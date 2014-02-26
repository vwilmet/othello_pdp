package com.model.factory.impl;

import java.awt.Color;

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
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class PieceFactoryImpl extends AbstractFactory{

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
	public Piece getBlackPiece(int width, int height, int posX, int posY){
		return (new Piece(width, height, posX, posY)).setPieceState(new BlackPiece());
	}
	
	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY){
		return (new Piece(width, height, posX, posY)).setPieceState(new EmptyPiece());
	}
	
	@Override
	public Player getHumanPlayer(String playerLogin, Color c) { //throws FactoryHandlerException {
		//Log.error();
		//Throw
		return null;
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c) {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public Board getBoard(int width, int height, int sizeX, int sizeY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameSettings getGameSettings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaveGame getSaveGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestoreGame getRestoreGame() {
		// TODO Auto-generated method stub
		return null;
	}
}