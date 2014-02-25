package com.factory;
import java.awt.Color;

import com.model.Piece;
import com.model.Player;


public interface PiecerInter {

	public Piece getPiece(String pieceType, int width, int height, int posX, int posY);
	
}
