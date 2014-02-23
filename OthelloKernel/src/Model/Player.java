package Model;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public abstract class Player {
	protected String login;
	
	public Player (String login){
		this.login = login;
	}
	
	public abstract String toString();
}
