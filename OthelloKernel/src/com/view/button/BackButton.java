package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import utils.ViewSettings;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class BackButton extends ImageButton implements MouseListener {

	public BackButton(ButtonEventListener event) {
		super(ViewSettings.BACK_BUTTON_IMAGE, event,
				ViewSettings.BACK_BUTTON_CODE, ViewSettings.BACK_BUTTON_TEXT);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.onButtonCliked(this, code);
		super.setImage(ViewSettings.BACK_BUTTON_IMAGE);
		listener.onBackButtonCliked();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		super.setImage(ViewSettings.BACK_BUTTON_IMAGE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.setImage(ViewSettings.BACK_BUTTON_CLIKED_IMAGE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.setImage(ViewSettings.BACK_BUTTON_IMAGE);

	}

}
