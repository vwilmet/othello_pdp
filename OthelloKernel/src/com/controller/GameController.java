package com.controller;

import com.controller.interfaces.NotifyGameController;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.view.GameViewImpl;
import com.view.button.ImageButton;
import com.view.event.ButtonImageMenuEventListener;
import com.view.event.GameCanvasMouseEventListener;
import com.view.event.GameViewMenuEventListener;
import com.view.interfaces.GameView;

public class GameController implements NotifyGameController, GameCanvasMouseEventListener, ButtonImageMenuEventListener, GameViewMenuEventListener{

	private GameCanvasMouseEventListener mouseEvent;
	private InitGameController initGameController;
	private GameView gameView;
	private GameSettings gameSettings;

	public GameController() {
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		BoardObservable board = bFacto.getInitialBoard(8,8);
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		GameSettings gameSetts = gsFacto.getGameSettings(player1, player2, board, artificialIntelligenceThinkingTime, artificialIntelligenceDifficulty)
		
		this.gameView = new GameViewImpl(this.board, this);
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