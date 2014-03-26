package com.view.event;

import javax.swing.JFormattedTextField;

public interface InitGameButtonEventListener {
	public void onValidButtonPressed(int row, int ligne, int IATime, int HelpIADifficulty,
			String player1Name, boolean isPlayer1AI, int iaPlayer1Difficulty, 
			String player2Name, boolean isPlayer2AI, int iaPlayer2Difficulty);
	public void onCancelButtonPressed();
	public void onBenchMarkButtonPressed(JFormattedTextField AITime);
}