package com.ai.impl;

import java.awt.Point;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.ai.ArtificialIntelligence;
import com.board.Board;
import com.tree.NodeMove;
import com.tree.TreeMove;
import com.utils.WrongPlayablePositionException;

public class BruteForceAI implements ArtificialIntelligence {

	TreeMove<Point> tree;
	Set<Point> whitePiece;
	Set<Point> blackPiece;
	Integer boardWidth;
	Integer boardHeight;
	private Set<Point> borderLine;
	List<Integer> scoreArray;
	Integer call;
	Board initBoard;
	
	@Override
	public Point nextMove(Integer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> nextMoves(Integer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer winStatus(Integer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		initBoard = new Board(boardWidth, boardHeight, whitePiece, blackPiece);
		tree = new TreeMove<Point>();
		tree.setRootElement(new NodeMove<Point>(new Point(-1,-1),1,initBoard));
		tree.setSentinel(tree.getRootElement());
		call = 0;
		//Integer finalScore = miniMax(8, tree.getSentinel());
		Integer alpha = Integer.MIN_VALUE;
		Integer beta = Integer.MAX_VALUE;
		Integer finalScore = alphaBeta(12, tree.getSentinel(), alpha, beta);
		System.out.println("Call : " + call);
		showBestMoveParty();
		return true;
	}

	public Integer miniMax(Integer depth, NodeMove<Point> node){
		Stack<Point> playablePosition = node.calculatePlayablePosition();
		call += 1;
		Integer i = 0;
		Integer bestScore;
		Integer opponent = node.getPlayer()%2+1;
		if(node.getPlayer() == 1)
			bestScore = Integer.MIN_VALUE;
		else
			bestScore = Integer.MAX_VALUE;

		if(!playablePosition.isEmpty() && depth > 0){ 
			while(!playablePosition.isEmpty() && depth > 0){
				Point p = playablePosition.pop();
				NodeMove<Point> n = new NodeMove<Point>(p,opponent,new Board(node.getBoard())); 
				n.calculateTurnResult();
						//calculateTurnResult(p, node.getCopyOfBoard(), node.getCopyOfWhitePiece(), node.getCopyOfBlackPiece(), node.getPlayer());
				node.addChild(n);

				Integer score = miniMax(depth-1, n);
				if(node.getPlayer() == 1 && score > bestScore){
					bestScore = score;
					node.setBestMove(n.getData());
				}
				else if(node.getPlayer() == 2 && score < bestScore){
					bestScore = score;
					node.setBestMove(n.getData());
				}
				i++;

			}
		}
		else if(!node.getBoard().calculatePlayablePosition(opponent).isEmpty() && depth > 0){
			node.setPlayer(opponent);
			bestScore = miniMax(depth, node);
		}
		else{
			bestScore = node.getBoard().getNbWhitePiece();
		}
		return bestScore;

	}
	
	public Integer alphaBeta(Integer depth, NodeMove<Point> node, Integer alpha, Integer beta){
		Stack<Point> playablePosition = node.calculatePlayablePosition();
		Integer i = 0;
		Integer bestScore=0;
		call += 1;
		Integer opponent = node.getPlayer()%2+1;
		if(!playablePosition.isEmpty() && depth > 0){ 
			while(!playablePosition.isEmpty() && depth > 0){
				Point p = playablePosition.pop();
				NodeMove<Point> n = new NodeMove<Point>(p,opponent,new Board(node.getBoard())); 
				n.calculateTurnResult();
				node.addChild(n);
				Integer score = alphaBeta(depth-1, n, alpha, beta);
				if(node.getPlayer() == 1 && score > alpha){
					alpha = score;
					bestScore = alpha;
					node.setBestMove(n.getData());
					if(alpha>=beta)
						break;
				}
				else if(node.getPlayer() == 2 && score < beta){
					beta = score;
					bestScore = beta;
					node.setBestMove(n.getData());
					if(alpha>=beta)
						break;
				}
				else if(node.getBestMove() == null){
					node.setBestMove(n.getData());
				}
				i++;

			}
		}
		else if(!node.getBoard().calculatePlayablePosition(opponent).isEmpty() && depth > 0){
			node.setPlayer(node.getPlayer()%2 + 1);
			bestScore = alphaBeta(depth, node, alpha, beta);
		}
		else{
			bestScore = node.getBoard().getNbWhitePiece();
		}
		return bestScore;

	}
	
	public NodeMove<Point> findNodeFromMove(NodeMove<Point> node, Point p){
		NodeMove<Point> n = null;
		for(NodeMove<Point> child : node.getChildren()){
			if(child.getData().equals(p)){
				n = child;
				break;
			}
		}
		return n;
	}
	
	public void showBestMoveParty(){
		NodeMove<Point> s = tree.getRootElement();
		Point p = s.getBestMove();
		while(p != null){
			System.out.println(s.printBoard());
			System.out.println("BestMove : " + p.toString());
			s = findNodeFromMove(s, p);
			p = s.getBestMove();
		}
		System.out.println(s.printBoard());
		System.out.println("End of game!");
			
	}

	@Override
	public void notifyChosenMove(Point pos, Integer player)
			throws WrongPlayablePositionException {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean completeReflexion() {
		// TODO Auto-generated method stub
		return null;
	}



	
}
