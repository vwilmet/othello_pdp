package com.model.piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class WhitePiece implements PieceColor {

	public static WhitePiece instance;
	
	private WhitePiece() {}
	
	public static WhitePiece getInstance(){
		if(instance == null)
			instance = new WhitePiece();
		return instance;
	}
	
	public String toString() {
		return "Ce pion est blanc.\n";
	}

	public String graphicalDebug() {
		return "()";
	}

	@Override
	public void reverse(PieceImpl piece) {
		piece.setBlackPiece();
	}

	@Override
	public int getColor() {
		return 1;
	}
	
	
}
