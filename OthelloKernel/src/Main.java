import java.awt.Color;

import utils.Application;
import utils.FactoryHandlerException;

import com.error_manager.Log;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.piece.Piece;
import com.model.player.Player;



/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		Application app = Application.getInstance();
		
		System.out.println("Othello Kernel");
		
		System.out.println("Test de la factory : ");
		
		PieceFactory pieceFactory = FactoryProducer.getPieceFacory();
		Piece p1 = null;
		try {
			p1 = pieceFactory.getWhitePiece(10, 10, 1, 1);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
		}

		PlayerFactory playerFactory = FactoryProducer.getPlayerFacory();
		Player joueur1 = null;
		try {
			joueur1 = playerFactory.getHumanPlayer("batmann33", Color.white);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
		}
		Player joueur2 = null;
		try {
			joueur2 = playerFactory.getMachinePlayer("John", Color.black);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
		}

		System.out.println(joueur1.toString());
		System.out.println(joueur2.toString());
		System.out.println(p1.toString());
	}

}
