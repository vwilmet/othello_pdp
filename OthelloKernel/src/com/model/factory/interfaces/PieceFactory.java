package com.model.factory.interfaces;

import com.model.piece.Piece;


public interface PieceFactory {
	public Piece getWhitePiece(int width, int height, int posX, int posY);
	public Piece getBlackPiece(int width, int height, int posX, int posY);
	public Piece getEmptyPiece(int width, int height, int posX, int posY);
}
