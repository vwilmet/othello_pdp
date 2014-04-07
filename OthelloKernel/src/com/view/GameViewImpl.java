package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import utils.TextManager;

import com.model.BoardObservable;
import com.model.piece.Piece;
import com.model.view.ViewSettings;
import com.view.button.BackButton;
import com.view.button.ForwardButton;
import com.view.button.HelpIAButton;
import com.view.button.PlayButton;
import com.view.button.PositionButton;
import com.view.button.ResetButton;
import com.view.button.ReversePlayerButton;
import com.view.event.ButtonImageMenuEventListener;
import com.view.event.GameViewMenuEventListener;
import com.view.event.GameCanvasMouseEventListener;
import com.view.interfaces.GameView;

/**
 * Classe gérant l'affichage de la vue principale du jeu
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class GameViewImpl extends JFrame implements GameView{

	/**
	 * Interfaces de communication de la vue sur les différents évènements des menus
	 */
	private ButtonImageMenuEventListener buttonEvent;
	private GameViewMenuEventListener menuEvent;

	/**
	 * Objets du menu générale du logiciel
	 */
	private JMenu option;
	private JMenu help;
	private JMenuBar menuBar;
	private JToolBar actionBar;
	private JToolBar messageBar;
	private JToolBar informationBar;
	private JMenu menu;
	private JMenuItem newGame;
	private JMenuItem configureBoard;
	private JMenuItem savePositionHistory;
	private JMenu openFile;
	private JMenuItem saveGame;
	private JMenuItem continueGame;
	private JMenuItem choosePosition;

	/**
	 * Objets permettant d'afficher à l'utilisateur les différents messages concernant l'état du jeu
	 */
	private JLabel messageLabel;
	private JLabel statLabel;
	/**
	 * Objet permettant de gérer la liste d'évènements
	 */
	private JList<String> messageList;
	private DefaultListModel<String> messageListModel;

	/**
	 * Bouton du menu du jeu
	 */
	private PlayButton playButton;
	private ResetButton resetButton;
	private BackButton backButton;
	private ForwardButton forwardButton;
	private ReversePlayerButton reverseButton;
	private HelpIAButton helpButton;
	private PositionButton positionButton;

	/**
	 * Objet de la vue qui gère l'affichage de l'othellier
	 */
	private GameCanvas game;

	/**
	 * Constructeur qui initialise tous les composants de la vue
	 * @param board L'othellier a afficher
	 */
	public GameViewImpl(BoardObservable board) {

		this.setSize(ViewSettings.GAME_FRAME_WIDTH, ViewSettings.GAME_FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(TextManager.GAME_VIEW_TITLE_FR);

		instantiation();

		this.game.setData(board);

		informationBar.setBorder(BorderFactory.createMatteBorder(
				0, 1, 0, 0, Color.black));
		messageBar.setBorder(BorderFactory.createMatteBorder(
				1, 0, 0, 0, Color.black));

		statLabel.setBorder(BorderFactory.createMatteBorder(
				0, 1, 0, 0, Color.black));


		manageMenu();

		addImageButtonToMenu();

		this.setJMenuBar(menuBar);

		messageBar.add(messageLabel, BorderLayout.WEST);
		messageBar.add(statLabel, BorderLayout.EAST);

		//informationBar.add(messageList);

		setComponentSize();

		this.add(actionBar, BorderLayout.NORTH);

		this.add(messageBar, BorderLayout.SOUTH);

		this.add(game, BorderLayout.CENTER);
		this.add(informationBar, BorderLayout.EAST);
	}

	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#showFrame}
	 */
	@Override
	public void showFrame(){
		this.setVisible(true);
	}

	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#setOnPause}
	 */
	@Override
	public void setOnPause(boolean onPause){
		this.game.setOnPause(onPause);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#setGameMouseEventListener}
	 */
	@Override
	public void setGameMouseEventListener(GameCanvasMouseEventListener mouseEvent){
		this.game.setMouseListener(mouseEvent);
	}

	/**
	 * Méthode qui va permettre de donner aux composants leurs tailles
	 */
	private void setComponentSize(){

		messageBar.setPreferredSize(new Dimension(ViewSettings.MESSAGE_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));
		messageBar.setMinimumSize(new Dimension(ViewSettings.MESSAGE_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));
		messageBar.setMaximumSize(new Dimension(ViewSettings.MESSAGE_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));

		informationBar.setPreferredSize(new Dimension(ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH, ViewSettings.INFORMATION_COMPONENT_VIEW_HEIGHT));
		informationBar.setMinimumSize(new Dimension(ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH, ViewSettings.INFORMATION_COMPONENT_VIEW_HEIGHT));
		informationBar.setMaximumSize(new Dimension(ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH, ViewSettings.INFORMATION_COMPONENT_VIEW_HEIGHT));

		messageLabel.setPreferredSize(new Dimension(ViewSettings.GAMEVIEW_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));
		messageLabel.setMinimumSize(new Dimension(ViewSettings.GAMEVIEW_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));
		messageLabel.setMaximumSize(new Dimension(ViewSettings.GAMEVIEW_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));

		statLabel.setPreferredSize(new Dimension(ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));
		statLabel.setMinimumSize(new Dimension(ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));
		statLabel.setMaximumSize(new Dimension(ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH, ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT));
	}

	/**
	 * Cette méthode initialise les différents composants de la vue
	 */
	private void instantiation(){
		this.menuBar = new JMenuBar();
		this.actionBar = new JToolBar();
		this.messageBar = new JToolBar();
		this.informationBar = new JToolBar();
		this.messageLabel = new JLabel("Initial Post");
		this.statLabel = new JLabel("Initial Post");
		this.messageListModel = new DefaultListModel<String>();
		this.messageList = new JList<String>(messageListModel);

		JScrollPane listScroller = new JScrollPane(messageList);
		listScroller.setAlignmentX(RIGHT_ALIGNMENT);

		informationBar.add(listScroller);

		informationBar.setFloatable(false);
		messageBar.setFloatable(false);
		actionBar.setFloatable(false);

		// menu part
		this.menu = new JMenu(TextManager.MENU_TEXT_FR);
		this.newGame = new JMenuItem(TextManager.NEW_GAME_TEXT_FR);

		this.savePositionHistory = new JMenuItem(TextManager.SAVE_POSITON_TEXT_FR);
		this.configureBoard = new JMenuItem(TextManager.CONFIGURE_BOARD_FR);

		this.openFile = new JMenu(TextManager.OPEN_FILE_TEXT_FR);
		this.continueGame = new JMenuItem(TextManager.CONTINUE_GAME_TEXT_FR);
		this.choosePosition = new JMenuItem(TextManager.CHOOSE_POS_TEXT_FR);
		this.saveGame = new JMenuItem(TextManager.SAVE_GAME_TEXT_FR);

		//option part
		this.option = new JMenu(TextManager.OPTION_TEXT_FR);

		//help part
		this.help = new JMenu(TextManager.HELP_TEXT_FR);

		this.game = new GameCanvas(ViewSettings.GAMEVIEW_COMPONENT_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_VIEW_HEIGHT);
	}
	/**
	 * Méthode ajoutant le paramètre dans la liste des évènements
	 * @param element Le string à ajouter
	 */
	private void addStringToInformationList(String element){
		messageListModel.addElement(element);
		messageList.setModel(messageListModel);
		messageList.setSelectedIndex(messageList.getModel().getSize()-1);
		messageList.ensureIndexIsVisible(messageList.getSelectedIndex());
	}
	/**
	 * Méthode qui permet de gérer le menu
	 */
	private void manageMenu(){
		this.menu.add(newGame);
		this.menu.add(configureBoard);

		this.openFile.add(continueGame);
		this.openFile.add(choosePosition);
		this.menu.add(openFile);

		this.menu.add(savePositionHistory);
		this.menu.add(saveGame);

		this.menuBar.add(menu);
		this.menuBar.add(option);
		this.menuBar.add(help);

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuEvent != null)menuEvent.onNewGameItemMenuPressed();
			}
		});

		savePositionHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuEvent != null)menuEvent.onSaveHistoryPositionItemMenuPressed();
			}
		});

		configureBoard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuEvent != null)menuEvent.onConfigureBoardItemMenuPressed();
			}
		});

		saveGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuEvent != null)menuEvent.onSaveGameUnderItemMenuPressed();
			}
		});

		continueGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuEvent != null)menuEvent.onOpenFileAndContinueItemMenuPressed();
			}
		});

		choosePosition.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuEvent != null)menuEvent.onOpenFileAndChoosePositionItemMenuPressed();
			}
		});

		option.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent arg0) {
				if(menuEvent != null)menuEvent.onOptionItemMenuPressed();
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
			}

			@Override
			public void menuCanceled(MenuEvent arg0) {
			}
		});

		help.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent arg0) {
				if(menuEvent != null)menuEvent.onHelpItemMenuPressed();
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {}

			@Override
			public void menuCanceled(MenuEvent arg0) {}

		});
	}
	/**
	 * Méthode ajoutant les boutons du menu du jeu à l'interface utilisateur
	 */
	private void addImageButtonToMenu(){

		playButton = new PlayButton();
		resetButton = new ResetButton();
		backButton = new BackButton();
		forwardButton = new ForwardButton();
		reverseButton = new ReversePlayerButton();
		helpButton = new HelpIAButton();	
		positionButton = new PositionButton();


		actionBar.add(playButton);
		actionBar.add(resetButton);
		actionBar.add(backButton);
		actionBar.add(forwardButton);
		actionBar.add(reverseButton);
		actionBar.add(helpButton);
		actionBar.add(positionButton);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#addMessageToMessageList}
	 */
	@Override
	public void addMessageToMessageList(String element) {
		this.addStringToInformationList(element);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#changeStatViewMessage}
	 */
	@Override
	public void changeStatViewMessage(String message) {
		statLabel.setText(message);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#changeMessageViewContent}
	 */
	@Override
	public void changeMessageViewContent(String content) {
		messageLabel.setText(content);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#setMenuListener}
	 */
	@Override
	public void setMenuListener(GameViewMenuEventListener event) {
		this.menuEvent = event;
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#hideFrame}
	 */
	@Override
	public void hideFrame() {
		this.dispose();
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#setBoard}
	 */
	@Override
	public void setBoard(BoardObservable board) {
		this.game.setData(board);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#setIAAdvisedPiece}
	 */
	@Override
	public void setIAAdvisedPiece(Piece p) {
		this.game.setIAAdvisedPiece(p);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#setImageButtonEventListener}
	 */
	@Override
	public void setImageButtonEventListener(ButtonImageMenuEventListener event) {
		this.buttonEvent = event;

		playButton.setMouseListener(buttonEvent);
		resetButton.setMouseListener(buttonEvent);
		backButton.setMouseListener(buttonEvent);
		forwardButton.setMouseListener(buttonEvent);
		reverseButton.setMouseListener(buttonEvent);
		helpButton.setMouseListener(buttonEvent);
		positionButton.setMouseListener(buttonEvent);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#refresh}
	 */
	@Override
	public void refresh() {
		this.game.refreshView();
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#enableBackButton}
	 */
	@Override
	public void enableBackButton(boolean enable) {
		this.backButton.enableButton(enable);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#enableForwardButton}
	 */
	@Override
	public void enableForwardButton(boolean enable) {
		this.forwardButton.enableButton(enable);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#enableResetButton}
	 */
	@Override
	public void enableResetButton(boolean enable) {
		this.resetButton.enableButton(enable);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.GameView#resetMessageListContent}
	 */
	@Override
	public void resetMessageListContent() {
		messageListModel.clear();
		messageList.setModel(messageListModel);
	}
}
