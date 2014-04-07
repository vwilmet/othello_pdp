package com.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.FactoryHandlerException;
import utils.GameHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.PieceFactory;
import com.model.piece.BlackPiece;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.model.piece.PieceImpl;
import com.model.piece.WhitePiece;

/**
 * Classe BoardImpl
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class BoardImpl implements Board, Cloneable{

	/**
	 * Factory pour la création des pièces.
	 */
	protected PieceFactory pieceFacto;

	/**
	 * Taille de la Board suivant les Abscisses / Ordonnées
	 */
	protected int sizeX, sizeY;

	/**
	 * Structure représentant le plateau de jeu.
	 */
	protected PieceImpl[][] gameBoard;

	/**
	 * Liste contenant les pions initiaux d'une partie.
	 */
	protected List<Piece> initialPieces;

	/**
	 * Listes des pions.
	 */
	protected List<Piece> playablePiece;
	protected List<Piece> blackPiece;
	protected List<Piece> whitePiece;

	/**
	 * Constructeur de la classe.
	 * @param sizeX : int, taille de la grille suivant les abscisses.
	 * @param sizeY : int, taille de la grille suivant les ordonnées.
	 * @param initiaPieces : List<Piece>, liste des pions initiaux du jeu.
	 * @throws GameHandlerException
	 */
	public BoardImpl(int sizeX, int sizeY, List<Piece> initiaPieces) throws GameHandlerException {

		this.pieceFacto = FactoryProducer.getPieceFactory();
		this.playablePiece = new ArrayList<Piece>();
		this.whitePiece = new ArrayList<Piece>();
		this.blackPiece = new ArrayList<Piece>();

		if (sizeX >= GameSettings.BOARD_MIN_SIZE_X && sizeX <= GameSettings.BOARD_MAX_SIZE_X)
			this.sizeX = sizeX;
		else
			throw new GameHandlerException(
					GameHandlerException.WRONG_BOARD_SIZE, TextManager.WRONG_SIZE_X_FR);

		if (sizeY >= GameSettings.BOARD_MIN_SIZE_Y && sizeY <= GameSettings.BOARD_MAX_SIZE_X)
			this.sizeY = sizeY;
		else
			throw new GameHandlerException(
					GameHandlerException.WRONG_BOARD_SIZE, TextManager.WRONG_SIZE_Y_FR);

		try {
			this.gameBoard = pieceFacto.getMatrixPiece(this.sizeX, this.sizeY);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			this.initialPieces = pieceFacto.getArrayListOfPiece();
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		this.initialiseBoardToPlay();

		for (Piece p : initiaPieces) {
			addInitialPiece(p);
		}
	}

	/**
	 * Accesseur sur la taille d'un othellier (Axe des abscisses).
	 * @return : int la taille de l'othellier suivant l'axe des abscisses.
	 */
	@Override
	public int getSizeX() {
		return this.sizeX;
	}

	/**
	 * Accesseur sur la taille d'un othellier (Axe des ordonnées).
	 * @return : int la taille de l'othellier suivant l'axe des ordonnées.
	 */
	@Override
	public int getSizeY() {
		return this.sizeY;
	}
	
	/**
	 * Accesseur sur la matrice représentant l'othellier à un instant de jeu.
	 * @return int [][] : La matrice remplie de pièces.
	 */
	@Override
	public Piece[][] getBoard() {
		return this.gameBoard;
	}
	
	/**
	 * Accesseur sur la liste des pions initials l'une partie.
	 * @return List<Piece> : liste chainée des pions initiaux de la partie.
	 */
	@Override
	public List<Piece> getInitialPiece() {
		return Collections.unmodifiableList(this.initialPieces);
	}
	
	/**
	 * Permet l'affichge de l'othellier dans la console.
	 * @return String : Une chaine de caractère prête pour l'affichage en console.
	 */
	@Override
	public String toString() {
		String res = TextManager.PIECE_NUMBER_ON_BOARD_FR
				+ this.sizeX + "x" + this.sizeY + "\n";

		res += "   ";
		for (int k = 0; k < this.sizeX; k++)
			res += (k < 10) ? " " + k + " " : " " + k;
		res += "\n";

		for (int i = 0; i < this.sizeY; i++) {
			res += i + ((i < 10) ? "  |" : " |");
			for (int j = 0; j < this.sizeX; j++) {
				res += this.gameBoard[j][i].getColor().graphicalDebug() + "|";
			}
			res += "\n";
		}
		return res;
	}
	
	/**
	 * Méthode permettant de changer la couleur du pion correspondant aux positions en paramètre
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void reverse(int i, int j) {
		if(this.gameBoard[i][j].getColor() instanceof WhitePiece){
			this.whitePiece.remove(this.gameBoard[i][j]);
			this.blackPiece.add(this.gameBoard[i][j]);
		}else if(this.gameBoard[i][j].getColor() instanceof BlackPiece){
			this.blackPiece.remove(this.gameBoard[i][j]);
			this.whitePiece.add(this.gameBoard[i][j]);
		}

		this.gameBoard[i][j].reverse();
	}
	
	/**
	 * Méthode permettant de poser une pièce de couleur noire
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setBlackPiece(int i, int j) {
		if(this.gameBoard[i][j].getColor() instanceof EmptyPiece){
			this.gameBoard[i][j].setBlackPiece();

			this.blackPiece.add(this.gameBoard[i][j]);
			this.playablePiece.remove(this.gameBoard[i][j]);
		}
	}
	
	/**
	 * Méthode permettant de poser une pièce blanche
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setWhitePiece(int i, int j) {
		if(this.gameBoard[i][j].getColor() instanceof EmptyPiece){
			this.gameBoard[i][j].setWhitePiece();

			this.whitePiece.add(this.gameBoard[i][j]);
			this.playablePiece.remove(this.gameBoard[i][j]);
		}
	}
	
	/**
	 * Méthode permettant de poser une pièce vide
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setEmptyPiece(int i, int j) {
		if(!(this.gameBoard[i][j].getColor() instanceof EmptyPiece))
			this.gameBoard[i][j].setEmptyPiece();
	}
	
	/**
	 * Méthode permettant de définir une pièce jouable
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setPiecePlayable(int i, int j) {
		this.gameBoard[i][j].setPlayable();
		if(!this.playablePiece.contains((Piece)this.gameBoard[i][j]))
			this.playablePiece.add(this.gameBoard[i][j]);
	}
	
	/**
	 * Méthode permettant de définir une pièce non jouable
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setPieceNotPlayable(int i, int j) {
		this.gameBoard[i][j].setNotPlayable();
		playablePiece.remove(this.gameBoard[i][j]);
	}
	
	/**
	 * Méthode retournant la liste des pièces de couleur blanche
	 * @return La liste de pièce
	 */
	@Override
	public List<Piece> getWhitePieces(){
		return Collections.unmodifiableList(this.whitePiece);
	}
	
	/**
	 * Méthode permettant de définir la liste des pièces initials
	 * @param piece La liste de pièce
	 */
	public void setInitialPieces(List<Piece> piece){
		this.initialPieces = piece;
	}
	
	/**
	 * Méthode permettant de définir la liste des pièces jouables
	 * @param piece La liste de pièce
	 */
	public void setPlayablePieces(List<Piece> piece){
		this.playablePiece = piece;
	}
	
	/**
	 * Méthode permettant de définir la liste des pièces de couleurs noires
	 * @param piece La liste de pièce
	 */
	public void setBlackPieces(List<Piece> piece){
		this.blackPiece = piece;
	}
	
	/**
	 * Méthode permettant de définir la liste des pièces de couleurs blanches
	 * @param piece La liste de pièce
	 */
	public void setWhitePieces(List<Piece> piece){
		this.whitePiece = piece;
	}
	
	/**
	 * Méthode retournant la liste des pièce de couleur noire
	 * @return La liste de pièce
	 */
	@Override
	public List<Piece> getBlackPieces(){
		return Collections.unmodifiableList(this.blackPiece);
	}
	
	/**
	 * Méthode retournant la liste des pièce jouables
	 * @return La liste de pièce
	 */
	@Override
	public List<Piece> getPlayablePieces(){
		return Collections.unmodifiableList(this.playablePiece);
	}

	/**
	 * Cette méthode réinitialise les coups jouables
	 */
	@Override
	public void resetPlayablePosition(){
		for(Piece p : playablePiece)
			this.gameBoard[p.getPosX()][p.getPosY()].setNotPlayable();
				playablePiece.clear();
	}

	/**
	 * Méthode de classe permettant de mettre en place le jeu. Il pose le pion sur le plateau et l'ajoute à la liste des pieces initiales du jeu.
	 * @param p : Piece,  pion à ajouter sur le plateau pour jouer.
	 * @throws GameHandlerException
	 */
	protected void addInitialPiece(Piece p) throws GameHandlerException {
		if (p.getColor() instanceof EmptyPiece)
			throw new GameHandlerException(
					GameHandlerException.WRONG_INITIAL_PIECE_COLOR);

		if (this.gameBoard[p.getPosX()][p.getPosY()] == null)
			throw new GameHandlerException(
					GameHandlerException.WRONG_INITIAL_PIECE_POSITION);

		this.initialPieces.add(p.clone());

		if (p.getColor() instanceof WhitePiece){
			this.gameBoard[p.getPosX()][p.getPosY()].setWhitePiece();
			this.whitePiece.add(p);
		}else if (p.getColor() instanceof BlackPiece){
			this.gameBoard[p.getPosX()][p.getPosY()].setBlackPiece();
			this.blackPiece.add(p);
		}
	}

	/**
	 * Methode de classe initialisant le plateau avec des EmptyPieces. A appeler avant de placer les pions initaux (blanc et noir).
	 */
	protected void initialiseBoardToPlay() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.gameBoard[i][j] == null) {
					try {
						this.gameBoard[i][j] = this.pieceFacto.getEmptyPiece(i, j);
					} catch (FactoryHandlerException e) {
						Log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Cette méthode permet de définir l'othellier
	 * @param board La nouvelle board (othellier)
	 */
	public void setBoard(PieceImpl[][] board){
		this.gameBoard = board;
	}
	
	/**
	 * Cette méthode clone l'othellier
	 * @return Un clone de l'objet appellant la méthode
	 */
	@Override
	public Board clone() {
		try {
			BoardImpl board = (BoardImpl) super.clone();

			List<Piece> _initialPieces = new ArrayList<Piece>(),
					_playablePiece = new ArrayList<Piece>(),
					_blackPiece = new ArrayList<Piece>(),
					_whitePiece = new ArrayList<Piece>();

			for(Piece p : this.initialPieces){
				_initialPieces.add(p.clone());
			}

			for(Piece p : this.playablePiece){
				_playablePiece.add(p.clone());
			}

			for(Piece p : this.blackPiece){
				_blackPiece.add(p.clone());
			}

			for(Piece p : this.whitePiece){
				_whitePiece.add(p.clone());
			}

			board.setInitialPieces(_initialPieces);
			board.setPlayablePieces(_playablePiece);
			board.setBlackPieces(_blackPiece);
			board.setWhitePieces(_whitePiece);

			PieceImpl[][] gameBoard = new PieceImpl[this.sizeX][this.sizeY];

			for(int i = 0; i < this.sizeX; i++)
				for(int j = 0; j < this.sizeY; j++)
					gameBoard[i][j] = this.gameBoard[i][j].clone();

			board.setBoard(gameBoard);

			return board;
		} catch (Exception e) {
			return null;
		}
	}
}