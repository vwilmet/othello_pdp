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
 * 
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
	PieceFactory pieceFacto;

	/**
	 * Taille de la Board suivant les Abscisses / Ordonnées
	 */
	private int sizeX, sizeY;

	/**
	 * Structure représentant le plateau de jeu.
	 */
	private PieceImpl[][] gameBoard;
	
	/**
	 * Liste contenant les pions initiaux d'une partie.
	 */
	private List<Piece> initialPieces;

	private ArrayList<Piece> playablePiece;
	private ArrayList<Piece> blackPiece;
	private ArrayList<Piece> whitePiece;

	/**
	 * Constructeur de la classe.
	 * @param sizeX : int, taille de la grille suivant les abscisses.
	 * @param sizeY : int, taille de la grille suivant les ordonnées.
	 * @param initiaPieces : List<Piece>, liste des pions initiaux du jeu.
	 * @throws GameHandlerException
	 */
	public BoardImpl(int sizeX, int sizeY, List<PieceImpl> initiaPieces) throws GameHandlerException {

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

		for (PieceImpl p : initiaPieces) {
			addInitialPiece(p);
		}
	}

	@Override
	public int getSizeX() {
		return this.sizeX;
	}

	@Override
	public int getSizeY() {
		return this.sizeY;
	}

	@Override
	public PieceImpl[][] getBoard() {
		return this.gameBoard;
	}

	@Override
	public List<Piece> getInitialPiece() {
		return Collections.unmodifiableList(this.initialPieces);
	}

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

	@Override
	public void setBlackPiece(int i, int j) {
		if(this.gameBoard[i][j].getColor() instanceof EmptyPiece){
			this.gameBoard[i][j].setBlackPiece();
			
			this.blackPiece.add(this.gameBoard[i][j]);
			this.playablePiece.remove(this.gameBoard[i][j]);
		}
	}

	@Override
	public void setWhitePiece(int i, int j) {
		if(this.gameBoard[i][j].getColor() instanceof EmptyPiece){
			this.gameBoard[i][j].setWhitePiece();
			
			this.whitePiece.add(this.gameBoard[i][j]);
			this.playablePiece.remove(this.gameBoard[i][j]);
		}
	}

	@Override
	public void setEmptyPiece(int i, int j) {
		if(!(this.gameBoard[i][j].getColor() instanceof EmptyPiece))
			this.gameBoard[i][j].setEmptyPiece();
	}

	@Override
	public void setPiecePlayable(int i, int j) {
		this.gameBoard[i][j].setPlayable();
		this.playablePiece.add(this.gameBoard[i][j]);
	}

	@Override
	public void setPieceNotPlayable(int i, int j) {
		this.gameBoard[i][j].setNotPlayable();
		playablePiece.remove(this.gameBoard[i][j]);
	}

	@Override
	public ArrayList<Piece> getWhitePieces(){
		return this.whitePiece;
	}

	@Override
	public ArrayList<Piece> getBlackPieces(){
		return this.blackPiece;
	}

	@Override
	public ArrayList<Piece> getPlayablePieces(){
		return this.playablePiece;
	}

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
	private void addInitialPiece(PieceImpl p) throws GameHandlerException {
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
	private void initialiseBoardToPlay() {
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
	
	@Override
	public Board clone() {
		try {
			return (Board) super.clone();
		} catch (Exception e) {
			return null;
		}
	}
}