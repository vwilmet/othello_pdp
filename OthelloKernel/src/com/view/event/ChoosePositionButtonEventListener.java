package com.view.event;

import com.model.Board;

public interface ChoosePositionButtonEventListener {
	public void onValidButtonPressed(Board board, int position);
	public void onCancelButtonPressed();
}
