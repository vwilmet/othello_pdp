package com.model.player;

/**
 * Interface d'utilisation du type d'un joueur.
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public interface PlayerType {
	
	/**
	 * Permet de changer le type de joueur.
	 * @param p : Player, joueur à changer.
	 */
	public void changeType(Player p);
	
	/**
	 * Methode retournant le type de joueur.
	 * @return String : "machine" si le joueur est l'IA, "human" si le joueur est humain.
	 */
	public String getPlayerType();

}
