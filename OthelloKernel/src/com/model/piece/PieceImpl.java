package com.model.piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class PieceImpl implements Cloneable, Piece {
	
	protected int posX, posY;
	protected PieceColor piece;
	private boolean playable;

	public PieceImpl(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		playable = false;
		this.piece = new EmptyPiece();
	}
	
	public PieceImpl setWhitePiece() {
		this.piece = new WhitePiece();
		this.playable = false;
		return this;
	}
	
	public PieceImpl setBlackPiece() {
		this.piece = new BlackPiece();
		this.playable = false;
		return this;
	}

	public PieceImpl setEmptyPiece() {
		this.piece = new EmptyPiece();
		return this;
	}
	
	public void setPlayable(){
		this.playable = true;
	}
	
	public void setNotPlayable(){
		this.playable = false;
	}
	
	@Override
	public boolean isPlayable(){
		return this.playable;
	}
	
	public void reverse() {
		this.piece.reverse(this);
	}

	@Override
	public PieceColor getColor() {
		return piece;
	}
	
	@Override
	public int getPosX() {
		return this.posX;
	}
	
	@Override
	public int getPosY() {
		return this.posY;
	}
	
	@Override
	public String toString() {
		/*String res = "Position dans la grille : " + this.posX + " : " + this.posY
				+ "\n";
		res += this.piece.toString();*/
		String res = "[" + this.posX + "|" + this.posY + "] => " + this.piece.getClass();
		return res;
	}

	@Override
	public PieceImpl clone() {
		try {
			return (PieceImpl) super.clone();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		PieceImpl piece = (PieceImpl)obj;
	    if(piece.getPosX() == this.posX && piece.getPosY() == this.posY)
	    	return true;
	    	
	    return false;
	}
}
