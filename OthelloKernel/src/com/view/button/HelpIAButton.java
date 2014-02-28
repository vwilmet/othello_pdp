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
public class HelpIAButton extends ImageButton implements MouseListener {

	public HelpIAButton() {
		super(ViewSettings.HELP_IA_BUTTON_IMAGE, ViewSettings.HELP_IA_BUTTON_CODE, ViewSettings.HELP_IA_BUTTON_TEXT);
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
			super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
			listener.onHelpIAButtonCliked();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.setImage(ViewSettings.HELP_IA_BUTTON_CLIKED_IMAGE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
	}

}
