package AI;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomAI implements ArtificialIntelligence {

	Box[][] myBoard;
	Tree<Point> myTree;
	Set<Point> whitePiece;
	Set<Point> blackPiece;
	Integer boardWidth;
	Integer boardHeight;
	private Set<Point> borderLine;



	@Override
	public Point nextMove(Integer player) {
		Set<Point> playable = calculatePlayablePosition(player);
		Point[] arrayPoint = playable.toArray(new Point[0]);
		if(arrayPoint.length > 0){
			Random rand = new Random();
			int randomNum = rand.nextInt(arrayPoint.length);
			return arrayPoint[randomNum];
		}
		else
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
		this.whitePiece = whitePiece;
		this.blackPiece = blackPiece;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		myTree = new Tree<Point>();
		myTree.setRootElement(new Node<Point>(new Point(-1,-1),-1));
		myTree.setSentinel(myTree.getRootElement());
		myBoard = new Box[boardWidth][boardHeight];
		for(Integer i = 0; i < this.boardWidth; i ++)
			for(Integer j = 0; j < this.boardHeight; j++){
				myBoard[i][j] = new BoxImpl();
				if(whitePiece.contains(new Point(i,j)))
					myBoard[i][j].putP1Piece();
				else if(blackPiece.contains(new Point(i,j)))
					myBoard[i][j].putP2Piece();
				else
					myBoard[i][j].removePiece();
			}
		return true;
	}

	/*@Override
	public Boolean actualize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void notifyChosenMove(Point pos, Integer player) {
		
		if(player == 1){
			myBoard[pos.x][pos.y].putP1Piece();
			this.whitePiece.add(pos);
		}
		else if(player == 2){
			myBoard[pos.x][pos.y].putP2Piece();
			this.blackPiece.add(pos);
		}
		calculateTurnResult(pos);
		Node<Point> myNode = new Node<Point>(pos,player,myTree.getSentinel(), this.whitePiece.size(), this.blackPiece.size());
		myTree.getSentinel().addChild(myNode);
		myTree.setSentinel(myNode);
	}

	public void printBoard(){
		for(int i = 0; i < this.boardWidth; i++)
			System.out.print(" _");
		for(int j = 0; j < this.boardHeight; j++){
			System.out.println("");
			System.out.print("|");
			for(int i = 0; i < this.boardWidth; i++){
				if(myBoard[i][j].isP1Piece())
					System.out.print("1|");
				else if(myBoard[i][j].isP2Piece())
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

	@Override
	public Boolean completeReflexion() {
		return true;
	}

	public void calculateBorderLine(){
		Set<Point> borderLine = new HashSet<Point>();
		for(Point white : whitePiece){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if(white.x + x >= 0 && white.x + x < this.boardWidth && white.y + y >= 0 && white.y + y < this.boardHeight && !(x == 0 && y == 0)){
						if(myBoard[x + white.x][y + white.y].isEmpty()){
							borderLine.add(new Point(x + white.x, y + white.y));
						}
					}

				}
			}

		}
		for(Point black : blackPiece){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if(black.x + x >= 0 && black.x + x < this.boardWidth && black.y + y >= 0 && black.y + y < this.boardHeight && !(x == 0 && y == 0)){
						if(myBoard[x + black.x][y + black.y].isEmpty()){
							borderLine.add(new Point(x + black.x, y + black.y));
						}
					}

				}
			}

		}
		this.borderLine = borderLine;
	}

	public Set<Point> calculatePlayablePosition(Integer player){
		calculateBorderLine();
		Set<Point> possiblePosition = new HashSet<Point>();
		if(player == 1 || player == 2){
			for(Point p : this.borderLine){
				for(int x = - 1; x < 2; x++){
					for(int y = -1; y < 2; y++){
						if((p.x+x) < boardWidth && (p.x + x) >= 0 && (p.y + y) < boardHeight && (p.y+ y) >= 0 && !(x == 0 && y == 0)){
							if(!myBoard[p.x + x][p.y + y].isPlayer(player) && !myBoard[p.x+x][p.y+y].isEmpty()){
								Integer i = p.x + x + x;
								Integer j = p.y + y + y;
								Boolean playable = false;
								while(i < boardWidth && i >= 0 && j < boardHeight && j >= 0 && playable == false){
									if(!myBoard[i][j].isPlayer(player) && !myBoard[i][j].isEmpty()){ // teste s'il y a un pion et si c'est un pion adverse
										i += x;
										j += y;
									}
									else if(myBoard[i][j].isPlayer(player)) //teste le pion du joueur qui va "encadrer le coup" 
										playable = true;
									else if(myBoard[i][j].isEmpty())
										break;
								}
								if(playable)
									possiblePosition.add(p);
							}
						}
					}
				}
			}
		}
		else
			System.err.println("Erreur fonction calculatePlayablePosition paramètre player = "+ player + " .");
		return possiblePosition;

	}

	public Set<Point> getBorderLine() {
		return borderLine;
	}

	public void setBorderLine(Set<Point> borderLine) {
		this.borderLine = borderLine;
	}

	public void calculateTurnResult(Point position){
		if(myBoard[position.x][position.y].isP1Piece()){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < boardWidth && (position.x + x) >= 0 && (position.y + y) < boardHeight && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(myBoard[position.x + x][position.y + y].isP2Piece()){
							Set<Point> tmp = new HashSet<Point>();
							tmp.add(new Point(position.x+x,position.y+y));
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							while(i < boardWidth && i >= 0 && j < boardHeight && j >= 0){
								if(myBoard[i][j].isP2Piece()){
									tmp.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (myBoard[i][j].isP1Piece()){
									whitePiece.addAll(tmp);
									for(Point p : tmp)
										myBoard[p.x][p.y].turnPiece();
									break;
								}
								else if(myBoard[i][j].isEmpty())
									break;
							}
						}
					}
				}
			}
			this.blackPiece.removeAll(whitePiece);
		}
		else if(myBoard[position.x][position.y].isP2Piece()){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < boardWidth && (position.x + x) >= 0 && (position.y + y) < boardHeight && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(myBoard[position.x + x][position.y + y].isP1Piece()){
							Set<Point> tmp = new HashSet<Point>();
							tmp.add(new Point(position.x+x,position.y+y));
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							Boolean stop = false;
							while(i < boardWidth && i >= 0 && j < boardHeight && j >= 0 && stop == false){
								if(myBoard[i][j].isP1Piece()){
									tmp.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (myBoard[i][j].isP2Piece()){
									this.blackPiece.addAll(tmp);
									for(Point p : tmp){
										myBoard[p.x][p.y].turnPiece();
									}
									break;
								}
								else if(myBoard[i][j].isEmpty())
									break;
							}

						}
					}
				}
			}
			this.whitePiece.removeAll(blackPiece); 
		}
	}

}
