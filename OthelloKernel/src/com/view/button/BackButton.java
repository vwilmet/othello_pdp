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
public class BackButton extends ImageButton implements MouseListener {

	public BackButton() {
		super(ViewSettings.BACK_BUTTON_IMAGE, ViewSettings.BACK_BUTTON_CODE, ViewSettings.BACK_BUTTON_TEXT);
	}

	@Override
	public void setMouseListener(ButtonImageMenuEventListener event){
		this.listener = event;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.listener != null)
		{
			listener.onButtonCliked(this, code);
			super.setImage(ViewSettings.BACK_BUTTON_IMAGE);
			listener.onBackButtonCliked();
		}
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

	@Override
	public void enableButton(boolean enable) {
		this.setEnabled(enable);
		if(enable)
			super.setImage(ViewSettings.BACK_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.BACK_BUTTON_DISABLE_IMAGE);
	}
}
