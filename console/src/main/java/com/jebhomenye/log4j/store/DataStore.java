package com.jebhomenye.log4j.store;

import java.util.List;

public interface DataStore {
	
	void store(String key, String event);
	
	List<String> get(String key);
}
