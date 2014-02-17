package utils;

public class FileHandlingException extends Exception{

	public static final int ERROR = 0;
	public static final int BAD_EXTENSION_FILENAME = 1;
	public static final int WRITING_ERROR = 2;
	public static final int READING_ERROR = 3;
	
	private int error;
	
	public FileHandlingException(int error) {
		this.error = error;
	}
	
	public int getError(){
		return error;
	}
	
}
