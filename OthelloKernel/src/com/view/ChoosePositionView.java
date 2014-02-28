package com.view;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.model.view.ViewSettings;


public class ChoosePositionView extends JFrame implements ChangeListener{

	private int currentChoosenPosition = 0;
	
	public ChoosePositionView(int min, int max, int current) {

		this.setSize(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH, ViewSettings.CHOOSE_BOARD_FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.currentChoosenPosition = current;
		
		JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
				min, max, current);

		framesPerSecond.addChangeListener(this);
		framesPerSecond.setMajorTickSpacing(10);
		framesPerSecond.setMinorTickSpacing(1);
		framesPerSecond.setPaintTicks(true);
		framesPerSecond.setPaintLabels(true);

		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			this.currentChoosenPosition = (int)source.getValue();
			System.out.println("Position slider : " + currentChoosenPosition);
		}
	}

}
