package com.controller.interfaces;

import com.model.BoardObservable;
import com.model.GameSettings;

public interface NotifyGameController {
	public void initGameFinished(boolean valid, GameSettings game);
	public void chooseGameBoardFinished(boolean valid, BoardObservable board, int position);
}