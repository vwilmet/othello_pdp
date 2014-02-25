package com.view.button;

import com.view.button.ImageButton;

public interface ButtonEventListener {

	public void onButtonCliked(ImageButton button, int code);
	
	public void onPlayButtonCliked();

	public void onPauseButtonCliked();

	public void onForwardButtonCliked();
	
	public void onBackButtonCliked();
	
	public void onResetButtonCliked();
	
	public void onHelpIAButtonCliked();

	public void onPositionButtonCliked();

	public void onReversePlayerButtonCliked();
}
