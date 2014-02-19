package com.manager;

import java.io.File;
import java.io.IOException;

import utils.FileHandlingException;

/**
 * Classe qui définie les méthodes de gestion de fichier
 * Elle implémente l'interface {@link com.manager.FilesManager}
 * @author <ul><li>Vincent Wilmet</li><li>Morgane Badré</li>
 * @version 1.0
 */
public class FilesManagerImpl implements FilesManager{
	
	private String autoSaveFileName = FilesManager.DEFAULT_AUTOSAVE_FILENAME;
	private String saveFileName = FilesManager.DEFAULT_SAVE_FILENAME;
	MNBVFile saveFile, currentAutoSaveFile;
	MNBVFile[] autoSaveFile;
	
	private boolean verification = false;
	
	public FilesManagerImpl() {
		autoSaveFile = new MNBVFile[2];
	}
	
	@Override
	public void init(boolean enableVerification) {
		this.verification = enableVerification;
	}

	@Override
	public void init(String autosaveFilename, boolean enableVerification) {
		this.init(enableVerification);
		this.autoSaveFileName = autosaveFilename;
	}

	@Override
	public void init(String saveFilename, String autosaveFilename, boolean enableVerification) {
		this.init(autosaveFilename, enableVerification);
		this.saveFileName = saveFilename;
	}
	
	private MNBVFile createFile(String name) throws FileHandlingException, IOException{
		return new MNBVFile(name, "." + File.separator);
	}
	
	private void initIfNeededFiles(MNBVFile file, String name, String path) throws FileHandlingException, IOException{
		if (file == null){
			file = new MNBVFile(name, path);
		}
	}
	
	@Override
	public boolean save(String data) {
		
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(String name, String path, String data) {
		
		
		return false;
	}

	@Override
	public boolean autoSave(String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String load(String name, String path) {
		try {
			return new MNBVFile(name, path).getContent();
		} catch (FileHandlingException | IOException e) {
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
