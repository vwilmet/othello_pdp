package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.view.event.ButtonEventListener;

import utils.ViewSettings;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class PlayButton extends ImageButton implements MouseListener {

	private boolean onPlay;

	public PlayButton(ButtonEventListener event) {
		super(ViewSettings.PAUSE_BUTTON_IMAGE, event,
				ViewSettings.PLAY_BUTTON_CODE, ViewSettings.PLAY_BUTTON_TEXT);
		this.addMouseListener(this);
		onPlay = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.onButtonCliked(this, code);

		if (onPlay) {
			onPlay = false;
			super.setImage(ViewSettings.PLAY_BUTTON_IMAGE);
			listener.onPauseButtonCliked();
		} else {
			onPlay = true;
			super.setImage(ViewSettings.PAUSE_BUTTON_IMAGE);
			listener.onPlayButtonCliked();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (onPlay)
			super.setImage(ViewSettings.PAUSE_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.PLAY_BUTTON_IMAGE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (onPlay)
			super.setImage(ViewSettings.PAUSE_BUTTON_CLIKED_IMAGE);
		else
			super.setImage(ViewSettings.PLAY_BUTTON_CLIKED_IMAGE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (onPlay)
			super.setImage(ViewSettings.PAUSE_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.PLAY_BUTTON_IMAGE);
	}

}
