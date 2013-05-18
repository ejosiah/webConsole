package com.jebhomenye.log4j.service;

import static com.jebhomenye.log4j.util.Util.*;
import java.util.List;

import com.google.inject.Inject;
import com.jebhomenye.log4j.store.DataStore;

public class LoggerService {
		
	@Inject
	private DataStore dataStore;
	
	public void log(String appName, String event, long time){
		dataStore.store(key(appName, time), event);
	}
	
	public List<String> readLog(String appName, long time){
		return dataStore.get(key(appName, time));
	}

}
