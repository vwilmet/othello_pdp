package com.ai;
import java.awt.Point;
import java.util.List;
import java.util.Set;

import com.ai.impl.ArtificialIntelligenceImpl;
import com.utils.WrongPlayablePositionException;

/**
 * Interface de gestion des différentes intelligences artificielles.
 * </br>Cette interface contient les méthodes afin d'utiliser les différentes intelligences artificielles. 
 * </br>Il est important d'utiliser la méthode notifyChosenMove {@link com.ai.ArtificialIntelligence#notifyChosenMove}
 * </br>afin de signaler à l'IA le coup choisit afin qu'elle positionne automatique la sentinelle au bon endroit dans l'arbre.
 * </br>L'arbre permet de prévoir les coups pour les 2 joueurs, il faut donc notifier à la fois le coup choisi par
 * </br> le joueur 1, mais aussi le joueur 2, qu'il soit un joueur réel ou simplement l'ordinateur.
 * </br>Par exemple : le choix de l'intelligence artficielle à utiliser grâce 
 * à la fonction chooseDifficulty...
 * 
 * @author <ul><li>Nicolas Yvon</li></ul>
 * @version 1.0
 */

public interface ArtificialIntelligence {

	/**
	 * Calcule le prochain mouvement grâce aux algorithmes de l'IA.
	 * @param player est un entier représentant le joueur effectuant le prochain mouvement.
	 * @return le prochain mouvement calculé par l'IA et s'il n'y a pas de prochain mouvement pour ce joueur, null.
	 */
	public Point nextMove(Integer player);

	/**
	 * Renvoie un entier représentant le statut de la partie pour le joueur (0 pour perdant, 1 pour gagnant et 2 pour match-nul).
	 * @param player est un entier représentant le joueur demandant le statut de la partie de son point de vue.
	 * @return un entier repr�sentant le statut.
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
	 * Initialise l'IA dans le but de calculer les mouvements
	 * @param ai, l'ia à partir de laquelle on initialise notre ia
	 * @return un booléen, vrai si l'initialisation a fini sans erreur, sinon faux.
	 */
	public Boolean initialize(ArtificialIntelligenceImpl ai);

	/**
	 * Notifie à l'IA le mouvement que le joueur a choisi.
	 * @param pos la position choisie par le joueur.
	 * @param player le joueur qui a effectué le mouvement.
	 * @throws WrongPlayablePositionException 
	 */
	public void notifyChosenMove(Point pos, Integer player) throws WrongPlayablePositionException;

	/**
	 * Interrompt la recherche de mouvement de l'algorithme.
	 * @return un booleen, vrai si l'algorithme a ete stoppe, faux sinon
	 */
	public Boolean completeReflexion();

	/**
	 * Choisit la difficulté de l'IA, c'est-à-dire, l'algorithme utilisé
	 * @param difficulty un entier représentant la difficulté choisie pour le joueur
	 */
	public void chooseDifficulty(Integer difficulty);

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

