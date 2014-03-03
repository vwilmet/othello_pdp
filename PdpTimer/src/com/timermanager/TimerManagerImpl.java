package com.timermanager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe gérant les différentes méthodes et comportements du timer
 * Celle-ci implémente l'interface {@link com.timermanager.TimerManager}
 * @author  <ul>
 *         <li>Morgane Badré</li>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class TimerManagerImpl implements TimerManager{

	private int interval;
	private Timer timer;
	private TimerActionEvent timerInterface;
	private boolean DEBUG;
	private boolean isRunning;
	private long elapstedTime;

	/**
	 * Constructeur qui initialise l'interface d'évènements et initialise le timer
	 * @param timerInterface L'object interface qui sera appelé lors des évènements soulevés par le timer
	 */
	public TimerManagerImpl(TimerActionEvent timerInterface) {
		this.timer = new Timer();
		this.timerInterface = timerInterface;
		this.isRunning = false;
		this.DEBUG = false;
		this.elapstedTime = 0;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.timermanager.TimerManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.timermanager.TimerManager#startTimer}
	 */
	@Override
	public void startTimer(int time){
		if(this.isRunning && this.DEBUG){ System.err.println("Erreur : Deux timer demandés en même temps! Timer ignoré."); return;}

		this.interval = time;
		this.isRunning = true;

		this.timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				if(TimerManagerImpl.this.DEBUG)System.out.println("Time : " + TimerManagerImpl.this.interval);
				if (TimerManagerImpl.this.interval == 0){
					TimerManagerImpl.this.timer.cancel();
					TimerManagerImpl.this.isRunning = false;
					TimerManagerImpl.this.timerInterface.onTimerEnded();
				}
				TimerManagerImpl.this.interval--;
			}
		}, 0, 1000);
	}
	
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.timermanager.TimerManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.timermanager.TimerManager#stopTimer}
	 */
	@Override
	public void stopTimer(){
		this.timer.cancel();
		this.isRunning = false;
		this.timerInterface.onTimerStopped();
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.timermanager.TimerManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.timermanager.TimerManager#enableDebug}
	 */
	@Override
	public void enableDebug() {
		this.DEBUG  = true;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.timermanager.TimerManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.timermanager.TimerManager#startCountingElapsedTime}
	 */
	@Override
	public void startCountingElapsedTime() {
		this.elapstedTime = System.currentTimeMillis();
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.timermanager.TimerManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.timermanager.TimerManager#getElapsedTime}
	 */
	@Override
	public long getElapsedTime() {
		return System.currentTimeMillis() - this.elapstedTime;
	}

}
