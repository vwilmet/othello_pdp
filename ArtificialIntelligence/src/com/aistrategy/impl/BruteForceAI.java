package com.aistrategy.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.aistrategy.ArtificialIntelligenceStrategy;
import com.board.Board;
import com.error_manager.Log;
import com.timermanager.TimerManager;
import com.timermanager.TimerManagerImpl;
import com.tree.NodeMove;
import com.tree.TreeMove;
import com.utils.WrongPlayablePositionException;

/**
 * Classe qui implémente les méthodes de la stratégie / Intelligence
 * Artificielle avec une approche BruteForce (calcule totale de l'arbre de
 * partie). </br>Elle implémente l'interface
 * {@link com.aistrategy.ArtificialIntelligenceStrategy}.
 * 
 * @author <ul>
 *         <li>Nicolas Yvon</li>
 *         </ul>
 * @version 1.0
 */
public class BruteForceAI implements
ArtificialIntelligenceStrategy {

	/**
	 * Arbre de coup représentant l'ensemble d'une partie
	 */
	protected TreeMove<Point> tree;

	/**
	 * Plateau initial au début du lancement de l'IA
	 */
	protected Board initBoard;
	/**
	 * Profondeur de recherche pour l'arbre de coups
	 */
	protected static final Integer DEPTH = 7;

	/**
	 * Minuteur pour le temps d'execution de l'algorithme.
	 */
	protected TimerManager tm;

	/**
	 * Booleen pour la gestion de l'arrêt de l'execution de l'algorithme
	 */
	protected volatile Boolean stopAlgorithm;

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
		Point p = tree.getSentinel().getBestMove();
		if (p == null && !tree.getSentinel().getBoard().calculatePlayablePosition(player).isEmpty()) {
			if((initBoard.getHeight()*initBoard.getWidth() - (this.tree.getSentinel().getBoard().getNbBlackPiece() + this.tree.getSentinel().getBoard().getNbWhitePiece())) <= DEPTH){
				Integer alpha = Integer.MIN_VALUE;
				Integer beta = Integer.MAX_VALUE;
				alphaBetaPVS(DEPTH, tree.getSentinel(), alpha, beta);
			}
			else{
				p = nextBestMovePositionMobility(player);
			}
			p = tree.getSentinel().getBestMove();
		} else if (tree.getSentinel().getBoard()
				.calculatePlayablePosition(player).isEmpty())
			p = null;
		return p;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#nextMove}
	 */
	public Point quickNextMove(Integer player){
		Point p = tree.getSentinel().getBestMove();
		this.stopAlgorithm = true;
		if (p == null && !tree.getSentinel().getBoard().calculatePlayablePosition(player).isEmpty()) {
			p = nextBestMovePositionMobility(player);
		} 
		else if (tree.getSentinel().getBoard()
				.calculatePlayablePosition(player).isEmpty())
			p = null;
		return p;
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
		NodeMove<Point> s = tree.getSentinel();
		List<Point> nextMoves = new ArrayList<Point>();
		Point p = s.getBestMove();
		while (p != null) {
			nextMoves.add(p);
			s = findNodeFromMove(s, p);
			p = s.getBestMove();
		}
		Integer i = 0;
		if (s.getBoard().getNbWhitePiece() == s.getBoard().getNbBlackPiece())
			i = 2;
		else if (player == 1
				&& s.getBoard().getNbWhitePiece() > s.getBoard()
				.getNbBlackPiece())
			i = 1;
		else if (player == 2
				&& s.getBoard().getNbWhitePiece() < s.getBoard()
				.getNbBlackPiece())
			i = 1;
		else
			i = 0;
		return i;
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
		stopAlgorithm = false;
		initBoard = new Board(boardWidth, boardHeight, whitePiece, blackPiece);
		tree = new TreeMove<Point>();
		tree.setRootElement(new NodeMove<Point>(new Point(-1, -1), 1, initBoard));
		tree.setSentinel(tree.getRootElement());
		tm = new TimerManagerImpl();
		if (maxTime == null)
			this.maxTime = 0;
		return true;
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
		stopAlgorithm = false;
		initBoard = random.initBoard;
		tree = random.tree;
		maxTime = random.maxTime;
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#initialize}
	 */
	@Override
	public Boolean initialize(NextBestMoveAI nextBestMove) {
		stopAlgorithm = false;
		initBoard = nextBestMove.initBoard;
		tree = nextBestMove.tree;
		tm = new TimerManagerImpl();
		maxTime = nextBestMove.maxTime;
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir {@link com.aistrategy.ArtificialIntelligenceStrategy#initialize}
	 */
	@Override
	public Boolean initialize(BruteForceAI brute) {
		stopAlgorithm = false;
		initBoard = brute.initBoard;
		tree = brute.tree;
		tm = new TimerManagerImpl();
		this.maxTime = brute.maxTime;
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
		System.out.println(this.tree.getSentinel().getBoard().printBoard());
		System.out.println(player);
		System.out.println(this.tree.getSentinel().getBoard()
				.calculatePlayablePosition(player));
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

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée ! <br/>
	 * Utiliser l'interface
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy} pour stocker
	 * l'objet de la classe <br/>
	 * Voir
	 * {@link com.aistrategy.ArtificialIntelligenceStrategy#completeReflexion}
	 */
	@SuppressWarnings("static-access")
	@Override
	public Boolean completeReflexion() {
		this.stopAlgorithm = true;
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

	/**
	 * Fonction récursive permettant de créer un arbre représentant tous les
	 * coups possibles jouables </br> puis calculant les meilleurs coups
	 * jouables en remontant depuis les feuilles </br> jusqu'à la racine le
	 * meilleur score de chaque noeud à partir des noeud fils.
	 * 
	 * @param depth
	 *            correspond au nombre de coups à calculer, c'est à dire, la
	 *            profondeur de l'arbre à visiter.
	 * @param node
	 *            correspond au noeud à partir duquel les coups possibles seront
	 *            calculés.
	 * @return le meilleur score obtenu à partir de ce noeud
	 */
	public Integer miniMax(Integer depth, NodeMove<Point> node) {
		Stack<Point> playablePosition = node.calculatePlayablePosition();
		Integer bestScore;
		Integer opponent = node.getPlayer() % 2 + 1;
		if (node.getPlayer() == 1)
			bestScore = Integer.MIN_VALUE;
		else
			bestScore = Integer.MAX_VALUE;

		if (!playablePosition.isEmpty() && depth > 0) {
			while (!playablePosition.isEmpty() && depth > 0) {
				Point p = playablePosition.pop();
				NodeMove<Point> n = new NodeMove<Point>(p, opponent, new Board(
						node.getBoard()));
				n.calculateTurnResult();
				node.addChild(n);

				Integer score = miniMax(depth - 1, n);
				if (node.getPlayer() == 1 && score > bestScore) {
					bestScore = score;
					node.setBestMove(n.getLastMove());
				} else if (node.getPlayer() == 2 && score < bestScore) {
					bestScore = score;
					node.setBestMove(n.getLastMove());
				}

			}
		} else if (!node.getBoard().calculatePlayablePosition(opponent)
				.isEmpty()
				&& depth > 0) {
			node.setPlayer(opponent);
			bestScore = miniMax(depth, node);
		} else {
			bestScore = node.getBoard().getNbWhitePiece();
		}
		return bestScore;

	}

	/**
	 * Version amélioré de l'algorithme MiniMax
	 * {@link com.aistrategy.impl.BruteForceAI#miniMax(Integer, NodeMove)}.
	 * 
	 * @param depth
	 *            correspond au nombre de coups à calculer, c'est à dire, la
	 *            profondeur de l'arbre à visiter.
	 * @param node
	 *            correspond au noeud à partir duquel les coups possibles seront
	 *            calculés.
	 * @return le meilleur score obtenu à partir de ce noeud
	 */
	public Integer alphaBeta(Integer depth, NodeMove<Point> node,
			Integer alpha, Integer beta) {
		Stack<Point> playablePosition = node.calculatePlayablePosition();
		Integer bestScore = 0;
		Integer opponent = node.getPlayer() % 2 + 1;
		if (!playablePosition.isEmpty() && depth > 0) {
			while (!playablePosition.isEmpty() && depth > 0) {
				Point p = playablePosition.pop();
				NodeMove<Point> n = new NodeMove<Point>(p, opponent, new Board(
						node.getBoard()));
				n.calculateTurnResult();
				node.addChild(n);
				Integer score = alphaBeta(depth - 1, n, alpha, beta);
				if (node.getPlayer() == 1 && score > alpha) {
					alpha = score;
					bestScore = alpha;
					node.setBestMove(n.getLastMove());
					if (alpha >= beta)
						break;
				} else if (node.getPlayer() == 2 && score < beta) {
					beta = score;
					bestScore = beta;
					node.setBestMove(n.getLastMove());
					if (alpha >= beta)
						break;
				} else if (node.getBestMove() == null) {
					node.setBestMove(n.getLastMove());
				}
			}
		} else if (!node.getBoard().calculatePlayablePosition(opponent)
				.isEmpty()
				&& depth > 0) {
			node.setPlayer(node.getPlayer() % 2 + 1);
			bestScore = alphaBeta(depth, node, alpha, beta);
		} else {
			bestScore = node.getBoard().getNbWhitePiece();
		}
		return bestScore;

	}

	public Integer alphaBetaNegaMax(Integer depth, NodeMove<Point> node,
			Integer alpha, Integer beta) {
		Stack<Point> playablePosition = node.calculatePlayablePosition();
		Integer bestScore = 0;
		Integer opponent = node.getPlayer() % 2 + 1;
		if (!playablePosition.isEmpty() && depth > 0) {
			while (!playablePosition.isEmpty() && depth > 0) {
				Point p = playablePosition.pop();
				NodeMove<Point> n = new NodeMove<Point>(p, opponent, new Board(
						node.getBoard()));
				n.calculateTurnResult();
				node.addChild(n);
				Integer score = -alphaBetaNegaMax(depth - 1, n, -beta, -alpha);
				if (score >= alpha) {
					alpha = score;
					bestScore = alpha;
					node.setBestMove(n.getLastMove());
					if (alpha >= beta)
						break;
				} else if (node.getBestMove() == null) {
					node.setBestMove(n.getLastMove());
				}

			}
		} else if (!node.getBoard().calculatePlayablePosition(opponent)
				.isEmpty()
				&& depth > 0) {
			node.setPlayer(node.getPlayer() % 2 + 1);
			bestScore = -alphaBeta(depth, node, -beta, -alpha);
		} else {
			bestScore = node.getBoard().getNbWhitePiece();
		}
		return bestScore;

	}

	public Integer failSoftAlphaBeta(Integer depth, NodeMove<Point> node,
			Integer alpha, Integer beta) {
		Stack<Point> playablePosition = node.calculatePlayablePosition();
		Integer bestScore = 0;
		Integer current = Integer.MIN_VALUE;
		Integer opponent = node.getPlayer() % 2 + 1;
		if (!playablePosition.isEmpty() && depth > 0) {
			while (!playablePosition.isEmpty() && depth > 0) {
				Point p = playablePosition.pop();
				NodeMove<Point> n = new NodeMove<Point>(p, opponent, new Board(
						node.getBoard()));
				n.calculateTurnResult();
				node.addChild(n);
				Integer score = -failSoftAlphaBeta(depth - 1, n, -beta, -alpha);
				if (score >= current) {
					current = score;
					bestScore = current;
					node.setBestMove(n.getLastMove());
					if (score >= alpha) {
						alpha = score;
						bestScore = alpha;
						node.setBestMove(n.getLastMove());
						if (score >= beta)
							break;
					}
				} else if (node.getBestMove() == null) {
					node.setBestMove(n.getLastMove());
				}

			}
		} else if (!node.getBoard().calculatePlayablePosition(opponent)
				.isEmpty()
				&& depth > 0) {
			node.setPlayer(node.getPlayer() % 2 + 1);
			bestScore = -alphaBeta(depth, node, -beta, -alpha);
		} else {
			bestScore = node.getBoard().getNbWhitePiece();
		}
		return bestScore;
	}

	@SuppressWarnings("static-access")
	public synchronized Integer alphaBetaPVS(Integer depth, NodeMove<Point> node,
			Integer alpha, Integer beta) {
		Stack<Point> playablePosition = node.calculatePlayablePosition();
		Integer bestScore = 0;
		Integer opponent = node.getPlayer() % 2 + 1;
		if (!playablePosition.isEmpty() && depth > 0 && !this.stopAlgorithm) {
			Point firstMove = playablePosition.pop();
			node.setBestMove(firstMove);
			NodeMove<Point> firstNode = new NodeMove<Point>(firstMove,
					opponent, new Board(node.getBoard()));
			firstNode.calculateTurnResult();
			node.addChild(firstNode);
			Integer current = -alphaBetaPVS(depth - 1, firstNode, -beta, -alpha);
			if (current >= alpha)
				alpha = current;
			if (current < beta) {
				while (!playablePosition.isEmpty() && depth > 0
						&& !this.stopAlgorithm) {
					Point p = playablePosition.pop();
					NodeMove<Point> n = new NodeMove<Point>(p, opponent,
							new Board(node.getBoard()));
					n.calculateTurnResult();
					node.addChild(n);
					Integer score = -alphaBetaPVS(depth - 1, n, -(alpha + 1),
							-alpha);
					if (score > alpha && score < beta)
						score = -alphaBetaPVS(depth - 1, n, -beta, -alpha);
					if (score >= current) {
						current = score;
						bestScore = current;
						node.setBestMove(n.getLastMove());
						if (score >= alpha) {
							alpha = score;
							bestScore = alpha;
							node.setBestMove(n.getLastMove());
							if (score >= beta)
								break;
						}
					} else if (node.getBestMove() == null) {
						node.setBestMove(n.getLastMove());
					}
				}
			}
		} else if (!node.getBoard().calculatePlayablePosition(opponent)
				.isEmpty()
				&& depth > 0 && !this.stopAlgorithm) {
			node.setPlayer(node.getPlayer() % 2 + 1);
			bestScore = -alphaBetaPVS(depth, node, -beta, -alpha);
		} else {
			bestScore = node.getBoard().getNbWhitePiece();
		}
		return bestScore;
	}

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
			/*if(positionalMatrix[best.x][best.y] == positionalMatrix[p.x][p.y]){
				if(node.getBoard().calculatePlayablePosition(player).size() == score){
					score = node.getBoard().getNbWhitePiece();
					tree.getSentinel().setBestMove(p);
				}
			}
			else if(positionalMatrix[best.x][best.y] < positionalMatrix[p.x][p.y]){
				score = node.getBoard().getNbWhitePiece();
				tree.getSentinel().setBestMove(p);
				if (node.getBoard().calculatePlayablePosition(player).size() == score) {

				}
			}*/

			if (node.getBoard().calculatePlayablePosition(player).size() == score) {
				best = tree.getSentinel().getBestMove();
				if (positionalMatrix[best.x][best.y] < positionalMatrix[p.x][p.y]) { 
					score = node.getBoard().calculatePlayablePosition(player).size();
					tree.getSentinel().setBestMove(p);
				}
			} 
			else if (node.getBoard().calculatePlayablePosition(player).size() < score) {
				score = node.getBoard().calculatePlayablePosition(player).size();
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

	public void showBestMoveParty() {
		NodeMove<Point> s = tree.getRootElement();
		Point p = s.getBestMove();
		while (p != null) {
			System.out.println(s.printBoard());
			System.out.println("BestMove : " + p.toString());
			s = findNodeFromMove(s, p);
			p = s.getBestMove();
		}
		System.out.println(s.printBoard());
		System.out.println("End of game!");

	}




	public String boardToString() {
		return this.tree.getSentinel().getBoard().printBoard();
	}

	class doAlgorithm implements Runnable{
		public void run(){
			Integer alpha = Integer.MIN_VALUE;
			Integer beta = Integer.MAX_VALUE;
			alphaBetaPVS(DEPTH, tree.getSentinel(), alpha,
					beta);
		}
	}
}

