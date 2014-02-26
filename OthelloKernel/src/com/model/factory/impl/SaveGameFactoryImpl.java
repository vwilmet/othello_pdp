package com.model.factory.impl;

import java.awt.Color;

import utils.FactoryHandlerException;

import com.model.Board;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.player.Player;

public class SaveGameFactoryImpl extends AbstractFactory {

	private static SaveGameFactoryImpl instance;
	
	private SaveGameFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static SaveGameFactoryImpl getInstance() {
		if(instance == null)
			instance = new SaveGameFactoryImpl();
		
		return instance;
	}
	
	@Override
	public SaveGame getSaveGame() {
		return new SaveGame();
	}

	@Override
	public Piece getWhitePiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, Color c) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public Board getBoard(int width, int height, int sizeX, int sizeY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.SAVE_GAME_FACTORY_REQUIRED_FR);
	}
}
