package com.model.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

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
 * Gestion de la lecture de fichier de sauvegarde du jeu.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class RestoreGame {

	/**
	 * Attribut stoquant la racine du de notre fichier de sauvegarde (format XML).
	 */
	private Element root;
	
	/**
	 * Attribut stoquant la liste des coups initiaux du plateau.
	 */
	private List<Piece> initialPieces;
	
	/**
	 * Variable stoquant l'historique de jeu de la partie sauvegardée.
	 */
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
	 * @param gameFileName : String contenant le nom du fichier sauvegarde à
	 * changer dans le jeu.
	 */
	public RestoreGame(String gameFileName) {
		this.gsFacto = FactoryProducer.getGameSettingsFactory();
		this.bFacto = FactoryProducer.getBoardFactory();
		this.pFacto = FactoryProducer.getPieceFactory();
		this.gameFileName = gameFileName;
	}
	
	/**
	 * Méthode à appeler pour lancer le chargement de la sauvegarde.
	 */
	public void loadGameFromBackupFile(){
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

		gameGrid = fmanager.load(this.gameFileName, TextManager.DOT_SLASH);
		
		if (gameGrid.equals(FilesManager.ERROR_ON_LOAD_FILE_NOT_EXISTING)){
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE, 
					TextManager.FILE_NOT_EXISTING_1_FR + this.gameFileName + TextManager.FILE_NOT_EXISTING_2_FR );
		}
		else if (gameGrid.equals(FilesManager.ERROR_ON_LOAD_ON_READING)){
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);
		}

		try {
			this.saveDoc = sbx.build(new ByteArrayInputStream(gameGrid
					.getBytes()));
		} catch (JDOMException e) {
			throw new GameHandlerException(
					GameHandlerException.ERROR_WRONG_FORMAT_SAVE_GAME_FILE,
					e.getMessage());
		} catch (IOException e) {
			/*
			 * DEBUG
			 */
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
		
		Element initPart = this.root.getChild(TextManager.INIT_PART);

		if (initPart == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		int[] gridSize = null;
		int aILevel = 0, aIThinkingTime = 0;
		List<Player> players = null;
		List<Piece> playedPieces = null;
		BoardObservable board = null;

		/* BOARD SIZE */
		try {
			gridSize = xmlGetBoardSize(initPart);
			/*
			 * DEBUG ...
			 */
			System.out.println("DEBUG  : size de la grille " + gridSize[0] + " : " + gridSize[1]);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* AI LEVEL */
		try {
			aILevel = xmlGetIntValueFromField(initPart, TextManager.AI_LEVEL_PART);
			/*
			 * DEBUG
			 */
			System.out.println("DEBUG  : AILevel " + aILevel);
			
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* AI THINKING TIME */
		try {
			aIThinkingTime = xmlGetIntValueFromField(initPart, TextManager.AI_THINKING_TIME_PART);
			/*
			 * DEBUG
			 */
			System.out.println("DEBUG  : aIThinkingTime " + aIThinkingTime);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* INITIAL PIECES */
		try {
			this.initialPieces = xmlGetPiecesFromPart(initPart.getChild(TextManager.PIECES_PART), false);
			/*
			 * DEBUG
			 */
			System.out.println("DEBUG  : pieces ");
			for (Piece p : this.initialPieces)
				System.out.println(p.toString());
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* PLAYERS */
		players = xmlGetPlayers(initPart);
		
		if (((ArrayList<Player>)(players)).size() < 2)
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE, TextManager.ERROR_ABOUT_PLAYER_NUMBER_FR);
		/*
		 * DEBUG
		 */
		System.out.println("DEBUG  : Players");
		for (Player p : players)
			System.out.println(p.toString());
		 

		/* PLAYED PIECES */
		try {
			playedPieces = xmlGetPiecesFromPart(this.root.getChild(TextManager.PLAYED_PIECES_PART), false);
			/*
			 * DEBUG 
			 */
			System.out.println("DEBUG  : playedPcs ");
			for (Piece p : playedPieces)
				System.out.println(p.toString());
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* HISTORY -> FACULTATIF */
		try {
			this.history = xmlGetPiecesFromPart(this.root.getChild(TextManager.HISTORY_PART),false);
			/*
			 * DEBUG
			 */
			System.out.println("DEBUG  : History ");
			for (Piece p : this.history)
				System.out.println(p.toString());

		} catch (GameHandlerException e) {
			/*
			 * ON NE FAIT RIEN COMME CETTE BALISE EST FACULTATIVE
			 */			
		}

		/* Construction de la board */
		try {
			board = bFacto.getBoard(gridSize[0], gridSize[1], playedPieces);
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

	/**
	 * Methode permettant de réccupérer les infoamations de la partie "Size" du fichier de sauvegarde de jeu.
	 * @param initPart : Element Partie "init" du fichier de sauvegarde.
	 * @return int[] : Taille de la grille de jeu.
	 * @throws GameHandlerException
	 */
	private int[] xmlGetBoardSize(Element initPart) throws GameHandlerException {
		Element sizePart = initPart.getChild(TextManager.SIZE_PART);

		if (sizePart == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		int size[] = new int[2];

		size[0] = -1;
		size[1] = -1;

		try {
			size[0] = Integer.decode(sizePart.getChild(TextManager.X_PART).getText());
			size[1] = Integer.decode(sizePart.getChild(TextManager.Y_PART).getText());
		} catch (NumberFormatException e) {
			Log.error(TextManager.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE_FR);
			e.printStackTrace();
		}

		if (size[0] == -1 || size[1] == -1)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		return size;
	}

	/**
	 * Methode permettant de réccupérer le contenu d'une partie contenant un ensemble de pion (Piece).
	 * @param part : Ememnt la partie contenant les pions 
	 * @param isHistoryPart : booléen indiquant si l'on est dans la partie Hystory (qui est facultative) dans le fichier de sauvegarde.
	 * @return List \<Piece\> : La Liste des pieces contenues dans la partie.
	 * @throws GameHandlerException
	 */
	private List<Piece> xmlGetPiecesFromPart(Element part, boolean isHistoryPart)
			throws GameHandlerException {

		if (part == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		ArrayList<Piece> pcs = new ArrayList<Piece>();

		List<Element> listePieces = part.getChildren(TextManager.PIECE_PART);

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

	/**
	 * Methode permettant de réccupérer le contenu d'un pion (piece).
	 * @param piece : Piece dont il faut récupérer les informations.
	 * @return Piece : le pion créé à partir des informations.
	 * @throws GameHandlerException
	 */
	private Piece xmlGetPiece(Element piece) throws GameHandlerException {
		Piece p = null;

		int c = -1, x = -1, y = -1;
		try {
			x = xmlGetIntValueFromField(piece, TextManager.X_PART);
			y = xmlGetIntValueFromField(piece, TextManager.Y_PART);
			c = xmlGetIntValueFromField(piece, TextManager.COLOR_PART);
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

	/**
	 * Méthode permettant de réccupérer les deux utilisateurs dans le fichier de sauvegarde.
	 * @param initPart : Element, partie du fichier de sauvegarde dans lequel se trouve la partie player.
	 * @return List<Player> : Une liste de deux éléments correspondant aux deux joueurs.
	 */
	private List<Player> xmlGetPlayers(Element initPart) {
		
		ArrayList<Player> players = new ArrayList<Player>();

		List<Element> listePlayers = initPart.getChildren(TextManager.PLAYER_PART);

		Iterator<Element> i = listePlayers.iterator();

		while (i.hasNext()) {
			try {
				players.add(xmlGetPlayer((Element) i.next()));
			} catch (GameHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		}	
		
		return players;
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 * @throws GameHandlerException
	 */
	private Player xmlGetPlayer(Element player) throws GameHandlerException {
		Player p = null;

		/*
		 * TODO 
		 */
//		int c = -1, x = -1, y = -1;
//		try {
//			x = xmlGetIntValueFromField(piece, TextManager.X_PART);
//			y = xmlGetIntValueFromField(piece, TextManager.Y_PART);
//			c = xmlGetIntValueFromField(piece, TextManager.COLOR_PART);
//		} catch (GameHandlerException e) {
//			Log.error(e.getMessage());
//			e.printStackTrace();
//		}
//
//		if (c == 1) {
//			try {
//				p = this.pFacto.getWhitePiece(x, y);
//			} catch (FactoryHandlerException e) {
//				Log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		} else if (c == 2) {
//			try {
//				p = this.pFacto.getBlackPiece(x, y);
//			} catch (FactoryHandlerException e) {
//				Log.error(e.getMessage());
//				e.printStackTrace();
//			}
//		} else {
//			throw new GameHandlerException(
//					GameHandlerException.WRONG_INITIAL_PIECE_COLOR);
//		}
		return p;
	}

	/**
	 * Méthode Permettant de récupérer la valeur d'un champ du fichier de sauvegarde.
	 * @param part : Element, la partie dans laquelle il faut récupérer le contenu du champ.
	 * @param xmlField : String, nom du champ dans lequel il faut récupérer la valeur. 
	 * @return int : Contenu du champ. Si le champ n'existe pas, une exception est retournée.
	 * @throws GameHandlerException
	 */
	private int xmlGetIntValueFromField(Element part, String xmlField) throws GameHandlerException {
		Element elemField = part.getChild(xmlField);

		if (elemField == null)
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

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
