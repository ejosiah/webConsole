package com.jebhomenye.log4j.appender;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {
	private static final String PROPERTIES_PATH = "/webconsole.properties";
	public static final String CONSOLE_URL;
	public static final String LOG_URL;
	public static final String APP_NAME;
	
	static{
		InputStream propsStream = Constants.class.getResourceAsStream(PROPERTIES_PATH);
		Properties properties = new Properties();
		try {
			properties.load(propsStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		CONSOLE_URL = properties.getProperty("webconsole.url");
		APP_NAME = properties.getProperty("webconsole.app.name");
		LOG_URL = CONSOLE_URL +  "/logger.do";
	}

}
