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

	public static final String HELP_WEBSITE_PATH = "resources/website/index.html";

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
	private List <BoardObservable> gameBoardHistory;
	/**
	 * Sentinel utilisée pour se repérer dans l'historique! Les deux listes gameHistory et gameBoardHistory 
	 * l'utilise mais gameBoardHistory à une case de plus car il contient la partie initial
	 */
	private int sentinel;

	public GameSettings (Player player1, Player player2, BoardObservable gameBoard, int artificialIntelligenceThinkingTime, int artificialIntelligenceDifficulty, List<Piece> history) {
		this.player1 = player1;
		this.player2 = player2;

		this.gameBoard = gameBoard;

		this.artificialIntelligenceThinkingTime = artificialIntelligenceThinkingTime;
		this.artificialIntelligenceDifficulty = artificialIntelligenceDifficulty;

		this.gameHistory = history;

		this.gameBoardHistory = new ArrayList<BoardObservable>();
		this.gameBoardHistory.add((BoardObservable)gameBoard.clone());
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

	public int getHistoryPosition(){
		return this.sentinel;
	}

	public void setHistoryPosition(int sentinel){
		this.sentinel = sentinel;
	}

	public BoardObservable getHistoryBoard(int position){
		return this.gameBoardHistory.get(position);
	}

	public List<BoardObservable> getGameBoardHistory(){
		return this.gameBoardHistory;
	}

	public void resetHistory(){
		this.gameHistory.clear();
		this.gameBoardHistory.clear();
		this.gameBoardHistory.add((BoardObservable)gameBoard.clone());
		this.sentinel = -1;
	}

	public void restartGame(){
		//TODO tester l'affectation sans le clone voir si sa marche
		if(this.gameBoardHistory.size() > 0)
			this.gameBoard = (BoardObservable)this.gameBoardHistory.get(0).clone();
		resetHistory();
		this.currentPlayer = this.player1;
	}

	public boolean getBackInHistory(){

		System.out.println("Sentinel : " + this.sentinel);
		System.out.println("gameHistorySize: " + this.gameHistory.size());
		System.out.println("Board history size: " + this.gameBoardHistory.size());

		if(this.sentinel >= 0){
			if(this.gameHistory.get(this.sentinel).getColor() instanceof WhitePiece)
				this.setCurrentPlayer(this.player1);
			else
				this.setCurrentPlayer(this.player2);
			System.out.println("value sentinel " + this.sentinel );
			this.gameBoard = this.gameBoardHistory.get(this.sentinel);
			this.gameBoard.notifyObservers();

			this.sentinel--;
			return true;
		}

		return false;
	}

	public boolean getForwardInHistory(){
		System.out.println("[getForwardInHistory]");
		if(this.sentinel < this.gameHistory.size()-1){
			System.out.println("sentinel : " + sentinel);
			System.out.println("size  : " +  this.gameBoardHistory.size());

			//TODO check si ++ de sentinel avant ou après a cause de l'avancement des boards
			this.sentinel++;
			this.gameBoard = this.gameBoardHistory.get(this.sentinel+1);
			this.changePlayer();
			return true;
		}

		return false;
	}

	public void reversePlayer(){
		/*for(int i = 0; i < this.gameBoard.getSizeX(); i++)
			for(int j = 0; j < this.gameBoard.getSizeY(); j++)
				this.gameBoard.reverse(i, j);

		//TODO change la couleur du joueur et non le joueur!
		changePlayer();*/

		if(this.player1.getColor().equals(TextManager.BLACK_PLAYER))
			this.player1.setColor(TextManager.WHITE_PLAYER);
		else
			this.player1.setColor(TextManager.BLACK_PLAYER);

		if(this.player2.getColor().equals(TextManager.BLACK_PLAYER))
			this.player2.setColor(TextManager.WHITE_PLAYER);
		else
			this.player2.setColor(TextManager.BLACK_PLAYER);
	}

	public void setPiece(int i, int j) {

		if(this.currentPlayer.getColor().equals(TextManager.WHITE_PLAYER))
			this.gameBoard.setWhitePiece(i, j);
		else
			this.gameBoard.setBlackPiece(i, j);
	}

	/**
	 * 
	 * @param p : Piece que l'utilisateur viens de jouer. 
	 */
	public void manageBoardHistory(int x, int y){

		Piece p = this.gameBoard.getBoard()[x][y];
		if(this.gameHistory.size()-1 > this.sentinel){
			this.sentinel++;
			if(!this.gameHistory.get(this.sentinel).equals(p)){
				for(int i = this.sentinel, bordI = this.sentinel+1; i < this.gameHistory.size(); i++, bordI++){
					System.out.println( "Remove piece : " + this.gameHistory.remove(i));
					System.out.println( "Remove Board : " + this.gameBoardHistory.remove(bordI));
				}
				this.gameHistory.add(this.sentinel, p);
				this.gameBoardHistory.add((BoardObservable)this.gameBoard.clone());
			}
		}else{
			this.sentinel++;
			this.gameHistory.add(this.sentinel, p);
			this.gameBoardHistory.add((BoardObservable)this.gameBoard.clone());
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
