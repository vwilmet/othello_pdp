package com.model.factory.interfaces;

import utils.FactoryHandlerException;

import com.model.GameSettings;

/**
 * 
 * @author <ul>
 *         <li>Benjamin Letourneau</li>
 *         </ul>
 * @version 1.0
 */
public interface GameSettingsFactory {
	public GameSettings getGameSettings() throws FactoryHandlerException;
}
