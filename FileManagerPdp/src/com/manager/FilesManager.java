package com.manager;

/**
 * Interface de gestion du controleur de fichier
 * @author <ul><li>Vincent Wilmet</li><li>Morgane Badré</li>
 * @version 1.0
 */
public interface FilesManager {
	
	/**
	 * Méthode initialisant le module :<ul>
	 * <li> préparation du fichier de sauvegarde automatique </li>
	 * <li> préparation du tableau de gestion de plusieurs fichiers </li>
	 * @param enableVerification Un booléen qui, si passé à true, autorise la vérification de l'intérité du fichier
	 */
	public void init(boolean enableVerification);
	
	/**
	 * Voir la documentation {@link com.manager.FilesManager#init}
	 * @param autosaveFilename Le nom du fichier pour les sauvegardes automatiques. Par défault le nom est othello_autosave-{date:AAAA-MM-JJ-HH-MM-SS}.mnbv
	 * @param enableVerification Un booléen qui, si passé à true, autorise la vérification de l'intérité du fichier
	 */
	public void init(String autosaveFilename, boolean enableVerification);
	
	/**
	 * Voir la documentation {@link com.manager.FilesManager#init}
	 * @param saveFilename Le nom du fichier de sauvegarde générale si on ne veut pas le préciser à chaque sauvegarde utilisateur.
	 * Par défault le nom est othello_save-{date:AAAA-MM-JJ-HH-MM-SS}.mnbv
	 * @param autosaveFilename Le nom du fichier pour les sauvegardes automatiques. Par défault le nom est othello_autosave-{date:AAAA-MM-JJ-HH-MM-SS}.mnbv
	 * @param enableVerification Un booléen qui, si passé à true, autorise la vérification de l'intérité du fichier
	 */
	public void init(String saveFilename, String autosaveFilename, boolean enableVerification);
	
	/**
	 * Méthode appelée lors d'une demande de sauvegarde utilisateur
	 * <br><b>Attention : </b> Cette méthode efface complétement le fichier de sauvegarde où elle enregistre les données  
	 * @param data Le contenue à écrire dans le fichier de sauvegarde
	 * @return true si la sauvegarde à réussi, false sinon
	 */
	public boolean save(String data);
	
	/**
	 * Voir la documentation {@link com.manager.FilesManager#save}
	 * @param name Le nom du fichier 
	 * @param data Le contenue à écrire dans le fichier de sauvegarde
	 * @return true si la sauvegarde à réussi, false sinon
	 */
	public boolean save(String name, String data);
	/**
	 * Cette méthode permet de sauvegarder dans un fichier automatique une sauvegarde rapide.
	 * <br>Elle fonctionne en utilisant deux fichiers ; elle sauve alternativement dans un fichier puis dans l'autre. Ainsi, en cas d'erreur, seulement un coup est perdu.
	 * @param data Le contenue à écrire dans le fichier de sauvegarde
	 * @return true si la sauvegarde automatique à réussi, false sinon
	 */
	public boolean autoSave(String data);
	
	/**
	 * Cette méthode lit le contenue d'un fichier et le retourne sous forme de String
	 * @param name Le nom du fichier à charger
	 * @return Le contenue complet du fichier 
	 */
	public String load(String name);
	
	/**
	 * Méthode qui permet d'autoriser la vérification de la bonne écriture dans le fichier du contenue en se basant sur la longueur du contenue à écrire
	 */
	public void enableVerification();
	/**
	 * Méthode qui permet d'éviter la vérification de la bonne écriture dans le fichier
	 */
	public void disableVerification();
}
