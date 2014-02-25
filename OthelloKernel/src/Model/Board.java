package Model;

import java.util.ArrayList;
import java.util.List;

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
	
	public Board(int width, int height, int sizeX, int sizeY){
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
	
	// Pour la copie de la piece. mais ca a pas l'aire de marcher
	public void addInitialPiece (Piece p){
		this.initialPieces.add(new Piece(p));
	}

	public String toString(){
		return null;
	}
}
