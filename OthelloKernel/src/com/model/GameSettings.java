package com.model;

import com.model.player.Player;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class GameSettings {

	private Player player1, player2;
	private Board gameBoard;
	private int artificialIntelligenceThinkingTime, artificialIntelligenceDifficulty;
	
	public GameSettings (Player player1, Player player2, Board gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) {
		this.player1 = player1;
		this.player2 = player2;
				
		this.gameBoard = gameBoard;
		
		this.artificialIntelligenceThinkingTime = artificialIntelligenceThinkingTime;
		this.artificialIntelligenceDifficulty = artificialIntelligenceDifficulty;
	}
	
	public Player getFirstPlayer(){
		return this.player1;
	}
	
	public Player getSecondPlayer(){
		return this.player2;
	}
	
	public Board getGameBoard(){
		return this.gameBoard;
	}
	
	public int getAIThinkingTime(){
		return this.artificialIntelligenceThinkingTime;
	}
	
	public int getAIDifficulty(){
		return this.artificialIntelligenceDifficulty;
	}
	
	public String toString() {
		String res = "Nom joueur 1 : " + this.player1 + "\n";
		res += "Nom joueur 2 : " + this.player2 + "\n";
		res += "Plateau de jeu : \n" + this.gameBoard.toString();
		res += "Temps de réflexion de l'IA : " + this.artificialIntelligenceThinkingTime + "\n";
		res += "Difficulté de l'IA : " + this.artificialIntelligenceDifficulty + "\n";
		return res;
	}
}
