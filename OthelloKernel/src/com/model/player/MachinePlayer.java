package com.model.player;

import java.awt.Color;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public class MachinePlayer implements PlayerType {

	@Override
	public void changeType(Player p) {
		p.setHuman();
	}	
}
