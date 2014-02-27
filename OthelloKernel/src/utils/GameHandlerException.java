package utils;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class GameHandlerException extends Exception {

	public static final int ERROR = 0;
	public static final int WRONG_BOARD_SIZE = 1;
	public static final int WRONG_INITIAL_PIECE_COLOR = 2;
	public static final int WRONG_INITIAL_PIECE_POSITION = 3;

	public static final String ERROR_FR = "";
	public static final String WRONG_BOARD_SIZE_FR = "La taille de votre othellier est incorrecte, veuillez vous référer au manuel de jeu. ";
	public static final String WRONG_INITIAL_PIECE_COLOR_FR = "Vous ne pouvez ajouter qu'un pion réel à la liste des pions initiaux de votre jeu. ";
	public static final String WRONG_INITIAL_PIECE_POSITION_FR = "Vous ne pouvez pas ajouter deux pions sur un même endroit de l'othellier. ";

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
			return GameHandlerException.WRONG_BOARD_SIZE_FR + this.message;
		case WRONG_INITIAL_PIECE_COLOR:
			return GameHandlerException.WRONG_INITIAL_PIECE_COLOR_FR
					+ this.message;
		case ERROR:
		default:
			return GameHandlerException.ERROR_FR + this.message;
		}
	}
}
