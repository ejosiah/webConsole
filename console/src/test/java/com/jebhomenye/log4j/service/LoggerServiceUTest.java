package com.jebhomenye.log4j.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jebhomenye.log4j.store.DataStore;

@RunWith(MockitoJUnitRunner.class)
public class LoggerServiceUTest {
	
	private static final Date NOW = new Date();
	private static final String EVENT = "I just logged the time which was " + NOW.toString();
	private static final String APP_NAME = "someApp";
	
	@Mock
	private DataStore mockStore;
	
	@InjectMocks
	private LoggerService loggerService;

	@Test
	public void testLog() {
		loggerService.log(APP_NAME, EVENT , NOW.getTime());
		verify(mockStore).store(APP_NAME + "_" + floor(NOW.getTime()), EVENT);
	}
	
	private long floor(long time){
		return time - time % 1000;
	}

	@Test
	public void testReadLog() {
		when(mockStore.get(APP_NAME + "_" + floor(NOW.getTime()))).thenReturn(Arrays.asList(EVENT));
		List<String> result = loggerService.readLog(APP_NAME, NOW.getTime());
		assertTrue(result.size() == 1);
		assertEquals(EVENT, result.get(0));
	}

}
