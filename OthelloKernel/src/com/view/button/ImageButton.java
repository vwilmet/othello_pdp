package com.view.button;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import utils.TextManager;

import com.error_manager.Log;
import com.model.view.ViewSettings;
import com.view.event.ButtonImageMenuEventListener;

/**
 * Classe permettant de gérer le comportement des boutons du menu du jeu
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public abstract class ImageButton extends JButton {

	/**
	 * String qui va contenir le nom de l'image du bouton
	 */
	protected String imageName;
	/**
	 * Interface qui va permettre la communication sur le clique du bouton
	 */
	protected ButtonImageMenuEventListener listener;
	/**
	 * Images qui sont affichées pour le bouton dans l'état normale et cliqué
	 */
	protected Image image, imageCliked;
	/**
	 * Entier contenant le code associé à ce bouton
	 */
	protected int code;

	/**
	 * Constructeur qui va initialiser le bouton
	 * @param imageName Le chemin vers l'image du bouton
	 * @param code L'entier représentant le bouton
	 * @param text Le texte a afficher lorsque l'utilisateur laisse sa souris sur le bouton
	 */
	public ImageButton(String imageName, int code,
			String text) {
		this.code = code;
		this.imageName = imageName;
		this.setToolTipText(text);
		this.setImage(imageName);
	}
	
	/**
	 * Méthode permettant de définir l'interface de communication pour les cliques du bouton
	 * @param event L'interface
	 */
	public abstract void setMouseListener(ButtonImageMenuEventListener event);
	
	/**
	 * Cette méthode charge l'image du paramètre et l'affiche pour le bouton
	 * @param imageName Le chemin de l'image à charger
	 */
	protected void setImage(String imageName) {

		try {
			image = ImageIO.read(new File(ViewSettings.IMAGE_BUTTON_PATH
					+ imageName));
			this.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			Log.error(TextManager.IO_EXCEPTION_ERROR);
			e.printStackTrace();
		}
	}
	
	public abstract void enableButton(boolean enable);
}
