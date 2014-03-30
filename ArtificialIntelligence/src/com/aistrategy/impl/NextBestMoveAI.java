package com.aistrategy.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.aistrategy.ArtificialIntelligenceStrategy;
import com.board.Board;
import com.error_manager.Log;
import com.tree.NodeMove;
import com.tree.TreeMove;
import com.utils.WrongPlayablePositionException;

/**
 * Classe qui implémente les méthodes de la stratégie / Intelligence
 * Artificielle avec une approche "prochain meilleur" (calcule du meilleur
 * prochain coup jouable). Elle implémente l'interface
 * {@link com.aistrategy.ArtificialIntelligenceStrategy}.
 * 
 * @author <ul>
 *         <li>Nicolas Yvon</li>
 *         </ul>
 * @version 1.0
 */
public class NextBestMoveAI implements ArtificialIntelligenceStrategy {

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
		return nextBestLessPlayableMove(player);
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#nextMoves}
	 */
	@Override
	public List<Point> nextMoves(Integer player) {
		List<Point> l = new ArrayList<Point>();
		l.add(nextMove(player));
		return l;
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
		if (tree.getSentinel().getBoard().getNbWhitePiece() == tree
				.getSentinel().getBoard().getNbBlackPiece())
			return 2;
		else if (player == 1
				&& tree.getSentinel().getBoard().getNbWhitePiece() > tree
						.getSentinel().getBoard().getNbBlackPiece())
			return 1;
		else if (player == 2
				&& tree.getSentinel().getBoard().getNbWhitePiece() < tree
						.getSentinel().getBoard().getNbBlackPiece())
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
			tree.setSentinel(findNodeFromMove(tree.getSentinel(), pos));
		}
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

	public String boardToString() {
		return this.tree.getSentinel().printBoard();
	}

	private Point nextBestScore(Integer player) {
		Stack<Point> stackPoint = tree.getSentinel().getBoard()
				.calculatePlayablePosition(player);
		Integer score;
		if (player == 1)
			score = Integer.MIN_VALUE;
		else
			score = Integer.MAX_VALUE;
		Integer opponent = player % 2 + 1;
		while (!stackPoint.isEmpty()) {
			Point p = stackPoint.pop();
			NodeMove<Point> node = new NodeMove<Point>(p, opponent, new Board(
					tree.getSentinel().getBoard()), tree.getSentinel());
			node.calculateTurnResult();
			tree.getSentinel().addChild(node);
			if (node.getBoard().getNbWhitePiece() == score) {
				if ((p.x == 0 && p.y == 0)
						|| (p.x == 0 && p.y == (tree.getSentinel().getBoard()
								.getHeight() - 1))
						|| (p.x == (tree.getSentinel().getBoard().getWidth() - 1) && p.y == 0)
						|| (p.x == (tree.getSentinel().getBoard().getWidth() - 1) && p.y == (tree
								.getSentinel().getBoard().getHeight() - 1))) {
					score = node.getBoard().getNbWhitePiece();
					tree.getSentinel().setBestMove(p);
				}
			} else if (node.getBoard().getNbWhitePiece() > score && player == 1) {
				score = node.getBoard().getNbWhitePiece();
				tree.getSentinel().setBestMove(p);
			} else if (node.getBoard().getNbWhitePiece() < score && player == 2) {
				score = node.getBoard().getNbWhitePiece();
				tree.getSentinel().setBestMove(p);
			}

		}
		return tree.getSentinel().getBestMove();
	}

	private Point nextBestLessPlayableMove(Integer player) {
		Stack<Point> stackPoint = tree.getSentinel().getBoard()
				.calculatePlayablePosition(player);
		Integer score = Integer.MAX_VALUE;
		Integer opponent = player % 2 + 1;
		while (!stackPoint.isEmpty()) {
			Point p = stackPoint.pop();
			NodeMove<Point> node = new NodeMove<Point>(p, opponent, new Board(
					tree.getSentinel().getBoard()), tree.getSentinel());
			node.calculateTurnResult();
			tree.getSentinel().addChild(node);
			if (node.getBoard().calculatePlayablePosition(player).size() == score) {
				if ((p.x == 0 && p.y == 0)
						|| (p.x == 0 && p.y == (tree.getSentinel().getBoard()
								.getHeight() - 1))
						|| (p.x == (tree.getSentinel().getBoard().getWidth() - 1) && p.y == 0)
						|| (p.x == (tree.getSentinel().getBoard().getWidth() - 1) && p.y == (tree
								.getSentinel().getBoard().getHeight() - 1))) {
					score = node.getBoard().getNbWhitePiece();
					tree.getSentinel().setBestMove(p);
				}
			} else if (node.getBoard().calculatePlayablePosition(player).size() < score) {
				score = node.getBoard().getNbWhitePiece();
				tree.getSentinel().setBestMove(p);
			}

		}
		return tree.getSentinel().getBestMove();
	}

}
