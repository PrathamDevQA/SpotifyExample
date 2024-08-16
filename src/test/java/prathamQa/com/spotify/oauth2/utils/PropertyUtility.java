package prathamQa.com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {

	public static Properties propertyLoader(String filePath) {
		Properties properties = new Properties();
		BufferedReader bufferedReader;

		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));

			try {
				properties.load(bufferedReader);
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("failed to load properties file " + filePath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("properties file not found at " + filePath);

		}
		return properties;

	}

}
