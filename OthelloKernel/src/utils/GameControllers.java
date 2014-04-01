package utils;

import java.util.ArrayList;

import com.model.GameSettings;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.publisher.BoardPublisher;

public class GameControllers {

	public static void setPlayablePiece(GameSettings gameSettings){

		gameSettings.getGameBoard().resetPlayablePosition();
		ArrayList<Piece> origins;
		int longestCombinaisonSize = (gameSettings.getGameBoard().getSizeX() > gameSettings.getGameBoard().getSizeY() ? gameSettings.getGameBoard().getSizeX() : gameSettings.getGameBoard().getSizeY());
		Piece target = null;
		int posX = 0, posY = 0, previousIntermediatePosX, previousIntermediatePosy;

		if(gameSettings.getCurrentPlayer().getColor().equals(BoardPublisher.WHITE_PLAYER))
			origins = new ArrayList<Piece>(gameSettings.getGameBoard().getWhitePieces());
		else
			origins = new ArrayList<Piece>(gameSettings.getGameBoard().getBlackPieces());

		for(Piece origin : origins){

			for(Piece intermediatePiece : GameControllers.getReversePieceAround(gameSettings, origin)){
				previousIntermediatePosX = origin.getPosX();
				previousIntermediatePosy = origin.getPosY();

				//on utilise un for pour optimiser la recherche et être sur de s'arreter! Il ne peut pas y avoir de combinaison plus longue que la diagonale ou le coté le plus long
				for(int i = 0; i < longestCombinaisonSize; i++){
					posX = 2*intermediatePiece.getPosX() - previousIntermediatePosX;
					posY = 2*intermediatePiece.getPosY() - previousIntermediatePosy;

					if(posX >= gameSettings.getGameBoard().getSizeX() || posX < 0 || posY >= gameSettings.getGameBoard().getSizeY() || posY < 0)
						break;

					target = gameSettings.getGameBoard().getBoard()[posX][posY];

					if(target.getColor().getClass().equals(origin.getColor().getClass())){
						break;
					}else if(target.getColor().getClass().equals(intermediatePiece.getColor().getClass())){
						previousIntermediatePosX = intermediatePiece.getPosX();
						previousIntermediatePosy = intermediatePiece.getPosY();
						intermediatePiece = target;
						continue;
					}else if(target.getColor() instanceof EmptyPiece){
						gameSettings.getGameBoard().setPiecePlayable(posX, posY);
						break;
					}
				}
			}

		}
	}

	private static ArrayList<Piece> getReversePieceAround(GameSettings gameSettings, Piece origin){
		ArrayList<Piece> neighbours = new ArrayList<Piece>();
		int posX, posY;

		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				posX = origin.getPosX()+j-1;
				posY = origin.getPosY()+i-1;

				if(posX >= gameSettings.getGameBoard().getSizeX() || posX < 0 || posY >= gameSettings.getGameBoard().getSizeY() || posY < 0)
					continue;

				if(posX == origin.getPosX() && posY == origin.getPosY())
					continue;

				if(!gameSettings.getGameBoard().getBoard()[posX][posY].getColor().getClass().equals(origin.getColor().getClass()) && 
						!gameSettings.getGameBoard().getBoard()[posX][posY].getColor().getClass().equals(EmptyPiece.class))
					neighbours.add(gameSettings.getGameBoard().getBoard()[posX][posY]);

			}
		return neighbours;
	}

	public static void reverseInbetweenPieceAfterPlaying(GameSettings gameSettings, int originPosX, int originPosY){

		ArrayList<Piece> inBetween = new ArrayList<Piece>();
		int longestCombinaisonSize = 
				(gameSettings.getGameBoard().getSizeX() > gameSettings.getGameBoard().getSizeY() ?
						gameSettings.getGameBoard().getSizeX() :
							gameSettings.getGameBoard().getSizeY());

		Piece origin = gameSettings.getGameBoard().getBoard()[originPosX][originPosY];
		Piece target = null;
		int posX = 0, posY = 0, previousIntermediatePosX, previousIntermediatePosy;

		for(Piece intermediatePiece : GameControllers.getReversePieceAround(gameSettings, origin)){
			inBetween.add(intermediatePiece);
			previousIntermediatePosX = origin.getPosX();
			previousIntermediatePosy = origin.getPosY();
			//on utilise un for pour optimiser la recherche et être sur de s'arreter! Il ne peut pas y avoir de combinaison plus longue que la diagonale ou le coté le plus long
			for(int i = 0; i < longestCombinaisonSize; i++){

				posX = 2*intermediatePiece.getPosX() - previousIntermediatePosX;
				posY = 2*intermediatePiece.getPosY() - previousIntermediatePosy;

				if(posX >= gameSettings.getGameBoard().getSizeX() || posX < 0  || posY >= gameSettings.getGameBoard().getSizeY() || posY < 0){
					inBetween.clear();
					break;
				}

				target = gameSettings.getGameBoard().getBoard()[posX][posY];

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
				gameSettings.getGameBoard().reverse(p.getPosX(), p.getPosY());
			}
			inBetween.clear();
		}

		GameControllers.checkPlayersPiecesCount(gameSettings);
	}
	
	public static void checkPlayersPiecesCount(GameSettings gameSettings){
		if(gameSettings.getFirstPlayer().getColor().equals(BoardPublisher.WHITE_PLAYER)){
			gameSettings.getFirstPlayer().setPiecesNumber(gameSettings.getGameBoard().getWhitePieces().size());
		}else{
			gameSettings.getFirstPlayer().setPiecesNumber(gameSettings.getGameBoard().getBlackPieces().size());
		}
		if(gameSettings.getSecondPlayer().getColor().equals(BoardPublisher.BLACK_PLAYER)){
			gameSettings.getSecondPlayer().setPiecesNumber(gameSettings.getGameBoard().getBlackPieces().size());
		}else{
			gameSettings.getSecondPlayer().setPiecesNumber(gameSettings.getGameBoard().getWhitePieces().size());
		}
	}
}
