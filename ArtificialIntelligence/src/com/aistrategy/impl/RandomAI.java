package com.aistrategy.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.aistrategy.ArtificialIntelligenceStrategy;
import com.board.Board;
import com.error_manager.Log;
import com.tree.NodeMove;
import com.tree.TreeMove;
import com.utils.WrongPlayablePositionException;

public class RandomAI implements ArtificialIntelligenceStrategy {

	Board initBoard;
	TreeMove<Point> tree;
	Set<Point> whitePiece;
	Set<Point> blackPiece;
	Integer boardWidth;
	Integer boardHeight;
	
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
		if(this.whitePiece.size() == this.blackPiece.size())
			return 2;
		else if(player == 1 && this.whitePiece.size() > this.blackPiece.size())
			return 1;
		else if(player == 2 && this.blackPiece.size() > this.whitePiece.size())
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
		this.whitePiece = whitePiece;
		this.blackPiece = blackPiece;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
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
			Board newBoard = new Board(initBoard);
			newBoard.calculateTurnResult(pos, player);
			NodeMove<Point> myNode = new NodeMove<Point>(pos,player,newBoard);
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
		this.tree.setSentinel(this.tree.getSentinel().getParent());
	}


}