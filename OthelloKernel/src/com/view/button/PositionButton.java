package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import utils.ViewSettings;

public class PositionButton extends ImageButton implements MouseListener {

	public PositionButton(ButtonEventListener event) {
		super(ViewSettings.POSITION_BUTTON_IMAGE, event, ViewSettings.POSITION_BUTTON_CODE, ViewSettings.POSITION_BUTTON_TEXT);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.onButtonCliked(this, code);
		super.setImage(ViewSettings.POSITION_BUTTON_IMAGE);
		listener.onPositionButtonCliked();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {
		super.setImage(ViewSettings.POSITION_BUTTON_IMAGE);	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.setImage(ViewSettings.POSITION_BUTTON_CLIKED_IMAGE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.setImage(ViewSettings.POSITION_BUTTON_IMAGE);
	}

}
