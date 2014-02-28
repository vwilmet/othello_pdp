package com.view;

import com.view.event.GameViewMenuEventListener;

public interface GameView {
	public void showFrame();
	public void addMessageToMessageList(String element);
	public void changeStatViewMessage(String message);
	public void changeMessageViewContent(String content);
	public void setMenuListener(GameViewMenuEventListener event);
}
