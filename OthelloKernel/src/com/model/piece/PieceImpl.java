package com.model.piece;

/**
 * Classe PieceImpl
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class PieceImpl implements Cloneable, Piece {
	
	/**
	 * Positions du pion dans le plateau de jeu.
	 */
	protected int posX, posY;
	
	/**
	 * Couleur du pion.
	 */
	protected PieceColor piece;
	
	/**
	 * Booleen indiquant si la piece est jouable.
	 */
	private boolean playable;

	/**
	 * Constructeur de classe.
	 * @param posX : int position en X dans le plateau de jeu.
	 * @param posY : int position en Y dans le plateau de jeu.
	 */
	public PieceImpl(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		playable = false;
		this.piece = EmptyPiece.getInstance();
	}
	
	/**
	 * Accesseur en écriture pour modifier la couleur d'un pion.
	 * @return Piece : le pion modifié en pion blanc.
	 */
	public PieceImpl setWhitePiece() {
		this.piece = null;
		this.piece = WhitePiece.getInstance();
		this.playable = false;
		return this;
	}
	
	/**
	 * Accesseur en écriture pour modifier la couleur d'un pion.
	 * @return Piece : le pion modifié en pion noir.
	 */
	public PieceImpl setBlackPiece() {
		this.piece = null;
		this.piece = BlackPiece.getInstance();
		this.playable = false;
		return this;
	}

	/**
	 * Accesseur en écriture pour modifier la couleur d'un pion.
	 * @return Piece : le pion modifié en pion vide.
	 */
	public PieceImpl setEmptyPiece() {
		this.piece = null;
		this.piece = EmptyPiece.getInstance();
		return this;
	}
	
	/**
	 * Accesseur (écriture)  pour autoriser le changement d'état du pion.
	 */
	public void setPlayable(){
		this.playable = true;
	}
	
	/**
	 * Accesseur (écriture)  pour empécher le changement d'état du pion.
	 */
	public void setNotPlayable(){
		this.playable = false;
	}
	
	/**
	 * Methode indiquant si la piece est jouable.
	 * @return boolean : true si la piece est jouable, false sinon.
	 */
	@Override
	public boolean isPlayable(){
		return this.playable;
	}
	
	/**
	 * Permet de changer la couleur du pion (noir -> blanc ou blanc -> noir).
	 */
	public void reverse() {
		this.piece.reverse(this);
	}

	/**
	 * Accesseur (lecture) sur le type d'un pion.
	 * @return PieceColor : type du pion. 
	 */
	@Override
	public PieceColor getColor() {
		return piece;
	}
	
	/**
	 * Accesseur (lecture) sur la coordonnée (X) du pion dans le plateau.
	 * @return int, corrdonnée (X) du pion sur le plateau.
	 */
	@Override
	public int getPosX() {
		return this.posX;
	}
	
	/**
	 * Accesseur (lecture) sur la coordonnée (Y) du pion dans le plateau.
	 * @return int, corrdonnée (Y) du pion sur le plateau.
	 */
	@Override
	public int getPosY() {
		return this.posY;
	}
	
	/**
	 * Méthode toString permettant d'afficher le contenu d'un pion.
	 * @return String, chaîne de caractère correspondant au contenu d'un pion.
	 */
	@Override
	public String toString() {
		String res = "[" + this.posX + "|" + this.posY + "] => " + this.piece.getClass();
		return res;
	}

	/**
	 * Methode permettant de dupliquer l'instance de pion.
	 * @return Piece : pion cloné.
	 */
	@Override
	public PieceImpl clone() {
		try {
			return (PieceImpl) super.clone();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Méthode permettant de tester si deux pions sont équivalent ou non.
	 * @param obj : Object, objet correspondant au pion à tester.
	 * @return boolean, true si les deux pions sont identiques, false sinon.
	 */
	@Override
	public boolean equals(Object obj) {
		PieceImpl piece = (PieceImpl)obj;
	    if(piece.getPosX() == this.posX && piece.getPosY() == this.posY)
	    	return true;
	    	
	    return false;
	}
}
