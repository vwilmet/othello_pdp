package com.model.player;

import java.awt.Color;

import javax.xml.soap.Text;

import utils.TextManager;

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
	
	@Override
	public String getPlayerType(){
		return TextManager.MACHINE_PLAYER;	
	}
	
	@Override
	public String toString() {
		return TextManager.MACHINE_LABEL_PLAYER;
	}
}
