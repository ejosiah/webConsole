package com.jebhomenye.log4j.appender;

import lombok.Setter;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

@Setter
public class WebConsoleAppender extends WriterAppender {
	
	private String url;
	private String appName;
	
	public WebConsoleAppender(){
	}
	
	public WebConsoleAppender(Layout layout){
		setLayout(layout);
		activateOptions();
	}
	
	public WebConsoleAppender(Layout layout, String url, String appName){
		this(layout);
		setUrl(url);
		setAppName(appName);
	}
	
	@Override
	public void activateOptions(){
		setWriter(new WebConsoleWriter(url));
		super.activateOptions();
	}

}
