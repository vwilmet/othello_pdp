package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.piece.Piece;


public interface PieceFactory {
	public Piece getWhitePiece(int width, int height, int posX, int posY) throws FactoryHandlerException;
	public Piece getBlackPiece(int width, int height, int posX, int posY) throws FactoryHandlerException;
	public Piece getEmptyPiece(int width, int height, int posX, int posY) throws FactoryHandlerException;
}
