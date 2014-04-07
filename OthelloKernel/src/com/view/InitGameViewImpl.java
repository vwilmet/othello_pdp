package com.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.TextManager;

import com.model.GameSettings;
import com.model.view.ViewSettings;
import com.view.event.InitGameButtonEventListener;
import com.view.interfaces.InitGameView;
/**
 * Classe gérant l'affichage de la vue permettant d'initialiser une partie
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class InitGameViewImpl extends JFrame implements InitGameView{

	/**
	 * Interface de communication sur les boutons
	 */
	private InitGameButtonEventListener event;
	/**
	 * Boutons de l'interface
	 */
	private JButton valid, cancel, benchmark;
	/**
	 * Objet de l'interface pour choisir, entrer et cocher les données utilisateurs
	 */
	private JTextField player1, player2;
	private JFormattedTextField row, ligne;
	private JFormattedTextField AItime;
	private JComboBox<String> helpAIDifficulty;
	private JComboBox<String> player1AIDifficulty;
	private JComboBox<String> player2AIDifficulty;
	private JCheckBox player1IsAI;
	private JCheckBox player2IsAI;
	/**
	 * Panel qui vont contenir les composants de la vue
	 */
	private JPanel gridPanel, AIPanel, playerPanel;

	/**
	 * Constructeur qui initialise tous les composants de la vue
	 */
	public InitGameViewImpl() {

		this.setSize(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.CHOOSE_BOARD_FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
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

	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.InitGameView#setButtonListener}
	 */
	@Override
	public void setButtonListener(InitGameButtonEventListener event) {
		this.event = event;
	}

	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.InitGameView#showFrame}
	 */
	@Override
	public void showFrame(){
		this.setVisible(true);
	}

	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.InitGameView#hideFrame}
	 */
	@Override
	public void hideFrame(){
		this.dispose();
	}
	/**
	 * Méthode qui va permettre de donner aux composants leurs tailles
	 */
	private void setComponentSize() {
		player1.setPreferredSize(new Dimension(100, 20));
		player2.setPreferredSize(new Dimension(100, 20));
		row.setPreferredSize(new Dimension(50, 20));
		ligne.setPreferredSize(new Dimension(50, 20));
		AItime.setPreferredSize(new Dimension(50, 20));
	}
	/**
	 * Cette méthode initialise les différents composants de la vue
	 */
	private void instantiation() {
		valid = new JButton(TextManager.INIT_GAMEVIEW_VALID_BUTTON_FR);
		cancel = new JButton(TextManager.INIT_GAMEVIEW_CANCEL_BUTTON_FR);
		benchmark = new JButton(TextManager.INIT_GAMEVIEW_BENCHMARK_BUTTON_FR);
		benchmark.setToolTipText(TextManager.INIT_GAMEVIEW_BENCHMARK_TITLE_BUTTON_FR);

		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(event != null)
					event.onValidButtonPressed(((Number) InitGameViewImpl.this.row.getValue()).intValue(), ((Number) InitGameViewImpl.this.ligne.getValue()).intValue(), ((Number) InitGameViewImpl.this.AItime.getValue()).intValue(), helpAIDifficulty.getSelectedIndex(),
							player1.getText(), player1IsAI.isSelected(), player1AIDifficulty.getSelectedIndex(), 
							player2.getText(), player2IsAI.isSelected(), player2AIDifficulty.getSelectedIndex());
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
		row.setValue(GameSettings.DEFAULT_ROW_SIZE);
		ligne = new JFormattedTextField(NumberFormat.getNumberInstance());
		ligne.setValue(GameSettings.DEFAULT_LIGNE_SIZE);

		AItime = new JFormattedTextField(NumberFormat.getNumberInstance());
		AItime.setValue(GameSettings.DEFAULT_IA_THINKING_TIME);

		helpAIDifficulty = new JComboBox<String>();
		player1AIDifficulty = new JComboBox<String>();
		player2AIDifficulty = new JComboBox<String>();
		player1AIDifficulty.setEnabled(false);
		player2AIDifficulty.setEnabled(false);
		
		player1IsAI = new JCheckBox(TextManager.IS_PLAYER_AI_LABEL_TEXT_FR);
		player2IsAI = new JCheckBox(TextManager.IS_PLAYER_AI_LABEL_TEXT_FR);
		
		player1IsAI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(player1IsAI.isSelected())
					player1AIDifficulty.setEnabled(true);
				else
					player1AIDifficulty.setEnabled(false);
			}
		});
		
		player2IsAI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(player2IsAI.isSelected())
					player2AIDifficulty.setEnabled(true);
				else
					player2AIDifficulty.setEnabled(false);
			}
		});
		
		for(int i = 0; i < TextManager.AI_DIFFICULTY_VALUE_TEXT_FR.length; i++){
			helpAIDifficulty.addItem(TextManager.AI_DIFFICULTY_VALUE_TEXT_FR[i]);
			player1AIDifficulty.addItem(TextManager.AI_DIFFICULTY_VALUE_TEXT_FR[i]);
			player2AIDifficulty.addItem(TextManager.AI_DIFFICULTY_VALUE_TEXT_FR[i]);
		}

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
		AIPanelContent2.add(helpAIDifficulty);

		AIPanel.setBorder(BorderFactory.createTitledBorder(TextManager.AI_LABEL_TEXT_FR));

		AIPanel.add(AIPanelContent1);
		AIPanel.add(AIPanelContent2);

		//---------------------Player-----------------------------

		JPanel playerPanelContent1 = new JPanel();
		JPanel playerPanelContent2 = new JPanel();
		
		playerPanelContent1.setLayout(new BoxLayout(playerPanelContent1, BoxLayout.Y_AXIS));
		playerPanelContent1.add(new JLabel(TextManager.PLAYER_1_NAME_TEXT_FR));
		playerPanelContent1.add(player1);
		playerPanelContent1.add(player1IsAI);
		playerPanelContent1.add(player1AIDifficulty);

		playerPanelContent2.setLayout(new BoxLayout(playerPanelContent2, BoxLayout.Y_AXIS));
		playerPanelContent2.add(new JLabel(TextManager.PLAYER_2_NAME_TEXT_FR));
		playerPanelContent2.add(player2);
		playerPanelContent2.add(player2IsAI);
		playerPanelContent2.add(player2AIDifficulty);

		playerPanel.setBorder(BorderFactory.createTitledBorder(TextManager.PLAYER_LABEL_TEXT_FR));

		playerPanel.add(playerPanelContent1);
		playerPanel.add(playerPanelContent2);
	}
}
