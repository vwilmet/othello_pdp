package com.view.interfaces;

import com.model.BoardObservable;
import com.model.piece.Piece;
import com.view.event.ButtonImageMenuEventListener;
import com.view.event.GameCanvasMouseEventListener;
import com.view.event.GameViewMenuEventListener;

/**
 * Interface définissant les actions autorisées par la fenêtre générale du jeu
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface GameView {
	/**
	 * Méthode permettant d'afficher la vue
	 */
	public void showFrame();
	/**
	 * Méthode permettant de cacher la vue
	 */
	public void hideFrame();
	/**
	 * Méthode permettant de rafraichir la vue
	 */
	public void refresh();
	
	/**
	 * Cette méthode permet de lier l'othellier à afficher à l'interface utilisateur
	 * @param board L'othellier à afficher
	 */
	public void setBoard(BoardObservable board);
	/**
	 * Méthode qui initialise l'interface de communication pour les cliques souris sur l'othellier
	 * @param mouseEvent L'object interface
	 */
	public void setGameMouseEventListener(GameCanvasMouseEventListener mouseEvent);
	/**
	 * Méthode qui initialise l'interface de communication pour les buttons icones du menu de la vue
	 * @param event L'object interface
	 */
	public void setImageButtonEventListener(ButtonImageMenuEventListener event);
	
	/**
	 * Cette méthode permet de mettre l'affichage en mode "Pause"
	 * @param onPause Le booléen qui détermine si la vue doit être en pause
	 */
	public void setOnPause(boolean onPause);
	/**
	 * Cette méthode permet de définir la pièce conseillé par l'IA pour l'afficher
	 * @param p La pièce conseillé par l'IA qui doit être affiché
	 */
	public void setIAAdvisedPiece(Piece p);
	
	/**
	 * Méthode permettant d'ajouter à la liste d'évènements la chaîne de caractère : element
	 * @param element La chaîne de caractère à ajouter
	 */
	public void addMessageToMessageList(String element);
	/**
	 * Méthode permettant de changer le message de statistique du jeu
	 * @param message Le contenu du texte de statisque
	 */
	public void changeStatViewMessage(String message);
	/**
	 * Méthode permettant de changer le message de notification utilisateur
	 * @param content Le nouveau message à afficher
	 */
	public void changeMessageViewContent(String content);
	/**
	 * Méthode permettant de vider la liste des évènements
	 */
	public void resetMessageListContent();
	/**
	 * Méthode qui initialise l'interface de communication pour les buttons du menu général
	 * @param event L'object interface
	 */
	public void setMenuListener(GameViewMenuEventListener event);
	
	/**
	 * Cette méthode permet de gérer l'activation du bouton "Retour"
	 * @param enable Le booléen permettant de gérer l'activation du bouton de retour
	 */
	public void enableBackButton(boolean enable);
	/**
	 * Cette méthode permet de gérer l'activation du bouton "Suivant"
	 * @param enable Le booléen permettant de gérer l'activation du bouton pour avancer
	 */
	public void enableForwardButton(boolean enable);
	/**
	 * Cette méthode permet de gérer l'activation du bouton "Recommencer"
	 * @param enable Le booléen permettant de gérer l'activation du bouton pour recommencer la partie
	 */
	public void enableResetButton(boolean enable);
}
