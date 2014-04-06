package com.aistrategy.impl;

import java.awt.Point;
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
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#nextMove}
	 */
	@Override
	public Point nextMove(Integer player) {
		Integer boardSize = this.initBoard.getHeight()
				* this.initBoard.getWidth();
		Point nextMove;
		if (((Integer) (boardSize / 3))*1 > (boardSize - (this.tree.getSentinel()
				.getBoard().getNbBlackPiece() + this.tree.getSentinel()
				.getBoard().getNbWhitePiece())))
			nextMove = nextBestMovePositionMobility(player) ;
		else
			nextMove = nextBestScore(player);
		return nextMove;
	}
	
	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#quickNextMove}
	 */
	@Override
	public Point quickNextMove(Integer player) {
		return nextMove(player);
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
			if (this.tree.findNodeFromMove(tree.getSentinel(), pos) == null) {
				NodeMove<Point> newSentinel = new NodeMove<Point>(pos,
						player % 2 + 1,
						new Board(tree.getSentinel().getBoard()),
						this.tree.getSentinel());
				newSentinel.calculateTurnResult();
				tree.getSentinel().addChild(newSentinel);
				tree.getSentinel().setBestMove(pos);
			}
			tree.setSentinel(this.tree.findNodeFromMove(tree.getSentinel(), pos));
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
		this.tree = null;
		this.initBoard = null;
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
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#initialize}
	 */
	@Override
	public Boolean initialize(RandomAI random) {
		initBoard = random.initBoard;
		tree = random.tree;
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#initialize}
	 */
	@Override	public Boolean initialize(NextBestMoveAI nextBestMove) {
		initBoard = nextBestMove.initBoard;
		tree = nextBestMove.tree;
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#initialize}
	 */
	@Override	public Boolean initialize(BruteForceAI brute) {
		initBoard = brute.initBoard;
		tree = brute.tree;
		return true;
	}

	/**
	 * Fonction permettant de calculer le meilleur prochain coup en terme de score de capture de pion.
	 * @param player est le joueur demandant le pion
	 * @return la position du meilleur pion à jouer
	 */
	private Point nextBestScore(Integer player) {
		Stack<Point> stackPoint = tree.getSentinel().getBoard()
				.calculatePlayablePosition(player);
		Integer score;
		Integer[][] positionalMatrix = this.tree.getSentinel().getBoard().getPositionalMatrix(player);
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
			Point best = tree.getSentinel().getBestMove();
			if(best == null)
				best = p;
			if (node.getBoard().getNbWhitePiece() == score) {
				if (positionalMatrix[best.x][best.y] < positionalMatrix[p.x][p.y]) {
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
	
	/**
	 * Fonction permettant de calculer le meilleur prochain coup en terme de score de mobilité
	 * </br>(nombre de possibilité de coup pour l'adversaire au prochain tour) et de position (priorité
	 * de certaine case sur d'autre).
	 * @param player est le joueur demandant le pion
	 * @return la position du meilleur pion à jouer
	 */
	private Point nextBestMovePositionMobility(Integer player) {
		Stack<Point> stackPoint = tree.getSentinel().getBoard()
				.calculatePlayablePosition(player);
		Integer[][] positionalMatrix = this.tree.getSentinel().getBoard().getPositionalMatrix(player);
		Integer score = Integer.MAX_VALUE;
		Integer opponent = player % 2 + 1;
		Point p = null;
		if(!stackPoint.isEmpty()){
			p = stackPoint.pop();
			NodeMove<Point> node = new NodeMove<Point>(p, opponent, new Board(tree.getSentinel().getBoard()), tree.getSentinel());
			node.calculateTurnResult();
			tree.getSentinel().addChild(node);
			score = node.getBoard().calculatePlayablePosition(player).size();
			tree.getSentinel().setBestMove(p);
		}
		while (!stackPoint.isEmpty()) {
			p = stackPoint.pop();
			NodeMove<Point> node = new NodeMove<Point>(p, opponent, new Board(
					tree.getSentinel().getBoard()), tree.getSentinel());
			node.calculateTurnResult();
			tree.getSentinel().addChild(node);
			Point best = tree.getSentinel().getBestMove();
			
			if(positionalMatrix[best.x][best.y] == positionalMatrix[p.x][p.y]){
				if (node.getBoard().calculatePlayablePosition(player).size() < score) {
					score = node.getBoard().calculatePlayablePosition(player).size();
					tree.getSentinel().setBestMove(p);
				}
			}
			else if(positionalMatrix[best.x][best.y] < positionalMatrix[p.x][p.y]){
				score = node.getBoard().calculatePlayablePosition(player).size();
				tree.getSentinel().setBestMove(p);
			}

		}
		return tree.getSentinel().getBestMove();
	}



}
