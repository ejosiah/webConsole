package com.jebhomenye.log4j.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestFilter extends AbstractFilter{

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		RequestWrapper req = new RequestWrapper((HttpServletRequest)request);
	/*	
		String url = req.getRequestURI();
		String pattern = "/stream";
		int index = url.lastIndexOf(pattern);
		
		if(index < 0){
			filterChain.doFilter(request, response);
		}else{
			
			index += pattern.length();
			
			String query = url.substring(index);
			String[] params = query.split("/");
			
			
			String time = params.length == 0 ?  
					System.currentTimeMillis() + "" : new Long(params[0]) + "";
			
			req.setParam("time", time);
			
			
			filterChain.doFilter(req, response);
		}*/
		req.setParam("time", System.currentTimeMillis() + "");
		filterChain.doFilter(req, response);
		
		
	}

}
