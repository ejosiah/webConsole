package com.jebhomenye.log4j.store;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.jebhomenye.log4j.util.Util.*;

import java.util.ArrayList;
import java.util.Collections;
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
	
	@Mock
	private LoadingCache<Long, String> mockCache;
	
	@Mock
	private ConcurrentNavigableMap<Long, String> mockDisMap;
	
	@InjectMocks
	private CompositeStore dataStore;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStore() {
		Long key = NOW;
		String event = String.format(EVENT, NOW);
		
		dataStore.store(key, event);
		
		
		verify(mockCache).put(key, event);
		verify(mockDisMap).put(key, event);
	}
	
	@Test
	public void testGet() throws Exception{
		String log = String.format(EVENT, NOW);
		long key = NOW;
		when(mockCache.get(key)).thenReturn(log);
		
		String result = dataStore.get(key);
		
		assertEquals(log, result);
	}
	
	@Test
	public void testNoData() throws Exception{
		Long key = NOW;
		when(mockCache.get(key)).thenReturn(null);
		
		String result = dataStore.get(key);
		
		assertNull(result);
	}

}
