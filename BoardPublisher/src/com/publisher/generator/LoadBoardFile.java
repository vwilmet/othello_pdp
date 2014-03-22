package com.publisher.generator;

import com.manager.FilesManager;
import com.manager.FilesManagerImpl;
import com.publisher.Board;
import com.publisher.utils.BPHandlerException;
import com.publisher.utils.PostsPublisher;

/**
 * Classe permettant de charger un fichier contenant un grille de jeu.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class LoadBoardFile {
	
	/**
	 * String : nom du fichier à charger.
	 */
	private String fileName;

	/**
	 * Taille de l'otholier à charger dans le fichier contenant la grille initiale.
	 */
	private int nbPieceX, nbPieceY;
	
	/**
	 * Grille de jeu.
	 */
	private int [][] board;
	
	/**
	 * Constructeur de classe.
	 * @param fileName : String, nom du fichier à charger.
	 * @param nbCol : int, nombre de colonnes de l'othelier.
	 * @param nbLine : int, nombre de lignes de l'othelier.
	 */
	public LoadBoardFile(String fileName, int nbCol, int nbLine){
		this.fileName = fileName;
		this.nbPieceX = nbLine;
		this.nbPieceY = nbCol;
		this.board = new int[this.nbPieceY][this.nbPieceX];
	}
	
	/**
	 * Méthode permettant de charger une grille de jeu à partir d'un fichier.
	 */
	public void getMapFromFile() throws BPHandlerException {
		FilesManager fm = new FilesManagerImpl();
		String s = fm.load(fileName + ".xml", "./");
		
		if (s.equals(FilesManager.ERROR_ON_LOAD_FILE_NOT_EXISTING)){
			throw new BPHandlerException(BPHandlerException.ERROR_DURING_LOAD_BOARD_FILE, PostsPublisher.ERROR_FILE_NOT_EXISTING_FR);
		}
		else if (s.equals(FilesManager.ERROR_ON_LOAD_ON_READING)){
			throw new BPHandlerException(BPHandlerException.ERROR_DURING_LOAD_BOARD_FILE);
		}
		int [][] tmp = new int [this.nbPieceX][this.nbPieceY];
		String [] str = s.split("\n");		

		if (str.length != this.nbPieceX)
			throw new BPHandlerException(BPHandlerException.ERROR_DURING_LOAD_BOARD_FILE, PostsPublisher.WRONG_BOARD_SIZE_FR);
		
		for (int i = 0; i < this.nbPieceX; i++){
			if (str[i].length() != nbPieceY)
				throw new BPHandlerException(BPHandlerException.ERROR_DURING_LOAD_BOARD_FILE, PostsPublisher.WRONG_BOARD_SIZE_FR);
			
			for (int j = 0; j < this.nbPieceY; j++){	
				if (str[i].charAt(j) == '-')
					tmp[i][j] = Board.EMPTY_COLOR_VALUE;
				else if (str[i].charAt(j) == 'o')
					tmp[i][j] = Board.WHITE_COLOR_VALUE;
				else if (str[i].charAt(j) == '*')
					tmp[i][j] = Board.BLACK_COLOR_VALUE;
				else 
					throw new BPHandlerException(BPHandlerException.ERROR_DURING_LOAD_BOARD_FILE, PostsPublisher.WRONG_FILE_FORMAT_FR);
			}
		}
			
		for (int l = 0; l < this.nbPieceY; l++){
			for (int m = 0; m < this.nbPieceX; m++){
				this.board[l][m] = tmp[m][l];
			}
		}
	}
	
	/**
	 * Accesseur (lecture) du plateau de jeu crée.
	 * @return int[][] : la grille de jeu initial.
	 */
	public int [][] getBoard(){
		return this.board;
	}
	
	/**
	 * Methode d'affichage de la grille de jeu chargée à partir d'un fichier.
	 */
	public String toString(){
		String res = "";
		res += PostsPublisher.THREE_SPACES;
		for (int k = 0; k < this.nbPieceY; k++)
			res += (k<10)? PostsPublisher.ONE_SPACES + k + PostsPublisher.ONE_SPACES : PostsPublisher.ONE_SPACES + k ;
		res += PostsPublisher.EOF;
		for (int i = 0; i < this.nbPieceX; i++){
			res += i + ((i<10)? PostsPublisher.TWO_SPACES_PIPE :PostsPublisher.SPACES_PIPE);
			for (int j = 0; j < this.nbPieceY; j++){
				if (this.board[j][i] == 0)
					res += "  |";
				else if (this.board[j][i] == 1)
					res += "()|"; 
				else if (this.board[j][i] == 2)
					res +="##|"; 
			}
			res += PostsPublisher.EOF;
		}
		return res;
	}
}
