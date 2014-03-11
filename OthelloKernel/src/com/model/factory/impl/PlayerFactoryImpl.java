package com.model.factory.impl;

import java.awt.Color;
import java.awt.font.TextLayout;
import java.util.List;

import utils.FactoryHandlerException;
import utils.GameHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.model.BoardImpl;
import com.model.BoardObservable;
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
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class PlayerFactoryImpl extends AbstractFactory {

	private static PlayerFactoryImpl instance;

	private PlayerFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public static PlayerFactoryImpl getInstance() {
		if (instance == null)
			instance = new PlayerFactoryImpl();

		return instance;
	}

	@Override
	public Player getHumanPlayer(String playerLogin, String c) {
		Player p = null;
		
		try {
			p = new Player(playerLogin, c).setHuman();
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Player getMachinePlayer(String playerLogin, String c) {
		Player p = null;
		
		try {
			 p = new Player(playerLogin, c).setMachine();
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Piece getWhitePiece(int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int posX, int posY) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece[][] getMatrixPiece(int i, int j) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public BoardObservable getBoard(int sizeX, int sizeY,	List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}
	
	@Override
	public BoardObservable getInitialBoard(int sizeX, int sizeY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame(GameSettings gameSettings, String saveFileName) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame(String gameFileName) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}
}
