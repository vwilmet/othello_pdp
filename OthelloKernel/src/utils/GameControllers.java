package utils;

import java.util.ArrayList;

import com.model.BoardObservable;
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

			for(Piece intermediatePiece : GameControllers.getReversePieceAround(gameSettings.getGameBoard(), origin)){
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
		gameSettings.getGameBoard().refresh();
	}

	private static ArrayList<Piece> getReversePieceAround(BoardObservable gameBoard, Piece origin){
		ArrayList<Piece> neighbours = new ArrayList<Piece>();
		int posX, posY;

		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++){
				posX = origin.getPosX()+j-1;
				posY = origin.getPosY()+i-1;

				if(posX >= gameBoard.getSizeX() || posX < 0 || posY >= gameBoard.getSizeY() || posY < 0)
					continue;

				if(posX == origin.getPosX() && posY == origin.getPosY())
					continue;

				if(!gameBoard.getBoard()[posX][posY].getColor().getClass().equals(origin.getColor().getClass()) && 
						!gameBoard.getBoard()[posX][posY].getColor().getClass().equals(EmptyPiece.class))
					neighbours.add(gameBoard.getBoard()[posX][posY]);

			}
		return neighbours;
	}

	public static void reverseInbetweenPieceAfterPlaying(BoardObservable gameBoard, int originPosX, int originPosY){

		ArrayList<Piece> inBetween = new ArrayList<Piece>();
		int longestCombinaisonSize = 
				(gameBoard.getSizeX() > gameBoard.getSizeY() ?
						gameBoard.getSizeX() :
							gameBoard.getSizeY());

		Piece origin = gameBoard.getBoard()[originPosX][originPosY];
		Piece target = null;
		int posX = 0, posY = 0, previousIntermediatePosX, previousIntermediatePosy;

		for(Piece intermediatePiece : GameControllers.getReversePieceAround(gameBoard, origin)){
			inBetween.add(intermediatePiece);
			previousIntermediatePosX = origin.getPosX();
			previousIntermediatePosy = origin.getPosY();
			//on utilise un for pour optimiser la recherche et être sur de s'arreter! Il ne peut pas y avoir de combinaison plus longue que la diagonale ou le coté le plus long
			for(int i = 0; i < longestCombinaisonSize; i++){

				posX = 2*intermediatePiece.getPosX() - previousIntermediatePosX;
				posY = 2*intermediatePiece.getPosY() - previousIntermediatePosy;

				if(posX >= gameBoard.getSizeX() || posX < 0  || posY >= gameBoard.getSizeY() || posY < 0){
					inBetween.clear();
					break;
				}

				target = gameBoard.getBoard()[posX][posY];

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
				gameBoard.reverse(p.getPosX(), p.getPosY());
			}
			inBetween.clear();
		}
		gameBoard.refresh();
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
