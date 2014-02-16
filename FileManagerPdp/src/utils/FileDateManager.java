package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe qui permet de générer la date formater spécialement pour le nom de fichier
 * @author <ul><li>Vincent Wilmet</li><li>Morgane Badré</li>
 * @version 1.0
 */
public class FileDateManager {
	
	/**
	 * Méthode retournant la date sous format AAAA-MM-DD-HH-MM-SS avec : 
	 * <ul>
	 * <li>AAAA : Année -> 2014 </li>
	 * <li>MM : Mois -> 01 </li>
	 * <li>DD : jour -> 12 </li>
	 * <li>HH : heure -> 24 </li>
	 * <li>MM : minute -> 36 </li>
	 * <li>SS : seconde -> 38 </li>
	 * <li>Exemple : 2014-02-16-19-27-38</li>
	 * </ul>
	 * @return la date + heure en String
	 */
	public static String getDateFormatAAAAMMJJHHMMSS(){
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}
	
}
