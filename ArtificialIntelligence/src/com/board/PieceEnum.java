package com.board;

/**
 * Enumeration qui représente les différentes pièces de plateau.
 * @author <ul><li>Nicolas Yvon</li></ul>
 * @version 1.0
 */
public enum PieceEnum {
	/**
	 * Cette valeur représente une case vide
	 */
	EMPTYPIECE(0),
	
	/**
	 * Cette valeur représente une pièce blanche
	 */
	WHITEPIECE(1), 
	/**
	 * Cette valeur représente une pièce noire
	 */
	BLACKPIECE(2);

	/**
	 * Cette variable représente la valeur de l'enumération.
	 */
	private Integer value;
	
	/**
	 * Constructeur privé de l'enumération. Il permet d'attribuer sa valeur à la pièce choisie.
	 * @param value : int, la valeur choisie.
	 */
	private PieceEnum(int value){
		this.value = value;
		
	}
	
	/**
	 * Accesseur en lecture de la variable value.
	 * @return la valeur de l'objet
	 */
	public Integer getValue() {
		return value;
	}
	

}
