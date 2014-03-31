package com.model.piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class EmptyPiece implements PieceColor {

	public String toString() {
		return "Ce pion est vide.\n";
	}

	public String graphicalDebug() {
		return "  ";
	}

	@Override
	public void reverse(PieceImpl piece) {}

	@Override
	public int getColor() {
		return 0;
	}
}
