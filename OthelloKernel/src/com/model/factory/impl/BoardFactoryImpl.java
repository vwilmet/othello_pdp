package com.model.factory.impl;

import java.util.ArrayList;
import java.util.List;

import utils.FactoryHandlerException;
import utils.GameHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.model.BoardImpl;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.PieceFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.player.Player;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class BoardFactoryImpl extends AbstractFactory {

	private static BoardFactoryImpl instance;

	private BoardFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public static BoardFactoryImpl getInstance() {
		if (instance == null)
			instance = new BoardFactoryImpl();

		return instance;
	}

	@Override
	public BoardObservable getBoard(int sizeX, int sizeY, List<Piece> initiaPieces) {
		BoardObservable b = null;
		
		try {
			b = new BoardObservable(new BoardImpl(sizeX, sizeY, initiaPieces));
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		return b;
	}
	
	@Override
	public BoardObservable getInitialBoard(int sizeX, int sizeY) throws FactoryHandlerException {	
		BoardObservable b = null;
		
		PieceFactory pFacto = FactoryProducer.getPieceFactory();
		
		List<Piece> initialPieces = new ArrayList<Piece>();
		
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < 2; j ++){
				if (i == 0)
					initialPieces.add(pFacto.getWhitePiece((sizeX - i) /2, (sizeY - j) /2));
				else
					initialPieces.add(pFacto.getBlackPiece((sizeX - i) /2, (sizeY - j) /2));
			}
		}
		
		try {
			b = new BoardObservable(new BoardImpl(sizeX, sizeY, initialPieces));
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Piece getWhitePiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getBlackPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Piece[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getHumanPlayer(String playerLogin, String c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public Player getMachinePlayer(String playerLogin, String c)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public SaveGame getSaveGame(GameSettings gameSettings, String saveFileName) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}

	@Override
	public RestoreGame getRestoreGame(String gameFileName) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.BOARD_FACTORY_REQUIRED_FR);
	}
}
