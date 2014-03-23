package com.view.interfaces;

import com.view.event.ChoosePositionButtonEventListener;

public interface ChoosePositionView {

	public void setButtonListener(ChoosePositionButtonEventListener event);
	public void showFrame();
	public void hideFrame();
	public void showPlayablePiece(boolean visible);
}
