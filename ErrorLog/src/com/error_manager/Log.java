package com.error_manager;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
	
	private static final String FILE_NAME = "log.txt";
	private static final String ERROR = "Erreur los de l'écriture dans le fichier d'erreur!";
	private static final String ERROR_FILE_NOT_FOUND = "Le fichier demandé est introuvable!";
	
	public static void error(String error){
		FileWriter file;
		try {
			file = new FileWriter(FILE_NAME, true);
			file.write(error + "\n");
			file.close();
		} catch (IOException e) {
			System.err.println(ERROR);
		}
	}
	
	public static void reset(){
		PrintWriter pw;
		try {
			pw = new PrintWriter(FILE_NAME);
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println(ERROR_FILE_NOT_FOUND);
		}
	}
}