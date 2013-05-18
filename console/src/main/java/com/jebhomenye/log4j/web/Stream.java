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
		String appName = request.getParameter("appName");
		long time = new Long(request.getParameter("time"));
		
		List<String> logs = ApplicationRegistry.get().loggerService().readLog(appName, time);
		Map<String, List<String>> model = new HashMap<String, List<String>>();
		model.put("logs", logs);
		
		response.setContentType("text/json");
		OutputStream out = response.getOutputStream();
		new ObjectMapper().writeValue(out, model);
		out.flush();
	}

}
