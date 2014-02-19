/*package AI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class RandomAIOld implements ArtificialIntelligence {

	Box[][] board;
	List<Point> whitePiece;
	List<Point> blackPiece;
	Integer width;
	Integer height;
	Set<Point> borderLine;
	Tree<Point> myTree;
	Node<Point> mySentinel;
	
	
	@Override
	public Point nextMove(Integer player) {
		// TODO Auto-generated method stub
		if(!(mySentinel.data.x == -1 && mySentinel.data.y == -1))
		{
			calculateTurnResult(mySentinel.data);
		}
		Set<Point> playable = calculatePlayablePosition(player);
		Point[] arrayPoint = playable.toArray(new Point[0]);
		Random rand = new Random();
		int randomNum = rand.nextInt(arrayPoint.length);
		Node<Point> myNode = new Node<Point>(arrayPoint[randomNum],player);
		myNode.setParent(mySentinel);
		mySentinel.addChild(myNode);
		mySentinel = myNode;
		return arrayPoint[randomNum];
	}

	@Override
	public List<Point> nextMoves(Integer player) {
		
		return null;
	}

	@Override
	public Integer winStatus(Integer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean initialize(List<Point> whitePiece, List<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		this.whitePiece = new ArrayList<Point>();
		this.blackPiece = new ArrayList<Point>();
		this.whitePiece.addAll(whitePiece);
		this.blackPiece.addAll(blackPiece);
		this.width = boardWidth;
		this.height = boardHeight;
		this.myTree = new Tree<Point>();
		Node<Point> myRoot = new Node<Point>();
		myRoot.setData(new Point(-1,-1));
		myTree.setRootElement(myRoot);
		mySentinel = myTree.getRootElement();
		board = new BoxImpl[boardWidth][boardHeight];
		for(int i = 0; i < boardWidth; i++)
			for(int j = 0; j < boardHeight; j++)
			board[i][j] = new BoxImpl();
		for( int i = 0; i<whitePiece.size();i++){
			board[this.whitePiece.get(i).x][this.whitePiece.get(i).y].putP1Piece();
		}
		for(int i = 0; i<blackPiece.size();i++){
			board[blackPiece.get(i).x][blackPiece.get(i).y].putP2Piece();
		}
		
		this.borderLine = calculateBorderLine();

		if(this.borderLine != null)
			return true;
		else
			return false;
	}

	@Override
	public Boolean completeReflexion() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Set<Point> calculateBorderLine(){
		Set<Point> borderLine = new HashSet<Point>();

		for(int i = 0; i < whitePiece.size() ; i++){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if(whitePiece.get(i).x + x >= 0 && whitePiece.get(i).x + x < this.width && whitePiece.get(i).y + y >= 0 && whitePiece.get(i).y + y < this.width && !(x == 0 && y == 0)){
						if(board[x + whitePiece.get(i).x][y + whitePiece.get(i).y].isEmpty()){
							borderLine.add(new Point(x + whitePiece.get(i).x, y + whitePiece.get(i).y));
						}
					}
							
				}
			}
				
		}
		for(int i = 0; i < blackPiece.size() ; i++){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if(blackPiece.get(i).x + x >= 0 && blackPiece.get(i).x + x < this.width && blackPiece.get(i).y + y >= 0 && blackPiece.get(i).y + y < this.width && !(x == 0 && y == 0)){
						if(board[x + blackPiece.get(i).x][y + blackPiece.get(i).y].isEmpty()){
							borderLine.add(new Point(x + blackPiece.get(i).x, y + blackPiece.get(i).y));
						}
					}
							
				}
			}
				
		}
		
		return borderLine;
	}
	
	private Set<Point> calculatePlayablePosition(Integer player){
		this.borderLine = calculateBorderLine();
		Set<Point> possiblePosition = new HashSet<Point>();
		if(player == 1){
			for(Point p : this.borderLine){
				for(int x = - 1; x < 2; x++){
					for(int y = -1; y < 2; y++){
						if((p.x+x) < width && (p.x + x) >= 0 && (p.y + y) < height && (p.y+ y) >= 0 && !(x == 0 && y == 0)){
							if(board[p.x + x][p.y + y].isP2Piece()){
								Integer i = p.x + x + x;
								Integer j = p.y + y + y;
								Boolean playable = false;
								while(i < width && i >= 0 && j < height && j >= 0 && playable == false){
									if(board[i][j].isP2Piece()){
										i += x;
										j += y;
									}
									else if(board[i][j].isP1Piece())
										playable = true;
									else if(board[i][j].isEmpty())
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
		else if(player == 2){
			for(Point p : this.borderLine){
				for(int x = - 1; x < 2; x++){
					for(int y = -1; y < 2; y++){
						if((p.x+x) < width && (p.x + x) >= 0 && (p.y + y) < height && (p.y+ y) >= 0 && !(x == 0 && y == 0)){
							if(board[p.x + x][p.y + y].isP1Piece()){
								Integer i = p.x + x + x;
								Integer j = p.y + y + y;
								Boolean playable = false;
								
								while(i < width && i >= 0 && j < height && j >= 0 && playable == false){
									if(board[i][j].isP1Piece()){
										i += x;
										j += y;
									}
									else if(board[i][j].isP2Piece())
										playable = true;
									else if(board[i][j].isEmpty())
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
		
		return possiblePosition;
	}
	
	private void calculateTurnResult(Point position){
		if(board[position.x][position.y].isP1Piece()){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < width && (position.x + x) >= 0 && (position.y + y) < height && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(board[position.x + x][position.y + y].isP2Piece()){
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							while(i < width && i >= 0 && j < height && j >= 0){
								if(board[i][j].isP2Piece()){
									board[i][j].turnPiece();
									System.out.println("Point : " + i + " " + j + " . ");
									this.blackPiece.remove(new Point(i,j)); // PROBLEME LA
									System.out.println("List : " + this.blackPiece.toString());
									this.whitePiece.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (board[i][j].isP1Piece())
									break;
							}
								
						}
					}
				}
			}
			
		}
		else if(board[position.x][position.y].isP2Piece()){
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < width && (position.x + x) >= 0 && (position.y + y) < height && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(board[position.x + x][position.y + y].isP2Piece()){
							Integer i = position.x + x + x;
							Integer j = position.y + y + y;
							while(i < width && i >= 0 && j < height && j >= 0){
								if(board[i][j].isP1Piece()){
									board[i][j].turnPiece();
									System.out.println("Point : " + i + " " + j + " . ");
									this.whitePiece.remove(new Point(i,j)); // PROBLEME LA
									System.out.println("List : " + this.whitePiece.toString());
									this.blackPiece.add(new Point(i,j));
									i += x;
									j += y;
								}
								else if (board[i][j].isP2Piece())
									break;
							}
								
						}
					}
				}
			}
		}
	}

	@Override
	public void chosenPosition(Point pos, Integer player) {
		if(player == 1){
			this.whitePiece.add(pos);
			this.board[pos.x][pos.y].putP1Piece();
		}
		else{
			this.blackPiece.add(pos);
			this.board[pos.x][pos.y].putP2Piece();
		}
		calculateTurnResult(pos);
		System.out.println("List white " + whitePiece.toString());
		System.out.println("List black " + blackPiece.toString());
		this.borderLine = calculateBorderLine();
		this.printBoard();
	}
	
	public void printBoard(){
		System.out.println("AI board");
		for(int i = 0; i < this.width; i++)
			System.out.print(" _");
		for(int j = 0; j < this.height; j++){
			System.out.println("");
			System.out.print("|");
			for(int i = 0; i < this.width; i++){
				if(board[i][j].isP1Piece())
					System.out.print("1|");
				else if(board[i][j].isP2Piece())
					System.out.print("2|");
				else
					System.out.print(" |");
			}
			System.out.println("");
			for(int i = 0; i < this.width; i++)
				System.out.print(" _");
		}
			
				
	}

	@Override
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		// TODO Auto-generated method stub
		return null;
	}

}*/
