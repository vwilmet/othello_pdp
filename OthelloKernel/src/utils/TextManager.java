package utils;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public interface TextManager {
	// Utils
	public static final String DOT_XML = ".xml";
	public static final String DOT = ".";
	
	// /////////////////////////////////////////
	// ////////////////View/////////////////////
	// /////////////////////////////////////////
	// error
	public static final String IO_EXCEPTION_ERROR = "Une erreur est survenue pendant l'ouverture du fichier!";

	// menu part
	public static final String MENU_TEXT_FR = "Menu";
	public static final String NEW_GAME_TEXT_FR = "Nouvelle partie";
	public static final String OPEN_FILE_TEXT_FR = "Ouvrir";
	public static final String CONTINUE_GAME_TEXT_FR = "Continuer";
	public static final String CHOOSE_POS_TEXT_FR = "Choisir la position";
	public static final String PRE_CONF_FILE_TEXT_FR = "Fichier pré-configuré";
	public static final String SAVE_GAME_TEXT_FR = "Enregistrer sous ...";

	// Option part
	public static final String OPTION_TEXT_FR = "Options";

	// Help part
	public static final String HELP_TEXT_FR = "Aide";

	public static final String GRID_SIZE_LABEL_TEXT_FR = "Taille de la grille";
	public static final String GRID_ROW_LABEL_TEXT_FR = "Colonne : ";
	public static final String GRID_LIGNE_LABEL_TEXT_FR = "Ligne : ";
	public static final String AI_LABEL_TEXT_FR = "Intelligence Artificielle";
	public static final String AI_THINKING_TIME_LABEL_TEXT_FR = "Temps de réflexion : ";
	public static final String AI_DIFFICULTY_LABEL_TEXT_FR = "Difficulté : ";
	public static final String[] AI_DIFFICULTY_VALUE_TEXT_FR = {"Facile", "Moyen", "Difficile"};
	public static final String PLAYER_LABEL_TEXT_FR = "Joueur";
	public static final String PLAYER_1_NAME_TEXT_FR = "Nom du joueur 1 : ";
	public static final String PLAYER_2_NAME_TEXT_FR = "Nom du joueur 2 : ";
	
	
	//Other
	//frame title
	public static final String  CHOOSE_VIEW_TITLE_FR = "Choix de la position de reprise";
	public static final String  GAME_VIEW_TITLE_FR = "Othello";
	public static final String  INITGAME_VIEW_TITLE_FR = "Initialisation du jeu";
	public static final String  BENCHMARK_VIEW_TITLE_FR = "Lancement du benchmark ...";
	
	//button title
	public static final String  CHOOSE_VIEW_VALID_BUTTON_FR = "Lancer";
	public static final String  CHOOSE_VIEW_CANCEL_BUTTON_FR = "Annuler";

	public static final String  INIT_GAMEVIEW_VALID_BUTTON_FR = "OK";
	public static final String  INIT_GAMEVIEW_BENCHMARK_BUTTON_FR = "BenchMark";
	public static final String  INIT_GAMEVIEW_CANCEL_BUTTON_FR = "Annuler";
	
	public static final String  INIT_GAMEVIEW_BENCHMARK_TITLE_BUTTON_FR = "Lance un benchmark sur les performances de la machine pour calculer le temps idéal de l'IA !";
	
	// /////////////////////////////////////////
	// //////////////Controller/////////////////
	// /////////////////////////////////////////

	// /////////////////////////////////////////
	// ////////////////Model////////////////////
	// /////////////////////////////////////////
	public static final String ERROR_FR = "Une erreur est survenue. ";
	
	// Factory Error
	public static final String WRONG_FACTORY_REFERRED_FR = "Vous avez instancié la mauvaise fabrique. ";
	public static final String PIECE_FACTORY_REQUIRED_FR = "Vous êtes dans une fabrique de \"Piece\". ";
	public static final String PLAYER_FACTORY_REQUIRED_FR = "Vous êtes dans une fabrique de \"Player\". ";
	public static final String BOARD_FACTORY_REQUIRED_FR = "Vous êtes dans une fabrique de \"Board\". ";
	public static final String GAME_SETTINGS_FACTORY_REQUIRED_FR = "Vous êtes dans une fabrique de \"GameSettings\". ";
	public static final String SAVE_GAME_FACTORY_REQUIRED_FR = "Vous êtes dans une fabrique de \"SaveGame\". ";
	public static final String RESTORE_GAME_FACTORY_REQUIRED_FR = "Vous êtes dans une fabrique de \"RestoreGame\". ";

	// Game Errors
	public static final String WRONG_BOARD_SIZE_FR = "La taille de votre othellier est incorrecte, veuillez vous référer au manuel de jeu. ";
	public static final String WRONG_INITIAL_PIECE_COLOR_FR = "Vous ne pouvez ajouter qu'un pion réel à la liste des pions initiaux de votre jeu. ";
	public static final String WRONG_PIECE_COLOR_FR = "Vous ne pouvez ajouter qu'un pion réel à la liste des pions de votre jeu. ";
	public static final String WRONG_INITIAL_PIECE_POSITION_FR = "Vous ne pouvez pas ajouter deux pions sur un même endroit de l'othellier. ";
	public static final String ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE_FR = "Une erreur est survenue pendant la lecture du fichier de sauvegarde de jeu. ";
	public static final String ERROR_DURING_THE_WRITE_OF_GAME_SAVE_FILE_FR = "Une erreur est survenue pendant l'écriture du fichier de sauvegarde de jeu. ";
	public static final String ERROR_WRONG_FORMAT_SAVE_GAME_FILE_FR = "Il y a une erreur dans le formattage du fichier de sauvegarde.";
	
	// Player
	public static final String PLAYER_INFORMATION_1ST_PLAYER_FR = "Vous êtes le premier joueur."; 
	public static final String PLAYER_INFORMATION_2ND_PLAYER_FR = "Vous êtes le second joueur."; 
	
	public static final String HUMAN_PLAYER = "human";
	public static final String MACHINE_PLAYER = "machine";
	public static final String WHITE_PLAYER = "white";
	public static final String BLACK_PLAYER = "black";
	
	
	//Save/RestoreGame
	public static final String BOARD_PART = "board";
	public static final String INIT_PART = "init";
	public static final String SIZE_PART = "size";
	public static final String X_PART = "x";
	public static final String Y_PART = "y";
	public static final String COLOR_PART = "c";
	public static final String PIECES_PART = "pieces";
	public static final String PIECE_PART = "piece";
	
	public static final String PLAYER_PART = "player";
	public static final String PLAYER_LOGIN_PART = "name";
	public static final String PLAYER_COLOR_PART = "rgb";
	public static final String PLAYER_TYPE_PART = "type";
	public static final String PLAYER_NUMBER_PART = "num";
	
	public static final String AI_LEVEL_PART = "AILevel";
	public static final String AI_THINKING_TIME_PART = "AIThinkingTime";
	public static final String PLAYED_PIECES_PART = "playedPcs";
	public static final String HISTORY_PART = "history";
	
	public static final String SAVE_FATAL_ERROR_FR = "Une erreur est survenue pendant la sauvegarde de la partie.";
	
	public static final String DEFAULT_PLAYER1_NAME_FR = "Joueur 1";
	public static final String DEFAULT_PLAYER2_NAME_FR = "Joueur 2";
}
