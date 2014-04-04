package com.aistrategy;
import java.awt.Point;
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
	 * Interrompt tout algorithme lourd et calcule rapidement le prochain mouvement
	 * @param player est un entier représentant le joueur effectuant le prochain mouvement.
	 * @return le prochain mouvement calculé par l'IA et s'il n'y a pas de prochain mouvement, null.
	 */
	public Point quickNextMove(Integer player);
	
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

	/**
	 * Initialise l'IA dans le but de calculer les mouvements à partir d'une IA random
	 * @param random
	 * @return un booléen, vrai si l'initialisation a fini sans erreur, sinon faux.
	 */
	public Boolean initialize(RandomAI random);
	
	/**
	 * Initialise l'IA dans le but de calculer les mouvements à partir d'une IA random
	 * @param nextBestMove l'ia nextBestMove
	 * @return un booléen, vrai si l'initialisation a fini sans erreur, sinon faux.
	 */
	public Boolean initialize(NextBestMoveAI nextBestMove);
	
	/**
	 * Initialise l'IA dans le but de calculer les mouvements à partir d'une IA force brute
	 * @param brute l'ia brute force
	 * @return un booléen, vrai si l'initialisation a fini sans erreur, sinon faux.
	 */
	public Boolean initialize(BruteForceAI brute);

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
	
}
