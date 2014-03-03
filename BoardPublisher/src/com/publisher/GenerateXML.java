package com.publisher;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.error_manager.Log;
import com.manager.FilesManager;
import com.manager.FilesManagerImpl;

/**
 * Classe permettant de générer le nouvel Othellier formaté en XML.
 * @author Benjamin Letourneau
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
		this.root = new Element("board");
		this.saveDoc = new Document (root);
	}
	
	/**
	 * Methode récupérant la taille de l'Othellier et le formate en XML.
	 * @return Element, la taille de l'Othellier en XML.
	 */
	private Element boardSizeInXML(){
		Element size = new Element("size");
		Element x = new Element ("x");
		Element y = new Element ("y");
		
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
	private Element boardPiecesInXML (){
		Element initialPieces = new Element("pieces");
		
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
			
		Element x = new Element("x");
		Element y = new Element("y");
		Element rgb = new Element("rgb"); 
		Element piece = new Element ("piece");
		
		x.setText(String.valueOf(i));
		y.setText(String.valueOf(j));
		rgb.setText(String.valueOf(this.board.getGameBoard()[i][j]));
		
		piece.addContent(x);
		piece.addContent(y);
		piece.addContent(rgb);
		
		return piece;
	}
	
	/**
	 * Méthode permettant de générer la partie du XML correspondant au premier joueur (Balises XML + contenu de la balise). 
	 * @return Element : l'objet contenant les informations du premier joueur.
	 */
	private Element boardFirstPlayerInXML (){
		Element player = new Element("player");
		player.setText(String.valueOf(this.board.getFirstPlayer()));
		return player;
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

	/**
	 * Méthode permettant de générer un Othellier.
	 */
	@Override
	public void boardMaker() {
		this.board = new Board();
		Element initial = new Element ("initial");
		
		initial.addContent(boardSizeInXML());
		initial.addContent(boardPiecesInXML());
		initial.addContent(boardFirstPlayerInXML());
		
		this.root.addContent(initial);
		
		FilesManager fmanager = new FilesManagerImpl();
		if (fmanager.save(this.board.getBoardFileName(), ".", this.toString()) == false){
			Log.error(PostsPublisher.SAVE_FATAL_ERROR_FR);
		}
	}
}
