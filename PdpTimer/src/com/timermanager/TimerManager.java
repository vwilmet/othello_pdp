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
	 * Cette méthode lance le minuteur et déclenche au bout de "time" milli-secondes la 
	 * méthode {@link com.timermanager.TimerActionEvent#onTimerEnded} de 
	 * l'interface {@link com.timermanager.TimerActionEvent}
	 * <b>Attention : </b>Cette méthode ne peut être appelée qu'une seule fois, en même temps ou après avoir 
	 * arrêté le timer, à l'aide de la méthode {@link com.timermanager.TimerManager#stopTimer}
	 * @param time Le temps du timer en milli-secondes
	 */
	public void startTimer(int time);

	/**
	 * Cette méthode stoppe le minuteur et déclenche immédiatement la 
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
	 * @return le temps calculé par le chronomètre en milli-secondes
	 */
	public long getElapsedTime();
	
	/**
	 * Méthode qui initialise l'interface de communication
	 * @param timerInterface L'object interface qui sera appelé lors des évènements soulevés par le timer
	 */
	public void setTimerActionEvent(TimerActionEvent timerInterface);
	
	/**
	 * Cette méthode renvoie la valeur du chronomètre sous la formes "X min, Y sec"<br/>
	 * Celui ci doit être lancé par la méthode : {@link com.timermanager.TimerActionEvent#startCountingElapsedTime}
	 * @return Le temps calculé par le chronomètre en minutes et secondes
	 */
	public String getElapsedTimeInMinAndSeconde();
}