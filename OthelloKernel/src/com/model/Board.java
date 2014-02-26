package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.piece.EmptyPiece;
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
		this.initialPieces.add(p.clone());
		//else
			// throw new exception Interdit d'ajouter une piece vide
	}

	public String toString(){
		String res = "Taille graphique de l'othellier : " + this.width + "x" + this.height + "\n" ;
		res += "Taille de l'othellier (nombre de pions sur le plateau) : " + this.sizeX + "x" + this.sizeY + "\n" ;
		
		res += "   ";
		for (int k = 0; k < this.sizeY; k++)
			res += (k<10)? " " + k + " " :" " + k ;
		res += "\n";
		for (int i = 0; i < this.sizeX; i++){
			res += i + ((i<10)?"  |":" |");
			for (int j = 0; j < this.sizeY; j++){
				res += this.gameBoard[i][j].getColor().graphicalDebug() + "|";  
			}
			res += "\n";
		}
		return res;
	}
}
