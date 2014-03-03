package com.controller;

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