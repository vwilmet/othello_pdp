package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.io.SaveGame;

public interface SaveGameFactory {
	public SaveGame getSaveGame() throws FactoryHandlerException;

}
