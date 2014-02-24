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
	public Piece getPiece(String pieceType) {
		if (pieceType == null)
			return null;

		if (pieceType.equalsIgnoreCase("white"))
			return new WhitePiece();
		else if (pieceType.equalsIgnoreCase("black"))
			return new BlackPiece();
		else if (pieceType.equalsIgnoreCase("empty"))
			return new EmptyPiece();

		return null;
	}
}