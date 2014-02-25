package com.model.factory;


/**
 * Gestionnaire de la fabrique.
 * @author <ul><li>Benjamin Letourneau</li></ul>
 * @version 1.0
 */
public class FactoryProducer  {
	
	public static AbstractFactory getPieceFacory(){
		return PieceFactoryImpl.getInstance();
	}
	
	public static AbstractFactory getPlayerFacory(){
		return PlayerFactoryImpl.getInstance();
	}
	
	public static AbstractFactory getBoardFactory(){
		return BoardFactoryImpl.getInstance();
	}
	
	public static AbstractFactory getGameSettingsFactory(){
		return GameSettingsFactoryImpl.getInstance();
	}
	
	public static AbstractFactory getRestoreGameFactory(){
		return RestoreGameFactoryImpl.getInstance();
	}
	
	public static AbstractFactory getSaveGameFactory(){
		return SaveGameFactoryImpl.getInstance();
	}
}
