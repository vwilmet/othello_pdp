package com.manager;

import java.util.ArrayList;

public class FilesManagerImpl implements FilesManager{
	
	ArrayList<MNBVFile> files = new ArrayList<MNBVFile>();

	public FilesManagerImpl() {
		
	}
	
	private MNBVFile createFile(String name){
		
		return null;
	}
	
	@Override
	public boolean save(String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(String name, String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean autoSave(String data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String load(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enableVerification() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(boolean enableVerification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(String autosaveFilename, boolean enableVerification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(String saveFilename, String autosaveFilename,
			boolean enableVerification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableVerification() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
