package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.model.view.ViewSettings;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class Application {

	private Dimension screenSize;
	private double width = 0;
	private double height = 0;
	private long startTime = 0;
	private static Application instance;

	private Application() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.height = screenSize.height;
		this.width = screenSize.width;
		this.startTime = System.currentTimeMillis();
		
	}

	public static Application getInstance() {
		if (Application.instance == null)
			Application.instance = new Application();

		return Application.instance;
	}

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

	public long getExecutionTime() {
		return System.currentTimeMillis() - this.startTime;
	}

	public int getScreenWidth() {
		return (int)this.width;
	}

	public int getScreenHeight() {
		return (int)this.height;
	}

	public static long getJVMTotalMemory() {
		return Runtime.getRuntime().totalMemory();
	}

	public static long getJVMFreeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	public static long getJVMAvailableProcessor() {
		return Runtime.getRuntime().availableProcessors();
	}

	public void killJVM() {
		Runtime.getRuntime().exit(0);
	}

	public static String getJavaVendor() {
		return System.getProperty("java.vendor");
	}

	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}

	public static String getOsArch() {
		return System.getProperty("os.arch");
	}

	public static String getOsName() {
		return System.getProperty("os.name");
	}

	public static String getOsVersion() {
		return System.getProperty("os.version");
	}

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
