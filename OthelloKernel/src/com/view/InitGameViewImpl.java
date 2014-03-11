package com.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.TextManager;

import com.model.view.ViewSettings;
import com.view.event.InitGameButtonEventListener;
import com.view.interfaces.InitGameView;

public class InitGameViewImpl extends JFrame implements InitGameView{

	private InitGameButtonEventListener event;
	private JButton valid, cancel, benchmark;
	private JTextField player1, player2;
	private JFormattedTextField row, ligne;
	private JFormattedTextField AItime;
	private JComboBox<String> AIDifficulty;
	private JPanel gridPanel, AIPanel, playerPanel;

	public InitGameViewImpl() {

		this.setSize(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.CHOOSE_BOARD_FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(TextManager.INITGAME_VIEW_TITLE_FR);

		instantiation();

		setComponentSize();

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		this.add(gridPanel);
		this.add(AIPanel);
		this.add(playerPanel);

		JPanel buttonPanel = new JPanel();

		buttonPanel.add(valid);
		buttonPanel.add(cancel);

		this.add(buttonPanel);

		this.pack();
	}

	@Override
	public void setButtonListener(InitGameButtonEventListener event) {
		this.event = event;
	}

	@Override
	public void showFrame(){
		this.setVisible(true);
	}

	@Override
	public void hideFrame(){
		this.dispose();
	}

	private void setComponentSize() {
		player1.setPreferredSize(new Dimension(100, 20));
		player2.setPreferredSize(new Dimension(100, 20));
		row.setPreferredSize(new Dimension(50, 20));
		ligne.setPreferredSize(new Dimension(50, 20));
		AItime.setPreferredSize(new Dimension(50, 20));
	}

	private void instantiation() {
		valid = new JButton(TextManager.INIT_GAMEVIEW_VALID_BUTTON_FR);
		cancel = new JButton(TextManager.INIT_GAMEVIEW_CANCEL_BUTTON_FR);
		benchmark = new JButton(TextManager.INIT_GAMEVIEW_BENCHMARK_BUTTON_FR);
		benchmark.setToolTipText(TextManager.INIT_GAMEVIEW_BENCHMARK_TITLE_BUTTON_FR);

		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(event != null) 
					event.onValidButtonPressed(
							Integer.valueOf(row.getText()),
							Integer.valueOf(ligne.getText()),
							Integer.valueOf(AItime.getText()),
							(String)AIDifficulty.getSelectedItem(),
							player1.getText(), 
							player2.getText());
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(event != null) event.onCancelButtonPressed();
			}
		});

		benchmark.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(event != null) event.onBenchMarkButtonPressed(AItime);
			}
		});

		player1 = new JTextField(TextManager.DEFAULT_PLAYER1_NAME_FR);
		player2 = new JTextField(TextManager.DEFAULT_PLAYER2_NAME_FR);

		row = new JFormattedTextField(NumberFormat.getNumberInstance());
		row.setValue(ViewSettings.DEFAULT_ROW_SIZE);
		ligne = new JFormattedTextField(NumberFormat.getNumberInstance());
		ligne.setValue(ViewSettings.DEFAULT_LIGNE_SIZE);

		AItime = new JFormattedTextField(NumberFormat.getNumberInstance());
		AItime.setValue(ViewSettings.DEFAULT_IA_THINKING_TIME);

		AIDifficulty = new JComboBox<String>();

		for(int i = 0; i < TextManager.AI_DIFFICULTY_VALUE_TEXT_FR.length; i++)
			AIDifficulty.addItem(TextManager.AI_DIFFICULTY_VALUE_TEXT_FR[i]);

		gridPanel = new JPanel();
		AIPanel = new JPanel();
		playerPanel = new JPanel();

		//----------------------Grid----------------------------

		JPanel gridPanelContent1 = new JPanel();
		JPanel gridPanelContent2 = new JPanel();

		gridPanelContent1.add(new JLabel(TextManager.GRID_ROW_LABEL_TEXT_FR));
		gridPanelContent1.add(row);

		gridPanelContent2.add(new JLabel(TextManager.GRID_LIGNE_LABEL_TEXT_FR));
		gridPanelContent2.add(ligne);

		gridPanel.setBorder(BorderFactory.createTitledBorder(TextManager.GRID_SIZE_LABEL_TEXT_FR));

		gridPanel.add(gridPanelContent1);
		gridPanel.add(gridPanelContent2);

		//----------------------AI----------------------------

		JPanel AIPanelContent1 = new JPanel();
		JPanel AIPanelContent2 = new JPanel();

		AIPanelContent1.add(new JLabel(TextManager.AI_THINKING_TIME_LABEL_TEXT_FR));
		AIPanelContent1.add(AItime);
		AIPanelContent1.add(benchmark);

		AIPanelContent2.add(new JLabel(TextManager.AI_DIFFICULTY_LABEL_TEXT_FR));
		AIPanelContent2.add(AIDifficulty);

		AIPanel.setBorder(BorderFactory.createTitledBorder(TextManager.AI_LABEL_TEXT_FR));

		AIPanel.add(AIPanelContent1);
		AIPanel.add(AIPanelContent2);

		//---------------------Player-----------------------------

		JPanel playerPanelContent1 = new JPanel();
		JPanel playerPanelContent2 = new JPanel();

		playerPanelContent1.add(new JLabel(TextManager.PLAYER_1_NAME_TEXT_FR));
		playerPanelContent1.add(player1);

		playerPanelContent2.add(new JLabel(TextManager.PLAYER_2_NAME_TEXT_FR));
		playerPanelContent2.add(player2);

		playerPanel.setBorder(BorderFactory.createTitledBorder(TextManager.PLAYER_LABEL_TEXT_FR));

		playerPanel.add(playerPanelContent1);
		playerPanel.add(playerPanelContent2);
	}
}
