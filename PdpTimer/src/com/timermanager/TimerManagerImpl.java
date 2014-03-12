package com.timermanager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @mainpage Module Timer
 *
 * 
 * Ce module a pour but de proposer une gestion d'un chronomètre en milli-secondes ainsi que d'un minuteur en secondes
 * 
 * <b>Exemple de BONNE utilisation du module : </b>
 * 
 * 	TimerActionEvent t = new TimerActionEvent() {
 *
 *			@Override
 *			public void onTimerEnded() {
 *				System.out.println("timer ended !! : ");
 *			}
 *
 *			@Override
 *			public void onTimerStopped() {
 *				System.out.println("timer stopped !!");
 *			}
 *		};
 *
 *		TimerManager time = new TimerManagerImpl(t);
 *		time.enableDebug();
 *		time.startTimer(2);
 *		time.stopTimer();
 *		time.startCountingElapsedTime();
 */

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

	/**
	 * Entier qui va contenir le temps restant pour le minuteur
	 */
	private int interval;
	/**
	 * Objet du package utils de java permettant de lancer une tâche à intervalle régulier
	 */
	private Timer timer;
	/**
	 * L'interface qui va permettre la communication pour connaître les différentes étapes 
	 * des différentes méthodes. <br/> Exemple : Fin du minuteur.
	 */
	private TimerActionEvent timerInterface;
	/**
	 * Flag qui va permettre de contrôler l'affichage dans la sortie d'erreur lors d'éventuelles erreurs
	 */
	private boolean DEBUG;
	/**
	 * Booléen qui permet de contrôler qu'une unique minuterie est lancée à l'instant donné
	 */
	private boolean isRunning;
	/**
	 * Variable qui va contenir, pour le mode chronomètre, l'heure actuelle en milli-secondes
	 */
	private long elapstedTime;

	/**
	 * Constructeur qui initialise le timer
	 * @param timerInterface 
	 */
	public TimerManagerImpl() {
		this.timer = new Timer();
		this.isRunning = false;
		this.DEBUG = false;
		this.elapstedTime = 0;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.TimerManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.timermanager.TimerManager#setTimerActionEvent}
	 */
	@Override
	public void setTimerActionEvent(TimerActionEvent timerInterface){
		this.timerInterface = timerInterface;
	}

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.manager.TimerManager} pour stocker l'objet de la classe
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

	/**
	 ** <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br>Utiliser l'interface {@link com.timermanager.TimerManager} pour stocker l'objet de la classe
	 * <br>Voir {@link com.timermanager.TimerManager#getElepsedTimeInMinAndSeconde}
	 */
	@Override
	public String getElepsedTimeInMinAndSeconde(){
		long minutes = TimeUnit.MILLISECONDS.toMinutes(getElapsedTime());
		long secondes = TimeUnit.MILLISECONDS.toSeconds(getElapsedTime()) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getElapsedTime())); 
		String format = "",
				result = "";
		if(minutes > 0 && secondes > 0)
			result = String.format("%d min, %d sec", 
					minutes,
					secondes
					);
		else if (minutes == 0)
			result = String.format("%d sec", 
					secondes
					);
		else if(minutes > 0)
			result = String.format("%d min", 
					minutes
					);

		return result;
	}
}
