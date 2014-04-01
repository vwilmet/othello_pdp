package com.aistrategy.impl;

import java.awt.Point;
import java.util.Random;
import java.util.Set;

import com.aistrategy.ArtificialIntelligenceStrategy;
import com.board.Board;
import com.error_manager.Log;
import com.tree.NodeMove;
import com.tree.TreeMove;
import com.utils.WrongPlayablePositionException;

/**
 * Classe qui implémente les méthodes de la stratégie / Intelligence
 * Artificielle avec une approche "au hasard" (calcule des coups jouables et
 * renvoie d'un coup au hasard). </br>Elle implémente l'interface
 * {@link com.aistrategy.ArtificialIntelligenceStrategy}.
 * 
 * @author <ul>
 *         <li>Nicolas Yvon</li>
 *         </ul>
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
	 * Temps maximal pour l'execution de l'algorithme
	 */
	protected Integer maxTime;

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#nextMove}
	 */
	@Override
	public Point nextMove(Integer player) {
		Point[] arrayPoint = tree.getSentinel().getBoard()
				.calculatePlayablePosition(player).toArray(new Point[0]);
		if (arrayPoint.length > 0) {
			Random rand = new Random();
			int randomNum = rand.nextInt(arrayPoint.length);
			return arrayPoint[randomNum];
		} else
			return null;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#winStatus}
	 */
	@Override
	public Integer winStatus(Integer player) {
		if (this.tree.getSentinel().getBoard().getNbWhitePiece() == this.tree
				.getSentinel().getBoard().getNbBlackPiece())
			return 2;
		else if (player == 1
				&& this.tree.getSentinel().getBoard().getNbWhitePiece() > this.tree
						.getSentinel().getBoard().getNbBlackPiece())
			return 1;
		else if (player == 2
				&& this.tree.getSentinel().getBoard().getNbBlackPiece() > this.tree
						.getSentinel().getBoard().getNbWhitePiece())
			return 1;
		else
			return 0;

	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#initialize}
	 */
	@Override
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		initBoard = new Board(boardWidth, boardHeight, whitePiece, blackPiece);
		tree = new TreeMove<Point>();
		tree.setRootElement(new NodeMove<Point>(new Point(-1, -1), 1, initBoard));
		tree.setSentinel(tree.getRootElement());
		if (maxTime == null)
			this.maxTime = 0;
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy#notifyChosenMove}
	 */
	@Override
	public void notifyChosenMove(Point pos, Integer player)
			throws WrongPlayablePositionException {
		if (!this.tree.getSentinel().getBoard()
				.calculatePlayablePosition(player).contains(pos)) {
			WrongPlayablePositionException e = new WrongPlayablePositionException(
					pos);
			Log.error(e.getMessage());
			throw e;
		} else {
			if (findNodeFromMove(tree.getSentinel(), pos) == null) {
				NodeMove<Point> newSentinel = new NodeMove<Point>(pos,
						player % 2 + 1,
						new Board(tree.getSentinel().getBoard()),
						this.tree.getSentinel());
				newSentinel.calculateTurnResult();
				tree.getSentinel().addChild(newSentinel);
				tree.getSentinel().setBestMove(pos);
			}
			NodeMove<Point> newSentinel = findNodeFromMove(tree.getSentinel(),
					pos);
			tree.setSentinel(newSentinel);
		}
	}

	public NodeMove<Point> findNodeFromMove(NodeMove<Point> node, Point p) {
		NodeMove<Point> n = null;
		for (NodeMove<Point> child : node.getChildren()) {
			if (child.getLastMove().equals(p)) {
				n = child;
				break;
			}
		}
		return n;
	}
	
	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy#completeReflexion}
	 */
	@Override
	public Boolean completeReflexion() {
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#undoMove}
	 */
	@Override
	public void undoMove() {
		if (!this.tree.getSentinel().equals(this.tree.getRootElement()))
			this.tree.setSentinel(this.tree.getSentinel().getParent());
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#setMaxTime}
	 */
	@Override
	public void setMaxTime(Integer time) {
		this.maxTime = time;
	}

	@Override
	public Boolean initialize(RandomAI random) {
		initBoard = random.initBoard;
		tree = random.tree;
		this.maxTime = random.maxTime;
		return true;
	}

	@Override
	public Boolean initialize(NextBestMoveAI nextBestMove) {
		initBoard = nextBestMove.initBoard;
		tree = nextBestMove.tree;
		this.maxTime = nextBestMove.maxTime;
		return true;
	}

	@Override
	public Boolean initialize(BruteForceAI brute) {
		initBoard = brute.initBoard;
		tree = brute.tree;
		this.maxTime = brute.maxTime;
		return true;
	}

	public String boardToString() {
		return this.tree.getSentinel().printBoard();
	}

}