package com.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import utils.FileHandlingException;

public class MNBVFile {

	public String name;
	public String path;
	private String content;
	private FileReader fileReader; 
	private FileWriter fileWriter;
	private boolean canWrite, canRead;
	PrintWriter writer;

	public MNBVFile(String name, String path) throws FileHandlingException, IOException{
		this.name = name;
		this.path = path;
		this.canWrite = true;
		this.canRead = true;
		this.content = "";

		File file = new File(path + File.separator + name);
		
		if (!file.exists()){
			file.createNewFile();
		}
		
		open(file);

		if(!name.substring(name.length()-5).matches(".mnbv"))
			throw new FileHandlingException(FileHandlingException.BAD_EXTENSION_FILENAME);

		content = read();
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
		
		try{
			writer.println(content);
			return true;
		} catch (Exception e) {} 
		return false;
	}

	public boolean close() throws IOException{
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
		if (writer != null) {
	    		writer.close();
	        	success = true;
		}
		
		return success;
	}

	private void open(File file) throws FileHandlingException{
		try{
			this.fileReader = new FileReader(file);
			this.fileWriter = new FileWriter(file, true);
			writer = new PrintWriter(fileWriter);
		}catch(FileNotFoundException n){
			throw new FileHandlingException(FileHandlingException.ERROR);
		} catch (IOException e) {
			throw new FileHandlingException(FileHandlingException.ERROR);
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

	public String read() throws FileHandlingException{
		if(!canRead)
			return null;
		
		try {
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				content += line + "\n";
			}
		} catch (Exception e) {
			throw new FileHandlingException(FileHandlingException.WRITING_ERROR);
		} 
		return content;
	}
}
