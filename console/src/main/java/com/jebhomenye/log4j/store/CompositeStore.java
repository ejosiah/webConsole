package com.jebhomenye.log4j.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ExecutionException;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CompositeStore implements DataStore {
	
	private static final List<String> EMPTY_LIST = Collections.emptyList();
	
	@Inject
	private LoadingCache<String, List<String>> cache;
	
	@Inject
	private ConcurrentNavigableMap<String, List<String>> diskMap;

	public void store(String key, String event) {
		List<String> events = cache.getIfPresent(key);
		if(events == null){
			events = new ArrayList<String>();
			events.add(event);
			cache.put(key, events);
		}else{
			events.add(event);
		}
		diskMap.put(key, events);
		
	}

	public List<String> get(String key) {
		List<String> result = null;
		try {
			result = cache.get(key);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		return result != null ? result : EMPTY_LIST;
	}

}
