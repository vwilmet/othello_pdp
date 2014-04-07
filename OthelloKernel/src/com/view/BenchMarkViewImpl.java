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

/**
 * Classe gérant l'affichage de la vue de BenchMark
 * @author <ul>
 *         <li>Vincent Wilmet</li>
 *         </ul>
 * @version 1.0
 */
public class BenchMarkViewImpl extends JFrame implements BenchMarkView{
	
	/**
	 * La progresse bar permettant d'afficher à l'utilisateur l'avancement du calcul
	 */
	private static JProgressBar progressBar;
	/**
	 * Bouton ok, activé lorsque le BenchMark est terminé
	 */
	private static JButton okButton;
	/**
	 * Objet BenchMark qui gère les calculs
	 */
	private static BenchMark benchmark;
	/**
	 * Variable qui va contenir le résultat du BenchMark
	 */
	private static BenchMarkResult result;
	/**
	 * Panel qui va contenir les composants de la vue
	 */
	private static JPanel panel;
	/**
	 * Interface qui permet de récupérer l'avancement du calcul du BenchMark
	 */
	private BenchMarkViewButtonEventListener event;
	
	/**
	 * Constructeur de la vue qui initialise les différents objets
	 */
	public BenchMarkViewImpl() {
		
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(TextManager.BENCHMARK_VIEW_TITLE_FR);
		
		initComponent();
		pack();
	}
	
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.BenchMarkView#setButtonListener}
	 */
	@Override
	public void setButtonListener(BenchMarkViewButtonEventListener event){
		this.event = event;
	}
	
	/**
	 * Méthode initialisation les composants de la vue
	 */
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
	
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.BenchMarkView#launchBenchMark}
	 */
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
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.BenchMarkView#showFrame}
	 */
	@Override
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * <b>Attention : </b>Cette classe ne doit pas être utilisée !
	 * <br/>Utiliser l'interface {@link com.view.interfaces.GameView} pour stocker l'objet de la classe
	 * <br/>Voir {@link com.view.interfaces.BenchMarkView#hideFrame}
	 */
	@Override
	public void hideFrame() {
		this.dispose();
	}
}
