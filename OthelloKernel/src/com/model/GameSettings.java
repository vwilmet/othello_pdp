package com.model;

import java.util.ArrayList;
import java.util.List;

import utils.TextManager;

import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.model.player.Player;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class GameSettings {

	/**
	 * La taille par défaut de la grille en colonne
	 */
	public static final int DEFAULT_ROW_SIZE = 4;
	/**
	 * La taille par défaut de la grille en ligne
	 */
	public static final int DEFAULT_LIGNE_SIZE = 4;

	/**
	 * Le temps donnée à l'IA pour donner une solution par défaut : 2 secondes
	 */
	public static final int DEFAULT_IA_THINKING_TIME = 2000;
	/**
	 * La difficulté de l'IA par défaut [0, 1 ou 2 avec 0 le plus facile et 2 le plus compliqué] 
	 */
	public static final int DEFAULT_IA_DIFFICULTY = 0;

	public static final int AI_THINKING_TIME_LIMIT_MAX = 900000;
	public static final int AI_THINKING_TIME_LIMIT_MIN = 2000;
	public static final int BOARD_MIN_SIZE_X = 4;
	public static final int BOARD_MIN_SIZE_Y = 4;

	public static final int BOARD_MAX_SIZE_X = 50;
	public static final int BOARD_MAX_SIZE_Y = 50;

	public static final String HELP_WEBSITE_PATH = "./resources/website/index.html";
	//public static final int  = 60;
	//public static final int  = 60;



	/**
	 * Joueurs du jeu (machine ou humain). 
	 */
	private Player player1, player2;

	private Player currentPlayer;

	/**
	 * Plateau de jeu.
	 */
	private BoardObservable gameBoard;

	/**
	 * Variables permettant le paramètrage de l'intelligence artificielle. 
	 */
	private int artificialIntelligenceThinkingTime, artificialIntelligenceDifficulty;

	/**
	 * Variable stoquant l'historique des coups.
	 */
	private List <Piece> gameHistory;
	private int sentinel;

	public GameSettings (Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty) {
		this.player1 = player1;
		this.player2 = player2;

		this.gameBoard = gameBoard;

		this.artificialIntelligenceThinkingTime = artificialIntelligenceThinkingTime;
		this.artificialIntelligenceDifficulty = artificialIntelligenceDifficulty;

		this.gameHistory = new ArrayList<Piece>();
		this.sentinel = -1;
		this.currentPlayer = player1;
	}

	public void changePlayer(){
		this.currentPlayer = this.currentPlayer == player1 ? player2 : player1;
	}

	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer){
		this.currentPlayer = currentPlayer;
	}

	public Player getFirstPlayer(){
		return this.player1;
	}

	public Player getSecondPlayer(){
		return this.player2;
	}

	public BoardObservable getGameBoard(){
		return this.gameBoard;
	}

	public int getAIThinkingTime(){
		return this.artificialIntelligenceThinkingTime;
	}

	public int getAIDifficulty(){
		return this.artificialIntelligenceDifficulty;
	}

	public List<Piece> getGameHistory(){
		return this.gameHistory;
	}

	public void resetHistoryAndRestartGame(){
		for(int i = 1, j = this.gameHistory.size(); i <= j; i++){
			this.gameBoard.setEmptyPiece(
					this.gameHistory.get(j-i).getPosX(),
					this.gameHistory.get(j-i).getPosY());
			this.gameHistory.remove(j-i);
		}
		this.sentinel = -1;
		this.currentPlayer = this.player1;
	}

	public boolean getBackInHistory(){

		if(this.sentinel >= 0){

			Piece p = this.gameHistory.get(this.sentinel);
			if(p.getColor() instanceof WhitePiece)
				this.setCurrentPlayer(this.player1);
			else
				this.setCurrentPlayer(this.player2);

			this.gameBoard.setEmptyPiece(p.getPosX(), p.getPosY());
			this.sentinel--;

			return true;
		}
		return false;
	}

	public boolean getForwardInHistory(){
		if(this.sentinel < this.gameHistory.size()-1){

			Piece p = this.gameHistory.get(this.sentinel + 1);

			this.setPiece(p.getPosX(), p.getPosY());

			this.changePlayer();

			return true;
		}

		return false;
	}

	public void reversePlayer(){
		for(int i = 0; i < this.gameBoard.getSizeX(); i++)
			for(int j = 0; j < this.gameBoard.getSizeY(); j++)
				this.gameBoard.reverse(i, j);
		changePlayer();
	}

	public void setPiece(int i, int j) {

		if(this.currentPlayer.getColor().equals(TextManager.WHITE_PLAYER))
			this.gameBoard.setWhitePiece(i, j);
		else
			this.gameBoard.setBlackPiece(i, j);

		this.addPieceMove(this.gameBoard.getBoard()[i][j]);
	}

	/**
	 * 
	 * @param p : Piece que l'utilisateur viens de jouer. 
	 */
	public void addPieceMove(Piece p){
		this.sentinel++;

		if(this.gameHistory.size()-1 > this.sentinel){
			if(!this.gameHistory.get(this.sentinel).equals(p)){
				for(int i = this.sentinel; i < this.gameHistory.size(); i++){
					System.out.println( "Remove piece : " + this.gameHistory.remove(i));
				}
				this.gameHistory.add(this.sentinel, p);
			}
		}else{
			this.gameHistory.add(this.sentinel, p);
		}
	}

	public String toString() {
		String res = "Joueur 1 : " + this.player1.toString() + "\n";
		res += "Joueur 2 : " + this.player2.toString() + "\n";
		res += "Plateau de jeu : \n" + this.gameBoard.toString();

		res +="Historique des Coups : " ;
		for (Piece p : this.gameHistory){
			res += "("+ p.getPosX() + ":" + p.getPosY() + p.getColor().toString() + ")";
		}

		res += "Temps de réflexion de l'IA : " + this.artificialIntelligenceThinkingTime + "\n";
		res += "Difficulté de l'IA : " + this.artificialIntelligenceDifficulty + "\n";
		return res;
	}
}
