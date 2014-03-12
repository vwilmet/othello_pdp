package com.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import utils.FactoryHandlerException;
import utils.TextManager;

import com.controller.interfaces.NotifyGameController;
import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.factory.interfaces.RestoreGameFactory;
import com.model.io.RestoreGame;
import com.timermanager.TimerManager;
import com.timermanager.TimerManagerImpl;
import com.view.GameViewImpl;
import com.view.button.ImageButton;
import com.view.event.ButtonImageMenuEventListener;
import com.view.event.GameCanvasMouseEventListener;
import com.view.event.GameViewMenuEventListener;
import com.view.interfaces.GameView;

public abstract class GameController{

	protected GameSettings gameSettings;
	protected TimerManager timer;

	protected GameController() {
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();

		BoardObservable board = null;
		timer = new TimerManagerImpl();

		try {
			
			
			board = bFacto.getInitialBoard(8,8);
			
			
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {
			this.gameSettings = gsFacto.getGameSettings(
					pFacto.getHumanPlayer("toto", TextManager.WHITE_PLAYER), 
					pFacto.getMachinePlayer("John DOE", TextManager.BLACK_PLAYER),
					board,
					GameSettings.DEFAULT_IA_THINKING_TIME, 
					GameSettings.DEFAULT_IA_DIFFICULTY);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		timer.startCountingElapsedTime();
	}

	protected abstract void initializeNewGame();
	
	protected abstract void loadFileForGame();

	
}