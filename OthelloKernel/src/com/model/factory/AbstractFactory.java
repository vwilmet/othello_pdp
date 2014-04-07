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
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion blanc à construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public abstract PieceImpl getWhitePiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un pion noir.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion noir à construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public abstract PieceImpl getBlackPiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un pion vide.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion vide construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public abstract PieceImpl getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'une matrice de pion.
	 * @param i : int, taille de la matrice.
	 * @param j : int, taille de la matrice
	 * @return PieceImpl[][]: Matrice de Pion construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public abstract PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException;

	/**
	 *  Fabrique d'un tableau dynamique de pion.
	 * @return List<Piece> : Liste vide de Pion construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public abstract List<Piece> getArrayListOfPiece()
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un joueur humain.
	 * @param playerLogin : String, login du joueur.
	 * @param c : String, chaine de caractère représentant la couleur du pion.
	 * @param playerNumber : int, numero du joueur.
	 * @return Player : Joueur humain construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public abstract Player getHumanPlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException;
	
	/**
	 * Fabrique d'un joueur machine (IA).
	 * @param playerLogin : String, login du joueur.
	 * @param c : String, chaine de caractère représentant la couleur du pion.
	 * @param playerNumber : int, numero du joueur.
	 * @return Player : Joueur machine construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public abstract Player getMachinePlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un plateau de jeu.
	 * @param sizeX : int, Taille du plateau de jeu.
	 * @param sizeY : int, Taille du plateau de jeu.
	 * @param List<Piece> : Ensemble des pieces initiales de la partie.
	 * @return BoardObservable : Plateau de jeu
	 * @throws FactoryHandlerException.
	 */
	public abstract BoardObservable getBoard(int sizeX, int sizeY, List<Piece> initiaPieces)
			throws FactoryHandlerException;
	
	/**
	 * Fabrique d'un plateau de jeu initial.
	 * @param sizeX : int, Taille du plateau de jeu.
	 * @param sizeY : int, Taille du plateau de jeu.
	 * @return BoardObservable : Plateau de jeu
	 * @throws FactoryHandlerException.
	 */
	public abstract BoardObservable getInitialBoard(int sizeX, int sizeY) 
			throws FactoryHandlerException;
	
	/**
	 * Fabrique d'une liste de plateau de jeu.
	 * @return List<BoardObservable> : ensemble de plateaux de jeu.
	 * @throws FactoryHandlerException.
	 */
	public abstract List<BoardObservable> getBoardList() 
			throws FactoryHandlerException;

	/**
	 * Fabrique d'une partie d'othello.
	 * @param player1 : Player, joueur 1 de la partie.
	 * @param player2 : Player, joueur 2 de la partie.
	 * @param gameBoard : 
	 */
	public abstract GameSettings getGameSettings(Player player1, Player player2, 
			BoardObservable gameBoard, int artificialIntelligenceThinkingTime,
			int artificialIntelligenceDifficulty, List<Piece> history)
			throws FactoryHandlerException;

	/**
	 * Fabrique de la classe de sauvegarde du jeu.
	 */
	public abstract SaveGame getSaveGame()
			throws FactoryHandlerException;

	/**
	 * Fabrique de la classe de chargement d'une partie de jeu.
	 */
	public abstract RestoreGame getRestoreGame(String gameFileName)
			throws FactoryHandlerException;
}
