package Factory;

import java.awt.Color;

import Model.HumanPlayer;
import Model.MachinePlayer;
import Model.Player;

/**
 * Fabrique d'un joueur.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class PlayerFactory extends Factory {

	@Override
	public Player getPlayer(String playerType, String playerLogin, Color c) {
		if (playerType == null)
			return null;
		
		if (playerType.equalsIgnoreCase("human"))
			return new HumanPlayer(playerLogin, c);
		else if (playerType.equalsIgnoreCase("machine"))
			return new MachinePlayer(playerLogin, c);
		
		return null;
	}

}
