package com.model.piece;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class WhitePiece extends Piece {
	
	public WhitePiece(int width, int height, int posX, int posY) {
		super(width, height, posX, posY);
		// TODO Auto-generated constructor stub
	}
	
	public WhitePiece(WhitePiece p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		String res = super.toString();
		res += "Ce pion est blanc.\n";
		return res;
	}

}
