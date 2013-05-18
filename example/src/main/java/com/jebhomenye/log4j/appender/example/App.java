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
        File file = new File("C:\\Users\\jay\\workspace\\sample data\\50000.csv");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
        	logger.info(scanner.nextLine());
        }
    }
}
