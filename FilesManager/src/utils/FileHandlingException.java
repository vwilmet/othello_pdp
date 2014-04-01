package utils;

import com.manager.FilesManager;

@SuppressWarnings("serial")
public class FileHandlingException extends Exception{

	public static final int ERROR = 0;
	public static final int WRONG_EXTENSION_FILENAME = 1;
	public static final int WRITING_ERROR = 2;
	public static final int READING_ERROR = 3;
	public static final int IO_ERROR = 4;
	
	private int error;
	private String message;
	
	public FileHandlingException(int error) {
		this.error = error;
		this.message = "";
	}
	
	public FileHandlingException(int error, String message) {
		this.error = error;
		this.message = message;
	}
	
	public int getError(){
		return error;
	}
	
	public String getMessage(){
		switch(error){
		case WRITING_ERROR:
			return FilesManager.ERROR_WRITING + message;
		case READING_ERROR:
			return FilesManager.ERROR_ON_LOAD_ON_READING + message;
		case WRONG_EXTENSION_FILENAME:
			return FilesManager.ERROR_WRONG_EXTENSION + message;
		case IO_ERROR:
			return FilesManager.IO_ERROR + message;
		case ERROR:
		default :
			return FilesManager.ERROR + message;
			
		}
	}
	
}
