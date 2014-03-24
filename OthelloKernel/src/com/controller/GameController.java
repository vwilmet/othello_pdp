package com.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import utils.FactoryHandlerException;
import utils.TextManager;

import com.ai.ArtificialIntelligence;
import com.ai.impl.ArtificialIntelligenceImpl;
import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PieceFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.model.player.MachinePlayer;
import com.timermanager.TimerManager;
import com.timermanager.TimerManagerImpl;
import com.utils.WrongPlayablePositionException;

public abstract class GameController{

	protected GameSettings gameSettings;
	protected TimerManager timer;
	protected HashMap<String, ArtificialIntelligence> ia;

	protected GameController() {
		GameSettingsFactory gsFacto = FactoryProducer.getGameSettingsFactory();
		BoardFactory bFacto = FactoryProducer.getBoardFactory();
		PlayerFactory pFacto = FactoryProducer.getPlayerFactory();
		PieceFactory pieceFacto = FactoryProducer.getPieceFactory();

		BoardObservable board = null;
		timer = new TimerManagerImpl();
		this.ia = new HashMap<String, ArtificialIntelligence>();

		try {
			board = bFacto.getInitialBoard(8,8);
		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		try {

			this.gameSettings = gsFacto.getGameSettings(
					pFacto.getHumanPlayer("toto", TextManager.WHITE_PLAYER,1), 
					pFacto.getMachinePlayer("John DOE", TextManager.BLACK_PLAYER,2),
					board,
					GameSettings.DEFAULT_IA_THINKING_TIME, 
					GameSettings.DEFAULT_IA_DIFFICULTY,
					pieceFacto.getArrayListOfPiece());
			this.ia.put("John DOE", new ArtificialIntelligenceImpl());
			this.initializeIA();
			this.setPlayablePiece();

		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		timer.startCountingElapsedTime();
		this.checkPlayersPiecesCount();
	}

	protected void initializeIA(){

		Set<Point> 	whitePiece = new HashSet<Point>(),
				blackPiece = new HashSet<Point>();

		for(Piece p : this.gameSettings.getGameBoard().getWhitePieces())
			whitePiece.add(new Point(p.getPosX(), p.getPosY()));

		for(Piece p : this.gameSettings.getGameBoard().getBlackPieces())
			blackPiece.add(new Point(p.getPosX(), p.getPosY()));

		for(ArtificialIntelligence ia_tmp : ia.values()){
			ia_tmp.chooseDifficulty(GameSettings.DEFAULT_IA_DIFFICULTY);
			ia_tmp.initialize(whitePiece, blackPiece, this.gameSettings.getGameBoard().getSizeX(), this.gameSettings.getGameBoard().getSizeY());
			ia_tmp.setMaxTime(GameSettings.DEFAULT_IA_THINKING_TIME);
		}
	}

	protected abstract void initializeNewGame();

	protected abstract void loadFileForGame();

	protected boolean onPiecePlayed(int i, int j){
		for(Piece possiblePiece : this.gameSettings.getGameBoard().getPlayablePieces())
			if(possiblePiece.getPosX() == i && possiblePiece.getPosY() == j){
				this.gameSettings.setPiece(i, j);
				this.reverseInbetweenPieceAfterPlaying(i, j);
				this.gameSettings.manageBoardHistory(i, j);
				this.gameSettings.changePlayer();
				this.setPlayablePiece();
				return true;
			}
		return false;
	}

	protected void dealWithCurrentPlayer(){

		//Si le joueur à jouer est l'IA
		if(this.gameSettings.getCurrentPlayer().getPlayerType() instanceof MachinePlayer){

			ArtificialIntelligence _ia = this.ia.get(this.gameSettings.getCurrentPlayer().getLogin());

			Point p = _ia.nextMove(this.gameSettings.getCurrentPlayer().getPlayerNumber());

			if(onPiecePlayed(p.x, p.y)){
				try {
					toto: lol hih hi
					//%2+ à gérer
					_ia.notifyChosenMove(p, this.gameSettings.getCurrentPlayer().getPlayerNumber()%2+1);
				} catch (WrongPlayablePositionException e) {
					Log.error(e.getMessage());
					e.printStackTrace();
				}

				this.onIAPlayed();
				this.dealWithCurrentPlayer();	
			}
		}
		/*
		 * Sinon c'est un joueur humain. Du coup on attend qu'il joue et lorsqu'il joue l'évenement GameControllerGraphical.onLeftMouseButtonPressed est soulevé.
		 */


	}

	protected abstract void onIAPlayed();

	protected void quickSaveOFCurrentBoard(){


	}

	protected void saveCurrentBoard(String path){

	}

	private ArrayList<Piece> getReversePieceAround(Piece origin){
		ArrayList<Piece> neighbours = new ArrayList<Piece>();
		int posX, posY;

		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				posX = origin.getPosX()+j-1;
				posY = origin.getPosY()+i-1;

				if(posX >= this.gameSettings.getGameBoard().getSizeX() || posX < 0 || posY >= this.gameSettings.getGameBoard().getSizeY() || posY < 0)
					continue;

				if(posX == origin.getPosX() && posY == origin.getPosY())
					continue;

				if(!this.gameSettings.getGameBoard().getBoard()[posX][posY].getColor().getClass().equals(origin.getColor().getClass()) && 
						!this.gameSettings.getGameBoard().getBoard()[posX][posY].getColor().getClass().equals(EmptyPiece.class))
					neighbours.add(this.gameSettings.getGameBoard().getBoard()[posX][posY]);

			}
		return neighbours;
	}

	/**
	 * Pk ici dans controller et pas comme les méthode history dans le model 
	 * => cette méthode fait partie des regles du jeu! du coup elle pourrait etre modifier pour changer le jeu alors que le comportement de back and forwrd seras tjr le même et dépend de la board 
	 */
	protected void setPlayablePiece(){

		this.gameSettings.getGameBoard().resetPlayablePosition();
		ArrayList<Piece> origins;
		int longestCombinaisonSize = (this.gameSettings.getGameBoard().getSizeX() > this.gameSettings.getGameBoard().getSizeY() ? this.gameSettings.getGameBoard().getSizeX() : this.gameSettings.getGameBoard().getSizeY());
		Piece target = null;
		int posX = 0, posY = 0, previousIntermediatePosX, previousIntermediatePosy;

		if(this.gameSettings.getCurrentPlayer().getColor().equals(TextManager.WHITE_PLAYER))
			origins = new ArrayList<Piece>(gameSettings.getGameBoard().getWhitePieces());
		else
			origins = new ArrayList<Piece>(this.gameSettings.getGameBoard().getBlackPieces());

		for(Piece origin : origins){

			for(Piece intermediatePiece : getReversePieceAround(origin)){
				//System.out.println("================================");
				//System.out.println("Pour le pion origin : " + origin);

				previousIntermediatePosX = origin.getPosX();
				previousIntermediatePosy = origin.getPosY();

				//on utilise un for pour optimiser la recherche et être sur de s'arreter! Il ne peut pas y avoir de combinaison plus longue que la diagonale ou le coté le plus long
				for(int i = 0; i < longestCombinaisonSize; i++){
					//System.out.println("--------------"); 
					posX = 2*intermediatePiece.getPosX() - previousIntermediatePosX;
					posY = 2*intermediatePiece.getPosY() - previousIntermediatePosy;

					if(posX >= this.gameSettings.getGameBoard().getSizeX() || posX < 0 || posY >= this.gameSettings.getGameBoard().getSizeY() || posY < 0)
						break;

					target = this.gameSettings.getGameBoard().getBoard()[posX][posY];

					//	System.out.println("Intermediate : " + intermediatePiece);
					//	System.out.println("Target : " + target);

					if(target.getColor().getClass().equals(origin.getColor().getClass())){
						//System.out.println("on sort du premier if : target == origin | not playable");
						break;
					}else if(target.getColor().getClass().equals(intermediatePiece.getColor().getClass())){
						//System.out.println("on sort du second if : target == intermediatePiece | ont continue");
						previousIntermediatePosX = intermediatePiece.getPosX();
						previousIntermediatePosy = intermediatePiece.getPosY();
						intermediatePiece = target;
						continue;
					}else if(target.getColor() instanceof EmptyPiece){
						//System.out.println("géniale c'est jouable en :" + target);
						this.gameSettings.getGameBoard().setPiecePlayable(posX, posY);
						break;
					}
				}
				//System.out.println("================================");
			}

		}
	}

	protected void reverseInbetweenPieceAfterPlaying(int originPosX, int originPosY){

		ArrayList<Piece> inBetween = new ArrayList<Piece>();
		int longestCombinaisonSize = 
				(this.gameSettings.getGameBoard().getSizeX() > this.gameSettings.getGameBoard().getSizeY() ?
						this.gameSettings.getGameBoard().getSizeX() :
							this.gameSettings.getGameBoard().getSizeY());

		Piece origin = this.gameSettings.getGameBoard().getBoard()[originPosX][originPosY];
		Piece target = null;
		int posX = 0, posY = 0, previousIntermediatePosX, previousIntermediatePosy;

		for(Piece intermediatePiece : getReversePieceAround(origin)){
			inBetween.add(intermediatePiece);
			previousIntermediatePosX = origin.getPosX();
			previousIntermediatePosy = origin.getPosY();
			//on utilise un for pour optimiser la recherche et être sur de s'arreter! Il ne peut pas y avoir de combinaison plus longue que la diagonale ou le coté le plus long
			for(int i = 0; i < longestCombinaisonSize; i++){

				posX = 2*intermediatePiece.getPosX() - previousIntermediatePosX;
				posY = 2*intermediatePiece.getPosY() - previousIntermediatePosy;

				if(posX >= this.gameSettings.getGameBoard().getSizeX() || posX < 0  || posY >= this.gameSettings.getGameBoard().getSizeY() || posY < 0){
					inBetween.clear();
					break;
				}

				target = this.gameSettings.getGameBoard().getBoard()[posX][posY];

				if(target.getColor().getClass().equals(origin.getColor().getClass())){
					break;
				}else if(target.getColor().getClass().equals(intermediatePiece.getColor().getClass())){
					inBetween.add(target);
					previousIntermediatePosX = intermediatePiece.getPosX();
					previousIntermediatePosy = intermediatePiece.getPosY();
					intermediatePiece = target;
					continue;
				}else if(target.getColor() instanceof EmptyPiece){
					inBetween.clear();
					break;
				} 
			}

			for(Piece p : inBetween){
				this.gameSettings.getGameBoard().reverse(p.getPosX(), p.getPosY());
			}
			inBetween.clear();
		}

		this.checkPlayersPiecesCount();
	}

	protected void checkPlayersPiecesCount(){
		if(this.gameSettings.getFirstPlayer().getColor().equals(TextManager.WHITE_PLAYER)){
			this.gameSettings.getFirstPlayer().setPiecesNumber(this.gameSettings.getGameBoard().getWhitePieces().size());
		}else{
			this.gameSettings.getFirstPlayer().setPiecesNumber(this.gameSettings.getGameBoard().getBlackPieces().size());
		}
		if(this.gameSettings.getSecondPlayer().getColor().equals(TextManager.BLACK_PLAYER)){
			this.gameSettings.getSecondPlayer().setPiecesNumber(this.gameSettings.getGameBoard().getBlackPieces().size());
		}else{
			this.gameSettings.getSecondPlayer().setPiecesNumber(this.gameSettings.getGameBoard().getWhitePieces().size());
		}
	}
}