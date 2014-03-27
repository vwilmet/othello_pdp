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
import com.model.player.MachinePlayer;
import com.publisher.generator.GenerateXML;
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
			int playerNumber = this.gameSettings.getCurrentPlayer().getPlayerNumber();

			if(onPiecePlayed(i, j)){
				this.gameView.setIAAdvisedPiece(null);

				try {
					this.helpAI.notifyChosenMove(new Point(i, j), playerNumber);
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
			this.initializeCompletGameAfterNewConfiguration();
			this.addMessageToListForUser(TextManager.NEM_GAME_START_MESSAGE_LIST_VUE);
			this.updateInformationField();
			this.dealWithCurrentPlayer();
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
		if(this.gameSettings.canGoForward()){
			if(this.gameSettings.getForwardInHistory()){
				this.addMessageToListForUser(TextManager.FORWARD_PIECE_MESSAGE_LIST_VUE);
				this.gameView.setBoard(this.gameSettings.getGameBoard());
				this.setPlayablePiece();	
				this.updateInformationField();

				Piece p = this.gameSettings.getGameHistory().get(this.gameSettings.getHistoryPosition());
				try {
					this.ai.get(this.gameSettings.getCurrentPlayer().getLogin()).
					notifyChosenMove(new Point(p.getPosX(), p.getPosY()), this.gameSettings.getCurrentPlayer().getPlayerNumber());
				} catch (WrongPlayablePositionException e) {
					Log.error(e.getMessage());
					e.printStackTrace();
				}

				this.dealWithCurrentPlayer();
			}
		}else
			this.addMessageToListForUser("Vous ne pouvez pas revenir en avant !!");
	}

	@Override
	public void onBackButtonCliked() {
		if(this.gameSettings.canGoBack()){
			if(this.gameSettings.getBackInHistory()){
				this.addMessageToListForUser(TextManager.BACK_PIECE_MESSAGE_LIST_VUE);
				this.gameView.setBoard(this.gameSettings.getGameBoard());
				this.setPlayablePiece();
				this.updateInformationField();
				//On revient deux coups en arrière si l'adversaire est une IA

				this.helpAI.undoMove();
				if(this.gameSettings.getOpponentPlayer().getPlayerType() instanceof MachinePlayer)
					this.helpAI.undoMove();

				this.dealWithCurrentPlayer();
			}
		}else
			this.addMessageToListForUser("Vous ne pouvez pas revenir en arrière !!");
	}

	@Override
	public void onResetButtonCliked() {
		if(!this.gameSettings.canReset()){
			this.addMessageToListForUser("Vous ne pouvez pas recommencer la partie!! Vous êtes déjà au début de la partie");
			return;
		}
		this.gameSettings.restartGame();
		this.gameView.setBoard(this.gameSettings.getGameBoard());
		this.addMessageToListForUser(TextManager.RESET_PIECE_MESSAGE_LIST_VUE);
		this.setPlayablePiece();
		this.updateInformationField();
		this.stopAllAI();
		this.initializeIA();
		this.dealWithCurrentPlayer();
	}

	@Override
	public void onHelpIAButtonCliked() {
		if(this.gameSettings.getGameBoard().getPlayablePieces().size() != 0){
			Point p = this.helpAI.nextMove(this.gameSettings.getCurrentPlayer().getPlayerNumber());
			Piece piece = this.gameSettings.getGameBoard().getBoard()[p.x][p.y];
			this.gameView.setIAAdvisedPiece(piece);
			this.gameView.refresh();
		}
	}

	@Override
	public void onPositionButtonCliked() {
		if(this.gameSettings.getHistoryPosition()>=0)
			chooseHistoryBoardPosition();
		else
			this.addMessageToListForUser("Vous devez jouer au moins un coup pour choisir une position !!");
	}

	@Override
	public void onReversePlayerButtonCliked() {
		this.gameSettings.reversePlayer();
		this.setPlayablePiece();
		this.addMessageToListForUser(TextManager.REVERSE_PLAYER_MESSAGE_LIST_VUE);
		this.updateInformationField();
		this.dealWithCurrentPlayer();	
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
			this.saveCurrentBoard(chooser.getSelectedFile().getPath());
		}
	}

	@Override
	public void onOpenFileAndContinueItemMenuPressed() {
		this.loadFileForGame();
		this.initializeCompletGameAfterNewConfiguration();
		this.addMessageToListForUser(TextManager.NEM_GAME_START_MESSAGE_LIST_VUE);
		this.updateInformationField();
		this.dealWithCurrentPlayer();
	}

	@Override
	public void onOpenFileAndChoosePositionItemMenuPressed() {
		this.loadFileForGame();
		this.onPositionButtonCliked();
	}

	@Override
	public void onOptionItemMenuPressed() {
		String message = 	"Joueur____________________" + "\n" + 
				this.gameSettings + "\n\n" +
				"Othellier_________________" + "\n" +
				"Nombre de pion : " + (this.gameSettings.getGameBoard().getBlackPieces().size() + this.gameSettings.getGameBoard().getWhitePieces().size()) + "\n" +
				"Taille : " + this.gameSettings.getGameBoard().getSizeX() + "x" + this.gameSettings.getGameBoard().getSizeY() + "\n" +
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
		
		int recoil = this.gameSettings.getBoardHistoryPosition() - position;
		
		this.gameSettings.setHistoryPosition(position-1);
		this.gameView.setBoard(this.gameSettings.getGameBoard());
		this.setPlayablePiece();
		
		for(int i = 0; i < recoil; i++){
			this.helpAI.undoMove();
		}
		
		this.updateInformationField();
		this.dealWithCurrentPlayer();
	}
	
	private void updateInformationField(){
		this.checkPlayersPiecesCount();
		this.writeMessageToUser("Tour du joueur : " + this.gameSettings.getCurrentPlayer().getLogin() + ", de couleur " + this.gameSettings.getCurrentPlayer().getColor() + " => " + this.gameSettings.getGameBoard().getPlayablePieces().size() + " coup(s) jouable(s)");
		this.writeStatMessage(
				"Blanc [" + this.gameSettings.getFirstPlayer().getLogin() + " - " + this.gameSettings.getFirstPlayer().getPlayerType() + "] : " + 
						this.gameSettings.getFirstPlayer().getPiecesNumber() + 
						" | Noir [" + this.gameSettings.getSecondPlayer().getLogin() + " - " + this.gameSettings.getSecondPlayer().getPlayerType() + "] : " + 
						this.gameSettings.getSecondPlayer().getPiecesNumber());

	}

	@Override
	protected void onIAPlayed(String login, int i, int j) {
		this.updateInformationField();
		this.addMessageToListForUser("L'IA : " + login + " a joué en " + i + ", " + j);
	}

	/*
	protected void manageButtonState(){

		this.gameView.enableBackButton(
				this.gameSettings.canGoBack() == true ? 
						true : false);

		this.gameView.enableForwardButton(
				this.gameSettings.canGoForward() == true ? 
						true : false);

		this.gameView.enableResetButton(
				this.gameSettings.canReset() == true ? 
						true : false);
	}*/

	@Override
	protected void playerCantPlay() {
		String message = "Partie terminée !!! \n\n Le joueur : ";

		message += (this.gameSettings.getFirstPlayer().getPiecesNumber() > this.gameSettings.getSecondPlayer().getPiecesNumber()) ? this.gameSettings.getFirstPlayer().getLogin() : this.gameSettings.getSecondPlayer().getLogin();

		message += " à remporter le match!!!";

		if(this.gameSettings.getFirstPlayer().getPiecesNumber() == this.gameSettings.getSecondPlayer().getPiecesNumber())
			message = "Partie terminée !!!\n\n Match nul!! Mais alors vraiement nul ... !";

		JOptionPane.showMessageDialog(null, message, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	protected void beforeDealingWithCurrentPlayer() {
		//this.manageButtonState();
	}

	@Override
	protected void onAutoSaveCurrentBoardFailed() {
		this.addMessageToListForUser("Echec de la sauvegarde automatique de la liste de coups joués : " + this.timer.getElapsedTimeInMinAndSeconde());
	}

	@Override
	protected void onAutoSaveCurrentBoardSuccess() {
		this.addMessageToListForUser("Réussite de la sauvegarde des coups joués : " + this.timer.getElapsedTimeInMinAndSeconde());
	}

	@Override
	public void onConfigureBoardItemMenuPressed() {
		GenerateXML gxml = new GenerateXML();
		gxml.boardMaker();
	}

	@Override
	public void onSaveHistoryPositionItemMenuPressed() {
		this.saveHistoryPosition();
	}
}
