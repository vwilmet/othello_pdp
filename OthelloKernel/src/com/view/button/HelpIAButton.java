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
public class HelpIAButton extends ImageButton implements MouseListener {

	public HelpIAButton(ButtonEventListener event) {
		super(ViewSettings.HELP_IA_BUTTON_IMAGE, event,
				ViewSettings.HELP_IA_BUTTON_CODE,
				ViewSettings.HELP_IA_BUTTON_TEXT);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.onButtonCliked(this, code);
		super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
		listener.onHelpIAButtonCliked();
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
