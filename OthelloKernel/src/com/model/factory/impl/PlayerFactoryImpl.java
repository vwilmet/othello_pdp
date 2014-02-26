package com.model.factory.impl;

import java.awt.Color;
import java.util.List;

import utils.FactoryHandlerException;

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
	public Piece getWhitePiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int width, int height, int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}
	
	@Override
	public Piece[][] getMatrixPiece(int i, int j) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public Board getBoard(int width, int height, int sizeX, int sizeY, List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}
	
	@Override
	public GameSettings getGameSettings() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(FactoryHandlerException.WRONG_FACTORY_REFERRED, FactoryHandlerException.PLAYER_FACTORY_REQUIRED_FR);
	}

}
