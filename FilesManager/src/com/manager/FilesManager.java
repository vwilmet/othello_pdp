package com.manager;

/**
 * Interface de gestion du controleur de fichier
 * Cette interface contient les messages d'erreurs ainsi que 
 * de nombreuses constantes modifiables
 * Par exemple : l'extension des fichiers de sauvegarde, 
 * les noms par défaut, les messages d'erreurs, ... .
 * 
 * @author <ul><li>Morgane Badré</li><li>Vincent Wilmet</li></ul>
 * @version 1.0
 */
public interface FilesManager {
	
	/**
	 * Constante qui contient le message d'erreur renvoyé lors de l'échec de lecture du fichier demandé
	 */
	public static final String ERROR_ON_LOAD_ON_READING = "Une erreur est survenue pendant la lecture du fichier";
	
	/**
	 * Constante informant que le fichier demandé n'existe pas
	 */
	public static final String ERROR_ON_LOAD_FILE_NOT_EXISTING = "Le fichier demandé n'existe pas";
	
	/**
	 * Constante informant que le fichier de sauvegarde automatique n'existe pas car il n'as pas pu être créé 
	 */
	public static final String ERROR_ON_AUTOSAVE_FILE_NOT_EXISTING = "Le fichier de sauvegarde automatique n'as pas pu être ouvert!!";
	
	/**
	 * Constante contenant le message d'erreur sourvenu lors de l'échec d'écriture dans le fichier
	 */
	public static final String ERROR_WRITING = "Une erreur est survenue pendant l'écriture dans le fichier";
	
	/**
	 * Message d'erreur indiquant que l'extension du fichier est mauvaise
	 */
	public static final String ERROR_WRONG_EXTENSION = "La typographie du nom de fichier est mauvaise. Celle-ci doit être de la forme : [nom].[extension]";
	
	/**
	 * Constante relatant que l'opération d'entrée-sortie a échoué
	 */
	public static final String IO_ERROR = "Une opération d'entrée-sortie a échoué!";
	
	/**
	 * Constante informant q'une erreur inconnue est survenue
	 */
	public static final String ERROR = "Une erreur inconnue est survenue!";
	
	/**
	 * Constante qui représente le nom de fichier par défaut pour la sauvegarde automatique demandé par le logiciel
	 */
	public static final String DEFAULT_AUTOSAVE_FILENAME = "autosave";
	
	/**
	 * Constante qui représente le nom de fichier par défaut pour la sauvegarde demandé par l'utilisateur
	 */
	public static final String DEFAULT_SAVE_FILENAME = "save";
	
	public static final String DEFAULT_FILENAME_EXTENSION = ".xml";

	/**
	 * Constante informant que le fichier demandé n'existe pas
	 */
	public static final String ERROR_SAVE_FILE_NAME_NOT_PROVIDED = "Aucun nom de fichier fournis pour la sauvegarde, nom par défault utilisé : [date]_" + DEFAULT_SAVE_FILENAME + DEFAULT_FILENAME_EXTENSION;
	
	/**
	 * Constante qui représente le chemin par défaut qui sera utilisé pour sauvegarder les fichiers de sauvegarde automatique
	 */
	public static final String DEFAULT_FILE_PATH = ".";

	/**
	 * Méthode initialisant le module :<ul>
	 * <li> préparation du fichier de sauvegarde automatique </li>
	 * <li> préparation du tableau de gestion de plusieurs fichiers </li></ul>
	 * @param enableVerification Un booléen qui, si passé à true, autorise la vérification de l'intégrité du fichier
	 * @return true si l'initilisation s'est effectuée correctement
	 */
	public boolean init(boolean enableVerification);

	/**
	 * Voir la documentation {@link com.manager.FilesManager#init}
	 * @param autosaveFilename Le nom du fichier pour les sauvegardes automatiques. Par défaut le nom est othello_autosave-{date:AAAA-MM-JJ-HH-MM-SS}.mnbv
	 * @param enableVerification Un booléen qui, si passé à true, autorise la vérification de l'intégrité du fichier
	 * @return true si l'initilisation s'est effectuée correctement
	 */
	public boolean init(String autosaveFilename, boolean enableVerification);

	/**
	 * Méthode appelée lors d'une demande de sauvegarde utilisateur
	 * <br/><b>Attention : </b> Cette méthode efface complétement le fichier de sauvegarde où elle enregistre les données  
	 * @param name Le nom du fichier 
	 * @param path Le chemin vers le fichier
	 * @param data Le contenu à écrire dans le fichier de sauvegarde
	 * @return true si la sauvegarde a réussi, false sinon
	 */
	public boolean save(String name, String path, Object data);
	/**
	 * Cette méthode permet de sauvegarder dans un fichier automatique une sauvegarde rapide.
	 * <br/>Elle fonctionne en utilisant deux fichiers ; elle sauve alternativement dans un fichier puis dans l'autre. Ainsi, en cas d'erreur, seulement un coup est perdu.
	 * @param data Le contenu à écrire dans le fichier de sauvegarde
	 * @return true si la sauvegarde automatique a réussi, false sinon
	 */
	public boolean autoSave(Object data);

	/**
	 * Cette méthode lit le contenu d'un fichier et le retourne sous forme de String
	 * @param name Le nom du fichier à charger
	 * @param path Le chemin vers le fichier
	 * @return Le contenu complet du fichier ou un message d'erreur :
	 * <ul>
	 * <li>FilesManager.ERROR_ON_LOAD_FILE_NOT_EXISTING : si le fichier demandé n'existe pas</li>
	 * <li>FilesManager.ERROR_ON_LOAD_ON_READING : si un problème est survenu pendant la lecture</li>
	 * </ul>
	 */
	public String load(String name, String path);

	/**
	 * Méthode qui permet d'autoriser la vérification de la bonne écriture dans le fichier du contenu en se basant sur la longueur du contenu à écrire
	 */
	public void enableVerification();
	
	/**
	 * Méthode qui permet d'éviter la vérification de la bonne écriture dans le fichier
	 */
	public void disableVerification();
	
	/**
	 * Méthode qui retourne le nom du premier fichier de sauvegarde automatique
	 *  construit à partir du nom fourni par l'utilisateur
	 */
	public String getFirstAutoSaveRealFileName();
	
	/**
	 * Méthode qui retourne le nom du second fichier de sauvegarde automatique
	 *  construit à partir du nom fourni par l'utilisateur
	 */
	public String getSecondAutoSaveRealFileName();
}
