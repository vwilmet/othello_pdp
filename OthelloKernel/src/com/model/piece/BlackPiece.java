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
