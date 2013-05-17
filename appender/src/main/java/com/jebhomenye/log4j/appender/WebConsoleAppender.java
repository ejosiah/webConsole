package com.jebhomenye.log4j.appender;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

public class WebConsoleAppender extends WriterAppender {
	
	public WebConsoleAppender(){
	}
	
	public WebConsoleAppender(Layout layout){
		setLayout(layout);
		activateOptions();
	}
	
	@Override
	public void activateOptions(){
		setWriter(new WebConsoleWriter());
		super.activateOptions();
	}

}
