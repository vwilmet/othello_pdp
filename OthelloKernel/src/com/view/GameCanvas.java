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
 * Classe implémentant la classe Java "Canvas". Celle-ci ne fait que gérer l'affichage de l'othellier sous forme de grille avec les pions.
 * Elle utilise l'interface MouseListener pou gérer les cliques
 * Elle utilise l'interface Observer afin d'être rafraichie à chaque modification de l'othellier
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class GameCanvas extends Canvas implements MouseListener, Observer{

	/**
	 * Variable contenant la dimension de l'othellier
	 */
	private Dimension gridSize;
	/**
	 * Variable contenant la taille de la marge entre la grille pour qu'elle soit plus ou moins centrée
	 */
	private Dimension margin;
	/**
	 * Interface soulevant les évenements associés au clique de la souris
	 */
	private GameCanvasMouseEventListener mouseEvent;
	/**
	 * Variable contenant la board qui va être lié en tant qu'observée par la vue
	 */
	private Board board;
	/**
	 * Les tailles de la fênetre contenant l'othellier = taille de l'othellier + des marges
	 */
	private int canvasWidth, canvasHeight;
	/**
	 * Les tailles pour les pièces dans l'othellier
	 */
	private int pieceSizeWidth, pieceSizeHeight;
	/**
	 * Booléen permettant de savoir si le jeu est courament en pause
	 */
	private boolean onPause;
	/**
	 * Booléen vérifiant si on affiche les pièces jouables
	 */
	private boolean showPlayablePiece;
	/**
	 * Pièce correspondant à celle conseillé par l'IA et sinon null
	 */
	private Piece IAAdvisedPiece;
	/**
	 * Image du pion noire
	 */
	private Image blackPieceimg;
	/**
	 * Image du pion blanc
	 */
	private Image whitePieceimg;
	/**
	 * Image d'un pion jouable
	 */
	private Image possiblePieceimg;
	/**
	 * Image du pion conseillé par l'IA
	 */
	private Image advisedIAPieceimg;

	/**
	 * Constructeur qui initialise les images.
	 * 
	 * @param canvasWidth La taille de la fenêtre en largeur
	 * @param canvasHeight La taille de la fenêtre en longueur
	 */
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

	/**
	 * Méthode définissant la pièce à jouer conseillé par l'IA
	 * @param p Pièce à afficher
	 */
	public void setIAAdvisedPiece(Piece p){
		this.IAAdvisedPiece = p;
	}

	/**
	 * Setter de l'interface permettant la gestion des évènements
	 * @param event L'interface à lier
	 */
	public void setMouseListener(GameCanvasMouseEventListener event){
		this.mouseEvent = event;
		this.addMouseListener(this);
	}

	/**
	 * Méthode désinnant l'othellier, les pièces : noire, blanche, jouable et la pièce conseillé par l'IA
	 * @param g L'object permettant de dessiner sur la zone Canvas
	 */
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

	/**
	 * Méthode affichant, quand le jeu est en pause, le texte correspondant pour informer l'utilisateur
	 * @param g L'object permettant de dessiner sur la zone Canvas
	 */
	private void drawPauseScreen(Graphics2D g){
		g.drawString(TextManager.PAUSE_TEXT_VUE, gridSize.width/2, gridSize.height/2);
	}

	/**
	 * Gère si le jeu est en pause. <br/>
	 * Cette méthode rafraichit l'interface pour voir la modification immédiatement
	 * @param onPause Booléen définissant si le jeu est en pause
	 */
	public void setOnPause(boolean onPause){
		this.onPause = onPause;
		refreshView();
	}

	/**
	 *  Méthode appelé automatiquement appartenant à la classe "Canvas" 
	 *  @param g L'objet permettant de dessiner
	 */
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(ViewSettings.DRAW_LINE_SIZE));

		if(onPause) drawPauseScreen(g2);
		else if(board != null) drawGrid(g2);
	}

	/**
	 * Méthode calculant la taille des pièce en fonction de la taille de l'othellier et de la place de la fenêtre.<br/>
	 * Cette fonction redimmensionne également les images à la taille des pièces calculée
	 */
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

	/**
	 * Cette fonction permet de redimmensionner une image selon les paramètres
	 * @param image L'image à redimensionner
	 * @param width La largeur de l'image une fois redimmensionnée
	 * @param height La longueur de l'image une fois redimmensionnée
	 * @return La nouvelle image une fois redimmensionné
	 */
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

	/**
	 * L'appel de cette fonction permet de rafraichir la vue
	 */
	public void refreshView(){
		this.repaint();
	}

	/**
	 * Cette méthode retourne les coordonnées dans la board en fonction de la position en pixel de la souris passée en paramètres
	 * @param x La position en X
	 * @param y La position en Y
	 * @return un tableau avec la coordonnées : en position [0] la position en i et en [1] la position en j
	 */
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

	/**
	 * Cette fonction permet de définir un nouveaux plateau à afficher
	 * @param board Le nouveau plateau à afficher
	 */
	public void setData(BoardObservable board){
		if(board != null){
			this.board = board;
			this.calculatePieceSize();
			board.addObserver(this);
		}
		this.refreshView();
	}

	/**
	 * Méthode soulevé selon les actions souris [non-utilisé]
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Méthode soulevé selon les actions souris [non-utilisé]
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Méthode soulevé selon les actions souris [non-utilisé]
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Fonction appelée lors du clique sur une case de l'othellier<br/>
	 * Elle soulève les évenements de l'interface {@link com.view.event.GameCanvasMouseEventListener}<br/>
	 * Ces évènements sont :
	 * 	<ul>
	 * 		<li> {@link com.view.event.GameCanvasMouseEventListener#onLeftMouseButtonPressed} pour le clique gauche avec les positions dans le plateau en paramètre</li>
	 * 		<li>{@link com.view.event.GameCanvasMouseEventListener#onRightMouseButtonPressed} pour le clique droit avec les positions dans le plateau en paramètre</li>
	 * 	</ul>
	 */
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

	/**
	 * Méthode soulevé selon les actions souris [non-utilisé]
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * Méthode soulevé grâce au pattern Observable. Elle est soulevé dès que le plateau est modifié
	 */
	@Override
	public void update(Observable o, Object arg) {
		refreshView();
	}

	/**
	 * Méthode définissant le booléen permettant d'autoriser l'affichage des pièces jouables
	 */
	public void showPlayablePiece(boolean showPlayablePiece) {
		this.showPlayablePiece = showPlayablePiece;
	}
}
