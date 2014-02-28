package com.view.event;

import com.view.button.ImageButton;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public interface ButtonImageMenuEventListener {
	
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
