package com.jebhomenye.log4j.service;

import com.google.inject.Inject;
import com.jebhomenye.log4j.store.DataStore;

public class LoggerService {
	
	@Inject
	private DataStore dataStore;
	
	public void log(String appName, String event, long time){
		
	}
	
	public String readLog(String appName, long time){
		return null;
	}

}
