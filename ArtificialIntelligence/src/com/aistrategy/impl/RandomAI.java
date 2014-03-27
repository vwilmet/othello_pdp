package com.aistrategy.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import com.aistrategy.ArtificialIntelligenceStrategy;
import com.board.Board;
import com.error_manager.Log;
import com.tree.NodeMove;
import com.tree.TreeMove;
import com.utils.WrongPlayablePositionException;


/**
 * Classe qui implémente les méthodes de la stratégie / Intelligence Artificielle avec une approche "au hasard" (calcule des coups jouables et renvoie d'un coup au hasard).
 * </br>Elle implémente l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy}.
 * @author <ul><li>Nicolas Yvon</li></ul>
 * @version 1.0
 */
public class RandomAI implements ArtificialIntelligenceStrategy {

	/**
	 * Arbre de coup représentant l'ensemble d'une partie
	 */
	protected TreeMove<Point> tree;

	/**
	 * Plateau initial au début du lancement de l'IA
	 */
	protected Board initBoard;

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#nextMove}
	 */
	@Override
	public Point nextMove(Integer player) {
		Point[] arrayPoint = tree.getSentinel().getBoard().calculatePlayablePosition(player).toArray(new Point[0]);
		if(arrayPoint.length > 0){
			Random rand = new Random();
			int randomNum = rand.nextInt(arrayPoint.length);
			return arrayPoint[randomNum];
		}
		else
			return null;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#nextMoves}
	 */
	@Override
	public List<Point> nextMoves(Integer player) {
		List<Point> l = new ArrayList<Point>();
		l.add(nextMove(player));
		return l;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#winStatus}
	 */
	@Override
	public Integer winStatus(Integer player) {
		if(this.tree.getSentinel().getBoard().getNbWhitePiece() == this.tree.getSentinel().getBoard().getNbBlackPiece())
			return 2;
		else if(player == 1 && this.tree.getSentinel().getBoard().getNbWhitePiece() > this.tree.getSentinel().getBoard().getNbBlackPiece())
			return 1;
		else if(player == 2 && this.tree.getSentinel().getBoard().getNbBlackPiece() > this.tree.getSentinel().getBoard().getNbWhitePiece())
			return 1;
		else
			return 0;

	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#initialize}
	 */
	@Override
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		initBoard = new Board(boardWidth, boardHeight, whitePiece, blackPiece);
		tree = new TreeMove<Point>();
		tree.setRootElement(new NodeMove<Point>(new Point(-1,-1), 1,initBoard));
		tree.setSentinel(tree.getRootElement());
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#notifyChosenMove}
	 */
	@Override
	public void notifyChosenMove(Point pos, Integer player) throws WrongPlayablePositionException {
		if(!this.tree.getSentinel().getBoard().calculatePlayablePosition(player).contains(pos)){
			WrongPlayablePositionException e = new WrongPlayablePositionException(pos);
			Log.error(e.getMessage());
			throw e;
		}
		else{
			Board newBoard = new Board(tree.getSentinel().getBoard());
			newBoard.calculateTurnResult(pos, player);
			NodeMove<Point> myNode = new NodeMove<Point>(pos,player,newBoard,this.tree.getSentinel());
			tree.getSentinel().addChild(myNode);
			tree.setSentinel(myNode);
		}
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#completeReflexion}
	 */
	@Override
	public Boolean completeReflexion() {
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#undoMove}
	 */
	@Override
	public void undoMove() {
		if(!this.tree.getSentinel().equals(this.tree.getRootElement()))
			this.tree.setSentinel(this.tree.getSentinel().getParent());
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#setMaxTime}

	 */
	@Override
	public void setMaxTime(Integer time) {
		// Fonction inutile pour cette IA
	}

	@Override
	public Boolean initialize(RandomAI random) {
		initBoard = random.initBoard;
		tree = random.tree;
		return true;
	}

	@Override
	public Boolean initialize(NextBestMoveAI nextBestMove) {
		initBoard = nextBestMove.initBoard;
		tree = nextBestMove.tree;
		return true;
	}

	@Override
	public Boolean initialize(BruteForceAI brute) {
		initBoard = brute.initBoard;
		tree = brute.tree;
		return true;
	}
	
	public String boardToString(){
		return this.tree.getSentinel().printBoard();
	}



}