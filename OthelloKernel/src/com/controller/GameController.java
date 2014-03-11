package com.controller;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.view.event.GameCanvasMouseEventListener;

public class GameController {

	private GameCanvasMouseEventListener mouseEvent;
	
	public GameController() {
		
		mouseEvent = new GameCanvasMouseEventListener() {
			
			@Override
			public void onRightMouseButtonPressed(int x, int y) {
				System.out.println("Right button Position x:y =>" + x + ":" + y);
			}
			
			@Override
			public void onLeftMouseButtonPressed(int x, int y) {
				System.out.println("Left button Position x:y =>" + x + ":" + y);
			}
		};
		
		
		
	}

}

/*
 * 
 * NE SUPRIME PAS : c'est le selecteur de fichier
 * 
 
JFileChooser chooser = new JFileChooser();
FileNameExtensionFilter filter = new FileNameExtensionFilter(
    "XML Files", "xml");
chooser.setFileFilter(filter);
int returnVal = chooser.showOpenDialog(this);
if(returnVal == JFileChooser.APPROVE_OPTION) {
   System.out.println("You chose to open this file: " +
        chooser.getSelectedFile().getName());
}
*/