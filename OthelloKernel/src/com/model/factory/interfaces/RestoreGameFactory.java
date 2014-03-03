package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.io.RestoreGame;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface RestoreGameFactory {
	public RestoreGame getRestoreGame(String gameFileName) throws FactoryHandlerException;
}
