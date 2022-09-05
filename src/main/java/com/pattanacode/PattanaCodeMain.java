package com.pattanacode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pattanacode.utils.DateTimeUtils;


/**
 * 
 * @author patismyname
 *
 */
@SpringBootApplication
public class PattanaCodeMain {
	/**
	 * Run Main for this java file
	 * @param args
	 */
	private static Logger logger = LoggerFactory.getLogger(PattanaCodeMain.class);
	
	public static void main(String[] args) {
		logger.info("Start Pattana Code Main "+DateTimeUtils.getSystemDate());
		SpringApplication.run(PattanaCodeMain.class, args);
		
	}


}
