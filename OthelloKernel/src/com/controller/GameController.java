package com.controller;

import java.util.ArrayList;

import utils.FactoryHandlerException;
import utils.TextManager;

import com.error_manager.Log;
import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.factory.FactoryProducer;
import com.model.factory.interfaces.BoardFactory;
import com.model.factory.interfaces.GameSettingsFactory;
import com.model.factory.interfaces.PlayerFactory;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.model.piece.PieceImpl;
import com.timermanager.TimerManager;
import com.timermanager.TimerManagerImpl;

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

			this.setPlayablePiece();

		} catch (FactoryHandlerException e) {
			Log.error(e.getMessage());
			e.printStackTrace();
		}

		timer.startCountingElapsedTime();
	}

	protected abstract void initializeNewGame();

	protected abstract void loadFileForGame();

	private ArrayList<PieceImpl> getReversePieceAround(Piece origin){
		ArrayList<PieceImpl> neighbours = new ArrayList<PieceImpl>();
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
		int posX = 0, posY = 0;
		
		if(this.gameSettings.getCurrentPlayer().getColor().equals(TextManager.WHITE_PLAYER))
			origins = this.gameSettings.getGameBoard().getWhitePieces();
		else
			origins = this.gameSettings.getGameBoard().getBlackPieces();
		
		System.out.println("*--------------------------------------------------------------------------------*");
		System.out.println("Longest combination : " + longestCombinaisonSize);
		System.out.println("Joueur : " + this.gameSettings.getCurrentPlayer().getColor());
		System.out.println("Origins pieces : " + origins);
		System.out.println("*--------------------------------------------------------------------------*");
		for(Piece origin : origins){

			System.out.println("*-------------origin------------------------------------*");
			System.out.println("origin : " + origin);
			
			System.out.println("Nombre de voisin de couleur opposés : " + getReversePieceAround(origin).size());
			
			for(PieceImpl intermediatePiece : getReversePieceAround(origin)){
				System.out.println("*-------------Voisin--------------*");
				System.out.println("Voisin : " +  intermediatePiece);

				//on utilise un for pour optimiser la recherche et être sur de s'arreter! Il ne peut pas y avoir de combinaison plus longue que la diagonale ou le coté le plus long
				for(int i = 0; i < longestCombinaisonSize; i++){
					System.out.println("Recherche de combinaison | i : " + i);
					System.out.println("Inetermediaire : " + intermediatePiece);
					
					posX = 2*intermediatePiece.getPosX()-origin.getPosX();
					posY = 2*intermediatePiece.getPosY()- origin.getPosY();

					if(posX >= this.gameSettings.getGameBoard().getSizeX() || posX < 0 || posY >= this.gameSettings.getGameBoard().getSizeY() || posY < 0)
						break;
					
					target = this.gameSettings.getGameBoard().getBoard()[posX][posY];
					
					System.out.println("Target : " + target);
					
					if(target.getColor().getClass().equals(origin.getColor().getClass())){
						break;
					}else if(target.getColor().getClass().equals(intermediatePiece.getColor().getClass())){
						continue;
					}else if(target.getColor() instanceof EmptyPiece){
						this.gameSettings.getGameBoard().setPiecePlayable(posX, posY);
						break;
					}
				}
				System.out.println("*---------Fin Voisin------*");
			}

			System.out.println("*-------------fin origin----------------------------------*");
		}
	}

	protected void reverseInbetweenPieceAfterPlaying(int originPosX, int originPosY){

		ArrayList<PieceImpl> inBetween = new ArrayList<PieceImpl>();
		int longestCombinaisonSize = (this.gameSettings.getGameBoard().getSizeX() > this.gameSettings.getGameBoard().getSizeY() ? this.gameSettings.getGameBoard().getSizeX() : this.gameSettings.getGameBoard().getSizeY());
		PieceImpl origin = this.gameSettings.getGameBoard().getBoard()[originPosX][originPosY];
		PieceImpl target = null;
		int posX = 0, posY = 0;


		for(PieceImpl intermediatePiece : getReversePieceAround(origin)){
			inBetween.add(intermediatePiece);
			//on utilise un for pour optimiser la recherche et être sur de s'arreter! Il ne peut pas y avoir de combinaison plus longue que la diagonale ou le coté le plus long
			for(int i = 0; i < longestCombinaisonSize; i++){

				posX = 2 * intermediatePiece.getPosX() - origin.getPosX();
				posY = 2 * intermediatePiece.getPosY() - origin.getPosY();

				if(posX >= this.gameSettings.getGameBoard().getSizeX() || posX < 0  || posY >= this.gameSettings.getGameBoard().getSizeY() || posY < 0){
					inBetween.clear();
					break;
				}

				target = this.gameSettings.getGameBoard().getBoard()[posX][posY];

				if(target.getColor().getClass().equals(origin.getColor().getClass())){
					break;
				}else if(target.getColor().getClass().equals(intermediatePiece.getColor().getClass())){
					inBetween.add(target);
					continue;
				}else if(target.getColor() instanceof EmptyPiece){
					inBetween.clear();
					break;
				}
				intermediatePiece = target;
			}

			for(PieceImpl p : inBetween){
				this.gameSettings.getGameBoard().getBoard()[p.getPosX()][p.getPosY()].reverse();
			}
		}
	}

}