package com.jebhomenye.log4j.store;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.jebhomenye.log4j.web.LoggerClient;

@Singleton
public class ConnectionProvider implements Provider<Set<LoggerClient>> {
	
	private final Set<LoggerClient> loggerClients = new CopyOnWriteArraySet<LoggerClient>();

	public Set<LoggerClient> get() {
		return loggerClients;
	}

}
