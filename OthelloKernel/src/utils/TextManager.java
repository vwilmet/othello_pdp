package utils;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public interface TextManager {

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

	public static final String DEFAULT_PLAYER1_NAME_FR = "Joueur 1";
	public static final String DEFAULT_PLAYER2_NAME_FR = "Joueur 2";
}
