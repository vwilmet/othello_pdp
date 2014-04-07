package test;

import static org.junit.Assert.assertTrue;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ai.ArtificialIntelligence;
import com.ai.impl.ArtificialIntelligenceImpl;


public class LogicielTest {

	ArtificialIntelligence myAI;
	Set<Point> whitePiece;
	Set<Point> blackPiece;
	
	
	@Before
	public void setUp() throws Exception {
		this.whitePiece = new HashSet<Point>();
		this.blackPiece = new HashSet<Point>();
		this.whitePiece.add(new Point(3,3));
		this.whitePiece.add(new Point(4,4));
		this.blackPiece.add(new Point(3,4));
		this.blackPiece.add(new Point(4,3));
		this.myAI = new ArtificialIntelligenceImpl();
		this.myAI.chooseDifficulty(2);
		this.myAI.initialize(whitePiece, blackPiece, 8, 8);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCorrectPosition() {
		
		Set<Point> possiblePosition = new HashSet<Point>();
		possiblePosition.add(new Point(5,3));
		possiblePosition.add(new Point(4,2));
		possiblePosition.add(new Point(3,5));
		possiblePosition.add(new Point(2,4));
		
		assertTrue("Position non valide pour le plateau actuel et le premier joueur",possiblePosition.contains(this.myAI.nextMove(1)));
	}
	
}
