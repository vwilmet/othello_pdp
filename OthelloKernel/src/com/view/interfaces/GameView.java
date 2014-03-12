package com.view.interfaces;

import com.model.BoardObservable;
import com.view.event.GameCanvasMouseEventListener;
import com.view.event.GameViewMenuEventListener;

public interface GameView {
	public void showFrame();
	public void hideFrame();

	public void setBoard(BoardObservable board);
	public void setGameMouseEventListener(GameCanvasMouseEventListener mouseEvent);
	
	public void setOnPause(boolean onPause);
	
	public void addMessageToMessageList(String element);
	public void changeStatViewMessage(String message);
	public void changeMessageViewContent(String content);
	public void setMenuListener(GameViewMenuEventListener event);
}
