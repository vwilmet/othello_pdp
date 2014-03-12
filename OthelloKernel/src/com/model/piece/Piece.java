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
	protected int posX, posY;
	protected PieceColor piece;
	private boolean playable;

	public Piece(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		playable = false;
		this.piece = new EmptyPiece();
	}
	
	public Piece setWhitePiece() {
		this.piece = new WhitePiece();
		this.playable = false;
		return this;
	}
	
	public Piece setBlackPiece() {
		this.piece = new BlackPiece();
		this.playable = false;
		return this;
	}

	public Piece setEmptyPiece() {
		this.piece = new EmptyPiece();
		return this;
	}
	
	public void setPlayable(){
		this.playable = true;
	}
	
	public void setNotPlayable(){
		this.playable = false;
	}
	
	public boolean isPlayable(){
		return this.playable;
	}
	
	public void reverse() {
		this.piece.reverse(this);
	}

	public PieceColor getColor() {
		return piece;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
	
	public String toString() {
		String res = "Position dans la grille : " + this.posX + " : " + this.posY
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
