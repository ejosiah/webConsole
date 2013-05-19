package com.jebhomenye.log4j.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.jebhomenye.log4j.store.DataStore;

@RunWith(MockitoJUnitRunner.class)
public class LoggerServiceUTest {
	
	private static final long NOW = System.currentTimeMillis();
	private static final String EVENT = "I just logged the time which was " + NOW;
	
	@Mock
	private DataStore mockStore;
	
	@InjectMocks
	private LoggerService loggerService;

	@Test
	public void testLog() {
		loggerService.log(NOW, EVENT);
		verify(mockStore).store(NOW, EVENT);
	}

	@Test
	public void testReadLog() {
		when(mockStore.get(NOW)).thenReturn(EVENT);
		String result = loggerService.readLog(NOW);
		assertEquals(EVENT, result);
	}
	
	@Test
	public void testReadLogRetry(){
		RetryAnswer answer = new RetryAnswer();
		for(int i = 0; i < 11; i++){
			when(mockStore.get(NOW + i)).thenAnswer(answer);			
		}
		
		String result = loggerService.readLog(NOW + 10);
		
		verify(mockStore, times(11)).get(anyLong());
		assertEquals(EVENT, result);
	}
	
	
	private static class RetryAnswer implements Answer<String>{

		public String answer(InvocationOnMock invocation) throws Throwable {
			long time = (Long) invocation.getArguments()[0];
			
			return time != NOW ? null : EVENT;
		}
		
	}

}
