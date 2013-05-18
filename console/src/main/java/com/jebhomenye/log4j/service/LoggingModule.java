package com.jebhomenye.log4j.service;

import java.util.concurrent.ConcurrentNavigableMap;

import com.google.inject.AbstractModule;
import com.jebhomenye.log4j.store.CacheProvider;
import com.jebhomenye.log4j.store.CompositeStore;
import com.jebhomenye.log4j.store.DBMapProvider;
import com.jebhomenye.log4j.store.DataStore;
import com.google.common.cache.LoadingCache;

public class LoggingModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(DataStore.class).to(CompositeStore.class);
		bind(ConcurrentNavigableMap.class).toProvider(DBMapProvider.class);
		bind(LoadingCache.class).toProvider(CacheProvider.class);
		bind(LoggerService.class).to(LoggerService.class);
	}

}
