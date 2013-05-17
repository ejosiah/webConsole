package com.jebhomenye.log4j.store;

public interface DataStore {
	
	void store(String appName, String event, long time);
	
	String get(String appName, long time);
}
