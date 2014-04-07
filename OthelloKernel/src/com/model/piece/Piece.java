package com.model.piece;

/**
 * Interface d'utilisation de la classe Piece
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public interface Piece {

	/**
	 * Methode indiquant si la piece est jouable.
	 * @return boolean : true si la piece est jouable, false sinon.
	 */
	public boolean isPlayable();
	
	/**
	 * Accesseur (lecture) sur le type d'un pion.
	 * @return PieceColor : type du pion. 
	 */
	public PieceColor getColor();
	
	/**
	 * Accesseur (lecture) sur la coordonnée (X) du pion dans le plateau.
	 * @return int, corrdonnée (X) du pion sur le plateau.
	 */
	public int getPosX();

	/**
	 * Accesseur (lecture) sur la coordonnée (Y) du pion dans le plateau.
	 * @return int, corrdonnée (Y) du pion sur le plateau.
	 */
	public int getPosY();
	
	/**
	 * Méthode toString permettant d'afficher le contenu d'un pion.
	 * @return String, chaîne de caractère correspondant au contenu d'un pion.
	 */
	public String toString();
	
	/**
	 * Methode permettant de dupliquer l'instance de pion.
	 * @return Piece : pion cloné.
	 */
	public PieceImpl clone();
	
	/**
	 * Méthode permettant de tester si deux pions sont équivalent ou non.
	 * @param obj : Object, objet correspondant au pion à tester.
	 * @return boolean, true si les deux pions sont identiques, false sinon.
	 */
	public boolean equals(Object obj);
}
