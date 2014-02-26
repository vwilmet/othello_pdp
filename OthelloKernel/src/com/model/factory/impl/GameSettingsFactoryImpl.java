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
import com.model.piece.Piece;
import com.model.player.Player;

public class GameSettingsFactoryImpl extends AbstractFactory {

	private static GameSettingsFactoryImpl instance;
	
	private GameSettingsFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static GameSettingsFactoryImpl getInstance(){
		if(instance == null)
			instance = new GameSettingsFactoryImpl();
		
		return instance;
	}

	@Override
	public GameSettings getGameSettings() {
		return new GameSettings();
	}
	
	@Override
	public Piece getWhitePiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, Color c) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public Board getBoard(int width, int height, int sizeX, int sizeY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryMessages.GAME_SETTINGS_FACTORY_REQUIRED_FR);
	}
}
