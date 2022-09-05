/**
 * 
 */
package com.pattanacode.services;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

import com.pattanacode.dto.GreetingDto;

/**
 * @author patismyname
 *
 */
@Service
public class GreetingMakerService {

	/**
	 * 
	 */
	public GreetingMakerService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param strLanguageCode En=English Language Th=Thai Language
	 * @return
	 */
	public GreetingDto getGreetingLanguage(String strLanguageCode) { // or return String in your case
		String strGreetingLanguage = "";
		GreetingDto greetingDto=new GreetingDto();
		if (strLanguageCode != null && strLanguageCode.equalsIgnoreCase("Th")) {
			strGreetingLanguage = getGreetingTh();
			greetingDto.setSayGreeting(strGreetingLanguage);
		} else {
			strGreetingLanguage = getGreetingEn();
			greetingDto.setSayGreeting(strGreetingLanguage);
		}
		return greetingDto;
	}// end method

	public static String getGreetingEn() {
		String strGreetingEn = "Hello";
		GregorianCalendar time = new GregorianCalendar();
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int min = time.get(Calendar.MINUTE);
		int day = time.get(Calendar.DAY_OF_MONTH);
		int month = time.get(Calendar.MONTH) + 1;
		int year = time.get(Calendar.YEAR);

		System.out.println("The current time is \t" + hour + ":" + min);
		System.out.println("Today's date is \t" + month + "/" + day + "/" + year);

		if (hour < 12)
			strGreetingEn = "Good Morning!";
		else if (hour < 17 && !(hour == 12))
			strGreetingEn = "Good Afternoon";
		else if (hour == 12)
			strGreetingEn = "Good Noon";
		else
			strGreetingEn = "Good Evening";

		return strGreetingEn;
	}// if

	public static String getGreetingTh() { // or return String in your case
	
		String strGreetingTh = "สวัสดี";
		GregorianCalendar time = new GregorianCalendar();
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int min = time.get(Calendar.MINUTE);
		int day = time.get(Calendar.DAY_OF_MONTH);
		int month = time.get(Calendar.MONTH) + 1;
		int year = time.get(Calendar.YEAR);

		System.out.println("The current time is \t" + hour + ":" + min);
		System.out.println("Today's date is \t" + month + "/" + day + "/" + year);

		if (hour < 12)
			strGreetingTh = "สวัสดี ตอนเช้า!";
		else if (hour < 17 && !(hour == 12))
			strGreetingTh = "สวัสดี ตอนบ่าย";
		else if (hour == 12)
			strGreetingTh = "สวัสดี ตอนเที่ยง";
		else
			strGreetingTh = "สวัสดี ตอนเย็น";
		return strGreetingTh;
	}// end method



	/**
	 * @param args
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(GreetingMakerService.getGreetingLanguage("Th"));
	}
 */
}
