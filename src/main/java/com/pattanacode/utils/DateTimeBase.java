package com.pattanacode.utils;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2547</p>
 * <p>Company: </p>
 *
 * @author Mr.
 * @version 1.0
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.GregorianCalendar;


public abstract class DateTimeBase {


    public static final String ENGLISH_SHORT_DATE = "yyyy-MM-dd";
    public static final String ENGLISH_SHORT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String THAI_SHORT_DATE = "dd/MM/yyyy";
    public static final String THAI_SHORT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
    public static final String SHORT_TIME = "HH:mm";
    public static final String FULL_TIME = "HH:mm:ss";

    // Extension
    public static final int DATE = 1;
    public static final int WEEK = 2;
    public static final int MONTH = 3;
    public static final int YEAR = 4;
    public static int DATEDIFF = 0;
    public static int MONTHDIFF = 0;
    public static int YEARDIFF = 0;
    public static final String[] LONG_MONTHNAMES_EN = {"January", "February", "March", "Apil", "May", "June", "July", "August", "September", "October", "November", "December"};
    public static final String[] LONG_MONTHNAMES_TH = {"¡°√“§¡", "°ÿ¡¿“æ—π∏Ï", "¡’π“§¡", "‡¡…“¬π", "æƒ…¿“§¡", "¡‘∂ÿπ“¬π", "°√°Æ“§¡", " ‘ßÀ“§¡", "°—π¬“¬π", "µÿ≈“§¡", "æƒ»®‘°“¬π", "∏—π«“§¡"};

    private String strDatabasType;

    public DateTimeBase() {
    }

    /**
     * @return String of Current Date in format yyyy-MM-dd
     */
    public static String getStrDateEN() {
        String strDateEN = "";
        try {
            strDateEN = getStrDateEN(new java.util.Date());
        } catch (
                Exception e) {
            e.printStackTrace();

        }
        return strDateEN;

    }

    /**
     * @return String of Current Date in format yyyyMMdd
     */
    public static String getStrDateENYYYYMMDD() {
        String strDateEN = "";
        try {
            strDateEN = getStrDateENYYYYMMDD(new java.util.Date());
        } catch (
                Exception e) {
            e.printStackTrace();

        }
        return strDateEN;

    }

    /**
     * @return String of Current Date in format dd/MM/yyyy
     */
    public static String getStrDateTH() throws Exception {
        return getStrDateTH(new java.util.Date());
    }

    /**
     * @return String of Current Date in format yyyy-MM-dd HH:mm:ss
     */
    public static String getStrDateTimeEN() throws Exception {
        return getStrDateTimeEN(new java.util.Date());
    }

    /**
     * @return String of Current Date in format yyyy-MM-dd HH:mm:ss
     */
    public static String getStrDateTimeTH() throws Exception {
        return getStrDateTimeTH(new java.util.Date());
    }

    /**
     * @param date a java.util.Date
     * @return String of Date in format yyyy-MM-dd
     */
    public static String getStrDateEN(Date date) {
        String strDateEn = "";
        try {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            strDateEn = simpledateformat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDateEn;
    }


    /**
     * @param date a java.util.Date
     * @return String of Date in format yyyyMMdd
     */
    public static String getStrDateENYYYYMMDD(Date date) {
        String strDateEn = "";
        try {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            strDateEn = simpledateformat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDateEn;
    }

    /**
     * @param date a java.util.Date
     * @return String of Date in format dd/MM/yyyy
     */
    public static String getStrDateTH(Date date) throws Exception {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
        return simpledateformat.format(date);
    }

    /**
     * @param date a java.util.Date
     * @return String of Date in format yyyy-MM-dd
     */
    public static String getStrDateTimeEN(Date date) throws Exception {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return simpledateformat.format(date);
    }


    /**
     * @param date a java.util.Date
     * @return String of Date in format dd/MM/yyyy HH:mm:ss
     */
    public static String getStrDateTimeTH(Date date) throws Exception {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"));
        return simpledateformat.format(date);
    }

    /**
     * @param strThaiDate in format dd/MM/yyyy (Thai Year)
     * @return String of Date in format yyyy-MM-dd (Eng Year)
     */
    public static String setStrDateToEN(String strThaiDate) throws Exception {
        if (strThaiDate == null)
            return strThaiDate;
        if (strThaiDate.length() == 0)
            return "";
        return setStrDateToEN(strThaiDate, ENGLISH_SHORT_DATE);
    }

    public static String setStrDateToEN(String strThaiDate, String strFormat) throws Exception {
        if (strThaiDate == null)
            return strThaiDate;
        if (strThaiDate.length() == 0)
            return "";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
        SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat, Locale.ENGLISH);
        return simpledateformat.format(df.parse(strThaiDate));
    }

    public static String setStrDateToTH(String strDateTime) throws Exception {
        return setStrDateToTH(strDateTime, THAI_SHORT_DATE);
    }

    public static String setStrDateToTH(String strDateTime, String strFormat) throws Exception {
        String strReturn = "";
        if (strDateTime != null && !strDateTime.equals("")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat, new Locale("th", "TH"));
            strReturn = simpledateformat.format(df.parse(strDateTime));
        } else {
            strReturn = strDateTime;
        }
        return strReturn;
    }

    public static String setStrDateTimeToEN(String strThaiDateTime) throws Exception {
        String strReturn = "";
        if (strThaiDateTime != null && !strThaiDateTime.equals("")) {
            strReturn = setStrDateTimeToEN(strThaiDateTime, ENGLISH_SHORT_DATE_TIME);
        } else {
            strReturn = strThaiDateTime;
        }
        return strReturn;
    }

    /**
     * @param strThaiDateTime in format dd/MM/yyyy HH:mm:ss (Thai Year)
     * @return String of Date in format yyyy-MM-dd HH:mm:ss (Eng Year)
     */
    public static String setStrDateTimeToEN(String strThaiDateTime, String strFormat) throws Exception {
        String strReturn = "";
        if (strThaiDateTime != null && !strThaiDateTime.equals("")) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"));
            SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat, Locale.ENGLISH);
            strReturn = simpledateformat.format(df.parse(strThaiDateTime));
        } else {
            strReturn = strThaiDateTime;
        }
        return strReturn;
    }

    public static String setStrDateTimeToTH(String strDateTime) throws Exception {
        return setStrDateTimeToTH(strDateTime, THAI_SHORT_DATE_TIME);
    }

    public static String setStrDateTimeToTH(String strDateTime, String strFormat) throws Exception {
        String strReturn = "";
        if (strDateTime != null && !strDateTime.equals("")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat, new Locale("th", "TH"));
            strReturn = simpledateformat.format(df.parse(strDateTime));
        } else {
            strReturn = strDateTime;
        }
        return strReturn;
    }

    /**
     * @param strDateTime in format dd/MM/yyyy HH:mm:ss (Thai) or yyyy-MM-dd HH:mm:ss (Eng)
     * @return String of Time in format HH:mm
     */
    public static String getStrTime(String strDateTime) throws Exception {
        return getStrTime(strDateTime, SHORT_TIME);
    }

    public static String getStrTime(String strDateTime, String strFormat) throws Exception {
        String strReturn = "";
        if (strDateTime.indexOf("/") != -1) { // delimeter is /
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"));
            SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat, Locale.ENGLISH);
            strReturn = simpledateformat.format(df.parse(strDateTime));
        } //if
        if (strDateTime.indexOf("-") != -1) { // delimeter is -
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat, Locale.ENGLISH);
            strReturn = simpledateformat.format(df.parse(strDateTime));
        } //if
        return strReturn;
    }

    // Extension

    /**
     * @return String of Current Date in format dd
     */
    public static String getDay() throws Exception {
        return getDay(new java.util.Date(), Locale.ENGLISH);
    }

    /**
     * @return String of Current Date in format MM
     */
    public static String getMonth() throws Exception {
        return getMonth(new java.util.Date(), Locale.ENGLISH);
    }


    /**
     * @return String of Current Date in format yyyy (ENG)
     */
    public static String getYear() throws Exception {
        return getYear(new java.util.Date(), Locale.ENGLISH);
    }

    /**
     * @return String of Current Date in format yyyy (Thai)
     */
    public static String getYearTH() throws Exception {
        return getYear(new java.util.Date(), new Locale("th", "TH"));
    }

    public static String getDay(Date date, Locale locale) throws Exception {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd", locale);
        return simpledateformat.format(date);
    }

    public static String getMonth(Date date, Locale locale) throws Exception {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("MM", locale);
        return simpledateformat.format(date);
    }

    public static String getYear(Date date, Locale locale) throws Exception {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy", locale);
        return simpledateformat.format(date);
    }

    /**
     * @param strDate in format dd/mm/yyyy (Thai Year) or yyyy/mm/dd (Eng Year)
     * @param strFromLang - Language of strDate (‡™Ëπ TH À√◊Õ EN)
     * @param strToLang - Transform to Language (‡™Ëπ TH À√◊Õ EN)
     * @param strDelimeter - µ—«§—Ëπ (‡™Ëπ /)  ”À√—∫ strDate
     * @return String of ThaiDate that added (numDays, numMonths, numYears)
     */
    public static String getYear(String strDate, String strFromLang, String strToLang, String strDelimeter) throws Exception {
        String strReturn = "";
        if (strDate != null && strDate.length() > 0) {
            String[] strs = strDate.split(strDelimeter);
            if (strFromLang.equalsIgnoreCase("TH")) {
                strReturn = strs[2];
                if (strToLang.equalsIgnoreCase("EN"))
                    strReturn = (Integer.parseInt(strReturn) - 543) + "";
            }//if
            if (strFromLang.equalsIgnoreCase("EN")) {
                strReturn = strs[0];
                if (strToLang.equalsIgnoreCase("TH"))
                    strReturn = (Integer.parseInt(strReturn) + 543) + "";
            }//if
        }//if
        return strReturn;
    }

    public static String getOptionMonthEN(String strSelectedData) {
        return getOptionMonth(strSelectedData, "EN");
    }//fn


    public static String getOptionMonth(String strSelectedData, String strLANG) {
        StringBuffer temp = new StringBuffer();
        int nSelectedData = 0;
        if (strSelectedData != null && strSelectedData.length() > 0)
            nSelectedData = Integer.parseInt(strSelectedData);
        if (strLANG.equals("TH")) {
            for (int i = 0; i < LONG_MONTHNAMES_TH.length; i++) {
                temp.append("<option value=\"" + (i + 1) + "\"" + ((nSelectedData == i + 1) ? " selected" : "") + ">" + LONG_MONTHNAMES_TH[i] + "</option>");
            }//for
        }//if
        if (strLANG.equals("EN")) {
            for (int i = 0; i < LONG_MONTHNAMES_EN.length; i++) {
                temp.append("<option value=\"" + (i + 1) + "\"" + ((nSelectedData == i + 1) ? " selected" : "") + ">" + LONG_MONTHNAMES_EN[i] + "</option>");
            }//for
        }//if
        return temp.toString();
    }//fn

    /**
     * @param strThaiDate in format dd/mm/yyyy (Thai Year)
     * @param numDays a number of Day
     * @param numMonths a number of Month
     * @param numYears a number of Year
     * @return String of ThaiDate that added (numDays, numMonths, numYears)
     */
  /*public static String ThaiDateAdd(String strThaiDate, int numDays, int numMonths, int numYears){
          String[] strs = strThaiDate.split("/");
          Calendar cal = new GregorianCalendar(Integer.parseInt(strs[2])-543, Integer.parseInt(strs[1])-1, Integer.parseInt(strs[0]));
          cal.add(Calendar.DATE, numDays);		cal.add(Calendar.MONTH, numMonths);		cal.add(Calendar.YEAR, numYears);
          return (cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+(cal.get(Calendar.YEAR)+543));
  }//fn*/

    /**
     * @param strEngDate in format yyyy-mm-dd (End Year)
     * @param numDays a number of Day
     * @param numMonths a number of Month
     * @param numYears a number of Year
     * @return String of EndDate that added (numDays, numMonths, numYears)
     */
    public static String EngDateAdd(String strEngDate, int numDays, int numMonths, int numYears) {
        String[] strs = strEngDate.split("-");
        Calendar cal = new GregorianCalendar(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]) - 1, Integer.parseInt(strs[2]));
        cal.add(Calendar.DATE, numDays);
        cal.add(Calendar.MONTH, numMonths);
        cal.add(Calendar.YEAR, numYears);
        String strYear = String.valueOf(cal.get(Calendar.YEAR));
        String strMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (strMonth.length() < 2) {
            strMonth = "0" + strMonth;
        }//if
        String strDate = String.valueOf(cal.get(Calendar.DATE));
        if (strDate.length() < 2) {
            strDate = "0" + strDate;
        }//if
        return strYear+"-"+strMonth+"-"+strDate;
       // return (cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
    }//fn

    /**
     * @param strDate String
     * @param nDays int
     * @param nMonths int
     * @param nYears int
     * @return String
     */
    public static String getDateAdd(String strDate, int nDays, int nMonths, int nYears) {
        String[] strDates = null;
        Calendar calendar = null;
        String strDateAdd = "";

        if (strDate.indexOf("-") != -1) {
            strDates = strDate.split("-");
            calendar = new GregorianCalendar(Integer.parseInt(strDates[0]), Integer.parseInt(strDates[1]) - 1, Integer.parseInt(strDates[2]));
            calendar.add(Calendar.DATE, nDays);
            calendar.add(Calendar.MONTH, nMonths);
            calendar.add(Calendar.YEAR, nYears);
            strDateAdd = (calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE));
            try {
                strDateAdd = setStrDateToEN(setStrDateToTH(strDateAdd));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (strDate.indexOf("/") != -1) {
            strDates = strDate.split("/");
            calendar = new GregorianCalendar(Integer.parseInt(strDates[2]) - 543, Integer.parseInt(strDates[1]) - 1, Integer.parseInt(strDates[0]));
            calendar.add(Calendar.DATE, nDays);
            calendar.add(Calendar.MONTH, nMonths);
            calendar.add(Calendar.YEAR, nYears);
            strDateAdd = (calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.YEAR) + 543));
            try {
                strDateAdd = setStrDateToTH(setStrDateToEN(strDateAdd));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return strDateAdd;
    }

    /**
     * get result from static int DATEDIFF, MONTHDIFF, YEARDIFF
     *@param strEngDate1 in format yyyy-MM-dd (Eng Year)
     *@param strEngDate2 in format yyyy-MM-dd (Eng Year)
     */
  /*public static void doEngDateDiff(String strEngDate1, String strEngDate2) throws Exception {
          if(strEngDate1 ==null || strEngDate2 ==null || strEngDate1.length()==0 || strEngDate2.length()==0 ){
                  DATEDIFF = 0;
                  MONTHDIFF = 0;
                  YEARDIFF = 0;
                  return;
          }//if

          int dfy=0, dfm=0, dfd=0;
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
          SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
          strEngDate1 = simpledateformat.format(df.parse(strEngDate1));
          strEngDate2 = simpledateformat.format(df.parse(strEngDate2));

          dfm = EngDateDiff(strEngDate1, strEngDate2, MONTH);
          if(dfm > 0){
                  dfd =  EngDateDiff(EngDateAdd(strEngDate1, 0, dfm, 0), strEngDate2, DATE);
                  if(dfd < 0 && dfm > 1){
                          dfm = dfm-1;
                          dfd =  EngDateDiff(EngDateAdd(strEngDate1, 0, dfm, 0), strEngDate2, DATE);
                  }//if
                  if(dfd < 0 && dfm == 1){
                          dfd =  EngDateDiff(strEngDate1, strEngDate2, DATE);
                          dfm = 0;
                  }//if
          }else{
                  dfd =  EngDateDiff(strEngDate1, strEngDate2, DATE);
          }

          if(dfm >= 12){
                  dfy = dfm/12;
                  dfm = dfm%12;
          }//if

          DATEDIFF = dfd;
          MONTHDIFF = dfm;
          YEARDIFF = dfy;
          return;
  }//fn*/

    /**
     * get result from static int DATEDIFF, MONTHDIFF, YEARDIFF
     * @param strThaiDate1 in format dd/mm/yyyy (Thai Year)
     * @param strThaiDate2 in format dd/mm/yyyy (Thai Year)
     */
  /*public static void doThaiDateDiff(String strThaiDate1, String strThaiDate2) throws Exception {
          doEngDateDiff(getStrEngDate(strThaiDate1), getStrEngDate(strThaiDate2));
  }//fn*/

    /**
     *@param strEngDate1 in format yyyy-MM-dd (Eng Year)
     *@param strEngDate2 in format yyyy-MM-dd (Eng Year)
     *@return String number of difference of year, month, year (1 ª’ 2 ‡¥◊Õπ 3 «—π)
     */
  /*public static String EngDateDiff(String strEngDate1, String strEngDate2) throws Exception {
          String strTmp="";	int dfy=0, dfm=0, dfd=0;
          dfm = EngDateDiff(strEngDate1, strEngDate2, MONTH);
          if(dfm > 0){
                  dfd =  EngDateDiff(EngDateAdd(strEngDate1, 0, dfm, 0), strEngDate2, DATE);
                  if(dfd < 0 && dfm > 1){
                          dfm = dfm-1;
                          dfd =  EngDateDiff(EngDateAdd(strEngDate1, 0, dfm, 0), strEngDate2, DATE);
                  }//if
                  if(dfd < 0 && dfm == 1){
                          dfd =  EngDateDiff(strEngDate1, strEngDate2, DATE);
                          dfm = 0;
                  }//if
          }else{
                  dfd =  EngDateDiff(strEngDate1, strEngDate2, DATE);
          }
          if(dfm >= 12){
                  dfy = dfm/12;
                  dfm = dfm%12;
                  strTmp = Integer.toString(dfy) +" ª’ ";
          }//if
          if(dfm > 0)	strTmp += Integer.toString(dfm) +" ‡¥◊Õπ ";
          if(dfd > 0)	strTmp += Integer.toString(dfd) +" «—π ";

          return ThaiUtilities.ASCII2Unicode(strTmp);
  }//fn*/

    /**
     * @param strThaiDate1 in format dd/mm/yyyy (Thai Year)
     * @param strThaiDate2 in format dd/mm/yyyy (Thai Year)
     *@return String number of difference of year, month, year (1 ª’ 2 ‡¥◊Õπ 3 «—π)
     */
  /*public static String ThaiDateDiff(String strThaiDate1, String strThaiDate2) throws Exception {
          return EngDateDiff(getStrEngDate(strThaiDate1), getStrEngDate(strThaiDate2));
  }//fn*/

    /**
     * @param strThaiDate1 in format dd/mm/yyyy (Thai Year)
     * @param strThaiDate2 in format dd/mm/yyyy (Thai Year)
     * @return int of differenct date
     */
  /*public static int ThaiDateDiff(String strThaiDate1, String strThaiDate2, int timefield) throws Exception {
          String[] strs = strThaiDate1.split("/");
          strThaiDate1 = (Integer.parseInt(strs[2])-543)+"-"+(Integer.parseInt(strs[1])-1)+"-"+strs[0];
          strs = strThaiDate2.split("/");
          strThaiDate2 = (Integer.parseInt(strs[2])-543)+"-"+(Integer.parseInt(strs[1])-1)+"-"+strs[0];
          return getDateDiff(strThaiDate1, strThaiDate2, timefield);
  }//fn*/

    /**
     *@param strEngDate1 in format yyyy-MM-dd (Eng Year)
     *@param strEngDate2 in format yyyy-MM-dd (Eng Year)
     *@return int number of difference of date
     */
    public static int EngDateDiff(String strEngDate1, String strEngDate2, int timefield) throws Exception {
        return getDateDiff(strEngDate1, strEngDate2, timefield);
    }//fn

    /*
     *@param String strDate1 format 2002-10-25
     *@param String strDate2 format 2002-10-25
     *@return int number of difference of date
     */
    public static int getDateDiff(String strDate1, String strDate2, int timefield) throws Exception {
        if (strDate1 == null || strDate1.equals("") || strDate2 == null || strDate2.equals(""))
            return 0;

        int nDiff = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date date1 = df.parse(strDate1);
        Date date2 = df.parse(strDate2);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // different date might have different offset
        cal1.setTime(date1);
        long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
        cal2.setTime(date2);
        long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
        // Use integer calculation, truncate the decimals
        int hr1 = (int) (ldate1 / 3600000); //60*60*1000
        int hr2 = (int) (ldate2 / 3600000);
        int days1 = (int) hr1 / 24;
        int days2 = (int) hr2 / 24;
        int dateDiff = days2 - days1;
        int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK)) < 0 ? 1 : 0;
        int weekDiff = dateDiff / 7 + weekOffset;
        int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        switch (timefield) {
            case DATE:
                nDiff = dateDiff;
                break;
            case WEEK:
                nDiff = weekDiff;
                break;
            case MONTH:
                nDiff = monthDiff;
                break;
            case YEAR:
                nDiff = yearDiff;
                break;
        }//switch
        return nDiff;
    }//getDateDiff


    /**
     * @param strThaiDate in format dd/MM/yyyy (Thai Year)
     * @return String of Date in format yyyy-MM-dd (Eng Year)
     */
    public static String getStrEngDate(String strThaiDate) throws Exception {
        if (strThaiDate == null)
            return strThaiDate;
        if (strThaiDate.length() == 0)
            return "";
        String[] strAD = strThaiDate.split("/");
        return ((Integer.parseInt(strAD[2]) - 543) + "-" + strAD[1] + "-" + strAD[0]);
    }//

    /**
     * @param strEngDate in format yyyy-MM-dd (Eng Year)
     * @return String of Date in format yyyy-MM-dd (Eng Year)
     */
    public static String getLastDayOfMonth(String strEngDate) {
        if (strEngDate == null || strEngDate.length() == 0) return "";
        String[] strAD = strEngDate.split("-");
        String strT = Integer.toString(getNumOfDaysInMonth(Integer.parseInt(strAD[1]), Integer.parseInt(strAD[0])));
        if (strT.length() == 1)
            strT = "0" + strT;
        return (strAD[0] + "-" + strAD[1] + "-" + strT);
    }//fn

    /**
     * @param strEngDate in format yyyy-MM-dd (Eng Year)
     * @return String of Date in format yyyy-MM-dd (Eng Year)
     */
    public static String getFirstDayOfMonth(String strEngDate) {
        if (strEngDate == null || strEngDate.length() == 0) return "";
        String[] strAD = strEngDate.split("-");
        return (strAD[0] + "-" + strAD[1] + "-01");
    }//fn

    /**
     * @param nDay
     * @param nMonth
     * @param nYear
     * @return int of DAY_OF_WEEK (SUNDAY=1, SATURDAY=7)
     */
    public static int getDayOfWeek(int nDay, int nMonth, int nYear) {
        Calendar cal = new GregorianCalendar(nYear, nMonth - 1, nDay);
        return cal.get(Calendar.DAY_OF_WEEK);
    }//fn

    /**
     *  get number of day in month
     * @param nMonth
     * @param nYear
     * @return int of Number of Day in month
     */
    public static int getNumOfDaysInMonth(int nMonth, int nYear) {
        int nRetVal = 0;
        if (nMonth == 1 || nMonth == 3 || nMonth == 5 || nMonth == 7 || nMonth == 8 || nMonth == 10 || nMonth == 12)
            nRetVal = 31;
        if (nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11)
            nRetVal = 30;
        if (nMonth == 2)
            if (isLeapYear(nYear))
                nRetVal = 29;
            else
                nRetVal = 28;
        return nRetVal;
    }//

    /**
     * @param nYear
     * @return boolean if isLeapYear return true
     */
    public static boolean isLeapYear(int nYear) {
        if ((nYear % 400 == 0) || ((nYear % 4 == 0) && (nYear % 100 != 0)))
            return true;
        return false;
    }//fn

    public static long getDateDiff(String strStartDate, String strEndDate, String strDelimiter) {

        String[] strStartDates = ReplaceBase.getStrSplit(strStartDate, strDelimiter);
        String[] strEndDates = ReplaceBase.getStrSplit(strEndDate, strDelimiter);

        Date startDate = new Date(Integer.parseInt(strStartDates[0]), Integer.parseInt(strStartDates[1]), Integer.parseInt(strStartDates[2]));
        Date endDate = new Date(Integer.parseInt(strEndDates[0]), Integer.parseInt(strEndDates[1]), Integer.parseInt(strEndDates[2]));
        if (strDelimiter.equals("/")) {
            //startDate = new GregorianCalendar(Integer.parseInt(strStartDates[2]), Integer.parseInt(strStartDates[1]), Integer.parseInt(strStartDates[0])).getTime();
            //endDate = new GregorianCalendar(Integer.parseInt(strEndDates[2]), Integer.parseInt(strEndDates[1]), Integer.parseInt(strEndDates[0])).getTime();
            startDate = new Date(Integer.parseInt(strStartDates[2]), Integer.parseInt(strStartDates[1]), Integer.parseInt(strStartDates[0]));
            endDate = new Date(Integer.parseInt(strEndDates[2]), Integer.parseInt(strEndDates[1]), Integer.parseInt(strEndDates[0]));
        }

        long ldiff = endDate.getTime() - startDate.getTime();
        //System.out.println("  and " + startDate + " is " + (diff / (1000L * 60L * 60L * 24L)) + " days.");

        return (ldiff / (1000L * 60L * 60L * 24L));
    }//end method

    public String getStrDatabasType() {
        return strDatabasType;
    }

    public void setStrDatabasType(String strDatabasType) {
        this.strDatabasType = strDatabasType;
    }

    public static void main(String args[]) {
        try {
            Date startDate = new Date();

            String strStartDate = DateTimeBase.getStrDateEN(startDate);
            /// String strDateAdd=DateTimeBase.getDateAdd(currentDate,10,0,0);
            System.out.println("strStartDate=" + strStartDate);
            String strDateEndFromSystem = "2021-09-27";
            Long longDateDiff = DateTimeBase.getDateDiff(strDateEndFromSystem, strStartDate, "-");
            System.out.println("longDateDiff=" + longDateDiff);

            longDateDiff = DateTimeBase.getDateDiff(strDateEndFromSystem, strStartDate, "-");

            System.out.println("longDateDiff=" + longDateDiff);
            if (longDateDiff < 0) {
                System.out.println("Period has been frozen.");

            }//if

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}//end class



