package com.factory;

import java.awt.Color;

import com.controller.GameSettingsController;
import com.model.Board;
import com.model.GameSettings;
import com.model.Piece;
import com.model.Player;
import com.model.RestoreGame;
import com.model.SaveGame;

/**
 * Fabrique du module OthelloKernel.
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public abstract class Factory implements PieceFactory, PlayerFactory{
	
	// Model Factory
	public abstract Piece getPiece(String pieceType, int width, int height, int posX, int posY);
	public abstract Player getPlayer(String playerType, String playerName, Color c);
	public abstract Board getBoard();
	public abstract GameSettings getGameSettings();
	public abstract SaveGame getSaveGame();
	public abstract RestoreGame getRestoreGame();
	
	// Controller Factory
	public abstract GameSettingsController getGameSettingsController();
	
	//View Factory
	
}
