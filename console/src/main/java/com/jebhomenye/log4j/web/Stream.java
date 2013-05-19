package com.jebhomenye.log4j.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.jebhomenye.log4j.service.ApplicationRegistry;

public class Stream  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long time = new Long(request.getParameter("time"));
		
		String log = ApplicationRegistry.get().loggerService().readLog(time);
		Map<String, String> model = new HashMap<String, String>();
		model.put("log", log);
		
		response.setContentType("text/json");
		OutputStream out = response.getOutputStream();
		new ObjectMapper().writeValue(out, model);
		out.flush();
	}

}
