package com.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import jnt.BenchMark;
import jnt.BenchMarkResult;
import jnt.BenchMarkResultEvent;
import utils.TextManager;

import com.view.event.BenchMarkViewButtonEventListener;
import com.view.interfaces.BenchMarkView;

public class BenchMarkViewImpl extends JFrame implements BenchMarkView{
	
	private static JProgressBar progressBar;
	private static JButton okButton;
	private static BenchMark benchmark;
	private static BenchMarkResult result;
	private static JPanel panel;
	private BenchMarkViewButtonEventListener event;
	
	public BenchMarkViewImpl() {
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(TextManager.BENCHMARK_VIEW_TITLE_FR);
		
		initComponent();
		pack();
	}
	
	@Override
	public void setButtonListener(BenchMarkViewButtonEventListener event){
		this.event = event;
	}
	
	private void initComponent(){

		okButton = new JButton("Start");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(event != null) event.onOkButtonPressed(result);
			}
		});
		okButton.setEnabled(false);


		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		panel.add(progressBar);
		panel.add(okButton);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.setOpaque(true);

		add(panel, BorderLayout.PAGE_START);
	}

	@Override
	public void launchBenchMark(){
		SwingWorker sw = new SwingWorker(){
			protected Object doInBackground() throws Exception {
				benchmark = new BenchMark(new BenchMarkResultEvent() {

					@Override
					public void onStart() {
						okButton.setEnabled(false);
					}

					@Override
					public void onProgress(int arg0) {
						setProgress(arg0);
					}

					@Override
					public void onEnd(BenchMarkResult arg0) {
						result = arg0;
					}
				});               
				benchmark.launch();
				return null;
			}
			@Override
			public void done(){
				okButton.setEnabled(true);
				panel.setCursor(null);
			} 
		};

		sw.addPropertyChangeListener(new PropertyChangeListener(){
			//Méthode de l'interface
			public void propertyChange(PropertyChangeEvent evt) {
				if ("progress" == evt.getPropertyName()) {
					int progress = (Integer) evt.getNewValue();
					progressBar.setValue(progress);
				}         
			}
		});

		sw.execute();
	}

	
	@Override
	public void showFrame() {
		setVisible(true);
	}
	

	@Override
	public void hideFrame() {
		this.dispose();
	}
}
