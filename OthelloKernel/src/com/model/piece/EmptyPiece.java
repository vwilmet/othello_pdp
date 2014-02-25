package com.model.piece;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li><li> Vincent Wilmet </li></ul>
 * @version 1.0
 */
public class EmptyPiece implements PieceColor{

	public String toString(){
		String res = super.toString();
		res += "Ce pion est vide.\n";
		return res;
	}

	@Override
	public void reverse(Piece piece) {
		//TODO
	}
}
