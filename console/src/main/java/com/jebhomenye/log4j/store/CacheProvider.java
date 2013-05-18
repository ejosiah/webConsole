package com.jebhomenye.log4j.store;

import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jebhomenye.log4j.util.Constants;

public class CacheProvider implements Provider<LoadingCache<String, List<String>>> {
	
	@Inject
	private ConcurrentNavigableMap<String, List<String>> diskMap;

	public LoadingCache<String, List<String>> get() {
		return CacheBuilder.newBuilder()
				 .maximumSize(Constants.MAX_SIZE)
				 .expireAfterWrite(Constants.CACHE_EXPIRY_TIME, TimeUnit.MINUTES)
				 .build(new CacheLoader<String, List<String>>(){

					@Override
					public List<String> load(String key) throws Exception {
						
						return diskMap.get(key);
					}
					 
				 });
	}

}
