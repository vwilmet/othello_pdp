package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.BoardObservable;
import com.model.piece.Piece;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface BoardFactory {
	public BoardObservable getBoard(int sizeX, int sizeY, List<Piece> initiaPieces) throws FactoryHandlerException;
}
