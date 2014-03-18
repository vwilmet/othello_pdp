package com.publisher;

import java.util.Scanner;

public class Player {
	/**
	 * 
	 */
	private String name, color, type; 
	
	/**
	 * 
	 */
	private int number;
	
	public Player(String name, String color, String type, int number){
		this.name = name;
		this.color = color;
		this.type = type;
		this.number = number;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getColor(){
		return this.color;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getNumber(){
		return this.number;
	}

}
