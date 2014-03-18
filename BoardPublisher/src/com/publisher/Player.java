package com.publisher;

/**
 * Classe permettant de gérer la partie joueur dans la sauvegarde de fichier. 
 * @author <ul><li> Benjamin Letourneau </li></ul>
 * @version 1.0
 */
public class Player {
	/**
	 * Variables concernant le joueur.
	 */
	private String name, color, type; 
	
	/**
	 * Numero du joueur (1 ou 2).
	 */
	private int number;
	
	public Player(String name, String color, String type, int number){
		this.name = name;
		this.color = color;
		this.type = type;
		this.number = number;
	}
	
	/**
	 * Accesseur (lecture) sur le nom d'utilisateur.
	 * @return String : Nom de l'utilisateur.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Accesseur (lecture) sur la couleur de l'utilisateur.
	 * @return String : Couleur de l'utilisateur (white ou black).
	 */
	public String getColor(){
		return this.color;
	}
	
	/**
	 * Accesseur (lecture) sur le type d'utilisateur.
	 * @return String : Type d'utilisateur (humain ou machine).
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * Accesseur (lecture) sur le numéro de l'utilisateur
	 * @return int : Numéro de l'utilisateur.
	 */
	public int getNumber(){
		return this.number;
	}
}
