package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.FactoryHandlerException;
import utils.GameControllers;
import utils.TextManager;

import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.player.HumanPlayer;
import com.model.player.MachinePlayer;
import com.model.player.Player;
import com.publisher.BoardPublisher;

public class LogicielTest {

	BoardObservable board = null;
	GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
	BoardFactory bFacto = FactoryProducer.getBoardFactory();
	PlayerFactory pFacto = FactoryProducer.getPlayerFactory();
	PieceFactory pieceFacto = FactoryProducer.getPieceFactory();
	GameSettings gameSettings;

	@Before
	public void setUp() throws Exception {

		try {
			board = bFacto.getInitialBoard(4, 4);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			this.gameSettings = gsFacto.getGameSettings(
					pFacto.getHumanPlayer("Harry POTTER", BoardPublisher.WHITE_PLAYER,1), 
					pFacto.getHumanPlayer("John DOE", BoardPublisher.BLACK_PLAYER,2),
					board,
					GameSettings.DEFAULT_IA_THINKING_TIME, 
					GameSettings.DEFAULT_IA_DIFFICULTY,
					pieceFacto.getArrayListOfPiece());

			GameControllers.setPlayablePiece(this.gameSettings);

		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCorrectSizeBoard() {
		try {
			board = bFacto.getInitialBoard(3, 8);
			assertTrue(TextManager.WRONG_GRID_SIZE_FR, board == null);
		} catch (FactoryHandlerException e) {
			fail(TextManager.WRONG_FACTORY_USES_FR);
		}

		try {
			board = bFacto.getInitialBoard(51, 20);
			assertTrue(TextManager.WRONG_GRID_SIZE_FR, board == null);
		} catch (FactoryHandlerException e) {
			fail(TextManager.WRONG_FACTORY_USES_FR);
		}

		try {
			board = bFacto.getBoard(2, 8, null);
			assertTrue(TextManager.WRONG_GRID_SIZE_FR, board == null);
		} catch (FactoryHandlerException e) {
			fail(TextManager.WRONG_FACTORY_USES_FR);
		}

		try {
			board = bFacto.getBoard(10, -1, null);
			assertTrue(TextManager.WRONG_GRID_SIZE_FR, board == null);
		} catch (FactoryHandlerException e) {
			fail(TextManager.WRONG_FACTORY_USES_FR);
		}
	}
	
	@Test
	public void testCorrectContentBoard() {
		try {
			board = bFacto.getInitialBoard(4, 4);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		assertTrue(board.getBlackPieces().size() == 2 && board.getWhitePieces().size() == 2);
	}

	@Test
	public void testCorrectPlayerContent() {
		Player player = this.gameSettings.getCurrentPlayer();
		assertTrue(player.getPlayerNumber() > 0 && player.getPlayerNumber() < 3);
		assertTrue(player.getColor().equals(BoardPublisher.WHITE_PLAYER) || player.getColor().equals(BoardPublisher.BLACK_PLAYER));
		assertTrue(player.getPlayerType() instanceof HumanPlayer || player.getPlayerType() instanceof MachinePlayer);
	}

	@Test
	public void testCorrectPlayablePosition() {
		//vérification à l'initialisation
		// un joueur n'ayant pas forcement un pion à jouer à tous ses tours on ne peut le généraliser
		int playedPiece = this.gameSettings.getGameBoard().getWhitePieces().size() + this.gameSettings.getGameBoard().getBlackPieces().size();
		if(playedPiece < (this.gameSettings.getGameBoard().getSizeX()*this.gameSettings.getGameBoard().getSizeY()))
			assertTrue(this.gameSettings.getGameBoard().getPlayablePieces().size() > 0);

	}

	public void testSetPlayablePiece(){
		
	}
	
	
	public void testGetReversePieceAround(){
		
	}
	
	public void testReverseInbetweenPieceAfterPlaying(){
		
	}
}
