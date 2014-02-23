package Model;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class HumanPlayer extends Player {
	public HumanPlayer(String login){
		super(login);
	}

	@Override
	public String toString() {
		String res = this.login;
		return res;
	}

}
