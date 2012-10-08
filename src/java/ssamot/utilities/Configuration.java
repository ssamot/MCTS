package ssamot.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;

public class Configuration {
	private static Configuration theInstance;
	private static String PATH = "./";
	private static String PROPERTIES_FILENAME = "main.config";
	private Properties props = null;
	


	public String getRuntime_directory() {
		return getPropertyAsString("runtime.directory");
	}

	public String getCma_configuration_file() {
		return getRuntime_directory() + getPropertyAsString("cma.configuration.file") ;
	}

	public Boolean istCocoBbopNoisy() {
		return getPropertyAsBoolean("coco.bbop.noisy") ;
	}
	
	
	private Configuration() throws IOException {

		FileInputStream propFile = new FileInputStream(PATH
				+ PROPERTIES_FILENAME);
		props = new Properties(System.getProperties());
		props.load(propFile);

	}

	public static Configuration getConfiguration() {
		synchronized (Configuration.class) {
			if (theInstance == null) {
				try {
					theInstance = new Configuration();
				} catch (IOException e) {
					throw new MissingResourceException(
							"Unable to load property file \"" + PATH + "\"",
							Configuration.class.getName(), PROPERTIES_FILENAME);
				}
			}
		}
		return theInstance;
	}

	public static String getPropertyAsString(String name) {
		String value = getConfiguration().props.getProperty(name);
		if (value == null) {
			System.err.println("Searching for unknown property:" + name);
		}

		return value;
	}
	
	
	private Integer getPropertyAsInt(String name) {
		String value = getConfiguration().props.getProperty(name);
		if (value == null) {
			System.err.println("Searching for unknown property:" + name);
		}

		return Integer.decode(value);
	}
	
	private Double getPropertyAsDouble(String name) {
		String value = getConfiguration().props.getProperty(name);
		if (value == null) {
			System.err.println("Searching for unknown property:" + name);
		}

		return Double.valueOf(value);
	}
	
	private Boolean getPropertyAsBoolean(String name) {
		String value = getConfiguration().props.getProperty(name);
		if (value == null) {
			System.err.println("Searching for unknown property:" + name);
		}

		return Boolean.valueOf(value);
	}
	
	

}
