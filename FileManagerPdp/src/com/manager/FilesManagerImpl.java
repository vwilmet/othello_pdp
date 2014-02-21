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
 * @author <ul><li>Vincent Wilmet</li><li>Morgane Badré</li>
 * @version 1.0
 */
public class FilesManagerImpl implements FilesManager{

	private String autoSaveFileName = FilesManager.DEFAULT_AUTOSAVE_FILENAME;

	private MNBVFile saveFile, currentAutoSaveFile;
	private MNBVFile[] autoSaveFile;

	private boolean verification = false;

	public FilesManagerImpl() {
		autoSaveFile = new MNBVFile[2];
	}

	@Override
	public boolean init(boolean enableVerification) {
		this.verification = enableVerification;

		try {
			autoSaveFile[0] = createFile(autoSaveFileName + "-01-" + FileDateManager.getDateFormatAAAAMMJJHHMMSS() + "", FilesManager.DEFAULT_FILE_PATH + File.separator);
			autoSaveFile[1] = createFile(autoSaveFileName + "-02-" + FileDateManager.getDateFormatAAAAMMJJHHMMSS() + "", FilesManager.DEFAULT_FILE_PATH + File.separator);
			currentAutoSaveFile = autoSaveFile[0];
		} catch (FileHandlingException e) {
			Log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean init(String autosaveFilename, boolean enableVerification) {
		this.autoSaveFileName = autosaveFilename;
		return this.init(enableVerification);
	}

	private MNBVFile createFile(String name, String path) throws FileHandlingException{
		return new MNBVFile(name, path);
	}

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

	@Override
	public boolean autoSave(Object data) {

		try {
			currentAutoSaveFile.write(data);
		} catch (FileHandlingException e) {
			Log.error(e.getMessage());
			return false;
		}catch (NullPointerException n){
			Log.error("");
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

	@Override
	public String load(String name, String path) {
		try {
			return new MNBVFile(name, path).read();
		} catch (FileHandlingException f) {
			Log.error(f.getMessage());
			return FilesManager.ERROR_ON_LOAD;
		}
	}

	@Override
	public void enableVerification() {
		this.verification = true;
	}

	@Override
	public void disableVerification() {
		this.verification = false;
	}
}
