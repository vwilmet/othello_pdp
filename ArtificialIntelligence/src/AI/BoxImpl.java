package AI;

public class BoxImpl implements Box {

	private BoxState myState;
	
	public BoxImpl()
	{
		myState = new BoxEmpty();
	}
	
	
	@Override
	public void removePiece() {
		myState.removePiece(this);

	}

	@Override
	public void putP1Piece() {
		myState.putP1Piece(this);
	}

	@Override
	public void putP2Piece() {
		myState.putP2Piece(this);
	}

	@Override
	public void turnPiece() {
		myState.turnPiece(this);
	}

	@Override
	public boolean isEmpty() {
		return myState.isEmpty();
	}

	@Override
	public boolean isP1Piece() {
		return myState.isP1Piece();
	}

	@Override
	public boolean isP2Piece() {
		return myState.isP2Piece();
	}
	
	@Override
	public boolean isPlayer(Integer player) {
		boolean result = false;
		if(player == 1)
			result = myState.isP1Piece();
		else if (player == 2)
			result = myState.isP2Piece();
		return result;
			
	}

	@Override
	public void setState(final BoxState newState) {
		myState = newState;
	}

	@Override
	public BoxState getState() {
		return this.myState;
	}

}
