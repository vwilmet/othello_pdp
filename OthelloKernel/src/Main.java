import utils.Application;

import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.player.Player;

/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application app = Application.getInstance();
		
		System.out.println("Othello Kernel");
		
		System.out.println("Test de la factory : ");
		
		PieceFactory p = FactoryProducer.getPieceFacory();
		
		PlayerFactory playerFactory = FactoryProducer.getFacory("player");
		Player joueur1 = playerFactory.getPlayer("human", "batmann33");
		Player joueur2 = playerFactory.getPlayer("machine", "John");

		System.out.println(joueur1.toString());
		System.out.println(joueur2.toString());
	}

}
