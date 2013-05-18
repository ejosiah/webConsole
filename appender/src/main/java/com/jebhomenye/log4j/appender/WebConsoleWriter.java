package com.jebhomenye.log4j.appender;

import static com.jebhomenye.log4j.appender.Constants.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebConsoleWriter extends Writer {
	
	private static final int TIME_OUT = 1000;

	
	public WebConsoleWriter(){
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		HttpURLConnection httpConnection = openConnection();
		DataOutputStream out = new DataOutputStream(httpConnection.getOutputStream());
		
		try{
			out.writeUTF(Constants.APP_NAME);
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
		URL url = new URL(LOG_URL);
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
