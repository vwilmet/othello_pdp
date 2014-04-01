package com.model.factory;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.factory.interfaces.RestoreGameFactory;
import com.model.factory.interfaces.SaveGameFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.piece.PieceImpl;
import com.model.player.Player;

/**
 * Fabrique abstraite du modèle.
 * 
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public abstract class AbstractFactory implements PieceFactory, PlayerFactory,
		BoardFactory, GameSettingsFactory, RestoreGameFactory, SaveGameFactory {

	/**
	 * Fabrique d'un pion blanc.
	 */
	public abstract PieceImpl getWhitePiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un pion noir.
	 */
	public abstract PieceImpl getBlackPiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Pabrique d'un pion vide.
	 */
	public abstract PieceImpl getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'une matrice de pion.
	 */
	public abstract PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un tableau dynamique de pion.
	 */
	public abstract List<Piece> getArrayListOfPiece()
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un joueur humain.
	 */
	public abstract Player getHumanPlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un joueur machine (IA).
	 */
	public abstract Player getMachinePlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un plateau de jeu.
	 */
	public abstract BoardObservable getBoard(int sizeX, int sizeY, List<Piece> initiaPieces)
			throws FactoryHandlerException;
	
	/**
	 * Fabrique d'un plateau de jeu initial.
	 */
	public abstract BoardObservable getInitialBoard(int sizeX, int sizeY) 
			throws FactoryHandlerException;
	
	
	public abstract List<BoardObservable> getBoardList() 
			throws FactoryHandlerException;

	/**
	 * Fabrique d'une partie d'othello.
	 */
	public abstract GameSettings getGameSettings(Player player1, Player player2, 
			BoardObservable gameBoard, int artificialIntelligenceThinkingTime,
			int artificialIntelligenceDifficulty, List<Piece> history)
			throws FactoryHandlerException;

	/**
	 * Fabrique de la classe de sauvegarde du jeu.
	 */
	public abstract SaveGame getSaveGame(GameSettings gameSettings)
			throws FactoryHandlerException;

	/**
	 * Fabrique de la classe de chargement d'une partie de jeu.
	 */
	public abstract RestoreGame getRestoreGame(String gameFileName)
			throws FactoryHandlerException;
}
