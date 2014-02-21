package com.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import utils.FileHandlingException;

public class MNBVFile {
	/**
	 * Attribut qui contient le nom du fichier
	 */
	public String name;

	private String path;
	private File file;
	private FileReader fileReader; 
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
	public MNBVFile(String name, String path) throws FileHandlingException{
		this.name = name;
		this.path = path;
		this.canWrite = true;
		this.canRead = true;

		file = new File(path + File.separator + name);

		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new FileHandlingException(FileHandlingException.IO_ERROR);
			}
		}

		open();

		if(name.length() <= FilesManager.DEFAULT_FILENAME_EXTENSION.length())
			throw new FileHandlingException(FileHandlingException.WRONG_EXTENSION_FILENAME);

		if(!name.substring(name.length()-FilesManager.DEFAULT_FILENAME_EXTENSION.length()).matches(FilesManager.DEFAULT_FILENAME_EXTENSION))
			throw new FileHandlingException(FileHandlingException.WRONG_EXTENSION_FILENAME);
	}

	/**
	 * Méthode retournant le chemin du fichier
	 * @return path : le chemin du fichier
	 */
	public String getPath(){
		return this.path;
	}

	/**
	 * Méthode qui écrit le contenu dans le fichier à la fin
	 * <br><b>Attention : </b> il faut posséder les droits en écriture
	 * @param content : contenu du fichier
	 * @return true si l'écriture a réussi, false sinon
	 */
	public boolean write(Object content) throws FileHandlingException{
		if(!this.canWrite)
			return false;

		try {
			//reset files automatically
			this.writer = new PrintWriter(file);
			this.writer.print(content);
		} catch (FileNotFoundException e) {
			throw new FileHandlingException(FileHandlingException.WRITING_ERROR);
		}

		return true;
	}

	/**
	 * Méthode qui ferme le fichier à la suite d'une lecture, écriture
	 * <br><b>Attention : </b> cette méthode <b>doit</b> être appelé afin de fermer correctement le fichier
	 * @return true si la fermeture du fichier a réussi, false sinon
	 * @throws IOException : exception soulevée si une erreur survient lors de la fermeture d'un des deux fichiers 
	 */
	public boolean close() throws FileHandlingException{
		try {
			if (this.fileReader != null) 
				this.fileReader.close();
			if (this.writer != null) 
				this.writer.close();

			return true;
		} catch (IOException e) {
			throw new FileHandlingException(FileHandlingException.ERROR);
		}
	}

	/**
	 * Méthode qui permet l'ouverture d'un fichier pour la lecture ou l'écriture de celui-ci
	 */
	private void open() throws FileHandlingException{
		try{
			this.fileReader = new FileReader(file);
		}catch(FileNotFoundException n){
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

	public void deleteFile(){
		file.delete();
	}

	/**
	 * Méthode permettant la lecture du contenu dans le fichier
	 * @return content : le contenu du fichier
	 * @throws FileHandlingException : exception soulevée si la lecture n'a pas pu aboutir
	 */
	public String read() throws FileHandlingException{
		String content;
		
		if(!this.canRead)
			return null;
		
		try {
			BufferedReader reader = new BufferedReader(this.fileReader);
			String line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line + "\n";
			}
		} catch (Exception e) {
			throw new FileHandlingException(FileHandlingException.READING_ERROR);
		} 
		return content;
	}
}
