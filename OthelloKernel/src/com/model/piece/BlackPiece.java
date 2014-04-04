package com.model.piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class BlackPiece implements PieceColor {

	public static BlackPiece instance;
	
	private BlackPiece() {}
	
	public static BlackPiece getInstance(){
		if(instance == null)
			instance = new BlackPiece();
		return instance;
	}
	
	public String toString() {
		return "Ce pion est noir.\n";
	}

	public String graphicalDebug() {
		return "##";
	}

	@Override
	public void reverse(PieceImpl piece) {
		piece.setWhitePiece();
	}

	@Override
	public int getColor() {
		return 2;
	}
}
