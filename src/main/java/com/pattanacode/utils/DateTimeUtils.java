package com.pattanacode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DateTimeUtils {

	
	public static Date getUTCTime(Date date) throws Exception {
		Calendar utc = Calendar.getInstance();
		utc.setTime(date);
		utc.set(Calendar.HOUR_OF_DAY, 7);
		return utc.getTime();
	}
	
    public static String dateToString(Date date, String pattern) {
            if (StringUtils.isBlank(pattern)) {
                pattern = "dd-MM-yyyy";
            }
            return date != null ? (new SimpleDateFormat(pattern, Locale.US)).format(date) : null;
        }

        public static Date stringToDate(String date, String pattern) {
            try {
                if (StringUtils.isBlank(pattern)) {
                    pattern = "dd-MM-yyyy";
                }
                return date != null ? (new SimpleDateFormat(pattern, Locale.US)).parse(date.trim()) : null;
            } catch (ParseException ex) {
            }
            return null;
        }

        public static String getCurrentDate(){
            return dateToString(new Date(), "yyyy-MM-dd");
        }

        public static String getBeforeCurrentDate(int nDays){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -nDays); // I just want date before nDays days. you can give that you want.
            return dateToString(new Date(cal.getTimeInMillis()), "yyyy-MM-dd");
            //SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd"); // you can specify your format here...
            // return s.format(new Date(cal.getTimeInMillis()));
        }
        // public static String convertLongToDateStr(long l) {
        // DateTime dt = new DateTime(l, DateTimeZone.UTC);
        // String h = dt.getHourOfDay() == 0 ? "" : String.valueOf(dt.getHourOfDay() +
        // "h ");
        // String m = dt.getMinuteOfHour() == 0 ? "" :
        // String.valueOf(dt.getMinuteOfHour() + "m ");
        // String s = dt.getSecondOfMinute() == 0 ? "" :
        // String.valueOf(dt.getSecondOfMinute() + "s ");
        // return h + m + s + dt.getMillisOfSecond() + "ms";
        // }


        public static Date getSystemDate() {
            Calendar c = new GregorianCalendar(Locale.US);
            return c.getTime();
        }


}
