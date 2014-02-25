package com.model.factory.interfaces;

import com.model.Board;

public interface BoardFactory {
	public Board getBoard(int width, int height, int sizeX, int sizeY);
}
