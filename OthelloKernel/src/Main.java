import utils.Application;

/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		Application app = Application.getInstance();
		
		System.out.println("Othello Kernel");
		
		System.out.println("Test de la factory : ");
		
		/*PieceFactory pieceFactory = FactoryProducer.getPieceFacory();
		Piece p1 = pieceFactory.getWhitePiece(10, 10, 1, 1);

		PlayerFactory playerFactory = FactoryProducer.getPlayerFacory();
		Player joueur1 = playerFactory.getHumanPlayer("batmann33", Color.white);
		Player joueur2 = playerFactory.getMachinePlayer("John", Color.black);

		System.out.println(joueur1.toString());
		System.out.println(joueur2.toString());
		System.out.println(p1.toString());*/
	}

}
