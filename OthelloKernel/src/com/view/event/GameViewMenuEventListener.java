package com.view.event;

/**
 * Interface contenant les méthodes soulevés lors du choix d'un item du menu générale
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface GameViewMenuEventListener {

	/**
	 * Méthode soulevé lors de la sélection de l'item "Nouvelle partie" dans le menu
	 */
	public void onNewGameItemMenuPressed();
	/**
	 * Méthode soulevé lors de la sélection de l'item "Enregistrer sous ..." dans le menu
	 */
	public void onSaveGameUnderItemMenuPressed();
	/**
	 * Méthode soulevé lors de la sélection de l'item "Ouvrir et continuer" dans le menu
	 */
	public void onOpenFileAndContinueItemMenuPressed();
	/**
	 * Méthode soulevé lors de la sélection de l'item "Ouvrir et choisir la position" dans le menu
	 */
	public void onOpenFileAndChoosePositionItemMenuPressed();
	/**
	 * Méthode soulevé lors de la sélection de l'item "Options" dans le menu
	 */
	public void onOptionItemMenuPressed();
	/**
	 * Méthode soulevé lors de la sélection de l'item "Aide" dans le menu
	 */
	public void onHelpItemMenuPressed();
	/**
	 * Méthode soulevé lors de la sélection de l'item "Configurer une partie" dans le menu
	 */
	public void onConfigureBoardItemMenuPressed();
	/**
	 * Méthode soulevé lors de la sélection de l'item "Enregistrer la suite de coup" dans le menu
	 */
	public void onSaveHistoryPositionItemMenuPressed();
}