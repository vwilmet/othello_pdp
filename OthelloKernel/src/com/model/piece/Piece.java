package com.model.piece;

public interface Piece {

	public boolean isPlayable();
	public PieceColor getColor();
	public int getPosX();
	public int getPosY();
	public String toString();
	public PieceImpl clone();
	public boolean equals(Object obj);
}
