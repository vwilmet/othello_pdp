package Model;

import java.awt.Color;

/**
 * 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class HumanPlayer extends Player {
	

	public HumanPlayer(String login, Color c) {
		super(login, c);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String res = super.toString();
		res += "Joueur humain.\n";
		return res;
	}

}
