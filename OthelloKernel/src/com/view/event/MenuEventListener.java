package com.view.event;

public interface MenuEventListener {

	public void onNewGameItemMenuPressed();
	
	public void onSaveGameUnderItemMenuPressed();

	public void onOpenFileAndContinueItemMenuPressed();
	
	public void onOpenFileAndChoosePositionItemMenuPressed();
	
	public void onOpenPreConfFileItemMenuPressed();
	
	public void onOptionItemMenuPressed();
	
	public void onHelpItemMenuPressed();
}