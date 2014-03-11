package com.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;


import com.model.Board;
import com.model.BoardObservable;
import com.model.piece.BlackPiece;
import com.model.piece.Piece;
import com.model.piece.WhitePiece;
import com.model.view.ViewSettings;
import com.view.event.GameCanvasMouseEventListener;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class GameCanvas extends Canvas implements MouseListener, Observer{

	private Dimension gridSize;
	private Dimension margin;
	private GameCanvasMouseEventListener mouseEvent;
	private Board board;
	private int canvasWidth, canvasHeight;
	private int pieceSizeWidth, pieceSizeHeight;

	public GameCanvas(int canvasWidth, int canvasHeight){
		setBackground (Color.white);
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
	}

	public void setMouseListener(GameCanvasMouseEventListener event){
		this.mouseEvent = event;
		this.addMouseListener(this);
	}

	private void drawGrid(Graphics2D g){
		//draw grid
		for(int i = 0; i < board.getSizeX(); i ++){
			for(int j = 0; j < board.getSizeY(); j++){
				g.drawRect(i*this.pieceSizeWidth+margin.width, j*this.pieceSizeHeight+margin.height, this.pieceSizeWidth, this.pieceSizeHeight);

				Image img;
				try {
					Piece p = board.getBoard()[i][j];
					if(p.getColor() instanceof WhitePiece)
						img = ImageIO.read(new File(ViewSettings.IMAGE_PIECE_PATH + ViewSettings.WHITE_PIECE_IMG));
					else if (p.getColor() instanceof BlackPiece)
						img = ImageIO.read(new File(ViewSettings.IMAGE_PIECE_PATH + ViewSettings.BLACK_PIECE_IMG));
					else if(p.isPlayable())
						img = ImageIO.read(new File(ViewSettings.IMAGE_PIECE_PATH + ViewSettings.POSSIBLE_PIECE_IMG));
					else
						continue;
					g.drawImage(
							scaleImage(
									img,
									this.pieceSizeWidth,
									this.pieceSizeHeight
									),
									i*this.pieceSizeWidth+margin.width+ ViewSettings.DRAW_LINE_SIZE/2,
									j*this.pieceSizeHeight+margin.height+ ViewSettings.DRAW_LINE_SIZE/2,
									this);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(ViewSettings.DRAW_LINE_SIZE));
		if(board != null) drawGrid(g2);
	}

	private void calculatePieceSize(){
		int boardSizeX = board.getSizeX(), 
				boardSizeY = board.getSizeY();

		int pieceSizeX = (this.canvasWidth - (boardSizeX*(ViewSettings.DRAW_LINE_SIZE*2))) / boardSizeX;
		int pieceSizeY = (this.canvasHeight - (boardSizeY*(ViewSettings.DRAW_LINE_SIZE*2))) / boardSizeY;

		if(pieceSizeX > pieceSizeY){
			pieceSizeWidth = pieceSizeY;
			pieceSizeHeight = pieceSizeY;
		}else{
			pieceSizeWidth = pieceSizeX;
			pieceSizeHeight = pieceSizeX;
		}

		gridSize = new Dimension((boardSizeX*this.pieceSizeWidth), (boardSizeY*this.pieceSizeHeight));

		int marginX = (this.canvasWidth - gridSize.width)/2;
		int marginY = (this.canvasHeight - gridSize.height)/2;

		margin = new Dimension(
				(marginX > marginY ? marginX : 0),
				(marginX > marginY ? 0 : marginY)
				);
	}
	
	private Image scaleImage(Image image, int width, int height) {
		int type = BufferedImage.TYPE_INT_ARGB;

		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();

		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(image, 0, 0, width, height, this);
		g.dispose();

		return resizedImage;
	}

	public void refreshView(){
		this.repaint();

	}

	private int[] getPiecePositionFromCoordinates(int x, int y){
		int[] result = new int[2];

		if(x > gridSize.width || x < 0 || y < 0 || y > gridSize.height){
			result[0] = -1;
			result[1] = -1;
			return result;
		}

		result[0] = x/pieceSizeWidth;
		result[1] = y/pieceSizeHeight;

		return result;
	}

	public void setData(BoardObservable board){
		if(board != null){
		this.board = board;
		this.calculatePieceSize();
		board.addObserver(this);
		}
		this.refreshView();
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

		int[] pos = getPiecePositionFromCoordinates(x, y);

		if(SwingUtilities.isLeftMouseButton(e))
			mouseEvent.onLeftMouseButtonPressed(pos[0], pos[1]);
		else if(SwingUtilities.isRightMouseButton(e))
			mouseEvent.onRightMouseButtonPressed(pos[0], pos[1]);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void update(Observable o, Object arg) {
		refreshView();
		System.out.println("coucou!!");
	}
}
