package com.jebhomenye.log4j.service;

import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.jebhomenye.log4j.store.CacheProvider;
import com.jebhomenye.log4j.store.CompositeStore;
import com.jebhomenye.log4j.store.DBMapProvider;
import com.jebhomenye.log4j.store.DataStore;
import com.google.common.cache.LoadingCache;


public class LoggingModule extends AbstractModule{

	@Override
	protected void configure() {
		TypeLiteral<ConcurrentNavigableMap<String, List<String>>> concurrentNavigableMap = new TypeLiteral<ConcurrentNavigableMap<String,List<String>>>() {};
		TypeLiteral<LoadingCache<String, List<String>>> loadingCache = new TypeLiteral<LoadingCache<String,List<String>>>() {};
		bind(concurrentNavigableMap).toProvider(DBMapProvider.class);
		bind(loadingCache).toProvider(CacheProvider.class);
		bind(DataStore.class).to(CompositeStore.class);
	}

}
