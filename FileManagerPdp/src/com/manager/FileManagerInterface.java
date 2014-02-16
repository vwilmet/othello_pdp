package com.manager;

public interface FileManagerInterface {

	public boolean save(String data);
	public boolean save(String name, String data);
	public boolean autoSave(String data);
		
	public String load(String name);
	
	public void enableVerification();
}
