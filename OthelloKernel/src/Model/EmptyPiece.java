package Model;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class EmptyPiece extends Piece {

	public EmptyPiece(int width, int height, int posX, int posY) {
		super(width, height, posX, posY);
		// TODO Auto-generated constructor stub
	}
	
	public EmptyPiece(EmptyPiece p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		String res = super.toString();
		res += "Ce pion est vide.\n";
		return res;
	}
}
