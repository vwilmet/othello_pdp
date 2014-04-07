package com.model.player;

import utils.GameHandlerException;
import utils.TextManager;

import com.publisher.BoardPublisher;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class Player {
	
	private String login;
	private String color;
	private int piecesNumber;
	private int playerNumber;
	private PlayerType type;

	public Player(String login, String c, int playerNumber) throws GameHandlerException{
		this.login = login;
		
		if ((c.equals(BoardPublisher.WHITE_PLAYER)) || (c.equals(BoardPublisher.BLACK_PLAYER)))
			this.color = c;
		else {
			throw new GameHandlerException(GameHandlerException.WRONG_INITIAL_PIECE_COLOR);
		}
		this.piecesNumber = 0;
		this.playerNumber = playerNumber;
		this.type = new HumanPlayer();
	}

	public String getLogin() {
		return this.login;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
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
	
	public void setPlayerNumber(int number){
		this.playerNumber = number;
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
		String res = this.login + ".\n";
		res += "Couleur du joueur : " + this.color + ".\n";
		res += "Type du joueur : " + this.type + ".\n";
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
