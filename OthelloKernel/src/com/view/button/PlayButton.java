package com.view.button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.model.view.ViewSettings;
import com.view.event.ButtonImageMenuEventListener;


/**
 * Classe permettant de gérer le comportement du bouton "PlayButton"
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class PlayButton extends ImageButton implements MouseListener {

	/**
	 * Booléen qui permet de savoir si le bouton est en mode "Pause" ou "Play"
	 */
	private boolean onPlay;
	/**
	 * Constructeur qui réutilise le constructeur de la classe mère {@link com.view.button.ImageButton}
	 */
	public PlayButton() {
		super(ViewSettings.PAUSE_BUTTON_IMAGE, ViewSettings.PLAY_BUTTON_CODE, ViewSettings.PLAY_BUTTON_TEXT);
		onPlay = true;
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

			if (onPlay) {
				onPlay = false;
				super.setImage(ViewSettings.PLAY_BUTTON_IMAGE);
				listener.onPauseButtonCliked();
			} else {
				onPlay = true;
				super.setImage(ViewSettings.PAUSE_BUTTON_IMAGE);
				listener.onPlayButtonCliked();
			}
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
		if (onPlay)
			super.setImage(ViewSettings.PAUSE_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.PLAY_BUTTON_IMAGE);
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (onPlay)
			super.setImage(ViewSettings.PAUSE_BUTTON_CLIKED_IMAGE);
		else
			super.setImage(ViewSettings.PLAY_BUTTON_CLIKED_IMAGE);
	}
	/**
	 * Evènement soulevé lors d'une action souris
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (onPlay)
			super.setImage(ViewSettings.PAUSE_BUTTON_IMAGE);
		else
			super.setImage(ViewSettings.PLAY_BUTTON_IMAGE);
	}
	/**
	 * @see {@link com.view.button.ImageButton#enableButton}
	 */
	@Override
	public void enableButton(boolean enable) {
		this.setEnabled(enable);
	}

}
