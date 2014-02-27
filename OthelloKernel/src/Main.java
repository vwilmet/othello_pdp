import utils.Application;

import com.view.GameView;
import com.view.event.MouseEventListener;
import com.view.event.ViewMessageContentHandler;

/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		Application app = Application.getInstance();
		
		System.out.println("Othello Kernel");
		
		
		MouseEventListener mouse = new MouseEventListener() {
			
			@Override
			public void onRightMouseButtonPressed(int x, int y) {
				System.out.println("Right button Position x:y =>" + x + ":" + y);
			}
			
			@Override
			public void onLeftMouseButtonPressed(int x, int y) {
				System.out.println("Left button Position x:y =>" + x + ":" + y);
			}
		};
		
		ViewMessageContentHandler message = new GameView(app.getScreenWidth()-100, app.getScreenHeight()-100, null, null, mouse);
		
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.changeMessageViewContent("Petit message !!");
		message.changeStatViewMessage("stat");
		
		System.out.println("Test de la factory : ");
		
		/*System.out.println("Test de la factory : ");

		PieceFactory pieceFactory = FactoryProducer.getPieceFactory();
		Piece p1 = null, p2 = null;

		try {
			p1 = pieceFactory.getWhitePiece(10, 10, 4, 4);
			p2 = pieceFactory.getBlackPiece(10, 10, 4, 5);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
		}

		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		Board b = null;
		List<Piece> pcs = new ArrayList<Piece>();
		pcs.add(p1);
		pcs.add(p2);
		try {
			b = bFacto.getBoard(10, 10, pcs);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		System.out.println(b.toString()); */
		
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
