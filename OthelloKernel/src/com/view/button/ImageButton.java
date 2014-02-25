package com.view.button;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import utils.TextManager;
import utils.ViewSettings;

import com.error_manager.Log;

public class ImageButton extends JButton {

	protected String imageName;
	protected ButtonEventListener listener;
	protected Image image, imageCliked;
	protected int code;

	public ImageButton(String imageName, ButtonEventListener event, int code, String text) {
		this.code = code;
		this.imageName = imageName;
		this.listener = event;
		this.setToolTipText(text);
		
		this.setImage(imageName);
	}

	protected void setImage(String imageName){
		
		try {
			image = ImageIO.read(new File(ViewSettings.IMAGE_BUTTON_PATH + imageName));
			this.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			Log.error(TextManager.IO_EXCEPTION_ERROR);
		}
	}
}







