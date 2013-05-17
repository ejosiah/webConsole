package com.jebhomenye.log4j.store;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.jebhomenye.log4j.util.Util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import com.google.common.cache.LoadingCache;

@RunWith(MockitoJUnitRunner.class)
public class CompositeStoreUTest {
	
	private static final long NOW = 1368829433730L;
	private static final String EVENT = "I just logged the time which was %s";
	private static final String APP_NAME = "someApp";
	
	@Mock
	private LoadingCache<String, List<String>> mockCache;
	
	@Mock
	private ConcurrentNavigableMap<String, List<String>> mockDisMap;
	
	@InjectMocks
	private CompositeStore dataStore;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStore() {
		String key = key(APP_NAME, floor(NOW));
		String event = String.format(EVENT, NOW);
		List<String> events = new ArrayList<String>();
		
		dataStore.store(key, event);
		
		events.add(event);
		
		verify(mockCache).put(key, events);
		verify(mockDisMap).put(key, events);
	}
	
	@Test
	public void testGet() throws Exception{
		List<String> logs = buildLogs();
		String key = key(APP_NAME, floor(NOW));
		when(mockCache.get(key)).thenReturn(logs );
		
		List<String> result = dataStore.get(key);
		
		assertEquals(logs, result);
	}
	
	private List<String> buildLogs(){
		List<String> logs = new ArrayList<String>();
		for(int i = 0; i < 10; i++){
			logs.add(String.format(EVENT, NOW+i));
		}
		
		return logs;
	}

}
