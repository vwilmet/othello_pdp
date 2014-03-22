package com.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.FactoryHandlerException;
import utils.TextManager;

import com.controller.interfaces.NotifyGameController;
import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.RestoreGameFactory;
import com.model.io.RestoreGame;
import com.model.piece.Piece;
import com.view.GameViewImpl;
import com.view.button.ImageButton;
import com.view.event.ButtonImageMenuEventListener;
import com.view.event.GameCanvasMouseEventListener;
import com.view.event.GameViewMenuEventListener;
import com.view.interfaces.GameView;

public class GameControllerGraphical extends GameController implements NotifyGameController, GameCanvasMouseEventListener, ButtonImageMenuEventListener, GameViewMenuEventListener{
	
	protected GameView gameView;
	protected InitGameController initGameController;
	protected ChoosePositionController chooseGameBoardController;
	
	public GameControllerGraphical() {
		super();

		this.gameView = new GameViewImpl(this.gameSettings.getGameBoard());
		this.gameView.setImageButtonEventListener(this);
		this.gameView.setMenuListener(this);
		this.gameView.setGameMouseEventListener(this);
		this.gameView.showFrame();
		this.gameView.setIAAdvisedPiece(this.gameSettings.getGameBoard().getBoard()[0][0]);
	}
	
	protected void initializeNewGame(){
		this.initGameController = InitGameController.getInstance(this);
		this.initGameController.showView();
	}
	
	protected void chooseHistoryBoardPosition(){
		chooseGameBoardController = ChoosePositionController.getInstance(this, this.gameSettings.getHistoryPosition(), this.gameSettings.getGameBoardHistory());
		chooseGameBoardController.showView();
	}
	
	protected void loadFileForGame(){

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
	public void onLeftMouseButtonPressed(int i, int j) {
		if(i!=-1 && j != -1){
			
			for(Piece possiblePiece : this.gameSettings.getGameBoard().getPlayablePieces())
				if(possiblePiece.getPosX() == i && possiblePiece.getPosY() == j){
					this.gameSettings.setPiece(i, j);
					
					this.reverseInbetweenPieceAfterPlaying(i, j);
					this.gameSettings.changePlayer();
					this.setPlayablePiece();
					this.writeMessageToUser(this.gameSettings.getCurrentPlayer().toString());
					break;
				}
		}
	}
	
	@Override
	public void onRightMouseButtonPressed(int i, int j) {}

	protected void addMessageToListForUser(String message){
		this.gameView.addMessageToMessageList(message);
	}

	protected void writeStatMessage(String stat){
		this.gameView.changeStatViewMessage(stat);
	}

	protected void writeMessageToUser(String message){
		this.gameView.changeMessageViewContent(message);
	}

	@Override
	public void initGameFinished(boolean valid, GameSettings game) {

		if(valid){
			this.gameSettings = game;
			this.gameView.setBoard(this.gameSettings.getGameBoard());
			this.setPlayablePiece();
			this.addMessageToListForUser(TextManager.NEM_GAME_START_MESSAGE_LIST_VUE);
		}
	}

	@Override
	public void onButtonCliked(ImageButton button, int code) {}

	@Override
	public void onPlayButtonCliked() {
		this.gameView.setOnPause(false);
		this.addMessageToListForUser(TextManager.PLAY_MESSAGE_LIST_VUE + " : " + timer.getElapsedTimeInMinAndSeconde());
	}
	
	@Override
	public void onPauseButtonCliked() {
		this.gameView.setOnPause(true);
		this.addMessageToListForUser(TextManager.PAUSE_MESSAGE_LIST_VUE + " : " + timer.getElapsedTimeInMinAndSeconde());
	}
	
	@Override
	public void onForwardButtonCliked() {
		if(this.gameSettings.getForwardInHistory()){
			this.addMessageToListForUser(TextManager.FORWARD_PIECE_MESSAGE_LIST_VUE);
			this.gameView.setBoard(this.gameSettings.getGameBoard());
			this.setPlayablePiece();	
		}
	}
	
	@Override
	public void onBackButtonCliked() {
		if(this.gameSettings.getBackInHistory()){
			this.addMessageToListForUser(TextManager.BACK_PIECE_MESSAGE_LIST_VUE);
			this.gameView.setBoard(this.gameSettings.getGameBoard());
			this.setPlayablePiece();
		}
	}
	
	@Override
	public void onResetButtonCliked() {
		this.gameSettings.restartGame();
		this.gameView.setBoard(this.gameSettings.getGameBoard());
		this.addMessageToListForUser(TextManager.RESET_PIECE_MESSAGE_LIST_VUE);
		this.setPlayablePiece();
	}
	
	@Override
	public void onHelpIAButtonCliked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPositionButtonCliked() {
		chooseHistoryBoardPosition();
	}

	@Override
	public void onReversePlayerButtonCliked() {
		this.gameSettings.reversePlayer();
		this.addMessageToListForUser(TextManager.REVERSE_PLAYER_MESSAGE_LIST_VUE);
	}

	@Override
	public void onNewGameItemMenuPressed() {
		this.initializeNewGame();
	}

	@Override
	public void onSaveGameUnderItemMenuPressed() {
		
		JFileChooser chooser = new JFileChooser();
		int returnVal;
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichiers XML", "xml");
		chooser.setFileFilter(filter);
		
		returnVal = chooser.showOpenDialog((GameViewImpl)this.gameView);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +  chooser.getSelectedFile().getPath());
			this.saveCurrentBoard(chooser.getSelectedFile().getPath() + ".xml");
		}
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
//			java.awt.Desktop.getDesktop().browse(new URI(GameSettings.HELP_WEBSITE_PATH));
			java.awt.Desktop.getDesktop().browse(new File(GameSettings.HELP_WEBSITE_PATH).toURI());
		} catch (IOException  e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void chooseGameBoardFinished(boolean valid, BoardObservable board,
			int position) {
		
		this.gameSettings.setHistoryPosition(position);
		this.gameView.setBoard(this.gameSettings.getHistoryBoard(position));
		this.setPlayablePiece();
	}
	
	private void updateInformationField(){
		//tour du joueur actuelle
		
		//nombre de pion par joueur
		
		//Nombre de coup possible
		
		
	}
	
	
}
