package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.io.SaveGame;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface SaveGameFactory {
	public SaveGame getSaveGame() throws FactoryHandlerException;

}