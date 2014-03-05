package com.model.factory.impl;

import java.awt.Color;
import java.awt.font.TextLayout;
import java.util.List;

import utils.FactoryHandlerException;
import utils.TextManager;

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
	public Player getHumanPlayer(String playerLogin, Color c) {
		return new Player(playerLogin, c).setHuman();
	}

	@Override
	public Player getMachinePlayer(String playerLogin, Color c) {
		return new Player(playerLogin, c).setMachine();
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
	public GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.PLAYER_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
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
