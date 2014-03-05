package com.board;

public class BoxImpl implements Box {

	private BoxState state;
	
	public BoxImpl()
	{
		state = new BoxEmpty();
	}
	
	
	@Override
	public void removePiece() {
		state.removePiece(this);

	}

	@Override
	public void putP1Piece() {
		state.putP1Piece(this);
	}

	@Override
	public void putP2Piece() {
		state.putP2Piece(this);
	}

	@Override
	public void turnPiece() {
		state.turnPiece(this);
	}

	@Override
	public boolean isEmpty() {
		return state.isEmpty();
	}

	@Override
	public boolean isP1Piece() {
		return state.isP1Piece();
	}

	@Override
	public boolean isP2Piece() {
		return state.isP2Piece();
	}
	
	@Override
	public boolean isPlayer(Integer player) {
		boolean result = false;
		if(player == 1)
			result = state.isP1Piece();
		else if (player == 2)
			result = state.isP2Piece();
		return result;
			
	}

	@Override
	public void setState(final BoxState newState) {
		state = newState;
	}

	@Override
	public BoxState getState() {
		return this.state;
	}

}
