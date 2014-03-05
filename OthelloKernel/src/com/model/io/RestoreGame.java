package com.model.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import utils.FactoryHandlerException;
import utils.GameHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.manager.FilesManager;
import com.manager.FilesManagerImpl;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.piece.Piece;
import com.model.player.Player;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class RestoreGame {

	/**
	 * Attribut stoquant la racine du XML, en l'occurence notre balise
	 * <board></board>.
	 */
	private Element root;
	private List<Piece> playedPieces;
	private List<Piece> history;

	/**
	 * Attribut permettant la génération du XML avec la librairie JDOM.
	 */
	private org.jdom2.Document saveDoc;

	/**
	 * Variable contenant le nom de ficheier de sauvegarde.
	 */
	String gameFileName;

	/**
	 * Attribut permettant de créer le nouvel Othellier en fonction des entrées
	 * utilisateur.
	 */
	private GameSettings gameSettings;

	/**
	 * Fabrique de GameSettings.
	 */
	GameSettingsFactory gsFacto;

	/**
	 * Fabrique de Board.
	 */
	BoardFactory bFacto;

	/**
	 * Fabrique de Piece.
	 */
	PieceFactory pFacto;

	/**
	 * Constructeur de la Classe RestoreGame.
	 * 
	 * @param gameFileName
	 *            : String contenant le nom du fichier sauvegarde à changer dans
	 *            le jeu.
	 */
	public RestoreGame(String gameFileName) {

		this.gameFileName = gameFileName;

		try {
			loadXmlFile();
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		try {
			this.root = this.saveDoc.getRootElement();
		} catch (IllegalStateException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			xmlGetFileContent();
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant de charger et de parser le ficher de sauvegarde au
	 * format XML.
	 * @throws GameHandlerException : Exception du jeu.
	 */
	private void loadXmlFile() throws GameHandlerException {
		SAXBuilder sbx = new SAXBuilder();

		String gameGrid = null;

		FilesManager fmanager = new FilesManagerImpl();
		fmanager.init(false);

		gameGrid = fmanager.load(this.gameFileName, "./");
		/*
		 * TODO Vérifier LE MESSAGE d'ERREUR if (gameGrid.equals(FilesManager.))
		 */

		try {
			this.saveDoc = sbx.build(new ByteArrayInputStream(gameGrid
					.getBytes()));
		} catch (JDOMException e) {
			throw new GameHandlerException(
					GameHandlerException.ERROR_WRONG_FORMAT_SAVE_GAME_FILE,
					e.getMessage());
		} catch (IOException e) {
			System.out.println("33333333");
			Log.error(e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Méthode qui réccupère les informations dans la partie initiale du
	 * ficheier de sauvegarde.
	 */
	private void xmlGetFileContent() throws GameHandlerException {
		this.gsFacto = FactoryProducer.getGameSettingsFactory();
		this.bFacto = FactoryProducer.getBoardFactory();
		this.pFacto = FactoryProducer.getPieceFactory();

		Element initPart = this.root.getChild("init");

		if (initPart == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		int[] gridSize = null;
		int aILevel = 0, aIThinkingTime = 0;
		Player p1 = null, p2 = null;
		List<Piece> initialPieces = null;
		BoardObservable board = null;

		/* BOARD SIZE */
		try {
			gridSize = xmlGetBoardSize(initPart);
			// **********//
			System.out.println("DEBUG  : size de la grille " + gridSize[0]
					+ " : " + gridSize[1]);
			// **********//
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* AI LEVEL */
		try {
			aILevel = xmlGetIntValueFromField(initPart, "AILevel");
			// **********//
			System.out.println("DEBUG  : AILevel " + aILevel);
			// **********//
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* AI THINKING TIME */
		try {
			aIThinkingTime = xmlGetIntValueFromField(initPart, "AIThinkingTime");
			// **********//
			System.out.println("DEBUG  : aIThinkingTime " + aIThinkingTime);
			// **********//
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* INITIAL PIECES */
		try {
			initialPieces = xmlGetPiecesFromPart(initPart.getChild("pieces"),
					false);
			// **********//
			System.out.println("DEBUG  : pieces ");
			for (Piece p : initialPieces)
				System.out.println(p.toString());
			// **********//
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/****
		 * A garder mais pour le moment je connait pas la structure du XML donc
		 * on va le faire plus tard
		 * 
		 * p1 = xmlGetFirstPlayer(initPart); p2 = xmlGetSecondPlayer(initPart);
		 * 
		 */

		/* PLAYED PIECES */
		try {
			this.playedPieces = xmlGetPiecesFromPart(
					this.root.getChild("playedPcs"), false);
			// **********//
			System.out.println("DEBUG  : playedPcs ");
			for (Piece p : this.playedPieces)
				System.out.println(p.toString());
			// **********//
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* HISTORY -> FACULTATIF */
		try {
			this.history = xmlGetPiecesFromPart(this.root.getChild("history"),
					false);
			// **********//
			System.out.println("DEBUG  : History ");
			for (Piece p : this.history)
				System.out.println(p.toString());
			// **********//
		} catch (GameHandlerException e) {
			// Log.error(e.getMessage());
			// e.printStackTrace();
		}

		/* Construction de la board */
		try {
			board = bFacto
					.getBoard(gridSize[0], gridSize[1], this.playedPieces);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		System.out.println(board.toString());

		/******************************************************************************/
		// System.exit(0);
		/******************************************************************************/

		// Construction de GameSettings
		/*
		 * try { this.gameSettings = gsFacto.getGameSettings(p1, p2, board,
		 * aIThinkingTime, aILevel); } catch (FactoryHandlerException e) {
		 * Log.error(e.getMessage()); e.printStackTrace(); }
		 */

	}

	private int[] xmlGetBoardSize(Element initPart) throws GameHandlerException {
		Element sizePart = initPart.getChild("size");

		if (sizePart == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		int size[] = new int[2];

		size[0] = -1;
		size[1] = -1;

		try {
			size[0] = Integer.decode(sizePart.getChild("x").getText());
			size[1] = Integer.decode(sizePart.getChild("y").getText());
		} catch (NumberFormatException e) {
			Log.error(TextManager.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE_FR);
			e.printStackTrace();
		}

		if (size[0] == -1 || size[1] == -1)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		return size;
	}

	private List<Piece> xmlGetPiecesFromPart(Element part, boolean isHistoryPart)
			throws GameHandlerException {

		if (part == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		ArrayList<Piece> pcs = new ArrayList<Piece>();

		List<Element> listePieces = part.getChildren("piece");

		Iterator<Element> i = listePieces.iterator();

		while (i.hasNext()) {
			try {
				pcs.add(xmlGetPiece((Element) i.next()));
			} catch (GameHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		}

		if (pcs.size() < 4 && !isHistoryPart)
			throw new GameHandlerException(
					GameHandlerException.WARNING_ABOUT_NUMBER_OF_INITIAL_PIECES);

		return pcs;
	}

	private Piece xmlGetPiece(Element piece) throws GameHandlerException {
		Piece p = null;

		int c = -1, x = -1, y = -1;
		try {
			x = xmlGetIntValueFromField(piece, "x");
			y = xmlGetIntValueFromField(piece, "y");
			c = xmlGetIntValueFromField(piece, "c");
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		if (c == 1) {
			try {
				p = this.pFacto.getWhitePiece(x, y);
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		} else if (c == 2) {
			try {
				p = this.pFacto.getBlackPiece(x, y);
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		} else {
			throw new GameHandlerException(
					GameHandlerException.WRONG_INITIAL_PIECE_COLOR);
		}
		return p;
	}

	private Player xmlGetFirstPlayer(Element initPart) {
		Element firstPlayer = initPart.getChild("1stPlayer");
		/*
		 * TODO ...
		 */
		return null;
	}

	private Player xmlGetSecondPlayer(Element initPart) {
		Element secondPlayer = initPart.getChild("2ndPlayer");
		/*
		 * TODO ..
		 */
		return null;
	}

	private int xmlGetIntValueFromField(Element part, String xmlField)
			throws GameHandlerException {
		Element elemField = part.getChild(xmlField);

		if (elemField == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		int fieldValue = -1;

		try {
			fieldValue = Integer.decode(elemField.getText());
		} catch (NumberFormatException e) {
			Log.error(TextManager.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE_FR);
			e.printStackTrace();
		}

		if (fieldValue == -1)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		return fieldValue;
	}
}
