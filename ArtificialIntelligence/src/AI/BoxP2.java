package AI;

public class BoxP2 implements BoxState {

	@Override
	public void removePiece(Box b) {
		b.setState(new BoxEmpty());
	}

	@Override
	public void putP1Piece(Box b) {
		b.setState(new BoxP2());
	}

	@Override
	public void putP2Piece(Box b) {
		b.setState(new BoxP2());
	}

	@Override
	public void turnPiece(Box b) {
		b.setState(new BoxP1());
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isP1Piece() {
		return false;
	}

	@Override
	public boolean isP2Piece() {
		return true;
	}

}