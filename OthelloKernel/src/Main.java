import utils.Application;
import utils.FactoryHandlerException;

import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.RestoreGameFactory;
import com.model.factory.interfaces.SaveGameFactory;
import com.model.io.RestoreGame;
import com.model.io.SaveGame;


/**
 * @author Benjamin Letourneau
 * @version 1.0
 */
public class Main {

	static BoardObservable b, b1;

	public static void main(String[] args) {
		Log.reset();
		Application app = Application.getInstance();
		app.calculateComponentSize();
		//GameController game = new GameControllerGraphical();
		
		/*PieceFactory pieceFactory = FactoryProducer.getPieceFactory();
		Piece p1 = null, p2 = null,  p3 = null, p4 = null;
		List<Piece> pcs = new ArrayList<Piece>();
		
		try {
			p1 = pieceFactory.getWhitePiece(1, 1);
			p2 = pieceFactory.getBlackPiece(1, 2);
			p3 = pieceFactory.getWhitePiece(2, 2);
			p4 = pieceFactory.getBlackPiece(2, 1);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
		}
		
		pcs.add(p1);
		pcs.add(p2);
		pcs.add(p3);
		pcs.add(p4);
		
		try {
			
			BoardImpl b = new BoardImpl(4,4, pcs);
			BoardImpl bclone = (BoardImpl)b.clone();
			System.out.println(b.getBoard().hashCode());
			System.out.println(bclone.getBoard().hashCode());
			
			System.out.println(b);
			System.out.println(bclone);
			
			System.out.println("============On modifie bclone============");
			bclone.setBlackPiece(0, 0);
			
			System.out.println(b.getBoard()[0][0].hashCode());
			System.out.println(bclone.getBoard()[0][0].hashCode());
			
			System.out.println("========================");
			
			System.out.println("b : " + b);
			System.out.println("bclone : " + bclone);

			System.out.println("============List=OK===========");

			System.out.println(b.getInitialPiece().get(0).hashCode());
			System.out.println(bclone.getInitialPiece().get(0).hashCode());
			
			System.out.println("============Piece=OK===========");
			
			PieceImpl p = new PieceImpl(0, 0);
			p.setBlackPiece();
			
			PieceImpl pclone = p.clone();
			
			System.out.println(p);
			System.out.println(pclone);
			
			System.out.println("=====modif de p===");
			
			p.setWhitePiece();
			
			System.out.println(p);
			System.out.println(pclone);
			
		} catch (GameHandlerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		*/
		
		
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
		
		System.out.println("test de la lecure de partie");
		RestoreGameFactory rgFacto = FactoryProducer.getRestoreGameFactory();
		
		RestoreGame rg = null;
		
		try {
			rg = rgFacto.getRestoreGame("grille4x4.xml");
			rg.loadGameFromBackupFile();
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		
		System.out.println("test de la Ecriture de partie");
		SaveGameFactory sgFacto = FactoryProducer.getSaveGameFactory();
		SaveGame sg = null;
		
		try {
			sg = sgFacto.getSaveGame(rg.getGameSettings(),"fileTest");
			System.out.println("toto");
			sg.saveGameToBackupFile();
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
