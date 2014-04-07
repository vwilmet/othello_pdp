package com.view.event;

import jnt.BenchMarkResult;

/**
 * Interface du module de BenchMark
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface BenchMarkViewButtonEventListener {
	/**
	 * Méthode soulevé lors du clique pour fermer la fenêtre de Benchmark
	 * @param result Le résultat du BenchMark une fois terminé
	 */
	public void onOkButtonPressed(BenchMarkResult result);
}
