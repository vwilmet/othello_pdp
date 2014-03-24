package com.controller;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.Application;
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
import com.utils.WrongPlayablePositionException;
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

		//this.gameView.setIAAdvisedPiece(this.gameSettings.getGameBoard().getBoard()[0][0]);

		this.updateInformationField();
		this.dealWithCurrentPlayer();
	}

	protected void initializeNewGame(){
		this.initGameController = InitGameController.getInstance(this);
		this.initGameController.showView();
	}

	protected void chooseHistoryBoardPosition(){
		chooseGameBoardController = ChoosePositionController.getInstance();
		chooseGameBoardController.setEvent(this);
		chooseGameBoardController.setHistory(this.gameSettings.getBoardHistoryPosition(), this.gameSettings.getGameBoardHistory());
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
			if(onPiecePlayed(i, j)){
				try {
					toto: lol hih hi
					//%2+ à gérer
					this.ia.get(this.gameSettings.getCurrentPlayer().getLogin()).notifyChosenMove(new Point(i, j), this.gameSettings.getCurrentPlayer().getPlayerNumber()%2+1);
				} catch (WrongPlayablePositionException e) {
					Log.error(e.getMessage());
					e.printStackTrace();
				}
				this.updateInformationField();
				this.dealWithCurrentPlayer();
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
			this.updateInformationField();
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
			this.updateInformationField();
			
			Piece p = this.gameSettings.getGameHistory().get(this.gameSettings.getHistoryPosition());
			try {
				this.ia.get(this.gameSettings.getCurrentPlayer().getPlayerNumber()).
					notifyChosenMove(new Point(p.getPosX(), p.getPosY()), this.gameSettings.getCurrentPlayer().getPlayerNumber());
			} catch (WrongPlayablePositionException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onBackButtonCliked() {
		if(this.gameSettings.getBackInHistory()){
			this.addMessageToListForUser(TextManager.BACK_PIECE_MESSAGE_LIST_VUE);
			this.gameView.setBoard(this.gameSettings.getGameBoard());
			this.setPlayablePiece();
			this.updateInformationField();
			this.ia.get(this.gameSettings.getCurrentPlayer().getPlayerNumber()).undoMove();
		}
	}

	@Override
	public void onResetButtonCliked() {
		this.gameSettings.restartGame();
		this.gameView.setBoard(this.gameSettings.getGameBoard());
		this.addMessageToListForUser(TextManager.RESET_PIECE_MESSAGE_LIST_VUE);
		this.setPlayablePiece();
		this.updateInformationField();
	}

	@Override
	public void onHelpIAButtonCliked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPositionButtonCliked() {
		if(this.gameSettings.getHistoryPosition()>=0)
			chooseHistoryBoardPosition();
	}

	@Override
	public void onReversePlayerButtonCliked() {
		this.gameSettings.reversePlayer();
		this.setPlayablePiece();
		this.addMessageToListForUser(TextManager.REVERSE_PLAYER_MESSAGE_LIST_VUE);
		this.updateInformationField();
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
		String message = 	"Joueur____________________" + "\n" + 
				this.gameSettings.getFirstPlayer() + "\n\n" + 
				this.gameSettings.getSecondPlayer() + "\n\n" +
				"Othellier_________________" + "\n" +
				"Nombre de pion : " + (this.gameSettings.getGameBoard().getBlackPieces().size() + this.gameSettings.getGameBoard().getWhitePieces().size()) + "\n" +
				"Taille : " + this.gameSettings.getGameBoard().getSizeX() + "x" + this.gameSettings.getGameBoard().getSizeY() + "\n" +
				"Difficulté de l'IA : " + this.gameSettings.getAIDifficulty() + "\n" + 
				"Temps de réflexion de l'IA : " + this.gameSettings.getAIThinkingTime() + "\n\n" + 
				"PC_____________________" + "\n" +
				Application.getInstance().toString();

		JOptionPane.showMessageDialog(null, message, TextManager.OPTION_POPUP_TITLE, JOptionPane.INFORMATION_MESSAGE);
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
		this.checkPlayersPiecesCount();
		this.writeMessageToUser("Tour du joueur : " + this.gameSettings.getCurrentPlayer().getLogin() + ", de couleur " + this.gameSettings.getCurrentPlayer().getColor() + " => " + this.gameSettings.getGameBoard().getPlayablePieces().size() + " coup(s) jouable(s)");
		this.writeStatMessage("Blanc [" + this.gameSettings.getFirstPlayer().getLogin() + "] : " + this.gameSettings.getFirstPlayer().getPiecesNumber() + " | Noir[" + this.gameSettings.getSecondPlayer().getLogin() + "] : " + this.gameSettings.getSecondPlayer().getPiecesNumber());
	}

	@Override
	protected void onIAPlayed() {
		this.updateInformationField();
	}


}
