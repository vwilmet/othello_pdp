import java.awt.Point;
import java.util.HashSet;
import java.util.List;

import com.ai.impl.ArtificialIntelligenceImpl;
import com.aistrategy.ArtificialIntelligenceStrategy;
import com.aistrategy.impl.BruteForceAI;
import com.aistrategy.impl.NextBestMoveAI;
import com.aistrategy.impl.RandomAI;
import com.utils.WrongPlayablePositionException;

public class Model {

	public Board myBoard;
	public ArtificialIntelligenceImpl myAI;
	public ArtificialIntelligenceImpl myAI2;


	public Model() throws WrongPlayablePositionException
	{
		myBoard = new Board();
		
		myAI = new ArtificialIntelligenceImpl();
		myAI2 = new ArtificialIntelligenceImpl();
		myAI.chooseDifficulty(2);
		myAI2.chooseDifficulty(0);
		myAI.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);
		myAI2.initialize(myAI);

		/*myAI = new BruteForceAI();
		myAI2 = new RandomAI();
		myAI.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);
		myAI2.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);*/

		Point p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}

	}

	public Model(Integer width, Integer height)
	{
	/*	myBoard = new Board(width, height);
		myAI = new BruteForceAI();
		this.width = myBoard.width;
		this.height = myBoard.height;
		myAI.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);*/
		
	}



}
