package com.board;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


/**
 * Classe qui représente un plateau de jeu.
 * @author <ul><li>Nicolas Yvon</li></ul>
 * @version 1.0
 */
public class Board {
	/**
	 * Grille de PiecEnum représentant le plateau de jeu 
	 */
	private PieceEnum[][] grid;

	/**
	 * Taille du plateau en largeur
	 */
	private Integer width;

	/**
	 * Taille du plateau en hauteur
	 */
	private Integer height;

	/**
	 * Ensemble des pions blanc
	 */
	private Set<Point> whitePiece;

	/** 
	 * Ensemble des pions noir
	 */
	private Set<Point> blackPiece;

	/**
	 * Frontière de case vide entre les pions joués et le reste des cases vides
	 */
	private Set<Point> borderLine;

	private Integer[][] positionalMatrix;

	/**
	 * Constructeur de la classe.
	 * @param boardWidth : int, taille de la grille en largeur.
	 * @param boardHeight : int, taille de la grille en hauteur.
	 * @param boardWhitePiece : Set<Piece>, ensemble des pions blanc.
	 * @param boardBlackPiece : Set<Piece>, ensemble des pions noir.
	 */
	public Board(Integer boardWidth, Integer boardHeight, Set<Point> boardWhitePiece, Set<Point> boardBlackPiece){
		this.width = boardWidth;
		this.height = boardHeight;
		borderLine = new HashSet<Point>();
		this.whitePiece = new HashSet<Point>(boardWhitePiece);
		this.blackPiece = new HashSet<Point>(boardBlackPiece);
		grid = new PieceEnum[this.width][this.height];
		for(int i =0; i< this.width; i++)
			for(int j =0; j< this.height; j++)
				grid[i][j] = PieceEnum.EMPTYPIECE;
		for(Point p : whitePiece)
			grid[p.x][p.y] = PieceEnum.WHITEPIECE;
		for(Point p : blackPiece)
			grid[p.x][p.y] = PieceEnum.BLACKPIECE;
		positionalMatrix = initializePositionalMatrix();
	}

	/**
	 * Constructeur par copie de la classe.
	 * @param otherBoard : Board, un autre plateau.
	 */
	public Board(Board otherBoard){
		this.width = otherBoard.width;
		this.height = otherBoard.height;
		borderLine = new HashSet<Point>(otherBoard.borderLine);
		this.whitePiece = new HashSet<Point>(otherBoard.whitePiece);
		this.blackPiece = new HashSet<Point>(otherBoard.blackPiece);
		grid = new PieceEnum[this.width][this.height];
		for(int i =0; i< this.width; i++)
			for(int j =0; j< this.height; j++)
				grid[i][j] = otherBoard.grid[i][j];
		positionalMatrix = initializePositionalMatrix();

	}


	/**
	 * Calcule la frontière définissant la zone entre les cases vides et les cases avec pion.
	 * @param player : Integer, le joueur jouant le coup.
	 * @return l'ensemble des points représentant la frontière
	 */
	public Set<Point> calculateBorderLine(Integer player){
		Set<Point> borderLine = new HashSet<Point>();
		if(player == 1){
			for(Point black : this.blackPiece){
				for(int x = - 1; x < 2; x++){
					for(int y = -1; y < 2; y++){
						if(black.x + x >= 0 && black.x + x < this.width && black.y + y >= 0 && black.y + y < this.height && !(x == 0 && y == 0)){
							if(this.isEmpty(x+black.x,y+black.y) && !borderLine.contains(new Point(x+black.x, y+black.y))){
								borderLine.add(new Point(x + black.x, y + black.y));
							}
						}

					}
				}

			}
		}
		else{
			for(Point white : whitePiece){
				for(int x = - 1; x < 2; x++){
					for(int y = -1; y < 2; y++){
						if(white.x + x >= 0 && white.x + x < this.width && white.y + y >= 0 && white.y + y < this.height && !(x == 0 && y == 0)){
							if(this.isEmpty(x + white.x,y+white.y)){
								borderLine.add(new Point(x + white.x, y + white.y));
							}
						}

					}
				}

			}
		}
		this.borderLine = borderLine;
		return this.borderLine;
	}

	/**
	 * Calcule les cases jouable par le joueur.
	 * @param player : Integer, le joueur jouant le coup.
	 * @return la pile de cases jouables.
	 */
	public Stack<Point> calculatePlayablePosition(Integer player){
		calculateBorderLine(player);
		Set<Point> nodeBorderline = this.borderLine;
		Stack<Point> possiblePosition = new Stack<Point>();
		for(Point p : nodeBorderline){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((p.x+x) < width && (p.x + x) >= 0 && (p.y + y) < height && (p.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(!isPlayer(p.x + x,p.y + y,player) && !isEmpty(p.x+x,p.y+y)){
							Integer i = p.x + x + x;
							Integer j = p.y + y + y;
							Boolean playable = false;
							while(i < width && i >= 0 && j < height && j >= 0 && playable == false){
								if(!isPlayer(i,j,player) && !isEmpty(i,j)){ // teste s'il y a un pion et si c'est un pion adverse
									i += x;
									j += y;
								}
								else if(isPlayer(i,j,player)) //teste le pion du joueur qui va "encadrer le coup" 
									playable = true;
								else if(isEmpty(i,j))
									break;
							}
							if(playable && !possiblePosition.contains(p)){
								possiblePosition.push(p);
							}
						}
					}
				}
			}
		}
		return possiblePosition;

	}

	/**
	 * Calcule le plateau résultant de la pose d'un pion.
	 * @param Position : Point, la position jouée.
	 * @param player : Integer, le joueur jouant le coup.
	 * @return la pile de cases jouables.
	 */
	public void calculateTurnResult(Point position, Integer player){
		Set<Point> tmp = new HashSet<Point>();
		Integer opponent = player%2 + 1; // player%2+1 donne le joueur adverse
		if(player == 1){
			whitePiece.add(position);
			putWhitePiece(position);
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < width && (position.x + x) >= 0 && (position.y + y) < height && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(isPlayer(position.x + x, position.y +y, opponent)){ 
							tmp.clear();
							tmp.add(new Point(position.x+x,position.y+y));
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							while(i < width && i >= 0 && j < height && j >= 0){
								if(isPlayer(i,j,opponent)){
									tmp.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (isPlayer(i,j,player)){
									whitePiece.addAll(tmp);
									for(Point p : tmp)
										turnPiece(p);
									break;
								}
								else if(isEmpty(i,j))
									break;
							}
						}
					}
				}
			}
			blackPiece.removeAll(whitePiece);
		}
		else if(player == 2){
			blackPiece.add(position);
			putBlackPiece(position);
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < width && (position.x + x) >= 0 && (position.y + y) < height && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(isPlayer(position.x+x, position.y+y, opponent)){
							tmp.clear();
							tmp.add(new Point(position.x+x,position.y+y));
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							Boolean stop = false;
							while(i < width && i >= 0 && j < height && j >= 0 && stop == false){
								if(isPlayer(i,j,opponent)){
									tmp.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (isPlayer(i,j,player)){
									blackPiece.addAll(tmp);
									for(Point p : tmp){
										turnPiece(p);
									}
									break;
								}
								else if(isEmpty(i,j))
									break;
							}

						}
					}
				}
			}
			whitePiece.removeAll(blackPiece); 
		}
	}

	public void recalculatePositionalMatrix(Integer player){
		if(this.positionalMatrix == null)
			this.positionalMatrix = initializePositionalMatrix();
		for(int i = 0; i<= this.getWidth()-3; i ++){
			if(isEmpty(i+1, 0) && isPlayer(i,0 , player) && isPlayer(i+2, 0, player))
				positionalMatrix[i+1][0] = 	Math.abs(positionalMatrix[i+1][0]);
			else if(isEmpty(i+1, 0))
				positionalMatrix[i+1][0] = -Math.abs(positionalMatrix[i+1][0]);
			if(isEmpty(i+1, this.getHeight() - 1) && isPlayer(i,this.getHeight() - 1, player) && isPlayer(i+2, this.getHeight() - 1, player))
				positionalMatrix[i+1][this.getHeight() - 1] = Math.abs(positionalMatrix[i+1][this.getHeight() - 1]);
			else if(isEmpty(i+1, this.getHeight() - 1))
				positionalMatrix[i+1][this.getHeight() - 1] = -Math.abs(positionalMatrix[i+1][this.getHeight() - 1]);
		}	
		for(int i = 0; i<= this.getHeight()-3; i ++){
			if(isEmpty(0, i+1) && isPlayer(0,i , player) && isPlayer(0, i+2, player)){
				System.out.println("Got there");	
				positionalMatrix[0][i+1] = Math.abs(positionalMatrix[0][i+1]);
			}
			else if(isEmpty(0, i+1))
				positionalMatrix[0][i+1] = -Math.abs(positionalMatrix[0][i+1]);
			if(isEmpty(this.getWidth() - 1,i+1) && isPlayer(this.getWidth() - 1,i, player) && isPlayer(this.getWidth() - 1,i+2, player))
				positionalMatrix[this.getWidth() - 1][i+1] = Math.abs(positionalMatrix[this.getWidth() - 1][i+1]);
			else if(isEmpty( this.getWidth() - 1, i+1))
				positionalMatrix[this.getWidth() - 1][i+1] = -Math.abs(positionalMatrix[this.getWidth() - 1][i+1]);
		}
		if(isEmpty(1, 1) && isPlayer(0,0 , player) && isPlayer(0, 1, player) && isPlayer(1, 0, player))
			positionalMatrix[1][1] = Math.abs(positionalMatrix[1][1]);
		if(isEmpty(this.width-2, this.height-2) && isPlayer(this.width-1,this.height-1 , player) && isPlayer(this.width-1, this.height-2, player) && isPlayer(this.width-2, this.height-1, player))
			positionalMatrix[this.width-2][this.height-2] = Math.abs(positionalMatrix[this.width-2][this.height-2]);
		if(isEmpty(1, this.height-2) && isPlayer(0,this.height-1 , player) && isPlayer(0, this.height-2, player) && isPlayer(1, this.height-1, player))
			positionalMatrix[1][this.height-2] = Math.abs(positionalMatrix[1][this.height-2]);
		if(isEmpty(this.width-2, 1) && isPlayer(this.width-1,0 , player) && isPlayer(this.width-1, 1, player) && isPlayer(this.width-2, 0, player))
			positionalMatrix[this.width-2][1] = Math.abs(positionalMatrix[this.width-2][1]);

	}
	
	private Integer[][] initializePositionalMatrix() {
		Integer[][] matrix = positionalMatrix;
		if(matrix == null){
			matrix = new Integer[this.getWidth()][this.getHeight()];
		}
		for(int i = 0; i < this.getWidth(); i++)
			for(int j = 0; j < this.getHeight(); j++){
				matrix[i][j] = 1;
			}
		
		Integer corner = 30;
		Integer xCase = -25;
		Integer cCase = -20;
		Integer aCase = 10;
		Integer bCase = 5;
		Integer dCase = 2;

		matrix[3][2] = dCase;
		matrix[2][3] = dCase;
		matrix[this.getWidth()-4][2] = dCase;
		matrix[this.getWidth()-3][3] = dCase;
		matrix[3][this.getHeight()-3] = dCase;
		matrix[2][this.getHeight()-4] = dCase;
		matrix[this.getWidth()-4][this.getHeight()-3] = dCase;
		matrix[this.getWidth()-3][this.getHeight()-4] = dCase;

		matrix[2][2] = bCase;
		matrix[this.getWidth()-3][2] = bCase;
		matrix[this.getWidth()-3][this.getHeight()-3] = bCase;
		matrix[2][this.getHeight()-3] = bCase;

		matrix[3][0] = bCase;
		matrix[0][3] = bCase;
		matrix[this.getWidth()-4][0] = bCase;
		matrix[0][this.getHeight()-4] = bCase;
		matrix[3][this.getHeight()-1] = bCase;
		matrix[this.getWidth()-1][3] = bCase;
		matrix[this.getWidth()-4][this.getHeight()-1] = bCase;
		matrix[this.getWidth()-1][this.getHeight()-4] = bCase;

		matrix[2][0] = aCase;
		matrix[0][2] = aCase;
		matrix[this.getWidth()-3][0] = aCase;
		matrix[0][this.getHeight()-3] = aCase;
		matrix[2][this.getHeight()-1] = aCase;
		matrix[this.getWidth()-1][2] = aCase;
		matrix[this.getWidth()-3][this.getHeight()-1] = aCase;
		matrix[this.getWidth()-1][this.getHeight()-3] = aCase;

		matrix[1][0] = cCase;
		matrix[0][1] = cCase;
		matrix[this.getWidth()-2][0] = cCase;
		matrix[0][this.getHeight()-2] = cCase;
		matrix[1][this.getHeight()-1] = cCase;
		matrix[this.getWidth()-1][1] = cCase;
		matrix[this.getWidth()-2][this.getHeight()-1] = cCase;
		matrix[this.getWidth()-1][this.getHeight()-2] = cCase;

		matrix[1][1] = xCase;
		matrix[this.getWidth()-2][this.getHeight()-2] = xCase;
		matrix[this.getWidth()-2][1] = xCase;
		matrix[1][this.getHeight()-2] = xCase;

		matrix[0][0] = corner;
		matrix[this.getWidth()-1][this.getHeight()-1] = corner;
		matrix[this.getWidth()-1][0] = corner;
		matrix[0][this.getHeight()-1] = corner;

		positionalMatrix = matrix;
		
		return matrix;
	}


	/**
	 * Vérifie si la case est vide.
	 * @param x : Integer, la position en abscisse de la case.
	 * @param y : Integer, la position en ordonnée de la case.
	 * @return un booléen, true si la case est vide, false sinon..
	 */
	public boolean isEmpty(Integer x, Integer y){
		return isEmpty(new Point(x,y));
	}

	/**
	 * Vérifie si la case est rempli par le joueur passé en paramètre.
	 * @param x : Integer, la position en abscisse de la case.
	 * @param y : Integer, la position en ordonnée de la case.
	 * @param player : Integer, le joueur.
	 * @return un booléen, true si la case est vide, false sinon..
	 */
	public boolean isPlayer(Integer x, Integer y, Integer player){
		return isPlayer(new Point(x,y), player);
	}

	/**
	 * Vérifie si la case est vide.
	 * @param p : Point, la position de la case sur le plateau.
	 * @return un booléen, true si la case est vide, false sinon..
	 */
	public boolean isEmpty(Point p){
		return grid[p.x][p.y].getValue() == 0? true :false;
	}

	/**
	 * Vérifie si la case est rempli par le joueur passé en paramètre.
	 * @param p : Point, la position de la case sur le plateau.
	 * @param player : Integer, le joueur.
	 * @return un booléen, true si la case est vide, false sinon..
	 */
	public boolean isPlayer(Point p, Integer player){
		return grid[p.x][p.y].getValue() == player? true :false;
	}

	/**
	 * Ajoute un pion noir à l'emplacement donné
	 * @param x : Integer, la position en abscisse.
	 * @param y : Integer, la position en ordonnée.
	 */
	public void putBlackPiece(Integer x, Integer y){
		if(grid[x][y] == PieceEnum.EMPTYPIECE)
			grid[x][y] = PieceEnum.BLACKPIECE;
	}

	/**
	 * Ajoute un pion blanc à l'emplacement donné
	 * @param x : Integer, la position en abscisse.
	 * @param y : Integer, la position en ordonnée.
	 */
	public void putWhitePiece(Integer x, Integer y){
		if(grid[x][y] == PieceEnum.EMPTYPIECE)
			grid[x][y] = PieceEnum.WHITEPIECE;
	}

	/**
	 * Ajoute un pion noir à l'emplacement donné
	 * @param p : Point, la position de la case sur le plateau.
	 */
	public void putBlackPiece(Point p){
		putBlackPiece(p.x,p.y);
	}

	/**
	 * Ajoute un pion blanc à l'emplacement donné
	 * @param p : Point, la position de la case sur le plateau.
	 */
	public void putWhitePiece(Point p){
		putWhitePiece(p.x,p.y);
	}

	/**
	 * Retourne la pièce sur la case donnée en paramètre
	 * @param i : Integer, la position de la case en abscisse.
	 * @param j : Integer, la position de la case en ordonnée.
	 */
	public void turnPiece(Integer i, Integer j){
		switch (grid[i][j]){
		case WHITEPIECE :
			grid[i][j] = PieceEnum.BLACKPIECE;
			break;
		case BLACKPIECE:
			grid[i][j] = PieceEnum.WHITEPIECE;
			break;
		default:
			break;
		}
	}

	/**
	 * Retourne la pièce sur la case donnée en paramètre
	 * @param p : Point, la position de la case.
	 */
	public void turnPiece(Point p){
		turnPiece(p.x,p.y);
	}

	/**
	 * Retourne l'ensemble des pions blancs du plateau
	 * @return l'ensemble des pions blancs
	 */
	public Set<Point> getWhitePiece() {
		return whitePiece;
	}

	/**
	 * Retourne l'ensemble des pions noirs du plateau
	 * @return l'ensemble des pions noirs
	 */
	public Set<Point> getBlackPiece() {
		return blackPiece;
	}

	/**
	 * Retourne le nombre de pions blancs.
	 * @return le nombre de pions blancs.
	 */
	public Integer getNbWhitePiece(){
		return whitePiece.size();
	}

	/**
	 * Retourne le nombre de pions noirs.
	 * @return le nombre de pions noirs.
	 */
	public Integer getNbBlackPiece(){
		return blackPiece.size();
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}


	public Integer[][] getPositionalMatrix(Integer player) {
		this.recalculatePositionalMatrix(player);
		return positionalMatrix;
	}

	/**
	 * Méthode permettant de créer une chaîne de  caractère représentant le plateau.
	 * @return la chaîne de caractère représentant le plateau.
	 */
	public String printBoard(){
		String res = new String("");
		for(int i = 0; i < this.width; i++)
			res +=  " _";
		for(int j = 0; j < this.height; j++){
			res += "\n|";
			for(int i = 0; i < this.width; i++){
				if(isPlayer(i,j,1))
					res += "1|";
				else if(isPlayer(i,j,2))
					res += "2|";
				else
					res += " |";
			}
			res += "\n";
			for(int i = 0; i < this.width; i++)
				res += " _";
		}

		return res;	
	}
}
