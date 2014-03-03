package com.timermanager;

/**
 * Interface définissant les actions autorisées pour le timer
 * @author Vincent Wilmet
 * @version 1.0
 */
public interface TimerManager {

	/**
	 * Cette méthode lance le minuteur et déclenche au bout de "time" secondes la 
	 * méthode {@link com.timermanager.TimerActionEvent#onTimerEnded} de 
	 * l'interface {@link com.timermanager.TimerActionEvent}
	 * @param time Le temps du timer en seconde
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
	
	public void startCountingElapsedTime();
	
	public long getElapsedTime();
}