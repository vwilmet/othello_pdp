package com.manager;

public interface FileManagerInterface {

	public void init(boolean enableVerification);
	public void init(String autosaveFilename, boolean enableVerification);
	public void init(String saveFilename, String autosaveFilename, boolean enableVerification);
	
	public boolean save(String data);
	public boolean save(String name, String data);
	public boolean autoSave(String data);
		
	public String load(String name);
	
	public void enableVerification();
	public void disableVerification();
}
