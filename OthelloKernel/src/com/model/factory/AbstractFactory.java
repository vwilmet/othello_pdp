package com.model.factory;

import java.awt.Color;

import com.controller.GameSettingsController;
import com.model.Board;
import com.model.GameSettings;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.player.Player;

/**
 * Fabrique du module OthelloKernel.
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public abstract class AbstractFactory implements PieceFactory, PlayerFactory, BoardFactory{
	public abstract Piece getPiece(String pieceType, int width, int height, int posX, int posY);
	public abstract Player getPlayer(String playerType, String playerName, Color c);
	public abstract Board getBoard(int width, int height, int sizeX, int sizeY);
	public abstract GameSettings getGameSettings();
	public abstract SaveGame getSaveGame();
	public abstract RestoreGame getRestoreGame();
}
