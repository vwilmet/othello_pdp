package com.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import utils.ViewSettings;

import com.model.Board;
import com.model.piece.BlackPiece;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.view.event.MouseEventListener;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class GameCanvas extends Canvas implements MouseListener{

	private Dimension gridSize;
	private int pieceSize;
	private Dimension margin;
	private MouseEventListener mouseEvent;
	private Board board;

	public GameCanvas(MouseEventListener event, Board board) {
		setBackground (Color.white);
		this.mouseEvent = event;
		this.board = board;
		this.pieceSize = ViewSettings.PIECE_WIDTH;

		this.addMouseListener(this);

		gridSize = new Dimension((board.getSizeX()*pieceSize), (board.getSizeY()*pieceSize));

		int marginX = (ViewSettings.GAMEVIEW_COMPONENT_VIEW_WIDTH-gridSize.width)/2;
		int marginY = (ViewSettings.GAMEVIEW_COMPONENT_VIEW_HEIGHT-gridSize.height)/2;

		margin = new Dimension(
				(marginX > marginY ? marginX : 0),
				(marginX > marginY ? 0 : marginY)
				);

		System.out.println("gridSize : " + gridSize);
		System.out.println("margin : " + margin);
	}

	private void drawGrid(Graphics2D g){
		//draw grid
		for(int i = 0; i < board.getSizeX(); i ++){
			for(int j = 0; j < board.getSizeY(); j++){
				g.drawRect(i*pieceSize+margin.width, j*pieceSize+margin.height, pieceSize, pieceSize);

				Image img;
				try {
					Piece p = board.getBoard()[i][j];
					if(p.getColor() instanceof WhitePiece)
						img = ImageIO.read(new File("./resources/fx/piece/white_piece.png"));
					else if (p.getColor() instanceof BlackPiece)
						img = ImageIO.read(new File("./resources/fx/piece/black_piece.png"));
					else
						continue;
					g.drawImage(img, i*pieceSize+margin.width+ ViewSettings.DRAW_LINE_SIZE/2, j*pieceSize+margin.height+ ViewSettings.DRAW_LINE_SIZE/2, this);
				} catch (IOException e) {
					e.printStackTrace();
				}




			}
		}


	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(ViewSettings.DRAW_LINE_SIZE));
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
