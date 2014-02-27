package AI;

import java.awt.Point;

public class WrongPlayablePositionException extends Exception {

	String message;
	
	public WrongPlayablePositionException(Point pos){
		this.message = new String("The position (" + pos.x + "," + pos.y + ") is not a playable position for the actual tree : synchronisation problem!");
	}
	
}
