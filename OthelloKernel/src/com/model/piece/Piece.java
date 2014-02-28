package com.model.piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class Piece implements Cloneable {

	/*
	 * Piece p = Piece.BlackPiece(...);
	 */
	protected int width, height;
	protected int posX, posY;
	protected PieceColor piece;

	public Piece(int width, int height, int posX, int posY) {
		this.width = width;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		this.piece = new EmptyPiece();
	}

	public Piece setWhitePiece() {
		this.piece = new WhitePiece();
		return this;
	}
	
	public Piece setBlackPiece() {
		this.piece = new BlackPiece();
		return this;
	}

	public void reverse() {
		this.piece.reverse(this);
	}

	public PieceColor getColor() {
		return piece;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public String toString() {
		String res = "Taille : " + this.width + " * " + this.height + "\n";
		res += "Position dans la grille : " + this.posX + " : " + this.posY
				+ "\n";
		res += this.piece.toString();
		return res;
	}

	public Piece clone() {
		try {
			return (Piece) super.clone();
		} catch (Exception e) {
			return null;
		}
	}
}
