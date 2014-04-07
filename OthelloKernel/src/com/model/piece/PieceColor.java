package com.model.piece;

/**
 * Interface d'utilisation de la couleur d'un pion.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface PieceColor {
	
	/**
	 * Méthode utilisée pour retourner un pion du plateau.
	 * @param piece : Piece, pion à retourner.
	 */
	public void reverse(PieceImpl piece);

	/**
	 * Méthode utile pour l'affichage en terminal.
	 * @return : String, le symbol à afficher à l'écran.
	 */
	public String graphicalDebug();
	
	/**
	 * Methode donnant la couleur d'un pion sur le plateau.
	 * @return int : 0 si EmptyPiece, 1 si WhitePiece, 2 si BlackPiece.
	 */
	public int getColor();
}
