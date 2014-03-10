package com.model;

import java.util.List;

import com.model.piece.Piece;

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
	 * 
	 * @param i
	 * @param j
	 */
	public void reverse(int i, int j);
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void setBlackPiece(int i, int j);

	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void setWhitePiece(int i, int j);
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void setPiecePlayable(int i, int j);
	
	/**
	 * 
	 * @param i
	 * @param j
	 */
	public void setPieceNotPlayable(int i, int j);
	
	/**
	 * Permet l'affichge de l'othellier dans la console.
	 * @return String : Une chaine de caractère prête pour l'affichage en console.
	 */
	public String toString();	
}