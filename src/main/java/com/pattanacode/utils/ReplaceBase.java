package com.pattanacode.utils;

import java.io.*;
import java.util.regex.*; // JDK 1.4

public class ReplaceBase implements Serializable {

    private static Pattern pattern = null;
    private static Matcher matcher = null;

    public ReplaceBase() {
        //
    }

    /**
     * Replace String for use in HTML Code
     * @param str
     * @return String
     */
    public static String getStrReplaceToHtml(String str) {
        // replace less than
        pattern = Pattern.compile("<");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("&lt;");
        // replace more than
        pattern = Pattern.compile(">");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("&gt;");
        // replace double quote
        pattern = Pattern.compile("\"");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("&#034;");
        // replace single quote
        pattern = Pattern.compile("'");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("&#039;");
        // replace space
        //pattern = Pattern.compile(" ");
        //matcher = pattern.matcher(str);
        //str = matcher.replaceAll("&nbsp;");
        // replace enter
        pattern = Pattern.compile("\r\n");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("<br>");
        // replace tab
        pattern = Pattern.compile("\t");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("&#x9;");

        return str;
    }

    /**
     * Replace String for use in HTML Code
     * @param str
     * @return String
     */
    public static String getStrReplaceToTagHtml(String str) {
        // replace tab
        pattern = Pattern.compile("&#x9;");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("\t");
        // replace enter
        pattern = Pattern.compile("<br>");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("\r\n");
        // replace less than
        pattern = Pattern.compile("&lt;");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("<");
        // replace more than
        pattern = Pattern.compile("&gt;");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll(">");
        // replace double quote
        pattern = Pattern.compile("&#034;");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("\"");
        // replace single quote
        pattern = Pattern.compile("&#039;");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("'");
        // replace space
        //pattern = Pattern.compile("&nbsp;");
        //matcher = pattern.matcher(str);
        //str = matcher.replaceAll(" ");

        return str;
    }

    public static String setTabToSpaceSize(String str, int nSize) {
        String strSpace = "";
        for(int i=0; i<nSize; i++)
            strSpace+=" ";

        // replace tab to space by manual size
        pattern = Pattern.compile("\t");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll(strSpace);

        return str;
    }

    /**
     * Replace String for use in Javascript Value
     * @param str
     * @return String
     */
    public static String getStrReplaceToJavaScripts(String str) {
        // replace one \ with \\
        pattern = Pattern.compile("\\\\");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("\\\\\\\\");
        // replace double quote with \"
        pattern = Pattern.compile("\"");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("\\\\\"");
        // replace single quote with \'
        pattern = Pattern.compile("'");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("\\\\'");
        return str;
    }



    public static String getStrReplaceMoney(String strMoney) {
        return strMoney.replaceAll(",","");
    }


    // FixStrLength
    public static String getStrFixLength(String strText, int nLength, String strEnd) {
        if (strText.length() > nLength)
            return strText.substring(0, nLength) + strEnd;
        else
            return strText;
    }

    public static String getStrFixLength(String strText, int nLength) {
        if (strText.length() > nLength)
            return strText.substring(0, nLength) + "...";
        else
            return strText;
    }


    /**
     *	Classname : ReplaceBase<br>
     *	Description : Replace oldString by newString<br>
     *	<pre>Example usage:
     *	String s = "aaaabbbb";
     *	String oldString = "aaaa";
     *	String newString = "cccc";
     *	ReplaceStr hStr = new ReplaceStr();
     *	hStr = hStr.replace(s, oldString, newString);
     *	hStr => "ccccbbbb"
     *	OR
     *	String hStr = ReplaceStr.replace("aaaabbbb","aaaa","cccc");
     *	hStr => "ccccbbbb"</pre>
     *  @version 1.0
     */

    // method
    /**
     *	Returns a new string resulting from replacing all occurrences of oldString in this string with newString
     */
  /*public static String replaceString(String s, String oldString, String newString) {
  // In a string replace oldString substring with newString
    if (s.equals("")) return "";
    String res = "";
    int i = s.indexOf(oldString,0);
    int lastpos = 0;
    while (i != -1) {
          res += s.substring(lastpos,i) + newString;
          lastpos = i + oldString.length();
          i = s.indexOf(oldString,lastpos);
    }
    res += s.substring(lastpos);  // the rest
    return res;
  }*/

    // Split String by use delimiter
    public static String[] getStrSplit(String str, String dim) {
        int arrlength = 0;
        int start = 0, end = 0;
        int i = 0;

        for (i = 0; i < str.length(); i++) {
            if (i + dim.length() > str.length())
                end = str.length();
            else
                end = i + dim.length();
            if (str.substring(i, end).equals(dim)) {
                arrlength++;
                i = end - 1;
            }
        }

        String[] toks = new String[arrlength + 1];
        arrlength = 0;
        for (i = 0; i < str.length(); i++) {
            if (i + dim.length() > str.length())
                end = str.length();
            else
                end = i + dim.length();
            if (str.substring(i, end).equals(dim)) {
                toks[arrlength++] = str.substring(start, end - dim.length());
                start = i + dim.length();
                i = end - 1;
            }
        }
        toks[arrlength++] = str.substring(start, str.length());
        return toks;
    }

    //Add On Change EN Number To TH Number
    public static String getStrChangeNumberEnToTH(String str){
        String temp="";
        String strText="";

        for(int i=0;i<str.length();i++){
            temp="";
            switch(str.charAt(i)){
                case '1': temp="?";break;
                case '2': temp="?";break;
                case '3': temp="?";break;
                case '4': temp="?";break;
                case '5': temp="?";break;
                case '6': temp="?";break;
                case '7': temp="?";break;
                case '8': temp="?";break;
                case '9': temp="?";break;
                case '0': temp="?";break;
                default :temp=""+str.charAt(i);
            }
            strText+=temp;
        }
        return strText;
    }



}

