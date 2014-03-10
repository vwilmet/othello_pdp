import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Board {

	Box[][] myGrid;
	Integer width;
	Integer height;
	List<Point> whitePiece;
	List<Point> blackPiece;
	List<Box> borderLine;
	
	public Board(){
		this.width = 8;
		this.height = 8;
		borderLine = new ArrayList<Box>();
		this.whitePiece = new ArrayList<Point>();
		this.blackPiece = new ArrayList<Point>();
		// TO DO INITIALIZE borderLine
		myGrid = new BoxImpl[width][height];
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height ; j++)
				myGrid[i][j] = new BoxImpl();
		myGrid[width/2-1][height/2-1].putP1Piece();
		myGrid[width/2][height/2].putP1Piece();
		whitePiece.add(new Point(width/2-1,height/2-1));
		whitePiece.add(new Point(width/2,height/2));
		myGrid[width/2-1][height/2].putP2Piece();
		myGrid[width/2][height/2-1].putP2Piece();
		blackPiece.add(new Point(width/2-1,height/2));
		blackPiece.add(new Point(width/2,height/2-1));
		


	}
	
	public Board(Integer width, Integer height){
		this.width = width;
		this.height = height;
		borderLine = new ArrayList<Box>();
		this.whitePiece = new ArrayList<Point>();
		this.blackPiece = new ArrayList<Point>();
		myGrid = new BoxImpl[width][height];
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height ; j++)
				myGrid[i][j] = new BoxImpl();
		myGrid[width/2-1][height/2-1].putP1Piece();
		myGrid[width/2][height/2].putP1Piece();
		whitePiece.add(new Point(width/2-1,height/2-1));
		whitePiece.add(new Point(width/2,height/2));
		myGrid[width/2-1][height/2].putP2Piece();
		myGrid[width/2][height/2-1].putP2Piece();
		blackPiece.add(new Point(width/2-1,height/2));
		blackPiece.add(new Point(width/2,height/2-1));
	}
	
	public void addBlackPiece(Point pos){
		this.blackPiece.add(pos);
	}
	
	public void addWhitePiece(Point pos){
		this.whitePiece.add(pos);
	}
	
	
	public void printBoard(){
		for(int i = 0; i < this.width; i++)
			System.out.print(" _");
		for(int j = 0; j < this.height; j++){
			System.out.println("");
			System.out.print("|");
			for(int i = 0; i < this.width; i++){
				if(myGrid[i][j].isP1Piece())
					System.out.print("1|");
				else if(myGrid[i][j].isP2Piece())
					System.out.print("2|");
				else
					System.out.print(" |");
			}
			System.out.println("");
			for(int i = 0; i < this.width; i++)
				System.out.print(" _");
		}
			
				
	}
	
}
