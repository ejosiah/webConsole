package com.jebhomenye.log4j.web.filter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jebhomenye.log4j.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class RequestFliterUTest {
	
	private static final String REQUEST_URL = "http://localhost/console/stream/someApp";
	private static final String INVALID_URL = "http://localhost/consoles/streams/someApp";
	private static final String MISSING_PARAMS_URL = "http://localhost/console/stream/";
	private static final Long TIME = 1368834205088L;
	
	@Mock
	private FilterChain mockFilterChain;
	
	@Mock
	private ServletResponse mockServletRes;
	
	@Mock
	private HttpServletRequest mockServletReq;
	
	@InjectMocks
	private RequestFilter requestFilter;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDoFilterWithNotime() throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		when(mockServletReq.getRequestURI()).thenReturn(REQUEST_URL);
		when(mockServletReq.getParameterMap()).thenReturn(params);
		
		requestFilter.doFilter(mockServletReq, mockServletRes, mockFilterChain);
		
		Map<String, String> expected = new HashMap<String, String>();
		expected.put("appName", "someApp");
		expected.put("time", Util.floor(new Date().getTime()) + "");
		
		assertEquals(expected, params);
		verify(mockFilterChain).doFilter(mockServletReq, mockServletRes);
	}
	
	@Test
	public void testDoFilterWithTime() throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		when(mockServletReq.getRequestURI()).thenReturn(REQUEST_URL + "/" + TIME);
		when(mockServletReq.getParameterMap()).thenReturn(params);
		
		requestFilter.doFilter(mockServletReq, mockServletRes, mockFilterChain);
		
		Map<String, String> expected = new HashMap<String, String>();
		expected.put("appName", "someApp");
		expected.put("time", Util.floor(TIME) + "");
		
		assertEquals(expected, params);
		verify(mockFilterChain).doFilter(mockServletReq, mockServletRes);		
	}
	
	@Test
	public void testForMalFormedUrl() throws Exception{
		when(mockServletReq.getRequestURI()).thenReturn(INVALID_URL);
		
		requestFilter.doFilter(mockServletReq, mockServletRes, mockFilterChain);
		verify(mockFilterChain).doFilter(mockServletReq, mockServletRes);		

	}


}
