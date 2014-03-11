package com.controller;

import javax.swing.JFormattedTextField;

import jnt.BenchMarkResult;

import com.controller.interfaces.NotifyGameController;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.GameSettingsFactory;
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
			String IADifficulty, String player1Name, String player2Name) {
		this.view.hideFrame();
		
		//GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		//GameSettings gameSetts = gsFacto.getGameSettings(player1, player2, gameBoard, artificialIntelligenceThinkingTime, artificialIntelligenceDifficulty)

		this.event.initGameFinished(true, );
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