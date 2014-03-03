package com.model.io;

import java.io.File;
import java.io.IOException;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.error_manager.Log;
import com.model.GameSettings;

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
	 * "<board></board>".
	 */
	private Element root;

	/**
	 * Attribut permettant la génération du XML avec la librairie JDOM.
	 */
	private org.jdom2.Document saveDoc;

	/**
	 * Attribut permettant de créer le nouvel Othellier en fonction des entrées
	 * utilisateur.
	 */
	private GameSettings gameSettings;

	/*
	 * public RestoreGame (String gameFileName){ SAXBuilder sbx = new
	 * SAXBuilder(); try { this.saveDoc = sbx.build(new File (gameFileName)); }
	 * catch (JDOMException e) { Log.error(e.getMessage()); e.printStackTrace();
	 * } catch (IOException e) { Log.error(e.getMessage()); e.printStackTrace();
	 * }
	 * 
	 * this.root = this.saveDoc.getRootElement();
	 * 
	 * XMLOutputter outputXML = new XMLOutputter(Format.getCompactFormat());
	 * System.out.println(outputXML.outputString(this.saveDoc)); }
	 */

	public RestoreGame(String gameFileName) {
		SAXBuilder sbx = new SAXBuilder();
		try {
			this.saveDoc = sbx.build(new File(gameFileName));
		} catch (JDOMException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		this.root = this.saveDoc.getRootElement();

		XMLOutputter outputXML = new XMLOutputter(Format.getCompactFormat());
		System.out.println(outputXML.outputString(this.saveDoc));
	}

	public String toString() {
		return null;
	}
}
