package com.model;

import java.util.List;

import com.model.piece.Piece;

public interface Board {

	public int getSizeX();
	
	public int getSizeY();
	
	public Piece[][] getBoard();
	
	public List<Piece> getInitialPiece();
	
	public String toString();	
	
	public void reverse(int i, int j);
	
	public void setBlackPiece(int i, int j);

	public void setWhitePiece(int i, int j);
	
	public void setPiecePlayable(int i, int j);
	
	public void setPieceNotPlayable(int i, int j);
}