package com.jebhomenye.log4j.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.jebhomenye.log4j.store.DBHolder;
import com.jebhomenye.log4j.util.Constants;

@WebListener
public class ContextInitializer implements ServletContextListener{
	

	public void contextDestroyed(ServletContextEvent event) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		DBHolder.db().createTreeMap(Constants.MAP_NAME);
		
	}

}
