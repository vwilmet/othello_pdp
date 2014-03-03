package com.publisher;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.error_manager.Log;
/**
 * Classe permettant de reccupérer les différentes informations sur les caractéristiques de l'Othellier à créer auprès de l'utilisateur. 
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Board {
	
	/**
	 * Attributs indiquant la taille de l'Othellier
	 */
	private int nbPieceX, nbPieceY;

	/**
	 * Attribut indiquant qui est le premier joueur.
	 * <br/> 1 pour le joueur blanc, 2 pour le joueur noir. 
	 */
	private int firstPlayer;

	/**
	 * Attribut permettant de stocker le plateau de jeu.
	 */
	private int[][] gameBoard;
	
	private String boardFileName;

	/**
	 * Attribut permettant de réccupérer les entrées utilisateur.
	 */
	private Scanner sc;
	
	/**
	 * Constructeur de la structure. <br/> Il demande à l'utilisateur de renseigner les différentes informations nécessaires à la génération de l'Othellier.
	 */
	public Board (){
		int nbInitialPieces = 0;
		
		this.sc = new Scanner(System.in);
		
		this.nbPieceX = initializeBoardSize(PostsPublisher.LENGTH_CAPITAL_FR);
		this.nbPieceY = initializeBoardSize(PostsPublisher.WIDTH_CAPIAL_FR);

		this.firstPlayer = 1;

		this.gameBoard = new int[this.nbPieceX][this.nbPieceY];

		System.out.println(PostsPublisher.INITIALIZATION_POST_FR);
		System.out.println(PostsPublisher.INITIALIZATION_RULES_POST_FR);
		
		while (nbInitialPieces < 4){
			nbInitialPieces += putAPieceOnBoard();
		}
		
		while (putOtherPiece()){
			putAPieceOnBoard ();
		}

		System.out.println(this.toString());
		
		this.boardFileName = initializeBoardFileName();
		
		
		System.out.println(PostsPublisher.END_POST_FR);
	}

	/**
	 * Accesseur en lecture sur la taille de l'Othellier (Axe des abscisses).
	 * @return int : la taille de l'othellier suivant l'axe des abscisses.
	 */
	public int getNbPieceX (){
		return this.nbPieceX;
	}
	
	/**
	 * Accesseur en lecture sur la taille de l'Othellier (Axe des ordonnées).
	 * @return int : la taille de l'othellier suivant l'axe des ordonnées.
	 */
	public int getNbPieceY(){
		return this.nbPieceY;
	}
	
	/**
	 * Accesseur en lecture sur le premier joueur à poser un pion sur l'Othellier.
	 * @return int : Entier correspondant à un joueur. 1 pour le joueur blanc, 2 pour le noir.
	 */
	public int getFirstPlayer(){
		return this.firstPlayer;
	}
	
	/**
	 * Accesseur en lecture permettant de réccupérer la grille de jeu une fois remplie.
	 * @return int[][] : Grille de jeu.
	 */
	public int[][] getGameBoard(){
		return this.gameBoard;
	}
	
	public String getBoardFileName(){
		return this.boardFileName;
	}
	
	/**
	 * Méthode interne à la classe permettant de réccupérer les entrées utilisateur et de gérer les exceptions.
	 * @return int : Le résultat correct de l'utilisateur.
	 */
	private int getUserEntries(){
		int tmp = 999;
		try {
			tmp = this.sc.nextInt();
		} catch (InputMismatchException e){
			Log.error(PostsPublisher.ERROR_RECOVERY_RESULT_FR);
			this.sc.nextLine();
			tmp = 999;
		} catch (NoSuchElementException e){
			Log.error(PostsPublisher.INPUT_FATAL_ERROR_FR);
			e.printStackTrace();
		} catch (IllegalStateException e){
			Log.error(PostsPublisher.INPUT_FATAL_ERROR_FR);
			e.printStackTrace();
		}
		return tmp;
	}
	
	/**
	 * Methode demandant à l'utilisateur de renseigner les dimensions de l'Othellier.
	 * @param widthOrLength : String permettant de générer une phrase avec soit largeur, soir longueur. 
	 * @return int : La taille de plateau saisie par l'utilisateur.
	 */
	private int initializeBoardSize(String widthOrLength){
		return getUserChoice (PostsPublisher.INITIALIZE_BOARD_POST_1_FR + widthOrLength + PostsPublisher.INITIALIZE_BOARD_POST_2_FR, PostsPublisher.INITIALIZE_BOARD_POST_1_FR + widthOrLength + PostsPublisher.INITIALIZE_BOARD_POST_2_FR, 4, 50);
	}
	
	/**
	 * Méthode gérant le choix de l'utilisateur (autorise, ou n'autorise pas le choix de l'utilisateur si celui est incorrect).
	 * @param messageToPrint : String, message à afficher à l'utilisateur.
	 * @param optionalMessage : String, message optionnel à afficher à l'utilisateur.
	 * @param lowerBound : int, borne inférieure indiquant le choix minimal autorisé à l'utilisateur.
	 * @param upperBound : int, borne supérieure indiquant le choix maximal autorisé à l'utilisateur.
	 * @return int : Le choix de l'utilisateur. 
	 */
	private int getUserChoice (String messageToPrint, String optionalMessage, int lowerBound, int upperBound ){
		int tmp;
		System.out.println(messageToPrint);
		tmp = getUserEntries();
		
		while (tmp < lowerBound || tmp > upperBound){
			if (optionalMessage != null) 
				System.out.println(optionalMessage);
			tmp = getUserEntries();
		}
		return tmp;
	}
	
	/**
	 * Methode permettant de positionner une pièce sur l'Othellier.
	 * @return int, indicateur permettant de savoir si l'utilisateur à bien positionné les quatre pièces nécessaires à une partie d'othello.
	 */
	private int putAPieceOnBoard (){
		
		int tmpX, tmpY, color;
		
		System.out.println(this.toString());
			
		color = getUserChoice (PostsPublisher.COLOR_QUESTION_1_FR, PostsPublisher.COLOR_QUESTION_2_FR, 1, 2 );
		
		System.out.println(PostsPublisher.PIECE_POSITION_QUESTION_FR);
	
		tmpX = getUserChoice (PostsPublisher.PIECE_POSITION_LENGTH_HINT_FR + (this.nbPieceX - 1) + PostsPublisher.COLON_FR, null, 0, this.nbPieceX - 1 );
		tmpY = getUserChoice (PostsPublisher.PIECE_POSITION_WIDTH_HINT_FR + (this.nbPieceY - 1) + PostsPublisher.COLON_FR, null, 0, this.nbPieceY - 1 );
	
		if (this.gameBoard[tmpX][tmpY] != 0){
			System.out.println(PostsPublisher.WARNING_PIECE_POSITION_POST_FR);
			return 0;
		}
		else{
			this.gameBoard[tmpX][tmpY] = color;
			return 1;
		}
	}

	/**
	 * Methode demandant à l'utilisateur s'il veut poser une autre pièce sur l'Othellier.
	 * @return boolean : Le choix de l'utilisateur.
	 */
	private boolean putOtherPiece(){
		int tmp = getUserChoice (PostsPublisher.PUT_NEW_PIECE_POST_1_FR, PostsPublisher.PUT_NEW_PIECE_POST_2_FR, 0, 1);
		return (tmp == 1)? true: false;
	}
	
	private String initializeBoardFileName() {
		String fileName;

		System.out.println(PostsPublisher.SAVE_FILE_NAME_REQUEST_FR);
		fileName = this.sc.next();

		return fileName; 
	}
	
	/**
	 * Methode permettant d'afficher l'Othellier en cours de création.
	 * @return String : La chaîne de caractère à afficher pour l'utilisateur.
	 */
	public String toString(){
		String res = PostsPublisher.BOARD_SIZE_POST_FR + this.nbPieceX + "x" + this.nbPieceY + PostsPublisher.EOF_FR ;
		res += "   ";
		for (int k = 0; k < this.nbPieceX; k++)
			res += (k<10)? " " + k + " " :" " + k ;
		res += PostsPublisher.EOF_FR;
		for (int i = 0; i < this.nbPieceY; i++){
			res += i + ((i<10)?"  |":" |");
			for (int j = 0; j < this.nbPieceX; j++){
				if (this.gameBoard[j][i] == 0)
					res += "  |";
				else if (this.gameBoard[j][i] == 1)
					res += "()|"; 
				else
					res +="##|"; 
			}
			res += PostsPublisher.EOF_FR;
		}
		res += PostsPublisher.FIRST_PLAYER_POST_FR + PostsPublisher.EOF_FR;
		return res;
	}
}
