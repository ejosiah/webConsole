package com.jebhomenye.log4j.store;


public interface DataStore {
	
	void store(Long key, String event);
	
	String get(Long key);
}
