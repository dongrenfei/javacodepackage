package com.steve.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PropertiesReader {

	private final static String PROPERTIES_FILE = "com.steve.resource.steve";
	private static Logger logger = Logger.getLogger(PropertiesReader.class);
	private static ResourceBundle properties;

	/**
	 * Get a property. If the file has not already been read, this method will
	 * read it. If it has been read, it will used the cached copy. Web app must
	 * be recycled for changes to the props file to become effective. Creation
	 * date: (8/03/2004 12:00:45 PM)
	 * 
	 * @return String
	 */
	public static String getProperty(String key) {

		// first, ensure properties file has been loaded
		// if it's null, it hasn't been loaded, we need to
		// synchronize it's loading in case 2 threads hit at once
		if (properties == null) {
			synchronized (PROPERTIES_FILE) {
				if (properties == null) {
					// if a MissingResourceException happens, we throw
					properties = ResourceBundle.getBundle(PROPERTIES_FILE);
				}
			}
		}

		// we have a good props file, look for the value
		String value = null;
		try {
			value = properties.getString(key);
			if (value == null) {
				if(logger.isDebugEnabled()) {
					logger.warn("Attempt to retrieve parameter \"" + key + "\" returned null.");
				}
			}
		} catch (MissingResourceException e) {
			// catch and release:
			if(logger.isDebugEnabled()) {
				logger.error("Attempt to retrieve parameter \"" + key + "\" threw MissingResourceException");
			}
			throw e;
		}
		return value;
	}

}
