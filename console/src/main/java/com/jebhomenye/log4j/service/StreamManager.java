package com.jebhomenye.log4j.service;

import java.nio.CharBuffer;
import java.util.Set;

import lombok.SneakyThrows;

import com.google.inject.Inject;
import com.jebhomenye.log4j.web.LoggerClient;

public class StreamManager implements LogEventListener{
	
	@Inject
	private Set<LoggerClient> connections;
	
	@SneakyThrows
	public void onLogEvent(LogEvent event) {
		for(LoggerClient client : connections){
			client.getWsOutbound().writeTextMessage(
					CharBuffer.wrap(event.getMessage()));
		}
		
	}


}
