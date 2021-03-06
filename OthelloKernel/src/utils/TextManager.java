package utils;

import com.model.GameSettings;

/**
 * Cette interface contient tous les textes de l'interface utilisateur, écrit en français
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public interface TextManager {
	// Utils
	public static final String DOT_XML = ".xml";
	public static final String DOT = ".";
	public static final String DOT_SLASH = "./";
	public static final String EOF = "\n";
	public static final String TAB = "\t";
	
	// /////////////////////////////////////////
	// ////////////////View/////////////////////
	// /////////////////////////////////////////
	// error
	public static final String IO_EXCEPTION_ERROR = "Une erreur est survenue pendant l'ouverture du fichier!";
	public static final String ERROR_DURING_BUTTON_IMG_FR = "Impossible d'ouvrir les images des pions!";
	public static final String WRONG_GRID_SIZE_FR = "Grille hors des limites autorisées";
	public static final String WRONG_FACTORY_USES_FR = "Mauvaise utilisation de la factory";
	// menu part
	public static final String MENU_TEXT_FR = "Menu";
	public static final String NEW_GAME_TEXT_FR = "Nouvelle partie";
	public static final String CONFIGURE_BOARD_FR = "Configurer une partie";
	public static final String SAVE_POSITON_TEXT_FR = "Enregistrer la suite de coup";
	public static final String OPEN_FILE_TEXT_FR = "Ouvrir";
	public static final String CONTINUE_GAME_TEXT_FR = "Continuer";
	public static final String CHOOSE_POS_TEXT_FR = "Choisir la position";
	public static final String SAVE_GAME_TEXT_FR = "Enregistrer sous ...";

	// Option part
	public static final String OPTION_TEXT_FR = "Options";

	// Help part
	public static final String HELP_TEXT_FR = "Aide";

	public static final String GRID_SIZE_LABEL_TEXT_FR = "Taille de la grille";
	public static final String GRID_ROW_LABEL_TEXT_FR = "Colonne : ";
	public static final String GRID_LIGNE_LABEL_TEXT_FR = "Ligne : ";
	public static final String AI_LABEL_TEXT_FR = "Intelligence Artificielle pour l'aide";
	public static final String IS_PLAYER_AI_LABEL_TEXT_FR = "Jouer en tant qu'IA";
	public static final String AI_THINKING_TIME_LABEL_TEXT_FR = "Temps de réflexion : ";
	public static final String AI_DIFFICULTY_LABEL_TEXT_FR = "Difficulté : ";
	public static final String[] AI_DIFFICULTY_VALUE_TEXT_FR = {"Facile", "Moyen", "Difficile"};
	public static final String PLAYER_LABEL_TEXT_FR = "Joueur";
	public static final String PLAYER_1_NAME_TEXT_FR = "Nom du joueur Blanc : ";
	public static final String PLAYER_2_NAME_TEXT_FR = "Nom du joueur Noir : ";
	
	public static final String ERROR_INIT_ROW_OUT_OF_LIMIT = "Le nombre de colonne doit être compris entre " + GameSettings.BOARD_MIN_SIZE_X + " et " + GameSettings.BOARD_MAX_SIZE_X + " !";
	public static final String ERROR_INIT_LIGNE_OUT_OF_LIMIT = "Le nombre de ligne doit être compris entre " + GameSettings.BOARD_MIN_SIZE_Y + " et " + GameSettings.BOARD_MAX_SIZE_Y + " !";
	public static final String ERROR_INIT_IA_THINKING_TIME_OUT_OF_LIMIT = "La durée de réflexion de l'IA doit être compris entre " + GameSettings.AI_THINKING_TIME_LIMIT_MIN + " et " + GameSettings.AI_THINKING_TIME_LIMIT_MAX + " !";
	public static final String ERROR_INIT_TITLE_POPUP = "Erreur de paramètrage ...";
	
	//Other
	//frame title
	public static final String  CHOOSE_VIEW_TITLE_FR = "Choix de la position de reprise";
	public static final String  GAME_VIEW_TITLE_FR = "Othello";
	public static final String  INITGAME_VIEW_TITLE_FR = "Initialisation du jeu";
	public static final String  SELECT_FILE_VIEW_TITLE_FR = "Choisir un fichier";
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
	public static final String FILE_NOT_EXISTING_1_FR = "Le fichier de sauvegarde \"";
	public static final String FILE_NOT_EXISTING_2_FR = "\" n'existe pas.";
	
	// Player
	public static final String PLAYER_INFORMATION_1ST_PLAYER_FR = "Premier joueur.";
	public static final String PLAYER_ONE_FR = "Joueur 1 : ";
	public static final String PLAYER_TWO_FR = "Joueur 2 : ";
	public static final String PLAYER_INFORMATION_2ND_PLAYER_FR = "Second joueur."; 
	public static final String DOT_EOF = ".\n";
	public static final String PLAYER_COLOR_POST_FR = "Couleur du joueur : ";
	public static final String PLAYER_TYPE_POST_FR = "Type du joueur : ";
	public static final String PIECES_ON_BOARD_FR = "Pions sur le plateau : ";
	
	public static final String ERROR_ABOUT_PLAYER_NUMBER_FR = "Deux joueurs (humain ou machine) sont nécessaires pour le jeu."; 

	public static final String HUMAN_LABEL_PLAYER = "Humain";
	public static final String MACHINE_LABEL_PLAYER = "Machine";
	
	// Board
	public static final String WRONG_SIZE_X_FR = "La taille de votre othellier sur l'axe des abscisses doit être comprise entre 4 et 50.";
	public static final String WRONG_SIZE_Y_FR = "La taille de votre othellier sur l'axe des ordonnées doit être comprise entre 4 et 50.";
	public static final String PIECE_NUMBER_ON_BOARD_FR = "Taille de l'othellier (nombre de pions sur le plateau) : ";

	// GameSettings
	public static final String AI_DIFFICULTY_POST_FR = "Difficulté de l'IA : ";
	public static final String HELP_AI_DIFFICULTY_POST_FR = "Difficulté de l'IA d'aide: ";
	public static final String AI_THINKING_TIME_POST_FR = "Temps de réflexion des IA : ";
	
	
	//Save/RestoreGame	
	public static final String SAVE_FATAL_ERROR_FR = "Une erreur est survenue pendant la sauvegarde de la partie.";
	public static final String AUTOSAVE_FATAL_ERROR_FR = "Une erreur est survenue pendant la sauvegarde automatique de la partie.";
	
	// Status Posts
	public static final String DEFAULT_PLAYER1_NAME_FR = "Blanc";
	public static final String DEFAULT_PLAYER2_NAME_FR = "Noir";

	public static final String PAUSE_TEXT_VUE = "Jeu en pause !! Appuyer sur le bouton play pour reprendre";
	public static final String PAUSE_MESSAGE_LIST_VUE = "Jeu mis en pause";
	public static final String PLAY_MESSAGE_LIST_VUE = "Jeu démarré";
	public static final String NEM_GAME_START_MESSAGE_LIST_VUE = "Nouvelle partie démarrée!";
	
	public static final String BACK_PIECE_MESSAGE_LIST_VUE = "Retrait d'une pièce dans l'othellier";
	public static final String FORWARD_PIECE_MESSAGE_LIST_VUE = "Rajout de la dernière pièce jouée dans l'othellier";

	public static final String RESET_PIECE_MESSAGE_LIST_VUE = "Partie recommencée";
	public static final String REVERSE_PLAYER_MESSAGE_LIST_VUE = "Joueurs inversés";
	

	public static final String OPTION_POPUP_TITLE = "Caractéristiques";

}
