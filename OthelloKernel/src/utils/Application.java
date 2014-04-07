package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.model.view.ViewSettings;

/**
 * Classe contenant les informations de base sur la machine actuel et faisant les calculs necessaires pour les tailles des fenêtres.
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class Application {

	/**
	 * La taille du moniteur
	 */
	private Dimension screenSize;
	/**
	 * La largeur de l'écran
	 */
	private double width = 0;
	/**
	 * La longueur de l'écran
	 */
	private double height = 0;
	/**
	 * La variable qui va contenir le nombre de milli-secondes écoulé depuis le lancement du logiciel
	 */
	private long startTime = 0;
	/**
	 * Objet de cette classe qui va permetre le pattern singleton
	 */
	private static Application instance;

	/**
	 * Constructeur de la classe Application
	 * Il récupère la taille de l'écran ainsi que le nombre de milli-seconde écoulé
	 */
	private Application() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.height = screenSize.height;
		this.width = screenSize.width;
		this.startTime = System.currentTimeMillis();
		
	}

	/**
	 * Méthode renvoyant l'objet unique de la classe. Si celui-ci n'est pas initialisé alors on l'initialise avant de le renvoyer.
	 * @return L'objet de la classe
	 */
	public static Application getInstance() {
		if (Application.instance == null)
			Application.instance = new Application();

		return Application.instance;
	}

	/**
	 * Cette méthode lance le calcul de la taille des différentes fenêtres de l'application en fonction de la taille de l'écran
	 */
	public void calculateComponentSize(){
		
		////////////////////////////////////////////////////////////
		//////////////////// GAME VIEW /////////////////////////////
		////////////////////////////////////////////////////////////
		ViewSettings.GAME_FRAME_WIDTH = (int)this.width-10;
		ViewSettings.GAME_FRAME_HEIGHT = (int)this.height-50;
		
		ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH = (int)((float)(ViewSettings.GAME_FRAME_WIDTH*(float) (1.0/3.0)));
		ViewSettings.INFORMATION_COMPONENT_VIEW_HEIGHT = (int)ViewSettings.GAME_FRAME_HEIGHT - ViewSettings.MESSAGE_COMPONENT_VIEW_HEIGHT - ViewSettings.MENU_COMPONENT_VIEW_HEIGHT; 
		
		ViewSettings.MESSAGE_COMPONENT_VIEW_WIDTH = (int)ViewSettings.GAME_FRAME_WIDTH - ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH;
		ViewSettings.STATISTICS_COMPONENT_VIEW_WIDTH = ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH;
		
		ViewSettings.GAMEVIEW_COMPONENT_VIEW_WIDTH = (int)ViewSettings.GAME_FRAME_WIDTH - ViewSettings.INFORMATION_COMPONENT_VIEW_WIDTH;
		ViewSettings.GAMEVIEW_COMPONENT_VIEW_HEIGHT = ViewSettings.INFORMATION_COMPONENT_VIEW_HEIGHT;

		

		////////////////////////////////////////////////////////////
		///////////////CHOOSE POSITION VIEW ////////////////////////
		////////////////////////////////////////////////////////////
		
		ViewSettings.CHOOSE_BOARD_FRAME_WIDTH = (int)((float)(ViewSettings.GAME_FRAME_WIDTH*(float) (9.0/10.0)));
		ViewSettings.CHOOSE_BOARD_FRAME_HEIGHT = (int)((float)(ViewSettings.GAME_FRAME_HEIGHT*(float) (9.0/10.0)));
		
		ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH = (int)((float)(ViewSettings.CHOOSE_BOARD_FRAME_WIDTH*(float) (1.0/5.0)));
		
		ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT = ViewSettings.CHOOSE_BOARD_FRAME_HEIGHT - ViewSettings.SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT;
		ViewSettings.GAMEVIEW_COMPONENT_CHOOSE_VIEW_WIDTH = ViewSettings.CHOOSE_BOARD_FRAME_WIDTH - ViewSettings.BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH;
	}

	/**
	 * Méthode retournant le temps d'exécution du logiciel en milli-seconde
	 * @return Le temps d'exécution
	 */
	public long getExecutionTime() {
		return System.currentTimeMillis() - this.startTime;
	}

	/**
	 * Méthode retournant la largeur de l'écran
	 * @return La largeur
	 */
	public int getScreenWidth() {
		return (int)this.width;
	}

	/**
	 * Méthode retournant la longueur de l'écran
	 * @return La longueur
	 */
	public int getScreenHeight() {
		return (int)this.height;
	}

	/**
	 * Méthode retournant la taille totale de la mémoire de la JVM
	 * @return La taille de la JVM
	 */
	public static long getJVMTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Méthode retournant la taille de la mémoire de la JVM libre
	 * @return La taille restante de la JVM
	 */
	public static long getJVMFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * Méthode retournant le nombre de processeur disponible
	 * @return
	 */
	public static long getJVMAvailableProcessor() {
		return Runtime.getRuntime().availableProcessors();
	}

	/**
	 * Méthode permettant de terminer la JVM
	 */
	public void killJVM() {
		Runtime.getRuntime().exit(0);
	}

	/**
	 * Méthode retournant la valeur de System.getProperty("java.vendor")
	 * @return La valeur en chaîne de caractère
	 */
	public static String getJavaVendor() {
		return System.getProperty("java.vendor");
	}

	/**
	 * Méthode retournant la valeur de System.getProperty("java.version")
	 * @return La valeur en chaîne de caractère
	 */
	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}

	/**
	 * Méthode retournant la valeur de System.getProperty("os.arch")
	 * @return La valeur en chaîne de caractère
	 */
	public static String getOsArch() {
		return System.getProperty("os.arch");
	}

	/**
	 * Méthode retournant la valeur de System.getProperty("os.name")
	 * @return La valeur en chaîne de caractère
	 */
	public static String getOsName() {
		return System.getProperty("os.name");
	}

	/**
	 * Méthode retournant la valeur de System.getProperty("os.version")
	 * @return La valeur en chaîne de caractère
	 */
	public static String getOsVersion() {
		return System.getProperty("os.version");
	}

	/**
	 * Méthode renvoyant toutes les variables de la classes ainsi que diverses informations sur la machine en chaîne de caractère
	 */
	public String toString(){
		return "Screen Width : " + this.width + " | Height : " + this.height + "\n" +
				"Temps écoulée : " + this.getExecutionTime() + " milli-secondes\n" +
				"JVm :" + "\n" +
				"Mémoire libre : " + getJVMFreeMemory() + "\n" +
				"Mémoire total : " + getJVMTotalMemory() + "\n" +
				"Processeur disponible : " + getJVMAvailableProcessor() + "\n" +
				"Java :" + "\n" +
				"Vendeur : " + getJavaVendor() + "\n" +
				"Version : " + getJavaVersion() + "\n" +
				"Arch : " + getOsArch() + "\n" +
				"Nom de l'OS : " + getOsName() + "\n" +
				"Version de l'OS : " + getOsVersion();
	}
}
