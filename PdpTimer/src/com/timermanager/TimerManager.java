package com.timermanager;

/**
 * Interface définissant les actions autorisées pour le timer
 * @author <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface TimerManager {

	/**
	 * Cette méthode lance le minuteur et déclenche au bout de "time" secondes la 
	 * méthode {@link com.timermanager.TimerActionEvent#onTimerEnded} de 
	 * l'interface {@link com.timermanager.TimerActionEvent}
	 * @param time Le temps du timer en secondes
	 */
	public void startTimer(int time);

	/**
	 * Cette méthode stop le minuteur et déclenche immédiatement la 
	 * méthode {@link com.timermanager.TimerActionEvent#onTimerStopped} de 
	 * l'interface {@link com.timermanager.TimerActionEvent}
	 */
	public void stopTimer();

	/**
	 * Cette méthode autorise l'affichage dans la console du debugging du timer
	 */
	public void enableDebug();
	
	/**
	 * Cette méthode lance un chronomètre <br/>
	 * La valeur peut être récupérée par la méthode : {@link com.timermanager.TimerActionEvent#getElapsedTime}
	 */
	public void startCountingElapsedTime();
	
	/**
	 * Cette méthode renvoie la valeur du chronomètre <br/>
	 * Celui ci doit être lancé par la méthode : {@link com.timermanager.TimerActionEvent#startCountingElapsedTime}
	 * @return le temps calculé par le chronomètre
	 */
	public long getElapsedTime();
}