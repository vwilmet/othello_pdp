package com.utils;

import java.awt.Point;

/**
 * Classe d'exception pour la détection de position non jouable.
 * @author Nicolas YVON
 *
 */
@SuppressWarnings("serial")
public class WrongPlayablePositionException extends Exception {

	String message;
	
	
	public WrongPlayablePositionException(){
		this.message = new String("The position is not a playable position for the actual tree : synchronisation problem!");
	}
	public WrongPlayablePositionException(Point pos){
		this.message = new String("The position (" + pos.x + "," + pos.y + ") is not a playable position for the actual tree : synchronisation problem!");
	}
	
}
