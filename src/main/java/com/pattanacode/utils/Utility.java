package com.pattanacode.utils;

import java.text.NumberFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.security.MessageDigest;

public class Utility {

	private final static Logger logger = LoggerFactory.getLogger(Utility.class);
	
	protected static String[] monthTHList = { "มกราคม", "กุมภาพันธ์", "มีนาคม",
			"เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน",
			"ตุลาคม", "พฤศจิกายน", "ธันวาคม" };
	protected static String[] monthENList = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	protected static String[] monthShortTHList = { "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.",
			"มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค." };
	protected static String[] monthShortENList = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" };
	protected static String[] numStr = { "ศูนย์", "หนึ่ง", "สอง", "สามͧ", "สี่", "ห้า", "หก",
			"เจ็ด", "แปด", "เก้า" };
	protected static String[] numDigit = { "", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน" };

	/**
	 * used for get date and time value in specify format
	 * 
	 * @param format String - dd : Day, MM : Month, yyyy : Year, HH : Hour, mm :
	 *               Minute, ss : Second
	 * @return String
	 */
	public static String getNow(String format) {
		GregorianCalendar calendar = new GregorianCalendar();
		String result = format;
		String dd = fillZero(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2);
		String MM = fillZero(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2);
		String yyyy = String.valueOf(calendar.get(Calendar.YEAR));
		String HH = fillZero(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2);
		String mm = fillZero(String.valueOf(calendar.get(Calendar.MINUTE)), 2);
		String ss = fillZero(String.valueOf(calendar.get(Calendar.SECOND)), 2);
		String ii = fillZero(String.valueOf(calendar.get(Calendar.MILLISECOND)), 2);
		String yy = yyyy.substring(2);
		result = replaceStr(result, "dd", dd);
		result = replaceStr(result, "MM", MM);
		result = replaceStr(result, "yyyy", yyyy);
		result = replaceStr(result, "yy", yy);
		result = replaceStr(result, "HH", HH);
		result = replaceStr(result, "mm", mm);
		result = replaceStr(result, "ss", ss);
		result = replaceStr(result, "ii", ii);
		return result;
	}

	/**
	 * used for get date and time value in "MM/dd/yyyy" format
	 * 
	 * @param src    String
	 * @param format String
	 * @return String
	 */
	public static String getDateFormat(String src, String format) {
		return getDateFormat(src, "MM/dd/yyyy", format);
	}

	/**
	 * used for change date format from source format to specify format
	 * 
	 * @param src       String
	 * @param srcFormat String
	 * @param newFormat String
	 * @return String
	 */
	public static String getDateFormat(String src, String srcFormat, String newFormat) {
		String dateString = src;
		String frm = srcFormat;
		String result = newFormat;
		Calendar calendar1 = new GregorianCalendar();
		Integer tmYear = 0;
		String dd = "";
		String MM = "";
		String yyyy = "";
		String HH = "";
		String mm = "";
		String ss = "";
		String tmCountry = Locale.getDefault().getCountry();
		String tmLanguage = Locale.getDefault().getLanguage();
		boolean bChangeLang = false;
		try {
			logger.info(tmCountry);
			logger.info(tmLanguage);
			if (!tmLanguage.equals("en")) {
				Locale.setDefault(new Locale("en", "US"));
				bChangeLang = true;
			}

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(frm);
			java.sql.Date date = new java.sql.Date(sdf.parse(dateString).getTime());
			calendar1.setTime(date);
			dd = fillZero(String.valueOf(calendar1.get(Calendar.DATE)), 2);
			MM = fillZero(String.valueOf(calendar1.get(Calendar.MONTH) + 1), 2);
			tmYear = calendar1.get(Calendar.YEAR);
			logger.info(dd);
			logger.info(MM);
			logger.info(tmYear.toString());
			if (tmYear > 2500) {
				tmYear -= 543;
			} else if (tmYear < 1900) {
				tmYear += 543;
			}
			yyyy = String.valueOf(tmYear);
			HH = fillZero(String.valueOf(calendar1.get(Calendar.HOUR_OF_DAY)), 2);
			mm = fillZero(String.valueOf(calendar1.get(Calendar.MINUTE)), 2);
			ss = fillZero(String.valueOf(calendar1.get(Calendar.SECOND)), 2);

			result = replaceStr(result, "dd", dd);
			result = replaceStr(result, "MM", MM);
			result = replaceStr(result, "yyyy", yyyy);
			result = replaceStr(result, "HH", HH);
			result = replaceStr(result, "mm", mm);
			result = replaceStr(result, "ss", ss);
			if (bChangeLang) {
				Locale.setDefault(new Locale(tmLanguage, tmCountry));
			}
		} catch (Exception e) {
			// result = null;
			// System.err.println("Utility.GetDateFormat Error : "+e.getMessage());
		}

		return result;
	}


	/**
	 * used for create Date object from string (yyyy-MM-dd , yyyyMMdd)
	 * 
	 * @param src       String
	 * @return Date
	 * @throws Exception 
	 */
	public static Date parseStringToDate(String src) throws Exception {
		
		Date result = null;
		SimpleDateFormat sdf = null;
		if(src.length()==8) {
			sdf = new SimpleDateFormat("yyyyMMdd");
		}
		else if(src.length()==10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			sdf = new SimpleDateFormat();
		}
		result = sdf.parse(src);

		return result;
	}
	
	/**
	 * used for create Date object from string (yyyy-MM-dd HHmmss , yyyyMMdd HHmmss)
	 * 
	 * @return Date
	 * @throws Exception 
	 */
	public static Date parseStringToDateTime(String date, String time) throws Exception {
		
		Date result = null;
		SimpleDateFormat sdf = null;
		if(date.length()==8) {
			sdf = new SimpleDateFormat("yyyyMMdd hhmmss");
		}
		else if(date.length()==10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hhmmss");
		}
		else {
			sdf = new SimpleDateFormat();
		}
		
		result = sdf.parse(date+" "+time);

		return result;
	}
	
	/**
	 * used for fill "0" in front of source value
	 * 
	 * @param src       int
	 * @param totLength int
	 * @return String
	 */
	public static String fillZero(int src, int totLength) {
		return fillZero(String.valueOf(src), totLength);
	}

	/**
	 * used for fill "0" in front of source value
	 * 
	 * @param src       String
	 * @param totLength int
	 * @return String
	 */
	public static String fillZero(String src, int totLength) {
		String tm = src;
		for (int i = 0; i < (totLength - src.length()); i++) {
			tm = "0" + tm;
		}
		return tm;
	}

	public static String parseNum2TextTH(String srcNum) {
		String result = "";
		String resultDec = "";
		long num = 0;
		int nFloat = 0;
		long nDigit = 10;
		long tmpNum = 0;
		int digit = 0;
		boolean isOneDigit = false;

		if (srcNum.indexOf(".") == -1) {
			num = Integer.parseInt(srcNum);
		} else {
			num = Integer.parseInt(srcNum.substring(0, srcNum.indexOf(".")));
			nFloat = Integer.parseInt(srcNum.substring(srcNum.indexOf(".") + 1, srcNum.length()));
			if (String.valueOf(nFloat).length() == 1) {
				nFloat *= 10;
			}
			if (nFloat > 99) {
				nFloat = (int) (nFloat / 10);
			}
		}
		if (String.valueOf(num).length() == 1)
			isOneDigit = true;

		tmpNum = num;
		for (int i = 0; i < String.valueOf(num).length(); i++) {
			digit = (i % 7);
			if (i > 6)
				digit++;
			result = getNumberStr((int) (tmpNum % nDigit), digit, isOneDigit) + result;
			tmpNum = (int) (tmpNum / nDigit);
		}
		result += "\uFFFD\u04B7";
		if (nFloat > 0) {
			tmpNum = nFloat;
			digit = 0;
			for (int i = 0; i < String.valueOf(nFloat).length(); i++) {
				digit = (i % 7);
				if (i > 6)
					digit++;
				resultDec = getNumberStr((int) (tmpNum % nDigit), (i % 7), isOneDigit) + resultDec;
				tmpNum = (int) (tmpNum / nDigit);
			}
			resultDec += "\u02B5\u04A7\uFFFD\uFFFD";
		} else {
			result += "\uFFFD\uFFFD\u01F9";
		}

		return (result + " " + resultDec).trim();
	}

	protected static String getNumberStr(int srcNum, int srcDigit, boolean bSingleDigit) {
		String result = "";

		if (srcNum == 0) {
			result += "";
		} else if (srcNum == 1 && srcDigit == 1) {
			result += "";
			result += numDigit[srcDigit];
		} else if (srcNum == 1 && srcDigit == 0 && !bSingleDigit) {
			result += "เอ็ด";
			result += numDigit[srcDigit];
		} else if (srcNum == 2 && srcDigit == 1) {
			result += "ยี่";
			result += numDigit[srcDigit];
		} else {
			result += numStr[srcNum];
			result += numDigit[srcDigit];
		}

		return result;
	}

	public static String getMonthTHName(int mNo) {
		return monthTHList[mNo - 1];
	}

	public static String getMonthENName(int mNo) {
		return monthTHList[mNo - 1];
	}

	public static String getShortMonthTHName(int mNo) {
		return monthShortTHList[mNo - 1];
	}

	public static String getShortMonthENName(int mNo) {
		return monthShortTHList[mNo - 1];
	}

	public static String getNumberFormat(float str) {
		return getNumberFormat(Float.toString(str));
	}

	public static String getNumberFormat(double str) {
		return getNumberFormat(Double.toString(str));
	}

	public static String swapColor(String src, String color1, String color2) {
		return (src.equals(color2)) ? color1 : color2;
	}

	public static String getNumberFormat(String str) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setMaximumFractionDigits(2);
		// numberFormat.setGroupingUsed(false);

		return numberFormat.format(Double.parseDouble(str));
	}

	public static String phoneFormater(String phoneNumber) {
		if (phoneNumber.length() != 9) {
			return phoneNumber;
		}
		String newph1 = phoneNumber.substring(0, 1);
		String newph2 = phoneNumber.substring(1, 5);
		String newph3 = phoneNumber.substring(5, 9);
		String newph = newph1 + "-" + newph2 + "-" + newph3;
		return newph;
	}

	public static String encode2Thai(String src) throws java.io.IOException {
		if (src == null) {
			return "";
		}
		if (src.length() <= 0) {
			return "";
		}
		return new String(src.getBytes("ISO8859_1"), "MS874");
	}

	public static long getLong(String in) {
		return getLong(in, 0);
	}

	public static long getLong(String in, long ret) {
		long tm = 0;

		try {
			tm = Long.parseLong(in);
		} catch (Exception e) {
			tm = ret;
		}

		return tm;
	}

	public static int getInt(String in) {
		return getInt(in, 0);
	}

	public static int getInt(String in, int ret) {
		int tm = 0;

		try {
			tm = Integer.parseInt(in);
		} catch (Exception e) {
			tm = ret;
		}

		return tm;
	}

	public static float getFloat(String in) {
		return getFloat(in, 0);
	}

	public static float getFloat(String in, float ret) {
		float tm = 0;

		try {
			tm = Float.parseFloat(in);
		} catch (Exception e) {
			tm = ret;
		}

		return tm;
	}

	public static String getAppendString(String delimeter, String append, boolean isEmpty){
		return (isEmpty)? append : delimeter + append;
	}


	public static String getString(String in) {
		return (in == null ? "" : in);
	}

	public static String getString(Object in) {
		return getString(String.valueOf(in));
	}

	public static String getString(int in) {
		try {
			return getString(String.valueOf(in));
		} catch (Exception e) {
			return "0";
		}
	}

	public static String getString(float in) {
		try {
			return getString(String.valueOf(in));
		} catch (Exception e) {
			return "0";
		}
	}

	public static String getString(float in, String ret) {
		try {
			return getString(String.valueOf(in), ret);
		} catch (Exception e) {
			return "0";
		}
	}

	public static boolean isEng(String in) {
		if ((in == null) || (in.length() == 0)) {
			return false;
		}
		char tmpChk = in.charAt(0);
		// logger.info(tmpChk);
		if (tmpChk <= 126) {
			return true;
		} else {
			return false;
		}
	}

	public static String getString(String in, String retVal) {
		return (((in == null) || (in.equals(""))) ? retVal : in);
	}

	public static String getTitle(String in, String title) {
		return (((in == null) || (in.equals(""))) ? "" : title + " " + in);
	}

	public static String[] getString(String[] in) {
		String[] arrReturn = null;
		return (in == null ? arrReturn : in);
	}

	public static String[] Split2Array(String src, String delim) {
		String[] result = null;

		StringTokenizer tokens = new StringTokenizer(src, delim);
		result = new String[tokens.countTokens()];
		for (int i = 0; tokens.hasMoreTokens(); i++) {
			result[i] = tokens.nextToken();
		}
		return result;
	}

	public static String Unicode2ASCII(String unicode) { // \uFFFD\u0167 Unicode \uFFFD\uFFFD ASCII
		StringBuffer ascii = new StringBuffer(unicode); // \uFFFD\uFFFD\u02F9\uFFFD\uFFFD\uFFFD\u9E77\uFFFD\uFFFD\uFFFD\uFFFD\u04B9\uFFFD\uFFFD\uFFFD\u01E4\uFFFD\uFFFD\uFFFD
		int code;
		for (int i = 0; i < unicode.length(); i++) { // \uFFFD\u067B\uFFFD\uFFFD\u04A8\u04F9\u01F9\uFFFD\uFFFD\uFFFD\uFFFD\u0461\uFFFD\uFFFD
			code = (int) unicode.charAt(i); // \uFFFD\uFFFD\u04B9\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u02B7\uFFFD\uFFFD\uFFFD\u0435\uFFFD\uFFFD\uFFFD\u0461\uFFFD\uFFFD
			if ((0xE01 <= code) && (code <= 0xE5B)) { // \uFFFD\uFFFD\u01E8\uFFFD\u037A\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u3E6A\uFFFD\u01E7\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u00A2\u0367
														// Unicode \uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD
				ascii.setCharAt(i, (char) (code - 0xD60)); // \uFFFD\u04A1\uFFFD\uFFFD\uFFFD\u0167\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u3E6A\uFFFD\u01E7\uFFFD\u0367
															// ASCII
			}
		}
		return ascii.toString(); // \uFFFD\u0167\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u0161\uFFFD\u047A\uFFFD\uFFFD\uFFFD\u1EBA
									// String \uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u04B9\uFFFD\uFFFD\uFFFD\uFFFD
	}

	public static String ASCII2Unicode(String ascii) {
		StringBuffer unicode = new StringBuffer(ascii);
		int code;
		for (int i = 0; i < ascii.length(); i++) {
			code = (int) ascii.charAt(i);
			if ((0xA1 <= code) && (code <= 0xFB)) { // \uFFFD\uFFFD\u01E8\uFFFD\u037A\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u3E6A\uFFFD\u01E7\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u00A2\u0367
													// ASCII \uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD
				unicode.setCharAt(i, (char) (code + 0xD60)); // \uFFFD\u04A1\uFFFD\uFFFD\uFFFD\u0167\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u3E6A\uFFFD\u01E7\uFFFD\u0367
																// Unicode
			}
		}
		return unicode.toString(); // \uFFFD\u0167\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u0161\uFFFD\u047A\uFFFD\uFFFD\uFFFD\u1EBA
									// String \uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\u04B9\uFFFD\uFFFD\uFFFD\uFFFD
	}

	public static String checkUserOperation(String roles, String rolename, String result) {
		String retVal = "";
		// logger.info("checkUserOperation()");
		// logger.info(roles.indexOf(rolename));
		if (roles.indexOf(rolename) >= 0) {
			retVal = result;
		} else {
			retVal = "";
		} // of if

		return retVal;
	} // of method

	public static boolean isExcute(String roles, String rolename) {
		if (roles.indexOf(rolename) >= 0) {
			return true;
		} else {
			return false;
		} // of if
	} // of method

	public static String formatDate(String value) { // Change Eng Date(yyyy/MM/dd) To Thai Date(dd/MM/yyyy)
		if (value == null || value.length() < 10) {
			value = "";
		} else {
			value = value.substring(8, 10) + "/" + value.substring(5, 7) + "/"
					+ String.valueOf(
							((Integer.parseInt(value.substring(0, 4)) > 2500) ? Integer.parseInt(value.substring(0, 4))
									: Integer.parseInt(value.substring(0, 4)) + 543));
		}
		return value;
	}

	public static String formatDateStrTH(String value) { // Change Eng Date(yyyy/MM/dd) To Thai Date(dd/MM/yyyy)
		if (value == null || value.length() < 10) {
			value = "";
		} else {
			value = String.valueOf(Integer.parseInt(value.substring(8, 10))) + " "
					+ getShortMonthTHName(Integer.parseInt(value.substring(5, 7))) + " "
					+ String.valueOf(
							((Integer.parseInt(value.substring(0, 4)) > 2500) ? Integer.parseInt(value.substring(0, 4))
									: Integer.parseInt(value.substring(0, 4)) + 543));
		}
		return value;
	}

	public static String formatDateDBCanNull(String value) { // Change Thai Date(dd/MM/yyyy) To DB Date(yyyy-MM-dd)
		if (value == null || value.length() < 10) {
			value = null;
		} else {
			value = String
					.valueOf(((Integer.parseInt(value.substring(6, 10)) < 2500)
							? Integer.parseInt(value.substring(6, 10))
							: Integer.parseInt(value.substring(6, 10)) - 543))
					+ "/" + value.substring(3, 5) + "/" + value.substring(0, 2);
		}
		return value;
	}

	public static String formatDateDB(String value) { // Change Thai Date(dd/MM/yyyy) To DB Date(yyyy-MM-dd)
		if (value == null || value.length() < 10) {
			value = "";
		} else {
			value = String
					.valueOf(((Integer.parseInt(value.substring(6, 10)) < 2500)
							? Integer.parseInt(value.substring(6, 10))
							: Integer.parseInt(value.substring(6, 10)) - 543))
					+ "-" + value.substring(3, 5) + "-" + value.substring(0, 2);
		}
		return value;
	}

	public static String getTodayDDMMYYYY() {
		return getTodayDDMMYYYY("/");
	}

	public static String getTodayDDMMYYYY(String li) {
		StringBuffer tmp = new StringBuffer();
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String dd = "";
		String mm = "";
		String yy = "";
		calendar.setTime(trialTime);

		dd = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if (dd.length() == 1) {
			dd = "0" + dd;
		}
		mm = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if (mm.length() == 1) {
			mm = "0" + mm;
		}
		if (calendar.get(Calendar.YEAR) < 2500) {
			yy = String.valueOf(calendar.get(Calendar.YEAR) + 543);
		} else {
			yy = String.valueOf(calendar.get(Calendar.YEAR));
		}

		tmp.append(dd);
		tmp.append(li);
		tmp.append(mm);
		tmp.append(li);
		tmp.append(yy);

		return tmp.toString();
	}

	public static String getTHTodayDDMMYYYY() {
		StringBuffer tmp = new StringBuffer();
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String dd = "";
		String mm = "";
		String yy = "";
		calendar.setTime(trialTime);

		dd = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if (dd.length() == 1) {
			dd = "0" + dd;
		}
		mm = monthShortTHList[calendar.get(Calendar.MONTH)];
		if (calendar.get(Calendar.YEAR) < 2500) {
			yy = String.valueOf(calendar.get(Calendar.YEAR) + 543);
		} else {
			yy = String.valueOf(calendar.get(Calendar.YEAR));
		}

		tmp.append(dd);
		tmp.append(" ");
		tmp.append(mm);
		tmp.append(" ");
		tmp.append(yy);

		return tmp.toString();
	}

	public static String getTodayMMDDYYYY(String li) {
		StringBuffer tmp = new StringBuffer();
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String dd = "";
		String mm = "";
		String yy = "";
		calendar.setTime(trialTime);

		dd = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if (dd.length() == 1) {
			dd = "0" + dd;
		}
		mm = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if (mm.length() == 1) {
			mm = "0" + mm;
		}
		if (calendar.get(Calendar.YEAR) < 2500) {
			yy = String.valueOf(calendar.get(Calendar.YEAR) + 543);
		} else {
			yy = String.valueOf(calendar.get(Calendar.YEAR));
		}

		tmp.append(mm);
		tmp.append(li);
		tmp.append(dd);
		tmp.append(li);
		tmp.append(yy);

		return tmp.toString();
	}

	public static String getTodayDD() {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String dd = "";
		calendar.setTime(trialTime);

		dd = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if (dd.length() == 1) {
			dd = "0" + dd;
		}

		return dd;
	}

	public static String getTodayMM() {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String mm = "";
		calendar.setTime(trialTime);

		mm = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		if (mm.length() == 1) {
			mm = "0" + mm;
		}

		return mm;
	}

	public static String getTodayYYYY() {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String yy = "";
		calendar.setTime(trialTime);

		if (calendar.get(Calendar.YEAR) < 2500) {
			yy = String.valueOf(calendar.get(Calendar.YEAR) + 543);
		} else {
			yy = String.valueOf(calendar.get(Calendar.YEAR));
		}

		return yy;
	}

	public static String getTodayEYYYY() {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String yy = "";
		calendar.setTime(trialTime);

		if (calendar.get(Calendar.YEAR) < 2500) {
			yy = String.valueOf(calendar.get(Calendar.YEAR));
		} else {
			yy = String.valueOf(calendar.get(Calendar.YEAR) - 543);
		}

		return yy;
	}

	public static String replaceStr(String s, String f, String r) {
		if (s == null) {
			return s;
		}
		if (f == null) {
			return s;
		}
		if (r == null) {
			r = "";

		}
		int index01 = s.indexOf(f);
		while (index01 != -1) {
			s = s.substring(0, index01) + r + s.substring(index01 + f.length());
			index01 += r.length();
			index01 = s.indexOf(f, index01);
		}
		return s;
	}

	public static String replaceStr(String s, int f, String r) {
		if (s == null) {
			return s;
		}
		// if (f == null) return s;
		if (r == null) {
			r = "";

		}
		int index01 = s.indexOf(f);
		while (index01 != -1) {
			s = s.substring(0, index01) + r + s.substring(index01 + 1);
			index01 += r.length();
			index01 = s.indexOf(f, index01);
		}
		return s;
	}

	public static String removeReturn(String s) {
		return replaceStr(replaceStr(s, 13, ""), 10, "\\n");
	}

	public static String removeReturnStr(String s) {
		return replaceStr(s, "\\n", " ");
	}

	public static String selCompare(String s1, String s2) {
		if (s1.trim().equals(s2.trim())) {
			return " selected";
		} else {
			return "";
		}
	}

	public static String isOpened(String src) {
		String result = "";

		if ((src == null) || (src.trim().length() == 0)) {
			result = "Open";
		} else if (src.trim().equalsIgnoreCase("1")) {
			result = "Closed";
		} else if (src.trim().equalsIgnoreCase("2")) {
			result = "Canceled";
		}

		return result;
	}

	public static String isChecked(String chk, String exist) {
		return (getString(chk).equals(exist)) ? "checked" : "";
	}


	/*
	 * public static int compareDate(String stDate, String fnDate) { int result = 0;
	 * 
	 * return result; }
	 */

	public static String compareDate(String d1, String d2) { // Format DATE = YYYY-MM-DD
		String msg = "";
		String symbol = "";
		int tmp1 = 0;
		int tmp2 = 0;

		tmp1 = Integer.parseInt(d1.substring(0, 4));
		tmp2 = Integer.parseInt(d2.substring(0, 4));
		if (tmp1 < tmp2) {
			symbol = "<";
			msg = String.valueOf(tmp2 - tmp1) + "\uFFFD\uFFFD";
		} else if (tmp1 > tmp2) {
			symbol = ">";
			msg = String.valueOf(tmp1 - tmp2) + "\uFFFD\uFFFD";
		}

		tmp1 = Integer.parseInt(d1.substring(5, 7));
		tmp2 = Integer.parseInt(d2.substring(5, 7));
		if (tmp1 < tmp2) {
			if (symbol.trim().length() == 0)
				symbol = ">";
			msg = String.valueOf(tmp2 - tmp1) + "\uFFFD\uFFFD\u0379 " + msg;
		} else if (tmp1 > tmp2) {
			if (symbol.trim().length() == 0)
				symbol = "<";
			msg = String.valueOf(tmp1 - tmp2) + "\uFFFD\uFFFD\u0379 " + msg;
		}

		tmp1 = Integer.parseInt(d1.substring(8, 10));
		tmp2 = Integer.parseInt(d2.substring(8, 10));
		if (tmp1 < tmp2) {
			if (symbol.trim().length() == 0)
				symbol = ">";
			msg = String.valueOf(tmp2 - tmp1) + "\uFFFD\u0479 " + msg;
		} else if (tmp1 > tmp2) {
			if (symbol.trim().length() == 0)
				symbol = "<";
			msg = String.valueOf(tmp1 - tmp2) + "\uFFFD\u0479 " + msg;
		}
		if (msg.trim().length() == 0)
			msg = "0 \uFFFD\u0479";
		else
			msg = symbol + msg;
		return msg;
	}

	public static String getEqualSelectInput(int s1, int s2) {
		return getEqualSelectInput(String.valueOf(s1), String.valueOf(s2));
	}

	public static String getEqualSelectInput(String s1, String s2) {
		return ((s1.equalsIgnoreCase(s2)) ? "selected" : "");
	}

	public static int getTotalPage(int nTotalItems, int nRange) {
		int result = 0;

		try {
			result = (int) (nTotalItems / nRange);
			if ((nTotalItems % nRange) > 0) {
				result++;
			}
		} catch (Exception e) {
			result = 0;
		}

		return result;
	}

	public static String getTimeStamp(String frm) {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		String result = "";
		calendar.setTime(trialTime);

		result = frm;
		result = replaceStr(result, "yyyy", String.valueOf(calendar.get(Calendar.YEAR)));
		result = replaceStr(result, "MM", fillChar(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2, "0"));
		result = replaceStr(result, "dd", fillChar(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2, "0"));
		result = replaceStr(result, "HH", fillChar(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
		result = replaceStr(result, "mm", fillChar(String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0"));
		result = replaceStr(result, "ss", fillChar(String.valueOf(calendar.get(Calendar.SECOND)), 2, "0"));

		return result;
	}

	public static String fillChar(String src, int totPoint, String filler) {
		StringBuffer tm = new StringBuffer();
		for (int i = src.length(); i < totPoint; i++) {
			tm.append(filler);
		}
		tm.append(src);

		return tm.toString();
	}

	public static String fillCharAfter(String src, int totPoint, String filler) {
		StringBuffer tm = new StringBuffer();
		tm.append(src);
		for (int i = src.length(); i < totPoint; i++) {
			tm.append(filler);
		}

		return tm.toString();
	}

	public static String dumpHexId(byte[] myId) {
		String result = "";
		result = "X'";
		for (int i = 0; i < myId.length; i++) {
			char b = (char) (myId[i] & 0xFF);
			if (b < 0x10) {
				result += "0";
			}
			result += ((String) (Integer.toHexString(b)).toUpperCase());
		}
		result += "'";

		return result;
	}

	public static boolean isInteger(String src) {
		try {
			int tmp = Integer.parseInt(src);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isLong(String src) {
		try {
			long tmp = Long.parseLong(src);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isEmpty(String src) {
		return ((src == null) || (src.trim().length() <= 0));
	}

	public static boolean isAlphabet(String src) {
		return src.matches("[a-zA-Z]+");
	}	

	public static boolean validateJavaDate(String strDate) {
		try {
			/* Check if date is 'null' */
			if (strDate.trim().equals("")) {
				return false;
			} else {
				
				SimpleDateFormat sdfrmt;
				
				if(strDate.length()==8) {
					sdfrmt = new SimpleDateFormat("yyyyMMdd");
				}
				else if(strDate.length()==10) {
					sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
				}
				else {
					return false;
				}
				
				sdfrmt.setLenient(false);

				Date javaDate = sdfrmt.parse(strDate);

				return true;
			}
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static Date stringToJavaDate(String sourceDate, String sourceFormat) {
		try {
			if (sourceDate.trim().equals("")) {
				return null;
			}
			else {
				SimpleDateFormat formatter = new SimpleDateFormat(sourceFormat);				
				formatter.setLenient(false);
				return formatter.parse(sourceDate);		
			}
		} catch (ParseException e) {			
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String getJavaDateAsyyyyMMdd(Date d) {
		String date = ""+d.getDate();
		String month = ""+(d.getMonth()+1);
		String year = ""+(d.getYear()+1900);

		date = date.length()<2 ? "0"+date : date;
		month = month.length()<2 ? "0"+month : month;
		
		return year+"-"+month+"-"+date;
	}
	
	@SuppressWarnings("deprecation")
	public static String getJavaDateTimeAsyyyyMMddHHmmss(Date d) {
		String date = ""+d.getDate();
		String month = ""+(d.getMonth()+1);
		String year = ""+(d.getYear()+1900);
		
		String hour = ""+d.getHours();
		String min = ""+d.getMinutes();
		String sec = ""+d.getSeconds();

		date = date.length()<2 ? "0"+date : date;
		month = month.length()<2 ? "0"+month : month;
		hour = hour.length()<2 ? "0"+hour : hour;
		min = min.length()<2 ? "0"+min : min;
		sec = sec.length()<2 ? "0"+sec : sec;
		
		return year+"-"+month+"-"+date+" "+hour+":"+min+":"+sec;
	}
	
	public static Date getNextWeek(int startDayInWeek) throws Exception {
		Calendar nextWeek = Calendar.getInstance();

		nextWeek.add(Calendar.DATE, 7);
		nextWeek.set(Calendar.DAY_OF_WEEK,startDayInWeek);
		nextWeek.set(Calendar.HOUR_OF_DAY, 0);
		nextWeek.set(Calendar.MINUTE, 0);
		nextWeek.set(Calendar.SECOND, 0);
		nextWeek.set(Calendar.MILLISECOND, 0);
		
		return nextWeek.getTime();
	}

	public static Date getStartDayofThatWeek(Date date, int week, int startDayInWeek) throws Exception {
		Calendar day = Calendar.getInstance();
		
		day.setTime(date);
		day.add(Calendar.DATE, 7*week);
		day.set(Calendar.DAY_OF_WEEK,startDayInWeek);
		day.set(Calendar.HOUR_OF_DAY, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		day.set(Calendar.MILLISECOND, 0);
				
		return day.getTime();
	}
	
	public static Date getUTCTime(Date ict) throws Exception {
		Calendar utc = Calendar.getInstance();
		utc.setTime(ict);
		utc.set(Calendar.HOUR_OF_DAY, 7);
		return utc.getTime();
	}
	
	@SuppressWarnings("deprecation")
	public static Date adjustDateTime(Date date, int d, int hour, int min, int sec) throws Exception{
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, sec);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DATE, date.getDate()+d);
		
		return c.getTime();
	}

	public static Date adjustDateTime(Date date, int field, int adjust){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, adjust);
		return c.getTime();
	}
	
	public static boolean isNumList(String string) {
		String[] spl = string.split(",");
		for (String s : spl) {
			if(!Utility.isLong(s)) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Long> parseNumList(String string) {
		String[] spl = string.split(",");
		List<Long> num = new ArrayList<Long>();
		for (String s : spl) {
			num.add(Long.parseLong(s));
		}
		return num;
	}
     
	public static String targetDateisBeforeAfter(Date target,Date compare) {
		if(target.before(compare)) {
			return "before";
		}else if (target.after(compare)) {
			return "after";
		}else if (target.equals(compare)) {
			return "equal";
		}else {
			return "error";
		}
	}
	public static Date convertUnixTimeStamp (Long unix_seconds) {
		return new Date(unix_seconds*1000L);
	}
	public static Date convertDateTimeZone(Date target,String Zone) throws ParseException {
		SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
 	   jdf.setTimeZone(TimeZone.getTimeZone(Zone));  //GMT+7
 	   String java_date = jdf.format(target);
 	   return jdf.parse(java_date);
	}
	public static String convertStrDateTimeZoneNow(Date target,String Zone,String Format) throws ParseException {
		SimpleDateFormat jdf = new SimpleDateFormat(Format);
		jdf.setTimeZone(TimeZone.getTimeZone(Zone));  //GMT+7
		String java_date = jdf.format(target);
		return java_date;
	}
	public static Long getMilisecTimeDiff(Date target,Date compare) {
		return target.getTime()-compare.getTime();
	}

	public  static int getDayDiffByDate(Date target,Date compare){
		long diff =  target.getTime()-compare.getTime();
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		return diffDays;
	}
	public static final boolean isDouble(String itemPrice)
	{
		Pattern pattern =Pattern.compile("^[-+]?\\d+(\\.{0,1}(\\d+?))?$");
		Matcher matcher = pattern.matcher(itemPrice);
		boolean result=matcher.matches();
		return result;

	}
	/*
	 * public static void main(String[] args) { String tmSrcDate = "29/02/2008";
	 * 
	 * logger.info("Source : "+tmSrcDate);
	 * logger.info("Result : "+Utility.GetDateFormat(tmSrcDate, "dd/MM/yyyy",
	 * "yyyyMMdd")); }
	 */

	public Utility() {
	}
	
	/**
	 * 
	 * @param strEmail
	 * @return
	 */
	public static String getUserNameFromEmail(String strEmail) {
		String strSplitFirst ="";
		if(strEmail!=null && strEmail.length()>0) {
			strSplitFirst = strEmail.substring(0,strEmail.indexOf("@"));
			//String[] For_split_email=strEmail.split("[@|\\.|_]");
			
		}//if
		return strSplitFirst;
	}//getUserNameFromEmail

}//end class
