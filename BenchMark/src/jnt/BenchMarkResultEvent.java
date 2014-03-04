package jnt;

/**
 * Interface définissant les actions du module de BenchMark
 * @author <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 *
 */
public interface BenchMarkResultEvent {

	/**
	 * 
	 * @param progress
	 */
	public void onProgress(int progress);
	/**
	 * 
	 */
	public void onStart();
	/**
	 * 
	 * @param result
	 */
	public void onEnd(BenchMarkResult result);
}
