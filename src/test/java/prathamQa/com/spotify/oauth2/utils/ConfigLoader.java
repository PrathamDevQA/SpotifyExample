package prathamQa.com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

	private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {
		properties = PropertyUtility.propertyLoader("src\\test\\resources\\Config.properties");
	}

	public static ConfigLoader getInstace() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getClintId() {
		String prop = properties.getProperty("client_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("Clint_Id is not found");
	}

	public String getClintSecret() {
		String prop = properties.getProperty("client_secret");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("Clint_Secret is not found");
	}

	public String getRefreshToken() {
		String prop = properties.getProperty("refresh_token");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("refresh_token is not found");
	}

	public String getGrantType() {
		String prop = properties.getProperty("grant_type");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("grant_type is not found");
	}

	public String getUserId() {
		String prop = properties.getProperty("user_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("user_id is not found");
	}
}
