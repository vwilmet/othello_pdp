package com.model.player;

import java.awt.Color;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public abstract class Player {
	
	protected String login;
	protected Color color;
	protected int piecesNumber;
	
	
	public Player (String login, Color c){
		this.login = login;
		this.color = c;
		this.piecesNumber = 0;
	}
	
	public String getLogin(){
		return this.login;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public int getPiecesNumber(){
		return this.piecesNumber;
	}
	
	public void setPiecesNumber (int piecesNumber){
		this.piecesNumber = piecesNumber;
	}
	
	public String toString(){
		String res = "Pseudonyme du joueur : " + this.login + ".\n";
		res += "Couleur du joueur : " + this.color.toString() + ".\n";
		res += "Pions sur le plateau : " + this.piecesNumber + ".\n";
		return res;
	}
}
