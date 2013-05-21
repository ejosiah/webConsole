package com.jebhomenye.log4j.appender.example;

import java.io.File;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args ) throws Exception
    {
        File file = new File("C:\\Users\\jay\\workspace\\sample data\\500.csv");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
        	Thread.sleep(10);
        	logger.info(scanner.nextLine());
        }
        
        try{
        	Object[] array = new Object[0];
        	array[1] = 0;
        }catch(Exception e){
        	logger.error("Error accessing object", e);
        }
    }
}
