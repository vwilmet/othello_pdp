package com.controller;

import javax.swing.JFormattedTextField;

import utils.FactoryHandlerException;
import utils.TextManager;
import jnt.BenchMarkResult;

import com.controller.interfaces.NotifyGameController;
import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.view.BenchMarkViewImpl;
import com.view.InitGameViewImpl;
import com.view.event.BenchMarkViewButtonEventListener;
import com.view.event.InitGameButtonEventListener;
import com.view.interfaces.BenchMarkView;
import com.view.interfaces.InitGameView;

public class InitGameController implements InitGameButtonEventListener, BenchMarkViewButtonEventListener{

	private InitGameView view;
	private NotifyGameController event;
	private BenchMarkView benchMark;
	private static InitGameController instance;

	public static InitGameController getInstance(NotifyGameController event){
		if(instance == null)
			instance = new InitGameController(event);
		return instance;
	}

	private InitGameController(NotifyGameController event) {
		this.event = event;
		this.benchMark = new BenchMarkViewImpl();
		this.view = new InitGameViewImpl();
		this.view.setButtonListener(this);
	}

	public void showView(){
		this.view.showFrame();
	}
	
	@Override
	public void onValidButtonPressed(int row, int ligne, int IATime,
			int IADifficulty, String player1Name, String player2Name) {

		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();
		GameSettings gameSetts = null;
		BoardObservable board = null;
		
		
		
		try {
			board = bFacto.getInitialBoard(row, ligne);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			gameSetts = gsFacto.getGameSettings(
					pFacto.getHumanPlayer(player1Name, TextManager.WHITE_PLAYER), 
					pFacto.getMachinePlayer(player2Name, TextManager.BLACK_PLAYER),
					board,
					IATime,
					IADifficulty);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		this.event.initGameFinished(true, gameSetts);
		this.view.hideFrame();
	}

	@Override
	public void onCancelButtonPressed() {
		this.view.hideFrame();
	}

	@Override
	public void onBenchMarkButtonPressed(JFormattedTextField AITime) {
		this.benchMark.showFrame();
		this.benchMark.launchBenchMark();	
	}

	@Override
	public void onOkButtonPressed(BenchMarkResult result) {
		this.benchMark.hideFrame();
	}
}