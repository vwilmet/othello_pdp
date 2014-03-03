package com.view.event;

import javax.swing.JFormattedTextField;

public interface InitGameButtonEventListener {
	public void onValidButtonPressed(int row, int ligne, int IATime, String IADifficulty, String player1Name, String player2Name);
	public void onCancelButtonPressed();
	public void onBenchMarkButtonPressed(JFormattedTextField AITime);
}
