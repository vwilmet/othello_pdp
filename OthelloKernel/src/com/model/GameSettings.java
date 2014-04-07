package com.model;

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
 * Cette classe contient toutes les données necessaires pour la gestion de l'othellier <br/>
 * C'est la classe principale du Model
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

	/**
	 * Temps de réflexion maximal de l'IA autorisé.
	 */
	public static final int AI_THINKING_TIME_LIMIT_MAX = 900000;
	
	/**
	 * Temps de réflexion minimal de l'IA autorisé.
	 */
	public static final int AI_THINKING_TIME_LIMIT_MIN = 2000;
	
	/**
	 * Taille minimal du plateau sur l'axe des abscisses.
	 */
	public static final int BOARD_MIN_SIZE_X = 4;
	
	/**
	 * Taille minimal du plateau sur l'axe des ordonnées.
	 */
	public static final int BOARD_MIN_SIZE_Y = 4;

	/**
	 * Taille maximal du plateau sur l'axe des abscisses.
	 */
	public static final int BOARD_MAX_SIZE_X = 50;
	
	/**
	 * Taille maximal du plateau sur l'axe des ordonnées.
	 */
	public static final int BOARD_MAX_SIZE_Y = 50;

	/**
	 * Constante contenant le chemin vers le site web local
	 */
	public static final String HELP_WEBSITE_PATH = "resources/website/index.html";

	/**
	 * Joueurs du jeu (machine ou humain). 
	 */
	private Player player1, player2;

	/**
	 * Variable contenant le joueur actuel qui doit jouer
	 */
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
	
	/**
	 * Variable contenant l'ensemble des board jouée depuis le début de la partie
	 */
	private List <BoardObservable> gameBoardHistory;
	
	/**
	 * Sentinel utilisée pour se repérer dans l'historique! Les deux listes gameHistory et gameBoardHistory 
	 * l'utilise mais gameBoardHistory à une case de plus car il contient la partie initial
	 */
	private int sentinel;
	
	/**
	 * Constructeur de classe.
	 * @param player1 : Player, joueur 1 de la partie.
	 * @param player2 : Player, joueur 2 de la partie.
	 * @param gameBoard : BoardObservable, plateau courrant de jeu.
	 * @param artificialIntelligenceThinkingTime : int, temps de réflexion de l'intelligence artificielle.
	 * @param artificialIntelligenceDifficulty : int, difficulté par defaut de l'IA (d'aide).
	 * @param history : List<Piece>, historique de tous les coups joués (si les coups existent).
	 */
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

	/**
	 * Méthode permettant de changer le joueur courrant.
	 */
	public void changePlayer(){
		this.currentPlayer = this.currentPlayer == player1 ? player2 : player1;
	}

	/**
	 * Accesseur (lecture) sur le joueur courrant.
	 * @return Player : Joueur courrant.
	 */
	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}

	/**
	 * Accesseur sur le joueur qui ne joue pas.
	 * @return Player : Joueur qui ne joue pas.
	 */
	public Player getOpponentPlayer(){
		return this.currentPlayer == player1 ? player2 : player1;
	}

	/**
	 * Accesseur (Ecriture) permettant de modifier le joueur courrant.
	 * @param currentPlayer : Player, joueur à affecteur au current player.
	 */
	public void setCurrentPlayer(Player currentPlayer){
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Accesseur sur le joueur 1 (en début de partie).
	 * @return Player : le joueur 1.
	 */
	public Player getFirstPlayer(){
		return this.player1;
	}

	/**
	 * Accesseur sur le joueur 2 (en début de partie).
	 * @return Player : le joueur 2.
	 */
	public Player getSecondPlayer(){
		return this.player2;
	}

	/**
	 * Accesseur (lecture) sur le plateau de jeu.
	 * @return BoardObservable : Plateau de jeu.
	 */
	public BoardObservable getGameBoard(){
		return this.gameBoard;
	}

	/**
	 * Accesseur (lecture) sur le temps de réflexion de l'IA.
	 * @return int : Le temps de réflexion de l'IA.
	 */
	public int getAIThinkingTime(){
		return this.artificialIntelligenceThinkingTime;
	}

	/**
	 * Accesseur (lecture) sur le niveau de l'IA (aide).
	 * @return int : Le niveau de difficulté de l'IA (aide).
	 */
	public int getHelpAIDifficulty(){
		return this.helpArtificialIntelligenceDifficulty;
	}	
	
	/**
	 * Accesseur (lecture) sur le niveau de l'IA (joueur 1).
	 * @return int : Le niveau de difficulté de l'IA (joueur 1).
	 */
	public int getPlayer1ArtificialIntelligenceDifficulty() {
		return player1ArtificialIntelligenceDifficulty;
	}

	/**
	 * Accesseur (Ecriture) sur le niveau de l'IA (joueur 1).
	 * @param int : Le niveau de difficulté de l'IA (joueur 1).
	 */
	public void setPlayer1ArtificialIntelligenceDifficulty(
			int player1ArtificialIntelligenceDifficulty) {
		this.player1ArtificialIntelligenceDifficulty = player1ArtificialIntelligenceDifficulty;
	}

	/**
	 * Accesseur (lecture) sur le niveau de l'IA (joueur 2).
	 * @return int : Le niveau de difficulté de l'IA (joueur 2).
	 */
	public int getPlayer2ArtificialIntelligenceDifficulty() {
		return player2ArtificialIntelligenceDifficulty;
	}

	/**
	 * Accesseur (Ecriture) sur le niveau de l'IA (joueur 2).
	 * @param int : Le niveau de difficulté de l'IA (joueur 2).
	 */
	public void setPlayer2ArtificialIntelligenceDifficulty(
			int player2ArtificialIntelligenceDifficulty) {
		this.player2ArtificialIntelligenceDifficulty = player2ArtificialIntelligenceDifficulty;
	}

	/**
	 * Accesseur (lecture) sur l'historique des pieces jouées pendant le jeu.
	 * @return List<Piece> : Historique des pieces joués.
	 */
	public List<Piece> getGameHistory(){
		return this.gameHistory;
	}

	/**
	 * Accesseur (lecture) sur la sentinelle dans l'historique des coups joués.
	 * @return int : position de la sentinelle dans la liste. 
	 */
	public int getHistoryPosition(){
		return this.sentinel;
	}

	/**
	 * Accesseur (lecture) sur la sentinelle dans l'historique des plateaux de jeu.
	 * @return int : position de la sentinelle dans la liste des plateaux de jeu. 
	 */
	public int getBoardHistoryPosition(){
		return this.sentinel + 1;
	}

	/**
	 * Permet de modifier la position de la sentinelle.
	 * @param sentinel : int, nouvelle position de la sentinelle.
	 */
	public void setHistoryPosition(int sentinel){
		this.sentinel = sentinel;

		if(this.sentinel == -1)
			this.setCurrentPlayer(this.player1);
		else if(this.gameHistory.get(this.sentinel).getColor() instanceof WhitePiece)
			this.setCurrentPlayer(this.player2);
		else
			this.setCurrentPlayer(this.player1);

		this.gameBoard = (BoardObservable) this.gameBoardHistory.get(this.sentinel+1).clone();
	}

	/**
	 * Méthode permettant de réccupérer un plateau de jeu à un certain moment de la partie.
	 * @param position : numero du plateau de jeu.
	 * @return BoardObservable : plateau de jeu. 
	 */
	public BoardObservable getHistoryBoard(int position){
		return this.gameBoardHistory.get(position);
	}

	/**
	 * Accesseur (lecture) sur l'historique des plateaux de jeu de la partie en cours.
	 * @return List<BoardObservable> : Ensemble des plateaux de jeu.
	 */
	public List<BoardObservable> getGameBoardHistory(){
		return this.gameBoardHistory;
	}
	
	/**
	 * Accesseur (ecriture) sur l'historique des plateaux de jeu de la partie en cours.
	 * @param List<BoardObservable> : Ensemble des plateaux de jeu à affecter.
	 */
	public void setGameBoardHistory(List<BoardObservable> gameBoardHistory){
		this.gameBoardHistory = gameBoardHistory;
	}

	/**
	 * Méthode permettant de remettre à zéro l'historique de la partie.
	 */
	public void resetHistory(){
		this.gameHistory.clear();
		this.gameBoardHistory.clear();
		this.gameBoardHistory.add((BoardObservable)gameBoard.clone());
		this.sentinel = -1;
		System.gc();
	}

	/**
	 * Méthode permettant la remise à zéro du jeu.
	 */
	public void restartGame(){
		if(this.gameBoardHistory.size() > 0)
			this.gameBoard = (BoardObservable)this.gameBoardHistory.get(0).clone();
		resetHistory();
		this.currentPlayer = this.player1;
	}
	
	/**
	 * Méthode indiquant si il y a possibilité de revenir en arrière dans la liste des coups joués.
	 * @return boolean : true si il y a possiblité, false sinon.
	 */
	public boolean canGoBack(){
		if(this.sentinel == -1)
			return false;
		return true;
	}

	/**
	 * Méthode indiquant si il y a possibilité d'avancer dans la liste des coups joués.
	 * @return boolean : true si il y a possiblité, false sinon.
	 */
	public boolean canGoForward(){
		if(this.gameHistory.size() == 0 || (this.gameHistory.size()-1) == this.sentinel)
			return false;
		return true;
	}

	/**
	 * Méthode indiquant on peut remettre à zero l'historique des coups.
	 * @return boolean : true si il y a possiblité, false sinon.
	 */
	public boolean canReset(){
		if(this.gameHistory.size() == 0)
			return false;
		return true;
	}

	/**
	 * Méthode indiquant si il y a possibilité de revenir en arrière dans l'historique.
	 * @return boolean : true si il y a possiblité, false sinon.
	 */
	public boolean getBackInHistory(){
		if(this.sentinel >= 0){
			if(this.gameHistory.get(this.sentinel).getColor() instanceof WhitePiece)
				this.setCurrentPlayer(this.player1);
			else
				this.setCurrentPlayer(this.player2);

			this.gameBoard = (BoardObservable) this.gameBoardHistory.get(this.sentinel).clone();

			this.sentinel--;
			
			return true;
		}

		return false;
	}

	/**
	 * Méthode indiquant si il y a possibilité d'avancer dans l'historique.
	 * @return boolean : true si il y a possiblité, false sinon.
	 */
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

	/**
	 * Méthode permettant de changer les joueurs.
	 */
	public void reversePlayer(){
		if(this.player1.getColor().equals(BoardPublisher.BLACK_PLAYER)){
			this.player1.setColor(BoardPublisher.WHITE_PLAYER);
		}else{
			this.player1.setColor(BoardPublisher.BLACK_PLAYER);
		}
		
		if(this.player2.getColor().equals(BoardPublisher.BLACK_PLAYER)){
			this.player2.setColor(BoardPublisher.WHITE_PLAYER);
		}else{
			this.player2.setColor(BoardPublisher.BLACK_PLAYER);
		}
		
		this.player1.setPlayerNumber(2);
		this.player2.setPlayerNumber(1);
		
		this.changePlayer();
	}

	/**
	 * Méthode permettant de modifier le la couleur d'un pion.
	 * @param i : int, coordonnée du pion sur l'axe des abscisses.
	 * @param j : int, coordonnée du pion sur l'axe des ordonnées.
	 */
	public void setPiece(int i, int j) {

		if(this.currentPlayer.getColor().equals(BoardPublisher.WHITE_PLAYER))
			this.gameBoard.setWhitePiece(i, j);
		else
			this.gameBoard.setBlackPiece(i, j);
	}

	/**
	 * 
	 * @param p : Piece que l'utilisateur vient de jouer. 
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

	/**
	 * Méthode d'affichage du contenu d'une partie.
	 */
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
}
