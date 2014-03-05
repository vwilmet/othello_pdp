package com.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.error_manager.Log;

import utils.FileDateManager;
import utils.FileHandlingException;

/**
 * Classe qui définie les méthodes de gestion de fichier
 * Elle implémente l'interface {@link com.manager.FilesManager}
 * @author <ul><li>Vincent Wilmet</li><li>Morgane Badré</li></ul>
 * @version 2.0
 */
public class FilesManagerImpl implements FilesManager{

	private String autoSaveFileName = FilesManager.DEFAULT_AUTOSAVE_FILENAME;

	private MNBVFile saveFile, currentAutoSaveFile;
	private MNBVFile[] autoSaveFile;

	private boolean verification = false;

	public FilesManagerImpl() {
		autoSaveFile = new MNBVFile[2];
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.manager.FilesManager#init}
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

	private MNBVFile createFile(String name, String path) throws FileHandlingException{
		return new MNBVFile(name, path);
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.manager.FilesManager#save}
	 */
	@Override
	public boolean save(String name, String path, Object data) {

		if (this.saveFile != null) this.saveFile = null;

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
					saveFile.deleteFile();
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
	 * <br>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.manager.FilesManager#autoSave}
	 */
	@Override
	public boolean autoSave(Object data) {

		if(autoSaveFile[0] == null){
			try {
				autoSaveFile[0] = createFile(autoSaveFileName + "-01-" + FileDateManager.getDateFormatAAAAMMJJHHMMSS() + FilesManager.DEFAULT_FILENAME_EXTENSION, FilesManager.DEFAULT_FILE_PATH + File.separator);
				autoSaveFile[1] = createFile(autoSaveFileName + "-02-" + FileDateManager.getDateFormatAAAAMMJJHHMMSS() + FilesManager.DEFAULT_FILENAME_EXTENSION, FilesManager.DEFAULT_FILE_PATH + File.separator);
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
			Log.error("Le fichier de sauvegarde automatique n'as pas pu être ouvert!!");
			return false;
		}

		try {
			if(this.verification)
				if(data.toString().length() != saveFile.read().length()){
					saveFile.deleteFile();
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
			System.out.println("current == 1");
			currentAutoSaveFile = autoSaveFile[0];
		}
		else {
			System.out.println("current == 0");
			currentAutoSaveFile = autoSaveFile[1];
		}

		return true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.manager.FilesManager#load}
	 */
	@Override
	public String load(String name, String path) {
		File file = new File(path + File.separator + name);

		if (!file.exists())
			return FilesManager.ERROR_ON_LOAD_FILE_NOT_EXISTING;

		try {
			return new MNBVFile(name, path).read();
		} catch (FileHandlingException f) {
			Log.error(f.getMessage());
			return FilesManager.ERROR_ON_LOAD_ON_READING;
		}
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.manager.FilesManager#enableVerification}
	 */
	@Override
	public void enableVerification() {
		this.verification = true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.FilesManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.manager.FilesManager#disableVerification}
	 */
	@Override
	public void disableVerification() {
		this.verification = false;
	}
}
