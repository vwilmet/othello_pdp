package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.model.view.ViewSettings;
import com.view.event.ButtonImageMenuEventListener;


/**
 * Classe permettant de gérer le comportement du bouton "HelpIAButton"
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class HelpIAButton extends ImageButton implements MouseListener {

	/**
	 * Constructeur qui réutilise le constructeur de la classe mère {@link com.view.button.ImageButton}
	 */
	public HelpIAButton() {
		super(ViewSettings.HELP_IA_BUTTON_IMAGE, ViewSettings.HELP_IA_BUTTON_CODE, ViewSettings.HELP_IA_BUTTON_TEXT);
	}

	/**
	 * @see {@link com.view.button.ImageButton#setMouseListener}
	 */
	@Override
	public void setMouseListener(ButtonImageMenuEventListener event){
		this.listener = event;
		this.addMouseListener(this);
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.listener != null)
		{
			listener.onButtonCliked(this, code);
			super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
			listener.onHelpIAButtonCliked();
		}
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		super.setImage(ViewSettings.HELP_IA_BUTTON_CLIKED_IMAGE);
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
	}
	/**
	 * @see {@link com.view.button.ImageButton#enableButton}
	 */
	@Override
	public void enableButton(boolean enable) {
		this.setEnabled(enable);
		if(enable)
			super.setImage(ViewSettings.HELP_IA_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.HELP_IA_BUTTON_DISABLE_IMAGE);
	}

}
