package com.model.factory.impl;

import java.awt.Color;

import com.model.Board;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.player.Player;

public class SaveGameFactoryImpl extends AbstractFactory{

	private static SaveGameFactoryImpl instance;
	
	private SaveGameFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static SaveGameFactoryImpl getInstance(){
		if(instance == null)
			instance = new SaveGameFactoryImpl();
		
		return instance;
	}
	
	@Override
	public SaveGame getSaveGame() {
		return new SaveGame();
	}

	@Override
	public Piece getWhitePiece(int width, int height, int posX, int posY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getHumanPlayer(String playerLogin, Color c) {
		// TODO Auto-generated method stub
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
	public RestoreGame getRestoreGame() {
		// TODO Auto-generated method stub
		return null;
	}
}
