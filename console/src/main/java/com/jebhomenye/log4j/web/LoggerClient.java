package com.jebhomenye.log4j.web;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Set;

import lombok.AllArgsConstructor;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

@AllArgsConstructor
public class LoggerClient extends MessageInbound {
	
	private final Long id;
	private final Set<LoggerClient> connections;
	
	@Override
	protected void onOpen(WsOutbound outbound){
		connections.add(this);
		System.out.println(id + " connected to logger");
		
	}
	
	@Override
	protected void onClose(int status){
		connections.remove(this);
	}
	

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not suppored");
		
	}

	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		System.out.println(id + ": " + message.toString());
	}

}
