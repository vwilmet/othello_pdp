import com.board.PieceEnum;
import com.utils.WrongPlayablePositionException;

public class AIModule {

	/**
	 * @param args
	 * @throws WrongPlayablePositionException 
	 */
	public static void main(String[] args) throws WrongPlayablePositionException {
		//Model myModel = new Model();
		
		PieceEnum[][] p  = new PieceEnum[5][5];
		for(int i =0; i< 5; i++)
			for(int j =0; j< 5; j++){
				p[i][j] = PieceEnum.EMPTYPIECE;
				System.out.println("p : " + p[i][j].getValue());
			}
		//System.out.println("p : " + p.getValue());

	}
}
