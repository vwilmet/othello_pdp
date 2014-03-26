package com.publisher.generator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.error_manager.Log;
import com.manager.FilesManager;
import com.manager.FilesManagerImpl;
import com.publisher.Board;
import com.publisher.BoardPublisher;
import com.publisher.Player;
import com.publisher.utils.PostsPublisher;
import com.publisher.utils.Utils;

/**
 * Classe permettant de générer le nouvel Othellier formaté en XML.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class GenerateXML implements BoardPublisher {
	
	/**
	 * Attribut stoquant la racine du XML, en l'occurence notre balise "<board></board>".
	 */
	private Element root;
	
	/**
	 * Attribut permettant la génération du XML avec la librairie JDOM. 
	 */
	private org.jdom2.Document saveDoc;
	
	/**
	 * Attribut permettant de créer le nouvel Othellier en fonction des entrées utilisateur.
	 */
	private Board board;
	
	/**
	 * Constructeur de classe.
	 */
	public GenerateXML (){
		this.board = null;
		this.root = new Element(BoardPublisher.BOARD_PART);
		this.saveDoc = new Document (root);
	}
	
	/**
	 * Methode récupérant la taille de l'Othellier et le formate en XML.
	 * @return Element, la taille de l'Othellier en XML.
	 */
	private Element boardSizeInXML(){
		Element size = new Element(BoardPublisher.SIZE_PART);
		Element x = new Element (BoardPublisher.X_PART);
		Element y = new Element (BoardPublisher.Y_PART);
		
		x.setText(String.valueOf(this.board.getNbPieceX()));
		y.setText(String.valueOf(this.board.getNbPieceY()));
		
		size.addContent(x);
		size.addContent(y);
		
		return size;
	}
	
	/**
	 * Methode récupérant les pièces de l'Othellier afin de générer le XML associé.
	 * @return Element : Les pièces en XML.
	 */
	private Element boardPiecesInXML (String part){
		Element initialPieces = new Element(part);
		
		for (int i = 0 ; i < this.board.getNbPieceX() ; i++){
			for(int j = 0 ; j < this.board.getNbPieceY() ; j++){
				if (this.board.getGameBoard()[i][j] != 0){
					initialPieces.addContent(boardPieceAtInXML(i, j));
				}
			}
		}
		return initialPieces;
	}
	
	/**
	 * Methode récupérant une pièce de l'Othellier en fonction des positions passés en paramètre.
	 * Attention il faut être sur que la couleur ne soit pas 0 car 0 correspond à une place vide dans l'Othellier.
	 * @param i : int, coordonnée de la pièce à réccupérer suivant l'axe des abscisses de l'Othellier.
	 * @param j : int, coordonnée de la pièce à réccupérer suivant l'axe des ordonnées de l'Othellier.
	 * @return Element : La pièce en XML.
	 */
	private Element boardPieceAtInXML(int i, int j){
			
		Element x = new Element(BoardPublisher.X_PART);
		Element y = new Element(BoardPublisher.Y_PART);
		Element rgb = new Element(BoardPublisher.COLOR_PART); 
		Element piece = new Element (BoardPublisher.PIECE_PART);
		
		x.setText(String.valueOf(i));
		y.setText(String.valueOf(j));
		rgb.setText(String.valueOf(this.board.getGameBoard()[i][j]));
		
		piece.addContent(x);
		piece.addContent(y);
		piece.addContent(rgb);
		
		return piece;
	}
	
	/**
	 * Methode permettant de générer un joueur sous forme XML.
	 * @param p : Player, Joueur à formater en XML.
	 * @return Element : Player formaté en XML.
	 */
	private Element playerInXML(Player p){
		Element player = new Element(BoardPublisher.PLAYER_PART);
		
		Element login = new Element(BoardPublisher.PLAYER_LOGIN_PART);
		login.setText(p.getName());
		
		Element rgb = new Element(BoardPublisher.PLAYER_COLOR_PART);
		rgb.setText(p.getColor());
		
		Element type = new Element(BoardPublisher.PLAYER_TYPE_PART);
		type.setText(p.getType());
		
		Element num = new Element(BoardPublisher.PLAYER_NUMBER_PART);
		num.setText(String.valueOf(p.getNumber()));
		
		player.addContent(login);
		player.addContent(rgb);
		player.addContent(type);
		player.addContent(num);
		
		return player;
	}

	@Override
	public void boardMaker() {
		this.board = new Board();
		Element initial = new Element (BoardPublisher.INIT_PART);
		
		initial.addContent(boardSizeInXML());
		initial.addContent(boardPiecesInXML(BoardPublisher.PIECES_PART));
		initial.addContent(playerInXML(this.board.getPlayer1()));
		initial.addContent(playerInXML(this.board.getPlayer2()));
		initial.addContent(Utils.xmlSetIntValueToField(BoardPublisher.AI_LEVEL_PART, this.board.getAILevel()));
		initial.addContent(Utils.xmlSetIntValueToField(BoardPublisher.AI_THINKING_TIME_PART, this.board.getAIThinkingTime()));
		
		this.root.addContent(initial);
		this.root.addContent(boardPiecesInXML(BoardPublisher.PLAYED_PIECES_PART));
				
		FilesManager fmanager = new FilesManagerImpl();
		if (fmanager.save(this.board.getBoardFileName() + PostsPublisher.DOT_XML, PostsPublisher.DOT, this.toString()) == false){
			Log.error(PostsPublisher.SAVE_FATAL_ERROR_FR);
		}
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
