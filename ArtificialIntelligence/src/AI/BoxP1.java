package AI;

public class BoxP1 implements BoxState {

	@Override
	public void removePiece(Box b) {
		b.setState(new BoxEmpty());
	}

	@Override
	public void putP1Piece(Box b) {
		b.setState(new BoxP1());
	}

	@Override
	public void putP2Piece(Box b) {
		b.setState(new BoxP1());
	}

	@Override
	public void turnPiece(Box b) {
		b.setState(new BoxP2());
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isP1Piece() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isP2Piece() {
		// TODO Auto-generated method stub
		return false;
	}

}
