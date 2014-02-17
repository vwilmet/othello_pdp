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
	/**
	 * Attribut qui contient le nom du fichier
	 */
	public String name;
	
	private String path;
	private String content;
	private FileReader fileReader; 
	private FileWriter fileWriter;
	private boolean canWrite, canRead;
	private PrintWriter writer;

	/**
	 * Constructeur qui initialise le fichier
	 * <br> La lecture et l'écriture sont autorisées
	 * Si le fichier n'existe pas, il est créé
	 * Si le fichier existe alors on place le contenu dans content
	 * Vérification de l'extension du fichier .mnbv
	 * @param name : nom du fichier
	 * @param path : chemin du fichier sans le nom
	 * @throws FileHandlingException : exception soulevée si le fichier ne possède pas la bonne extension
	 * @throws IOException : exception soulevée en cas de création de fichier impossible si nécessaire
	 */
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

		read();
	}

	/**
	 * Méthode retournant le contenu du fichier de la précédente lecture
	 * @return content : contenu du fichier
	 */
	public String getContent(){
		//TODO find the copy way to return a string
		return this.content;
	}

	/**
	 * Méthode retournant le chemin du fichier
	 * @return path : le chemin du fichier
	 */
	public String getPath(){
		return this.path;
	}
	
	/**
	 * Méthode qui écrit le contenu dans le fichier
	 * <br><b>Attention : </b> il faut posséder les droits en écriture
	 * @param content : contenu du fichier
	 * @return true si l'écriture a réussi, false sinon
	 */
	public boolean write(String content){
		if(!this.canWrite)
			return false;
		
		try{
			this.writer.println(content);
			return true;
		} catch (Exception e) {} 
		return false;
	}
	
	/**
	 * Méthode qui ferme le fichier à la suite d'une lecture, écriture
	 * <br><b>Attention : </b> cette méthode <b>doit</b> être appelé afin de fermer correctement le fichier
	 * @return true si la fermeture du fichier a réussi, false sinon
	 * @throws IOException : exception soulevée si une erreur survient lors de la fermeture d'un des deux fichiers 
	 */
	public boolean close() throws IOException{
		boolean success = false;

		if (this.fileReader != null) {
			try {
				this.fileReader.close();
				success = true;
			} catch (IOException e) {}
		}
		if (this.fileWriter != null) {
	        try {
	        	this.fileWriter.close();
	        	success = true;
	        } catch (IOException e) {}
		}
		if (this.writer != null) {
	    		this.writer.close();
	        	success = true;
		}
		
		return success;
	}

	/**
	 * Méthode qui permet l'ouverture d'un fichier pour la lecture ou l'écriture de celui-ci
	 * @param file : fichier passé en paramètre 
	 */
	private void open(File file) throws FileHandlingException{
		try{
			this.fileReader = new FileReader(file);
			this.fileWriter = new FileWriter(file, true);
			this.writer = new PrintWriter(this.fileWriter);
		}catch(FileNotFoundException n){
			throw new FileHandlingException(FileHandlingException.ERROR);
		} catch (IOException e) {
			throw new FileHandlingException(FileHandlingException.ERROR);
		}
	}

	/**
	 * Méthode autorisant uniquement la lecture
	 */
	public void setReadOnly(){
		this.canWrite = false;
		this.canRead = true;
	}

	/**
	 * Méthode autorisant uniquement l'écriture
	 */
	public void setWriteOnly(){
		this.canWrite = true;
		this.canRead = false;
	}

	/**
	 * Méthode permettant la lecture du contenu dans le fichier
	 * @return content : le contenu du fichier
	 * @throws FileHandlingException : exception soulevée si la lecture n'a pas pu aboutir
	 */
	public String read() throws FileHandlingException{
		if(!this.canRead)
			return null;
		
		try {
			BufferedReader reader = new BufferedReader(this.fileReader);
			String line = "";
			this.content = "";
			while ((line = reader.readLine()) != null) {
				this.content += line + "\n";
			}
		} catch (Exception e) {
			throw new FileHandlingException(FileHandlingException.READING_ERROR);
		} 
		return this.content;
	}
}
