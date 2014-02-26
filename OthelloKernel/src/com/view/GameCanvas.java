package com.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class GameCanvas extends Canvas {

	private static final int line_size = 2;
	public static final int SizeX = 50;
	public static final int SizeY = 50;

	public GameCanvas() {
		setBackground(Color.white);
	}

	private void drawGrid(Graphics2D g) {

		// draw grid
		for (int i = 0; i < SizeX; i++) {
			for (int j = 0; j < SizeY; j++) {
				// g.drawRect(i*Piece.size, j*Piece.size, Piece.size+
				// line_size/2, Piece.size+ line_size/2)
				// TODO
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(line_size));
		drawGrid(g2);
	}
}
