package com.controller;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import utils.Application;
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

/**
 * @mainpage Module de création d'Othellier
 * 
 * Ce module a pour but de permettre à l'utilisateur de créer un othellier personnalisé. Il peut ainsi décider de sa taille ainsi que des différentes pièces présentes sur le plateau. Ses actions sont toutefois limitées par les règles du jeu. 
 * 
 * <b>Exemple de BONNE utilisation du module : </b>
 *  
 *		BoardPublisher bp = new BoardPublisher();
 *		bp.boardMaker();
 */

/**
 * Classe qui implémente le contrôleur abstrait général du jeu 
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public abstract class GameController {

	/**
	 * Objet du model contenant les informations de bases sur l'othellier a afficher
	 */
	protected GameSettings gameSettings;
	/**
	 * Objet du module Timer
	 */
	protected TimerManager timer;
	/**
	 * Objets du module d'IA
	 */
	protected ArtificialIntelligence helpAI;
	protected HashMap<String, ArtificialIntelligence> ai;
	/**
	 * Objet du module de gestion de fichier
	 */
	protected FilesManager files;
	/**
	 * Booléen permettant de savoir si le joueur précèdent à passer son tour pour savoir si la partie est terminée
	 */
	protected boolean hasThePreviousPlayerPassHisTurn;
	/**
	 * La factory permettant de générer les objets pour la sauvegarde de la partie
	 */
	protected SaveGameFactory sgFacto;
	/**
	 * L'objet permettant de sauvegarder la partie en cours
	 */
	protected SaveGame saveGame = null;
	/**
	 * Interface gérant les coups de l'IA à travers le thread
	 */
	protected IAResponseTimeHandler iaInterface;

	/**
	 * Méthode lançant l'application
	 * @param args
	 */
	public static void main(String[] args) {
		Log.reset();
		Application app = Application.getInstance();
		app.calculateComponentSize();

		GameController game = new GameControllerGraphical();
	}

	/**
	 * Constructeur qui initialise le logiciel, tous les objets ainsi que une partie par défault en 4x4
	 */
	protected GameController() {
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();
		PieceFactory pieceFacto = FactoryProducer.getPieceFactory();
		this.sgFacto = FactoryProducer.getSaveGameFactory();

		BoardObservable board = null;
		files = new FilesManagerImpl();
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
					pFacto.getHumanPlayer("Harry POTTER", BoardPublisher.WHITE_PLAYER,1), 
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

	/**
	 * Méthode qui initialise toutes les IA
	 */
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

	/**
	 * Méthode qui stop toutes les IA
	 */
	protected void stopAllAI(){
		for(ArtificialIntelligence ai : this.ai.values())
			ai.completeReflexion();

		System.gc();
	}

	/**
	 * Méthode appelée pour l'initialisation d'une nouvelle partie
	 */
	protected abstract void initializeNewGame();
	/**
	 * Méthode appelée pour le chargement d'un fichier
	 */
	protected abstract void loadFileForGame();
	/**
	 * Méthode appelée lorsque les joueurs ne peuvent plus jouer
	 */
	protected abstract void playerCantPlay();
	/**
	 * Méthode appelée avant de donnée la main au joueur actuel
	 */
	protected abstract void beforeDealingWithCurrentPlayer();
	/**
	 * Méthode appelée quand l'IA à jouer
	 */
	protected abstract void onIAPlayed(String login, int i, int j);
	/**
	 * Méthode appelée à la fin du changement du tour du joueur
	 */
	protected abstract void onChangePlayerTurnFinished();
	/**
	 * Méthode appelée pour afficher un message utilisateur concernant la sauvegarde d'un fichier
	 */
	protected abstract void onSaveToFile(String message);
	/**
	 * Méthode appelée dès qu'un fichier à charger est fourni par l'utilisateur
	 */
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
	}
	/**
	 * Méthode soulevée dès qu'une pièce est jouée
	 * @param i La position en X
	 * @param j La position en Y
	 * @return true si l'ajout est réalisé sinon faux car impossible
	 */
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
	/**
	 * Cette méthode permet de changer le joueur qui doit jouer
	 */
	protected void changePlayerTurn(){
		this.gameSettings.changePlayer();
		GameControllers.setPlayablePiece(this.gameSettings);

		this.onChangePlayerTurnFinished();
	}

	/**
	 * Méthode gérant l'action à réaliser en fonction du type du joueur qui doit jouer
	 */
	protected void dealWithCurrentPlayer(){
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

				iaInterface = new IAResponseTimeHandler() {

					@Override
					public void onIAPositionGiven(final Point pointChoosen) {
						if(pointChoosen == null){
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
									}else{
										Log.error("La position renseigner par l'IA n'est actuellement impossible!");
									}
								}
							});
							time.startTimer(750);
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
								iaInterface.onIAPositionGiven(ai.get(userLogin).quickNextMove(playerNumber));
							}
						});
						time.startTimer(gameSettings.getAIThinkingTime());
						Point p = ai.get(userLogin).nextMove(playerNumber);
						time.stopTimer();
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
	/**
	 * Méthode réinitialisant la partie à zéro
	 */
	protected void resetGameBoard(){
		this.gameSettings.restartGame();
		GameControllers.setPlayablePiece(this.gameSettings);
		this.stopAllAI();
	}
	/**
	 * Cette méthode retourne la pièce conseillé par l'IA
	 * @return La pièce conseillé
	 */
	protected Piece getAdvisedPieceByAI(){
		Point p = this.helpAI.nextMove(this.gameSettings.getCurrentPlayer().getPlayerNumber());
		return this.gameSettings.getGameBoard().getBoard()[p.x][p.y];
	}

	/**
	 * Cette méthode permet d'inverser les joueurs
	 */
	protected void reversePlayer(){
		this.gameSettings.reversePlayer();
		GameControllers.setPlayablePiece(this.gameSettings);
	}
	/**
	 * Méthode permettant de sauvegarder les positions jouées jusqu'à l'appel de la méthode
	 */
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
	/**
	 * Méthode sauvegardeant rapidement, et appelé à chaque pièce jouée, la partie
	 */
	protected void quickSaveOFCurrentBoard(){
		saveGame.setAutoSaveFileName("autosave.xml");

		if(!saveGame.autoSaveGameToBackupFile(this.gameSettings)){
			Log.error("Echec lors de la sauvegarde automatique du jeu!");
			this.onSaveToFile("Echec lors de la sauvegarde automatique du jeu!");
		}
	}

	/**
	 * Méthode lançant le module d'édition de plateau
	 */
	protected void launchShellToLetUserConfigureNewBoard(){
		GenerateXML gxml = new GenerateXML();
		gxml.boardMaker();
	}
	/**
	 * Cette méthode termine l'initialisation d'une nouvelle partie
	 * @param game Le nouveau model contenant la nouvelle partie
	 */
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

	/**
	 * Méthode permettant de sauvegarder la partie dans un fichier du nom choisi par l'utilisateur
	 * @param path Le chemin complet vers le fichier
	 */
	protected void saveCurrentBoard(String path){
		saveGame.setSaveFileName(path);
		if(saveGame.saveGameToBackupFile(this.gameSettings))
			this.onSaveToFile("Réussite de la sauvegarde du jeu dans le fichier : " + path);
		else
			this.onSaveToFile("Echec de la sauvegarde du jeu dans le fichier : " + path);
	}

}