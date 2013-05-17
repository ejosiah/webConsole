package com.jebhomenye.log4j.util;

public final class Util {
	private Util(){}
	
	public static final long floor(long time){
		return time - time % Constants.INTERVAL;
	}
	
	public static final String key(String appName, long time){
		return appName + "_" + time;
	}
	
}
