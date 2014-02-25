package com.model.factory.interfaces;

import java.awt.Color;

import com.model.player.Player;


public interface PlayerFactory {
	public Player getHumanPlayer(String playerLogin, Color c);
	public Player getMachinePlayer(String playerLogin, Color c);
}
