package com.jebhomenye.log4j.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jebhomenye.log4j.service.ApplicationRegistry;

public class ContextInitializer implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent event) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		ApplicationRegistry.get();
		
	}

}
