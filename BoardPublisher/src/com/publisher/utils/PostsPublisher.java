package com.publisher.utils;
/**
 * Interface contenant l'intégralité des textes du module.
 * @author Benjamin Letourneau
 * @version 1.0
 */
public interface PostsPublisher {
	
	/**
	 * Constante pour afficher ":".
	 */
	public static final String COLON = " : ";

	/**
	 * Constante permettant de donner l'extension du fichier de sauvegarde.
	 */
	public static final String DOT_XML = ".xml", DOT = ".";
	
	/**
	 * Constante pour effectuer un saut de ligne dans la contruction d'une String.
	 */
	public static final String EOF = "\n";
	
	/**
	 * Constante qui affiche le signe de la multiplication.
	 */
	public static final String MULT_SIGN = "x";
	
	/**
	 * Constante qui realise un espace.
	 */
	public static final String ONE_SPACES = " ";
	
	/**
	 * Constante qui réalise trois espaces.
	 */
	public static final String THREE_SPACES = "   ";
	
	/**
	 * Constante qui réalise deux espaces et un pipe.
	 */
	public static final String TWO_SPACES_PIPE = "  |";
	
	/**
	 * Constante qui réalise un espace et un pipe.
	 */
	public static final String SPACES_PIPE = " |";
	
	/**
	 * Constantes indiquant la couleur du joueur. 
	 */
	public static final String WHITE = "white", BLACK = "black";
	
	/**
	 * Constante indiquant le type du joueur.
	 */
	public static final String HUMAN = "human", MACHINE = "machine";
	
	/**
	 * Constantes permettant de donner un nom par défaut à chaque joueur.
	 */
	public static final String FIRST_PLAYER_NAME_POST = "J. McCain", SECOND_PLAYER_NAME_POST = "J. Rambo";
	
	/**
	 * Constantes permettant de générer le fichier de sauvegarde.
	 */
	public static final String BOARD_PART = "board", INIT_PART = "init", SIZE_PART = "size", X_PART = "x",
			Y_PART = "y", COLOR_PART = "c", PIECES_PART = "pieces", PIECE_PART = "piece", 
			PLAYER_PART = "player", PLAYER_LOGIN_PART = "name", PLAYER_COLOR_PART = "rgb",
			PLAYER_TYPE_PART = "type", PLAYER_NUMBER_PART = "num", AI_LEVEL_PART = "AILevel",
			AI_THINKING_TIME_PART = "AIThinkingTime", PLAYED_PIECES_PART = "playedPcs";
	
	
	/**********************************************************************************
	 * 																				  *
	 *  CONSTANTES EN FRANCAIS PERMETTANT LA CREATION DE MESSAGES POUR L'UTILISATEUR  *
	 *  																			  * 
	 **********************************************************************************/
	/**
	 * Constante "longueur" pour la génération de question à poser à l'utilisateur.
	 */
	public static final String LENGTH_CAPITAL_FR = "LONGUEUR";
	
	/**
	 * Constante "longueur" pour la génération de question à poser à l'utilisateur.
	 */
	public static final String WIDTH_CAPIAL_FR = "LARGEUR";
	
	/**
	 * Constante permettant d'informer l'utilisateur sur le déroulement de la création de l'Othellier.
	 */
	public static final String INITIALIZATION_POST_FR = "Nous allons maintenant initialiser notre plateau de jeu avec des pions blanc et noir.";
	
	/**
	 * Constante permettant de donner des indications sur la façon d'initialiser un Othellier.
	 */
	public static final String INITIALIZATION_RULES_POST_FR = "Vous devez poser un minimum de deux pions blanc et deux pions noir sur le plateau afin de pouvoir jouer.";
	
	/**
	 * Constante permettant d'informer l'utilisateur sur le déroulement de la création de l'Othellier. 
	 */
	public static final String END_POST_FR = "Votre plateau est enfin prêt, bon jeu ;) : ";
	
	/**
	 * Constante permettant de créer une phrase pour l'initialisation de l'Othellier.
	 */
	public static final String INITIALIZE_BOARD_POST_1_FR = "Veuillez saisir la ";
	
	/**
	 * Constante permettant de créer une phrase pour l'initialisation de l'Othellier.
	 */
	public static final String INITIALIZE_BOARD_POST_2_FR = " de votre plateau de jeu (compris entre 4 et 50) : ";
	
	/**
	 * Constante demandant à l'utilsateur la couleur du pion qu'il veut ajouter à l'Othellier.
	 */
	public static final String COLOR_QUESTION_1_FR = "Quel est la couleur du pion à poser sur la grille ? (1 pour blanc, 2 pour noir) : " ;
	
	/**
	 * Constante indiquant les choix possibles pour la couleur d'un pion.
	 */
	public static final String COLOR_QUESTION_2_FR = "1 pour BLANC, 2 pour NOIR : ";
	
	/**
	 * Constante permettant de poser questionner l'utilisateur sur la position future de son pion.
	 */
	public static final String PIECE_POSITION_QUESTION_FR = "Quelle est la position de votre pion sur la grille ? ";
	
	/**
	 * Constante indiquant à l'utilisateur les possibilités de positionnement de son pion sur l'Othellier.
	 */
	public static final String PIECE_POSITION_LENGTH_HINT_FR = "La position sur la LONGUEUR doit être compris entre 0 et ";
	
	/**
	 * Constante indiquant à l'utilisateur les possibilités de positionnement de son pion sur l'Othellier.
	 */
	public static final String PIECE_POSITION_WIDTH_HINT_FR = "La position sur la LARGEUR doit être compris entre 0 et ";
	
	/**
	 * Constante permettant d'indiquer à l'utilisateur que les coordonnées qu'il vient de saisir pour poser son pion sont incorrecte.
	 */
	public static final String WARNING_PIECE_POSITION_POST_FR = "ATTENTION ! Vous ne pouvez pas poser un pion à cet emplacement, il y en a déjà un ! ";
	
	/**
	 * Constante demandant à l'utilisateur s'il veut poser un autre pion sur l'Othellier.
	 */
	public static final String PUT_NEW_PIECE_POST_1_FR = "Voulez vous poser un autre pion sur la grille ? (taper 1 pour oui et 0 pour non) :";
	
	/**
	 * Constante demandant à l'utilisateur s'il veut poser un autre pion sur l'Othellier.
	 */
	public static final String PUT_NEW_PIECE_POST_2_FR = "Taper 1 pour oui et 0 pour non : ";
	
	/**
	 * Constante permettant l'affichage de l'Othellier. 
	 */
	public static final String BOARD_SIZE_POST_FR = "Taille du plateau : ";
	
	/**
	 * Constante indiquant quel joueur va commencer.
	 */
	public static final String FIRST_PLAYER_POST_FR = "Le joueur Blanc commence.";
	
	/**
	 * Constante indiquant à l'utilisateur à saisi une entrée incorrecte.
	 */
	public static final String ERROR_RECOVERY_RESULT_FR = "Vous avez saisi une entrée incorrecte.";
	
	/**
	 * Constante indiquant à l'utilisateur qu'une erreur est survenue pendant la reccupération de ses données.
	 */
	public static final String INPUT_FATAL_ERROR_FR = "Un probleme est survenu au niveau de la réccupération des entrées utilisateur, fermeture du programme.";
	
	/**
	 * Constante indiquant à l'utilisateur qu'une erreur est survenue pendant la sauvegarde du jeu.
	 */
	public static final String SAVE_FATAL_ERROR_FR = "Une erreur est survenue pendant la sauvegarde de votre fichier, votre carte n'a pas été suavegardé. ";
	
	/**
	 * Constante demandant à l'utilisateur de saisir le nom du fichier de sauvegarde.
	 */
	public static final String SAVE_FILE_NAME_REQUEST_FR = "Quel nom voulez-vous donner à votre fichier de sauvegarde. ";
	
	/**
	 * Constante demandant à l'utilisateur de saisir le nom du fichier contenant la grille de jeu à charger.
	 */
	public static final String LOAD_BOARD_FILE_NAME_REQUEST_FR = "Quel est le nom du fichier contenant la grille de jeu ? ";
	
	/**
	 * Constante demandant à l'utilisateur de saisir le nom du fichier contenant la grille de jeu à charger.
	 */
	public static final String LOAD_OR_CREATE_BOARD_REQUEST_FR = "Voulez-vous charger un fichier contenant un othelier initial, ou le créer ? (1 pour charger le fichier, 2 pour le créer) ";
	
	/**
	 * Message d'erreur générale.
	 */
	public static final String ERROR_FR = "Une erreur est survenue. ";
	
	/**
	 * Message d'erreur sur le chargement de l'othelier initial.
	 */
	public static final String ERROR_DURING_LOAD_BOARD_FILE_FR = "Une erreur est survenue pendant le chargement du fichier contenant l'othelier initial. ";
	
	/**
	 * Message d'erreur sur le chargement de l'othelier initial, la taille ne correspond pas.
	 */
	public static final String WRONG_BOARD_SIZE_FR = "L'othelier ne correspond pas aux dimensions saisies plus haut. Chargement d'une grille par défaut. ";
	
	/**
	 * Message d'erreur sur le chargement de l'othelier initial, le fichier n'existe pas.
	 */
	public static final String ERROR_FILE_NOT_EXISTING_FR = "Le fichier n'existe pas. Chargement d'une grille par défaut.";
	
	/**
	 * Message d'erreur sur le chargement de l'othelier initial, le fichier contient un caractère interdit.
	 */
	public static final String WRONG_FILE_FORMAT_FR = "Un caractère interdit est contenu dans le fichier.";
}
