package AI;
import java.awt.Point;
import java.util.List;
import java.util.Set;


public interface ArtificialIntelligence {

	 /**
     * Calculate the next move evaluated by the algorithm.
     * @param player an integer representing the player asking the next move.
     * @return the next position evaluated by the algorithm and if no next position for the current player, null.
     */
	public Point nextMove(Integer player);

	 /**
     * Calculate an entire list of positions which can make the player win the game.
     * @param player an integer representing the player asking the next moves.
     * @return a list of position to win the game evaluated by the algorithm.
     */
	public List<Point> nextMoves(Integer player);
	
	
	/**
     * Return an Integer representing the status of the game (winning, losing or draw).
     * @param player an integer representing the player asking the status.
     * @return an integer for the status.
     */
	public Integer winStatus(Integer player);
	
	/**
     * Initialize the AI in order to calculate moves
     * @param whitePiece a list of all the pieces of the player 1.
     * @param blackPiece a list of all the pieces of the player 2.
     * @param boardWidth an integer representing the width of the board.
     * @param boardHeight an integer representing the height of the board.
     * @return a boolean, true if the initialization is finished without error, otherwise false.
     */
	public Boolean initialize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight);
	
	/**
     * Actualize the informations in order to recalculate moves
     * @param whitePiece a list of all the pieces of the player 1.
     * @param blackPiece a list of all the pieces of the player 2.
     * @param boardWidth an integer representing the width of the board.
     * @param boardHeight an integer representing the height of the board.
     * @return a boolean, true if the initialization is finished without error, otherwise false.
     */
	//public Boolean actualize(Set<Point> whitePiece, Set<Point> blackPiece, Integer boardWidth, Integer boardHeight); Pas sûr de l'utilisté encore
	
	/**
     * Notify the AI the move chose by the player
     * @param pos the position of the chosen move.
     * @param player the player who did the move.
     */
	public void notifyChosenMove(Point pos, Integer player);
	
	/**
     * Terminate the algorithm searching for solution.
     * @return a boolean, true if the algorithm has been stopped, otherwise false.
     */
	public Boolean completeReflexion();
			
	
}
