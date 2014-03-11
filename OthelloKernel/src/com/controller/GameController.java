package com.controller;

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

	public GameController() {
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();
		
		BoardObservable board = null;
		try {
			//board = bFacto.getInitialBoard(8,8);
			RestoreGameFactory rgFacto = FactoryProducer.getRestoreGameFactory();
			
			RestoreGame rg = rgFacto.getRestoreGame("saveFilebis");
			
			rg.loadGameFromBackupFile();
			
			
			gameSettings = rg.getGameSettings();
			board = gameSettings.getGameBoard();
	
			
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
		this.gameView.showFrame();
	}

	private void initializeNewGame(){
		this.initGameController = InitGameController.getInstance(this);
		this.initGameController.showView();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void onPauseButtonCliked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onForwardButtonCliked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackButtonCliked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResetButtonCliked() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
		initializeNewGame();
	}

	@Override
	public void onSaveGameUnderItemMenuPressed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpenFileAndContinueItemMenuPressed() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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