package com.view;

import com.view.event.ChoosePositionButtonEventListener;

public interface ChoosePositionView {

	public void setButtonListener(ChoosePositionButtonEventListener event);
	public void showFrame();
	public void hideFrame();
}
