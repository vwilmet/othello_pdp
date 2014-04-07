package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.piece.Piece;
import com.model.piece.PieceImpl;

/**
 * Interface de sécurisation de la fabrique de pion.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface PieceFactory {
	
	
	/**
	 * Fabrique d'un pion blanc.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion blanc à construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public PieceImpl getWhitePiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un pion noir.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion noir à construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public PieceImpl getBlackPiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'un pion vide.
	 * @param posX : int, coordonnée suivant l'axe des abscisse du pion.
	 * @param posY : int, coordonnée suivant l'axe des ordonnées du pion.
	 * @return PieceImpl : pion vide construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public PieceImpl getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException;

	/**
	 * Fabrique d'une matrice de pion.
	 * @param i : int, taille de la matrice.
	 * @param j : int, taille de la matrice
	 * @return PieceImpl[][]: Matrice de Pion construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException;

	/**
	 *  Fabrique d'un tableau dynamique de pion.
	 * @return List<Piece> : Liste vide de Pion construit par la fabrique.
	 * @throws FactoryHandlerException.
	 */
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException;
}