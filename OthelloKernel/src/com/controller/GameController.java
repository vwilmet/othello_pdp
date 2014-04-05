package com.controller;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import utils.FactoryHandlerException;
import utils.GameControllers;

import com.ai.ArtificialIntelligence;
import com.ai.impl.ArtificialIntelligenceImpl;
import com.controller.interfaces.IAResponseTimeHandler;
import com.error_manager.Log;
import com.manager.FilesManager;
import com.manager.FilesManagerImpl;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.factory.interfaces.RestoreGameFactory;
import com.model.factory.interfaces.SaveGameFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;
import com.model.piece.Piece;
import com.model.player.MachinePlayer;
import com.publisher.BoardPublisher;
import com.publisher.generator.GenerateXML;
import com.timermanager.TimerActionEvent;
import com.timermanager.TimerManager;
import com.timermanager.TimerManagerImpl;
import com.utils.WrongPlayablePositionException;

public abstract class GameController {

	protected GameSettings gameSettings;
	protected TimerManager timer;
	protected ArtificialIntelligence helpAI;
	protected HashMap<String, ArtificialIntelligence> ai;
	protected FilesManager files = new FilesManagerImpl();
	protected boolean hasThePreviousPlayerPassHisTurn;
	protected SaveGameFactory sgFacto;
	protected SaveGame saveGame = null;
	private IAResponseTimeHandler iaInterface;

	protected GameController() {
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();
		PieceFactory pieceFacto = FactoryProducer.getPieceFactory();
		this.sgFacto = FactoryProducer.getSaveGameFactory();

		BoardObservable board = null;
		this.timer = new TimerManagerImpl();
		this.ai = new HashMap<String, ArtificialIntelligence>();
		this.files.init(false);
		hasThePreviousPlayerPassHisTurn = false;

		try {
			board = bFacto.getInitialBoard(4, 4);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			this.gameSettings = gsFacto.getGameSettings(
					pFacto.getHumanPlayer("toto", BoardPublisher.WHITE_PLAYER,1), 
					pFacto.getMachinePlayer("John DOE", BoardPublisher.BLACK_PLAYER,2),
					board,
					GameSettings.DEFAULT_IA_THINKING_TIME, 
					GameSettings.DEFAULT_IA_DIFFICULTY,
					pieceFacto.getArrayListOfPiece());

			this.ai.put("John DOE", new ArtificialIntelligenceImpl());

			this.initializeIA();
			GameControllers.setPlayablePiece(this.gameSettings);

			try {
				saveGame = sgFacto.getSaveGame();
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		timer.startCountingElapsedTime();
		GameControllers.checkPlayersPiecesCount(this.gameSettings);
	}

	protected void initializeIA(){
		String current_key;
		Set<Point> 	whitePiece = new HashSet<Point>(),
				blackPiece = new HashSet<Point>();

		for(Piece p : this.gameSettings.getGameBoard().getWhitePieces())
			whitePiece.add(new Point(p.getPosX(), p.getPosY()));

		for(Piece p : this.gameSettings.getGameBoard().getBlackPieces())
			blackPiece.add(new Point(p.getPosX(), p.getPosY()));

		helpAI = new ArtificialIntelligenceImpl();
		helpAI.chooseDifficulty(this.gameSettings.getHelpAIDifficulty());
		helpAI.initialize(whitePiece, blackPiece, this.gameSettings.getGameBoard().getSizeX(), this.gameSettings.getGameBoard().getSizeY());

		Set<String> key = ai.keySet();
		Iterator<String> key_it = key.iterator();

		for (Iterator<String> it = key_it; it.hasNext(); ) {
			current_key = it.next();
			ArtificialIntelligence _ai = ai.get(current_key);

			if(this.gameSettings.getFirstPlayer().getLogin().equals(current_key))
				_ai.chooseDifficulty(this.gameSettings.getPlayer1ArtificialIntelligenceDifficulty());
			else
				_ai.chooseDifficulty(this.gameSettings.getPlayer2ArtificialIntelligenceDifficulty());

			_ai.initialize((ArtificialIntelligenceImpl)helpAI);
		}

	}

	protected void stopAllAI(){
		for(ArtificialIntelligence ai : this.ai.values())
			ai.completeReflexion();

		System.gc();
	}

	protected abstract void initializeNewGame();

	protected abstract void loadFileForGame();

	protected abstract void playerCantPlay();

	protected abstract void beforeDealingWithCurrentPlayer();

	protected abstract void onIAPlayed(String login, int i, int j);

	protected abstract void onChangePlayerTurnFinished();

	protected abstract void onSaveToFile(String message);

	protected void onLoadedFileChoosen(String path){
		RestoreGameFactory rgFacto = FactoryProducer.getRestoreGameFactory();
		RestoreGame rg = null;

		try {
			rg = rgFacto.getRestoreGame(path);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		rg.loadGameFromBackupFile();

		this.gameSettings = rg.getGameSettings();

		System.out.println(this.gameSettings);
	}

	protected boolean onPiecePlayed(int i, int j){
		for(Piece possiblePiece : this.gameSettings.getGameBoard().getPlayablePieces())
			if(possiblePiece.getPosX() == i && possiblePiece.getPosY() == j){
				this.gameSettings.setPiece(i, j);
				GameControllers.reverseInbetweenPieceAfterPlaying(this.gameSettings.getGameBoard(), i, j);
				GameControllers.checkPlayersPiecesCount(this.gameSettings);
				this.gameSettings.manageBoardHistory(i, j);

				try {
					this.helpAI.notifyChosenMove(new Point(i, j), this.gameSettings.getCurrentPlayer().getPlayerNumber());
				} catch (WrongPlayablePositionException e) {
					Log.error(e.getMessage());
					e.printStackTrace();
				}

				changePlayerTurn();
				this.quickSaveOFCurrentBoard();
				return true;
			}
		return false;
	}

	protected void changePlayerTurn(){
		this.gameSettings.changePlayer();
		GameControllers.setPlayablePiece(this.gameSettings);

		this.onChangePlayerTurnFinished();
	}

	protected void dealWithCurrentPlayer(){
		System.out.println("[dealWithCurrentPlayer]");
		System.out.println("Curent player : " + this.gameSettings.getCurrentPlayer());
		this.beforeDealingWithCurrentPlayer();

		if(this.gameSettings.getGameBoard().getPlayablePieces().size() == 0){

			if(!hasThePreviousPlayerPassHisTurn &&
					(this.gameSettings.getGameBoard().getBlackPieces().size() + this.gameSettings.getGameBoard().getWhitePieces().size()) <
					(this.gameSettings.getGameBoard().getSizeX()*this.gameSettings.getGameBoard().getSizeY())){
				hasThePreviousPlayerPassHisTurn = true;
				String message = "Le joueur " + this.gameSettings.getCurrentPlayer().getLogin() + " ne peux plus jouer! C'est au tour du joueur " + this.gameSettings.getOpponentPlayer().getLogin();
				JOptionPane.showMessageDialog(null, 
						message, 
						"Attention ... ", JOptionPane.INFORMATION_MESSAGE);

				changePlayerTurn();
				dealWithCurrentPlayer();
			}
			else{
				this.playerCantPlay();
			}

			return;
		}else{
			hasThePreviousPlayerPassHisTurn = false;

			//Si le joueur à jouer est l'IA
			if(this.gameSettings.getCurrentPlayer().getPlayerType() instanceof MachinePlayer){
				final String userLogin = this.gameSettings.getCurrentPlayer().getLogin();
				final int playerNumber = this.gameSettings.getCurrentPlayer().getPlayerNumber();

				
				System.out.println("passer par la!!");
				iaInterface = new IAResponseTimeHandler() {

					@Override
					public void onIAPositionGiven(final Point pointChoosen) {
						System.out.println("pointChoosen : " + pointChoosen);
						if(pointChoosen == null){
							/*JOptionPane.showMessageDialog(null, 
								"L'IA ne peut plus jouer !", 
								TextManager.OPTION_POPUP_TITLE, JOptionPane.INFORMATION_MESSAGE);*/
							Log.error("Erreur : l'ia ne peut plus jouer d'après le module mais elle à toujours des positions à jouer d'après le controlleur!");
						}else{
							TimerManager time = new TimerManagerImpl();
							time.setTimerActionEvent(new TimerActionEvent() {

								@Override
								public void onTimerStopped() {
									this.commonAction();
								}

								@Override
								public void onTimerEnded() {
									this.commonAction();
								}

								public void commonAction(){

									if(onPiecePlayed(pointChoosen.x, pointChoosen.y)){
										onIAPlayed(userLogin, pointChoosen.x, pointChoosen.y);
										dealWithCurrentPlayer();
									}
								}
							});
							time.startTimer(150);
						}
					}
				};
				
				final Thread iaThinkingThread = new Thread(new Runnable() {
				
					@Override
					public void run() {
						TimerManager time = new TimerManagerImpl();
						time.setTimerActionEvent(new TimerActionEvent() {

							@Override
							public void onTimerStopped() {}

							@Override
							public void onTimerEnded() {
								Thread.currentThread().suspend();
								System.out.println("IA choice time BOUM!!!");
								iaInterface.onIAPositionGiven(ai.get(userLogin).quickNextMove(playerNumber));
							}
						});
						time.startTimer(gameSettings.getAIThinkingTime());
						System.out.println("passer par la!!==================");
						Point p = ai.get(userLogin).nextMove(playerNumber);
						time.stopTimer();
						System.out.println("IA chosed correctly!!");
						iaInterface.onIAPositionGiven(p);
					}
				});
				iaThinkingThread.start();
			}
		}
		/*
		 * Sinon c'est un joueur humain. Du coup on attend qu'il joue et lorsqu'il joue l'évenement GameControllerGraphical.onLeftMouseButtonPressed est soulevé.
		 */
	}

	protected void resetGameBoard(){
		this.gameSettings.restartGame();
		GameControllers.setPlayablePiece(this.gameSettings);
		this.stopAllAI();
	}

	protected Piece getAdvisedPieceByAI(){
		Point p = this.helpAI.nextMove(this.gameSettings.getCurrentPlayer().getPlayerNumber());
		return this.gameSettings.getGameBoard().getBoard()[p.x][p.y];
	}

	protected void reversePlayer(){
		this.gameSettings.reversePlayer();
		GameControllers.setPlayablePiece(this.gameSettings);
	}

	protected void saveHistoryPosition(){

		String boardContent = "";

		for(Piece p : this.gameSettings.getGameHistory())
			boardContent += "J" + p.getColor().getColor() + ":" + p.getPosX() + "-" + p.getPosY() + "\n";

		if(files.save("history_position.txt", "./", boardContent)){
			this.onSaveToFile("Réussite de la sauvegarde des coups joués : " + this.timer.getElapsedTimeInMinAndSeconde());
		}else{
			Log.error("Echec de la sauvegarde automatique des coups joués !!");
			this.onSaveToFile("Echec de la sauvegarde automatique de la liste de coups joués : " + this.timer.getElapsedTimeInMinAndSeconde());
		}
	}

	protected void quickSaveOFCurrentBoard(){
		saveGame.setAutoSaveFileName("autosave.xml");

		if(!saveGame.autoSaveGameToBackupFile(this.gameSettings)){
			Log.error("Echec lors de la sauvegarde automatique du jeu!");
			this.onSaveToFile("Echec lors de la sauvegarde automatique du jeu!");
		}
	}

	protected void launchShellToLetUserConfigureNewBoard(){
		GenerateXML gxml = new GenerateXML();
		gxml.boardMaker();
	}

	protected void initializeCompletGameAfterNewConfiguration(GameSettings game){
		this.stopAllAI();
		this.gameSettings = null;
		this.gameSettings = game;
		GameControllers.setPlayablePiece(this.gameSettings);

		this.ai.clear();

		if(this.gameSettings.getFirstPlayer().getPlayerType() instanceof MachinePlayer)
			this.ai.put(this.gameSettings.getFirstPlayer().getLogin(),
					new ArtificialIntelligenceImpl());

		if(this.gameSettings.getSecondPlayer().getPlayerType() instanceof MachinePlayer)
			this.ai.put(this.gameSettings.getSecondPlayer().getLogin(),
					new ArtificialIntelligenceImpl());
		this.initializeIA();
	}

	protected void saveCurrentBoard(String path){
		saveGame.setSaveFileName(path);
		if(saveGame.saveGameToBackupFile(this.gameSettings))
			this.onSaveToFile("Réussite de la sauvegarde du jeu dans le fichier : " + path);
		else
			this.onSaveToFile("Echec de la sauvegarde du jeu dans le fichier : " + path);
	}

}