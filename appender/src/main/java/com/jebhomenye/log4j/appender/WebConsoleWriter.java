package com.jebhomenye.log4j.appender;

import static com.jebhomenye.log4j.appender.Constants.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebConsoleWriter extends Writer {
	
	private static final int TIME_OUT = 1000;
	
	private DataOutputStream out;
	private HttpURLConnection httpConnection;
	
	public WebConsoleWriter(){
		initialise();
	}
	
	private void initialise(){
		try {
			httpConnection = openConnection();
			out = new DataOutputStream(httpConnection.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		byte[] data = copy(cbuf, off, len);
		out.write(data);

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
	
	private byte[] copy(char[] cData, int off, int len){
		byte[] data = new byte[len];
		for(int i = 0; i < len; i++){
			data[i] = (byte) cData[off+i];
		}
		return data;
	}

	@Override
	public void flush() throws IOException {
		out.flush();
		httpConnection.getInputStream();
		
	}

	@Override
	public void close() throws IOException {
		if(out != null){
			out.close();
		}
		if(httpConnection != null){
			httpConnection.disconnect();
		}
		
	}

}
