package com.jebhomenye.log4j.web;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.jebhomenye.log4j.service.ApplicationRegistry;
import com.jebhomenye.log4j.service.LogEvent;
import com.jebhomenye.log4j.service.LogEventListener;

@WebServlet("/logger.do")
public class Logger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataInputStream in = new DataInputStream(request.getInputStream());
		
		String event = eventLog(in);
		long time = System.currentTimeMillis();
		
		publishLogEvent(time, event);

	}
	
	private String eventLog(DataInputStream in) throws IOException{
		char chr = 0;
		StringWriter writer = new StringWriter();
		while((chr = in.readChar()) > 0){
			writer.write(chr);
		}
		return writer.toString();
	}
	
	public void publishLogEvent(long time, String message){
		LogEvent event = new LogEvent(time, message);
		List<LogEventListener> listeners = ApplicationRegistry.get().logEventListeners();
		for(LogEventListener listener : listeners){
			listener.onLogEvent(event);
		}
	}

}