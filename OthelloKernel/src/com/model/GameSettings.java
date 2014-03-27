package com.model;

import java.util.ArrayList;
import java.util.List;

import utils.TextManager;

import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.model.player.MachinePlayer;
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
		this.player1 = player1;
		this.player2 = player2;

		this.gameBoard = gameBoard;

		this.artificialIntelligenceThinkingTime = artificialIntelligenceThinkingTime;
		this.helpArtificialIntelligenceDifficulty = artificialIntelligenceDifficulty;

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
			this.setCurrentPlayer(this.player1);
		else
			this.setCurrentPlayer(this.player2);
		
		this.gameBoard = this.gameBoardHistory.get(this.sentinel+1);
		this.gameBoard.notifyObservers();
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

			this.gameBoard = this.gameBoardHistory.get(this.sentinel);
			this.gameBoard.notifyObservers();

			this.sentinel--;
			return true;
		}

		if(this.getOpponentPlayer().getPlayerType() instanceof MachinePlayer){
			getBackInHistory();
		}
		return false;
	}

	public boolean getForwardInHistory(){
		System.out.println("[getForwardInHistory]");
		if(this.sentinel < this.gameHistory.size()-1){
			//TODO check si ++ de sentinel avant ou après a cause de l'avancement des boards
			this.sentinel++;
			this.gameBoard = this.gameBoardHistory.get(this.sentinel+1);

			//TODO check si change player fonctionne
			if(this.gameHistory.get(this.sentinel).getColor() instanceof WhitePiece)
				this.setCurrentPlayer(this.player1);
			else
				this.setCurrentPlayer(this.player2);

			return true;
		}

		return false;
	}

	public void reversePlayer(){
		if(this.player1.getColor().equals(TextManager.BLACK_PLAYER))
			this.player1.setColor(TextManager.WHITE_PLAYER);
		else
			this.player1.setColor(TextManager.BLACK_PLAYER);

		if(this.player2.getColor().equals(TextManager.BLACK_PLAYER))
			this.player2.setColor(TextManager.WHITE_PLAYER);
		else
			this.player2.setColor(TextManager.BLACK_PLAYER);
		this.changePlayer();
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
		System.out.println("[manageBoardHistory]");
		
		Piece p = this.gameBoard.getBoard()[x][y];
		if(this.gameHistory.size()-1 > this.sentinel){
			this.sentinel++;
			
			for(Piece p_ : gameHistory)
				System.out.println("Piece game history : " + p_);
			
			System.out.println("Sentinel  : " + sentinel);
			System.out.println("p : " + p);
			System.out.println("hg p : " + this.gameHistory.get(this.sentinel));
			
			if(!this.gameHistory.get(this.sentinel).equals(p)){
				System.out.println("On créer un nouveau future");
				for(int i = this.sentinel, bordI = this.sentinel+1; i < this.gameHistory.size(); i++, bordI++){
					System.out.println( "Remove piece : " + this.gameHistory.remove(i));
					System.out.println( "Remove Board : " + this.gameBoardHistory.remove(bordI));
				}
				this.gameHistory.add(this.sentinel, p.clone());
				this.gameBoardHistory.add((BoardObservable)this.gameBoard.clone());
			}else
				System.out.println("On recrit l'ancien future ... :p!");
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
