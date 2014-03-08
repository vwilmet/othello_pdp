package com.timermanager;

/**
 * Interface contenant les évènements soulevés par le timer.
 * Les méthodes doivent être implémentées par la classe qui lance le timer
 * @author <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface TimerActionEvent {
	
	/**
	 * Méthode appelée dès que le temps calculé par le timer est terminé
	 */
	public void onTimerEnded();
	
	/**
	 * Méthode appelée dès que le timer est stoppé de force
	 */
	public void onTimerStopped();
}
