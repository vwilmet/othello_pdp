package com.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utils.FileHandlingException;

public class MNBVFile {

	public String name;
	public String path;
	private String content;
	private FileReader fileReader; 
	private FileWriter fileWriter;
	private boolean canWrite, canRead;

	public MNBVFile(String name, String path) throws FileHandlingException{
		this.name = name;
		this.path = path;
		this.canWrite = true;
		this.canRead = true;
		this.content = "";
		
		open();

		if(!name.substring(name.length()-5).matches(".mnbv"))
			throw new FileHandlingException(1);

		//TODO si nexiste pas on cr�er
		
		try {
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				content += line + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println(content);
	}

	public String getContent(){
		//TODO find the copy way to return a string
		return content;
	}

	public String getPath(){
		return path;
	}

	public boolean write(String content){
		if(!canWrite)
			return false;
		
	    try {
	      BufferedWriter writer = new BufferedWriter(fileWriter);
	      writer.write(content);
	      System.out.println("pass� par la");
	      return true;
	    } catch (Exception e) {
	      //TODO;
	    } 
		return false;
	}

	/*public boolean write(){
		return false;
	}*/

	public boolean close() throws IOException{
		
		fileWriter.close();
		return true;
		
		/*
		boolean success = false;
	
		if (fileReader != null) {
			try {
				fileReader.close();
				success = true;
			} catch (IOException e) {}
		}
		if (fileWriter != null) {
	        try {
	        	fileWriter.close();
	        	success = true;
	        } catch (IOException e) {}
		}
		return success;*/
	}

	private void open() throws FileHandlingException{
		try{
			this.fileReader = new FileReader(path + File.separator + name);
			this.fileWriter = new FileWriter(path + File.separator + name, true);
		}catch(FileNotFoundException n){
			//TODO h 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setReadOnly(){
		this.canWrite = false;
		this.canRead = true;
	}

	public void setWriteOnly(){
		this.canWrite = true;
		this.canRead = false;
	}

	public String read(){
		if(!canRead)
			return null;
		
		return null;
	}
}
