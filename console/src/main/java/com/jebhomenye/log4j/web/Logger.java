package com.jebhomenye.log4j.web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.jebhomenye.log4j.service.ApplicationRegistry;

/**
 * Servlet implementation class Logger
 */
public class Logger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataInputStream in = new DataInputStream(request.getInputStream());
		
		String event = eventLog(in);
		long time = System.currentTimeMillis();
		
		ApplicationRegistry.get().loggerService().log(time, event);

	}
	
	private String eventLog(DataInputStream in) throws IOException{
		char chr = 0;
		StringWriter writer = new StringWriter();
		while((chr = in.readChar()) > 0){
			writer.write(chr);
		}
		return writer.toString();
	}

}