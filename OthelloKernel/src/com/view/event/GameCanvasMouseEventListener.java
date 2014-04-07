package com.view.event;

/**
 * Interface contenant les méthodes soulevés lors des cliques souris sur l'othellier
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public interface GameCanvasMouseEventListener {
	/**
	 * Méthodes soulevé par le clique gauche
	 * @param i La position de la pièce cliqué en X
	 * @param j La position de la pièce cliqué en Y
	 */
	public void onLeftMouseButtonPressed(int i, int j);
	/**
	 * Méthodes soulevé par le clique droit
	 * @param i La position de la pièce cliqué en X
	 * @param j La position de la pièce cliqué en Y
	 */
	public void onRightMouseButtonPressed(int i, int j);
}
