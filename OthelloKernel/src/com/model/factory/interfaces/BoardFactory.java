package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.BoardObservable;
import com.model.piece.Piece;

/**
 * Interface de sécurisation de la fabrique d'un plateau de jeu.
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface BoardFactory {
	
	/**
	 * Fabrique d'un plateau de jeu.
	 * @param sizeX : int, Taille du plateau de jeu.
	 * @param sizeY : int, Taille du plateau de jeu.
	 * @param List<Piece> : Ensemble des pieces initiales de la partie.
	 * @return BoardObservable : Plateau de jeu
	 * @throws FactoryHandlerException.
	 */
	public BoardObservable getBoard(int sizeX, int sizeY, List<Piece> initiaPieces) 
			throws FactoryHandlerException;
	
	/**
	 * Fabrique d'un plateau de jeu initial.
	 * @param sizeX : int, Taille du plateau de jeu.
	 * @param sizeY : int, Taille du plateau de jeu.
	 * @return BoardObservable : Plateau de jeu
	 * @throws FactoryHandlerException.
	 */
	public BoardObservable getInitialBoard(int sizeX, int sizeY)
			throws FactoryHandlerException;
	
	/**
	 * Fabrique d'une liste de plateau de jeu.
	 * @return List<BoardObservable> : ensemble de plateaux de jeu.
	 * @throws FactoryHandlerException.
	 */
	public List<BoardObservable> getBoardList() 
			throws FactoryHandlerException;
}
