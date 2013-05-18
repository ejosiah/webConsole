package com.jebhomenye.log4j.store;

import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import com.google.inject.Provider;
import com.jebhomenye.log4j.util.Constants;

public class DBMapProvider implements Provider<ConcurrentNavigableMap<String, List<String>>> {

	public ConcurrentNavigableMap<String, List<String>> get() {
		return DBHolder.db().getTreeMap(Constants.MAP_NAME);
	}
	

}
