import utils.Application;

import com.controller.test.GC;
import com.controller.test.GCShell;
import com.controller.test.GCView;
import com.model.BoardObservable;


/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	static BoardObservable b, b1;

	public static void main(String[] args) {
		Application app = Application.getInstance();
		app.calculateComponentSize();
		
		//GameController game = new GameController();
		GC g = new GCShell();
		g.test();
		
		GC h = new GCView();
		h.test();
		
		//////////////////////////////////////////////////////////////
		//TOUS CE QUI SUIT DOIT ÊTRE DANS LE CONTROLEUR (OU PRESQUE)//
		//////////////////////////////////////////////////////////////
		/*System.out.println("Othello Kernel");
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
		
		ChoosePositionView c = new ChoosePositionViewImpl(0, boards);
		c.showFrame();
		InitGameViewImpl vi = new InitGameViewImpl();
		vi.setButtonListener(new InitGameButtonEventListener() {
			
			@Override
			public void onValidButtonPressed(int row, int ligne, int IATime,
					String IADifficulty, String player1Name, String player2Name) {
				
			}
			
			@Override
			public void onCancelButtonPressed() {
				
			}
			
			@Override
			public void onBenchMarkButtonPressed(JFormattedTextField AITime) {
				System.out.println("bench!!");
			}
		});
		
		vi.showFrame();
		
		
		final BenchMarkView bench = new BenchMarkViewImpl();
		bench.setButtonListener(new BenchMarkViewButtonEventListener() {
			
			@Override
			public void onOkButtonPressed(BenchMarkResult result) {
				System.out.println(result.toString());
				bench.hideFrame();
			}
		});
		
//		 bench.showFrame();
//		 bench.launchBenchMark();
		
		GameCanvasMouseEventListener mouse = new GameCanvasMouseEventListener() {
			
			@Override
			public void onRightMouseButtonPressed(int x, int y) {
				System.out.println("Right button Position x:y =>" + x + ":" + y);
				b.setPiecePlayable(x, y);
			}
			
			@Override
			public void onLeftMouseButtonPressed(int x, int y) {
				System.out.println("Left button Position x:y =>" + x + ":" + y);
				b.setBlackPiece(x, y);
			}
		};
		
		GameView message = new GameViewImpl(b, null);
		message.setBoard(b);
		message.setGameMouseEventListener(mouse);
		
		message.showFrame();
		/*message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.addMessageToMessageList("toto");
		message.changeMessageViewContent("Petit message !!");
		message.changeStatViewMessage("stat");*/

		//System.out.println("Test de la factory : ");

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
		
		/*System.out.println("test de la lecure de partie");
		RestoreGameFactory rgFacto = FactoryProducer.getRestoreGameFactory();
		
		try {
			RestoreGame rg = rgFacto.getRestoreGame("saveFile2.xml");
			rg.loadGameFromBackupFile();
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		*/
		
	}
}
