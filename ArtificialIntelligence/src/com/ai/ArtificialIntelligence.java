package com.ai;
import java.awt.Point;
import java.util.List;
import java.util.Set;

import com.utils.WrongPlayablePositionException;


public interface ArtificialIntelligence {

	 /**
     * Calcule le prochain mouvement gr�ce aux algorithmes de l'IA.
     * @param player est un entier repr�sentant le joueur effectuant le prochain mouvement.
     * @return le prochain mouvement calcul� par l'IA et s'il n'y a pas de prochain mouvement, null.
     */
	public Point nextMove(Integer player);

	 /**
     * Calcule une liste enti�re de mouvement qui permettrait au joueur de gagner.
     * @param player est un entier repr�sentant le joueur effectuant le prochain mouvement.
     * @return une liste de mouvement amenant le joueur � gagner le jeu.
     */
	public List<Point> nextMoves(Integer player);
	
	
	/**
     * Renvoie un entier repr�sentant le statut de la partie pour le joueur (0 pour perdant, 1 pour gagnant et 2 pour match-nul).
     * @param player est un entier repr�sentant le joueur demandant le statut de la partie de son point de vue.
     * @return un entier repr�sentant le statut.
     */
	public Integer winStatus(Integer player);
	
	/**
     * Initialise l'IA dans le but de calculer les mouvements
     * @param whitePiece une liste de toutes les pi�ces du joueur 1 d�j� sur le plateau.
     * @param blackPiece une liste de toutes les pi�ces du joueur 2 d�j� sur le plateau.
     * @param boardWidth un entier repr�sentant la largeur du plateau
     * @param boardHeight un entier repr�sentant la hauteur du plateau
     * @return un bool�en, vrai si l'initialisation a fini sans erreur, sinon faux.
     */
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight);
	
	/**
	 * Pas s�r d'�tre impl�menter donc pas encore retraduit
     * Actualize the informations in order to recalculate moves
     * @param whitePiece a list of all the pieces of the player 1.
     * @param blackPiece a list of all the pieces of the player 2.
     * @param boardWidth an integer representing the width of the board.
     * @param boardHeight an integer representing the height of the board.
     * @return a boolean, true if the initialization is finished without error, otherwise false.
     */
	//public Boolean actualize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight); Pas sûr de l'utilisté encore
	
	/**
     * Notifie � l'IA le mouvement que le joueur a choisi.
     * @param pos la position choisie par le joueur.
     * @param player le joueur qui a effectu� le mouvement.
	 * @throws WrongPlayablePositionException 
     */
	public void notifyChosenMove(Point pos, Integer player) throws WrongPlayablePositionException;
	
	/**
     * Interrompt la recherche de mouvement de l'algorithme.
     * @return un bool�en, vrai si l'algorithme a �t� stopp�, faux sinon
     */
	public Boolean completeReflexion();
			
	
}
