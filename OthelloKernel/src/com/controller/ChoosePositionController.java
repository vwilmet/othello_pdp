package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.controller.interfaces.NotifyGameControllerGraphical;
import com.model.Board;
import com.model.BoardObservable;
import com.view.ChoosePositionViewImpl;
import com.view.event.ChoosePositionButtonEventListener;
import com.view.interfaces.ChoosePositionView;

public class ChoosePositionController implements ChoosePositionButtonEventListener {

	private static ChoosePositionController instance;
	private NotifyGameControllerGraphical event;
	private ChoosePositionView view;
	private int position;
	
	public static ChoosePositionController getInstance(){
		if(instance == null)
			instance = new ChoosePositionController();
		return instance;
	}
	
	private ChoosePositionController() {}

	public void setEvent(NotifyGameControllerGraphical event){
		this.event = event;
	}
	
	public void setHistory(int position, List<BoardObservable> boards){
		this.position = position;
		this.view = new ChoosePositionViewImpl(position, (ArrayList<BoardObservable>)boards);
		this.view.setButtonListener(this);
		this.view.showPlayablePiece(false);
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
