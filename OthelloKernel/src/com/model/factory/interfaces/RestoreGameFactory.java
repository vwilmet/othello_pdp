package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.io.RestoreGame;

public interface RestoreGameFactory {
	public RestoreGame getRestoreGame() throws FactoryHandlerException;
}
