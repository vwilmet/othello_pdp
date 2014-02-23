package Factory;

/**
 * Gestionnaire de la fabrique.
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public class FactoryProducer  {
	
	public static Factory getFacory(String factoryChoice){
		if (factoryChoice == null)
			return null;
		
		if (factoryChoice.equalsIgnoreCase("piece"))
			return new PieceFactory();
		else if (factoryChoice.equalsIgnoreCase("player"))
			return new PlayerFactory();
		else if (factoryChoice.equalsIgnoreCase("board") || 
					factoryChoice.equalsIgnoreCase("gameSettings") ||
					factoryChoice.equalsIgnoreCase("restoreGame") ||
					factoryChoice.equalsIgnoreCase("saveGame") ||
					factoryChoice.equalsIgnoreCase("gameSettingsController"))
			return new Factory();
		
		return null;
	}
}
