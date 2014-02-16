import java.io.IOException;

import utils.FileHandlingException;

import com.manager.MNBVFile;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MNBVFile f = null;
		
		try {
			f = new MNBVFile("toto.mnbv", ".");
			
			/*f.write("mais ... mais");
			f.write("test ... test");
			f.write("12");*/
	
			/*f.setReadOnly();
			f.write("vincent en met sa main à couper");
			System.out.println(f.read());*/
			
			f.setWriteOnly();
			f.write("vincent en met sa main à couper");
			System.out.println(f.read());
			
			try {
				f.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileHandlingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
