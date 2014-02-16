package utils;

public class FileHandlingException extends Exception{
	private int error;
	
	public FileHandlingException(int error) {
		this.error = error;
	}
	
	public int getError(){
		return error;
	}
	
}
