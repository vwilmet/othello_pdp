package com.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import com.view.event.MouseEventListener;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class GameCanvas extends Canvas implements MouseListener{

	private static final int line_size = 2;
	public static final int SizeX = 10;	
	public static final int SizeY = 4;
	private int viewWidth;
	private int viewHeight;
	private Dimension gridSize;
	public static int pieceSize = 12;
	private Dimension margin;
	private MouseEventListener mouseEvent;

	public GameCanvas(int width, int height, MouseEventListener event) {
		setBackground (Color.white);
		this.viewWidth = width;
		this.viewHeight = height;
		this.mouseEvent = event;

		this.addMouseListener(this);
		
		int pieceSizeX = (width - (SizeX*(line_size/2))) / SizeX;
		int pieceSizeY = (height - (SizeY*(line_size/2))) / SizeY;

		if(pieceSizeX > pieceSizeY){
			gridSize = new Dimension((SizeX*pieceSizeY) + (SizeX*(line_size/2)), (SizeY*pieceSizeY) + (SizeY*(line_size/2)));
			pieceSize = pieceSizeY;
			margin = new Dimension((width-gridSize.width)/2, 0);
		}else{
			gridSize = new Dimension((SizeX*pieceSizeX) + (SizeX*(line_size/2)), (SizeY*pieceSizeX) + (SizeY*(line_size/2)));
			pieceSize = pieceSizeX;
			margin = new Dimension(0, (height-gridSize.height)/2);
		}

	}


	private void drawGrid(Graphics2D g){
		//draw grid
		for(int i = 0; i < SizeX; i ++){
			for(int j = 0; j < SizeY; j++){
				g.drawRect(i*pieceSize+margin.width, j*pieceSize+margin.height, pieceSize+ line_size/2, pieceSize+ line_size/2);
			}
		}
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(line_size));
		drawGrid(g2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX() - margin.width;
		int y = e.getY() - margin.height;

		if(SwingUtilities.isLeftMouseButton(e))
			mouseEvent.onLeftMouseButtonPressed(x, y);
		else if(SwingUtilities.isRightMouseButton(e))
			mouseEvent.onRightMouseButtonPressed(x, y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
