package com.model.io;

import java.util.ArrayList;

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

/**
 * Classe permettant la sauvegarde d'une partie en cours.
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class SaveGame {
	
	private GameSettings gameSettings;
	
	private String saveFileName;
	
	/**
	 * Attribut stoquant la racine du fichier de sauvegarde.
	 */
	private Element root;
	
	/**
	 * Attribut permettant la génération du XML avec la librairie JDOM. 
	 */ 
	private org.jdom2.Document saveDoc;
	
	
	/**
	 * Constructeur de classe.
	 * @param gameSettings
	 */
	public SaveGame (GameSettings gameSettings, String saveFileName) throws GameHandlerException {
		this.gameSettings = gameSettings;

		if(saveFileName.isEmpty() || saveFileName == null)
			throw new GameHandlerException(GameHandlerException.ERROR_DURING_THE_WRITE_OF_GAME_SAVE_FILE);
		
		this.saveFileName = saveFileName;
		
		this.root = new Element(TextManager.BOARD_PART);
		this.saveDoc = new Document (root);
	}
	
	
	/**
	 * Méthode permettant la sauvegarde d'une partie.
	 */
	public void saveGameToBackupFile(){
		this.root.addContent(makeInitPartInXML());
		
		try {
			this.root.addContent(makePlayedPiecesPart());
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		if (this.gameSettings.getGameHistory().size() > 0)
			this.root.addContent(makePiecesPartFromList((ArrayList<Piece>)this.gameSettings.getGameHistory(), TextManager.HISTORY_PART));
		
		FilesManager fmanager = new FilesManagerImpl();
		if (fmanager.save(this.saveFileName + TextManager.DOT_XML, TextManager.DOT, this.toString()) == false){
			Log.error(TextManager.SAVE_FATAL_ERROR_FR);
		}
	}
	
	private Element makeInitPartInXML(){
		
		Element init = new Element (TextManager.INIT_PART);
		init.addContent(makeBardSizeInXML());
		init.addContent(makePiecesPartFromList((ArrayList<Piece>)this.gameSettings.getGameBoard().getInitialPiece(), TextManager.INIT_PART));
		init.addContent(makePlayerInXML(this.gameSettings.getFirstPlayer()));
		init.addContent(makePlayerInXML(this.gameSettings.getSecondPlayer()));
		
		try {
			init.addContent(xmlSetIntValueToField(TextManager.AI_LEVEL_PART, this.gameSettings.getAIDifficulty()));
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			init.addContent(xmlSetIntValueToField(TextManager.AI_THINKING_TIME_PART, this.gameSettings.getAIThinkingTime()));
		} catch (GameHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		return init; 
	}
	
	private Element makePiecesPartFromList(ArrayList<Piece> pcs, String part){
		Element pieces = new Element(part);
		for (Piece p : pcs){
			pieces.addContent(makePieceInXML(p));
		}		
		return pieces;
	}
	
	private Element makeBardSizeInXML(){
		Element size = new Element(TextManager.SIZE_PART);
		Element x = new Element (TextManager.X_PART);
		Element y = new Element (TextManager.Y_PART);
		
		x.setText(String.valueOf(this.gameSettings.getGameBoard().getSizeX()));
		y.setText(String.valueOf(this.gameSettings.getGameBoard().getSizeY()));
		
		size.addContent(x);
		size.addContent(y);
		
		return size;
	}
	
	private Element makePlayedPiecesPart() throws GameHandlerException {
		Element playedPieces = new Element(TextManager.PLAYED_PIECES_PART);
		
		for (int i = 0; i < this.gameSettings.getGameBoard().getSizeX(); i++){
			for (int j = 0; j < this.gameSettings.getGameBoard().getSizeY(); j++){
				if (this.gameSettings.getGameBoard().getBoard()[i][j].getColor().getColor() == 0)
					throw new GameHandlerException(GameHandlerException.WRONG_PIECE_COLOR);
				playedPieces.addContent(makePieceInXML(this.gameSettings.getGameBoard().getBoard()[i][j]));
			}
		}
		
		return playedPieces;
	}
	
	private Element makePieceInXML(Piece p){
		
		Element x = new Element(TextManager.X_PART);
		Element y = new Element(TextManager.Y_PART);
		Element color = new Element(TextManager.COLOR_PART); 
		Element piece = new Element (TextManager.PIECE_PART);
		
		x.setText(String.valueOf(p.getPosX()));
		y.setText(String.valueOf(p.getPosY()));
		color.setText(String.valueOf(p.getColor().getColor()));
		
		piece.addContent(x);
		piece.addContent(y);
		piece.addContent(color);
		
		return piece;
	}

	private Element makePlayerInXML(Player p){
		Element player = new Element(TextManager.PLAYER_PART);
		
		Element login = new Element(TextManager.PLAYER_LOGIN_PART);
		login.setText(p.getLogin());
		
		Element rgb = new Element(TextManager.PLAYER_COLOR_PART);
		rgb.setText(p.getColor());
		
		Element type = new Element(TextManager.PLAYER_TYPE_PART);
		type.setText(p.getPlayerType().getPlayerType());
		
		Element num = new Element(TextManager.PLAYER_NUMBER_PART);
		num.setText(String.valueOf(p.getPlayerNumber()));
		
		player.addContent(login);
		player.addContent(rgb);
		player.addContent(type);
		player.addContent(num);
		
		return player;
	}
	
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
