package com.jebhomenye.log4j.service;

import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jebhomenye.log4j.store.CompositeStore;
import com.jebhomenye.log4j.store.DataStore;

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
	
	public static void main(String[] args){
		ApplicationRegistry.get().injector.getInstance(DataStore.class);
	}
}
