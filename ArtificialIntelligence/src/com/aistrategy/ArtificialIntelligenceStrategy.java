package com.aistrategy;
import java.awt.Point;
import java.util.List;
import java.util.Set;

import com.aistrategy.impl.BruteForceAI;
import com.aistrategy.impl.NextBestMoveAI;
import com.aistrategy.impl.RandomAI;
import com.utils.WrongPlayablePositionException;

/**
 * Interface permettant d'implémenter les différentes stratégies/intelligences artificielles
 * </br>Cette interface contient les méthodes afin d'utiliser les 
 * différentes intelligences artificielles.
 * </br>Par exemple : la fonction nextMove permet de retourner le prochain mouvement calculer par l'IA...
 * 
 * @author <ul><li>Nicolas Yvon</li></ul>
 * @version 1.0
 */

public interface ArtificialIntelligenceStrategy {

	/**
	 * Calcule le prochain mouvement grâce aux algorithmes de l'IA.
	 * @param player est un entier représentant le joueur effectuant le prochain mouvement.
	 * @return le prochain mouvement calculé par l'IA et s'il n'y a pas de prochain mouvement, null.
	 */
	public Point nextMove(Integer player);

	/**
	 * Renvoie un entier représentant le statut de la partie pour le joueur (0 pour perdant, 1 pour gagnant et 2 pour match-nul).
	 * @param player est un entier représentant le joueur demandant le statut de la partie de son point de vue.
	 * @return un entier représentant le statut.
	 */
	public Integer winStatus(Integer player);

	/**
	 * Initialise l'IA dans le but de calculer les mouvements
	 * @param whitePiece une liste de toutes les pièces du joueur 1 déjà sur le plateau.
	 * @param blackPiece une liste de toutes les pièces du joueur 2 déjà sur le plateau.
	 * @param boardWidth un entier représentant la largeur du plateau
	 * @param boardHeight un entier représentant la hauteur du plateau
	 * @return un booléen, vrai si l'initialisation a fini sans erreur, sinon faux.
	 */
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight);

	public Boolean initialize(RandomAI random);
	
	public Boolean initialize(NextBestMoveAI nextBestMove);
	
	public Boolean initialize(BruteForceAI brute);


	/**
	 * Pas sûr d'être implémenter donc pas encore retraduit
	 * Actualize the informations in order to recalculate moves
	 * @param whitePiece a list of all the pieces of the player 1.
	 * @param blackPiece a list of all the pieces of the player 2.
	 * @param boardWidth an integer representing the width of the board.
	 * @param boardHeight an integer representing the height of the board.
	 * @return a boolean, true if the initialization is finished without error, otherwise false.
	 */
	//public Boolean actualize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight); Pas sûr de l'utilisté encore

	/**
	 * Notifie à l'IA le mouvement que le joueur a choisi.
	 * @param pos la position choisie par le joueur.
	 * @param player le joueur qui a effectué le mouvement.
	 * @throws WrongPlayablePositionException 
	 */
	public void notifyChosenMove(Point pos, Integer player) throws WrongPlayablePositionException;

	/**
	 * Interrompt la recherche de mouvement de l'algorithme.
	 * @return un booléen, vrai si l'algorithme a été stoppé, faux sinon
	 */
	public Boolean completeReflexion();


	/**
	 * Annule le dernier coup jouer.
	 */
	public void undoMove();

	/**
	 * Permet de donner un temps maximal de calcule pour l'IA en milliseconde.
	 * @param time le temps en milliseconde.
	 */
	public void setMaxTime(Integer time);
	
	public String boardToString();

}
