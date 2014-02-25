package com.model.factory.interfaces;
import java.awt.Color;

import com.model.piece.Piece;
import com.model.player.Player;


public interface PieceFactory {

	public Piece getPiece(String pieceType, int width, int height, int posX, int posY);
	
}
