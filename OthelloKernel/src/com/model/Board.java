package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.piece.BlackPiece;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class Board {
	
	private int width, height;
	private int sizeX, sizeY;
	
	private Piece[][] gameBoard;
	
	private List<Piece> initialPieces;
	
	public Board(int width, int height, int sizeX, int sizeY) {// throws //nom exception{
		this.width = width;
		this.height = height;
		
		if (sizeX > 3  && sizeX < 50)
			this.sizeX = sizeX;
		//else 
		//	throw new EXCEPTION sur la taille X 
		
		if (sizeY > 3  && sizeY < 50)
			this.sizeY = sizeY;
		//else 
		//	throw new EXCEPTION sur la taille Y
		
		this.gameBoard = new Piece[this.sizeX][this.sizeY];
		
		this.initialPieces = new ArrayList<Piece>();
		
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight (){
		return this.height;
	}
	
	public int getSizeX(){
		return this.sizeX;
	}
	
	public int getSizeY(){
		return this.sizeY;
	}
	
	public Piece[][] getBoard(){
		return this.gameBoard;
	}
	
	public List<Piece> getInitialPiece(){
		return this.initialPieces;
	}
	
	public void addInitialPiece (Piece p){
		if (p instanceof WhitePiece)
			this.initialPieces.add(new WhitePiece(p.getWidth(), p.getHeight(), p.getPosX(), p.getPosY()));
		else if (p instanceof BlackPiece)
			this.initialPieces.add(new BlackPiece(p.getWidth(), p.getHeight(), p.getPosX(), p.getPosY()));
		//else
			// throw new exception Interdit d'ajouter une piece vide
	}

	public String toString(){
		String res = "ToStringBOARD";
		return res;
	}
}
