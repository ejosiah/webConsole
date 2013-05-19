package com.jebhomenye.log4j.appender;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @AllArgsConstructor
public class WebConsoleWriter extends Writer {
	
	private static final int TIME_OUT = 1000;
	
	private String url;
	
	public WebConsoleWriter(){
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		HttpURLConnection httpConnection = openConnection();
		DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
		
		try{
			for(int i = 0; i < cbuf.length; i++){
				out.writeChar(cbuf[i]);
			}
			out.flush();
			httpConnection.getInputStream();
		}finally{
			if(out != null){
				out.close();
			}
			if(httpConnection != null){
				httpConnection.disconnect();
			}
		}

	}
	
	private HttpURLConnection openConnection() throws IOException{
		URL url = new URL(this.url + "/logger.do");
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(TIME_OUT);
		connection.setReadTimeout(TIME_OUT);
		
		return connection;
	}

	@Override
	public void flush() throws IOException {

		
	}

	@Override
	public void close() throws IOException {
		
	}

}
