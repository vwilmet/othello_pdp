package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.Board;

public interface BoardFactory {
	public Board getBoard(int width, int height, int sizeX, int sizeY) throws FactoryHandlerException;
}
