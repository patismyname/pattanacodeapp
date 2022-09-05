/**
 * 
 */
package com.pattanacode.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pattanacode.dto.GreetingDto;
import com.pattanacode.services.GreetingMakerService;


/**
 * @author patismyname
 *
 */
@RestController
@RequestMapping("/api/greeting/v1")
public class GreetingController {
	
	private final static Logger logger = LoggerFactory.getLogger(GreetingController.class);

	
	@Autowired
	private GreetingMakerService greetingMakerService;
	
	
	 @GetMapping({"/say", ""})
	public ResponseEntity<GreetingDto> getGreeting(@RequestParam(required = false, defaultValue = "Th", value = "strLanguageCode") String strLanguageCode) throws Exception{
		//String strGreeting="";
		GreetingDto greetingDto=new GreetingDto(); 
		greetingDto=greetingMakerService.getGreetingLanguage(strLanguageCode);
		
		  logger.info("Get Promotions successfully");
	        return ResponseEntity.status(HttpStatus.OK).body(greetingDto);
		
	}

	/**
	 * 
	 */
	public GreetingController() {
		// TODO Auto-generated constructor stub
	}

}
