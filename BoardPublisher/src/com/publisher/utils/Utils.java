package com.publisher.utils;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.jdom2.Element;

import com.error_manager.Log;

/**
 * Classe contenant uniquement des méthodes statiques.
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class Utils {

	/**
	 * Constante permettant de réinitialiser le champ de saisie utilisateur tant que celui ci est incorrect.
	 */
	private static final int BAD_USER_INPUT = 999;
	
	/**
	 * Méthode static interne à la classe permettant de réccupérer les entrées utilisateur et de gérer les exceptions.
	 * @return int : Le résultat correct de l'utilisateur.
	 */
	private static int getUserIntEntries(Scanner sc){
		int tmp = BAD_USER_INPUT;
		try {
			tmp = sc.nextInt();
		} catch (InputMismatchException e){
			Log.error(PostsPublisher.ERROR_RECOVERY_RESULT_FR);
			sc.nextLine();
			tmp = BAD_USER_INPUT;
		} catch (NoSuchElementException e){
			Log.error(PostsPublisher.INPUT_FATAL_ERROR_FR);
			e.printStackTrace();
		} catch (IllegalStateException e){
			Log.error(PostsPublisher.INPUT_FATAL_ERROR_FR);
			e.printStackTrace();
		}
		return tmp;
	}
	
	/**
	 * Méthode gérant le choix de l'utilisateur (autorise, ou n'autorise pas le choix de l'utilisateur si celui est incorrect).
	 * @param messageToPrint : String, message à afficher à l'utilisateur.
	 * @param optionalMessage : String, message optionnel à afficher à l'utilisateur.
	 * @param lowerBound : int, borne inférieure indiquant le choix minimal autorisé à l'utilisateur.
	 * @param upperBound : int, borne supérieure indiquant le choix maximal autorisé à l'utilisateur.
	 * @param sc : Scanner, permet de reccupérer l'entrée utilisateur.
	 * @return int : Le choix de l'utilisateur. 
	 */
	public static int getIntUserChoice (String messageToPrint, String optionalMessage, int lowerBound, int upperBound, Scanner sc){
		int tmp;
		System.out.println(messageToPrint);
		tmp = getUserIntEntries(sc);
		
		while (tmp < lowerBound || tmp > upperBound){
			if (optionalMessage != null) 
				System.out.println(optionalMessage);
			tmp = getUserIntEntries(sc);
		}
		return tmp;
	}
	
	/**
	 * Permet de créer une balise avec contenu dans le fichier de sauvegarde du jeu.
	 * @param part : String, nom de la balise xml.
	 * @param value : int, contenu de la balise.
	 * @return Element : Objet contenant la balise remplie.
	 */
	public static Element xmlSetIntValueToField(String part, int value){
		Element elem = new Element (part);
		elem.setText(String.valueOf(value));
		return elem;
	}
}
