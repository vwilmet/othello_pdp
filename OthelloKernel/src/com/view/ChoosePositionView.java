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


public class ChoosePositionView extends JFrame{

	private int currentChoosenPosition = 0;
	private JSlider slider;
	private ArrayList<Board> boards;
	private JButton valid, cancel;
	private JPanel buttonPanel;
	private GameCanvas game;
	private ChoosePositionButtonEventListener buttonEvent;

	public ChoosePositionView(int currentBoard, ArrayList<Board> boards) {

		this.setSize(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.CHOOSE_BOARD_FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		this.setVisible(true);
	}

	public void setButtonListener(ChoosePositionButtonEventListener event){
		this.buttonEvent = event;
	}
	
	public void showFrame(){
		this.setVisible(true);
	}
	
	public void hideFrame(){
		this.dispose();
	}
	
	private void setComponentSize() {
		slider.setPreferredSize(new Dimension(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT));
		slider.setMinimumSize(new Dimension(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT));
		slider.setMaximumSize(new Dimension(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT));

		buttonPanel.setPreferredSize(new Dimension(ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT));
		buttonPanel.setMinimumSize(new Dimension(ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT));
		buttonPanel.setMaximumSize(new Dimension(ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT));
	}

	private void instantiation() {
		slider = new JSlider(JSlider.HORIZONTAL,
				0, boards.size()-1, this.currentChoosenPosition);

		game = new GameCanvas((BoardObservable)boards.get(this.currentChoosenPosition), ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_WIDTH, ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT);

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
					System.out.println("Position slider : " + currentChoosenPosition);
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
					buttonEvent.onValidButtonPressed(boards.get(currentChoosenPosition));
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
}
