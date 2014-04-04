package com.ai.impl;

import java.awt.Point;
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
 */

/**
 * Classe qui implémente les méthodes de gestion des Intelligences Artificielles.
 * </br>Elle implémente l'interface {@link com.ai.ArtificialIntelligence}.
 * @author <ul><li>Nicolas Yvon</li></ul>
 * @version 1.0
 */
public class ArtificialIntelligenceImpl implements ArtificialIntelligence {

	/**
	 * Stratégie utilisée par l'intelligence artificielle
	 */
	ArtificialIntelligenceStrategy ai;

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#nextMove}
	 */
	@Override
	public Point nextMove(Integer player) {
		if(ai != null)
			return ai.nextMove(player);
		return null;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#quickNextMove}
	 */
	@Override
	public Point quickNextMove(Integer player) {
		if(ai != null)
			return ai.quickNextMove(player);
		return null;
	}
	
	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#winStatus}
	 */
	@Override
	public Integer winStatus(Integer player) {
		if(ai != null)
			return ai.winStatus(player);
		return 2;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#initialize}
	 */
	@Override
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece,
			Integer boardWidth, Integer boardHeight) {
		if(ai == null)
			ai = new RandomAI();
		return ai.initialize(whitePiece, blackPiece, boardWidth, boardHeight);
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#notifyChosenMove}
	 */
	@Override
	public void notifyChosenMove(Point pos, Integer player)
			throws WrongPlayablePositionException {
		if(ai != null)
			ai.notifyChosenMove(pos, player);
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#completeReflexion}
	 */
	@Override
	public Boolean completeReflexion() {
		if(ai != null)
			return ai.completeReflexion();
		return false;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#chooseDifficulty}
	 */
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
			ai = new RandomAI();
			break;
		}

	}


	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#undoMove}
	 */
	@Override
	public void undoMove() {
		if(ai != null)
			ai.undoMove();
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.ai.ArtificialIntelligence} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.ai.ArtificialIntelligence#setMaxTime}
	 */
	@Override
	public void setMaxTime(Integer time) {
		ai.setMaxTime(time);
	}

	@Override
	public Boolean initialize(ArtificialIntelligenceImpl ai) {
		if(ai.ai instanceof RandomAI)
			return this.ai.initialize((RandomAI) ai.ai);
		else if(ai.ai instanceof NextBestMoveAI)
			return this.ai.initialize((NextBestMoveAI) ai.ai);
		else if(ai.ai instanceof BruteForceAI){
			return this.ai.initialize((BruteForceAI) ai.ai);
		}
		else 
			return false;
	}

	public String boardToString(){
		return ai.boardToString();
	}


	
}
