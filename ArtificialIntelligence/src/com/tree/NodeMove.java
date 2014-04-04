package com.tree;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.board.Board;

/** 
 * Représente un noeud de la classe TreeMove<T>.
 */

public class NodeMove<T> {

	/**
	 * La position du pion joué.
	 */
	private T lastMove;

	/**
	 * La position du meilleur prochain coup.
	 */
	private T bestMove;

	/**
	 * Le joueur qui jouera le prochain coup.
	 */
	private Integer player;

	/**
	 * Le noeud père du noeud actuel.
	 */
	private NodeMove<T> parent;

	/**
	 * La liste des fils du noeud actuel.
	 */
	private List<NodeMove<T>> children;
	private Board currentBoard;

	/**
	 * Constructeur de la classe.
	 */
	public NodeMove() {
		super();
	}


	/**
	 * Constructeur permettant de créer un noeud avec en paamètre le coup choisi, le joueur qui effectura le prochain coup et le plateau après que le coup "data" soit effectué
	 * @param data : T , le coup choisi par le joueur.
	 * @param player : Integer, le prochain joueur à jouer.
	 * @param board : Board, le plateau de jeu.
	 */
	public NodeMove(T data, Integer player, Board board){
		super();
		setLastMove(data);
		setPlayer(player);
		setBoard(board);      
		bestMove = null;
		parent = null;

	}

	/**
	 * Constructeur permettant de créer un noeud avec en paamètre le coup choisi, le joueur qui effectura le prochain coup, le plateau après que le coup "data" soit effectué, et le neoud père.
	 * @param data : T , le coup choisi par le joueur.
	 * @param player : Integer, le prochain joueur à jouer.
	 * @param board : Board, le plateau de jeu.
	 * @param parent : NodeMove<T> , le noeud père.
	 */
	public NodeMove(T data, Integer player, Board board, NodeMove<T> parent){
		this();
		setLastMove(data);
		setPlayer(player);
		setParent(parent);
		setBoard(board);      
		bestMove = null;
	}

	/**
	 * Retourne les fils du noeud NodeMove<T>. 
	 * </br>L'arbre est représenté par un simple noeud racine NodeMove<T> dont les enfants sont représentés par une list List<NodeMove<T>>. 
	 * </br>Chaquun de ces noeuds peuvent à leur tour avoir des enfants.
	 * </br>Cette méthode retournera la liste des enfants du noeud.
	 * @return les enfants du noeud NodeMove<T>
	 */
	public List<NodeMove<T>> getChildren() {
		if (this.children == null) {
			return new ArrayList<NodeMove<T>>();
		}
		return this.children;
	}

	/**
	 * Modifie l'ensemble des fils du noeud.
	 * </br>Voir la documentation pour la méthode getChildren() pour plus d'information.
	 * @param children : List<NodeMove<T>>, la nouvelle liste.
	 */
	public void setChildren(List<NodeMove<T>> children) {
		this.children = children;
	}

	/**
	 * Retourne le nombre de fils directs du ce NodeMove<T>.
	 * @return le nombre de fils directs.
	 */
	public int getNumberOfChildren() {
		if (children == null) {
			return 0;
		}
		return children.size();
	}

	/**
	 * Adds a child to the list of children for this Node<T>. The addition of
	 * the first child will create a new List<Node<T>>.
	 * @param child a Node<T> object to set.
	 */

	/**
	 * Ajoute un fils à la liste des fils pour ce NodeMove<T>.
	 * @param child : NodeMove<T>, le noeud fils à ajouter.
	 */
	public void addChild(NodeMove<T> child) {
		if (children == null) {
			children = new ArrayList<NodeMove<T>>();
		}
		children.add(child);
	}

	/**
	 * Supprime le NodeMove<T> à l'indexe index de la liste List<NodeMove<T>>.
	 * @param index : Integer, indexe de l'élément supprimé.
	 * @throws IndexOutOfBoundsException
	 */
	public void removeChildAt(int index) throws IndexOutOfBoundsException {
		children.remove(index);
	}

	/**
	 * Retourne le coup joué.
	 * @return le coup joué.
	 */
	public T getLastMove() {
		return this.lastMove;
	}

	/**
	 * Modifie le coup joué.
	 * @param data : T, le nouveau coup joué.
	 */
	public void setLastMove(T data) {
		this.lastMove = data;
	}

	/** 
	 * Retourne le joueur qui jouera le prochain coup.
	 * @return le joueur.
	 */
	public Integer getPlayer() {
		return player;
	}

	/**
	 * Modifie le joueur qui jouera le prochain coup.
	 * @param player : Integer, le joueur.
	 */
	public void setPlayer(Integer player) {
		this.player = player;
	}

	/**
	 * Retourne le noeud père de ce NodeMove<T>.
	 * @return le noeud père.
	 */
	public NodeMove<T> getParent() {
		return parent;
	}

	/**
	 * Modifie le noeud père.
	 * @param parent : NodeMove<T>, le nouveau noeud père.
	 */
	public void setParent(NodeMove<T> parent) {
		this.parent = parent;
	}

	/**
	 * Retourne le plateau.
	 * @return le plateau.
	 */
	public Board getBoard() {
		return currentBoard;
	}

	/**
	 * Modifie le plateau.
	 * @param board : Board, le plateau.
	 */
	public void setBoard(Board board) {
		this.currentBoard = board;
	}


	/**
	 * Retourne le prochain meilleur coup à jouer.
	 * @return le meilleur coup à jouer.
	 */
	public T getBestMove() {
		return bestMove;
	}

	/**
	 * Modifie le meilleur coup à joué.
	 * @param bestMove : T, le meilleur coup à joué.
	 */
	public void setBestMove(T bestMove) {
		this.bestMove = bestMove;
	}

	/**
	 * Calcule des coups jouables.
	 * @return une pile des coups jouables.
	 */
	public Stack<Point> calculatePlayablePosition(){
		return this.currentBoard.calculatePlayablePosition(this.player);
	}

	/**
	 * Calcule et modification du plateau après que le coup LastMove ait été effectué.
	 */
	public void calculateTurnResult(){
		if(this.lastMove != null && this.player != null)
			this.currentBoard.calculateTurnResult((Point)this.lastMove, this.player%2+1);
	}

	/**
	 * Retourne un affichage du plateau dans une chaîne de caractère.
	 * @return une chaîne de caractère représentant le plateau.
	 */
	public String printBoard(){
		return this.currentBoard.printBoard();

	}

	/**
	 * Retourne une représentation de l'objet.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append(getLastMove().toString()).append(",[");
		int i = 0;
		for (NodeMove<T> e : getChildren()) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(e.getLastMove().toString());
			sb.append("White : " +e.getBoard().getNbWhitePiece() + " , ");
			sb.append("Black : " +e.getBoard().getNbBlackPiece() + " ");
			sb.append("Board : \n" + e.printBoard());
			i++;
		}
		sb.append("]").append("}");
		return sb.toString();
	}
}