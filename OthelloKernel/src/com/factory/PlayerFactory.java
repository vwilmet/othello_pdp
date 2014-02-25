package com.factory;

import java.awt.Color;

import com.model.HumanPlayer;
import com.model.MachinePlayer;
import com.model.Piece;
import com.model.Player;

/**
 * Fabrique d'un joueur.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class PlayerFactory extends Factory  {

	
	
	@Override
	public Player getPlayer(String playerType, String playerLogin, Color c) {
		if (playerType == null)
			return null;
		
		if (playerType.equalsIgnoreCase("human"))
			return new HumanPlayer(playerLogin, c);
		else if (playerType.equalsIgnoreCase("machine"))
			return new MachinePlayer(playerLogin, c);
		
		return null;
	}

	@Override
	public Piece getPiece(String pieceType, int width, int height, int posX,
			int posY) {
		// TODO Auto-generated method stub
		return null;
	}

}
