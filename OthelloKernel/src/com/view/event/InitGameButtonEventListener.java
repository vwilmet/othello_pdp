package com.view.event;

import javax.swing.JFormattedTextField;

/**
 * Interface définissant les évènements de la vue d'initialisation
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface InitGameButtonEventListener {
	
	/**
	 * Cette évènement est soulevé lorsque l'utilisateur valide la création d'une partie
	 * @param row La largeur de la nouvelle grille
	 * @param ligne La longueur de la nouvelle grille
	 * @param IATime Le temps consacré à l'IA
	 * @param HelpIADifficulty La difficulté de l'IA d'aide
	 * @param player1Name Le nom du joueur 1
	 * @param isPlayer1AI Booléen permettant de savoir si le joueur 1 est un Humain ou une IA
	 * @param iaPlayer1Difficulty La difficulté de l'IA associé au joueur 2
	 * @param player2Name Le nom du joueur 2
	 * @param isPlayer2AI Booléen permettant de savoir si le joueur 1 est un Humain ou une IA
	 * @param iaPlayer2Difficulty La difficulté de l'IA associé au joueur 2
	 */
	public void onValidButtonPressed(int row, int ligne, int IATime, int HelpIADifficulty,
			String player1Name, boolean isPlayer1AI, int iaPlayer1Difficulty, 
			String player2Name, boolean isPlayer2AI, int iaPlayer2Difficulty);
	
	/**
	 * Cette méthode est appelé quand l'utilisateur annule la création d'une nouvelle partie
	 */
	public void onCancelButtonPressed();
	/**
	 * Cette méthode est appelé dès que le bouton pour fermer la vue de BenchMark est appuié après le calcul du BenchMark
	 * @param AITime Le champs qui doit contenir le temps consacrée à l'IA selon le BenchMark
	 */
	public void onBenchMarkButtonPressed(JFormattedTextField AITime);
}