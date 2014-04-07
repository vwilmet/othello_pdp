package com.model.player;

import utils.TextManager;

import com.publisher.BoardPublisher;

/**
 * Classe MachinePlayer
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class MachinePlayer implements PlayerType {
	
	/**
	 * Permet de changer le type de joueur.
	 * @param p : Player, joueur à changer.
	 */
	@Override
	public void changeType(Player p) {
		p.setHuman();
	}
	
	/**
	 * Methode retournant le type de joueur.
	 * @return String : "machine" si le joueur est l'IA, "human" si le joueur est humain.
	 */
	@Override
	public String getPlayerType(){
		return BoardPublisher.MACHINE_PLAYER;	
	}
	
	/**
	 * Methode retournant le label correspondant au type du Joueur.
	 * @return String : Label correspondant au joueur.
	 */
	@Override
	public String toString() {
		return TextManager.MACHINE_LABEL_PLAYER;
	}
}
