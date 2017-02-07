package core.java.ch10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws IOException {
		Properties settings = new Properties();
		settings.put("width", "200");
		settings.put("title", "Hello World!");
		
		try {
			FileOutputStream out = new FileOutputStream("program.properties");
			settings.store(out, "Program Properties");
			
			FileInputStream in = new FileInputStream("program.properties");
			settings.load(in);
			
			System.out.println(settings.getProperty("width"));
			System.out.println(settings.getProperty("title"));
			
			String userDir = System.getProperty("user.home");
			System.out.println(userDir); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
