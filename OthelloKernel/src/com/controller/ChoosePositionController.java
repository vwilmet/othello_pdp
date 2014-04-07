package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.controller.interfaces.NotifyGameControllerGraphical;
import com.model.Board;
import com.model.BoardObservable;
import com.view.ChoosePositionViewImpl;
import com.view.event.ChoosePositionButtonEventListener;
import com.view.interfaces.ChoosePositionView;

/**
 * Classe qui définie le contrôleur qui gère le choix de la position à partir de la vue
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class ChoosePositionController implements ChoosePositionButtonEventListener {

	/**
	 * L'instance de l'objet unique de cette classe
	 */
	protected static ChoosePositionController instance;
	/**
	 * L'interface de communication avec le contrôleur principale
	 */
	protected NotifyGameControllerGraphical event;
	/**
	 * L'objet de la vue à afficher
	 */
	protected ChoosePositionView view;
	/**
	 * Entier contenant la position de l'othellier dans l'historique pour la vue
	 */
	protected int position;
	
	/**
	 * Méthode permettant de n'instancier qu'un objet de cette classe afin d'éviter la duplication du contrôleur
	 * @return L'objet de la classe
	 */
	public static ChoosePositionController getInstance(){
		if(instance == null)
			instance = new ChoosePositionController();
		return instance;
	}
	
	/**
	 * Constructeur privée pour le design pattern Singleton
	 */
	private ChoosePositionController() {}

	/**
	 * Méthode définissant l'interface permettant de communiquer avec le contrôleur principale pour notifier de la fin du choix de la position
	 * @param event
	 */
	public void setEvent(NotifyGameControllerGraphical event){
		this.event = event;
	}
	
	/**
	 * Méthode permettant de lancer la vue de gestion de position
	 * @param position La position dans l'historique de l'othellier du jeu
	 * @param boards La liste de l'enemble des boards
	 */
	public void setHistory(int position, List<BoardObservable> boards){
		this.position = position;
		this.view = new ChoosePositionViewImpl(position, (ArrayList<BoardObservable>)boards);
		this.view.setButtonListener(this);
		this.view.showPlayablePiece(false);
	}
	/**
	 * Méthode permettant d'afficher la vue
	 */
	public void showView(){
		this.view.showFrame();
	}
	/**
	 * @see {@link com.view.event.ChoosePositionButtonEventListener#onValidButtonPressed}
	 */
	@Override
	public void onValidButtonPressed(Board board, int position) {
		event.chooseGameBoardFinished(true, (BoardObservable)board, position);
		this.view.hideFrame();
	}
	/**
	 * @see {@link com.view.event.ChoosePositionButtonEventListener#onCancelButtonPressed}
	 */
	@Override
	public void onCancelButtonPressed() {
		this.view.hideFrame();
	}
}
