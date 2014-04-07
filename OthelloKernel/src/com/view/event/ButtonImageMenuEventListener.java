package com.view.event;

import com.view.button.ImageButton;

/**
 * Interface contenant les méthodes soulevés par le menu du jeu
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public interface ButtonImageMenuEventListener {
	
	/**
	 * Méthode appelé par chaque appuie de bouton
	 * @param button Le bouton qui à soulevé cette méthode
	 * @param code Le code permettant d'identifier le bouton
	 */
	public void onButtonCliked(ImageButton button, int code);
	
	/**
	 * Méthode soulevé lors du clique du bouton de repise du jeu
	 */
	public void onPlayButtonCliked();
	
	/**
	 * Méthode soulevé lors du clique du bouton pour mettre en pause le jeu
	 */
	public void onPauseButtonCliked();
	
	/**
	 * Méthode soulevé lors du clique du bouton pour avancer d'un coup
	 */
	public void onForwardButtonCliked();
	
	/**
	 * Méthode soulevé lors du clique du bouton pour reculer d'un coup
	 */
	public void onBackButtonCliked();
	
	/**
	 * Méthode soulevé lors du clique du bouton pour recommencer la partie
	 */
	public void onResetButtonCliked();
	
	/**
	 * Méthode soulevé lors du clique du bouton pour lancer l'aide
	 */
	public void onHelpIAButtonCliked();
	
	/**
	 * Méthode soulevé lors du clique du bouton pour choisir une position pour recommencer la partie
	 */
	public void onPositionButtonCliked();
	
	/**
	 * Méthode soulevé lors du clique du bouton pour changer les joueurs
	 */
	public void onReversePlayerButtonCliked();
}
