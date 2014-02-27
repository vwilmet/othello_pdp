package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.view.event.ButtonEventListener;

import utils.ViewSettings;

public class ForwardButton extends ImageButton implements MouseListener{

	public ForwardButton(ButtonEventListener event) {
		super(ViewSettings.FORWARD_BUTTON_IMAGE, event, ViewSettings.FORWARD_BUTTON_CODE, ViewSettings.FORWARD_BUTTON_TEXT);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		listener.onButtonCliked(this, code);
		super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);
		listener.onForwardButtonCliked();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {
		super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);	
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		super.setImage(ViewSettings.FORWARD_BUTTON_CLIKED_IMAGE);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);
	}

}
