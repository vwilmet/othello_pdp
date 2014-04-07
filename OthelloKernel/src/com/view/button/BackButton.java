package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.model.view.ViewSettings;
import com.view.event.ButtonImageMenuEventListener;

/**
 * Classe permettant de gérer le comportement du bouton "BackButton"
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class BackButton extends ImageButton implements MouseListener {

	/**
	 * Constructeur qui réutilise le constructeur de la classe mère {@link com.view.button.ImageButton}
	 */
	public BackButton() {
		super(ViewSettings.BACK_BUTTON_IMAGE, ViewSettings.BACK_BUTTON_CODE, ViewSettings.BACK_BUTTON_TEXT);
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
			super.setImage(ViewSettings.BACK_BUTTON_IMAGE);
			listener.onBackButtonCliked();
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
		super.setImage(ViewSettings.BACK_BUTTON_IMAGE);
	}
	
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		super.setImage(ViewSettings.BACK_BUTTON_CLIKED_IMAGE);
	}

	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		super.setImage(ViewSettings.BACK_BUTTON_IMAGE);

	}

	/**
	 * @see {@link com.view.button.ImageButton#enableButton}
	 */
	@Override
	public void enableButton(boolean enable) {
		this.setEnabled(enable);
		if(enable)
			super.setImage(ViewSettings.BACK_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.BACK_BUTTON_DISABLE_IMAGE);
	}
}
