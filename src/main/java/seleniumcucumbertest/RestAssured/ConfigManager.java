package seleniumcucumbertest.RestAssured;

import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class ConfigManager
{

	private static final Properties properties = new Properties();
	
	static {
		try (InputStream is =ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")){
		properties.load(is);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}
		
	}
	public static String getProperty(String key) {
		return System.getProperty(key, properties.getProperty(key));
	}	
}
