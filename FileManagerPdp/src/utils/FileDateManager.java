package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDateManager {
	
	public static String getDateFormatAAAAMMJJHHMMSS(){
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}
	
}
