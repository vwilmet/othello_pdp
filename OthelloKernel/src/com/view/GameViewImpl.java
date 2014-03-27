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
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class GameViewImpl extends JFrame implements GameView{

	private ButtonImageMenuEventListener buttonEvent;
	private GameViewMenuEventListener menuEvent;

	private JMenuBar menuBar;
	private JToolBar actionBar;
	private JToolBar messageBar;
	private JToolBar informationBar;

	// Menu part
	private JMenu menu;
	private JMenuItem newGame;
	private JMenuItem configureBoard;
	private JMenuItem savePositionHistory;
	private JMenu openFile;
	private JMenuItem saveGame;
	private JMenuItem continueGame;
	private JMenuItem choosePosition;
	private JMenuItem preConfFile;

	private JLabel messageLabel;
	private JLabel statLabel;

	private JList<String> messageList;
	private DefaultListModel<String> messageListModel;

	//Option part
	private JMenu option;

	//Help part
	private JMenu help;

	private PlayButton playButton;
	private ResetButton resetButton;
	private BackButton backButton;
	private ForwardButton forwardButton;
	private ReversePlayerButton reverseButton;
	private HelpIAButton helpButton;
	private PositionButton positionButton;

	private GameCanvas game;

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

	@Override
	public void showFrame(){
		this.setVisible(true);
	}

	@Override
	public void setOnPause(boolean onPause){
		this.game.setOnPause(onPause);
	}

	@Override
	public void setGameMouseEventListener(GameCanvasMouseEventListener mouseEvent){
		this.game.setMouseListener(mouseEvent);
	}

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

	private void instantiation(){
		this.menuBar = new JMenuBar();
		this.actionBar = new JToolBar();
		this.messageBar = new JToolBar();
		this.informationBar = new JToolBar();
		this.messageLabel = new JLabel("Textfiled ou il y aura des messages variables");
		this.statLabel = new JLabel("stat quelconque");
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
		this.preConfFile = new JMenuItem(TextManager.PRE_CONF_FILE_TEXT_FR);
		this.saveGame = new JMenuItem(TextManager.SAVE_GAME_TEXT_FR);

		//option part
		this.option = new JMenu(TextManager.OPTION_TEXT_FR);

		//help part
		this.help = new JMenu(TextManager.HELP_TEXT_FR);

		this.game = new GameCanvas(ViewSettings.GAMEVIEW_COMPONENT_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_VIEW_HEIGHT);
	}

	private void addStringToInformationList(String element){
		messageListModel.addElement(element);
		messageList.setModel(messageListModel);
		messageList.setSelectedIndex(messageList.getModel().getSize()-1);
		messageList.ensureIndexIsVisible(messageList.getSelectedIndex());
	}

	private void manageMenu(){
		this.menu.add(newGame);
		this.menu.add(configureBoard);
		
		this.openFile.add(continueGame);
		this.openFile.add(choosePosition);
		this.openFile.add(preConfFile);
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

		preConfFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(menuEvent != null)menuEvent.onOpenPreConfFileItemMenuPressed();
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

	
	
	@Override
	public void addMessageToMessageList(String element) {
		this.addStringToInformationList(element);
	}

	@Override
	public void changeStatViewMessage(String message) {
		statLabel.setText(message);
	}

	@Override
	public void changeMessageViewContent(String content) {
		messageLabel.setText(content);
	}

	@Override
	public void setMenuListener(GameViewMenuEventListener event) {
		this.menuEvent = event;
	}

	@Override
	public void hideFrame() {
		this.dispose();
	}

	@Override
	public void setBoard(BoardObservable board) {
		this.game.setData(board);
	}

	@Override
	public void setIAAdvisedPiece(Piece p) {
		this.game.setIAAdvisedPiece(p);
	}

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

	@Override
	public void refresh() {
		this.game.refreshView();
	}

	@Override
	public void enableBackButton(boolean enable) {
		this.backButton.enableButton(enable);
	}

	@Override
	public void enableForwardButton(boolean enable) {
		this.forwardButton.enableButton(enable);
	}

	@Override
	public void enableResetButton(boolean enable) {
		this.resetButton.enableButton(enable);
	}
}
