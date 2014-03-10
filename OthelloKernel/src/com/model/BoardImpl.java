package com.model;

import java.util.Collections;
import java.util.List;

import utils.FactoryHandlerException;
import utils.GameHandlerException;

import com.error_manager.Log;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.PieceFactory;
import com.model.piece.BlackPiece;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class BoardImpl implements Board{
	
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
	private Piece[][] gameBoard;

	/**
	 * Liste contenant les pions initiaux d'une partie.
	 */
	private List<Piece> initialPieces;

	/**
	 * Constructeur de la classe.
	 * @param sizeX : int, taille de la grille suivant les abscisses.
	 * @param sizeY : int, taille de la grille suivant les ordonnées.
	 * @param initiaPieces : List<Piece>, liste des pions initiaux du jeu.
	 * @throws GameHandlerException
	 */
	public BoardImpl(int sizeX, int sizeY, List<Piece> initiaPieces) throws GameHandlerException {

		pieceFacto = FactoryProducer.getPieceFactory();

		if (sizeX > 3 && sizeX < 51)
			this.sizeX = sizeX;
		else
			throw new GameHandlerException(
					GameHandlerException.WRONG_BOARD_SIZE,
					"La taille de votre othellier sur l'axe des abscisses doit être comprise entre 4 et 50.");

		if (sizeY > 3 && sizeY < 51)
			this.sizeY = sizeY;
		else
			throw new GameHandlerException(
					GameHandlerException.WRONG_BOARD_SIZE,
					"La taille de votre othellier sur l'axe des ordonnées doit être comprise entre 4 et 50.");

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

	@Override
	public int getSizeX() {
		return this.sizeX;
	}

	@Override
	public int getSizeY() {
		return this.sizeY;
	}

	@Override
	public Piece[][] getBoard() {
		return this.gameBoard;
	}

	@Override
	public List<Piece> getInitialPiece() {
		return Collections.unmodifiableList(this.initialPieces);
	}

	@Override
	public String toString() {
		String res = "Taille de l'othellier (nombre de pions sur le plateau) : "
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
		this.gameBoard[i][j].reverse();
	}
	
	@Override
	public void setBlackPiece(int i, int j) {
		if(this.gameBoard[i][j].getColor() instanceof EmptyPiece)
			this.gameBoard[i][j].setBlackPiece();
	}

	@Override
	public void setWhitePiece(int i, int j) {
		if(this.gameBoard[i][j].getColor() instanceof EmptyPiece)
			this.gameBoard[i][j].setWhitePiece();
	}

	@Override
	public void setPiecePlayable(int i, int j) {
		this.gameBoard[i][j].setPlayable();
	}

	@Override
	public void setPieceNotPlayable(int i, int j) {
		this.gameBoard[i][j].setNotPlayable();
	}
	
	/**
	 * Méthode de classe permettant de mettre en place le jeu. Il pose le pion sur le plateau et l'ajoute à la liste des pieces initiales du jeu.
	 * @param p : Piece,  pion à ajouter sur le plateau pour jouer.
	 * @throws GameHandlerException
	 */
	private void addInitialPiece(Piece p) throws GameHandlerException {
		if (p.getColor() instanceof EmptyPiece)
			throw new GameHandlerException(
					GameHandlerException.WRONG_INITIAL_PIECE_COLOR);

		if (this.gameBoard[p.getPosX()][p.getPosY()] == null)
			throw new GameHandlerException(
					GameHandlerException.WRONG_INITIAL_PIECE_POSITION);
		
		this.initialPieces.add(p.clone());
		
		if (p.getColor() instanceof WhitePiece)
			this.gameBoard[p.getPosX()][p.getPosY()].setWhitePiece();
		else if (p.getColor() instanceof BlackPiece)
			this.gameBoard[p.getPosX()][p.getPosY()].setBlackPiece();
	}

	/**
	 * Methode de classe initialisant le plateau avec des EmptyPieces. A appeler avant de placer les pions initaux (blanc et noir).
	 */
	private void initialiseBoardToPlay() {
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.gameBoard[i][j] == null) {
					try {
						Piece p = this.pieceFacto.getEmptyPiece(i, j);
						this.gameBoard[i][j] = p;
					} catch (FactoryHandlerException e) {
						Log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}
}