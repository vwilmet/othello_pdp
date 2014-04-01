package com.publisher;

/**
 * Interface de gestion du module de génération de plateau pour Othello
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public interface BoardPublisher {
	
	/**
	 * Constantes indiquant la couleur du joueur. 
	 */
	public static final String WHITE_PLAYER = "white", BLACK_PLAYER = "black";
	
	/**
	 * Constante indiquant le type du joueur.
	 */
	public static final String HUMAN_PLAYER = "human", MACHINE_PLAYER = "machine";
	
	/**
	 * Constantes permettant de générer le fichier de sauvegarde.
	 */
	public static final String BOARD_PART = "board",
			INIT_PART = "init",
			SIZE_PART = "size",
			X_PART = "x",
			Y_PART = "y", 
			COLOR_PART = "c",
			PIECES_PART = "pieces",
			PIECE_PART = "piece", 
			PLAYER_PART = "player",
			PLAYER_LOGIN_PART = "name",
			PLAYER_COLOR_PART = "rgb",
			PLAYER_TYPE_PART = "type",
			PLAYER_NUMBER_PART = "num",
			AI_HELP_LEVEL_PART = "AIHelpLevel",
			AI_PLAYER1_LEVEL_PART = "AIP1Level",
			AI_PLAYER2_LEVEL_PART = "AIP2Level",
			AI_THINKING_TIME_PART = "AIThinkingTime",
			PLAYED_PIECES_PART = "playedPcs",
			HISTORY_PART = "history";
	
	/**
	 * Methode permettant d'utiliser le module de génération d'othellier (plateau de jeu d'othello).
	 */
	public void boardMaker();
}
