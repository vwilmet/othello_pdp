package com.model;

import java.util.Collections;
import java.util.List;

import utils.FactoryHandlerException;
import utils.GameHandlerException;

import com.error_manager.Log;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.PieceFactory;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class Board {

	private final int WIDTH = 10, HEIGHT = 10;
	
	private int sizeX, sizeY;

	private Piece[][] gameBoard;

	private List<Piece> initialPieces;

	public Board(int sizeX, int sizeY, List<Piece> initiaPieces) throws GameHandlerException {

		PieceFactory pieceFacto = FactoryProducer.getPieceFactory();

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

		for (Piece p : initiaPieces) {
			addInitialPiece(p);
		}

		this.completeBoardToPlay();

	}

	public int getSizeX() {
		return this.sizeX;
	}

	public int getSizeY() {
		return this.sizeY;
	}

	public Piece[][] getBoard() {
		return this.gameBoard;
	}

	public List<Piece> getInitialPiece() {
		return Collections.unmodifiableList(this.initialPieces);
	}

	private void addInitialPiece(Piece p) throws GameHandlerException {
		if (p.getColor() instanceof EmptyPiece)
			throw new GameHandlerException(
					GameHandlerException.WRONG_INITIAL_PIECE_COLOR);

		if (this.gameBoard[p.getPosX()][p.getPosY()] != null)
			throw new GameHandlerException(
					GameHandlerException.WRONG_INITIAL_PIECE_POSITION,
					GameHandlerException.WRONG_INITIAL_PIECE_POSITION_FR);

		this.initialPieces.add(p.clone());
		this.gameBoard[p.getPosX()][p.getPosY()] = p;
	}

	private void completeBoardToPlay() {
		PieceFactory pFacto = FactoryProducer.getPieceFactory();

		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.gameBoard[i][j] == null) {
					try {
						Piece p = pFacto.getEmptyPiece(this.WIDTH, this.HEIGHT, i, j);
						this.gameBoard[i][j] = p;
					} catch (FactoryHandlerException e) {
						Log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}

	public String toString() {
		String res = "Taille de l'othellier (nombre de pions sur le plateau) : "
				+ this.sizeX + "x" + this.sizeY + "\n";

		res += "   ";
		for (int k = 0; k < this.sizeY; k++)
			res += (k < 10) ? " " + k + " " : " " + k;
		res += "\n";
		for (int i = 0; i < this.sizeX; i++) {
			res += i + ((i < 10) ? "  |" : " |");
			for (int j = 0; j < this.sizeY; j++) {
				res += this.gameBoard[i][j].getColor().graphicalDebug() + "|";
			}
			res += "\n";
		}
		return res;
	}
}
