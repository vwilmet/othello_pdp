package com.model.factory.impl;

import java.util.List;

import utils.FactoryHandlerException;
import utils.TextManager;

import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.AbstractFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.piece.PieceImpl;
import com.model.player.Player;

/**
 * Fabrique du gestionnaire de chargement d'une partie d'othello.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class RestoreGameFactoryImpl extends AbstractFactory {

	/**
	 * Variable permettant la mise en place du design pattern Singleton
	 */
	private static RestoreGameFactoryImpl instance;

	/**
	 * Constructeur private de la classe (constructeur private pour la mise en place du Singleton). 
	 */
	private RestoreGameFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Methode permettant de récupérer l'unique instance de la classe.
	 * @return RestoreGameFactoryImpl : Instance de la classe.
	 */
	public static RestoreGameFactoryImpl getInstance() {
		if (instance == null)
			instance = new RestoreGameFactoryImpl();

		return instance;
	}

	/**
	 * Fabrique de la classe de chargement d'une partie de jeu.
	 * @return RestoreGame : module de gestiond de chargement d'une partie du jeu.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public RestoreGame getRestoreGame() {
		return new RestoreGame();
	}

	/**
	 * Fabrique d'un pion blanc.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion blanc à construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public PieceImpl getWhitePiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique d'un pion noir.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion noir à construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public PieceImpl getBlackPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique d'un pion vide.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion vide construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public PieceImpl getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique d'une matrice de pion.
	 * @param i : int, taille de la matrice.
	 * @param j : int, taille de la matrice
	 * @return PieceImpl[][]: Matrice de Pion construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 *  Fabrique d'un tableau dynamique de pion.
	 * @return List<Piece> : Liste vide de Pion construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique d'un joueur humain.
	 * @param playerLogin : String, login du joueur.
	 * @param c : String, chaine de caractère représentant la couleur du pion.
	 * @param playerNumber : int, numero du joueur.
	 * @return Player : Joueur humain construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public Player getHumanPlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique d'un joueur machine (IA).
	 * @param playerLogin : String, login du joueur.
	 * @param c : String, chaine de caractère représentant la couleur du pion.
	 * @param playerNumber : int, numero du joueur.
	 * @return Player : Joueur machine construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public Player getMachinePlayer(String playerLogin, String c, int playerNumber)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique d'un plateau de jeu.
	 * @param sizeX : int, Taille du plateau de jeu.
	 * @param sizeY : int, Taille du plateau de jeu.
	 * @param List<Piece> : Ensemble des pieces initiales de la partie.
	 * @return BoardObservable : Plateau de jeu
	 * @throws FactoryHandlerException.
	 */
	@Override
	public BoardObservable getBoard(int sizeX, int sizeY,
			List<Piece> initiaPieces) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}
	
	/**
	 * Fabrique d'un plateau de jeu initial.
	 * @param sizeX : int, Taille du plateau de jeu.
	 * @param sizeY : int, Taille du plateau de jeu.
	 * @return BoardObservable : Plateau de jeu
	 * @throws FactoryHandlerException.
	 */
	@Override
	public BoardObservable getInitialBoard(int sizeX, int sizeY)
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}
	
	/**
	 * Fabrique d'une liste de plateau de jeu.
	 * @return List<BoardObservable> : ensemble de plateaux de jeu.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public List<BoardObservable> getBoardList()
			throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique d'une partie d'othello.
	 * @param player1 : Player, joueur 1 de la partie.
	 * @param player2 : Player, joueur 2 de la partie.
	 * @param gameBoard : BoardObservable, plateau courrant de jeu.
	 * @param artificialIntelligenceThinkingTime : int, temps de réflexion de l'intelligence artificielle.
	 * @param artificialIntelligenceDifficulty : int, difficulté par defaut de l'IA (d'aide).
	 * @param history : List<Piece>, historique de tous les coups joués (si les coups existent).
	 * return GameSettings : modèle d'une partie d'Othello.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public GameSettings getGameSettings(Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty, List<Piece> history) throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}

	/**
	 * Fabrique de la classe de sauvegarde du jeu.
	 * @return SaveGame : module de gestion de sauvegarde d'une partie du jeu.
	 * @throws FactoryHandlerException.
	 */
	@Override
	public SaveGame getSaveGame() throws FactoryHandlerException {
		throw new FactoryHandlerException(
				FactoryHandlerException.WRONG_FACTORY_REFERRED,
				TextManager.RESTORE_GAME_FACTORY_REQUIRED_FR);
	}
}
