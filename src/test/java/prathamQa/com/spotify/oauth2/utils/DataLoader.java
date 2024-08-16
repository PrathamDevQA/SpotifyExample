package prathamQa.com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {

	private final Properties properties;
	private static DataLoader dataLoader;

	private DataLoader() {
		properties = PropertyUtility.propertyLoader("src\\test\\resources\\Data.properties");
	}

	public static DataLoader getInstanceData() {
		if (dataLoader == null) {
			dataLoader = new DataLoader();
		}
		return dataLoader;
	}

	public String getPlayListId() {
		String prop = properties.getProperty("get_playlist_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("Property id is not found");
	}

	public String getUpdatePlayListId() {
		String prop = properties.getProperty("update_playlist_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("Property id is not found");
	}
}
