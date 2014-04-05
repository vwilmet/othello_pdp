package com.view.interfaces;

import com.view.event.BenchMarkViewButtonEventListener;

public interface BenchMarkView {
	public void setButtonListener(BenchMarkViewButtonEventListener event);
	public void showFrame();
	public void hideFrame();
	public void launchBenchMark();
}
