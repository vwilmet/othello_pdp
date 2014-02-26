package utils;

public class GameHandlerException extends Exception {
	
	public static final int ERROR = 0;
	public static final int WRONG_BOARD_SIZE = 1;
	public static final int WRONG_INITIAL_PIECE_COLOR = 2;
	
	private int error;
	private String message;
	
	public GameHandlerException (int error){
		this.error = error;		
	}
	
	public GameHandlerException(int error, String message){
		this.error = error;
		this.message = message;
	}

}
