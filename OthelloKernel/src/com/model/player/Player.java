package com.model.player;

import utils.GameHandlerException;
import utils.TextManager;

import com.publisher.BoardPublisher;

/**
 * Classe Player
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public class Player {
	
	/**
	 * Login du joueur.
	 */
	private String login;
	
	/**
	 * Couleur du joueur (white ou black).
	 */
	private String color;
	
	/**
	 * Nombre de pions que le joueur possède en cours de partie (util pour les statistiques à afficher).
	 */
	private int piecesNumber;
	
	/**
	 * Numéro du joueur.
	 */
	private int playerNumber;
	
	/**
	 * Type du joueur (humain ou machine).
	 */
	private PlayerType type;

	/**
	 * Constructeur de classe.
	 * @param login : String, chaine de caractère correspondant au login du joueur.
	 * @param c : String, Chaine de caractère correspondant à la couleur du joueur. C'est elle qui sera sauvegardée dans le fichier de sauvegarde. 
	 * @param playerNumber : int, numéro du joueur.
	 * @throws GameHandlerException
	 */
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

	/**
	 * Accesseur (lecture) le login du joueur.
	 * @return String : Login du joueur. 
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Accesseur (lecture) sur la couleur du joueur.
	 * @return String : Couleur du joueur.
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Accesseur (Ecriture) sur la couleur du joueur.
	 * @param color : String, couleur à affecter au joueur.
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Accesseur (lecture) sur le nombre de pions que le joueur possède.
	 * @return
	 */
	public int getPiecesNumber() {
		return this.piecesNumber;
	}

	/**
	 * Accesseur (Ecriture) sur le nombre de pions que le joueur possède.
	 * @param piecesNumber : nouveau nombre de pions que possède le joueur.
	 */
	public void setPiecesNumber(int piecesNumber) {
		this.piecesNumber = piecesNumber;
	}
	
	/**
	 * Accesseur (lecture) sur le numéro du joueur.
	 * @return int : Numéro du joueur. 
	 */
	public int getPlayerNumber(){
		return this.playerNumber;
	}
	
	/**
	 * Accesseur (Ecriture) sur le numéro du joueur.
	 * @param number : int,  Nouveau numéro de joueur.
	 */
	public void setPlayerNumber(int number){
		this.playerNumber = number;
	}
	
	/**
	 * Accesseur (Lecture) sur le type de joueur.
	 * @return PlayerType : type du joueur.
	 */
	public PlayerType getPlayerType(){
		return this.type;
	}
	
	/**
	 * Accesseur (Ecriture) sur le type de joueur.
	 * @return instance modifiée en humain du joueur. 
	 */
	public Player setHuman(){
		this.type = new HumanPlayer();
		return this;
	}
	
	/**
	 * Accesseur (Ecriture) sur le type de joueur.
	 * @return instance modifiée en machine du joueur. 
	 */
	public Player setMachine(){
		this.type = new MachinePlayer();
		return this;
	}

	/**
	 * Méthode permettant d'afficher le contenu d'un joueur.
	 * @return String : Chaine correspondant à l'affichage d'un joueur.
	 */
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
