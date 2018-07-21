package com.Cucumber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ReadPropertiesFile {

	public static String ReadProperties(String testKey){
		Properties properties = new Properties();
		try {
			String curDir = System.getProperty("user.dir");
			String fileData = curDir+ File.separator + "src" +File.separator + "test" + File.separator + "resources" + File.separator + "sqlConfig.properties";

			File file = new File(fileData);
			FileInputStream fileInput = new FileInputStream(file);

			properties.load(fileInput);
			fileInput.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(testKey);
	}	
}

