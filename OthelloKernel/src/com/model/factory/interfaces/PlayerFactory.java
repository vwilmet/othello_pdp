package com.model.factory.interfaces;

import java.awt.Color;

import utils.FactoryHandlerException;

import com.model.player.Player;


public interface PlayerFactory {
	public Player getHumanPlayer(String playerLogin, Color c) throws FactoryHandlerException;
	public Player getMachinePlayer(String playerLogin, Color c) throws FactoryHandlerException;
}
