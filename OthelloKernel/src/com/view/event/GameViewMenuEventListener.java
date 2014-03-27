package com.view.event;

public interface GameViewMenuEventListener {

	public void onNewGameItemMenuPressed();
	
	public void onSaveGameUnderItemMenuPressed();

	public void onOpenFileAndContinueItemMenuPressed();
	
	public void onOpenFileAndChoosePositionItemMenuPressed();
	
	public void onOptionItemMenuPressed();
	
	public void onHelpItemMenuPressed();

	public void onConfigureBoardItemMenuPressed();

	public void onSaveHistoryPositionItemMenuPressed();
}