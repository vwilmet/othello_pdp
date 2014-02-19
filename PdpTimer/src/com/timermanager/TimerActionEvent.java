package com.timermanager;

/**
 * Interface contenant les évenements soulevés par le timer.
 * Elles doivent être implémentée par la classe qui lance le timer
 * @author Vincent Wilmet
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
