package utils;


public class FactoryHandlerException extends Exception {
	
	public static final int ERROR = 0;
	public static final int WRONG_FACTORY_REFERRED = 1;

	public static final String ERROR_FR = "Une erreur est survenue dans la fabrique. ";
	public static final String WRONG_FACTORY_REFERRED_FR = "Vous avez instancié la mauvaise fabrique. ";
	
	public static final String PIECE_FACTORY_REQUIRED_FR = "Une fabrique de \"Piece\" est demandé. ";
	public static final String PLAYER_FACTORY_REQUIRED_FR = "Une fabrique de \"Player\" est demandé. ";
	public static final String BOARD_FACTORY_REQUIRED_FR = "Une fabrique de \"Board\" est demandé. ";
	public static final String GAME_SETTINGS_FACTORY_REQUIRED_FR = "Une fabrique de \"GameSettings\" est demandé. ";
	public static final String SAVE_GAME_FACTORY_REQUIRED_FR = "Une fabrique de \"SaveGame\" est demandé. ";
	public static final String RESTORE_GAME_FACTORY_REQUIRED_FR = "Une fabrique de \"RestoreGame\" est demandé. ";
	
	private int error;
	private String message;
	
	public FactoryHandlerException (int error){
		this.error = error;
		this.message = "";
	}
	
	public FactoryHandlerException (int error, String message){
		this.error = error;
		this.message = message;
	}
	
	public int getError (){
		return this.error;
	}
	
	public String getMessage(){
		switch (this.error){
		case WRONG_FACTORY_REFERRED :
			return FactoryHandlerException.WRONG_FACTORY_REFERRED_FR + this.message;
		case ERROR :
		default :
			return FactoryHandlerException.ERROR_FR + this.message;
		}
	}
}
