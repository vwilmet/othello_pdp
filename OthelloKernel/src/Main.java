import java.util.ArrayList;
import java.util.List;

import utils.Application;
import utils.FactoryHandlerException;

import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.piece.BlackPiece;
import com.model.piece.Piece;
import com.view.GameView;
import com.view.event.MouseEventListener;
import com.view.event.ViewMessageContentHandler;

/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	static BoardObservable b;

	public static void main(String[] args) {
		Application app = Application.getInstance();
		app.calculateComponentSize(5, 8);

		//////////////////////////////////////////////////////////////
		//TOUS CE QUI SUIT DOIT ÊTRE DANS LE CONTROLEUR (OU PRESQUE)//
		//////////////////////////////////////////////////////////////
		
		System.out.println("Othello Kernel");
		System.out.println("Test de la factory : ");

		PieceFactory pieceFactory = FactoryProducer.getPieceFactory();
		Piece p1 = null, p2 = null;

		try {
			p1 = pieceFactory.getWhitePiece(10, 10, 1, 1);
			p2 = pieceFactory.getBlackPiece(10, 10, 4, 2);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
		}

		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		b = null;
		List<Piece> pcs = new ArrayList<Piece>();
		pcs.add(p1);
		pcs.add(p2);

		try {
			b = bFacto.getBoard(5, 8, pcs);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		System.out.println(b.toString());

		MouseEventListener mouse = new MouseEventListener() {

			@Override
			public void onRightMouseButtonPressed(int x, int y) {
				System.out.println("Right button Position x:y =>" + x + ":" + y);
			}

			@Override
			public void onLeftMouseButtonPressed(int x, int y) {
				System.out.println("Left button Position x:y =>" + x + ":" + y);
				b.setBlackPiece(2,1);
				System.out.println(b.toString());
			}
		};

		ViewMessageContentHandler message = new GameView(b, null, null, mouse);

		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.changeMessageViewContent("Petit message !!");
		message.changeStatViewMessage("stat");

		System.out.println("Test de la factory : ");

		/**/

		/*
		 * PlayerFactory playerFactory = FactoryProducer.getPlayerFacory();
		 * 
		 * Player joueur1 = null; Player joueur2 = null;
		 * 
		 * try { joueur1 = playerFactory.getHumanPlayer("batmann33",
		 * Color.white); } catch (FactoryHandlerException e) {
		 * Log.error(e.getMessage()); }
		 * 
		 * try { joueur2 = playerFactory.getMachinePlayer("John", Color.black);
		 * } catch (FactoryHandlerException e) { Log.error(e.getMessage()); }
		 * 
		 * System.out.println(joueur1.toString());
		 * System.out.println(joueur2.toString());
		 * System.out.println(p1.toString());
		 */
	}

}
