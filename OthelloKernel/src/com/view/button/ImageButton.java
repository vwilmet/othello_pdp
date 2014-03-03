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
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public abstract class ImageButton extends JButton {

	protected String imageName;
	protected ButtonImageMenuEventListener listener;
	protected Image image, imageCliked;
	protected int code;

	public ImageButton(String imageName, int code,
			String text) {
		this.code = code;
		this.imageName = imageName;
		this.setToolTipText(text);
		this.setImage(imageName);
	}
	
	public abstract ImageButton setMouseListener(ButtonImageMenuEventListener event);
	
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
}
