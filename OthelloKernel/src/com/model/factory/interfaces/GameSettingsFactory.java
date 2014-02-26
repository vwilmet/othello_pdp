package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.GameSettings;

public interface GameSettingsFactory {
	public GameSettings getGameSettings() throws FactoryHandlerException;
}
