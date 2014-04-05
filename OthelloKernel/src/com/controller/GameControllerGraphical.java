package com.controller;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.Application;
import utils.GameControllers;
import utils.TextManager;

import com.controller.interfaces.NotifyGameControllerGraphical;
import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.piece.Piece;
import com.model.player.HumanPlayer;
import com.model.player.MachinePlayer;
import com.utils.WrongPlayablePositionException;
import com.view.GameViewImpl;
import com.view.button.ImageButton;
import com.view.event.ButtonImageMenuEventListener;
import com.view.event.GameCanvasMouseEventListener;
import com.view.event.GameViewMenuEventListener;
import com.view.interfaces.GameView;

public class GameControllerGraphical extends GameController implements NotifyGameControllerGraphical, GameCanvasMouseEventListener, ButtonImageMenuEventListener, GameViewMenuEventListener{

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
		int returnVal;

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichiers XML", "xml");
		chooser.setFileFilter(filter);

		returnVal = chooser.showOpenDialog((GameViewImpl)this.gameView);

		if(returnVal == JFileChooser.APPROVE_OPTION) {

			onLoadedFileChoosen(chooser.getSelectedFile().getPath());
			this.gameView.setBoard(gameSettings.getGameBoard());
			this.gameView.resetMessageListContent();
		}
	}

	@Override
	public void onLeftMouseButtonPressed(int i, int j) {

		if(this.gameSettings.getCurrentPlayer().getPlayerType() instanceof HumanPlayer){
			if(i!=-1 && j != -1){
				if(onPiecePlayed(i, j)){
					this.gameView.setIAAdvisedPiece(null);
					this.dealWithCurrentPlayer();
				}
			}
		}else
			this.addMessageToListForUser("Veuillez patientez, l'IA est en train de jouer !");
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
			this.initializeCompletGameAfterNewConfiguration(game);
			this.gameView.resetMessageListContent();
			this.gameView.setBoard(this.gameSettings.getGameBoard());
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
			if(this.gameSettings.getOpponentPlayer().getPlayerType() instanceof MachinePlayer){
				if(this.gameSettings.getForwardInHistory()){
					Piece p = this.gameSettings.getGameHistory().get(this.gameSettings.getHistoryPosition());
					try {
						this.helpAI.notifyChosenMove(new Point(p.getPosX(), p.getPosY()), this.gameSettings.getOpponentPlayer().getPlayerNumber());
					} catch (WrongPlayablePositionException e) {
						Log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}

			if(this.gameSettings.getForwardInHistory()){
				Piece p = this.gameSettings.getGameHistory().get(this.gameSettings.getHistoryPosition());
				try {
					this.helpAI.notifyChosenMove(new Point(p.getPosX(), p.getPosY()), this.gameSettings.getOpponentPlayer().getPlayerNumber());
				} catch (WrongPlayablePositionException e) {
					Log.error(e.getMessage());
					e.printStackTrace();
				}
			}
			this.addMessageToListForUser(TextManager.FORWARD_PIECE_MESSAGE_LIST_VUE);
			GameControllers.setPlayablePiece(this.gameSettings);	
			this.updateInformationField();
			this.gameView.setBoard(this.gameSettings.getGameBoard());
			this.dealWithCurrentPlayer();
		}else
			this.addMessageToListForUser("Vous ne pouvez pas revenir en avant !!");
	}

	@Override
	public void onBackButtonCliked() {
		System.out.println("[onBackButtonCliked]");

		if(this.gameSettings.canGoBack()){
			System.out.println("Joueur qui jouer : " + this.gameSettings.getCurrentPlayer());
			System.out.println("Sentinelle avant : " + this.gameSettings.getHistoryPosition());
			if(this.gameSettings.getOpponentPlayer().getPlayerType() instanceof MachinePlayer){
				this.gameSettings.getBackInHistory();
				this.helpAI.undoMove();
			}
			this.gameSettings.getBackInHistory();
			this.helpAI.undoMove();
			GameControllers.setPlayablePiece(this.gameSettings);	
			this.updateInformationField();
			this.gameView.setBoard(this.gameSettings.getGameBoard());
			this.addMessageToListForUser(TextManager.BACK_PIECE_MESSAGE_LIST_VUE);

			System.out.println("Sentinelle après : " + this.gameSettings.getHistoryPosition());
			System.out.println("Joueuer qui va jouer : " + this.gameSettings.getCurrentPlayer());

			this.dealWithCurrentPlayer();

		}else
			this.addMessageToListForUser("Vous ne pouvez pas revenir en arrière !!");
	}

	@Override
	public void onResetButtonCliked() {
		if(!this.gameSettings.canReset()){
			this.addMessageToListForUser("Vous ne pouvez pas recommencer la partie!! Vous êtes déjà au début de la partie");
			return;
		}

		resetGameBoard();

		this.gameView.setBoard(this.gameSettings.getGameBoard());
		this.addMessageToListForUser(TextManager.RESET_PIECE_MESSAGE_LIST_VUE);
		this.updateInformationField();
		this.initializeIA();
		this.dealWithCurrentPlayer();
	}

	@Override
	public void onHelpIAButtonCliked() {
		if(this.gameSettings.getGameBoard().getPlayablePieces().size() != 0){
			this.gameView.setIAAdvisedPiece(getAdvisedPieceByAI());
			this.gameView.refresh();
		}
	}

	@Override
	public void onPositionButtonCliked() {
		if(this.gameSettings.canGoBack() || this.gameSettings.canGoForward())
			chooseHistoryBoardPosition();
		else
			this.addMessageToListForUser("Vous devez jouer au moins un coup pour choisir une position !!");
	}

	@Override
	public void onReversePlayerButtonCliked() {
		reversePlayer();
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
		this.initializeCompletGameAfterNewConfiguration(this.gameSettings);
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
				this.gameSettings + "\n" +
				getCurrentWinningStateAsAString() +
				"\n\nOthellier_________________" + "\n" +
				"Nombre de pion : " + (this.gameSettings.getGameBoard().getBlackPieces().size() + this.gameSettings.getGameBoard().getWhitePieces().size()) + "\n" +
				"Taille : " + this.gameSettings.getGameBoard().getSizeX() + "x" + this.gameSettings.getGameBoard().getSizeY() + "\n" +
				"\nPC_____________________" + "\n" +
				Application.getInstance().toString();
		JOptionPane.showMessageDialog(null, message, TextManager.OPTION_POPUP_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void onHelpItemMenuPressed() {
		try {
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
		GameControllers.setPlayablePiece(this.gameSettings);	

		/*System.out.println("(------------------------------------------)");
		System.out.println("Before undoing!!");
		System.out.println(this.helpAI.boardToString());*/

		for(int i = 0; i < recoil; i++){
			this.helpAI.undoMove();
		}

		/*System.out.println(this.helpAI.boardToString());
		System.out.println("After undoing");
		System.out.println("(------------------------------------------)");*/

		this.updateInformationField();
		this.addMessageToListForUser("Vous avez fait un retour de " + recoil + " position(s).");

		this.dealWithCurrentPlayer();
	}

	private void updateInformationField(){
		GameControllers.checkPlayersPiecesCount(this.gameSettings);
		this.writeMessageToUser("Tour du joueur : " + this.gameSettings.getCurrentPlayer().getLogin() + ", de couleur " + this.gameSettings.getCurrentPlayer().getColor() + " => " + this.gameSettings.getGameBoard().getPlayablePieces().size() + " coup(s) jouable(s)");
		this.writeStatMessage(
				this.gameSettings.getFirstPlayer().getColor() + " [" + this.gameSettings.getFirstPlayer().getLogin() + " - " + this.gameSettings.getFirstPlayer().getPlayerType() + "] : " + 
						this.gameSettings.getFirstPlayer().getPiecesNumber() + 
						" | " + this.gameSettings.getSecondPlayer().getColor() +" [" + this.gameSettings.getSecondPlayer().getLogin() + " - " + this.gameSettings.getSecondPlayer().getPlayerType() + "] : " + 
						this.gameSettings.getSecondPlayer().getPiecesNumber());

	}

	@Override
	protected void onIAPlayed(String login, int i, int j) {
		this.updateInformationField();
		this.addMessageToListForUser("L'IA : " + login + " a joué en " + i + ", " + j);
	}

	protected String getCurrentWinningStateAsAString(){
		String result = "Status________________\n";
		
		switch(this.helpAI.winStatus(this.gameSettings.getFirstPlayer().getPlayerNumber())){
		case 0 :
			result += "Le joueur " + this.gameSettings.getFirstPlayer().getLogin() + " est plus susceptible de perdre\n";
			break;
		case 1 :
			result += "Le joueur " + this.gameSettings.getFirstPlayer().getLogin() + " est plus susceptible de gagner\n";
			break;
		case 2:
			result += "La partie devrait finir sur une égalité\n";
			break;
		default :
			Log.error("Une erreur est survenue pendant la récupération du status de la partie par l'IA");
		}
		
		return result;
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
	public void onConfigureBoardItemMenuPressed() {
		launchShellToLetUserConfigureNewBoard();
	}

	@Override
	public void onSaveHistoryPositionItemMenuPressed() {
		this.saveHistoryPosition();
	}

	@Override
	protected void onChangePlayerTurnFinished() {
		this.updateInformationField();
	}

	@Override
	protected void onSaveToFile(String message) {
		this.addMessageToListForUser(message);
	}
}
