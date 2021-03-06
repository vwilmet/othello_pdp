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
import utils.GameControllers;
import utils.GameHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.manager.FilesManager;
import com.manager.FilesManagerImpl;
import com.model.Board;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.model.player.Player;
import com.publisher.BoardPublisher;

/**
 * Gestion de la lecture de fichier de sauvegarde du jeu.
 * @author <ul><li>Benjamin Letourneau</li></ul>
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
	 * Fabrique de Pion.
	 */
	PieceFactory pieceFacto;
	
	/**
	 * Fabrique de Joueur.
	 */
	PlayerFactory playerFacto;

	/**
	 * Constructeur de la Classe RestoreGame.
	 * 
	 * @param gameFileName : String contenant le nom du fichier sauvegarde à
	 * changer dans le jeu.
	 */
	public RestoreGame() {
		this.gsFacto = FactoryProducer.getGameSettingsFactory();
		this.bFacto = FactoryProducer.getBoardFactory();
		this.pieceFacto = FactoryProducer.getPieceFactory();
		this.playerFacto = FactoryProducer.getPlayerFactory();
		this.gameFileName = null;
	}
	
	/**
	 * Accesseur (lecture) du modèle de jeu de la partie.
	 * @return GameSettings : modèle de la partie.
	 */
	public GameSettings getGameSettings(){
		return this.gameSettings;
	}
	
	/**
	 * Méthode à appeler pour lancer le chargement de la sauvegarde.
	 * @param String : chemin du fichier à charger.
	 */
	public void loadGameFromBackupFile(String gameFileName){
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

		gameGrid = fmanager.load(this.gameFileName, "");
		
		if (gameGrid.equals(FilesManager.ERROR_ON_LOAD_FILE_NOT_EXISTING)){
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE, 
					TextManager.FILE_NOT_EXISTING_1_FR + this.gameFileName + TextManager.FILE_NOT_EXISTING_2_FR );
		}
		else if (gameGrid.equals(FilesManager.ERROR_ON_LOAD_ON_READING)){
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);
		}

		try {
			this.saveDoc = sbx.build(new ByteArrayInputStream(gameGrid.getBytes()));
		} catch (JDOMException e) {
			throw new GameHandlerException(
					GameHandlerException.ERROR_WRONG_FORMAT_SAVE_GAME_FILE, e.getMessage());
		} catch (IOException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * Méthode qui réccupère les informations dans la partie initiale du
	 * fichier de sauvegarde.
	 */
	private void xmlGetFileContent() throws GameHandlerException {
		Element initPart = this.root.getChild(BoardPublisher.INIT_PART);

		if (initPart == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		int[] gridSize = null;
		int aIHelpLevel = 0, aIP1Level = 0, aIP2Level = 0, aIThinkingTime = 0;
		List<Player> players = null;
		List<Piece> playedPieces = null;
		List<Piece> history = null;
		List<BoardObservable> historyBoard = null;
		BoardObservable board = null;

		/* BOARD SIZE */
		try {
			gridSize = xmlGetBoardSize(initPart);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		/* AI HELP LEVEL */
		try {
			aIHelpLevel = xmlGetIntValueFromField(initPart, BoardPublisher.AI_HELP_LEVEL_PART);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		/* AI P1 LEVEL */
		try {
			aIP1Level = xmlGetIntValueFromField(initPart, BoardPublisher.AI_PLAYER1_LEVEL_PART);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		/* AI P2 LEVEL */
		try {
			aIP2Level = xmlGetIntValueFromField(initPart, BoardPublisher.AI_PLAYER2_LEVEL_PART);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* AI THINKING TIME */
		try {
			aIThinkingTime = xmlGetIntValueFromField(initPart, BoardPublisher.AI_THINKING_TIME_PART);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* INITIAL PIECES */
		try {
			this.initialPieces = xmlGetPiecesFromPart(initPart.getChild(BoardPublisher.PIECES_PART), false);
			
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* PLAYERS */
		players = xmlGetPlayers(initPart);
		if (((ArrayList<Player>)(players)).size() < 2)
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE, TextManager.ERROR_ABOUT_PLAYER_NUMBER_FR);

		/* PLAYED PIECES */
		try {
			playedPieces = xmlGetPiecesFromPart(this.root.getChild(BoardPublisher.PLAYED_PIECES_PART), false);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		/* HISTORY -> FACULTATIF */
		try {
			history = xmlGetPiecesFromPart(this.root.getChild(BoardPublisher.HISTORY_PART),true);
			
		} catch (GameHandlerException e) {
			try {
				history = pieceFacto.getArrayListOfPiece();
				
			} catch (FactoryHandlerException e1) {
				Log.error(e.getMessage());
				e1.printStackTrace();
			}		
		}

		/* Construction de la board */
		try {
			board = bFacto.getBoard(gridSize[0], gridSize[1], playedPieces);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		/* Construction de GameSettings */
		 try { 
			 BoardObservable btmp = null;
			 
			 this.gameSettings = gsFacto.getGameSettings(((ArrayList<Player>)(players)).get(0), ((ArrayList<Player>)(players)).get(1), board, aIThinkingTime, aIHelpLevel, history);

			 try {
				 btmp = bFacto.getBoard(board.getSizeX(), board.getSizeY(), this.initialPieces); 
			 }
			 catch (FactoryHandlerException e){
				 Log.error(e.getMessage());
				 e.printStackTrace();
			 }
			 
			 try{
				 historyBoard = (ArrayList<BoardObservable>) bFacto.getBoardList();
			 }
			 catch (FactoryHandlerException e){
				 Log.error(e.getMessage());
				 e.printStackTrace();
			 }
			 
			 historyBoard.add((BoardObservable) btmp.clone());
			 
			 for (Piece p : this.gameSettings.getGameHistory()){
				 if(p.getColor() instanceof WhitePiece)
					 btmp.setWhitePiece(p.getPosX(), p.getPosY());
				 else
					 btmp.setBlackPiece(p.getPosX(), p.getPosY());
					 
				 GameControllers.reverseInbetweenPieceAfterPlaying(btmp, p.getPosX(), p.getPosY());
				 historyBoard.add((BoardObservable) btmp.clone());
			 }
			 
			 this.gameSettings.setGameBoardHistory(historyBoard);
			 			 
			 this.gameSettings.setPlayer1ArtificialIntelligenceDifficulty(aIP1Level);
			 this.gameSettings.setPlayer2ArtificialIntelligenceDifficulty(aIP2Level);
			 this.gameSettings.setHistoryPosition(history.size()-1);
		 } catch (FactoryHandlerException e) {
			 Log.error(e.getMessage()); 
			 e.printStackTrace(); 
		 }
	}

	/**
	 * Methode permettant de réccupérer les infoamations de la partie "Size" du fichier de sauvegarde de jeu.
	 * @param initPart : Element Partie "init" du fichier de sauvegarde.
	 * @return int[] : Taille de la grille de jeu.
	 * @throws GameHandlerException
	 */
	private int[] xmlGetBoardSize(Element initPart) throws GameHandlerException {
		Element sizePart = initPart.getChild(BoardPublisher.SIZE_PART);

		if (sizePart == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		int size[] = new int[2];

		size[0] = -1;
		size[1] = -1;

		try {
			size[0] = Integer.decode(sizePart.getChild(BoardPublisher.X_PART).getText());
			size[1] = Integer.decode(sizePart.getChild(BoardPublisher.Y_PART).getText());
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
	 * Methode permettant de réccupérer le contenu d'une partie contenant un
	 * ensemble de pion (Piece).
	 * @param part : Ememnt la partie contenant les pions 
	 * @param isHistoryPart : booléen indiquant si l'on est dans la partie 
	 * hystory (qui est facultative) dans le fichier de sauvegarde.
	 * @return List \<Piece\> : La Liste des pieces contenues dans la partie.
	 * @throws GameHandlerException
	 */
	private List<Piece> xmlGetPiecesFromPart(Element part, boolean isHistoryPart)
			throws GameHandlerException {

		if (part == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		ArrayList<Piece> pcs = new ArrayList<Piece>();

		List<Element> listePieces = part.getChildren(BoardPublisher.PIECE_PART);

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
			x = xmlGetIntValueFromField(piece, BoardPublisher.X_PART);
			y = xmlGetIntValueFromField(piece, BoardPublisher.Y_PART);
			c = xmlGetIntValueFromField(piece, BoardPublisher.COLOR_PART);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		if (c == 1) {
			try {
				p = this.pieceFacto.getWhitePiece(x, y);
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		} else if (c == 2) {
			try {
				p = this.pieceFacto.getBlackPiece(x, y);
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

		List<Element> listePlayers = initPart.getChildren(BoardPublisher.PLAYER_PART);

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
	 * Methode permettant de lire le contenu du fichier de sauvegarde correspondant à un joueur et de le créer.
	 * @param player : Element, partie du fichier correspondant au joueur.
	 * @return Player : joueur créé.
	 * @throws GameHandlerException
	 */
	private Player xmlGetPlayer(Element player) throws GameHandlerException {
		Player p = null;

		String name = null, color = null, type = null; 
		int number = -1;
		try {
			name = xmlGetStringValueFromField(player, BoardPublisher.PLAYER_LOGIN_PART);
			color = xmlGetStringValueFromField(player, BoardPublisher.PLAYER_COLOR_PART);
			type = xmlGetStringValueFromField(player, BoardPublisher.PLAYER_TYPE_PART);
			number = xmlGetIntValueFromField(player, BoardPublisher.PLAYER_NUMBER_PART);
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		if (name == null)
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);
		
		if (!color.equalsIgnoreCase(BoardPublisher.WHITE_PLAYER) && !color.equalsIgnoreCase(BoardPublisher.BLACK_PLAYER))
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);
		
		if (!type.equalsIgnoreCase(BoardPublisher.HUMAN_PLAYER) && !type.equalsIgnoreCase(BoardPublisher.MACHINE_PLAYER))
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);
		
		if (number < 1 || number > 2 )
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);
			
		if (type.equalsIgnoreCase(BoardPublisher.HUMAN_PLAYER)){
			try {
				p = this.playerFacto.getHumanPlayer(name, color, number);
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		}
		else if (type.equalsIgnoreCase(BoardPublisher.MACHINE_PLAYER)){
			try {
				p = this.playerFacto.getMachinePlayer(name, color, number);
			} catch (FactoryHandlerException e) {
				Log.error(e.getMessage());
				e.printStackTrace();
			}
		}		
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
	
	/**
	 * Methode utilitaire permettant de recupérer le contenu (String) d'une balise. 
	 * @param part : Element, partie dans laquelle il faut récupérer le contenu du champ xmlField.
	 * @param xmlField : String, nom des balises dans lesquelles il faut récupérer le contenu.
	 * @return String : Contenu de la balise.
	 * @throws GameHandlerException
	 */
	private String xmlGetStringValueFromField(Element part, String xmlField) throws GameHandlerException {
		Element elemField = part.getChild(xmlField);

		if (elemField == null)
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		String fieldValue = null;

		try {
			fieldValue = elemField.getText();
		} catch (NumberFormatException e) {
			Log.error(TextManager.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE_FR);
			e.printStackTrace();
		}

		if (fieldValue == null)
			throw new GameHandlerException(
					GameHandlerException.ERROR_DURING_THE_READ_OF_GAME_SAVE_FILE);

		return fieldValue;
	}
}
