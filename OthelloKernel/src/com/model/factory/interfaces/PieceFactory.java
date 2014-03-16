package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.piece.Piece;
import com.model.piece.PieceImpl;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface PieceFactory {
	public PieceImpl getWhitePiece(int posX, int posY)
			throws FactoryHandlerException;

	public PieceImpl getBlackPiece(int posX, int posY)
			throws FactoryHandlerException;

	public PieceImpl getEmptyPiece(int posX, int posY)
			throws FactoryHandlerException;

	public PieceImpl[][] getMatrixPiece(int i, int j)
			throws FactoryHandlerException;

	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException;
}