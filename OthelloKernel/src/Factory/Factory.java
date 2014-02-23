package Factory;

import Controller.GameSettingsController;
import Model.Board;
import Model.GameSettings;
import Model.Piece;
import Model.Player;
import Model.RestoreGame;
import Model.SaveGame;

/**
 * Fabrique du module OthelloKernel.
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public class Factory {
	
	// Model Factory
	public Piece getPiece(String pieceType){
		return null;
	}
	
	public Player getPlayer(String playerType, String playerName){
		return null;
	}
	
	public Board getBoard(){
		return new Board();
	}
	
	public GameSettings getGameSettings(){
		return new GameSettings();
	}
	
	public SaveGame getSaveGame(){
		return new SaveGame();
	}
	
	public RestoreGame getRestoreGame(){
		return new RestoreGame();
	}
	
	// Controller Factory
	public GameSettingsController getGameSettingsController(){
		return new GameSettingsController();
	}
	
	//View Factory
	
}
