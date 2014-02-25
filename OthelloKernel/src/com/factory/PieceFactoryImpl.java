package com.factory;

import java.awt.Color;

import com.controller.GameSettingsController;
import com.error_manager.Log;
import com.model.BlackPiece;
import com.model.Board;
import com.model.EmptyPiece;
import com.model.GameSettings;
import com.model.Piece;
import com.model.Player;
import com.model.RestoreGame;
import com.model.SaveGame;
import com.model.WhitePiece;

/**
 * Fabrique d'un pion.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class PieceFactoryImpl extends Factory{

	private static PieceFactoryImpl instance;
	
	private PieceFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static PieceFactoryImpl getInstance(){
		if(instance == null)
			instance = new PieceFactoryImpl();
		
		return instance;
	}
	
	@Override
	public Piece getPiece(String pieceType, int width, int height, int posX, int posY) {
		if (pieceType == null)
			return null;

		if (pieceType.equalsIgnoreCase("white"))
			return new WhitePiece(width, height, posX, posY);
		else if (pieceType.equalsIgnoreCase("black"))
			return new BlackPiece(width, height, posX, posY);
		else if (pieceType.equalsIgnoreCase("empty"))
			return new EmptyPiece(width, height, posX, posY);
		
		return null;
	}

	@Override
	public Player getPlayer(String playerType, String playerName, Color c) {
		// TODO Auto-generated method stub
		Log.error("");
		//Throw
		return null;
	}

	@Override
	public Board getBoard() {
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

	@Override
	public GameSettingsController getGameSettingsController() {
		// TODO Auto-generated method stub
		return null;
	}
}