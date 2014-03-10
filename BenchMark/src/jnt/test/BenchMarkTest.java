package jnt.test;

import java.util.Timer;
import java.util.TimerTask;

import jnt.BenchMark;
import jnt.BenchMarkResult;
import jnt.BenchMarkResultEvent;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BenchMarkTest extends TestCase implements BenchMarkResultEvent{

	private BenchMark benchmark;
	private Timer timer;
	private static int interval = 30; //35 secondes

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		benchmark = new BenchMark(this);
		timer = new Timer();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test pour vérifier que le module de BenchMark s'execute bien en 35 secondes maximum
	 * - On lance le module
	 * - On lance un timer au démarage du module
	 * - A la fin du timer si le benchmark n'as pas fini alors on soulève une erreur
	 */
	@Test
	public void testLaunch() {

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				interval--;
			}
		}, 0, 1000);

		benchmark.launch();

		if(interval < 0)
			fail("Error : BenchMark too long! ");
	}

	@Override
	public void onProgress(int progress) {}

	@Override
	public void onStart() {}

	@Override
	public void onEnd(BenchMarkResult result) {
		timer.cancel();
	}

}
