
public interface BoxState {

	public abstract void removePiece(Box b);
	
	public abstract void putP1Piece(Box b);
	
	public abstract void putP2Piece(Box b);
	
	public abstract void turnPiece(Box b);
	
	public abstract boolean isEmpty();
	
	public abstract boolean isP1Piece();
	
	public abstract boolean isP2Piece();
	
}
