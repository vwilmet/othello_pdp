package com.model.player;

import java.awt.Color;

import utils.GameHandlerException;
import utils.TextManager;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class Player {

	private static int pNumber = 0;
	
	private String login;
	private String color;
	private int piecesNumber;
	private int playerNumber;
	private PlayerType type;

	/**
	 * Constructeur de player. 
	 * Attention à bien gérer le booléen firstPlayer dans la classe appelante, sinon risque de plantage.
	 * @param login
	 * @param c
	 */
	public Player(String login, String c) throws GameHandlerException{
		this.login = login;
		
		if ((c.equals(TextManager.WHITE_PLAYER)) || (c.equals(TextManager.BLACK_PLAYER)))
			this.color = c;
		else {
			throw new GameHandlerException(GameHandlerException.WRONG_INITIAL_PIECE_COLOR);
		}
		this.piecesNumber = 0;
		this.playerNumber = ++pNumber;
		this.type = new HumanPlayer();
	}

	public String getLogin() {
		return this.login;
	}

	public String getColor() {
		return this.color;
	}

	public int getPiecesNumber() {
		return this.piecesNumber;
	}

	public void setPiecesNumber(int piecesNumber) {
		this.piecesNumber = piecesNumber;
	}
	
	public int getPlayerNumber(){
		return this.playerNumber;
	}
	
	public PlayerType getPlayerType(){
		return this.type;
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
		res += "Couleur du joueur : " + this.color + ".\n";
		res += "Pions sur le plateau : " + this.piecesNumber + ".\n";
		if (this.playerNumber == 1){
			res += TextManager.PLAYER_INFORMATION_1ST_PLAYER_FR;
		}
		else if (this.playerNumber == 2){
			res += TextManager.PLAYER_INFORMATION_2ND_PLAYER_FR;
		}
		return res;
	}
}
