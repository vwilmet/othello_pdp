package com.ai.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.ai.ArtificialIntelligence;
import com.board.Board;
import com.board.Box;
import com.board.BoxImpl;
import com.tree.Node;
import com.tree.Tree;
import com.utils.WrongPlayablePositionException;

public class BruteForceAI2 implements ArtificialIntelligence {

	Box[][] board;
	Tree<Point> tree;
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
		tree = new Tree<Point>();
		tree.setRootElement(new Node<Point>(new Point(-1,-1), 1,whitePiece,blackPiece,board,boardWidth,boardHeight));
		tree.setSentinel(tree.getRootElement());
		/*scoreArray = new ArrayList<Integer>();
		call = 0;
		Integer finalScore = miniMax(8, tree.getSentinel());
		Integer alpha = Integer.MIN_VALUE;
		Integer beta = Integer.MAX_VALUE;
		//Integer finalScore = alphaBeta(8, tree.getSentinel(), alpha, beta);
		System.out.println("Call : " + call);
		showBestMoveParty();*/
		return true;
	}

	public Integer miniMax(Integer depth, Node<Point> node){
		Stack<Point> playablePosition = calculatePlayablePosition(node.getBoard(),node.getWhitePiece(),node.getBlackPiece(),node.getPlayer());
		call += 1;
		Integer i = 0;
		Integer bestScore;
		if(node.getPlayer() == 1)
			bestScore = Integer.MIN_VALUE;
		else
			bestScore = Integer.MAX_VALUE;

		if(!playablePosition.isEmpty() && depth > 0){ 
			while(!playablePosition.isEmpty() && depth > 0){
				Point p = playablePosition.pop();
				Node<Point> n = calculateTurnResult(p, node.getCopyOfBoard(), node.getCopyOfWhitePiece(), node.getCopyOfBlackPiece(), node.getPlayer());
				node.addChild(n);

				Integer score = miniMax(depth-1, n);
				if(node.player == 1 && score > bestScore){
					bestScore = score;
					node.setBestMove(n.getData());
				}
				else if(node.player == 2 && score < bestScore){
					bestScore = score;
					node.setBestMove(n.getData());
				}
				i++;

			}
		}
		else if(!calculatePlayablePosition(node.getBoard(),node.getWhitePiece(),node.getBlackPiece(),node.getPlayer()%2+1).isEmpty() && depth > 0){
			node.setPlayer(node.getPlayer()%2 + 1);
			bestScore = miniMax(depth, node);
		}
		else{
			bestScore = node.getNbWhite();
		}
		return bestScore;

	}
	
	public Integer alphaBeta(Integer depth, Node<Point> node, Integer alpha, Integer beta){
		Stack<Point> playablePosition = calculatePlayablePosition(node.getBoard(),node.getWhitePiece(),node.getBlackPiece(),node.getPlayer());
		Integer i = 0;
		Integer bestScore=0;
		call += 1;
		if(!playablePosition.isEmpty() && depth > 0){ 
			while(!playablePosition.isEmpty() && depth > 0){
				Point p = playablePosition.pop();
				Node<Point> n = calculateTurnResult(p, node.getCopyOfBoard(), node.getCopyOfWhitePiece(), node.getCopyOfBlackPiece(), node.getPlayer());
				node.addChild(n);

				Integer score = alphaBeta(depth-1, n, alpha, beta);
				if(node.player == 1 && score > alpha){
					alpha = score;
					bestScore = alpha;
					node.setBestMove(n.getData());
					if(alpha>=beta)
						break;
				}
				else if(node.player == 2 && score < beta){
					beta = score;
					bestScore = beta;
					node.setBestMove(n.getData());
					if(alpha>=beta)
						break;
				}
				i++;

			}
		}
		else if(!calculatePlayablePosition(node.getBoard(),node.getWhitePiece(),node.getBlackPiece(),node.getPlayer()%2+1).isEmpty() && depth > 0){
			node.setPlayer(node.getPlayer()%2 + 1);
			bestScore = alphaBeta(depth, node, alpha, beta);
		}
		else{
			bestScore = node.getNbWhite();
		}
		return bestScore;

	}
	
	public Node<Point> findNodeFromMove(Node<Point> node, Point p){
		Node<Point> n = null;
		for(Node<Point> child : node.getChildren()){
			if(child.getData().equals(p)){
				n = child;
				break;
			}
		}
		return n;
	}
	
	public void showBestMoveParty(){
		Node<Point> s = tree.getRootElement();
		Point p = s.getBestMove();
		while(p != null){
			System.out.println("BestMove : " + p.toString());
			printBoard(s.board);
			s = findNodeFromMove(s, p);
			p = s.getBestMove();
		}
			
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

	public Set<Point> calculateBorderLine(Box[][] nodeBoard, Set<Point> nodeWhitePiece, Set<Point> nodeBlackPiece, Integer player){
		Set<Point> borderLine = new HashSet<Point>();
		if(player == 1){
			for(Point black : nodeBlackPiece){
				for(int x = - 1; x < 2; x++){
					for(int y = -1; y < 2; y++){
						if(black.x + x >= 0 && black.x + x < this.boardWidth && black.y + y >= 0 && black.y + y < this.boardHeight && !(x == 0 && y == 0)){
							if(nodeBoard[x + black.x][y + black.y].isEmpty()){
								borderLine.add(new Point(x + black.x, y + black.y));
							}
						}

					}
				}

			}
		}
		else{
			for(Point white : nodeWhitePiece){
				for(int x = - 1; x < 2; x++){
					for(int y = -1; y < 2; y++){
						if(white.x + x >= 0 && white.x + x < this.boardWidth && white.y + y >= 0 && white.y + y < this.boardHeight && !(x == 0 && y == 0)){
							if(nodeBoard[x + white.x][y + white.y].isEmpty()){
								borderLine.add(new Point(x + white.x, y + white.y));
							}
						}

					}
				}

			}
		}
		return borderLine;
	}

	public Stack<Point> calculatePlayablePosition(Box[][] nodeBoard, Set<Point> boardWhitePiece, Set<Point> boardBlackPiece, Integer player){
		Set<Point> nodeBorderline = calculateBorderLine(nodeBoard, boardWhitePiece, boardBlackPiece, player);
		Stack<Point> possiblePosition = new Stack<Point>();
		for(Point p : nodeBorderline){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((p.x+x) < boardWidth && (p.x + x) >= 0 && (p.y + y) < boardHeight && (p.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(!nodeBoard[p.x + x][p.y + y].isPlayer(player) && !nodeBoard[p.x+x][p.y+y].isEmpty()){
							Integer i = p.x + x + x;
							Integer j = p.y + y + y;
							Boolean playable = false;
							while(i < boardWidth && i >= 0 && j < boardHeight && j >= 0 && playable == false){
								if(!nodeBoard[i][j].isPlayer(player) && !nodeBoard[i][j].isEmpty()){ // teste s'il y a un pion et si c'est un pion adverse
									i += x;
									j += y;
								}
								else if(nodeBoard[i][j].isPlayer(player)) //teste le pion du joueur qui va "encadrer le coup" 
									playable = true;
								else if(nodeBoard[i][j].isEmpty())
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
	public Node<Point> calculateTurnResult(Point position, Box[][] newBoard, Set<Point> whitePiece, Set<Point> blackPiece, Integer player){
		if(player == 1){
			whitePiece.add(new Point(position.x,position.y));
			newBoard[position.x][position.y].putP1Piece();
			Set<Point> tmp = new HashSet<Point>();
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < boardWidth && (position.x + x) >= 0 && (position.y + y) < boardHeight && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(newBoard[position.x + x][position.y + y].isP2Piece()){
							tmp.clear();
							tmp.add(new Point(position.x+x,position.y+y));
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							while(i < boardWidth && i >= 0 && j < boardHeight && j >= 0){
								if(newBoard[i][j].isP2Piece()){
									tmp.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (newBoard[i][j].isP1Piece()){
									whitePiece.addAll(tmp);
									for(Point p : tmp)
										newBoard[p.x][p.y].turnPiece();
									break;
								}
								else if(newBoard[i][j].isEmpty())
									break;
							}
						}
					}
				}
			}
			blackPiece.removeAll(whitePiece);
		}
		else if(player == 2){
			newBoard[position.x][position.y].putP2Piece();
			blackPiece.add(new Point(position.x,position.y));
			Set<Point> tmp = new HashSet<Point>();
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < boardWidth && (position.x + x) >= 0 && (position.y + y) < boardHeight && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(newBoard[position.x + x][position.y + y].isP1Piece()){
							tmp.clear();
							tmp.add(new Point(position.x+x,position.y+y));
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							Boolean stop = false;
							while(i < boardWidth && i >= 0 && j < boardHeight && j >= 0 && stop == false){
								if(newBoard[i][j].isP1Piece()){
									tmp.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (newBoard[i][j].isP2Piece()){
									blackPiece.addAll(tmp);
									for(Point p : tmp){
										newBoard[p.x][p.y].turnPiece();
									}
									break;
								}
								else if(newBoard[i][j].isEmpty())
									break;
							}

						}
					}
				}
			}
			whitePiece.removeAll(blackPiece); 
		}
		return new Node<Point>(position, player%2+1, whitePiece, blackPiece, newBoard, boardWidth, boardHeight);
	}

	public Box[][] copyOfBoard(Box[][] oldBoard){
		Box[][] newBoard = new Box[this.boardWidth][this.boardHeight];
		for(int i = 0; i < this.boardWidth;i++)
			for(int j = 0; j < this.boardHeight; j++){
				newBoard[i][j] = new BoxImpl();
				newBoard[i][j].setState(oldBoard[i][j].getState());
			}

		return newBoard;
	}

	public void printBoard(Box[][] board){
		for(int i = 0; i < this.boardWidth; i++)
			System.out.print(" _");
		for(int j = 0; j < this.boardHeight; j++){
			System.out.println("");
			System.out.print("|");
			for(int i = 0; i < this.boardWidth; i++){
				if(board[i][j].isP1Piece())
					System.out.print("1|");
				else if(board[i][j].isP2Piece())
					System.out.print("2|");
				else
					System.out.print(" |");
			}
			System.out.println("");
			for(int i = 0; i < this.boardWidth; i++)
				System.out.print(" _");
		}
		System.out.println("");

	}
}
