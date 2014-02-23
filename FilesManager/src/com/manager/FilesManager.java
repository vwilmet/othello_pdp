package com.manager;

/**
 * Interface de gestion du controleur de fichier
 * Cette interface contient les messages d'erreurs ainsi que de nombreuses constantes modifiables
 * Par exemple : l'extension des fichiers de sauvegarde, les noms par défault, les messages d'erreurs, ... .
 * @author <ul><li>Morgane Badré</li><li>Vincent Wilmet</li></ul>
 * @version 1.0
 */
public interface FilesManager {
	
	/**
	 * Constante qui contient le message d'erreur renvoyé lors de l'echec de lecture du fichier demandée
	 */
	public static final String ERROR_ON_LOAD = "Une erreur est survenue pendant la lecture du fichier";
	public static final String ERROR_WRITING = "Une erreur est survenue pendant l'écriture dans le fichier";
	public static final String ERROR_WRONG_EXTENSION = "L'extension du fichier demandée est mauvaise";
	public static final String IO_ERROR = "Une opération d'entré-sortie à échoué!";
	public static final String ERROR = "Une erreur inconnue est survenue!";
	
	/**
	 * Constante qui représente le nom de fichier par défaut pour la sauvegarde automatique demandé par le logiciel
	 */
	public static final String DEFAULT_AUTOSAVE_FILENAME = "autosave";
	public static final String DEFAULT_FILENAME_EXTENSION = ".xml";
	/**
	 * Constante qui représente le chemin par défault qui seras utilisé pour sauvegarder les fichiers de sauvegarde automatique
	 */
	public static final String DEFAULT_FILE_PATH = ".";
	
	/**
	 * Méthode initialisant le module :<ul>
	 * <li> préparation du fichier de sauvegarde automatique </li>
	 * <li> préparation du tableau de gestion de plusieurs fichiers </li>
	 * @param enableVerification Un booléen qui, si passé à true, autorise la vérification de l'intégrité du fichier
	 * @return true si l'initilisation s'est effectué correctement
	 */
	public boolean init(boolean enableVerification);
	
	/**
	 * Voir la documentation {@link com.manager.FilesManager#init}
	 * @param autosaveFilename Le nom du fichier pour les sauvegardes automatiques. Par défaut le nom est othello_autosave-{date:AAAA-MM-JJ-HH-MM-SS}.mnbv
	 * @param enableVerification Un booléen qui, si passé à true, autorise la vérification de l'intégrité du fichier
	 */
	public boolean init(String autosaveFilename, boolean enableVerification);
	
	/**
	 * Méthode appelée lors d'une demande de sauvegarde utilisateur
	 * <br><b>Attention : </b> Cette méthode efface complétement le fichier de sauvegarde où elle enregistre les données  
	 * @param name Le nom du fichier 
	 * @param path Le chemin vers le fichier
	 * @param data Le contenu à écrire dans le fichier de sauvegarde
	 * @return true si la sauvegarde à réussi, false sinon
	 */
	public boolean save(String name, String path, Object data);
	/**
	 * Cette méthode permet de sauvegarder dans un fichier automatique une sauvegarde rapide.
	 * <br>Elle fonctionne en utilisant deux fichiers ; elle sauve alternativement dans un fichier puis dans l'autre. Ainsi, en cas d'erreur, seulement un coup est perdu.
	 * @param data Le contenue à écrire dans le fichier de sauvegarde
	 * @return true si la sauvegarde automatique à réussi, false sinon
	 */
	public boolean autoSave(Object data);
	
	/**
	 * Cette méthode lit le contenue d'un fichier et le retourne sous forme de String
	 * @param name Le nom du fichier à charger
	 * @param path Le chemin vers le fichier
	 * @return Le contenu complet du fichier 
	 */
	public String load(String name, String path);
	
	/**
	 * Méthode qui permet d'autoriser la vérification de la bonne écriture dans le fichier du contenue en se basant sur la longueur du contenue à écrire
	 */
	public void enableVerification();
	/**
	 * Méthode qui permet d'éviter la vérification de la bonne écriture dans le fichier
	 */
	public void disableVerification();
}
