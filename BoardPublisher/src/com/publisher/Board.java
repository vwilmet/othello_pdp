package com.publisher;

import java.util.Scanner;

import com.error_manager.Log;
import com.publisher.generator.LoadBoardFile;
import com.publisher.utils.BPHandlerException;
import com.publisher.utils.PostsPublisher;
import com.publisher.utils.Utils;
/**
 * Classe permettant de reccupérer les différentes informations sur les caractéristiques de l'Othellier à créer auprès de l'utilisateur. 
 * @author <ul><li>Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class Board {
	
	/**
	 * Constantes sur les entiers utiles.
	 */
	private static final int ZERO = 0, ONE = 1;
	
	/**
	 * Constantes sur les bornes (choix possibles de l'utilisateur.
	 */
	private static final int BOUND_TWO = 2, BOUND_FOUR = 4, BOUND_TEN = 10, BOUND_FIFTY = 50;
	
	/**
	 * Constantes de parametrage de l'IA (valeures par defaut).
	 */
	private static final int DEFAULT_AI_THINKING_TIME = 2000, DEFAULT_AI_LEVEL = 1;
	
	/**
	 * Constantes permettant la représentation des couleures dans la grille en cours de création.
	 */
	public static final int EMPTY_COLOR_VALUE = 0, WHITE_COLOR_VALUE = 1, BLACK_COLOR_VALUE = 2;
	
	/**
	 * Attributs indiquant la taille de l'Othellier
	 */
	private int nbPieceX, nbPieceY;

	/**
	 * Attribut permettant de stocker le plateau de jeu.
	 */
	private int[][] gameBoard;
	
	/**
	 * Attributs permettant de stocker les unformations concernant un joueur dans le fichier de sauvegarde.
	 */
	private Player p1, p2;
	
	/**
	 * Temps de reflexion de l'IA en ms.
	 */
	private int AIThinkingTime;
	
	/**
	 * Difficulté de l'IA. 
	 */
	private int AILevel;

	/**
	 * Nom du fichier de sortie (fichier de sauvegarde de la partie).
	 */
	private String boardFileName;

	/**
	 * Permet de réccupérer les entrées utilisateur.
	 */
	private Scanner sc;
	
	/**
	 * Constructeur de la structure. <br/> Il demande à l'utilisateur de renseigner les différentes informations nécessaires à la génération de l'Othellier.
	 */
	public Board (){
		
		this.sc = new Scanner(System.in);
		
		this.nbPieceX = initializeBoardSize(PostsPublisher.LENGTH_CAPITAL_FR);
		this.nbPieceY = initializeBoardSize(PostsPublisher.WIDTH_CAPIAL_FR);
		
		this.gameBoard = new int[this.nbPieceX][this.nbPieceY];
		
		this.p1 = new Player(PostsPublisher.FIRST_PLAYER_NAME_POST, PostsPublisher.WHITE, PostsPublisher.HUMAN, 1);
		this.p2 = new Player(PostsPublisher.SECOND_PLAYER_NAME_POST, PostsPublisher.BLACK, PostsPublisher.MACHINE, 2);

		System.out.println(PostsPublisher.INITIALIZATION_POST_FR);
		
		if (Utils.getIntUserChoice(PostsPublisher.LOAD_OR_CREATE_BOARD_REQUEST_FR, null , ONE, BOUND_TWO, this.sc) == 1)
			loadBoard();
		else
			initialiseBoard();

		System.out.println(this.toString());
		
		this.boardFileName = initializeBoardFileName(PostsPublisher.SAVE_FILE_NAME_REQUEST_FR);
		
		this.AILevel = DEFAULT_AI_LEVEL;
		
		this.AIThinkingTime = DEFAULT_AI_THINKING_TIME;
		
		System.out.println(PostsPublisher.END_POST_FR);
	}

	/**
	 * Accesseur (lecture) sur la taille de l'Othellier (Axe des abscisses).
	 * @return int : la taille de l'othellier suivant l'axe des abscisses.
	 */
	public int getNbPieceX (){
		return this.nbPieceX;
	}
	
	/**
	 * Accesseur (lecture) sur la taille de l'Othellier (Axe des ordonnées).
	 * @return int : la taille de l'othellier suivant l'axe des ordonnées.
	 */
	public int getNbPieceY(){
		return this.nbPieceY;
	}
	
	/**
	 * Accesseur (lecture) permettant de réccupérer la grille de jeu une fois remplie.
	 * @return int[][] : Grille de jeu.
	 */
	public int[][] getGameBoard(){
		return this.gameBoard;
	}
	
	/**
	 * Accesseur (lecture) sur le joueur 1.
	 * @return Player : joueur 1.
	 */
	public Player getPlayer1(){
		return this.p1;
	}
	
	/**
	 * Accesseur (lecture) sur le joueur 2.
	 * @return Player : Joueur 2. 
	 */
	public Player getPlayer2(){
		return this.p2;
	}
	
	/**
	 * Accesseur (lecture) sur le nom de ficheir de sauvegarde.
	 * @return String : le nom du ficheier de sauvegarde.
	 */
	public String getBoardFileName(){
		return this.boardFileName;
	}
	
	/**
	 * Accesseur (lecture) sur le temps de reflexion de l'IA.
	 * @return int : temps de réflexion de l'IA en ms.
	 */
	public int getAIThinkingTime(){
		return this.AIThinkingTime;
	}
	
	/**
	 * Accesseur (lecture) sur la difficulté de l'IA.
	 * @return int : difficulté de l'IA (0 = simple, 1 = moyen, 2 = difficile).
	 */
	public int getAILevel(){
		return this.AILevel;
	}

	/**
	 * Methode demandant à l'utilisateur de renseigner les dimensions de l'Othellier.
	 * @param widthOrLength : String permettant de générer une phrase avec soit largeur, soir longueur. 
	 * @return int : La taille de plateau saisie par l'utilisateur.
	 */
	private int initializeBoardSize(String widthOrLength){
		return Utils.getIntUserChoice (PostsPublisher.INITIALIZE_BOARD_POST_1_FR +
				widthOrLength + PostsPublisher.INITIALIZE_BOARD_POST_2_FR,
				PostsPublisher.INITIALIZE_BOARD_POST_1_FR + widthOrLength +
				PostsPublisher.INITIALIZE_BOARD_POST_2_FR, BOUND_FOUR, BOUND_FIFTY, this.sc);
	}
	
	/**
	 * Methode permettant de créer un plateau de jeu par defaut.
	 * @param sizeX : int, taille du plateau suivant l'axe des abscisses.
	 * @param sizeY : int, taille du plateau suivant l'axe des ordonnées.
	 * @return int[][] : un plateau initial correct.
	 */
	private int[][] getInitialBoard(int sizeX, int sizeY) {	
		int[][] b = new int[sizeX][sizeY];
		
		b[(sizeX/2)-1][(sizeY/2)-1] = 1;		
		b[sizeX/2 -1][sizeY/2] = 2;
		b[sizeX/2][sizeY/2 -1] = 2;
		b[sizeX/2][sizeY/2] = 1;
		return b;
	}
	
	/**
	 * Méthode permettant de charger un plateau initial à partir d'un fichier existant.
	 */
	private void loadBoard(){
		String loadFile = initializeBoardFileName(PostsPublisher.LOAD_BOARD_FILE_NAME_REQUEST_FR);
		LoadBoardFile lbf = new LoadBoardFile(loadFile, this.nbPieceX, this.nbPieceY);
		try {
			lbf.getMapFromFile();
			this.gameBoard = lbf.getBoard();
		} catch (BPHandlerException e) {
			Log.error(e.getMessage());
			System.out.println(e.getMessage());
			this.gameBoard = getInitialBoard(this.nbPieceX, this.nbPieceY);
			//e.printStackTrace();
		}
		
	}
	
	/**
	 * Methode permettant de créer un plateau initial. 
	 */
	private void initialiseBoard(){
		int nbInitialPieces = ZERO;
		System.out.println(PostsPublisher.INITIALIZATION_RULES_POST_FR);
		
		while (nbInitialPieces < BOUND_FOUR){
			nbInitialPieces += putAPieceOnBoard();
		}
		
		while (putOtherPiece()){
			putAPieceOnBoard ();
		}
	}
		
	/**
	 * Methode permettant de positionner une pièce sur l'Othellier.
	 * @return int, indicateur permettant de savoir si l'utilisateur à bien positionné les quatre pièces nécessaires à une partie d'othello.
	 */
	private int putAPieceOnBoard (){
		
		int tmpX, tmpY, color;
		
		System.out.println(this.toString());
			
		color = Utils.getIntUserChoice (PostsPublisher.COLOR_QUESTION_1_FR, PostsPublisher.COLOR_QUESTION_2_FR, ONE, BOUND_TWO, this.sc );
		
		System.out.println(PostsPublisher.PIECE_POSITION_QUESTION_FR);
	
		tmpX = Utils.getIntUserChoice (PostsPublisher.PIECE_POSITION_LENGTH_HINT_FR + (this.nbPieceX - ONE) + PostsPublisher.COLON, null, ZERO, this.nbPieceX - ONE, this.sc );
		tmpY = Utils.getIntUserChoice (PostsPublisher.PIECE_POSITION_WIDTH_HINT_FR + (this.nbPieceY - ONE) + PostsPublisher.COLON, null, ZERO, this.nbPieceY - ONE, this.sc );
	
		if (this.gameBoard[tmpX][tmpY] != EMPTY_COLOR_VALUE){
			System.out.println(PostsPublisher.WARNING_PIECE_POSITION_POST_FR);
			return ZERO;
		}
		else{
			this.gameBoard[tmpX][tmpY] = color;
			return ONE;
		}
	}

	/**
	 * Methode demandant à l'utilisateur s'il veut poser une autre pièce sur l'Othellier.
	 * @return boolean : Le choix de l'utilisateur.
	 */
	private boolean putOtherPiece(){
		int tmp = Utils.getIntUserChoice (PostsPublisher.PUT_NEW_PIECE_POST_1_FR, PostsPublisher.PUT_NEW_PIECE_POST_2_FR, ZERO, ONE, this.sc);
		return (tmp == ONE)? true: false;
	}
	
	/**
	 * Methode demandant à l'utilisateur de saisir le nom du fichier de sauvegarde, le reformate en cas de besoin.
	 * @return String : Le nom du fichier de sauvegarde.
	 */
	private String initializeBoardFileName(String post) {
		String fileName;

		System.out.println(post);
		fileName = this.sc.next();
		
		fileName = fileName.replace('/', '-');
		fileName = fileName.replace('\\', '-');
		fileName = fileName.replace(':', '-');
		fileName = fileName.replace('*', '-');
		fileName = fileName.replace('?', '-');
		fileName = fileName.replace('"', '-');
		fileName = fileName.replace('<', '-');
		fileName = fileName.replace('>', '-');
		fileName = fileName.replace('|', '-');
		
		return fileName; 
	}
	
	/**
	 * Methode permettant d'afficher l'Othellier en cours de création.
	 * @return String : La chaîne de caractère à afficher pour l'utilisateur.
	 */
	public String toString(){
		String res = PostsPublisher.BOARD_SIZE_POST_FR + this.nbPieceX + PostsPublisher.MULT_SIGN + this.nbPieceY + PostsPublisher.EOF ;
		res += PostsPublisher.THREE_SPACES;
		for (int k = ZERO; k < this.nbPieceX; k++)
			res += (k<BOUND_TEN)? PostsPublisher.ONE_SPACES + k + PostsPublisher.ONE_SPACES : PostsPublisher.ONE_SPACES + k ;
		res += PostsPublisher.EOF;
		for (int i = ZERO; i < this.nbPieceY; i++){
			res += i + ((i<BOUND_TEN)? PostsPublisher.TWO_SPACES_PIPE :PostsPublisher.SPACES_PIPE);
			for (int j = ZERO; j < this.nbPieceX; j++){
				if (this.gameBoard[j][i] == ZERO)
					res += "  |";
				else if (this.gameBoard[j][i] == ONE)
					res += "()|"; 
				else
					res +="##|"; 
			}
			res += PostsPublisher.EOF;
		}
		res += PostsPublisher.FIRST_PLAYER_POST_FR + PostsPublisher.EOF;
		return res;
	}
}
