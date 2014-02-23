package Model;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class MachinePlayer extends Player {

	public MachinePlayer(String login) {
		super(login);
	}

	public String toString(){
		String res = this.login;
		return res;
	}
}
