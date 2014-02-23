import Factory.Factory;
import Factory.FactoryProducer;
import Model.Player;

/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Othello Kernel");
		
		System.out.println("Test de la factory : ");
		
		Factory playerFactory = FactoryProducer.getFacory("player");
		
		Player joueur1 = playerFactory.getPlayer("human", "batmann33");
		Player joueur2 = playerFactory.getPlayer("machine", "John");

		System.out.println(joueur1.toString());
		System.out.println(joueur2.toString());
	}

}
