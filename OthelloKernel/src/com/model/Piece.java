package com.model;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public abstract class Piece {
	
	protected int width, height;
	
	protected int posX, posY;
	
	public Piece (int width, int height, int posX, int posY){
		this.width = width;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
	}
	
	public Piece(Piece p){
		this.width = p.getWidth();
		this.height = p.getHeight();
		this.posX = p.getPosX();
		this.posY = p.getPosY();
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight (){
		return this.height;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public String toString(){
		String res = "Taille : " + this.width + " * " + this.height + "\n";
		res += "Position dans la grille : " + this.posX + " : " + this.posY + "\n";
		return res;
	}
}
