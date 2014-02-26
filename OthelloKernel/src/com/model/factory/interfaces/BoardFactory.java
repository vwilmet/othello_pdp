package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.Board;
import com.model.piece.Piece;

public interface BoardFactory {
	public Board getBoard(int width, int height, int sizeX, int sizeY, List<Piece> initiaPieces) throws FactoryHandlerException;
}
