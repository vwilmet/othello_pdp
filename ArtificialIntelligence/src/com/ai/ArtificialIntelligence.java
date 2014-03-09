package com.ai;
import java.awt.Point;
import java.util.List;
import java.util.Set;

import com.utils.WrongPlayablePositionException;

public interface ArtificialIntelligence {

		 /**
	     * Calcule le prochain mouvement grï¿½ce aux algorithmes de l'IA.
	     * @param player est un entier reprï¿½sentant le joueur effectuant le prochain mouvement.
	     * @return le prochain mouvement calculï¿½ par l'IA et s'il n'y a pas de prochain mouvement, null.
	     */
		public Point nextMove(Integer player);

		 /**
	     * Calcule une liste entiï¿½re de mouvement qui permettrait au joueur de gagner.
	     * @param player est un entier reprï¿½sentant le joueur effectuant le prochain mouvement.
	     * @return une liste de mouvement amenant le joueur ï¿½ gagner le jeu.
	     */
		public List<Point> nextMoves(Integer player);
		
		
		/**
	     * Renvoie un entier reprï¿½sentant le statut de la partie pour le joueur (0 pour perdant, 1 pour gagnant et 2 pour match-nul).
	     * @param player est un entier reprï¿½sentant le joueur demandant le statut de la partie de son point de vue.
	     * @return un entier reprï¿½sentant le statut.
	     */
		public Integer winStatus(Integer player);
		
		/**
	     * Initialise l'IA dans le but de calculer les mouvements
	     * @param whitePiece une liste de toutes les piï¿½ces du joueur 1 dï¿½jï¿½ sur le plateau.
	     * @param blackPiece une liste de toutes les piï¿½ces du joueur 2 dï¿½jï¿½ sur le plateau.
	     * @param boardWidth un entier reprï¿½sentant la largeur du plateau
	     * @param boardHeight un entier reprï¿½sentant la hauteur du plateau
	     * @return un boolï¿½en, vrai si l'initialisation a fini sans erreur, sinon faux.
	     */
		public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight);
		
		/**
		 * Pas sï¿½r d'ï¿½tre implï¿½menter donc pas encore retraduit
	     * Actualize the informations in order to recalculate moves
	     * @param whitePiece a list of all the pieces of the player 1.
	     * @param blackPiece a list of all the pieces of the player 2.
	     * @param boardWidth an integer representing the width of the board.
	     * @param boardHeight an integer representing the height of the board.
	     * @return a boolean, true if the initialization is finished without error, otherwise false.
	     */
		//public Boolean actualize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight); Pas sÃ»r de l'utilistÃ© encore
		
		/**
	     * Notifie ï¿½ l'IA le mouvement que le joueur a choisi.
	     * @param pos la position choisie par le joueur.
	     * @param player le joueur qui a effectuï¿½ le mouvement.
		 * @throws WrongPlayablePositionException 
	     */
		public void notifyChosenMove(Point pos, Integer player) throws WrongPlayablePositionException;
		
		/**
	     * Interrompt la recherche de mouvement de l'algorithme.
	     * @return un booleeb, vrai si l'algorithme a ete stoppe, faux sinon
	     */
		public Boolean completeReflexion();
		
		/**
	     * Choisit la difficultŽ de l'IA, c'est-ˆ-dire, l'algorithme utilisŽ
	     * @param difficulty un entier reprŽsentant la difficultŽ choisie pour le joueur
	     */
		public void chooseDifficulty(Integer difficulty);
				
		
	}

