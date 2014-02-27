package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

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

	public static Application getInstance(){
		if(Application.instance == null)
			Application.instance = new Application();

		return Application.instance;
	}

	public long getExecutionTime(){
		return System.currentTimeMillis() - this.startTime;
	}

	public int getScreenWidth() {
		return (int)this.width;
	}
	
	public int getScreenHeight() {
		return (int)this.height;
	}

	public static long getJVMTotalMemory(){
		return Runtime.getRuntime().totalMemory();
	}

	public static long getJVMFreeMemory(){
		return Runtime.getRuntime().freeMemory();
	}

	public static long getJVMAvailableProcessor(){
		return Runtime.getRuntime().availableProcessors();
	}

	public void killJVM(){
		Runtime.getRuntime().exit(0);
	}

	public static String getJavaVendor(){
		return System.getProperty("java.vendor");
	}

	public static String getJavaVersion(){
		return System.getProperty("java.version");
	}

	public static String getOsArch(){
		return System.getProperty("os.arch");
	}

	public static String getOsName(){
		return System.getProperty("os.name");
	}

	public static String getOsVersion(){
		return System.getProperty("os.version");
	}

	public String toString(){
		return "Screen Width : " + this.width + " | Height : " + this.height + "\n" +
				"Temps écoulée : " + this.getExecutionTime() + "\n" +
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
