package com.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.error_manager.Log;

import utils.FileDateManager;
import utils.FileHandlingException;

/**
 * @mainpage Module FilesManager
 * 
 * Ce module a pour but de proposer une gestion de fichier tel que :
 * <ul>
 * <li>création</li>
 * <li>lecture</li>
 * <li>écriture</li>
 * <li>vérification</li>
 * </ul>
 * 
 * Le nom du fichier généré automatiquement est sous la forme : 
 * <i>nom</i>-<i>N°</i>-<i>AAAA</i>-<i>MM</i>-<i>JJ</i>-<i>HH</i>-<i>MM</i>-<i>SS</i>.<i>extension</i>
 * <br/><b>Exemple de BONNE utilisation du module : </b>
 * 
 *		FilesManager files = new FilesManagerImpl();
 *		files.init(false);
 *		
 *		if(files.autoSave("Texte 1"))
 *			System.out.println("OK!!");
 */

/**
 * Classe qui définit les méthodes de gestion de fichier
 * Elle implémente l'interface {@link com.manager.FilesManager}
 * @author <ul><li>Vincent Wilmet</li><li>Morgane Badré</li></ul>
 * @version 2.0
 */
public class FilesManagerImpl implements FilesManager{
	
	/**
	 * Le nom du fichier de sauvegarde automatique
	 */
	private String autoSaveFileName = DEFAULT_AUTOSAVE_FILENAME + DEFAULT_FILENAME_EXTENSION;
	
	/**
	 * Fichier de sauvegarde pour la sauvegarde voulue par l'utilisateur
	 */
	private MNBVFile saveFile;
	/**
	 * Variable qui contient l'adresse vers le fichier de sauvegarde actuel
	 */
	private MNBVFile currentAutoSaveFile;
	/**
	 * Tableau qui va contenir les fichiers de sauvegarde automatique
	 * Ceci permet de sauvegarder dans plusieurs fichiers afin d'avoir le moins de pertes possibles en cas de problème
	 */
	private MNBVFile[] autoSaveFile;
	
	/**
	 * Booléen qui définit si il y a besoin de vérifier que l'écriture a bien fonctionné
	 */
	private boolean verification = false;
	
	/**
	 * Constructeur qui initialise les différents composants du module
	 */
	public FilesManagerImpl() {
		autoSaveFile = new MNBVFile[2];
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.manager.FilesManager#init}
	 */
	@Override
	public boolean init(boolean enableVerification) {
		this.verification = enableVerification;
		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.manager.FilesManager#init(String autosaveFilename, boolean enableVerification)}
	 */
	@Override
	public boolean init(String autosaveFilename, boolean enableVerification) {
		this.autoSaveFileName = autosaveFilename;
		return this.init(enableVerification);
	}

	/**
	 * Méthode créant un fichier au chemin "path" au nom de "name"
	 * @param name Le nom du fichier avec son extension
	 * @param path Le chemin vers ce fichier
	 * @return Le fichier créé en tant qu'objet de la classe MNBVFile
	 * @throws FileHandlingException si une erreur est survenue durant le processus de création
	 */
	private MNBVFile createFile(String name, String path) throws FileHandlingException{
		return new MNBVFile(name, path);
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.manager.FilesManager#save}
	 */
	@Override
	public boolean save(String name, String path, Object data) {

		if (data == null) return false;
		if (this.saveFile != null) this.saveFile = null;

		if(name.equals("") || name == null){
			Log.error(ERROR_SAVE_FILE_NAME_NOT_PROVIDED);
			name = FileDateManager.getDateFormatAAAAMMJJHHMMSS() + "_" + DEFAULT_SAVE_FILENAME + DEFAULT_FILENAME_EXTENSION;
		}
		
		if(path == null) path = "";
		
		try {
			saveFile = createFile(name, path);
		} catch (FileHandlingException e) {
			Log.error(e.getMessage());
			return false;
		}

		try {
			saveFile.write(data);
		} catch (FileHandlingException e) {
			Log.error(e.getMessage());
			return false;
		}

		try {
			saveFile.close();
		} catch (FileHandlingException e) {
			Log.error(e.getMessage());
			return false;
		}

		try {
			if(this.verification)
				if(data.toString().length() != saveFile.read().length()){
					return false;
				}
		}catch(FileHandlingException e){
			Log.error(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.manager.FilesManager#autoSave}
	 */
	@Override
	public boolean autoSave(Object data) {

		if (data == null) return false;
		
		if(autoSaveFile[0] == null){
			try {
				autoSaveFile[0] = createFile("01-" + FileDateManager.getDateFormatAAAAMMJJHHMMSS() + autoSaveFileName, DEFAULT_FILE_PATH + File.separator);
				autoSaveFile[1] = createFile("02-" + FileDateManager.getDateFormatAAAAMMJJHHMMSS() + autoSaveFileName, DEFAULT_FILE_PATH + File.separator);
				currentAutoSaveFile = autoSaveFile[0];
			} catch (FileHandlingException e) {
				Log.error(e.getMessage());
				return false;
			}
		}

		try {
			currentAutoSaveFile.write(data);
		} catch (FileHandlingException e) {
			Log.error(e.getMessage());
			return false;
		}catch (NullPointerException n){
			Log.error(ERROR_ON_AUTOSAVE_FILE_NOT_EXISTING);
			return false;
		}

		try {
			if(this.verification)
				if(data.toString().length() != saveFile.read().length()){
					return false;
				}
		}catch(FileHandlingException e){
			Log.error(e.getMessage());
			return false;
		}

		try {
			currentAutoSaveFile.close();
		} catch (FileHandlingException e) {
			Log.error(e.getMessage());
			return false;
		}

		if(currentAutoSaveFile == autoSaveFile[1]){
			currentAutoSaveFile = autoSaveFile[0];
		}
		else {
			currentAutoSaveFile = autoSaveFile[1];
		}

		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.manager.FilesManager#load}
	 */
	@Override
	public String load(String name, String path) {
		if (name == null || name.equals("")) return ERROR_ON_LOAD_FILE_NOT_EXISTING;
		File file = new File(path + File.separator + name);

		if (!file.exists())
			return ERROR_ON_LOAD_FILE_NOT_EXISTING;
		try {
			return new MNBVFile(name, path).read();
		} catch (FileHandlingException f) {
			Log.error(f.getMessage());
			return ERROR_ON_LOAD_ON_READING;
		}
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.manager.FilesManager#enableVerification}
	 */
	@Override
	public void enableVerification() {
		this.verification = true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.manager.FilesManager#disableVerification}
	 */
	@Override
	public void disableVerification() {
		this.verification = false;
	}
}
