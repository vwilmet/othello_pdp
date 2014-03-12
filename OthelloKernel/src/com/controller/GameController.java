package com.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import com.model.factory.interfaces.RestoreGameFactory;
import com.model.io.RestoreGame;
import com.timermanager.TimerManager;
import com.timermanager.TimerManagerImpl;
import com.view.GameViewImpl;
import com.view.button.ImageButton;
import com.view.event.ButtonImageMenuEventListener;
import com.view.event.GameCanvasMouseEventListener;
import com.view.event.GameViewMenuEventListener;
import com.view.interfaces.GameView;

public class GameController implements NotifyGameController, GameCanvasMouseEventListener, ButtonImageMenuEventListener, GameViewMenuEventListener{

	private InitGameController initGameController;
	private GameView gameView;
	private GameSettings gameSettings;
	private TimerManager timer;

	public GameController() {
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();

		BoardObservable board = null;
		timer = new TimerManagerImpl();

		try {
			board = bFacto.getInitialBoard(8,8);



		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			this.gameSettings = gsFacto.getGameSettings(
					pFacto.getHumanPlayer("toto", TextManager.WHITE_PLAYER), 
					pFacto.getMachinePlayer("John DOE", TextManager.BLACK_PLAYER),
					board,
					GameSettings.DEFAULT_IA_THINKING_TIME, 
					GameSettings.DEFAULT_IA_DIFFICULTY);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		this.gameView = new GameViewImpl(this.gameSettings.getGameBoard(), this);
		this.gameView.setMenuListener(this);
		this.gameView.setGameMouseEventListener(this);
		this.gameView.showFrame();
		timer.startCountingElapsedTime();
	}

	private void initializeNewGame(){
		this.initGameController = InitGameController.getInstance(this);
		this.initGameController.showView();
	}

	private void loadFileForGame(){

		RestoreGameFactory rgFacto = FactoryProducer.getRestoreGameFactory();
		RestoreGame rg = null;
		int returnVal;

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichiers XML", "xml");
		chooser.setFileFilter(filter);

		returnVal = chooser.showOpenDialog((GameViewImpl)this.gameView);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +  chooser.getSelectedFile().getPath());

			try {
				rg = rgFacto.getRestoreGame(chooser.getSelectedFile().getPath());
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
			rg.loadGameFromBackupFile();

			gameSettings = rg.getGameSettings();
			this.gameView.setBoard(gameSettings.getGameBoard());
		}
	}

	@Override
	public void initGameFinished(boolean valid, GameSettings game) {

		if(valid){
			this.gameSettings = game;
			this.gameView.setBoard(this.gameSettings.getGameBoard());
		}
	}

	@Override
	public void onLeftMouseButtonPressed(int i, int j) {
		System.out.println("Left button Position x:y =>" + i + ":" + j);

		if(i!=-1 && j != -1){
			this.gameSettings.setPiece(i, j);

			this.gameSettings.changePlayer();
		}
	}

	@Override
	public void onRightMouseButtonPressed(int i, int j) {
		//	System.out.println("Right button Position x:y =>" + i + ":" + j);

	}

	@Override
	public void onButtonCliked(ImageButton button, int code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayButtonCliked() {
		this.gameView.setOnPause(false);
		this.addMessageToListForUser(TextManager.PLAY_MESSAGE_LIST_VUE + " : " + timer.getElepsedTimeInMinAndSeconde());
	}

	@Override
	public void onPauseButtonCliked() {
		this.gameView.setOnPause(true);
		this.addMessageToListForUser(TextManager.PAUSE_MESSAGE_LIST_VUE + " : " + timer.getElepsedTimeInMinAndSeconde());
	}

	@Override
	public void onForwardButtonCliked() {
		this.gameSettings.getForwardInHistory();
	}

	@Override
	public void onBackButtonCliked() {
		this.gameSettings.getBackInHistory();
	}

	@Override
	public void onResetButtonCliked() {
		this.gameSettings.resetHistoryAndRestartGame();
	}

	@Override
	public void onHelpIAButtonCliked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPositionButtonCliked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReversePlayerButtonCliked() {
		this.gameSettings.reversePlayer();
	}

	private void addMessageToListForUser(String message){
		this.gameView.addMessageToMessageList(message);
	}

	private void writeStatMessage(String stat){
		this.gameView.changeStatViewMessage(stat);
	}

	private void writeMessageToUser(String message){
		this.gameView.changeMessageViewContent(message);
	}

	@Override
	public void onNewGameItemMenuPressed() {
		this.initializeNewGame();
	}

	@Override
	public void onSaveGameUnderItemMenuPressed() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onOpenFileAndContinueItemMenuPressed() {
		this.loadFileForGame();
	}

	@Override
	public void onOpenFileAndChoosePositionItemMenuPressed() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onOpenPreConfFileItemMenuPressed() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onOptionItemMenuPressed() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onHelpItemMenuPressed() {
		try {
			java.awt.Desktop.getDesktop().browse(new URI(GameSettings.HELP_WEBSITE_PATH));
		} catch (IOException  e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}