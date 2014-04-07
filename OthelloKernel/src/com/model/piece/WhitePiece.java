package com.model.piece;

/**
 * Class WhitePiece
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class WhitePiece implements PieceColor {
	
	/**
	 * Variable permettant la mise en place du design pattern Singleton
	 */
	public static WhitePiece instance;
	
	/**
	 * Constructeur private de la classe (constructeur private pour la mise en place du Singleton). 
	 */
	private WhitePiece() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Methode permettant de récupérer l'unique instance de la classe.
	 * @return WhitePiece : Instance de la classe.
	 */
	public static WhitePiece getInstance(){
		if(instance == null)
			instance = new WhitePiece();
		return instance;
	}
	
	/**
	 * Méthode de debug pour l'affichage de la couleur d'un pion.
	 */
	public String toString() {
		return "Ce pion est blanc.\n";
	}

	/**
	 * Méthode utile pour l'affichage en terminal.
	 * @return : String, le symbol à afficher à l'écran.
	 */
	@Override
	public String graphicalDebug() {
		return "()";
	}

	/**
	 * Méthode utilisée pour retourner un pion du plateau.
	 * @param piece : Piece, pion à retourner.
	 */
	@Override
	public void reverse(PieceImpl piece) {
		piece.setBlackPiece();
	}

	/**
	 * Methode donnant la couleur d'un pion sur le plateau.
	 * @return int : 0 si EmptyPiece, 1 si WhitePiece, 2 si BlackPiece.
	 */
	@Override
	public int getColor() {
		return 1;
	}
	
	
}
