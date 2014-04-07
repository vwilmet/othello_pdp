package utils;

import java.util.ArrayList;

import com.model.BoardObservable;
import com.model.GameSettings;
import com.model.piece.EmptyPiece;
import com.model.piece.Piece;
import com.publisher.BoardPublisher;

/**
 * Cette classe contient les méthodes spécifiques aux méthodes de gestions de modification du plateau après chaque pose de pion <br/>
 * Ces méthodes sont ici car elles doivent être utilisables dans plusieurs classes différentes
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class GameControllers {

	/**
	 * Cette méthode permet de calculer les pièces jouables dans l'othellier
	 * @param gameSettings Le model contenant l'othellier
	 */
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
	}

	/**
	 * Cette méthode permet de récupérer les voisins de la pièce passé en paramètre. Ces voisins sont de couleur opposés à la pièce d'origine et ne sont pas des pièces vide. 
	 * @param gameBoard L'othellier on l'on va chercher les voisins
	 * @param origin La pièce à l'origine de la recherche. On se base sur sa couleur et position pour chercher les voisins de couleur différente 
	 * @return Une ArrayList de pièce qui va contenir tous les voisins
	 */
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

	/**
	 * Cette méthode permet de calculer quelles sont les pions de l'adversaire qui doivent être retournée après avoir poser la pièce à la position originPosX, originPosY
	 * @param gameBoard L'othellier qui contient toutes les pièces
	 * @param originPosX La position en X de la pièce posée
	 * @param originPosY La position en Y de la pièce posée
	 */
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
	}
	
	/**
	 * Cette méthode permet d'attribuer à chaque joueur le nombre de pièces qu'ils ont sur l'othellier
	 * @param gameSettings Le model qui contient les données
	 */
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
