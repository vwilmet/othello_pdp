package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.piece.Piece;
import com.model.piece.PieceImpl;

/**
 * Interface d'utilisation de la classe board.
 * @author 	<ul>
 * 				<li>Vincent Wilmet</li>
 * 				<li>Benjamin Letourneau</li>
 * 			</ul>
 * @version 1.0
 */
public interface Board {

	/**
	 * Accesseur sur la taille d'un othellier (Axe des abscisses).
	 * @return : int la taille de l'othellier suivant l'axe des abscisses.
	 */
	public int getSizeX();

	/**
	 * Accesseur sur la taille d'un othellier (Axe des ordonnées).
	 * @return : int la taille de l'othellier suivant l'axe des ordonnées.
	 */
	public int getSizeY();

	/**
	 * Accesseur sur la matrice représentant l'othellier à un instant de jeu.
	 * @return int [][] : La matrice remplie de pièces.
	 */
	public Piece[][] getBoard();

	/**
	 * Accesseur sur la liste des pions initials l'une partie.
	 * @return List<Piece> : liste chainée des pions initiaux de la partie.
	 */
	public List<Piece> getInitialPiece();

	/**
	 * Méthode permettant de changer la couleur du pion correspondant aux positions en paramètre
	 * @param i La position en X
	 * @param j La position en Y
	 */
	public void reverse(int i, int j);

	/**
	 * Méthode permettant de poser une pièce de couleur noire
	 * @param i La position en X
	 * @param j La position en Y
	 */
	public void setBlackPiece(int i, int j);

	/**
	 * Méthode permettant de poser une pièce blanche
	 * @param i La position en X
	 * @param j La position en Y
	 */
	public void setWhitePiece(int i, int j);

	/**
	 * Méthode permettant de poser une pièce vide
	 * @param i La position en X
	 * @param j La position en Y
	 */
	public void setEmptyPiece(int i, int j);

	/**
	 * Méthode permettant de définir une pièce jouable
	 * @param i La position en X
	 * @param j La position en Y
	 */
	public void setPiecePlayable(int i, int j);

	/**
	 * Méthode permettant de définir une pièce non jouable
	 * @param i La position en X
	 * @param j La position en Y
	 */
	public void setPieceNotPlayable(int i, int j);
	/**
	 * Méthode retournant la liste des pièce de couleur noire
	 * @return La liste de pièce
	 */
	public List<Piece> getBlackPieces();
	/**
	 * Méthode retournant la liste des pièce de couleur blanche
	 * @return La liste de pièce
	 */
	public List<Piece> getWhitePieces();
	/**
	 * Méthode retournant la liste des pièce jouables
	 * @return La liste de pièce
	 */
	public List<Piece> getPlayablePieces();
	/**
	 * Cette méthode réinitialise les coups jouables
	 */
	public void resetPlayablePosition();

	/**
	 * Permet l'affichge de l'othellier dans la console.
	 * @return String : Une chaine de caractère prête pour l'affichage en console.
	 */
	public String toString();
	/**
	 * Cette méthode clone l'othellier
	 * @return Un clone de l'objet appellant la méthode
	 */
	public Board clone();
}