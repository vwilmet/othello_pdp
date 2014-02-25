package com.model.factory;

import java.awt.Color;

import com.error_manager.Log;
import com.model.Board;
import com.model.GameSettings;
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
	
	public static PieceFactoryImpl getInstance(){
		if(instance == null)
			instance = new PieceFactoryImpl();
		
		return instance;
	}
	
	public Piece getWhitePiece(int width, int height, int posX, int posY) {
		return new WhitePiece(width, height, posX, posY);
	}
	
	public Piece getBlackPiece(int width, int height, int posX, int posY){
		return new BlackPiece(width, height, posX, posY);
	}
	
	public Piece getEmptyPiece(int width, int height, int posX, int posY){
		return new EmptyPiece(width, height, posX, posY);
	}
	
	@Override
	public Player getHumanPlayer(String playerLogin, Color c) {
		// TODO Auto-generated method stub
		//Log.error("");
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