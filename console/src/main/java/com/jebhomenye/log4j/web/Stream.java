package com.jebhomenye.log4j.web;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.jebhomenye.log4j.service.ApplicationRegistry;

@WebServlet("/stream")
public class Stream  extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	
	private final AtomicLong clientId = new AtomicLong(0L);
	
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		
		return new LoggerClient(clientId.incrementAndGet()
				, ApplicationRegistry.get().loggerClients());
	}
	
	
}
