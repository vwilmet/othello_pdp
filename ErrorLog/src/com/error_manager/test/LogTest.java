package com.error_manager.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import junit.framework.TestCase;

import org.junit.Test;

import com.error_manager.Log;

public class LogTest extends TestCase {

	private BufferedReader reader;	

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		reader = new BufferedReader(new FileReader(new File("log.txt")));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testError() {
		String 	error = "Error message to verify",
				content = "";

		Log.error(error);

		try {
			String line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}

		if(!content.substring(content.length()-error.length()).equals(error))
			fail("Not working");
	}

	@Test
	public void testReset() {
		String 	error = "Error message to verify",
				content = "",
				line = "";

		Log.error(error);
		
		try {
			line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}
		
		if(content.length() <= error.length())
			fail("Could not write message !");
		
		Log.reset();
		
		try {
			line = "";
			content = "";
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		} catch (Exception e) {
			fail("Not working : exception e = " + e.getMessage());
		}
		
		if(content.length() != 0)
			fail("Reset not working !");
	}

}