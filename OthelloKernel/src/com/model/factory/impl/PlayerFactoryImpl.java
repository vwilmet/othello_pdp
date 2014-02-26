package com.model.factory.impl;

import java.awt.Color;

import com.controller.GameSettingsController;
import com.model.Board;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.player.HumanPlayer;
import com.model.player.MachinePlayer;
import com.model.player.Player;

/**
 * Fabrique d'un joueur.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class PlayerFactoryImpl extends AbstractFactory  {

	private static PlayerFactoryImpl instance;
	
	private PlayerFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static PlayerFactoryImpl getInstance(){
		if(instance == null)
			instance = new PlayerFactoryImpl();
		
		return instance;
	}
	
	@Override
	public Player getHumanPlayer(String playerLogin, Color c) {
		return new HumanPlayer(playerLogin, c);
	}
	
	@Override
	public Player getMachinePlayer(String playerLogin, Color c) {
		return new MachinePlayer(playerLogin, c);
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
