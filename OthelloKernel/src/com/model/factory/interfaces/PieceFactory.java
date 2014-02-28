package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.piece.Piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface PieceFactory {
	public Piece getWhitePiece(int posX, int posY)
			throws FactoryHandlerException;

	public Piece getBlackPiece(int posX, int posY)
			throws FactoryHandlerException;

	public Piece getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException;

	public Piece[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException;

	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException;
}