package com.controller;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import jnt.BenchMarkResult;
import utils.FactoryHandlerException;
import utils.TextManager;

import com.controller.interfaces.NotifyGameControllerGraphical;
import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.player.Player;
import com.publisher.BoardPublisher;
import com.view.BenchMarkViewImpl;
import com.view.InitGameViewImpl;
import com.view.event.BenchMarkViewButtonEventListener;
import com.view.event.InitGameButtonEventListener;
import com.view.interfaces.BenchMarkView;
import com.view.interfaces.InitGameView;
/**
 * Classe implémentant le contrôleur de la fenêtre d'initialisation
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class InitGameController implements InitGameButtonEventListener {

	/**
	 * La vue gérer par le contrôleur : la vue d'initialisation
	 */
	protected InitGameView view;
	/**
	 * Interface de communication avec le contrôleur général
	 */
	protected NotifyGameControllerGraphical event;
	/**
	 * L'objet de la vue du BenchMarck afin de le lancer à partir de la vue d'initialisation
	 */
	protected BenchMarkView benchMark;
	/**
	 * L'instance unique du contrôleur
	 */
	protected static InitGameController instance;
	/**
	 * Booléen qui détermine si le calcul du BenchMark est terminé
	 */
	protected boolean benchMarkOver;

	/**
	 * Méthode qui renvoie l'instance unique de cette classe
	 * @param event L'interface qui permet de communiquer avec le contrôleur général
	 * @return L'instance de la classe
	 */
	public static InitGameController getInstance(NotifyGameControllerGraphical event){
		if(instance == null)
			instance = new InitGameController(event);
		return instance;
	}

	/**
	 * Contrôleur privé utilisé uniquement pour instancier le design pattern singleton
	 * @param event L'interface qui permet de communiquer avec le contrôleur général
	 */
	private InitGameController(NotifyGameControllerGraphical event) {
		this.event = event;
		this.benchMark = new BenchMarkViewImpl();
		this.view = new InitGameViewImpl();
		this.view.setButtonListener(this);
		this.benchMarkOver = true;
	}
	
	/**
	 * Méthode permettant d'afficher la vue
	 */
	public void showView(){
		this.view.showFrame();
	}

	/**
	 * Méthode vérifiant la bonne valeur des différents champs rentrés par l'utilisateur
	 * @param row La taille en X de la grille
	 * @param ligne La taille en Y de la grille
	 * @param IATime Le temps accordé à l'IA
	 * @return le booléen confirmant que les champs sont corrects
	 */
	protected boolean verifyFields(int row, int ligne, int IATime){
		boolean result = true;
		String message = "";

		if(!this.benchMarkOver){
			result = false;
			message += "Calcul du BenchMark en cours veuillez patienter !!" + "\n";
		}

		if(row > GameSettings.BOARD_MAX_SIZE_X || row < GameSettings.BOARD_MIN_SIZE_X){
			result = false;
			message += TextManager.ERROR_INIT_ROW_OUT_OF_LIMIT + "\n";
		}

		if(ligne > GameSettings.BOARD_MAX_SIZE_Y || ligne < GameSettings.BOARD_MIN_SIZE_Y){
			result = false;
			message += TextManager.ERROR_INIT_LIGNE_OUT_OF_LIMIT + "\n";
		}

		if(IATime > GameSettings.AI_THINKING_TIME_LIMIT_MAX || IATime < GameSettings.AI_THINKING_TIME_LIMIT_MIN){
			result = false;
			message += TextManager.ERROR_INIT_IA_THINKING_TIME_OUT_OF_LIMIT + "\n";
		}

		if(message.length() > 0)
			JOptionPane.showMessageDialog((InitGameViewImpl)this.view, message, TextManager.ERROR_INIT_TITLE_POPUP, JOptionPane.INFORMATION_MESSAGE);

		return result;
	}
	/**
	 * @see {@link com.view.event.InitGameButtonEventListener#onCancelButtonPressed}
	 */
	@Override
	public void onCancelButtonPressed() {
		this.view.hideFrame();
	}
	/**
	 * @see {@link com.view.event.InitGameButtonEventListener#onBenchMarkButtonPressed}
	 */
	@Override
	public void onBenchMarkButtonPressed(final JFormattedTextField AITime) {
		this.benchMark.showFrame();
		this.benchMark.setButtonListener(new BenchMarkViewButtonEventListener() {

			@Override
			public void onOkButtonPressed(BenchMarkResult result) {
				AITime.setValue(result.globalScore);
				benchMarkOver = true;
				benchMark.hideFrame();
				//on écrit dans un fichier annexe le résultat TODO
			}
		});
		this.benchMarkOver = false;
		this.benchMark.launchBenchMark();
	}

	/**
	 * @see {@link com.view.event.InitGameButtonEventListener#onValidButtonPressed}
	 */
	@Override
	public void onValidButtonPressed(int row, int ligne, int IATime,
			int HelpIADifficulty, String player1Name, boolean isPlayer1AI,
			int iaPlayer1Difficulty, String player2Name, boolean isPlayer2AI,
			int iaPlayer2Difficulty) {

		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();
		PieceFactory pieceFacto = FactoryProducer.getPieceFactory();
		GameSettings gameSetts = null;
		BoardObservable board = null;
		Player player1 = null, player2 = null;

		if(this.verifyFields(row, ligne, IATime)){
			try {
				board = bFacto.getInitialBoard(row, ligne);
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}

			try {
				if(isPlayer1AI){
					player1 = pFacto.getMachinePlayer(player1Name, BoardPublisher.WHITE_PLAYER, 1);
				}else{
					player1 = pFacto.getHumanPlayer(player1Name, BoardPublisher.WHITE_PLAYER, 1);
				}

				if(isPlayer2AI){
					player2 = pFacto.getMachinePlayer(player2Name, BoardPublisher.BLACK_PLAYER, 2);
				}else{
					player2 = pFacto.getHumanPlayer(player2Name, BoardPublisher.BLACK_PLAYER, 2);
				}

				gameSetts = gsFacto.getGameSettings(
						player1, 
						player2,
						board,
						IATime,
						HelpIADifficulty,
						pieceFacto.getArrayListOfPiece());

				if(isPlayer1AI)
					gameSetts.setPlayer1ArtificialIntelligenceDifficulty(iaPlayer1Difficulty);
				if(isPlayer2AI)
					gameSetts.setPlayer2ArtificialIntelligenceDifficulty(iaPlayer2Difficulty);

			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}

			this.view.hideFrame();
			this.event.initGameFinished(true, gameSetts);
		}
	}
}