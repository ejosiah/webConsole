package com.jebhomenye.log4j.service;

import java.util.List;

import com.google.inject.Inject;
import com.jebhomenye.log4j.store.DataStore;

public class LoggerService {
	
	private static final long INTERVAL = 1000L;
	
	@Inject
	private DataStore dataStore;
	
	public void log(String appName, String event, long time){
		dataStore.store(key(appName, time), event);
	}
	
	public List<String> readLog(String appName, long time){
		return dataStore.get(key(appName, time));
	}
	
	private String key(String appName, long time){
		time = floor(time);
		return appName + "_" + time;
	}
	
	private long floor(long time){
		return time - (time % INTERVAL);
	}

}
