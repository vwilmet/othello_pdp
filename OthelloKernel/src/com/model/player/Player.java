package com.model.player;

import java.awt.Color;

import utils.TextManager;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class Player {

	private String login;
	private Color color;
	private int piecesNumber;
	private boolean isFirstPlayer;
	private PlayerType type;

	/**
	 * Constructeur de player. 
	 * Attention à bien gérer le booléen firstPlayer dans la classe appelante, sinon risque de plantage.
	 * @param login
	 * @param c
	 */
	public Player(String login, Color c) {
		this.login = login;
		this.color = c;
		this.piecesNumber = 0;
		this.isFirstPlayer = true;
		this.type = new HumanPlayer();
	}

	public String getLogin() {
		return this.login;
	}

	public Color getColor() {
		return this.color;
	}

	public int getPiecesNumber() {
		return this.piecesNumber;
	}

	public void setPiecesNumber(int piecesNumber) {
		this.piecesNumber = piecesNumber;
	}
	
	public boolean getIsFirstPlayer(){
		return this.isFirstPlayer;
	}
	
	public void setAsFirstPlayer(){
		this.isFirstPlayer = true;
	}
	
	public void setAsSecondPlayer(){
		this.isFirstPlayer = false;
	}
	
	public Player setHuman(){
		this.type = new HumanPlayer();
		return this;
	}
	
	public Player setMachine(){
		this.type = new MachinePlayer();
		return this;
	}

	public String toString() {
		String res = "Pseudonyme du joueur : " + this.login + ".\n";
		res += "Couleur du joueur : " + this.color.toString() + ".\n";
		res += "Pions sur le plateau : " + this.piecesNumber + ".\n";
		res += (this.isFirstPlayer)?TextManager.PLAYER_INFORMATION_1ST_PLAYER_FR:TextManager.PLAYER_INFORMATION_2ND_PLAYER_FR;
		return res;
	}
}
