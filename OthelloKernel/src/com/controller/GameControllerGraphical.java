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

/**
 * Classe qui spécifie le contrôleur générale pour l'affichage graphique
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class GameControllerGraphical extends GameController implements NotifyGameControllerGraphical, GameCanvasMouseEventListener, ButtonImageMenuEventListener, GameViewMenuEventListener{

	/**
	 * L'objet de la vue principale du jeu
	 */
	protected GameView gameView;
	/**
	 * Le contrôleur qui gère l'initialisation d'une nouvelle partie graphiquement
	 */
	protected InitGameController initGameController;

	/**
	 * Le contrôleur qui gère le choix d'une position dans l'historique graphiquement
	 */
	protected ChoosePositionController chooseGameBoardController;

	/**
	 * Constructeur qui lance la vue graphique du logiciel
	 */
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

	/**
	 * Méthode qui lance l'affichage de la vue d'initialisation d'une nouvelle partie
	 */
	protected void initializeNewGame(){
		this.initGameController = InitGameController.getInstance(this);
		this.initGameController.showView();
	}

	/**
	 * Méthode qui lance l'affichage de la vue de choix de position graphiquement
	 */
	protected void chooseHistoryBoardPosition(){
		chooseGameBoardController = ChoosePositionController.getInstance();
		chooseGameBoardController.setEvent(this);
		chooseGameBoardController.setHistory(this.gameSettings.getBoardHistoryPosition(), this.gameSettings.getGameBoardHistory());
		chooseGameBoardController.showView();
	}

	/**
	 * Méthode qui permet à l'utilisateur de choisir un fichier graphiquement et de le charger
	 */
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

	/**
	 * Evènements soulevés à chaque clique gauche de la souris sur l'othellier
	 */
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

	/**
	 * Méthode appelée sur le clique droit de la souris
	 */
	@Override
	public void onRightMouseButtonPressed(int i, int j) {}

	/**
	 * Méthode utilisée pour ajouter une entrée dans la liste des évènements
	 * @param message Le message à ajouter
	 */
	protected void addMessageToListForUser(String message){
		this.gameView.addMessageToMessageList(message);
	}

	/**
	 * Méthode qui permet de modifier le message des statistiques de l'interface
	 * @param stat Le nouveau message de statistique
	 */
	protected void writeStatMessage(String stat){
		this.gameView.changeStatViewMessage(stat);
	}

	/**
	 * Méthode qui modifie le message d'information pour l'utilisateur
	 * @param message Le nouveau contenue
	 */
	protected void writeMessageToUser(String message){
		this.gameView.changeMessageViewContent(message);
	}

	/**
	 * Evènement soulevé à la fin de l'initilisation d'une partie
	 * @param valid Booléen déterminant si l'initialisation est correct
	 * @param game Le nouveau model contenant la nouvelle partie
	 */
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

	/**
	 * Evènement soulevé à chaque clique de bouton du menu du jeu
	 * @param button Le bouton cliqué
	 * @code code Le code associé au bouton cliqué
	 */
	@Override
	public void onButtonCliked(ImageButton button, int code) {}

	/**
	 * Evènement soulevé lors du clique sur le bouton play
	 */
	@Override
	public void onPlayButtonCliked() {
		this.gameView.setOnPause(false);
		this.addMessageToListForUser(TextManager.PLAY_MESSAGE_LIST_VUE + " : " + timer.getElapsedTimeInMinAndSeconde());
	}
	/**
	 * Evènement soulevé lors du clique sur le bouton pause
	 */
	@Override
	public void onPauseButtonCliked() {
		this.gameView.setOnPause(true);
		this.addMessageToListForUser(TextManager.PAUSE_MESSAGE_LIST_VUE + " : " + timer.getElapsedTimeInMinAndSeconde());
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton suivant
	 */
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

	/**
	 * Evènement soulevé lors du clique sur le bouton précédent
	 */
	@Override
	public void onBackButtonCliked() {
		if(this.gameSettings.canGoBack()){
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

			this.dealWithCurrentPlayer();

		}else
			this.addMessageToListForUser("Vous ne pouvez pas revenir en arrière !!");
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton recommencer
	 */
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

	/**
	 * Evènement soulevé lors du clique sur le bouton d'aide de l'IA
	 */
	@Override
	public void onHelpIAButtonCliked() {
		if(this.gameSettings.getGameBoard().getPlayablePieces().size() != 0){
			this.gameView.setIAAdvisedPiece(getAdvisedPieceByAI());
			this.gameView.refresh();
		}
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton "choisir une position"
	 */
	@Override
	public void onPositionButtonCliked() {
		if(this.gameSettings.canGoBack() || this.gameSettings.canGoForward())
			chooseHistoryBoardPosition();
		else
			this.addMessageToListForUser("Vous devez jouer au moins un coup pour choisir une position !!");
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton changement de joueur
	 */
	@Override
	public void onReversePlayerButtonCliked() {
		reversePlayer();
		this.addMessageToListForUser(TextManager.REVERSE_PLAYER_MESSAGE_LIST_VUE);
		this.updateInformationField();
		GameControllers.setPlayablePiece(this.gameSettings);
		this.dealWithCurrentPlayer();	
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton "nouvelle patie"
	 */
	@Override
	public void onNewGameItemMenuPressed() {
		this.initializeNewGame();
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton de sauvegarde
	 */
	@Override
	public void onSaveGameUnderItemMenuPressed() {

		JFileChooser chooser = new JFileChooser();
		int returnVal;

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichiers XML", "xml");
		chooser.setFileFilter(filter);

		returnVal = chooser.showSaveDialog((GameViewImpl)this.gameView);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			this.saveCurrentBoard(chooser.getSelectedFile().getPath());
		}
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton d'ouverture de fichier et de reprise de jeu à la dernière board jouée
	 */
	@Override
	public void onOpenFileAndContinueItemMenuPressed() {
		this.loadFileForGame();
		this.initializeCompletGameAfterNewConfiguration(this.gameSettings);
		this.addMessageToListForUser(TextManager.NEM_GAME_START_MESSAGE_LIST_VUE);
		this.updateInformationField();
		this.dealWithCurrentPlayer();
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton d'ouverture de fichier et de choix de la position de reprise
	 */
	@Override
	public void onOpenFileAndChoosePositionItemMenuPressed() {
		this.loadFileForGame();
		this.onPositionButtonCliked();
	}

	/**
	 * Evènement soulevé lors du clique sur le bouton d'option
	 */
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

	/**
	 * Evènement soulevé lors du clique sur le bouton d'aide
	 */
	@Override
	public void onHelpItemMenuPressed() {
		try {
			java.awt.Desktop.getDesktop().browse(new File(GameSettings.HELP_WEBSITE_PATH).toURI());
		} catch (IOException  e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Evènement soulevé dès que le choix d'une position est fait
	 * @param valid Booléen déterminant si le choix réalisé est valide
	 * @param board L'othellier choisie
	 * @param position L'entier correspondant à la position choisie dans l'historique
	 */
	@Override
	public void chooseGameBoardFinished(boolean valid, BoardObservable board,
			int position) {

		int recoil = this.gameSettings.getBoardHistoryPosition() - position;

		this.gameSettings.setHistoryPosition(position-1);
		this.gameView.setBoard(this.gameSettings.getGameBoard());
		GameControllers.setPlayablePiece(this.gameSettings);	

		for(int i = 0; i < recoil; i++){
			this.helpAI.undoMove();
		}

		this.updateInformationField();
		this.addMessageToListForUser("Vous avez fait un retour de " + recoil + " position(s).");

		this.dealWithCurrentPlayer();
	}

	/**
	 * Méthode modifiant les messages sur la vue, visible pour l'utilisateur,
	 *  en fonction du contenue du model
	 */
	private void updateInformationField(){
		GameControllers.checkPlayersPiecesCount(this.gameSettings);
		this.writeMessageToUser("Tour du joueur : " + this.gameSettings.getCurrentPlayer().getLogin() + ", de couleur " + this.gameSettings.getCurrentPlayer().getColor() + " => " + this.gameSettings.getGameBoard().getPlayablePieces().size() + " coup(s) jouable(s)");
		this.writeStatMessage(
				this.gameSettings.getFirstPlayer().getColor() + " [" + this.gameSettings.getFirstPlayer().getLogin() + " - " + this.gameSettings.getFirstPlayer().getPlayerType() + "] : " + 
						this.gameSettings.getFirstPlayer().getPiecesNumber() + 
						" | " + this.gameSettings.getSecondPlayer().getColor() +" [" + this.gameSettings.getSecondPlayer().getLogin() + " - " + this.gameSettings.getSecondPlayer().getPlayerType() + "] : " + 
						this.gameSettings.getSecondPlayer().getPiecesNumber());

	}

	/**
	 * Méthode appelée dès que l'IA à jouer une pièce
	 */
	@Override
	protected void onIAPlayed(String login, int i, int j) {
		this.updateInformationField();
		this.addMessageToListForUser("L'IA : " + login + " a joué en " + i + ", " + j);
	}

	/**
	 * Méthode retournant pour les options le status de le personne gagnant la partie
	 * @return Status du joueur gagnant
	 */
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

	/**
	 * Méthode appelée quand les joueurs ne peuvent plus jouer
	 */
	@Override
	protected void playerCantPlay() {
		String message = "Partie terminée !!! \n\n Le joueur : ";

		message += (this.gameSettings.getFirstPlayer().getPiecesNumber() > this.gameSettings.getSecondPlayer().getPiecesNumber()) ? this.gameSettings.getFirstPlayer().getLogin() : this.gameSettings.getSecondPlayer().getLogin();

		message += " à remporter le match!!!";

		if(this.gameSettings.getFirstPlayer().getPiecesNumber() == this.gameSettings.getSecondPlayer().getPiecesNumber())
			message = "Partie terminée !!!\n\n Match nul!! Mais alors vraiement nul ... !";

		JOptionPane.showMessageDialog(null, message, "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Méthode appelée avant que les joueurs ne joue
	 */
	@Override
	protected void beforeDealingWithCurrentPlayer() {}

	/**
	 * Evènement soulevée lors du clique sur le bouton de configuration d'un othellier personnalisé
	 */
	@Override
	public void onConfigureBoardItemMenuPressed() {
		launchShellToLetUserConfigureNewBoard();
	}

	/**
	 * Evènement soulevée lors du clique sur le bouton de sauvegarde des positions des coups jouée jusqu'à l'appel de la méthode
	 */
	@Override
	public void onSaveHistoryPositionItemMenuPressed() {
		this.saveHistoryPosition();
	}

	/**
	 * Méthode appelée à la fin du changement de joueur à chaque tour
	 */
	@Override
	protected void onChangePlayerTurnFinished() {
		this.updateInformationField();
	}
	
	/**
	 * Méthode appelé pour afficher un message utilisateur concernant la sauvegarde d'un fichier
	 */
	@Override
	protected void onSaveToFile(String message) {
		this.addMessageToListForUser(message);
	}
}
