package com.jebhomenye.log4j.store;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.jdbm.DB;
import org.apache.jdbm.DBMaker;



import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jebhomenye.log4j.util.Constants;
public class CompositeStore implements DataStore {
	
	private LoadingCache<String, List<String>> cache;
	private ConcurrentNavigableMap<String, List<String>> diskMap;
	
	public CompositeStore(){
		initialize();
	}
	
	private void initialize() {
		DB db = DBMaker.openFile(Constants.DB_PATH)
					.closeOnExit()
					.make();
		diskMap = db.getTreeMap(Constants.MAP_NAME);
		
		cache = CacheBuilder.newBuilder()
				 .maximumSize(Constants.MAX_SIZE)
				 .expireAfterWrite(Constants.CACHE_EXPIRY_TIME, TimeUnit.MINUTES)
				 .build(new CacheLoader<String, List<String>>(){

					@Override
					public List<String> load(String key) throws Exception {
						
						return diskMap.get(key);
					}
					 
				 });
	}

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
		try {
			return cache.get(key);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

}
