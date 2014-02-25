package com.factory;
import java.awt.Color;

import com.model.Player;


public interface PlayerInter {

	public Player getPlayer(String playerType, String playerLogin, Color c);
	
}
