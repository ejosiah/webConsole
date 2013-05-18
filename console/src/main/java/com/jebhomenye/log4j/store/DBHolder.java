package com.jebhomenye.log4j.store;

import org.apache.jdbm.DB;
import org.apache.jdbm.DBMaker;

import com.jebhomenye.log4j.util.Constants;

public class DBHolder {
	
	private static final DB db;
	
	static{
		db = DBMaker.openFile(Constants.DB_PATH)
				.closeOnExit()
				.make();
	}

	public static DB db() {
		return db;
	}

}
