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
public class ForwardButton extends ImageButton implements MouseListener {

	public ForwardButton() {
		super(ViewSettings.FORWARD_BUTTON_IMAGE, ViewSettings.FORWARD_BUTTON_CODE,
				ViewSettings.FORWARD_BUTTON_TEXT);
	}

	@Override
	public ImageButton setMouseListener(ButtonImageMenuEventListener event){
		this.listener = event;
		this.addMouseListener(this);
		return this;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(this.listener != null)
		{
			listener.onButtonCliked(this, code);
			super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);
			listener.onForwardButtonCliked();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

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
