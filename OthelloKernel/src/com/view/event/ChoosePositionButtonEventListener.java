package com.view.event;

import com.model.Board;

public interface ChoosePositionButtonEventListener {
	public void onValidButtonPressed(Board board);
	public void onCancelButtonPressed();
}
