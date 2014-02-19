package AI;

public interface Box {

	public abstract void removePiece();
	
	public abstract void putP1Piece();
	
	public abstract void putP2Piece();
	
	public abstract void turnPiece();
	
	public abstract boolean isEmpty();
	
	public abstract boolean isP1Piece();
	
	public abstract boolean isP2Piece();
	
	public abstract void setState(BoxState bs);
	
	public abstract BoxState getState();

	boolean isPlayer(Integer player);
	
}