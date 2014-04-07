package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.model.view.ViewSettings;
import com.view.event.ButtonImageMenuEventListener;


/**
 * Classe permettant de gérer le comportement du bouton "ForwardButton"
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class ForwardButton extends ImageButton implements MouseListener {
	/**
	 * Constructeur qui réutilise le constructeur de la classe mère {@link com.view.button.ImageButton}
	 */
	public ForwardButton() {
		super(ViewSettings.FORWARD_BUTTON_IMAGE, ViewSettings.FORWARD_BUTTON_CODE,
				ViewSettings.FORWARD_BUTTON_TEXT);
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
	public void mouseClicked(MouseEvent arg0) {
		if(this.listener != null)
		{
			listener.onButtonCliked(this, code);
			super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);
			listener.onForwardButtonCliked();
		}
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		super.setImage(ViewSettings.FORWARD_BUTTON_CLIKED_IMAGE);
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);
	}
	/**
	 * @see {@link com.view.button.ImageButton#enableButton}
	 */
	@Override
	public void enableButton(boolean enable) {
		this.setEnabled(enable);
		if(enable)
			super.setImage(ViewSettings.FORWARD_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.FORWARD_BUTTON_DISABLE_IMAGE);
	}

}
