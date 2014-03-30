package com.manager.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.manager.FilesManager;
import com.manager.FilesManagerImpl;

public class FilesManagerImplTest  extends TestCase {

	private String file_name;
	private String file_path_name;
	private String file_content;
	private String autosave_file_name;
	private BufferedReader reader;	
	private FilesManager files;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		file_name = "test.txt";
		file_path_name = "";
		file_content = "test ... un ... deux";
		autosave_file_name = "autosave";
		files = new FilesManagerImpl();
		files.init(autosave_file_name, false);
	}

	@After
	public void tearDown() throws Exception {
		file_name = null;
		file_path_name = null;
		file_content = null;
		reader.close();
		File file = new File(file_path_name+file_name);
		file.delete();
		files = null;
	}
	
	@Test
	public void testSave() {
		String content = "";
		
		if(!files.save(file_name, file_path_name, file_content))
			fail("Erreur lors de l'écriture dans le fichier");
		
		try {
			reader = new BufferedReader(new FileReader(new File(file_path_name+file_name)));
		} catch (FileNotFoundException e) {
			fail("Not working : exception e = " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			String line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}
		
		if(!content.equals(file_content))
			fail("testSave not working");
	}
	
	@Test
	public void testLoad() {
		String content = "";
		
		if(!files.save(file_name, file_path_name, file_content))
			fail("Erreur lors de l'écriture dans le fichier");
		
		String text = files.load(file_name, file_path_name);
		
		try {
			reader = new BufferedReader(new FileReader(new File(file_path_name+file_name)));
		} catch (FileNotFoundException e) {
			fail("Not working : exception e = " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			String line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line + "\n";
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}
		
		if(!content.equals(text))
			fail("testLoad not working");
	}
	
	@Test
	public void testAutoSave() {
		String first_text = "uno",
				second_text ="due",
				third_text = "tre";
		
		String content = "";
	
		if(!files.autoSave(first_text))
			fail("Erreur lors de l'écriture dans le fichier");
		
		try {
			reader = new BufferedReader(new FileReader(new File(files.getFirstAutoSaveRealFileName())));
		} catch (FileNotFoundException e) {
			fail("Not working : exception e = " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			String line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}
		
		if(!content.equals(first_text))
			fail("Erreur dans le contenue de la première sauvegarde");
		
		if(!files.autoSave(second_text))
			fail("Erreur lors de l'écriture dans le fichier du second texte");
		
		try {
			reader = new BufferedReader(new FileReader(new File(files.getSecondAutoSaveRealFileName())));
		} catch (FileNotFoundException e) {
			fail("Not working : exception e = " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			String line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}
		
		if(!content.equals(second_text))
			fail("Erreur dans le contenue de la deuxième sauvegarde");
		
		if(!files.autoSave(third_text))
			fail("Erreur lors de l'écriture dans le fichier du troisième texte");
		
		try {
			reader = new BufferedReader(new FileReader(new File(files.getFirstAutoSaveRealFileName())));
		} catch (FileNotFoundException e) {
			fail("Not working : exception e = " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			String line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}
		
		if(!content.equals(third_text))
			fail("Erreur dans le contenue de la troisième sauvegarde");
	}
}