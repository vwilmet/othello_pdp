package com.ai.impl;

import java.awt.Point;
import java.util.List;
import java.util.Set;

import com.ai.ArtificialIntelligence;
import com.aistrategy.ArtificialIntelligenceStrategy;
import com.aistrategy.impl.BruteForceAI;
import com.aistrategy.impl.NextBestMoveAI;
import com.aistrategy.impl.RandomAI;
import com.utils.WrongPlayablePositionException;

/**
 * @mainpage Module ArtificialIntelligence
 * 
 * Ce module a pour but de proposer une gestion des Intelligence Artificielles  tel que :
 * <ul>
 * <li>gestion des différents algorithmes de recherche de solution</li>
 * <li>gestion de la difficulté de l'intelligence artificielle</li>
 * <li>calcule du/des prochain(s) meilleur(s) coup(s) selon l'algorithme</li>
 * </ul>
 * 
 * Le nom du fichier généré automatiquement est sous la forme : 
 * <i>nom</i>-<i>N°</i>-<i>AAAA</i>-<i>MM</i>-<i>JJ</i>-<i>HH</i>-<i>MM</i>-<i>SS</i>.<i>extension</i>
 * <br/><b>Exemple de BONNE utilisation du module : </b>
 * 
 *		FilesManager files = new FilesManagerImpl();
 *		files.init(false);
 *		
 *		if(files.autoSave("Texte 1"))
 *			System.out.println("OK!!");
 */

/**
 * Classe qui implémente les méthodes de gestion des Intelligences Artificielles
 * Elle implémente l'interface {@link com.manager.ArtifcialIntelligence}
 * @author <ul><li>Nicolas Yvon</li></ul>
 * @version 1.0
 */
public class ArtificialIntelligenceImplementation implements
		ArtificialIntelligence {
	
	ArtificialIntelligenceStrategy ai;

	@Override
	public Point nextMove(Integer player) {
		return ai.nextMove(player);
	}

	@Override
	public List<Point> nextMoves(Integer player) {
		return ai.nextMoves(player);
	}

	@Override
	public Integer winStatus(Integer player) {
		return ai.winStatus(player);
	}

	@Override
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		if(ai == null)
			ai = new RandomAI();
		return ai.initialize(whitePiece, blackPiece, boardWidth, boardHeight);
	}

	@Override
	public void notifyChosenMove(Point pos, Integer player)
			throws WrongPlayablePositionException {
		ai.notifyChosenMove(pos, player);
	}

	@Override
	public Boolean completeReflexion() {
		return ai.completeReflexion();
	}

	@Override
	public void chooseDifficulty(Integer difficulty) {
		switch(difficulty){
			case 0:
				ai = new RandomAI();
				break;
			case 1:
				ai = new NextBestMoveAI();
				break;
			case 2:
				ai = new BruteForceAI();
				break;
			default:
					break;
		}

	}

}
