package com.jebhomenye.log4j.web.filter;

import static com.jebhomenye.log4j.util.Util.*;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestFilter extends AbstractFilter{

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		
		String url = req.getRequestURI();
		String pattern = "/stream/";
		int index = url.lastIndexOf(pattern);
		
		if(index < 0){
			filterChain.doFilter(request, response);
		}else{
			
			index += pattern.length();
			
			String query = url.substring(index);
			String[] params = query.split("/");
			
			
			Map paramMap = req.getParameterMap();
			String time = params.length < 2 ?  
					floor(new Date().getTime()) + "" : floor(new Long(params[1])) + "";
			String appName = params[0];
			
			paramMap.put("appName", appName);
			paramMap.put("time", time);
			
			filterChain.doFilter(request, response);
		}
		
	}


}