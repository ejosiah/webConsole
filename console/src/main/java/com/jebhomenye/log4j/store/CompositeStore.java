package com.jebhomenye.log4j.store;

import java.util.concurrent.ConcurrentNavigableMap;

import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CompositeStore implements DataStore {
	
	
	@Inject
	private LoadingCache<Long, String> cache;
	
	@Inject
	private ConcurrentNavigableMap<Long, String> diskMap;

	public void store(Long key, String event) {
		cache.put(key, event);
		diskMap.put(key, event);
		
	}

	public String get(Long key) {
		String result = null;
		try {
			result = cache.get(key);
		} catch (Exception e) {
			 // Ignore 
		}
		return result;
	}

}
