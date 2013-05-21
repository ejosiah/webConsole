package com.jebhomenye.log4j.service;

import java.util.List;
import java.util.Set;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jebhomenye.log4j.store.ConnectionProvider;
import com.jebhomenye.log4j.store.LogEventListenersProdider;
import com.jebhomenye.log4j.web.LoggerClient;

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
	
	public List<LogEventListener> logEventListeners(){
		return injector.getProvider(LogEventListenersProdider.class).get().get();
	}
	
	public Set<LoggerClient> loggerClients(){
		return injector.getProvider(ConnectionProvider.class).get().get();
	}
	
	public static void main(String[] args){
		System.out.println(
				ApplicationRegistry.get().loggerClients());
	}
}
