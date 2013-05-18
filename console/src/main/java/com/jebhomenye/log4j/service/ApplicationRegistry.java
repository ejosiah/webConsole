package com.jebhomenye.log4j.service;

import com.google.inject.Guice;
import com.google.inject.Injector;

public final class ApplicationRegistry {
	
	private static final ApplicationRegistry instance = new ApplicationRegistry();
	private final Injector injector;
	
	private ApplicationRegistry(){
		injector = Guice.createInjector(new LoggingModule());
	}
	
	public static final ApplicationRegistry get(){
		return instance;
	}
	
	public LoggerService loggerService(){
		return injector.getInstance(LoggerService.class);
	}
}
