package com.model;

import java.util.ArrayList;
import java.util.List;

import utils.FactoryHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.model.player.MachinePlayer;
import com.model.player.Player;
import com.publisher.BoardPublisher;

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
	private int artificialIntelligenceThinkingTime, 
	helpArtificialIntelligenceDifficulty, 
	player1ArtificialIntelligenceDifficulty, 
	player2ArtificialIntelligenceDifficulty;

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
		
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		
		this.player1 = player1;
		this.player2 = player2;

		this.gameBoard = gameBoard;

		this.artificialIntelligenceThinkingTime = artificialIntelligenceThinkingTime;
		this.helpArtificialIntelligenceDifficulty = artificialIntelligenceDifficulty;

		this.gameHistory = history;

		try {
			this.gameBoardHistory = bFacto.getBoardList();
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
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

	public Player getOpponentPlayer(){
		return this.currentPlayer == player1 ? player2 : player1;
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

	public int getHelpAIDifficulty(){
		return this.helpArtificialIntelligenceDifficulty;
	}

	public List<Piece> getGameHistory(){
		return this.gameHistory;
	}

	public int getHistoryPosition(){
		return this.sentinel;
	}

	public int getBoardHistoryPosition(){
		return this.sentinel + 1;
	}

	public void setHistoryPosition(int sentinel){
		this.sentinel = sentinel;

		if(this.sentinel == -1)
			this.setCurrentPlayer(this.player1);
		else if(this.gameHistory.get(this.sentinel).getColor() instanceof WhitePiece)
			this.setCurrentPlayer(this.player2);
		else
			this.setCurrentPlayer(this.player1);

		this.gameBoard = (BoardObservable) this.gameBoardHistory.get(this.sentinel+1).clone();
		this.gameBoard.notifyObservers();
	}

	public BoardObservable getHistoryBoard(int position){
		return this.gameBoardHistory.get(position);
	}

	public List<BoardObservable> getGameBoardHistory(){
		return this.gameBoardHistory;
	}
	
	public void setGameBoardHistory(List<BoardObservable> gameBoardHistory){
		this.gameBoardHistory = gameBoardHistory;
	}

	public void resetHistory(){
		this.gameHistory.clear();
		this.gameBoardHistory.clear();
		this.gameBoardHistory.add((BoardObservable)gameBoard.clone());
		this.sentinel = -1;
		System.gc();
	}

	public void restartGame(){
		if(this.gameBoardHistory.size() > 0)
			this.gameBoard = (BoardObservable)this.gameBoardHistory.get(0).clone();
		resetHistory();
		this.currentPlayer = this.player1;
	}
	
	public boolean canGoBack(){
		if(this.sentinel == -1)
			return false;
		return true;
	}

	public boolean canGoForward(){
		if(this.gameHistory.size() == 0 || (this.gameHistory.size()-1) == this.sentinel)
			return false;
		return true;
	}

	//si il y a déjà eu un coup
	public boolean canReset(){
		if(this.gameHistory.size() == 0)
			return false;
		return true;
	}

	public boolean getBackInHistory(){
		if(this.sentinel >= 0){
			if(this.gameHistory.get(this.sentinel).getColor() instanceof WhitePiece)
				this.setCurrentPlayer(this.player1);
			else
				this.setCurrentPlayer(this.player2);

			this.gameBoard = (BoardObservable) this.gameBoardHistory.get(this.sentinel).clone();
			this.gameBoard.notifyObservers();

			this.sentinel--;
			
			return true;
		}

		return false;
	}

	public boolean getForwardInHistory(){
		if(this.sentinel < this.gameHistory.size()-1){
			this.sentinel++;
			this.gameBoard = (BoardObservable) this.gameBoardHistory.get(this.sentinel+1).clone();

			if(this.gameHistory.get(this.sentinel).getColor() instanceof WhitePiece)
				this.setCurrentPlayer(this.player2);
			else
				this.setCurrentPlayer(this.player1);
			
			return true;
		}

		return false;
	}

	public void reversePlayer(){
		if(this.player1.getColor().equals(BoardPublisher.BLACK_PLAYER))
			this.player1.setColor(BoardPublisher.WHITE_PLAYER);
		else
			this.player1.setColor(BoardPublisher.BLACK_PLAYER);

		if(this.player2.getColor().equals(BoardPublisher.BLACK_PLAYER))
			this.player2.setColor(BoardPublisher.WHITE_PLAYER);
		else
			this.player2.setColor(BoardPublisher.BLACK_PLAYER);
		
		//change couleur des pions sur le plateau
		for(int i = 0; i < this.gameBoard.getSizeX(); i++)
			for(int j = 0; j < this.gameBoard.getSizeY(); j++)
				this.gameBoard.reverse(i, j);
		
		this.changePlayer();
	}

	public void setPiece(int i, int j) {

		if(this.currentPlayer.getColor().equals(BoardPublisher.WHITE_PLAYER))
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
				int size = this.gameHistory.size();

				for(int i = this.sentinel; i < size; i++){
					this.gameHistory.remove(this.sentinel);
					this.gameBoardHistory.remove(this.sentinel+1);
				}
				this.gameHistory.add(p.clone());
				this.gameBoardHistory.add((BoardObservable)this.gameBoard.clone());
			}
		}else{
			this.sentinel++;
			this.gameHistory.add(this.sentinel, p.clone());
			this.gameBoardHistory.add((BoardObservable)this.gameBoard.clone());
		}
	}

	public String toString() {
		String res = "\tJoueur 1 : " + this.player1.toString() + "\n";

		if(this.player1.getPlayerType() instanceof MachinePlayer)
			res+= "Difficulté de l'IA : " + TextManager.AI_DIFFICULTY_VALUE_TEXT_FR[this.player1ArtificialIntelligenceDifficulty] + "\n";

		res += "\tJoueur 2 : " + this.player2.toString() + "\n";
		if(this.player2.getPlayerType() instanceof MachinePlayer)
			res+= "Difficulté de l'IA : " + TextManager.AI_DIFFICULTY_VALUE_TEXT_FR[this.player2ArtificialIntelligenceDifficulty] + "\n\n";
		
		res += "Temps de réflexion des IA : " + this.artificialIntelligenceThinkingTime + "\n";
		res += "Difficulté de l'IA d'aide: " + TextManager.AI_DIFFICULTY_VALUE_TEXT_FR[this.helpArtificialIntelligenceDifficulty] + "\n";
		return res;
	}

	public int getPlayer1ArtificialIntelligenceDifficulty() {
		return player1ArtificialIntelligenceDifficulty;
	}

	public void setPlayer1ArtificialIntelligenceDifficulty(
			int player1ArtificialIntelligenceDifficulty) {
		this.player1ArtificialIntelligenceDifficulty = player1ArtificialIntelligenceDifficulty;
	}

	public int getPlayer2ArtificialIntelligenceDifficulty() {
		return player2ArtificialIntelligenceDifficulty;
	}

	public void setPlayer2ArtificialIntelligenceDifficulty(
			int player2ArtificialIntelligenceDifficulty) {
		this.player2ArtificialIntelligenceDifficulty = player2ArtificialIntelligenceDifficulty;
	}
}
