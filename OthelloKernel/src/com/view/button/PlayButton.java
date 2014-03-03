package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.model.view.ViewSettings;
import com.view.event.ButtonImageMenuEventListener;


/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class PlayButton extends ImageButton implements MouseListener {

	private boolean onPlay;

	public PlayButton() {
		super(ViewSettings.PAUSE_BUTTON_IMAGE, ViewSettings.PLAY_BUTTON_CODE, ViewSettings.PLAY_BUTTON_TEXT);
		onPlay = true;
	}

	@Override
	public ImageButton setMouseListener(ButtonImageMenuEventListener event){
		this.listener = event;
		this.addMouseListener(this);
		return this;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.listener != null)
		{
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
