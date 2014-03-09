package com.ai.impl;

import java.awt.Point;
import java.util.List;
import java.util.Set;

import com.ai.ArtificialIntelligence;
import com.aistrategy.ArtificialIntelligenceStrategy;
import com.aistrategy.impl.BruteForceAI;
import com.aistrategy.impl.RandomAI;
import com.utils.WrongPlayablePositionException;

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
				ai = new BruteForceAI();
				break;
			default:
					break;
		}

	}

}
