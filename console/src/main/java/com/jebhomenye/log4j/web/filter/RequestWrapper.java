package com.jebhomenye.log4j.web.filter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.Delegate;

public class RequestWrapper implements HttpServletRequest {
	
	private interface Ignore{
		Map getParameterMap();
		String getParameter(String key);
	}
	
	@Delegate(excludes=Ignore.class)
	private final HttpServletRequest request;
	
	private final Map<Object, Object> params;
	
	public RequestWrapper(HttpServletRequest request){
		this.request = request;
		params = new HashMap<Object, Object>();
	}

	public Map getParameterMap() {
		Map<Object, Object> mergeMap = new HashMap<Object, Object>();
		mergeMap.putAll(request.getParameterMap());
		mergeMap.putAll(params);
		
		return Collections.unmodifiableMap(mergeMap);
	}
	
	void setParam(String key, Object value){
		params.put(key, value);
	}
	
	void setParams(Map<Object, Object> params){
		this.params.putAll(params);
	}

	public String getParameter(String key) {
		return (String)getParameterMap().get(key);
	}
	
	
	
}
