package com.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameCanvas extends Canvas{
	
	private static final int line_size = 2;
	public static final int SizeX = 50;
	public static final int SizeY = 50;
	
	public GameCanvas() {
		setBackground (Color.white);
	}

	private void drawGrid(Graphics2D g){
		int size = 12;
		//draw grid
		for(int i = 0; i < SizeX; i ++){
			for(int j = 0; j < SizeY; j++){
				g.drawRect(i*size, j*size, size+ line_size/2, size+ line_size/2);
				//TODO
			}
		}
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(line_size));
		drawGrid(g2);
	}
}
