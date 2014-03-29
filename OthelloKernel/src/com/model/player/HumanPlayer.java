package com.model.player;

import utils.TextManager;

import com.publisher.BoardPublisher;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class HumanPlayer implements  PlayerType {

	@Override
	public void changeType(Player p) {
		p.setMachine();
	}

	@Override
	public String getPlayerType(){
		return BoardPublisher.HUMAN_PLAYER;
	}

	@Override
	public String toString() {
		return TextManager.HUMAN_LABEL_PLAYER;
	}
}
