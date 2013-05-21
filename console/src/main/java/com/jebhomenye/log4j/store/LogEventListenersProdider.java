package com.jebhomenye.log4j.store;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.jebhomenye.log4j.service.LogEventListener;
import com.jebhomenye.log4j.service.LoggerService;
import com.jebhomenye.log4j.service.StreamManager;

@Singleton
public class LogEventListenersProdider implements Provider<List<LogEventListener>>{
	
	private List<LogEventListener> listeners = new ArrayList<LogEventListener>();
	
	@Inject
	public LogEventListenersProdider(StreamManager streamManager, LoggerService loggerService){
		listeners.add(streamManager);
		listeners.add(loggerService);
	}

	public List<LogEventListener> get() {
		return listeners;
	}
	
	

}
