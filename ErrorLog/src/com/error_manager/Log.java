package com.error_manager;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @mainpage Module d'erreurs
 * 
 * Ce module a pour but d'écrire les erreurs dans un fichier "log.txt" afin d'en garder une trace si on veut les consulter. 
 * 
 * <b>Exemple de BONNE utilisation du module : </b>
 *  
 *		Log.error("l'erreur");
 *		Log.reset();
 */

/**
 * Classe qui contient les deux méthodes static qui permettent d'écrire et réinitialiser le fichier d'erreurs
 * @author <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class Log {
	
	/**
	 * Variable contenant le nom du fichier où l'on va écrire les erreurs
	 */
	private static final String FILE_NAME = "log.txt";
	/**
	 * Variable contenant le message affiché dans la sortie d'erreur en cas d'échec
	 */
	private static final String ERROR = "Erreur lors de l'écriture dans le fichier d'erreur!";
	/**
	 * Variable contenant le message à afficher dans la sortie d'erreur si le fichier cible est introuvable
	 */
	private static final String ERROR_FILE_NOT_FOUND = "Le fichier demandé est introuvable!";
	
	/**
	 * Ecrit dans le fichier en sortie le texte entré en paramètre
	 * @param error La chaîne de caractères contenant l'erreur à écrire dans le fichier
	 */
	public static void error(String error){
		FileWriter file;
		
		try {
			file = new FileWriter(FILE_NAME, true);
			file.write(error + "\n");
			file.close();
		} catch (IOException e) {
			System.err.println(ERROR);
		}
	}
	
	/**
	 * Méthode réinitialisant le fichier d'erreur <br/>
	 * <b>Attention : </b> Cette méthode supprime complètement le contenu du fichier
	 */
	public static void reset(){
		PrintWriter pw;
		try {
			pw = new PrintWriter(FILE_NAME);
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println(ERROR_FILE_NOT_FOUND);
		}
	}
}