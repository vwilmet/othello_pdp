package com.model.factory.interfaces;

import java.util.List;

import utils.FactoryHandlerException;

import com.model.piece.Piece;


public interface PieceFactory {
	public Piece getWhitePiece(int width, int height, int posX, int posY) throws FactoryHandlerException;
	public Piece getBlackPiece(int width, int height, int posX, int posY) throws FactoryHandlerException;
	public Piece getEmptyPiece(int width, int height, int posX, int posY) throws FactoryHandlerException;
	public Piece[][] getMatrixPiece(int i, int j) throws FactoryHandlerException;
	public List<Piece> getArrayListOfPiece() throws FactoryHandlerException;
}
