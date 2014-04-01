import java.awt.Point;
import java.util.HashSet;

import com.ai.impl.ArtificialIntelligenceImpl;
import com.utils.WrongPlayablePositionException;

public class Model {

	public Board myBoard;
	public ArtificialIntelligenceImpl myAI;
	public ArtificialIntelligenceImpl myAI2;
	public ArtificialIntelligenceImpl myAI3;


	public Model() throws WrongPlayablePositionException
	{
		myBoard = new Board();
		myAI3 = new ArtificialIntelligenceImpl();
		myAI = new ArtificialIntelligenceImpl();
		myAI2 = new ArtificialIntelligenceImpl();
		myAI3.chooseDifficulty(2);
		myAI.chooseDifficulty(1);
		myAI2.chooseDifficulty(1);
		myAI3.setMaxTime(1000);
		myAI3.initialize(new HashSet<Point>(myBoard.whitePiece), new HashSet<Point>(myBoard.blackPiece), myBoard.width, myBoard.height);
		myAI2.initialize(myAI3);
		myAI.initialize(myAI3);

		System.out.println(myAI.boardToString());
		Point p = myAI.nextMove(1);
		System.out.println(p);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		System.out.println(p);
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}	
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		p = myAI2.nextMove(2);		
		if(p != null){
			myAI.notifyChosenMove(p, 2);
			//myAI2.notifyChosenMove(p, 2);
		}
		System.out.println(myAI.boardToString());
		p = myAI.nextMove(1);
		if(p != null){
			myAI.notifyChosenMove(p, 1);
			//myAI2.notifyChosenMove(p, 1);
		}
		System.out.println(myAI.boardToString());
		System.out.println("Fin");

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
