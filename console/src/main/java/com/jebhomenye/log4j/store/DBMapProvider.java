package com.jebhomenye.log4j.store;

import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.jebhomenye.log4j.util.Constants;

@Singleton
public class DBMapProvider implements Provider<ConcurrentNavigableMap<String, List<String>>> {
	
	private ConcurrentNavigableMap<String, List<String>> dbMap;

	public ConcurrentNavigableMap<String, List<String>> get() {
		if(dbMap == null){
			dbMap =  DBHolder.db().getTreeMap(Constants.MAP_NAME);
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("provider called for DBMap");
		System.out.println();
		System.out.println();
		System.out.println();
		return dbMap;
	}
	

}
