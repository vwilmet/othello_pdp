import java.io.IOException;

import utils.FileHandlingException;

import com.manager.MNBVFile;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MNBVFile f = null;
		
		try {
			f = new MNBVFile("toto.mnbv", ".");
			f.write("mais ... mais");
			try {
				f.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				System.out.println("bouh");
		} catch (FileHandlingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
