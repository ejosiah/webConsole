package com.jebhomenye.log4j.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.jebhomenye.log4j.util.Constants;

@Singleton
public class CacheProvider implements Provider<LoadingCache<String, List<String>>> {
	
	private static LoadingCache<String, List<String>> cache;
	
	@Inject
	private ConcurrentNavigableMap<String, List<String>> diskMap;

	public LoadingCache<String, List<String>> get() {
		if(cache == null){
			cache = createCache();
		}
		return cache;
	}
	
	private  LoadingCache<String, List<String>> createCache(){
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
