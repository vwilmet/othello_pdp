import java.util.ArrayList;
import java.util.List;

import utils.Application;
import utils.FactoryHandlerException;

import com.error_manager.Log;
import com.model.Board;
import com.model.BoardObservable;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.piece.Piece;
import com.view.GameView;
import com.view.GameViewImpl;
import com.view.InitGameView;
import com.view.event.GameCanvasMouseEventListener;

/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	static BoardObservable b, b1;

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
			p1 = pieceFactory.getWhitePiece(1, 1);
			p2 = pieceFactory.getBlackPiece(4, 2);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
		}
		
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		b = null;
		b1 = null;
		List<Piece> pcs = new ArrayList<Piece>();
		pcs.add(p1);
		pcs.add(p2);
		
		ArrayList<Board> boards = new ArrayList<Board>();
		try {
			b = bFacto.getBoard(5, 8, pcs);
			boards.add(b);
			b1 = bFacto.getBoard(5, 8, pcs);
			
			b1.setBlackPiece(2,1);
			boards.add(b1);
			boards.add(b1);
			boards.add(b1);
			boards.add(b1);
			boards.add(b1);
			for(int i = 0; i < 50; i++) 
				boards.add(b1);
			
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println(b.toString());
		
		//new ChoosePositionView(0, boards, null);
		
		InitGameView vi = new InitGameView();
		
		vi.showFrame();
		
		GameCanvasMouseEventListener mouse = new GameCanvasMouseEventListener() {
			
			@Override
			public void onRightMouseButtonPressed(int x, int y) {
				System.out.println("Right button Position x:y =>" + x + ":" + y);
			}
			
			@Override
			public void onLeftMouseButtonPressed(int x, int y) {
				System.out.println("Left button Position x:y =>" + x + ":" + y);
			}
		};
		
		GameView message = new GameViewImpl(b, null, mouse);
		message.showFrame();
		/*message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.changeMessageViewContent("Petit message !!");
		message.changeStatViewMessage("stat");*/

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
