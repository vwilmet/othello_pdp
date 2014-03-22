package com.publisher.utils;



/**
 * Classe de gestion d'exceptions dans le BoardPublisher.
 * @author <ul><li>Benjamin Letourneau </li></ul>
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BPHandlerException extends Exception {
	
	/**
	 * Constantes  d'erreurs
	 */
	public static final int ERROR = 0, ERROR_DURING_LOAD_BOARD_FILE = 1;
	
	/**
	 * Permet de stocker la constante correspondant à l'erreur réccupérée. 
	 */
	private int error;
	
	/**
	 * Permet de stocker un message complémentaire au message basique de l'erreur.
	 */
	private String message;

	/**
	 * Constructeur de classe.
	 * @param error : int, numéro de l'erreur.
	 */
	public BPHandlerException(int error) {
		this.error = error;
		this.message = "";
	}

	/**
	 * Constructeur de classe.
	 * @param error : int, numéro de l'erreur.
	 * @param message : String, message complémentaire au message de base de l'erreur.
	 */
	public BPHandlerException(int error, String message) {
		this.error = error;
		this.message = message;
	}

	/**
	 * Accesseur (lecture) sur le message d'erreur. 
	 */
	public String getMessage() {
		switch (this.error) {
		case ERROR_DURING_LOAD_BOARD_FILE :
			return PostsPublisher.ERROR_DURING_LOAD_BOARD_FILE_FR + this.message;
		case ERROR:
		default:
			return PostsPublisher.ERROR_FR + this.message;
		}
	}
}
