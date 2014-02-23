package Factory;

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
	public Player getPlayer(String playerType, String playerLogin) {
		if (playerType == null)
			return null;
		
		if (playerType.equalsIgnoreCase("human"))
			return new HumanPlayer(playerLogin);
		else if (playerType.equalsIgnoreCase("machine"))
			return new MachinePlayer(playerLogin);
		
		return null;
	}

}
