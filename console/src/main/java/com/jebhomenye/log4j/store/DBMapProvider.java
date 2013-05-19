package com.jebhomenye.log4j.store;

import java.util.concurrent.ConcurrentNavigableMap;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.jebhomenye.log4j.util.Constants;

@Singleton
public class DBMapProvider implements Provider<ConcurrentNavigableMap<Long, String>> {
	
	private ConcurrentNavigableMap<Long, String> dbMap;

	public ConcurrentNavigableMap<Long, String> get() {
		if(dbMap == null){
			dbMap =  DBHolder.db().getTreeMap(Constants.MAP_NAME);
		}
		return dbMap;
	}
	

}
