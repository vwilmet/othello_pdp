package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.model.piece.Piece;

/**
 * Cette classe permet de gérer le design pattern observer. Elle va permettre à chaque modification de l'othellier de prévenir la classe qui l'observe
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class BoardObservable extends Observable implements Board {

	/**
	 * L'othellier qui va être observé
	 */
	private Board board;

	/**
	 * Constructeur de la board observable
	 * @param board L'othellier à observer
	 */
	public BoardObservable(Board board) {
		this.board = board;
	}

	/**
	 * Cette méthode permet de prévenir toutes les classes qui observe l'othellier que l'objet à changé.
	 */
	private void notifierObservateurs() {
		setChanged();
		notifyObservers();
	}
	/**
	 * Accesseur sur la taille d'un othellier (Axe des abscisses).
	 * @return : int la taille de l'othellier suivant l'axe des abscisses.
	 */
	@Override
	public int getSizeX() {
		return this.board.getSizeX();
	}
	/**
	 * Accesseur sur la taille d'un othellier (Axe des ordonnées).
	 * @return : int la taille de l'othellier suivant l'axe des ordonnées.
	 */
	@Override
	public int getSizeY() {
		return this.board.getSizeY();
	}
	/**
	 * Accesseur sur la matrice représentant l'othellier à un instant de jeu.
	 * @return int [][] : La matrice remplie de pièces.
	 */
	@Override
	public Piece[][] getBoard() {
		return this.board.getBoard();
	}
	/**
	 * Accesseur sur la liste des pions initials l'une partie.
	 * @return List<Piece> : liste chainée des pions initiaux de la partie.
	 */
	@Override
	public List<Piece> getInitialPiece() {
		return this.board.getInitialPiece();
	}
	/**
	 * Méthode permettant de changer la couleur du pion correspondant aux positions en paramètre
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void reverse(int i, int j) {
		this.board.reverse(i, j);
		notifierObservateurs();
	}
	/**
	 * Méthode permettant de poser une pièce de couleur noire
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setBlackPiece(int i, int j) {
		this.board.setBlackPiece(i, j);
		notifierObservateurs();
	}
	/**
	 * Méthode permettant de poser une pièce blanche
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setWhitePiece(int i, int j) {
		this.board.setWhitePiece(i, j);
		notifierObservateurs();
	}
	/**
	 * Méthode permettant de poser une pièce vide
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setEmptyPiece(int i, int j) {
		this.board.setEmptyPiece(i, j);
		notifierObservateurs();
	}
	/**
	 * Permet l'affichge de l'othellier dans la console.
	 * @return String : Une chaine de caractère prête pour l'affichage en console.
	 */
	@Override
	public String toString() {
		return this.board.toString();
	}
	/**
	 * Méthode permettant de définir une pièce jouable
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setPiecePlayable(int i, int j) {
		this.board.setPiecePlayable(i, j);
		notifierObservateurs();
	}
	/**
	 * Méthode permettant de définir une pièce non jouable
	 * @param i La position en X
	 * @param j La position en Y
	 */
	@Override
	public void setPieceNotPlayable(int i, int j) {
		this.board.setPieceNotPlayable(i, j);
		notifierObservateurs();
	}
	/**
	 * Méthode retournant la liste des pièce de couleur noire
	 * @return La liste de pièce
	 */
	@Override
	public List<Piece> getBlackPieces() {
		return this.board.getBlackPieces();
	}
	/**
	 * Méthode retournant la liste des pièce de couleur blanche
	 * @return La liste de pièce
	 */
	@Override
	public List<Piece> getWhitePieces() {
		return this.board.getWhitePieces();
	}
	/**
	 * Méthode retournant la liste des pièce jouables
	 * @return La liste de pièce
	 */
	@Override
	public List<Piece> getPlayablePieces() {
		return this.board.getPlayablePieces();
	}
	/**
	 * Cette méthode réinitialise les coups jouables
	 */
	@Override
	public void resetPlayablePosition() {
		this.board.resetPlayablePosition();
		notifierObservateurs();
	}
	/**
	 * Cette méthode clone l'othellier
	 * @return Un clone de l'objet appellant la méthode
	 */
	@Override
	public Board clone() {
		return new BoardObservable(this.board.clone());
	}
}
