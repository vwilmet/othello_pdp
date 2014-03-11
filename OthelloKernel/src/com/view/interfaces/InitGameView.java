package com.view.interfaces;

import com.view.event.InitGameButtonEventListener;

public interface InitGameView {
	public void setButtonListener(InitGameButtonEventListener event);
	public void showFrame();
	public void hideFrame();
}