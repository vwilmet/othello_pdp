package com.model.io;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import utils.GameHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.manager.FilesManager;
import com.manager.FilesManagerImpl;
import com.model.GameSettings;
import com.model.piece.Piece;
import com.model.player.Player;
import com.publisher.BoardPublisher;

/**
 * Classe permettant la sauvegarde d'une partie en cours.
 * @author <ul><li>Benjamin Letourneau</li>
 *         <li>Vincent Wilmet</li></ul>
 * @version 1.0
 */
public class SaveGame {
	
	/**
	 * Partie à sauvegarder.
	 */
	private GameSettings gameSettings;
	
	/**
	 * Nom du fichier dans lequel il faut effectuer la sauvegarde.
	 */
	private String saveFileName;
	
	/**
	 * Nom du fichier dans lequel il faut effectuer les sauvegardes automatique.
	 */
	private String autoSaveFileName;
	
	/**
	 * Attribut stoquant la racine du fichier de sauvegarde.
	 */
	private Element root;
	
	/**
	 * Attribut permettant la génération du XML avec la librairie JDOM. 
	 */ 
	private org.jdom2.Document saveDoc;
	
	private FilesManager fmanager;
	
	/**
	 * Constructeur de classe 
	 * @param gameSettings : GameSettings, Partie à sauvegarder.
	 * @param saveFileName : String, nom du fichier de sortie dans lequel il faut effectuer la sauvegarde.
	 * @throws GameHandlerException
	 */
	public SaveGame (GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.fmanager = new FilesManagerImpl();
		this.fmanager.init("", false);
		
		this.root = new Element(BoardPublisher.BOARD_PART);
		this.saveDoc = new Document (root);
	}
	
	public void setSaveFileName(String saveFileName){
		this.saveFileName = saveFileName;
	}
	
	public void setAutoSaveFileName(String autoSaveFileName){
		this.autoSaveFileName = autoSaveFileName;
	}
	
	
	/**
	 * Méthode permettant la sauvegarde d'une partie.
	 */
	public boolean saveGameToBackupFile(){
		this.root.addContent(makeInitPartInXML());
		
		try {
			this.root.addContent(makePlayedPiecesPart());
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		if (this.gameSettings.getGameHistory().size() > 0)
			this.root.addContent(makePiecesPartFromList(this.gameSettings.getGameHistory(), BoardPublisher.HISTORY_PART));
		
		if (fmanager.save(this.saveFileName + TextManager.DOT_XML, "", this.toString()) == false){
			Log.error(TextManager.SAVE_FATAL_ERROR_FR);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Méthode permettant la sauvegarde automatique d'une partie.
	 */
	public boolean autoSaveGameToBackupFile(){
		this.root.addContent(makeInitPartInXML());
		
		fmanager.init(this.autoSaveFileName, false);
		
		try {
			this.root.addContent(makePlayedPiecesPart());
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		if (this.gameSettings.getGameHistory().size() > 0)
			this.root.addContent(makePiecesPartFromList(this.gameSettings.getGameHistory(), BoardPublisher.HISTORY_PART));
		
		if (fmanager.autoSave(this.toString()) == false){
			Log.error(TextManager.AUTOSAVE_FATAL_ERROR_FR);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Methode permettant de générer la partie initiale du fichier de sauvegarde.
	 * @return Element : Partie de la sauvegarde correspondant à la partie initiale du jeu.
	 */
	private Element makeInitPartInXML(){
		
		Element init = new Element (BoardPublisher.INIT_PART);
		init.addContent(makeBardSizeInXML());
		init.addContent(makePiecesPartFromList(this.gameSettings.getGameBoard().getInitialPiece(), BoardPublisher.INIT_PART));
		init.addContent(makePlayerInXML(this.gameSettings.getFirstPlayer()));
		init.addContent(makePlayerInXML(this.gameSettings.getSecondPlayer()));
		
		try {
			init.addContent(xmlSetIntValueToField(BoardPublisher.AI_LEVEL_PART, this.gameSettings.getHelpAIDifficulty()));
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			init.addContent(xmlSetIntValueToField(BoardPublisher.AI_THINKING_TIME_PART, this.gameSettings.getAIThinkingTime()));
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return init; 
	}
	
	/**
	 * Methode permettant de générer les parties correspondant aux pions 
	 * dans le fichier de sauvegarde (en fonction de la liste de pieces 
	 * passée en paramètre).
	 * @param pcs : List<Piece>, pions dont il faut générer la sauvegarde.
	 * @param part : String, nom de la partie dans laquelle les pions vont
	 * être stoqués dans le fichier de sauvegarde.
	 * @return Element, partie de la sauvegarde correspondant aux pions.
	 */
	private Element makePiecesPartFromList(List<Piece> pcs, String part){
		Element pieces = new Element(part);
		List<Piece> tmp = new ArrayList<Piece>();
		
		tmp.addAll(pcs);
		
		for (Piece p : tmp){
			pieces.addContent(makePieceInXML(p));
		}		
		return pieces;
	}
	
	/**
	 * Méthode permettant de générer la partie du fichier de sauvegarde 
	 * correspondant à la taille d'une grille de jeu.
	 * @return Element, la partie en question.
	 */
	private Element makeBardSizeInXML(){
		Element size = new Element(BoardPublisher.SIZE_PART);
		Element x = new Element (BoardPublisher.X_PART);
		Element y = new Element (BoardPublisher.Y_PART);
		
		x.setText(String.valueOf(this.gameSettings.getGameBoard().getSizeX()));
		y.setText(String.valueOf(this.gameSettings.getGameBoard().getSizeY()));
		
		size.addContent(x);
		size.addContent(y);
		
		return size;
	}
	
	/**
	 * Méthode permettant de générer la partie crrespondant aux pions
	 * joués dur l'othelier à un instant t de la partie.
	 * @return Element : Partie du fichier de sauvegarde correspondant à
	 * l'othelier à un instant t du jeu. 
	 * @throws GameHandlerException
	 */
	private Element makePlayedPiecesPart() throws GameHandlerException {
		Element playedPieces = new Element(BoardPublisher.PLAYED_PIECES_PART);
		
		for (int i = 0; i < this.gameSettings.getGameBoard().getSizeX(); i++){
			for (int j = 0; j < this.gameSettings.getGameBoard().getSizeY(); j++){
				if (this.gameSettings.getGameBoard().getBoard()[i][j].getColor().getColor() > 0)
					playedPieces.addContent(makePieceInXML(this.gameSettings.getGameBoard().getBoard()[i][j]));
			}
		}
		
		return playedPieces;
	}
	
	/**
	 * Méthode permettant de  générer le contenu d'un pion pour la sauvegarde.
	 * @param p : Piece, pion dont il faut effectuer une sauvegarde.
	 * @return Element : Pion pret pour la sauvegarde.
	 */
	private Element makePieceInXML(Piece p){
		
		Element x = new Element(BoardPublisher.X_PART);
		Element y = new Element(BoardPublisher.Y_PART);
		Element color = new Element(BoardPublisher.COLOR_PART); 
		Element piece = new Element (BoardPublisher.PIECE_PART);
		
		x.setText(String.valueOf(p.getPosX()));
		y.setText(String.valueOf(p.getPosY()));
		color.setText(String.valueOf(p.getColor().getColor()));
		
		piece.addContent(x);
		piece.addContent(y);
		piece.addContent(color);
		
		return piece;
	}

	/**
	 * Méthode permettant de générer la partie concernant un joueur pour la sauvegarde.
	 * @param p : Player, joueur dont il faut effectuer une sauvegarde.
	 * @return Element : La partie correspondant au joueur.
	 */
	private Element makePlayerInXML(Player p){
		Element player = new Element(BoardPublisher.PLAYER_PART);
		
		Element login = new Element(BoardPublisher.PLAYER_LOGIN_PART);
		login.setText(p.getLogin());
		
		Element rgb = new Element(BoardPublisher.PLAYER_COLOR_PART);
		rgb.setText(p.getColor());
		
		Element type = new Element(BoardPublisher.PLAYER_TYPE_PART);
		type.setText(p.getPlayerType().getPlayerType());
		
		Element num = new Element(BoardPublisher.PLAYER_NUMBER_PART);
		num.setText(String.valueOf(p.getPlayerNumber()));
		
		player.addContent(login);
		player.addContent(rgb);
		player.addContent(type);
		player.addContent(num);
		
		return player;
	}
	
	/**
	 * Methode permettant d'affecter une valeur de type " int"à un champ du fichier de sauvegarde. 
	 * @param part : String, le nom de la partie.
	 * @param value : int, la valeur de cette partie.
	 * @return : Element, partie du fichier de sauvegarde correspondant.
	 * @throws GameHandlerException
	 */
	private Element xmlSetIntValueToField(String part, int value) throws GameHandlerException {
		if (part.isEmpty() || part == null) {
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_WRITE_OF_GAME_SAVE_FILE);	
		}
		
		Element elem = new Element (part);
		
		elem.setText(String.valueOf(value));
		
		return elem;
	}

	/**
	 * Méthode permettant de générer le plateau sous forme de XML (dans une String).
	 * @return String : Le xml correspondant à un plateau de jeu Othello.
	 */
	public String toString (){
		String resXML; 
		XMLOutputter outputXML = new XMLOutputter(Format.getCompactFormat());
		resXML = outputXML.outputString(this.saveDoc);
		return resXML;
	}
}
