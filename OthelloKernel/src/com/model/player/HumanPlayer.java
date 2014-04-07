package com.model.player;

import utils.TextManager;

import com.publisher.BoardPublisher;

/**
 * Classe HumanPlayer
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class HumanPlayer implements  PlayerType {

	/**
	 * Permet de changer le type de joueur.
	 * @param p : Player, joueur à changer.
	 */
	@Override
	public void changeType(Player p) {
		p.setMachine();
	}

	/**
	 * Methode retournant le type de joueur.
	 * @return String : "machine" si le joueur est l'IA, "human" si le joueur est humain.
	 */
	@Override
	public String getPlayerType(){
		return BoardPublisher.HUMAN_PLAYER;
	}

	/**
	 * Methode retournant le label correspondant au type du Joueur.
	 * @return String : Label correspondant au joueur.
	 */
	@Override
	public String toString() {
		return TextManager.HUMAN_LABEL_PLAYER;
	}
}
