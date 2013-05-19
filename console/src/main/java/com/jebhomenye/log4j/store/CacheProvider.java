package com.jebhomenye.log4j.store;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.jebhomenye.log4j.util.Constants;

@Singleton
public class CacheProvider implements Provider<LoadingCache<Long, String>> {
	
	private static LoadingCache<Long, String> cache;
	
	@Inject
	private ConcurrentNavigableMap<Long, String> diskMap;

	public LoadingCache<Long, String> get() {
		if(cache == null){
			cache = createCache();
		}
		return cache;
	}
	
	private  LoadingCache<Long, String> createCache(){
		return CacheBuilder.newBuilder()
				 .maximumSize(Constants.MAX_SIZE)
				 .expireAfterWrite(Constants.CACHE_EXPIRY_TIME, TimeUnit.MINUTES)
				 .build(new CacheLoader<Long, String>(){

					@Override
					public String load(Long key) throws Exception {
						
						return diskMap.get(key);
					}
					 
				 });
	}
}
