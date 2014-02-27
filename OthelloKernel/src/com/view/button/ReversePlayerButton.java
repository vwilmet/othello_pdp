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
public class ReversePlayerButton extends ImageButton implements MouseListener {

	public ReversePlayerButton(ButtonEventListener event) {
		super(ViewSettings.REVERSE_BUTTON_IMAGE, event,
				ViewSettings.REVERSE_BUTTON_CODE,
				ViewSettings.REVERSE_BUTTON_TEXT);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.onButtonCliked(this, code);
		super.setImage(ViewSettings.REVERSE_BUTTON_IMAGE);
		listener.onReversePlayerButtonCliked();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		super.setImage(ViewSettings.REVERSE_BUTTON_IMAGE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.setImage(ViewSettings.REVERSE_BUTTON_CLIKED_IMAGE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.setImage(ViewSettings.REVERSE_BUTTON_IMAGE);
	}

}
