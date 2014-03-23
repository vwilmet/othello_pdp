package utils;

/**
 * Classe de gestion d'exceptions.
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameHandlerException extends Exception {

	public static final int ERROR = 0, WRONG_BOARD_SIZE = 1, WRONG_INITIAL_PIECE_COLOR = 2,
			WRONG_INITIAL_PIECE_POSITION = 3, ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE = 4,
			ERROR_DURING_THE_WRITE_OF_GAME_SAVE_FILE = 5, WARNING_ABOUT_NUMBER_OF_INITIAL_PIECES = 6,
			ERROR_WRONG_FORMAT_SAVE_GAME_FILE = 7, WRONG_PIECE_COLOR = 8;
	
	private int error;
	private String message;

	public GameHandlerException(int error) {
		this.error = error;
		this.message = "";
	}

	public GameHandlerException(int error, String message) {
		this.error = error;
		this.message = message;
	}

	public String getMessage() {
		switch (this.error) {
		case WRONG_BOARD_SIZE:
			return TextManager.WRONG_BOARD_SIZE_FR + this.message;
		case WRONG_INITIAL_PIECE_COLOR :
			return TextManager.WRONG_INITIAL_PIECE_COLOR_FR + this.message;
		case WRONG_INITIAL_PIECE_POSITION :
			return TextManager.WRONG_INITIAL_PIECE_POSITION_FR + this.message;
		case ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE : 
			return TextManager.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE_FR + this.message;
		case ERROR_DURING_THE_WRITE_OF_GAME_SAVE_FILE :
			return TextManager.ERROR_DURING_THE_WRITE_OF_GAME_SAVE_FILE_FR + this.message;
		case ERROR_WRONG_FORMAT_SAVE_GAME_FILE : 
			return TextManager.ERROR_WRONG_FORMAT_SAVE_GAME_FILE_FR + this.getMessage();
		case WRONG_PIECE_COLOR : 
			return TextManager.WRONG_PIECE_COLOR_FR + this.getMessage();
		case ERROR:
		default:
			return TextManager.ERROR_FR + this.message;
		}
	}
}
