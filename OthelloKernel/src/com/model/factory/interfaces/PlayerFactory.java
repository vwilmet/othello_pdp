package com.model.factory.interfaces;
import java.awt.Color;

import com.model.player.Player;


public interface PlayerFactory {

	public Player getPlayer(String playerType, String playerLogin, Color c);
	
}
