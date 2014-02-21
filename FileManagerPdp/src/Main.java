import com.error_manager.Log;
import com.manager.FilesManager;
import com.manager.FilesManagerImpl;

public class Main {
	
	public static void main(String[] args) {
		
		Log.reset();
		
		FilesManager files = new FilesManagerImpl();
		files.init(false);
		
		if(files.autoSave("Le texte de la mort qui tue :) youpi1"))
			System.out.println("OK!!");
		
		if(files.autoSave("Le texte de la mort qui tue :) youpi2"))
			System.out.println("OK!!");
		
		if(files.autoSave("Le texte de la mort qui tue :) youpi3"))
			System.out.println("OK!!");
		
		System.out.println("Terminée !!");
	}

}
