package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.controller.interfaces.NotifyGameController;
import com.model.Board;
import com.model.BoardObservable;
import com.view.ChoosePositionViewImpl;
import com.view.event.ChoosePositionButtonEventListener;
import com.view.interfaces.ChoosePositionView;

public class ChoosePositionController implements ChoosePositionButtonEventListener {

	private static ChoosePositionController instance;
	private NotifyGameController event;
	private ChoosePositionView view;
	
	public static ChoosePositionController getInstance(NotifyGameController event, int position, List<BoardObservable> boards){
		if(instance == null)
			instance = new ChoosePositionController(event, position, boards);
		return instance;
	}
	
	public ChoosePositionController(NotifyGameController event, int position, List<BoardObservable> boards) {
		this.event = event;
		this.view = new ChoosePositionViewImpl(position, (ArrayList<BoardObservable>)boards);
		this.view.setButtonListener(this);
	}

	public void showView(){
		this.view.showFrame();
	}
	
	@Override
	public void onValidButtonPressed(Board board, int position) {
		event.chooseGameBoardFinished(true, (BoardObservable)board, position);
		this.view.hideFrame();
	}

	@Override
	public void onCancelButtonPressed() {
		this.view.hideFrame();
	}
}
