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
	
	public boolean save(String data);
	public boolean save(String name, String data);
	public boolean autoSave(String data);
	
	public String load(String name);
	
	public void enableVerification();
	public void disableVerification();
}
