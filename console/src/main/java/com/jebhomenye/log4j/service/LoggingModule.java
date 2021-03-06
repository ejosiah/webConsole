package com.jebhomenye.log4j.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.jebhomenye.log4j.store.CacheProvider;
import com.jebhomenye.log4j.store.CompositeStore;
import com.jebhomenye.log4j.store.ConnectionProvider;
import com.jebhomenye.log4j.store.DBMapProvider;
import com.jebhomenye.log4j.store.DataStore;
import com.jebhomenye.log4j.store.LogEventListenersProdider;
import com.jebhomenye.log4j.web.LoggerClient;
import com.google.common.cache.LoadingCache;


public class LoggingModule extends AbstractModule{

	@Override
	protected void configure() {
		TypeLiteral<ConcurrentNavigableMap<Long, String>> concurrentNavigableMap = new TypeLiteral<ConcurrentNavigableMap<Long,String>>() {};
		TypeLiteral<LoadingCache<Long, String>> loadingCache = new TypeLiteral<LoadingCache<Long,String>>() {};
		TypeLiteral<Set<LoggerClient>> connections = new TypeLiteral<Set<LoggerClient>>() {};
		TypeLiteral<List<LogEventListener>> listeners = new TypeLiteral<List<LogEventListener>>() {};
		
		bind(concurrentNavigableMap).toProvider(DBMapProvider.class);
		bind(loadingCache).toProvider(CacheProvider.class);
		bind(DataStore.class).to(CompositeStore.class);
		bind(connections).toProvider(ConnectionProvider.class);
		bind(listeners).toProvider(LogEventListenersProdider.class);
	}

}
