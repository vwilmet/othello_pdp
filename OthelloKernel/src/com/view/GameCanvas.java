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

import utils.TextManager;

import com.error_manager.Log;
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
	private boolean onPause, showPlayablePiece;
	private Piece IAAdvisedPiece;
	private Image blackPieceimg;
	private Image whitePieceimg;
	private Image possiblePieceimg;
	private Image advisedIAPieceimg;

	public GameCanvas(int canvasWidth, int canvasHeight){
		this.setBackground (Color.white);
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.onPause = false;
		this.IAAdvisedPiece = null;
		this.showPlayablePiece(true);

		try {
			whitePieceimg = ImageIO.read(new File(ViewSettings.IMAGE_PIECE_PATH + ViewSettings.WHITE_PIECE_IMG));
			possiblePieceimg = ImageIO.read(new File(ViewSettings.IMAGE_PIECE_PATH + ViewSettings.POSSIBLE_PIECE_IMG));
			blackPieceimg = ImageIO.read(new File(ViewSettings.IMAGE_PIECE_PATH + ViewSettings.BLACK_PIECE_IMG));
			advisedIAPieceimg = ImageIO.read(new File(ViewSettings.IMAGE_PIECE_PATH + ViewSettings.IA_ADVISED_PIECE_IMG));
		} catch (IOException e) {
			Log.error("Impossible d'ouvrir les images des pions!");
			e.printStackTrace();
		}
	}

	public void setIAAdvisedPiece(Piece p){
		this.IAAdvisedPiece = p;
	}

	public void setMouseListener(GameCanvasMouseEventListener event){
		this.mouseEvent = event;
		this.addMouseListener(this);
	}

	private void drawGrid(Graphics2D g){
		//draw grid
		for(int i = 0; i < board.getSizeX(); i++){
			for(int j = 0; j < board.getSizeY(); j++){
				g.drawRect(i*this.pieceSizeWidth+margin.width, j*this.pieceSizeHeight+margin.height, this.pieceSizeWidth, this.pieceSizeHeight);

				Image img;
				Piece p = board.getBoard()[i][j];
				if(p.getColor() instanceof WhitePiece)
					img = this.whitePieceimg;
				else if (p.getColor() instanceof BlackPiece)
					img = this.blackPieceimg;
				else if(p.isPlayable() && this.showPlayablePiece)
					img = this.possiblePieceimg;
				else{
					continue;
				}
				g.drawImage(
						img,
						i*this.pieceSizeWidth+margin.width+ ViewSettings.DRAW_LINE_SIZE/2,
						j*this.pieceSizeHeight+margin.height+ ViewSettings.DRAW_LINE_SIZE/2,
						this);
			}
		}

		if(this.IAAdvisedPiece != null){
			g.drawImage(this.advisedIAPieceimg,
					this.IAAdvisedPiece.getPosX()*this.pieceSizeWidth+margin.width+ ViewSettings.DRAW_LINE_SIZE/2,
					this.IAAdvisedPiece.getPosY()*this.pieceSizeHeight+margin.height+ ViewSettings.DRAW_LINE_SIZE/2,
					this);
		}
	}

	private void drawPauseScreen(Graphics2D g){
		g.drawString(TextManager.PAUSE_TEXT_VUE, gridSize.width/2, gridSize.height/2);
	}

	public void setOnPause(boolean onPause){
		this.onPause = onPause;
		refreshView();
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(ViewSettings.DRAW_LINE_SIZE));

		if(onPause) drawPauseScreen(g2);
		else if(board != null) drawGrid(g2);
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
		
		advisedIAPieceimg = scaleImage(
				advisedIAPieceimg,
				this.pieceSizeWidth-ViewSettings.DRAW_LINE_SIZE,
				this.pieceSizeHeight-ViewSettings.DRAW_LINE_SIZE
				);
		
		blackPieceimg = scaleImage(
				this.blackPieceimg,
				this.pieceSizeWidth,
				this.pieceSizeHeight
				);
		
		whitePieceimg = scaleImage(
				this.whitePieceimg,
				this.pieceSizeWidth,
				this.pieceSizeHeight
				);
		
		possiblePieceimg = scaleImage(
				this.possiblePieceimg,
				this.pieceSizeWidth,
				this.pieceSizeHeight
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
		//System.out.println("[GameCanvas][update] refreshView");
		refreshView();
	}

	public void showPlayablePiece(boolean showPlayablePiece) {
		this.showPlayablePiece = showPlayablePiece;
	}
}
