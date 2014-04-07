package com.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utils.TextManager;

import com.model.Board;
import com.model.BoardObservable;
import com.model.view.ViewSettings;
import com.view.event.ChoosePositionButtonEventListener;
import com.view.interfaces.ChoosePositionView;

/**
 * Classe gérant l'affichage de la vue permettant le choix de la position
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class ChoosePositionViewImpl extends JFrame implements ChoosePositionView{

	/**
	 * Entier contenant la position actuel de l'othellier isuel accessible
	 */
	private int currentChoosenPosition = 0;
	/**
	 * Objet de l'interface qui est borné par les limites de l'historique
	 */
	private JSlider slider;
	/**
	 * ArrayList contenant toutes les boards du jeu
	 */
	private ArrayList<BoardObservable> boards;
	/**
	 * Boutons permettant de valider ou d'annuler le choix de position
	 */
	private JButton valid, cancel;
	/**
	 * Panel qui va contenir les composants de la vue
	 */
	private JPanel buttonPanel;
	/**
	 * Objet de la vue qui gère l'affichage de l'othellier
	 */
	private GameCanvas game;
	/**
	 * Interface de communication de la vue sur les actions des boutons
	 */
	private ChoosePositionButtonEventListener buttonEvent;

	/**
	 * Constructeur qui initialise tous les composants de la vue
	 * @param currentBoard Entier correspondant à la position dans l'historique de l'othellier actuel
	 * @param boards ArrayList contenant toutes les boards jouées depuis le début de la partie
	 */
	public ChoosePositionViewImpl(int currentBoard, ArrayList<BoardObservable> boards) {

		this.setSize(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.CHOOSE_BOARD_FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(TextManager.CHOOSE_VIEW_TITLE_FR);

		this.currentChoosenPosition = currentBoard;
		this.boards = boards;

		instantiation();

		setComponentSize();

		buttonPanel.add(valid, BorderLayout.NORTH);
		buttonPanel.add(cancel, BorderLayout.SOUTH);

		if(boards.size()>1)
			this.add(slider, BorderLayout.NORTH);

		this.add(game, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.EAST);
	}
	/**
	 * Méthode qui initialise l'interface de communication pour les buttons de la vue
	 * @param event L'object interface qui sera appelé lors des cliques sur les boutons de la vue
	 */
	@Override
	public void setButtonListener(ChoosePositionButtonEventListener event){
		this.buttonEvent = event;
	}
	/**
	 * Méthode permettant d'afficher la vue
	 */
	@Override
	public void showFrame(){
		this.setVisible(true);
	}
	/**
	 * Méthode permettant de cacher la vue
	 */
	@Override
	public void hideFrame(){
		this.dispose();
	}
	/**
	 * Méthode qui va permettre de donner aux composants leurs tailles
	 */
	private void setComponentSize() {
		slider.setPreferredSize(new Dimension(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT));
		slider.setMinimumSize(new Dimension(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT));
		slider.setMaximumSize(new Dimension(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT));

		buttonPanel.setPreferredSize(new Dimension(ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT));
		buttonPanel.setMinimumSize(new Dimension(ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT));
		buttonPanel.setMaximumSize(new Dimension(ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT));
	}
	/**
	 * Cette méthode initialise les différents composants de la vue
	 */
	private void instantiation() {
		slider = new JSlider(JSlider.HORIZONTAL,
				0, boards.size()-1, this.currentChoosenPosition);

		game = new GameCanvas(ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT);
		game.setData((BoardObservable)boards.get(this.currentChoosenPosition)); 

		slider.setMajorTickSpacing(((boards.size()-1)/10 == 0 ? 1 : (boards.size()-1)/10));
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					currentChoosenPosition = (int)source.getValue();
					game.setData((BoardObservable)boards.get(currentChoosenPosition));
					game.refreshView();
				}
			}
		});


		valid = new JButton(TextManager.CHOOSE_VIEW_VALID_BUTTON_FR);
		cancel = new JButton(TextManager.CHOOSE_VIEW_CANCEL_BUTTON_FR);

		valid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(buttonEvent != null)
					buttonEvent.onValidButtonPressed(boards.get(currentChoosenPosition), currentChoosenPosition);
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(buttonEvent != null)
					buttonEvent.onCancelButtonPressed();
			}
		});

		buttonPanel = new JPanel();


	}
	/**
	 * Méthode permettant d'autoriser l'affichage des pièces jouables sur l'othellier
	 */
	@Override
	public void showPlayablePiece(boolean visible) {
		this.game.showPlayablePiece(visible);
	}
}
