import java.awt.Point;
import java.util.HashSet;
import java.util.List;

import com.aistrategy.ArtificialIntelligenceStrategy;
import com.aistrategy.impl.BruteForceAI;
import com.aistrategy.impl.NextBestMoveAI;
import com.aistrategy.impl.RandomAI;
import com.utils.WrongPlayablePositionException;

public class Model {

	public Board myBoard;
	public ArtificialIntelligenceStrategy myAI;
	public ArtificialIntelligenceStrategy myAI2;
	public Integer width;
	public Integer height;

	public Model() throws WrongPlayablePositionException
	{
		myBoard = new Board();
		myAI = new BruteForceAI();
		myAI2 = new NextBestMoveAI();
		myAI.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);
		myAI2.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);
		this.width = myBoard.width;
		this.height = myBoard.height;
		Point p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			myAI2.notifyChosenMove(p, 2);
		}
		

	}

	public Model(Integer width, Integer height)
	{
		myBoard = new Board(width, height);
		myAI = new BruteForceAI();
		this.width = myBoard.width;
		this.height = myBoard.height;
		myAI.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);
		
	}



	private void calculateTurnResult(Point position, Integer player){
		if(player == 1){
			this.myBoard.myGrid[position.x][position.y].putP1Piece();
			this.myBoard.addWhitePiece(position);
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < width && (position.x + x) >= 0 && (position.y + y) < height && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(this.myBoard.myGrid[position.x + x][position.y + y].isP2Piece()){
							Integer i = position.x + x ;
							Integer j = position.y + y ;
							while(i < width && i >= 0 && j < height && j >= 0){
								System.out.println("pos.x + x : " + i + " pos.y + y: " + j);
								if(this.myBoard.myGrid[i][j].isP2Piece()){
									this.myBoard.myGrid[i][j].turnPiece();
									i += x;
									j += y;
								}
								else if (this.myBoard.myGrid[i][j].isP1Piece())
									break;
							}

						}
					}
				}
			}

		}
		else if(player == 2){
			this.myBoard.addBlackPiece(position);
			this.myBoard.myGrid[position.x][position.y].putP2Piece();
			for(int x = - 1; x < 2; x++){
				for(int y = -1; y < 2; y++){
					if((position.x+x) < width && (position.x + x) >= 0 && (position.y + y) < height && (position.y+ y) >= 0 && !(x == 0 && y == 0)){
						if(this.myBoard.myGrid[position.x + x][position.y + y].isP2Piece()){
							Integer i = position.x + x ;
							Integer j = position.y + y ;
							while(i < width && i >= 0 && j < height && j >= 0){
								if(this.myBoard.myGrid[i][j].isP1Piece()){
									this.myBoard.myGrid[i][j].turnPiece();
									i += x;
									j += y;
								}
								else if (this.myBoard.myGrid[i][j].isP2Piece())
									break;
							}

						}
					}
				}
			}
		}
		//myBoard.printBoard();

	}




	public void printBoard(){
		myBoard.printBoard();
	}
}
