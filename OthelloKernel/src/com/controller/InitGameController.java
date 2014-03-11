package com.controller;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import jnt.BenchMarkResult;
import utils.FactoryHandlerException;
import utils.TextManager;

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
		
		if(this.verifyFields(row, ligne, IATime)){
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
	}
	
	private boolean verifyFields(int row, int ligne, int IATime){
		boolean result = true;
		String message = "";
		
		if(row > GameSettings.BOARD_MAX_SIZE_X || row < GameSettings.BOARD_MIN_SIZE_X){
			result = false;
			message += TextManager.ERROR_INIT_ROW_OUT_OF_LIMIT + "\n";
		}
		
		if(ligne > GameSettings.BOARD_MAX_SIZE_Y || ligne < GameSettings.BOARD_MIN_SIZE_Y){
			result = false;
			message += TextManager.ERROR_INIT_LIGNE_OUT_OF_LIMIT + "\n";
		}
		
		if(IATime > GameSettings.AI_THINKING_TIME_LIMIT_MAX || IATime < GameSettings.AI_THINKING_TIME_LIMIT_MIN){
			result = false;
			message += TextManager.ERROR_INIT_IA_THINKING_TIME_OUT_OF_LIMIT + "\n";
		}
		
		if(message.length() > 0)
			JOptionPane.showMessageDialog((InitGameViewImpl)this.view, message, TextManager.ERROR_INIT_TITLE_POPUP, JOptionPane.INFORMATION_MESSAGE);
		
		return result;
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