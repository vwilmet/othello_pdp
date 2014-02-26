package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import utils.TextManager;
import utils.ViewSettings;

import com.view.button.BackButton;
import com.view.button.ButtonEventListener;
import com.view.button.ForwardButton;
import com.view.button.HelpIAButton;
import com.view.button.ImageButton;
import com.view.button.PlayButton;
import com.view.button.PositionButton;
import com.view.button.ResetButton;
import com.view.button.ReversePlayerButton;

public class GameView extends JFrame {

	private JMenuBar menuBar;
	private JToolBar actionBar;
	private JToolBar messageBar;
	private JToolBar informationBar;

	//Menu part 
	private JMenu menu;
	private JMenuItem newGame;
	private JMenu openFile;
	private JMenuItem saveGame;
	private JMenuItem continueGame;
	private JMenuItem choosePosition;
	private JMenuItem preConfFile;
	
	private JLabel messageLabel;
	private JLabel informationLabel;
	
	//Option part
	private JMenu option;
	
	//Help part
	private JMenu help;
	
	public GameView() {
		this.setSize(ViewSettings.sizeX, ViewSettings.sizeY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.menuBar = new JMenuBar();
		this.actionBar = new JToolBar();
		this.messageBar = new JToolBar();
		this.informationBar = new JToolBar();
		this.messageLabel = new JLabel();
		this.informationLabel = new JLabel();
		
		informationBar.setFloatable(false);
		messageBar.setFloatable(false);
		actionBar.setFloatable(false);
		
		informationBar.setBorder(BorderFactory.createMatteBorder(
                0, 1, 0, 0, Color.black));
		messageBar.setBorder(BorderFactory.createMatteBorder(
                1, 0, 0, 0, Color.black));
		
		//menu part
		this.menu = new JMenu(TextManager.MENU_TEXT_FR);
		this.newGame = new JMenuItem(TextManager.NEW_GAME_TEXT_FR);
		this.openFile = new JMenu(TextManager.OPEN_FILE_TEXT_FR);
		this.continueGame = new JMenuItem(TextManager.CONTINUE_GAME_TEXT_FR);
		this.choosePosition = new JMenuItem(TextManager.CHOOSE_POS_TEXT_FR);
		this.preConfFile = new JMenuItem(TextManager.PRE_CONF_FILE_TEXT_FR);
		this.saveGame = new JMenuItem(TextManager.SAVE_GAME_TEXT_FR);
		
		this.menu.add(newGame);
		this.openFile.add(continueGame);
		this.openFile.add(choosePosition);
		this.openFile.add(preConfFile);
		this.menu.add(openFile);
		this.menu.add(saveGame);

		//option part
		this.option = new JMenu(TextManager.OPTION_TEXT_FR);

		//help part
		this.help = new JMenu(TextManager.HELP_TEXT_FR);

		this.menuBar.add(menu);
		this.menuBar.add(option);
		this.menuBar.add(help);

		//////////////////////////////////////////////////
		// TODO
		help.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent arg0) {
				try {
					java.awt.Desktop.getDesktop().browse(new URI("www.google.fr"));
				} catch (IOException  e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}	
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {}

			@Override
			public void menuCanceled(MenuEvent arg0) {}
		});
		//////////////////////////////////////////////////

		ButtonEventListener event = new ButtonEventListener() {

			@Override
			public void onPlayButtonCliked() {
				System.out.println("play!");
			}

			@Override
			public void onPauseButtonCliked() {
				System.out.println("Pause");
			}

			@Override
			public void onButtonCliked(ImageButton button, int code) {
				System.out.println("Button cliked : " + code);
			}

			@Override
			public void onForwardButtonCliked() {
				System.out.println("forward");
			}

			@Override
			public void onBackButtonCliked() {
				System.out.println("back");
			}

			@Override
			public void onResetButtonCliked() {
				System.out.println("reset");
			}

			@Override
			public void onHelpIAButtonCliked() {
				System.out.println("help ia");				
			}

			@Override
			public void onPositionButtonCliked() {
				System.out.println("position");
			}

			@Override
			public void onReversePlayerButtonCliked() {
				System.out.println("reverse");
			}
		};

		actionBar.add(new PlayButton(event));
		actionBar.add(new ResetButton(event));
		actionBar.add(new BackButton(event));
		actionBar.add(new ForwardButton(event));
		actionBar.add(new HelpIAButton(event));
		actionBar.add(new PositionButton(event));
		actionBar.add(new ReversePlayerButton(event));
		
		this.setJMenuBar(menuBar);
		messageBar.add(new JTextArea("Textfiled ou il y aura des messages variables"));
		informationBar.add(new JTextArea("Information sur la partie en cours !!!"));
		
		this.add(actionBar, BorderLayout.NORTH);
		this.add(messageBar, BorderLayout.SOUTH);
		
		this.add(new GameCanvas(), BorderLayout.CENTER);
		this.add(informationBar, BorderLayout.EAST);
		
		this.setVisible(true);
	}
}
