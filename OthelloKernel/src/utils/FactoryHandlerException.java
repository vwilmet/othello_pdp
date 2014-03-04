package utils;

import javax.xml.soap.Text;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class FactoryHandlerException extends Exception {

	public static final int ERROR = 0;
	public static final int WRONG_FACTORY_REFERRED = 1;

	private int error;
	private String message;

	public FactoryHandlerException(int error) {
		this.error = error;
		this.message = "";
	}

	public FactoryHandlerException(int error, String message) {
		this.error = error;
		this.message = message;
	}

	public int getError() {
		return this.error;
	}

	public String getMessage() {
		switch (this.error) {
		case WRONG_FACTORY_REFERRED:
			return TextManager.WRONG_FACTORY_REFERRED_FR
					+ this.message;
		case ERROR:
		default:
			return TextManager.ERROR_FR + this.message;
		}
	}
}
