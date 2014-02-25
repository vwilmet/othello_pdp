package Model;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class BlackPiece extends Piece {

	public BlackPiece(int width, int height, int posX, int posY) {
		super(width, height, posX, posY);
		// TODO Auto-generated constructor stub
	}
	
	public BlackPiece(BlackPiece p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		String res = super.toString();
		res += "Ce pion est noir.\n";
		return res;
	}
}
