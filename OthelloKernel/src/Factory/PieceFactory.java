package Factory;

import Model.BlackPiece;
import Model.EmptyPiece;
import Model.Piece;
import Model.WhitePiece;

/**
 * Fabrique d'un pion.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class PieceFactory extends Factory {

	@Override
	public Piece getPiece(String pieceType, int width, int height, int posX, int posY) {
		if (pieceType == null)
			return null;

		if (pieceType.equalsIgnoreCase("white"))
			return new WhitePiece(width, height, posX, posY);
		else if (pieceType.equalsIgnoreCase("black"))
<<<<<<< HEAD
				return new BlackPiece(width, height, posX, posY);
		else if (pieceType.equalsIgnoreCase("empty"))
			return new EmptyPiece(width, height, posX, posY);
		
=======
			return new BlackPiece();
		else if (pieceType.equalsIgnoreCase("empty"))
			return new EmptyPiece();

>>>>>>> 705c8c68ac8f3fb0626fcdb1c0651636eb0c4f9b
		return null;
	}
}