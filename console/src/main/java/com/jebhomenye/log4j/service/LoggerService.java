package com.jebhomenye.log4j.service;

import com.google.inject.Inject;
import com.jebhomenye.log4j.store.DataStore;

public class LoggerService {
		
	@Inject
	private DataStore dataStore;
	
	public void log(long time, String event){
		dataStore.store(time, event);
	}
	
	public String readLog(long time){
		String log =  dataStore.get(time);
		for(int i = 1; (log == null && i < 2); i++){
			long newTime = time - i;
			log = dataStore.get(newTime);
		}
		return log != null ? log : "";
	}

}
